create table categories
(
    id bigserial primary key,
    title      varchar(45) unique not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into categories (title)
values ('Milk'),
       ('Seasonings'),
       ('Meat'),
       ('Sweets'),
       ('Vegetables'),
       ('Fruit'),
       ('Flour'),
       ('Seafood');

create table if not exists products
(
    id bigserial primary key,
    title       varchar(255),
    cost        numeric(8, 2) not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, cost, category_id)
values ('Milk', 55.00, 1),
       ('Bread', 43.00, 7),
       ('Chocolate', 44.00, 4),
       ('Banana', 65.00, 6),
       ('Potato', 23.00, 5),
       ('Sauce', 56.00, 7),
       ('Apple', 75.00, 6),
       ('Chicken', 150.00, 3),
       ('Fish', 240.00, 8),
       ('Cheese', 214.00, 1),
       ('Beef', 350.00, 3),
       ('Pork', 320.00, 3),
       ('Pepper', 13.00, 2),
       ('Salt', 10.00, 2),
       ('Glazed curd', 34.00, 1),
       ('Snickers', 45.00, 4),
       ('Yogurt', 99.00, 1),
       ('Cream', 200.00, 1),
       ('Cucumber', 190.00, 5),
       ('Tomato', 250.00, 5),
       ('Avocado', 300.00, 6),
       ('Mango', 280.00, 6),
       ('Sausage', 170.00, 3),
       ('Cake', 130.00, 4),
       ('Cookie', 80.00, 4),
       ('Paprika', 50.00, 2);


create table orders
(
    id bigserial primary key,
    username    varchar (45) not null,
    total_price numeric(8, 2) not null,
    address     varchar(255),
    phone       varchar(255),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table order_items
(
    id bigserial primary key,
    product_id        bigint not null references products (id),
    order_id          bigint not null references orders (id),
    quantity          int    not null,
    price_per_product numeric(8, 2) not null,
    price             numeric(8, 2) not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

