CREATE SCHEMA IF NOT EXISTS `i_cinema`;
USE `i_cinema`;

DROP TABLE IF EXISTS Cards;

CREATE TABLE Cards (
    card_id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL,
    card_number VARCHAR(16) NOT NULL,
    expiry_date DATE NOT NULL,
    cvv VARCHAR(3) NOT NULL,
    card_holder_name VARCHAR(100) NOT NULL,
    available_bal INT NOT NULL,
    username VARCHAR(50) NOT NULL
);

INSERT INTO Cards (booking_id, card_number, expiry_date, cvv, card_holder_name, available_bal, username) VALUES
(1, '1234567812345678', '2025-12-01', '123', 'John Doe', 10000, 'johndoe'),
(2, '2345678923456789', '2023-11-01', '234', 'Jane Smith', 2000, 'janesmith'),
(3, '3456789034567890', '2025-10-01', '345', 'Alice Johnson', 1500, 'alicejohnson'),
(4, '4567890145678901', '2026-09-01', '456', 'Bob Brown', 2500, 'bobbrown'),
(5, '5678901256789012', '2027-08-01', '567', 'Modin Khan', 300000, 'patan');


COMMIT;

SELECT * FROM Cards;