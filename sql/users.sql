CREATE TABLE users (	
user_id VARCHAR (255) NOT NULL,	
password VARCHAR (255) NOT NULL,	
nickname VARCHAR (50) NOT NULL,	
prefecture_id INT  NOT NULL,
PRIMARY KEY(user_id),	
FOREIGN KEY (prefecture_id) REFERENCES prefectures (prefecture_id)	
	ON DELETE RESTRICT
	ON UPDATE CASCADE
);	

INSERT INTO users(user_id,password,nickname,prefecture_id) VALUES
('dojouser1','#SEplus2025SEplus','もえもえ',4),
('dojouser2','#SEplus2025SEplus','まいやん',14),
('dojouser3','#SEplus2025SEplus','いっちー',13),
('dojouser4','#SEplus2025SEplus','よっしー',43);



/*usersテーブルのデータを確認*/
SELECT*FROM users;



