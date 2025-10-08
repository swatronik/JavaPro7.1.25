DROP TABLE IF EXISTS config_param CASCADE
DROP TABLE IF EXISTS payment_limit CASCADE
CREATE TABLE IF NOT EXISTS config_param (id bigserial primary key, name_config varchar(255), value_config varchar(255), start_date date, expire_date date)
CREATE TABLE IF NOT EXISTS payment_limit (id bigserial primary key, user_id int, spending_limit float, last_update_date date)
INSERT INTO config_param (id, name_config, value_config, start_date, expire_date) VALUES (1, 'max_limit', '5000', '2000-01-01', '2024-12-31')
INSERT INTO config_param (id, name_config, value_config, start_date, expire_date) VALUES (2, 'max_limit', '10000', '2025-01-01', '2025-12-31')
INSERT INTO config_param (id, name_config, value_config, start_date, expire_date) VALUES (3, 'max_limit', '20000', '2026-01-01', '2027-01-01')
