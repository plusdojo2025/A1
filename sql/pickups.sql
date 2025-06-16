CREATE TABLE pickups (
    pickup_id INT NOT NULL AUTO_INCREMENT,
    user VARCHAR(255) NOT NULL,
    prefecture INT NOT NULL,
    place VARCHAR(50),
    remarks TEXT,
    PRIMARY KEY(pickup_id),
    FOREIGN KEY (user) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (prefecture) REFERENCES prefectures(prefecture_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);