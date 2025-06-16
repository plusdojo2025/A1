CREATE TABLE emotions (
    emotion_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL,
    emoji CHAR(1) CHARACTER SET utf8mb4 NOT NULL,
    PRIMARY KEY(emotion_id)
);