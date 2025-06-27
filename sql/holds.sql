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
(5,7,'dojouser3','2025-01-13'),
-- dojouser4 北海道制覇
(6,1,'dojouser4','2025-05-07'),
-- dojouser4 東北制覇
(7,2,'dojouser4','2019-10-17'),
-- dojouser4 関東制覇
(8,3,'dojouser4','2020-11-11'),
-- dojouser4 中部制覇
(9,4,'dojouser4','2021-12-20'),
-- dojouser4 近畿制覇
(10,5,'dojouser4','2022-10-04'),
-- dojouser4 中国制覇
(11,6,'dojouser4','2023-06-08'),
-- dojouser4 四国制覇
(12,7,'dojouser4','2023-11-01'),
-- dojouser4 九州(沖縄含む
(13,8,'dojouser4','2024-10-21'),
-- dojouser4 都道府県制覇
(14,9,'dojouser4','2025-05-07');



/*holdsテーブルのデータを確認*/
SELECT*FROM holds;

