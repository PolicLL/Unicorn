CREATE USER 'unicorn'@'localhost' IDENTIFIED BY 'unicorn';

GRANT ALL PRIVILEGES ON * . * TO 'unicorn'@'localhost';

ALTER USER 'unicorn'@'localhost' IDENTIFIED WITH mysql_native_password BY 'unicorn';