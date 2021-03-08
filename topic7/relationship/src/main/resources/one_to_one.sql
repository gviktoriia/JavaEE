create table passport
(
    id     bigint primary key auto_increment,
    number varchar(10) not null,
    serial varchar(10) not null
);

create table person
(
    id          bigint primary key auto_increment,
    full_name   varchar(100) not null,
    passport_id bigint default null,
    constraint fk_person_to_passport foreign key (passport_id) references passport (id)
);