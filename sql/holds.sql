CREATE TABLE holds (
    hold_id INT NOT NULL AUTO_INCREMENT,
    badge INT NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    date_acquisition DATETIME NOT NULL,
    PRIMARY KEY(hold_id),
    FOREIGN KEY (badge) REFERENCES badges(badge_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
