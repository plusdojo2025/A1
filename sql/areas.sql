CREATE TABLE areas (
    area_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(10) NOT NULL,
    prefecture INT,
    PRIMARY KEY(area_id),
    FOREIGN KEY (prefecture) REFERENCES prefectures(prefecture_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);