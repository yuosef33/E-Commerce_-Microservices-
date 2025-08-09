-- Insert Categories
INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronic gadgets and devices', 'Electronics'),
    (nextval('category_seq'), 'Clothing and fashion items', 'Clothing'),
    (nextval('category_seq'), 'Fresh food and beverages', 'Groceries'),
    (nextval('category_seq'), 'Books, magazines, and educational materials', 'Books'),
    (nextval('category_seq'), 'Home furniture and décor', 'Home & Living'),
    (nextval('category_seq'), 'Sports and fitness equipment', 'Sports');

-- Insert Products
INSERT INTO product (id, description, name, quantity, price, category_id)
VALUES
    -- Electronics
    (nextval('product_seq'), 'Smartphone with 128GB storage', 'Smartphone', 50, 699.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Laptop with 16GB RAM', 'Laptop', 30, 1299.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Wireless noise-cancelling headphones', 'Headphones', 75, 199.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), '4K Ultra HD Smart TV 55-inch', 'Smart TV', 20, 899.50, (SELECT id FROM category WHERE name = 'Electronics')),

    -- Clothing
    (nextval('product_seq'), 'Men''s cotton T-shirt', 'T-Shirt', 100, 19.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Women''s leather handbag', 'Handbag', 40, 89.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Men''s running shoes size 42', 'Running Shoes', 60, 59.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Winter wool scarf', 'Scarf', 80, 25.00, (SELECT id FROM category WHERE name = 'Clothing')),

    -- Groceries
    (nextval('product_seq'), 'Whole milk 1L carton', 'Milk', 200, 1.49, (SELECT id FROM category WHERE name = 'Groceries')),
    (nextval('product_seq'), 'Organic free-range eggs (12-pack)', 'Eggs', 150, 3.29, (SELECT id FROM category WHERE name = 'Groceries')),
    (nextval('product_seq'), 'Fresh red apples (1kg)', 'Apples', 120, 2.50, (SELECT id FROM category WHERE name = 'Groceries')),
    (nextval('product_seq'), 'Roasted coffee beans 500g', 'Coffee', 90, 8.99, (SELECT id FROM category WHERE name = 'Groceries')),

    -- Books
    (nextval('product_seq'), 'Fantasy novel hardcover edition', 'Fantasy Book', 60, 14.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Data Structures and Algorithms in Java', 'Programming Book', 40, 39.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'Children''s bedtime stories illustrated', 'Story Book', 70, 9.99, (SELECT id FROM category WHERE name = 'Books')),

    -- Home & Living
    (nextval('product_seq'), 'Wooden dining table set for 6', 'Dining Table', 10, 499.99, (SELECT id FROM category WHERE name = 'Home & Living')),
    (nextval('product_seq'), 'Queen-size memory foam mattress', 'Mattress', 15, 349.99, (SELECT id FROM category WHERE name = 'Home & Living')),
    (nextval('product_seq'), 'Modern LED table lamp', 'Table Lamp', 35, 29.99, (SELECT id FROM category WHERE name = 'Home & Living')),

    -- Sports
    (nextval('product_seq'), 'Yoga mat non-slip 6mm', 'Yoga Mat', 80, 19.99, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('product_seq'), 'Mountain bike with 21 gears', 'Mountain Bike', 12, 599.00, (SELECT id FROM category WHERE name = 'Sports')),
    (nextval('product_seq'), 'Adjustable dumbbell set 20kg', 'Dumbbell Set', 25, 89.99, (SELECT id FROM category WHERE name = 'Sports'));
