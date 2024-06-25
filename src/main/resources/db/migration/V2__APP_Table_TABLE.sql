CREATE TABLE app_table (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         number INT NOT NULL,
                         seats INT NOT NULL,
                         is_occupied BOOLEAN DEFAULT false
    );
