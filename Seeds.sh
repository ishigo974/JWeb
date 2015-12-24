#!/bin/bash

echo "Creaing database with seeds"
mysql -u root -p <<EOF
CREATE DATABASE IF NOT EXISTS lopes_n;
use lopes_n;

CREATE TABLE IF NOT EXISTS articles (
  id int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  title varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  price varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  content varchar(900) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  img varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS news (
  PRIMARY KEY (id),
  id int(11) NOT NULL AUTO_INCREMENT,
  title varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  content varchar(900) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

CREATE TABLE IF NOT EXISTS users (
  id int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (id),
  email varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  pswd varchar(250) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  name varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  newsletter tinyint(1) NOT NULL,
  admin tinyint(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=29 ;

REPLACE INTO news (id, title, content) VALUES
(1, 'First news', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vitae tempus libero. Suspendisse luctus elementum ipsum at ultricies. Aliquam tristique lacinia commodo. Quisque vehicula, dui rutrum malesuada malesuada, mauris sem porta ipsum, ut imperdiet quam lectus semper ex. Integer in dolor orci. Integer fringilla massa mauris, eget luctus risus fringilla at. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam viverra turpis purus, et volutpat arcu fringilla in.'),
(2, 'Second news', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque vitae tempus libero. Suspendisse luctus elementum ipsum at ultricies. Aliquam tristique lacinia commodo. Quisque vehicula, dui rutrum malesuada malesuada, mauris sem porta ipsum, ut imperdiet quam lectus semper ex. Integer in dolor orci. Integer fringilla massa mauris, eget luctus risus fringilla at. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nullam viverra turpis purus, et volutpat arcu fringilla in.');

REPLACE INTO users (id, email, pswd, name, newsletter, admin) VALUES
(1, 'admin@jweb.com', 'admin', 'admin', 1, 1),
(2, 'user@jweb.com', 'user', 'user', 1, 0)
EOF

echo "Seed database done"
