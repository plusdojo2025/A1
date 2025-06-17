CREATE TABLE gacha (
    gacha_id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(255) NOT NULL,
    turned_date DATETIME NOT NULL,
    pickup_id INT NOT NULL,
    PRIMARY KEY(gacha_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (pickup_id) REFERENCES pickups(pickup_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE    
);

INSERT INTO gacha(
gacha_id,
user_id,
turned_date,
pickup_id   
)VALUES
/*鬼瓦*/
(1,'onigawara',2025-05-07,1),
(2,'onigawara',2025-04-02,3),
(3,'onigawara',2024-11-11,5),
(4,'onigawara',2025-05-30,8),
(5,'onigawara',2025-03-10,7),
/*神宮寺*/
(6,'zinguuzi',2024-12-25,12),
(7,'zinguuzi',2025-01-11,9),
(8,'zinguuzi',2025-02-26,10),
/*黒川*/
(10,'kuro',2024-09-11,23),
(11,'kuro',2024-11-21,15),
(12,'kuro',2024-12-31,16),
(13,'kuro',2025-03-15,22),
(14,'kuro',2025-04-11,18),
(15,'kuro',2025-04-29,14),
(16,'kuro',2025-05-18,25);

