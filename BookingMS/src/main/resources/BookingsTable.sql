CREATE SCHEMA IF NOT EXISTS `i_cinema`;
USE `i_cinema`;

DROP TABLE IF EXISTS bookings_bookedseats;
DROP TABLE IF EXISTS bookings;

CREATE TABLE bookings (
    booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    show_id INT,
    movie_id BIGINT ,
    theatre_id INT ,
    total_price DOUBLE ,
    booking_status VARCHAR(5) ,
    booking_time TIME 
);

CREATE TABLE bookings_bookedseats (
    booking_id BIGINT,
    seat_number VARCHAR(255),
    PRIMARY KEY (booking_id, seat_number),
    FOREIGN KEY (booking_id) REFERENCES bookings(booking_id) ON DELETE CASCADE
);

COMMIT;

SELECT * FROM bookings;
SELECT * FROM bookings_bookedseats;