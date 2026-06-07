create table orders
(
    id                 serial
        primary key,
    product_id         integer        not null
        constraint fk_product
            references product
            on delete cascade,
    client_id          integer        not null
        constraint fk_client
            references client
            on delete cascade,
    number_of_products integer        not null,
    total_cost         numeric(10, 2) not null
);

alter table orders
    owner to postgres;

