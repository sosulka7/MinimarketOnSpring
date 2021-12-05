CREATE TABLE `minimarket`.`products` (
                                         `id` INT NOT NULL AUTO_INCREMENT,
                                         `title` VARCHAR(45) NOT NULL,
                                         `cost` DOUBLE NOT NULL,
                                         PRIMARY KEY (`id`),
                                         UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);

# оставил как пример, все данные добавлял из mysql workbench'а
# insert into products (title, cost)
# values
#     ('Milk', 55.23),
#     ('Bread', 43.4),
#     ('Chocolate', 44.9),
#     ('Banana', 65.00),
#     ('Potato', 23.00),
#     ('Shaverma', 180.00),
#     ('Pasta', 45.55),
#     ('Sauce', 56.00),
#     ('Chicken', 150.6),
#     ('Fish', 240.24),
#     ('Cheese', 214.22),
#     ('Pepper', 13.00),
#     ('Salt', 10.00),
#     ('Glazed curd', 34.00),
#     ('Popcorn', 89.65),
#     ('Snikers', 45);

