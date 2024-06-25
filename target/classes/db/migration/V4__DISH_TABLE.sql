CREATE TABLE app_dish (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      description TEXT,
                      price DOUBLE NOT NULL,
                      order_id BIGINT,
                      FOREIGN KEY (order_id) REFERENCES app_order(id)
);
