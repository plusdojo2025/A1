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