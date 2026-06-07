create table log
(
    order_id     integer        not null
        primary key,
    client_name  varchar(255)   not null,
    product_name varchar(255)   not null,
    quantity     integer        not null,
    total_cost   numeric(10, 2) not null
);

alter table log
    owner to postgres;

