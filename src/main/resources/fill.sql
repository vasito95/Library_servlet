INSERT INTO user(email, is_active, password, phone_number, username, role)
VALUES ('admin1@gmail.com', 1, 'Admin$1admin', '3880667927552', 'admin1', 'ADMIN');
INSERT INTO user(email, is_active, password, phone_number, username, role)
VALUES ('admin2@gmail.com', 1, 'Admin$1admin', '3880667927552', 'admin2', 'ADMIN');
INSERT INTO user(email, is_active, password, phone_number, username, role)
VALUES ('user1@gmail.com', 1, 'User%1user', '3880667927552', 'user1', 'USER');
INSERT INTO user(email, is_active, password, phone_number, username, role)
VALUES ('user2@gmail.com', 1, 'User%1user', '3880667927552', 'user2', 'USER');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book1', null, 'Java');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book2', null, 'Java');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book3', null, 'Python');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book4', null, 'JavaScript');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book5', null, 'Java');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book6', null, 'JavaScript');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book7', null, 'Java');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book8', null, 'JavaScript');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book9', null, 'Java');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book10', null, 'JavaScript');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book11', null, 'Java');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book12', null, 'C++');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book13', null, 'Java');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book14', null, 'C++');
INSERT INTO book(in_use_by, is_in_use, name, user_id, attribute)
VALUES ('2020-02-27', 0, 'Book15', null, 'C++');
INSERT INTO author(book_id, authors)
VALUES (1, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (1, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (1, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (2, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (2, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (3, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (3, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (4, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (4, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (4, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (4, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (5, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (6, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (7, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (7, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (8, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (8, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (9, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (10, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (11, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (12, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (13, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (14, 'Author Author');
INSERT INTO author(book_id, authors)
VALUES (15, 'Author Author');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (1, 'Book1', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (2, 'Book2', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (3, 'Book3', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (4, 'Book4', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (5, 'Book5', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (6, 'Book6', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (7, 'Book7', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (8, 'Book8', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (9, 'Book9', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (10, 'Book10', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (11, 'Book11', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (12, 'Book12', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (13, 'Book13', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (14, 'Book14', '2020-03-10', 'user1', '3');
INSERT INTO book_order(book_id, book_name, date_to, user_name, user_id)
VALUES (15, 'Book15', '2020-03-10', 'user1', '3');

