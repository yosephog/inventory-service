create table Product (
    id BIGSERIAL PRIMARY KEY NOT NULL ,
    serial_number varchar(255) NOT NULL ,
    price float8 NOT NULL ,
    created_date        timestamp NOT NULL,
    last_modified_date  timestamp NOT NULL,
    version integer not null
);

create table Inventory (
    id BIGSERIAL PRIMARY KEY NOT NULL ,
    serial_number varchar(255) NOT NULL,
    count_in_stock integer not null ,
    created_date        timestamp NOT NULL,
    last_modified_date  timestamp NOT NULL,
    version integer not null
);