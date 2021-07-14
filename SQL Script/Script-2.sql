DROP TABLE IF EXISTS login CASCADE;

CREATE TABLE login(
	username VARCHAR(20) NOT NULL UNIQUE PRIMARY KEY,
	user_pass VARCHAR(20) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	employee VARCHAR(8),
	approved BOOLEAN
);

INSERT INTO login (username, user_pass, first_name, last_name,employee, approved) 
	VALUES('user1', 'password', 'Dylan', 'Villhauer', 'user', TRUE);
INSERT INTO login (username, user_pass, first_name, last_name,employee, approved) VALUES
	('user2', 'password', 'Employee', 'number1', 'employee', TRUE),
	('user3', 'password', 'Employee', 'number2', 'admin', TRUE);

CREATE TABLE bank_account(
	account_number SERIAL UNIQUE PRIMARY KEY,
	account_user VARCHAR(20) REFERENCES login(username),
	account_info VARCHAR(20) NOT NULL,
	account_balance NUMERIC(20,2) CHECK (account_balance >= 0) NOT NULL DEFAULT 0
);

DROP TABLE bank_account;

INSERT INTO bank_account(account_user, account_info, account_balance)VALUES
('user1', 'Checking Account',1000),
('user2', 'Checking Account',1000),
('user3', 'Checking Account',1000);

SELECT * FROM login WHERE username = 'user1';

SELECT * FROM BANK WHERE bank_account = 1;