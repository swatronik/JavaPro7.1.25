DROP TABLE IF EXISTS users
CREATE TABLE IF NOT EXISTS users (id bigserial primary key, username varchar(255) unique)
INSERT INTO users (id, username) VALUES (1, 'admin')