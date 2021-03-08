create table supermarket
(
    id   bigint primary key auto_increment,
    name varchar(100) not null
);

create table provider
(
    id   bigint primary key auto_increment,
    name varchar(100) not null
);

create table supermarket_to_provider
(
    supermarket_id bigint not null,
    provider_id    bigint not null,
    constraint fk_supermarket_to_relationship foreign key (supermarket_id) references supermarket (id),
    constraint fk_provider_to_relationship foreign key (provider_id) references provider (id)
);