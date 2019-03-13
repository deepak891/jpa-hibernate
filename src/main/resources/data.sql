insert into course(id, name, created_Date, last_Updated_Date) values (10001, 'Jpa in 50 steps', sysdate(), sysdate() );
insert into course(id, name, created_Date, last_Updated_Date) values (10002, 'Springbot in 100 steps',sysdate(), sysdate());
insert into course(id, name, created_Date, last_Updated_Date) values (10003, 'Microservice in 100 steps',sysdate(), sysdate());
insert into course(id, name, created_Date, last_Updated_Date) values (10004, 'dummy1', sysdate(), sysdate() );
insert into course(id, name, created_Date, last_Updated_Date) values (10005, 'dummy2',sysdate(), sysdate());
insert into course(id, name, created_Date, last_Updated_Date) values (10006, 'dummy3',sysdate(), sysdate());
insert into course(id, name, created_Date, last_Updated_Date) values (10007, 'dummy5', sysdate(), sysdate() );
insert into course(id, name, created_Date, last_Updated_Date) values (10008, 'dummy4',sysdate(), sysdate());
insert into course(id, name, created_Date, last_Updated_Date) values (10009, 'dummy6',sysdate(), sysdate());
insert into course(id, name, created_Date, last_Updated_Date) values (10010, 'dummy7', sysdate(), sysdate() );



insert into Passport(id, number ) values (40001, 'E123456');
insert into Passport(id, number ) values (40002, 'A123476');
insert into Passport(id, number ) values (40003, 'S1234098');

insert into Student(id, name, passport_id ) values (20001, 'Deepak', 40001);
insert into Student(id, name, passport_id ) values (20002, 'Adam', 40002);
insert into Student(id, name, passport_id ) values (20003, 'Jane', 40003);

insert into Review(id, rating, description, course_id ) values (50001, 'FIVE', 'Great course',10001);
insert into Review(id, rating, description, course_id ) values (50002, 'FIVE', 'Great course',10001);
insert into Review(id, rating, description, course_id ) values (50003, 'FIVE', 'Great course',10003);

insert into student_course(student_id, course_id) values (20001,10001);
insert into student_course(student_id, course_id) values (20001,10003);
insert into student_course(student_id, course_id) values (20002,10003);
