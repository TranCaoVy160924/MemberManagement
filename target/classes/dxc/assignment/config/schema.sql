CREATE TABLE IF NOT EXISTS members
(
   	member_id SERIAL PRIMARY KEY,
   	username varchar(255) not null,
   	email varchar(255) not null unique,
	password varchar(255) not null,
	phone_number varchar(255) not null,
	role varchar(255) not null
);

INSERT INTO members (username, email, password, phone_number, role) 
VALUES 
	('Phan Thanh Dung', 'thanhdung@gmail.com', '12345678', '0987654321', 'EDIT'),
	('Tran Caov Vy', 'caovy@gmail.com', '12345678', '0123498765', 'ADMIN'),
	('Dao Khac Nhien', 'dknhien@gmail.com', '12345678', '3256507861', 'VIEW');

SELECT member_id, username, email, password, phone_number FROM members