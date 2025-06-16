CREATE TABLE users (	
user_id VARCHAR (255) NOT NULL,	
password VARCHAR (255) NOT NULL,	
name VARCHAR (50) NOT NULL,	
live INT NOT NULL,	
PRIMARY KEY(user_id),	
FOREIGN KEY (live) REFERENCES prefectures (prefecture_id)	
	ON DELETE RESTRICT
	ON UPDATE CASCADE
);	

INSERT INTO users(user_id,password,name,live) VALUES
('onigawara','kyunmoe32','もえもえ',4),
('zinguuzi','Maaay2025','まいやん',14),
('kuro','Nagaoka25','いっちー',13);