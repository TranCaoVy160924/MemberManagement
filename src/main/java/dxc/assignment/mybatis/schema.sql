CREATE TABLE IF NOT EXISTS members
(
   	user_id SERIAL PRIMARY KEY,
   	username varchar(255) not null,
   	email_id varchar(255) not null,
	password varchar(255) not null,
	phone_number varchar(255) not null
);

INSERT INTO members (username, email_id, password, phone_number) 
VALUES 
	('Phan Thanh Dung', 'thanhdung@gmail.com', '12345678', 0987654321),
	('Tran Caov Vy', 'caovy@gmail.com', '12345678', 0123498765),
	('Dao Khac Nhien', 'dknhien@gmail.com', '12345678', 3256507861);
