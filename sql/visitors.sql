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

INSERT INTO visitors
(visitor_id,
user_id,
title,
componion,
start_date,
end_date,
prefecture_id,
place,
thought,
emotion_id
)VALUES
/*鬼瓦*/
-- ゼミのメンバーと初めて旅行へ。
(1,
'zinguuzi',
'ゼミのメンバーと初めて旅行へ。',
'ゼミのメンバー',
'2025-03-10',
'2025-03-12',
23,
'ジブリパーク,CHOUOHC',
'今まで行ったことのない県だったのでとても刺激的で楽しかった。',
1),
-- 友達お誕生日旅行
(2,
'zinguuzi',
'友達お誕生日旅行',
'友達',
'2024-12-11',
'2024-12-13',
27,
'道頓堀・通天閣・ユニバ・大阪城',
'1日目、大阪名物の粉物串カツを食べ歩いた。2日目、丸一日ユニバで楽しむ！3日日、大阪をお散歩しながら大阪城を見に行く久しぶりの大阪を十分楽しめ、食べたいもの見たものを全てできた。',
8),
-- 初夜行バス旅行
(3,
'zinguuzi',
'初夜行バス旅行',
'友達',
'2023-11-10',
'2023-11-12',
26,
'清水寺',
'京都で紅葉を見た。丁度見頃だったのでこの時期に来年も行きたい。',
8),
-- 沖縄自然巡り
(4,
'zinguuzi',
'沖縄自然巡り',
'無し',
'2023-04-04',
'2023-04-06',
47,
'友人宅、国際通りなど',
'沖縄の自然を十分に満喫することができた。',
1),
-- 牡鹿半島旅行
(5,
'zinguuzi',
'牡鹿半島旅行',
'友達',
'2025-02-23',
'2025-02-24',
4,
'おしか家族村オートキャンプ場',
'初の牡鹿半島旅行。自然豊かで夜は星がたくさん見えてとてと綺麗だった。コテージでのバーベキューは一味違った。とても寒かった。',
1),
-- 母の行きたい旅行1日目
(6,
'zinguuzi',
'母の行きたい旅行1日目',
'家族',
'2024-08-13',
'2024-08-13',
27,
'彦根城（登城）・近江神宮',
'彦根城:日本最古の木造のお城、すごく急な階段を登り続ける必要があってきつかった。近江神宮:ちはやふるの聖地で大好きなちはやふるの世界観に入ることができて感動しました！',
6),
-- 母の行きたい旅行1日目
(7,
'zinguuzi',
'母の行きたい旅行1日目',
'家族',
'2024-08-13',
'2024-08-13',
28,
'姫路城',
'今まで見てきた中で白くて綺麗なお城だった。また城自体も大きく感じ、1日目の夜にヴィランズのお城になってて面白かった',
6),
-- 母の行きたい旅行2日目
(8,
'zinguuzi',
'母の行きたい旅行2日目',
'家族',
'2024-08-14',
'2024-08-14',
36,
'鳴門海峡',
'船に乗って海峡に行く。時間帯的にも一番渦巻きが発生する時間帯だったこともあり見ることができた',
6);



