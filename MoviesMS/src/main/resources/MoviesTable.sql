CREATE SCHEMA IF NOT EXISTS `i_cinema`;
USE `i_cinema`;

-- Drop tables if they exist
DROP TABLE IF EXISTS movie_languages;
DROP TABLE IF EXISTS movie_genres;
DROP TABLE IF EXISTS ratings;
DROP TABLE IF EXISTS movies;

-- Create movies table
CREATE TABLE movies (
    movie_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    release_date DATE NOT NULL,
    duration TIME NOT NULL,
    rating DOUBLE NOT NULL,
    censor_rating VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    average_rating DOUBLE DEFAULT 0
);

-- Insert data into movies table
INSERT INTO movies (name, release_date, duration, rating, censor_rating, description, image_url, average_rating) VALUES
('Pushpa 2: The Rule', '2024-12-05', '03:20:00', 9.0, 'UA', 'The Rule begins on 5th December 2024.', 'pushpa2.png', 9.3),
('Deadpool & Wolverine', '2024-07-26', '02:08:00', 8.7, 'A', 'A listless Wade Wilson toils away in civilian life with his days as the morally flexible mercenary, Deadpool, behind him. But when his homeworld faces an existential threat, Wade must reluctantly suit-up again with an even more reluctant Wolverine.', 'deadpool_and_wolverine.jpg', 6.7),
('Moana 2', '2024-11-29', '01:40:00', 8.4, 'U', 'After receving an unexpected call from her wayfinding ancestors, Moana journeys alongside Maui and a new crew to the far seats of Oceania and into dangerous, long-lost waters for an adventure unlike anything she has ever faced.', 'moana_2.jpg', 8.9),
('The Dark Knight', '2018-06-18', '02:32:00', 8.5, '13+', 'With Lieutenant Jim Gordon and district attorney Harvey Dent, Batman sets out to destroy organized crime but they find themselves prey to a criminal mastermind called the Joker', 'the_dark_knight.jpg', 8.6),
('GTMAX', '2024-11-24', '02:45:00', 8.4, 'A', 'When a notorious gang of bikers recruits her brother for a heist, a former motocross champion must face her deepest fears to keep her family safe.', 'gt_max.jpg', 8.9),
('Amaran', '2024-10-31', '02:49:00', 9.2, 'UA', 'Amaran tells the inspiring story of Major Mukund Varadarajan, an Indian Army officer honoured with Ashoka Chakra.', 'amaran.png', 9.4),
('Lucky Bhaskar', '2024-10-31', '02:30:00', 9.1, 'UA', 'Tale of Bhaskar, a common man who turns into a shrewd banker to make ends meet.', 'luckybhaskar.png', 9.3),
('Kantara: A Legend Chapter 1', '2025-10-02', '3:05:00', 9.0, 'UA', 'A Legend was born! A prequel to Kantara(2002)', 'kantara.png', 9.0),
('Mufasa: The Lion King', '2024-12-20', '2:25:00', 9.1, 'U', 'Journey of an orphaned cub called Mufasa, with a group of misfits.', 'mufasa.png', 8.7),
('Veeramallu', '2025-03-28', '3:05:00', 9.0, 'UA', 'Depecting life of legendary outlaw VerraMallu, the film stars Pawan Kalyan.','veeramallu.png',8.7),
('Zebra','2024-11-22', '02:44:00', 8.7, 'UA', 'Banking on Survival is a gripping tale of love, sacrifice and a fine line between right and wrong.', 'zebra.png', 8.8),
('Bagheera','2024-10-31','02:38:00', 9.1, 'UA', "The countdown to justice begins", 'bagheera.png', 9.0),
('Bhairathi Ranagal', '2024-11-15', '02:14:00', 9.3, 'UA', 'How did Ranagal rise to prominence? Who was he before he became the most dreaded ganster.', 'bhairathi_ranagal.png', 9.1),
('The Sabarmathi Report', '2024-11-15', '02:03:00', 8.5, 'UA', 'Inspired by true events that took place in Sabarmathi express on 27 Feb 2022.', 'the_sabarmati_report.png', 8.0),
('Game Changer', '2025-01-10', '02:45:00', 7.6, 'UA', 'Get ready to see Ram Charan swag like never before!', 'game_changer.png', 7.2),
('Vidhuthalai Part-2', '2024-12-20', '02:52:00', 9.1, 'UA', 'Can a simple abiding school teacher transform into a people leader?', 'vidhuthalai_part2.png', 9.0),
('Marco', '2024-12-10', '03:05:00', 8.7, 'A', 'No mercy. It is payback time', 'marco.png', 8.9),
('UI', '2024-12-10', '02:34:00', 7.0, 'UA', 'Pan India film acted and directed by Upendra', 'ui.png',7.1),
('Kraven The Hunter', '2024-12-13', '02:09:00', 5.0, 'A', 'Sergei, a big game hunter takes a magical serum that gives him superhuman capabilities', 'kraven_the_hunter.png', 6.0),
('Max', '2024-12-25', '02:57:00', 7.6, 'UA', 'Arjun a law and order inspector comes back from 2 month suspension into an unexpected situation. Can Arjun save the day?', 'max.png',8.2),
('Bhool Bhulaiyaa3', '2024-11-01', '02:38:00', 6.2, 'UA', 'The gates of Haveli are open.', 'bhool_bhulaiyaa_3.png',6.2),
('Dhadak 2', '2025-02-01', '02:38:00', 8.2, 'UA', 'Lovers are here to disturb peace.', 'dhadak2.png',8.2),
('Dilwale Dulhania Le Jayenge', '2002-02-01', '03:28:00', 8.2, 'UA', 'Lovers are here!', 'dilwale_dulhania_le_jayenge.png',8.2);

