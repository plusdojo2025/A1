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
('onigawara','kyunmoe32','もえもえ',4),
('zinguuzi','Maaay2025','まいやん',14),
('kuro','Nagaoka25','いっちー',13);