CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

CREATE VIEW IF NOT EXISTS materialized_view_products AS
SELECT id, name, price
FROM product;