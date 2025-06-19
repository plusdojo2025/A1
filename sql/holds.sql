CREATE TABLE holds (
    hold_id INT NOT NULL AUTO_INCREMENT,
    badge_id INT NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    date_acquisition DATETIME NOT NULL,
    PRIMARY KEY(hold_id),
    FOREIGN KEY (badge_id) REFERENCES badges(badge_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO holds(
hold_id,
badge_id,
user_id,
date_acquisition)VALUES
-- dojouser2 北海道制覇
(1,1,'dojouser2','2025-02-06'),
-- dojouser2 関東制覇
(2,3,'dojouser2','2025-03-05'),
-- dojouser3 北海道制覇
(3,1,'dojouser3','2024-02-05'),
-- dojouser3 中部制覇
(4,4,'dojouser3','2025-06-15'),
-- dojouser3 四国制覇
(5,6,'dojouser3','2025-01-13');

/*holdsテーブルのデータを確認*/
SELECT*FROM holds;

