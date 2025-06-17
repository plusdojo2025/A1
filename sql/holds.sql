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
-- 鬼瓦　四国地方制覇
(1,6,'onigawara,',2025-01-13),
-- 神宮寺　北海道地方制覇
(2,1,'zinguuzi,',2025-02-06),
-- 神宮寺　関東地方制覇
(3,3,'zinguuzi,',2025-03-05),
-- 黒川　北海道地方制覇
(4,1,'kuro,',2024-02-05),
-- 黒川　中部地方制覇
(5,4,'kuro,',2025-06-15);

