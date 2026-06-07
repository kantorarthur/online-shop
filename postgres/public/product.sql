create table product
(
    id                serial
        primary key,
    name              varchar(100) not null,
    price             real         not null,
    quantity_in_stock integer      not null
);

INSERT INTO product (name, price, quantity_in_stock)
VALUES 
    ('Cars', 155, 25),
    ('Clothes',25, 33);


alter table product
    owner to postgres;

