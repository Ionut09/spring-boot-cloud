create schema jpa;
use jpa;

create table if not exists address
(
    id     int          not null
        primary key,
    city   varchar(255) null,
    state  varchar(255) null,
    street varchar(255) null,
    zip    varchar(255) null
);

create table if not exists department
(
    id   int          not null
        primary key,
    name varchar(255) null
);

create table if not exists employee
(
    id            int          not null
        primary key,
    name          varchar(255) null,
    salary        bigint       null,
    startdate     date         null,
    address_id    int          null,
    department_id int          null,
    manager_id    int          null
);

create table if not exists phone
(
    id          bigint       not null
        primary key,
    NUMBER      varchar(255) null,
    TYPE        varchar(255) null,
    employee_id int          null
);

create table if not exists project
(
    id        int          not null
        primary key,
    dtype     varchar(31)  null,
    name      varchar(255) null,
    qa_rating int          null
);

create table if not exists project_employee
(
    employees_id int null,
    projects_id  int null,
    constraint project_employee___fk2
        foreign key (employees_id) references employee (id),
    constraint project_employee_project_id_fk
        foreign key (projects_id) references project (id)
);

create table if not exists singer
(
    ID         bigint auto_increment
        primary key,
    FIRST_NAME varchar(60) not null,
    LAST_NAME  varchar(40) not null,
    BIRTH_DATE date        null,
    constraint UQ_SINGER_1
        unique (FIRST_NAME, LAST_NAME)
);

create table if not exists album
(
    ID           bigint auto_increment
        primary key,
    SINGER_ID    bigint       not null,
    TITLE        varchar(100) not null,
    RELEASE_DATE date         null,
    constraint UQ_SINGER_ALBUM_1
        unique (SINGER_ID, TITLE),
    constraint singer_id_foreign_key
        foreign key (SINGER_ID) references singer (ID)
            on delete cascade
);


