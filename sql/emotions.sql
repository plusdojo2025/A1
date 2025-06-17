CREATE TABLE emotions (
    emotion_id INT NOT NULL AUTO_INCREMENT,
    emotion_name VARCHAR(10) NOT NULL,
    emoji VARCHAR(10) CHARACTER SET utf8mb4 NOT NULL,
    PRIMARY KEY(emotion_id)
);



INSERT INTO emotions(emotion_id,emotion_name,emoji) VALUES
(1,'嬉しい','😊'),
(2,'ハート','❤️'),
(3,'無感情','😑'),
(4,'心配・困惑','😥'),
(5,'反省','😔'),
(6,'暑い','🥵'),
(7,'寒い','🥶'),
(8,'ときめき','🥰'),
(9,'悲しい','😢'),
(10,'感動','🥺'),
(11,'おにぎり','🍙'),
(12,'ラーメン','🍜'),
(13,'お寿司','🍣'),
(14,'お肉','🥩'),
(15,'ケーキ','🍰'),
(16,'アイスクリーム','🍦'),
(17,'車','🚗'),
(18,'バス','🚌'),
(19,'新幹線','🚄'),
(20,'電車','🚃'),
(21,'飛行機','✈️'),
(22,'船','🚢');


/*emotionsテーブルのデータを確認*/
SELECT*FROM emotions;