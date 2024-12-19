CREATE SCHEMA IF NOT EXISTS `i_cinema`;
USE `i_cinema`;

DROP TABLE if exists shows;
DROP TABLE if exists theatres;

CREATE TABLE theatres (
    theatre_id INT AUTO_INCREMENT PRIMARY KEY,
    theatre_name VARCHAR(255) NOT NULL,
    movie_id BIGINT NOT NULL,
    location VARCHAR(255) NOT NULL,
    total_seats INT NOT NULL,
    show_date DATE NOT NULL
);

INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("DRC Cinemas: BM Habitat Mall",1,"Gokulam Mysore",180,"2024-12-09");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("DRC Cinemas: BM Habitat Mall",1,"Gokulam Mysore",180,"2024-12-10");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("DRC Cinemas: BM Habitat Mall",6,"Gokulam Mysore",180,"2024-12-11");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("DRC Cinemas: BM Habitat Mall",6,"Gokulam Mysore",180,"2024-12-12");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("DRC Cinemas: BM Habitat Mall",3,"Gokulam Mysore",180,"2024-12-13");

INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Mall of Mysuru",1,"MG Road Mysore",180,"2024-12-09");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Mall of Mysuru",6,"MG Road Mysore",180,"2024-12-09");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Mall of Mysuru",6,"MG Road Mysore",180,"2024-12-10");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Mall of Mysuru",4,"MG Road Mysore",180,"2024-12-11");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Mall of Mysuru",3,"MG Road Mysore",180,"2024-12-12");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Mall of Mysuru",6,"MG Road Mysore",180,"2024-12-13");

INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("PVR Nexus Mall Centre City",1,"Hydreali Road Mysore",180,"2024-12-09");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("PVR Nexus Mall Centre City",6,"Hydreali Road Mysore",180,"2024-12-09");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("PVR Nexus Mall Centre City",1,"Hydreali Road Mysore",180,"2024-12-10");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("PVR Nexus Mall Centre City",6,"Hydreali Road Mysore",180,"2024-12-10");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("PVR Nexus Mall Centre City",6,"Hydreali Road Mysore",180,"2024-12-11");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("PVR Nexus Mall Centre City",7,"Hydreali Road Mysore",180,"2024-12-12");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("PVR Nexus Mall Centre City",3,"Hydreali Road Mysore",180,"2024-12-13");


INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Centro",1,"Nilgiri Road Mysore",180,"2024-12-09");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Centro",6,"Nilgiri Road Mysore",180,"2024-12-09");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Centro",6,"Nilgiri Road Mysore",180,"2024-12-10");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Centro",3,"Nilgiri Road Mysore",180,"2024-12-11");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Centro",3,"Nilgiri Road Mysore",180,"2024-12-12");
INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("INOX: Centro",3,"Nilgiri Road Mysore",180,"2024-12-13");

INSERT INTO theatres (theatre_name,movie_id,location,total_seats,show_date) VALUES ("DRC Cinemas: BM Habitat Mall",13,"Gokulam Mysore",180,"2024-12-09");

CREATE TABLE shows (
    show_id INT AUTO_INCREMENT PRIMARY KEY,
    theatre_id INT NOT NULL,
    show_time TIME NOT NULL,
    available_seats INT NOT NULL
);

INSERT INTO shows (theatre_id, show_time,available_seats) VALUES 
(1,"18:45",134),
(1,"21:45",148),
(2,"10:45",140),
(2,"13:45",120),
(3,"18:45",166),
(3,"23:05",140),
(3,"18:45",180),
(4,"11:45",180),
(5,"11:45",180),
(5,"13:25",180),
(6,"10:00", 180),
(6,"12:45",180),
(7, "15:35",46),
(7, "18:45",110),
(7, "21 :55",140),
(8, "12:05",120),
(8, "16:45",180),
(9, "09:45",120),
(9, "15:45",120),
(10, "11:45",160),
(11, "10:20",170),
(11, "13:05",170),
(12, "14:15" ,170),
(12, "20:00" ,130),
(13, "10:20",180),
(13, "19:25",170),
(14, "15:35",110),
(15, "21:17",180),
(15, "00:15",180),
(16, "14:12", 180),
(17, "13:07", 180),
(18, "19:16", 180),
(18, "22:18", 180),
(19, "21:10" , 180),
(20, "12:34", 180),
(21, "11:37", 180),
(22,"10:00", 180),
(22, "15:45",180),
(23, "20:06", 180),
(24, "13:18", 180),
(24, "21:07", 180),
(25, "16:45", 180),
(25, "22:15", 180);
commit;
select * from theatres;
select * from shows;