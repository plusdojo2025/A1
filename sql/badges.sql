CREATE TABLE badges (
    badge_id INT NOT NULL AUTO_INCREMENT,
    badge_name VARCHAR(20) NOT NULL,
    badge_image VARCHAR(255) NOT NULL,
    PRIMARY KEY(badge_id)
);

INSERT INTO badges
(badge_id,
badge_name,
badge_image
)VALUES
(1,'北海道地方',''),
(2,'東北地方',''),
(3,'関東地方',''),
(4,'中部地方',''),
(5,'近畿地方',''),
(6,'中国地方',''),
(7,'四国地方',''),
(8,'九州地方(沖縄含む)',''),
(9,'都道府県制覇','');