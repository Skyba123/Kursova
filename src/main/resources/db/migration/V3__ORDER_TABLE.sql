CREATE TABLE app_order (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         table_id BIGINT,
                         is_completed BOOLEAN NOT NULL DEFAULT FALSE,
                         created_at DATETIME NOT NULL,
                         FOREIGN KEY (table_id) REFERENCES app_table(id)
);
