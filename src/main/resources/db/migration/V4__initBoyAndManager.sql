CREATE TABLE IF NOT EXISTS manager (id VARCHAR UNIQUE, first_name VARCHAR, last_name VARCHAR, is_free BOOLEAN);
CREATE TABLE IF NOT EXISTS boy (id VARCHAR, first_name VARCHAR, last_name VARCHAR, is_free BOOLEAN, is_smart BOOLEAN, manager_id VARCHAR, FOREIGN KEY (manager_id) REFERENCES manager(id));