INSERT INTO singer (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('MisterMisterMisterMisterMisterMisterMisterMisterMisterMister', 'Clapton', '1945-03-30');
INSERT INTO singer (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('John', 'Butler', '1975-04-01');
INSERT INTO singer (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('John', 'Lennon', '1940-10-09');
INSERT INTO singer (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Mister', 'Starr', '1940-07-07');
INSERT INTO singer (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Elvis', 'Presley', '1942-10-12');
INSERT INTO singer (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Elton', 'John', '1941-10-12');
INSERT INTO singer (FIRST_NAME, LAST_NAME, BIRTH_DATE) VALUES ('Ion', 'Dolanescu', '1940-10-09');
INSERT INTO department (id, name) VALUES (1, 'Engineering');
INSERT INTO department (id, name) VALUES (2, 'QA');
INSERT INTO department (id, name) VALUES (3, 'Accounting');
INSERT INTO department (id, name) VALUES (4, 'CAEngOtt');
INSERT INTO department (id, name) VALUES (5, 'USEngCal');
INSERT INTO department (id, name) VALUES (6, 'CADocOtt');
INSERT INTO department (id, name) VALUES (7, 'QA_East');
INSERT INTO department (id, name) VALUES (8, 'QANorth');
INSERT INTO address (id, city, state, street, zip) VALUES (1, 'New York', 'NY', '123 Apple Tree Cr.', '10001');
INSERT INTO address (id, city, state, street, zip) VALUES (2, 'Manhattan', 'NY', '654 Stanton Way.', '10003');
INSERT INTO address (id, city, state, street, zip) VALUES (3, 'New York', 'NY', '99 Queen St.', '10001');
INSERT INTO address (id, city, state, street, zip) VALUES (4, 'Redwood Shores', 'CA', '445 McDonell Cr.', '90123');
INSERT INTO address (id, city, state, street, zip) VALUES (5, 'San Jose', 'CA', '624 Hamilton Dr.', '90123');
INSERT INTO address (id, city, state, street, zip) VALUES (6, 'San Jose', 'CA', '724 Coventry Rd.', '90123');
INSERT INTO address (id, city, state, street, zip) VALUES (7, 'San Francisco', 'CA', '77 Manchester Blvd.', '90123');
INSERT INTO address (id, city, state, street, zip) VALUES (8, 'Moorestown', 'NJ', '53 King St.', '08057');
INSERT INTO address (id, city, state, street, zip) VALUES (9, 'New York', 'NY', '14 Industrial Ave.', '10001');
INSERT INTO address (id, city, state, street, zip) VALUES (10, 'Redwood Shores', 'CA', '777 High Tech Ln.', '90123');
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (1, '(212)555-1234', 'Office', 1);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (2, '(212)555-9843', 'Home', 1);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (3, '(315)555-6253', 'Office', 2);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (4, '(516)555-9837', 'Office', 3);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (5, '(516)555-2034', 'Cell', 3);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (6, '(650)555-7583', 'Office', 4);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (7, '(650)555-5345', 'Home', 4);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (8, '(650)555-9386', 'Office', 5);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (9, '(650)555-4885', 'Cell', 5);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (10, '(650)555-3836', 'Office', 6);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (11, '(650)555-0985', 'Home', 6);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (12, '(650)555-1946', 'Cell', 6);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (13, '(650)555-4759', 'Office', 7);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (14, '(650)555-4757', 'Home', 7);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (15, '(650)555-6753', 'Office', 8);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (16, '(585)555-0693', 'Office', 9);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (17, '(585)555-3098', 'Home', 9);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (18, '(585)555-1457', 'Cell', 9);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (19, '(650)555-9838', 'Office', 10);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (20, '(650)555-2346', 'Home', 10);
INSERT INTO phone (id, NUMBER, TYPE, employee_id) VALUES (21, '(650)555-9874', 'Cell', 10);
INSERT INTO project_employee (employees_id, projects_id) VALUES (1, 1);
INSERT INTO project_employee (employees_id, projects_id) VALUES (2, 2);
INSERT INTO project_employee (employees_id, projects_id) VALUES (2, 3);
INSERT INTO project_employee (employees_id, projects_id) VALUES (3, 1);
INSERT INTO project_employee (employees_id, projects_id) VALUES (3, 2);
INSERT INTO project_employee (employees_id, projects_id) VALUES (3, 3);
INSERT INTO project_employee (employees_id, projects_id) VALUES (4, 1);
INSERT INTO project_employee (employees_id, projects_id) VALUES (5, 2);
INSERT INTO project_employee (employees_id, projects_id) VALUES (5, 3);
INSERT INTO project_employee (employees_id, projects_id) VALUES (6, 1);
INSERT INTO project_employee (employees_id, projects_id) VALUES (6, 2);
INSERT INTO project_employee (employees_id, projects_id) VALUES (7, 7);
INSERT INTO project_employee (employees_id, projects_id) VALUES (8, 8);
INSERT INTO project_employee (employees_id, projects_id) VALUES (8, 2);
INSERT INTO project_employee (employees_id, projects_id) VALUES (9, 3);
INSERT INTO project_employee (employees_id, projects_id) VALUES (9, 9);
INSERT INTO project_employee (employees_id, projects_id) VALUES (10, 7);
INSERT INTO project_employee (employees_id, projects_id) VALUES (10, 8);
INSERT INTO project_employee (employees_id, projects_id) VALUES (10, 9);
INSERT INTO project_employee (employees_id, projects_id) VALUES (10, 9);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (1, 'John', 60000, '2001-01-01', 1, 2, 9);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (2, 'Rob', 53000, '2001-05-23', 2, 2, 9);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (3, 'Peter', 40000, '2002-08-06', 3, 2, 9);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (4, 'Frank', 41000, '2003-02-17', 4, 1, 10);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (5, 'Scott', 60000, '2004-11-14', 5, 1, 10);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (6, 'Sue', 62000, '2005-08-18', 6, 1, 10);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (7, 'Stephanie', 54000, '2006-06-07', 7, 1, 10);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (8, 'Jennifer', 45000, '1999-08-11', 8, 1, null);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (9, 'Sarah', 52000, '2002-04-26', 9, 2, 10);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (10, 'Joan', 59000, '2003-04-16', 10, 1, null);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (11, 'Marcus', 35000, '1995-07-22', null, null, null);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (12, 'Joe', 36000, '1995-07-22', null, 3, 11);
INSERT INTO employee (id, name, salary, startdate, address_id, department_id, manager_id) VALUES (13, 'Jack', 43000, '1995-07-22', null, 3, null);
INSERT INTO album (SINGER_ID, TITLE, RELEASE_DATE) VALUES (2, ' From The Cradle ', '1994-09-13');
INSERT INTO album (SINGER_ID, TITLE, RELEASE_DATE) VALUES (6, 'The Beatles', '1968-01-01');
INSERT INTO album (SINGER_ID, TITLE, RELEASE_DATE) VALUES (7, 'Abbey Road', '1969-01-01');
INSERT INTO project (id, dtype, name, qa_rating) VALUES (1, 'P', 'Implement Release1', null);
INSERT INTO project (id, dtype, name, qa_rating) VALUES (2, 'P', 'Implement Release2', null);
INSERT INTO project (id, dtype, name, qa_rating) VALUES (3, 'DP', 'Design Release1', null);
INSERT INTO project (id, dtype, name, qa_rating) VALUES (4, 'DP', 'Design Release2', null);
INSERT INTO project (id, dtype, name, qa_rating) VALUES (5, 'DP', 'Design Release3', null);
INSERT INTO project (id, dtype, name, qa_rating) VALUES (6, 'QP', 'Test Release1', 4);
INSERT INTO project (id, dtype, name, qa_rating) VALUES (7, 'QP', 'Test Release2', 5);
INSERT INTO project (id, dtype, name, qa_rating) VALUES (8, 'QP', 'Test Release3', 5);
INSERT INTO project (id, dtype, name, qa_rating) VALUES (9, 'P', 'Implement Release3', null);


