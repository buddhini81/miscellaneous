-- Schema creation
CREATE DATABASE parser;
USE parser;

CREATE TABLE serverlog (
id INT unsigned NOT NULL auto_increment PRIMARY KEY, 
date TIMESTAMP(3) NOT NULL, 
ip VARCHAR(20) NOT NULL, 
request VARCHAR(20) NOT NULL, 
status INT NOT NULL, 
userAgent VARCHAR(1000));

CREATE TABLE logexception (
id INT unsigned NOT NULL auto_increment PRIMARY KEY, 
ip VARCHAR(20) NOT NULL, 
requestCount INT NOT NULL, 
reason VARCHAR(500));