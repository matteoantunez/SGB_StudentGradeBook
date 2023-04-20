# SGB_StudentGradeBook

SGB, also known as Student Grade Book, is a Java console-based application that allows a teacher to log-in and keep track of students grades in a particular course. The program is split into two parts: MVP (Minimal Viable Product) and Bonus requirements.

## MVP

- [X] The entire project will be built with Java and will be a **console application**
- [X] Teachers will be able to **Login**
- [X] New teachers will be able to **register for a new account**
- [X] Each teacher will have a **list of classes** that they are teaching
    - [X] Allow them to **create a new class**, especially if they do not already have one
- [X] When a class is select, a **list of students with their name and their current grade** in the class (number between 0 - 100)
- As well, the teacher should have the option to:
    - [X] Find the **average and median grade** for a class
    - [X] **Sort the students** by alphabetical order and by their grades
    - [X] **Update a student's grade**
    - [X] **Remove and add** students to a class
- [X] Data for this project can either be **stored in memory or within a file**

## Bonus
- [X] Use **JDBC & DAO** to store the data for this project
- [ ] **Students can login** to this application and be able to see the grades in all the classes they are enrolled in
- [ ] Instead updating grades manually, allow teachers to **input the grades for each assignment** a student has turned in, then allow the application to calculate the student's new grade on its own
    - [ ] Can take this further by having **different weights for grades** (20% for participation, 30% labs, 50% test, etc.)
