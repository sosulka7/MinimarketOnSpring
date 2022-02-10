CREATE TABLE popular_items_in_order
(
    id         int      NOT NULL AUTO_INCREMENT,
    product_id int      NOT NULL,
    added_at   DATETIME NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE popular_items_in_cart
(
    id         int      NOT NULL AUTO_INCREMENT,
    product_id int      NOT NULL,
    added_at   DATETIME NOT NULL DEFAULT current_timestamp,
    PRIMARY KEY (id)
);

INSERT INTO popular_items_in_cart (product_id)
VALUES (7),
       (7),
       (7),
       (7),
       (7),
       (7),
       (7),
       (5),
       (5),
       (5),
       (5),
       (5),
       (5),
       (5),
       (5),
       (5),
       (5),
       (5),
       (5),
       (5),
       (3),
       (3),
       (3),
       (3),
       (3),
       (3),
       (3),
       (3),
       (2),
       (2),
       (2),
       (2),
       (2),
       (1),
       (1),
       (1),
       (4),
       (4),
       (6);





