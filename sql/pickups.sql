CREATE TABLE pickups (
    pickup_id INT NOT NULL AUTO_INCREMENT,
    user_id VARCHAR(255) NOT NULL,
    prefecture_id INT NOT NULL,
    pickup_place VARCHAR(50),
    remarks TEXT,
    PRIMARY KEY(pickup_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (prefecture_id) REFERENCES prefectures(prefecture_id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO pickups
(pickup_id,
user_id,
prefecture_id,
pickup_place,
remarks
)VALUES
/*dojouser1*/
-- 舞妓体験専門店
(1,
'dojouser1',
26,
'舞妓体験専門店',
'花魁と舞妓さんになりたい'),
-- 札幌ラーメン悠
(2,
'dojouser1',
1,
'札幌ラーメン悠',
'ラーメンが食べたい！！！！！！！'),
-- 箱根
(3,
'dojouser1',
14,
'箱根',
'年内に行きたい'),
-- 仙台Loose Bar
(4,
'dojouser1',
4,
'仙台Loose Bar',
'楽しくお酒を飲みたい'),
-- 岩手わんこそばを食べれる場所
(5,
'dojouser1',
3,
'わんこそばを食べれる場所',
'目指せ200万杯'),
-- 鹿児島県　屋久島
(6,
'dojouser1',
46,
'屋久島',
'神秘的な自然と縄文杉を見に行きたい'),
-- 山口県　角島大橋
(7,
'dojouser1',
35,
'角島大橋',
'青い海の上をまっすぐ進む絶景ドライブしたい'),
-- 大分県　湯布院
(8,
'dojouser1',
44,
'湯布院',
'のんびりカフェ巡りと温泉を楽しみたい'),
-- 長岡
(9,
'dojouser1',
15,
'長岡',
'花火みたい！！！！！！！！！'),
-- 沖縄
(10,
'dojouser1',
47,
NULL,
'夏に行きたい'),
-- 鳥取砂丘
(11,
'dojouser1',
31,
'鳥取砂丘',
'コナン聖地いきたい！！！！！！！'),
/*dojouser3*/
-- 餃子通り
(12,
'dojouser3',
9,
'餃子通り',
'餃子巡りしたい'),
-- 千里浜なぎさドライブウェイ
(13,
'dojouser3',
17,
'千里浜なぎさドライブウェイ',
'日本唯一、砂浜を車で走れる海岸。夕日も絶景。'),
/*dojouser3*/
-- 加茂水族館
(14,
'dojouser3',
6,
'加茂水族館',
'たくさんクラゲをみたい'),
-- 平和記念公園
(15,
'dojouser3',
34,
'平和記念公園',
'一度も行ったことないので行ってみたい'),
-- 金刀比羅宮（ことひらぐう）
(16,
'dojouser3',
37,
'金刀比羅宮',
'「こんぴらさん」の愛称で親しまれる海の神様'),
-- 留久
(17,
'dojouser3',
21,
'留久',
'牛タン食べたい'),
-- もつ鍋が食べれる場所
(18,
'dojouser3',
40,
'もつ鍋が食べれる場所',
'前回の福岡旅行で〆のちゃんぽんを食べることができなかったため、リベンジをしたい。'),
-- 佐渡島
(19,
'dojouser3',
15,
'佐渡島',
'佐渡の景色と汽船をまた堪能したい'),
-- 茨城県	国営ひたち海浜公園	
(20,
'dojouser3',
8,
'国営ひたち海浜公園',
'ネモフィラやコキアの絶景を見たい'),
-- 東京都	神楽坂
(21,
'dojouser3',
13,
'神楽坂',
'路地裏グルメと和風の雰囲気を楽しみたい'),
-- 福島県	大内宿
(22,
'dojouser3',
7,
'大内宿',
'かやぶき屋根の集落でそばを食べたい'),
-- 山形県	銀山温泉
(23,
'dojouser3',
6,
'銀山温泉',
'大正ロマンな雰囲気の中で泊まってみたい'),
-- 青森県	十和田湖・奥入瀬渓流
(24,
'dojouser3',
2,
'十和田湖・奥入瀬渓流',
'大自然の中をハイキングしたい'),
-- 秋田県	乳頭温泉郷
(25,
'dojouser3',
5,
'乳頭温泉郷',
'雪見風呂を体験したい'),

INSERT INTO pickups (user_id, prefecture_id, pickup_place, remarks) VALUES
('dojouser4', 26, '嵐山', '竹林と渡月橋をゆっくり歩きたい'),
('dojouser4', 13, '東京スカイツリー', '夜景がきれいだから一度は登ってみたい'),
('dojouser4', 27, '道頓堀', '本場のお好み焼きを食べたい'),
('dojouser4', 40, '太宰府天満宮', '学問の神様にお参りしたい'),
('dojouser4', 1, '小樽運河', 'レトロな雰囲気の街並みを楽しみたい'),
('dojouser4', 23, '名古屋城', '歴史あるお城を見学したい'),
('dojouser4', 34, '宮島（厳島神社）', '海に浮かぶ鳥居を実際に見てみたい'),
('dojouser4', 30, 'アドベンチャーワールド', 'パンダに会いたい'),
('dojouser4', 16, '黒部ダム', '迫力ある放水を見たい'),
('dojouser4', 36, '鳴門の渦潮', '自然の力を感じられそう'),
('dojouser4', 22, '三保の松原', '富士山とのコントラストを見てみたい'),
('dojouser4', 46, '屋久島', 'もののけ姫の世界を体験したい'),
('dojouser4', 4, '松島', '日本三景の一つを見てみたい'),
('dojouser4', 33, '倉敷美観地区', '白壁の町並みで写真を撮りたい'),
('dojouser4', 14, '江ノ島', '海辺でリフレッシュしたい'),
('dojouser4', 31, '砂の美術館', '砂の彫刻を実際に見てみたい'),
('dojouser4', 18, '東尋坊', '断崖絶壁の景色をこの目で見たい'),
('dojouser4', 38, '道後温泉', '日本最古の温泉を体験したい'),
('dojouser4', 10, '草津温泉', '湯畑の雰囲気を感じたい'),
('dojouser4', 26, '舞妓体験専門店', '花魁と舞妓さんになりたい');









-- PickupsにprefecturesをJOINする
SELECT
v.pickup_id,
v.user_id,
v.prefecture_id,
p.prefecture_name,
v.pickup_place,
v.remarks
FROM
pickups v
JOIN
prefectures p
ON
v.prefecture_id = p.prefecture_id; 


/*pickupsテーブルのデータを確認*/
SELECT*FROM pickups;
