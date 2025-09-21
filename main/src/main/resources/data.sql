DROP TABLE IF EXISTS users CASCADE
DROP TABLE IF EXISTS products CASCADE
CREATE TABLE IF NOT EXISTS users (id bigserial primary key, username varchar(255) unique)
CREATE TABLE IF NOT EXISTS products (id bigserial primary key, number_account int, balance float, type_product varchar(255), user_id bigserial)
INSERT INTO users (id, username) VALUES (1, 'oleg')
INSERT INTO users (id, username) VALUES (2, 'vadim')
INSERT INTO users (id, username) VALUES (3, 'ivan')
INSERT INTO products (id, number_account, balance, type_product, user_id) VALUES (1, 123456, 150.0, 'CARD', 2)
INSERT INTO products (id, number_account, balance, type_product, user_id) VALUES (2, 123466, 1000.0, 'ACCOUNT', 2)
INSERT INTO products (id, number_account, balance, type_product, user_id) VALUES (3, 1111, 5000.0, 'ACCOUNT', 3)