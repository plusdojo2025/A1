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
/*dojouser1*/
(1,'dojouser1','2025-05-07',1),
(2,'dojouser1','2025-04-02',3),
(3,'dojouser1','2024-11-11',5),
(4,'dojouser1','2025-05-30',8),
(5,'dojouser1','2025-03-10',7),
(6,'dojouser1','2024-12-25',12),
/*dojouser3*/
(7,'dojouser3','2025-01-11',9),
(8,'dojouser3','2025-02-26',10),
(9,'dojouser3','2024-09-11',23),
(10,'dojouser3','2024-11-21',15),
(11,'dojouser3','2024-12-31',16),
(12,'dojouser3','2025-03-15',22),
(13,'dojouser3','2025-04-11',18),
(14,'dojouser3','2025-04-29',14),
(15,'dojouser3','2025-05-18',25);

/*gachaテーブルのデータを確認*/
SELECT*FROM gacha;








