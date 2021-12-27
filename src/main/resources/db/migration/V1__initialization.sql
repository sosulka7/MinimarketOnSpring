create table if not exists products (
                                        id bigserial primary key,
                                        title varchar(255),
                                        cost int
);

insert into products (title, cost)
values   ('Milk', 55),
         ('Bread', 43),
         ('Chocolate', 44),
         ('Banana', 65),
         ('Potato', 23),
         ('Shaverma', 180),
         ('Pasta', 45),
         ('Sauce', 56),
         ('Chicken', 150),
         ('Fish', 240),
         ('Cheese', 214),
         ('Pepper', 13),
         ('Salt', 10),
         ('Glazed curd', 34),
         ('Popcorn', 89),
         ('Snikers', 45);

create table users (
    id bigserial primary key,
    username varchar(45) unique not null,
    password varchar(80) not null,
    email varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into users (username, password, email)
values ('Bobby', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bobby@gmail.com'),
       ('Mike', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'mike@gmail.com'),
       ('Jonathan', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'jon@gmail.com');

create table roles (
    id bigserial primary key,
    name varchar(45) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

create table users_roles (
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);


