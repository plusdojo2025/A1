CREATE TABLE visitors (
    visitor_id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    componion VARCHAR(255),
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    prefecture_id INT NOT NULL,
    place VARCHAR(255),
    thought TEXT,
    emotion_id INT,
    photo1 VARCHAR(255),
    photo2 VARCHAR(255),
    photo3 VARCHAR(255),
    photo4 VARCHAR(255),
    photo5 VARCHAR(255),  
    PRIMARY KEY(visitor_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (prefecture_id) REFERENCES prefectures(prefecture_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (emotion_id) REFERENCES emotions(emotion_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);