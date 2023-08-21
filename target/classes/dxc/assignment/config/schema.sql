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
	('Phan Thanh Dung', 'thanhdung@gmail.com', crypt('12345678', gen_salt('bf', 10)), '0987654321', 'ROLE_EDIT'),
	('Tran Caov Vy', 'caovy@gmail.com', crypt('12345678', gen_salt('bf', 10)), '0123498765', 'ROLE_ADMIN'),
	('Dao Khac Nhien', 'dknhien@gmail.com', crypt('12345678', gen_salt('bf', 10)), '3256507861', 'ROLE_VIEW');

SELECT member_id, username, email, password, phone_number, role FROM members