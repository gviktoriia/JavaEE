create table category
(
    id   int primary key auto_increment,
    name varchar(50) not null
);

create table author
(
    id        int primary key auto_increment,
    full_name varchar(100) not null
);

create table book
(
    id                int primary key auto_increment,
    isbn              varchar(10) not null,
    title             varchar(50) not null,
    status            varchar(20) not null,
    created_timestamp timestamp   not null,
    category_id       int         not null,
    constraint fk_book_to_category foreign key (category_id) references category (id)
);

create table book_to_authors
(
    book_id   int not null,
    author_id int not null,
    constraint fk_book_to_author foreign key (book_id) references book (id),
    constraint fk_author_to_book foreign key (author_id) references author (id)
);

insert into category(name)
values ('Фантастика'),
       ('Класика'),
       ('ІТ');

insert into author(full_name)
values ('Тарас Шевченко'),
       ('Martin Fowler');

insert into book(isbn, title, status, category_id, created_timestamp)
values ('123', 'Кобзар', 'ACTIVE', 2, '2021-04-05 00:00:00'),
       ('456', 'Clean Code', 'ACTIVE', 3, '2021-03-05 12:45:11'),
       ('789', 'Clean Code Шеченко edition 1', 'DISABLED', 1, '2021-04-03 17:25:13'),
       ('7892', 'Clean Code edition 2', 'IN_REVIEW', 1, '2021-03-03 23:17:32'),
       ('1237', 'Domain Specific Languages', 'ACTIVE', 3, '2021-04-02 01:12:00');

insert into book_to_authors(book_id, author_id)
values (1, 1),
       (2, 2),
       (3, 1),
       (3, 2),
       (4, 2),
       (5, 2);