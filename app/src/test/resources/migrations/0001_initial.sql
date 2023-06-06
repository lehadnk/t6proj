create table users
(
    id       integer not null primary key,
    email    varchar(255) not null,
    password varchar(255) not null
);

alter table users owner to postgres;

create table departments
(
    id                   integer not null primary key,
    title                varchar(255) not null,
    parent_department_id integer constraint departments_parent_department_id_constraint references departments("id") on delete set null
);

alter table departments owner to postgres;

create table employee_documents
(
    id            integer not null primary key,
    employee_id   integer not null,
    url           varchar(255) not null,
    document_type smallint not null,
    document_number varchar(255),
    document_date date,
    issued_by varchar(255)
);

alter table employee_documents owner to postgres;

create table employee_requests
(
    id          integer not null primary key,
    employee_id integer not null,
    opened_at   date not null,
    status      smallint not null,
    text        varchar(255) not null,
    title       varchar(255) not null
);

alter table employee_requests owner to postgres;

create table employees
(
    id          integer not null primary key,
    birthdate   date not null,
    employed_at date not null,
    first_name  varchar(255) not null,
    last_name   varchar(255) not null,
    middle_name varchar(255),
    is_deleted boolean not null default false
);

alter table employees owner to postgres;

create table jobs
(
    id            integer not null primary key,
    department_id integer not null,
    title         varchar(255) not null
);

alter table jobs owner to postgres;

create table contracts
(
    id          integer not null primary key,
    employee_id integer not null,
    salary      double precision,
    starts_at   date not null,
    ends_at     date,
    terms       varchar(255) not null,
    job_id      integer constraint contracts_job_id_constraint references jobs("id")
);

alter table contracts owner to postgres;

CREATE OR REPLACE FUNCTION get_department_budget(in department_id int)
    RETURNS double precision
AS
$$
    WITH RECURSIVE tree AS
    (
        SELECT id
        FROM departments d
        WHERE d.id IN (department_id)

        UNION ALL

        SELECT departments.id
        FROM departments, tree
        WHERE tree.id = departments.parent_department_id
    )

    SELECT
        sum(salary) as salary
    FROM contracts c
             JOIN jobs j on c.job_id = j.id
             JOIN departments d on j.department_id = d.id
    WHERE d.id IN (
        SELECT id
        FROM tree
);
$$
LANGUAGE SQL;

INSERT INTO users(id, email, password) VALUES
(1, 'lehadnk@gmail.com', 'H������A�M|����a5s��[@Ӕ,˕U�5');

INSERT INTO departments(id, title, parent_department_id) VALUES
(1, 'Факультет информационных технологий и управления', null),
(2, 'Кафедра системного анализа и информационных технологий', 1),
(3, 'Химии веществ и материалов', null),
(4, 'Общей химического технологии и катализа', 3),
(5, 'Физической химии', 3),
(6, 'Факультет гендерных наук', null),
(7, 'Кафедра вопросов дискриминации', 6),
(8, 'Внештатные сотрудники кафедры вопросов дискриминации', 7);

INSERT INTO jobs(id, title, department_id) VALUES
(1, 'Системный администратор', 1),
(2, 'Лаборант', 4),
(3, 'Преподаватель', 5),
(4, 'Менеджер по вопросам инклюзивности', 7),
(5, 'Сумасшедший блоггер', 8);

INSERT INTO employees(id, birthdate, employed_at, first_name, last_name, middle_name) VALUES
(1, '1968-11-05', '2007-07-01', 'Иван', 'Иванов', 'Иванович'),
(2, '1985-12-08', '2012-08-10', 'Анита', 'Саркисян', null),
(3, '1985-12-08', '2012-08-10', 'Мария', 'Петрова', 'Афанасьевна'),
(4, '1985-12-08', '2012-08-10', 'Сергей', 'Пушкин', 'Александрович');

INSERT INTO employee_requests(id, employee_id, opened_at, status, text, title) VALUES
(1, 1, '2023-01-05', 0, 'Заявление на отпуск', 'Хочу в отпуск');

INSERT INTO employee_documents(id, employee_id, document_type, url, issued_by, issued_at, valid_by, document_number) VALUES
(1, 1, 0, 'http://s3.aws.com/3iau295O3j2a.png', 'УФМС 7023', '2012-08-24', '2027-08-24', '21 04 689250'),
(2, 2, 0, 'http://s3.aws.com/dsIdoZ8wk2dk.png', 'Паспортно-визовое отделение ОВД по р-ну Бибирево г. Москвы', '2013-05-21', '2025-02-24', 'РТ3926913862'),
(3, 2, 1, 'http://s3.aws.com/32iAij35hhai.png', 'Еще какой-нибудь орган', '2007-01-13', '2025-06-07', '395 ФХ 3863');

INSERT INTO contracts (id, employee_id, salary, starts_at, ends_at, terms, job_id) VALUES
(1, 1, 80000, '2023-01-01', '2024-01-01', 'Появляться на работе', 1),
(2, 3, 20000, '2022-01-01', '2023-01-01', 'Появляться на работе', 2),
(3, 3, 30000, '2023-01-01', null, 'Появляться на работе', 2),
(4, 3, 70000, '2023-01-01', '2024-01-01', 'Появляться на работе', 3),
(5, 2, 40000, '2023-01-01', '2024-01-01', 'Появляться на работе', 4),
(6, 2, 20000, '2023-01-01', '2024-01-01', 'Появляться на работе', 5);