-- Create ratings table
CREATE TABLE ratings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    movie_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE
);

-- Create movie_genres table
CREATE TABLE movie_genres (
    movie_id BIGINT NOT NULL,
    genre VARCHAR(255) NOT NULL,
    PRIMARY KEY (movie_id, genre),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE
);

-- Insert data into movie_genres table
INSERT INTO movie_genres (movie_id, genre) VALUES
(1, 'Action'),
(1, 'Thriller'),
(2, 'Action'),
(2, 'Adventure'),
(2, 'Comedy'),
(3, 'Action'),
(3, 'Adventure'),
(3, 'Comedy'),
(3, 'Fantasy'),
(4, 'Action'),
(4, 'Drama'),
(4, 'Crime'),
(4, 'Thriller'),
(5, 'Drama'),
(5, 'Action'),
(5, 'Crime'),
(6, 'Action'),
(6, 'Drama'),
(6, 'Thriller'),
(7, 'Crime'),
(7, 'Drama'),
(7, 'Thriller'),
(8, 'Thriller'),
(8, 'Drama'),
(8, 'Adventure'),
(9, 'Adventure'),
(9, 'Fantasy'),
(9, 'Comedy'),
(10, 'Action'),
(10, 'Adventure'),
(10, 'Thriller'),
(11, 'Action'),
(11, 'Comedy'),
(12, 'Action'),
(12, 'Thriller'),
(13, 'Action'),
(13, 'Crime'),
(13, 'Drama'),
(14, 'Drama'),
(15, 'Action'),
(15, 'Drama'),
(16, 'Action'),
(16, 'Crime'),
(16, 'Drama'),
(17, 'Action'),
(18, 'Sci-Fi'),
(18, 'Action'),
(18, 'Thriller'),
(19, 'Action'),
(19, 'Adventure'),
(20, 'Drama'),
(20, 'Thriller'),
(21, 'Horror'),
(21, 'Comedy'),
(22, 'Romance'),
(22, 'Drama'),
(23, 'Romance'),
(23, 'Drama');

-- Create movie_languages table
CREATE TABLE movie_languages (
    movie_id BIGINT NOT NULL,
    language VARCHAR(255) NOT NULL,
    PRIMARY KEY (movie_id, language),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id) ON DELETE CASCADE
);

-- Insert data into movie_languages table
INSERT INTO movie_languages (movie_id, language) VALUES
(1, 'Telugu'),
(1, 'Kannada'),
(1, 'Hindi'),
(2, 'English'),
(2, 'Hindi'),
(2, 'Telugu'),
(2, 'Tamil'),
(3, 'English'),
(3, 'Hindi'),
(4, 'English'),
(5, 'English'),
(5, 'Hindi'),
(6, 'Hindi'),
(6, 'English'),
(6, 'Tamil'),
(6, 'Kannada'),
(7, 'Telegu'),
(7, 'Malyalam'),
(7, 'Hindi'),
(7, 'English'),
(8, 'Kannada'),
(8, 'English'),
(9, 'English'),
(9, 'Hindi'),
(10, 'Tamil'),
(10, 'Kannada'),
(10, 'Telugu'),
(10, 'Hindi'),
(11, 'Tamil'),
(11, 'Kannada'),
(11, 'Telugu'),
(11, 'Hindi'),
(12, 'Kannada'),
(12, 'Telugu'),
(13, 'Kannada'),
(14, 'Hindi'),
(15, 'Malyalam'),
(15, 'Kannada'),
(15, 'Hindi'),
(15, 'Tamil'),
(16, 'Tamil'),
(17, 'English'),
(18, 'Kannada'),
(19, 'English'),
(19, 'Hindi'),
(20, 'Kannada'),
(21, 'Hindi'),
(22, 'Hindi'),
(23, 'Hindi');

-- Commit the transactions
COMMIT;

-- Select data to verify
SELECT * FROM movies;
SELECT * FROM ratings;
SELECT * FROM movie_genres;
SELECT * FROM movie_languages;