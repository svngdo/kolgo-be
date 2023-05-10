insert into addresses(city_id, details)
values (6, null),
       (7, null),
       (8, null),
       (9, null),
       (10, null);

insert into fields(name, type)
values ('Nông Nghiệp và Khai Thác', 'ENTERPRISE'),
       ('Thông Tin', 'ENTERPRISE'),
       ('Vận Tải', 'ENTERPRISE'),
       ('Dịch Vụ Tài Chính', 'ENTERPRISE'),
       ('Bất động sản', 'ENTERPRISE'),
       ('Bán Lẻ và Phân Phối', 'ENTERPRISE'),
       ('Thương Mại Điện Tử', 'ENTERPRISE'),
       ('Công Nghệ Thông Tin', 'ENTERPRISE'),
       ('Y Tế', 'ENTERPRISE'),
       ('Giáo Dục', 'ENTERPRISE'),
       ('Du Lịch', 'ENTERPRISE');

insert into users(first_name, last_name, email, password, avatar, role)
values ('sang', 'enterprise', 'sangenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa', null, 'ENTERPRISE'),
       ('huy', 'enterprise', 'huyenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa', null, 'ENTERPRISE'),
       ('thang', 'enterprise', 'thangenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa', null, 'ENTERPRISE'),
       ('chan', 'enterprise', 'chanenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa', null, 'ENTERPRISE'),
       ('hieu', 'enterprise', 'hieuenterprise@kolgo.com','$2a$10$4zkmuZDURkxgJBmH8g2JyOF6U6oczpJBKLQ2iFQdlf1zpD.cCFqLa', null, 'ENTERPRISE');

insert into enterprises(user_id, name, phone, tax_id, address_id, field_id)
values (7, 'Công ty TNHH 1 thành viên Sang Đỗ', '0123456789','3216549873216', 6, 10),
       (8, 'Công ty TNHH 1 thành viên Huy Trần', '0123456789', '1234567891234', 7, 11),
       (9, 'Công ty TNHH 1 thành viên Thắng Trần', '0123456789', '9876543219876', 8, 12),
       (10, 'Công ty TNHH 1 thành viên Chân Phạm', '0123456789', '7894561237894', 9, 13),
       (11, 'Công ty TNHH 1 thành viên Hiếu Văn', '0123456789', '6549873216549', 10, 14);