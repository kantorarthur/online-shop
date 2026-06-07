create table client
(
    id    serial
        primary key,
    name  varchar(100) not null,
    email varchar(255) not null
        unique
);

INSERT INTO client (email, name)
VALUES('Alex@yahoo.com', 'Alex'),
      ('Mircea@gmail.com', 'Mircea');
        

alter table client
    owner to postgres;

