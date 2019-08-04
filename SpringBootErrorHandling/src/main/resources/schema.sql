CREATE TABLE IF NOT EXISTS person ( 
   id INT PRIMARY KEY auto_increment NOT NULL, 
   first_name VARCHAR(100) NOT NULL, 
   last_name VARCHAR(100) NOT NULL, 
   age INT NOT NULL
);

INSERT INTO person(first_name,last_name,age)
VALUES('Buddhini','Samarakkody',20);

