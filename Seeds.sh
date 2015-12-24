#!/bin/bash

echo "Creaing database with seeds"
mysql -u root -p <<EOF
CREATE DATABASE IF NOT EXISTS lopes_n;
use lopes_n;
CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY NOT NULL, email VARCHAR(255), name VARCHAR(255), pswd VARCHAR(255), news BOOLEAN, admin BOOLEAN);
REPLACE INTO users (id, email, name, pswd, news, admin) VALUES (1, 'admin@jweb.com', 'admin', 'admin', true, true);
REPLACE INTO users (id, email, name, pswd, news, admin) VALUES (2, 'user@jweb.com', 'user', 'user', true, false);
CREATE TABLE IF NOT EXISTS news (id INT PRIMARY KEY NOT NULL, title VARCHAR(255), content TEXT);
REPLACE INTO news (id, title, content) VALUES (1, 'Premier article', 'Lorem ipsum blabla premier article');
REPLACE INTO news (id, title, content) VALUES (2, 'Deuxième article', 'Lorem ipsum blabla deuxième article');
EOF
echo "Seed database done"
