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
    cost        int,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

insert into products (title, cost, category_id)
values ('Milk', 55, 1),
       ('Bread', 43, 7),
       ('Chocolate', 44, 4),
       ('Banana', 65, 6),
       ('Potato', 23, 5),
       ('Sauce', 56, 7),
       ('Apple', 75, 6),
       ('Chicken', 150, 3),
       ('Fish', 240, 8),
       ('Cheese', 214, 1),
       ('Beef', 350, 3),
       ('Pork', 320, 3),
       ('Pepper', 13, 2),
       ('Salt', 10, 2),
       ('Glazed curd', 34, 1),
       ('Snickers', 45, 4),
       ('Yogurt', 99, 1),
       ('Cream', 200, 1),
       ('Cucumber', 190, 5),
       ('Tomato', 250, 5),
       ('Avocado', 300, 6),
       ('Mango', 280, 6),
       ('Sausage', 170, 3),
       ('Cake', 130, 4),
       ('Cookie', 80, 4),
       ('Paprika', 50, 2);


create table orders
(
    id bigserial primary key,
    username    varchar (45) not null,
    total_price int    not null,
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
    price_per_product int    not null,
    price             int    not null,
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);

