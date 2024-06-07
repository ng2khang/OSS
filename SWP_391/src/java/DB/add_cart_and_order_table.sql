
-- Create CartItems table
CREATE TABLE cart_items (
    id INT PRIMARY KEY,
    customer_id INT FOREIGN KEY REFERENCES users(id),
    product_detail_id INT FOREIGN KEY REFERENCES product_details(id),
    quantity INT
);

CREATE TABLE orders (
    id INT PRIMARY KEY,
    order_code VARCHAR(255),
    customer_id INT FOREIGN KEY REFERENCES users(id),
    oder_date DATETIME,
    order_status int, -- 1: new order, 2: sale confirmed, 3: shipping, 4: shipped, 5:order has been paid 6: Customers do not receive 7: cancel 8: return
    delivery_address_id INT FOREIGN KEY REFERENCES user_addresses(id),
    shiper_id INT FOREIGN KEY REFERENCES users(id),
    delivery_date DATETIME,
    payment_method INT -- 1: COD
);

-- Create OrderDetails table
CREATE TABLE order_details (
    id INT PRIMARY KEY,
    order_id INT FOREIGN KEY REFERENCES orders(id),
    product_detail_id INT FOREIGN KEY REFERENCES product_details(id),
    quantity INT,
    unit_price INT,
);

