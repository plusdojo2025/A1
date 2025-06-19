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
(1,'北海道','hokkaido.png'),
(2,'東北','touhoku.png'),
(3,'関東','kanto.png'),
(4,'中部','tyubu.png'),
(5,'近畿','kinki.png'),
(6,'中国','tyugoku.png'),
(7,'四国','sikoku.png'),
(8,'九州(沖縄含む)','kyusyu.png'),
(9,'都道府県制覇','japan.png');

/*badgesテーブルのデータを確認*/
SELECT*FROM badges;