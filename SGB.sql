DROP DATABASE SGB;

CREATE DATABASE SGB;

USE SGB;

CREATE TABLE teacher (
	teacher_id int NOT NULL AUTO_INCREMENT,
    teacher_firstName VARCHAR(50),
	teacher_lastName VARCHAR(50),
    teacher_username varchar(100) Unique NOT NULL,
    teacher_password varchar(100) NOT NULL,
    Primary Key (teacher_id)
);

CREATE TABLE class (
	class_id int NOT NULL AUTO_INCREMENT,
    class_name varchar(100),
    Primary Key (class_id)
);

CREATE TABLE student (
	student_id int NOT NULL AUTO_INCREMENT,
    student_firstName varchar(50),
    student_lastName varchar(50),
    PRIMARY KEY (student_id)
);

CREATE TABLE teacher_class (
	teacher_id int NOT NULL,
    class_id int NOT NULL,
	PRIMARY KEY (teacher_id, class_id),
    FOREIGN KEY (teacher_id) REFERENCES teacher(teacher_id),
    FOREIGN KEY (class_id) REFERENCES class(class_id)
);

CREATE TABLE student_class (
	student_id int NOT NULL,
    class_id int NOT NULL,
    grades float,
    Primary Key (student_id, class_id),
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (class_id) REFERENCES class(class_id)
);

INSERT INTO teacher (teacher_firstName, teacher_lastName, teacher_username, teacher_password) values ('Matteo', 'Antunez', 'matteoantunez', 'matteoantunez1');
INSERT INTO class (class_name) values ('Database Systems');
INSERT INTO student (student_firstName, student_lastName) values ('Sean', 'Bryson');
INSERT INTO teacher_class values (1,1);
INSERT INTO student_class values (1,1, null);


select * from teacher;
select * from class;
select * from student;
select * from teacher_class;
select * from student_class; 