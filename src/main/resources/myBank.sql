-- CREATE DATABASE
-- create database myBank; 



-- USE DATABASE 
-- use mybank; 



-- CREATE TABLE
-- create table users (
--   UID int not null auto_increment,
--   USERN varchar(20) not null,
--   UMAIL varchar(40) not null,
--   UPASS varchar(20) not null,
--   UDOB varchar(10) not null,
--   UCON varchar(10) not null,
--   UGEN int not null,
--   UROLE int not null,
--   UBAL double not null,
--   PRIMARY KEY (UID)
-- );

-- create table transactions (
--   TID int auto_increment,
--   UFID int not null,
--   TDOB varchar(30) not null,
--   TMOD int not null,
--   TFROM varchar(50),
--   TTO varchar(50),
--   TFUND double not null,
--   UFBAL double not null,
--   PRIMARY KEY (TID)
-- );



-- DROP/DELETE TABLE
-- DROP DATABASE mybank;
-- DROP TABLE users;
-- DROP TABLE transactions;



-- CLEAR ALL THE RECORDS
-- TRUNCATE TABLE users;
-- TRUNCATE TABLE transactions;



-- READ TABLE
-- select * from users;
-- select * from transactions;


-- CHECK TABLE EXIST OR NOT 
-- SHOW TABLES LIKE 'transactions';

-- CHECK DATABASE
-- SHOW DATABASES LIKE 'mybank';
    

-- MONEY UTILITIES
-- SELECT COUNT(*) FROM transactions;
-- SELECT SUM(UBAL) FROM users;


-- LOGIN TESTING
-- select * from users where (USERN = 'kunal' OR UMAIL = 'alien@gmail.com')  AND UPASS = 'admin';
-- select * from users where UMAIL = 'alien@gmail.com' AND UPASS = '@Kunal123';



-- INSERT RECORD
-- INSERT INTO users (USERN, UMAIL, UPASS, UDOB, UCON, UGEN, UROLE, UBAL) VALUES ('system', 'system@gmail.com', 'admin', '00-00-0000', '0987654321', 0, -1, 0);
-- INSERT INTO users (USERN, UMAIL, UPASS, UDOB, UCON, UGEN, UROLE, UBAL) VALUES ('kunal', 'kunal@gmail.com', 'admin', '09-04-2003', '0987654321', 1, 1, 0);
-- INSERT INTO users (USERN, UMAIL, UPASS, UDOB, UCON, UGEN, UROLE, UBAL) VALUES ('alien', 'alien@gmail.com', 'admin', '09-04-2003', '0987654321', 1, 1, 0);

-- INSERT INTO transactions (UFID, TDOB, TMOD, TFROM, TTO, TFUND, UFBAL) VALUES (2, '07/08/2022 22:03:56', 1, 'kunal@gmail.com', 'kunal@gamil.com', 25.00, 25.00);
-- INSERT INTO transactions (UFID, TDOB, TMOD, TFROM, TTO, TFUND, UFBAL) VALUES (2, '07/08/2022 22:03:56', 2, 'kunal@gmail.com', 'kunal@gamil.com', 25.00, 0);
-- INSERT INTO transactions (UFID, TDOB, TMOD, TFROM, TTO, TFUND, UFBAL) VALUES (2, '07/08/2022 22:03:56', 3, 'kunal@gmail.com', 'alien@gamil.com', 25.00, 0);

-- UPDATE RECORD
-- UPDATE users SET UBAL = 100.00 WHERE UID = 2;



-- DELETE RECORD
-- DELETE FROM users WHERE UID = 1;