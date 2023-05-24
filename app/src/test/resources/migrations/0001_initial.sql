create table users
(
    id       integer not null primary key,
    email    varchar(255),
    password varchar(255)
);

create table departments
(
    id    integer not null primary key,
    title varchar(255)
);

create table jobs
(
    id            integer not null primary key,
    title         varchar(255),
    department_id varchar(255) REFERENCES departments(id)
);

CREATE INDEX "job_departments" ON jobs USING btree(department_id);

create table employees
(
    id          integer not null primary key,
    birthdate   timestamp(6),
    employed_at timestamp(6),
    first_name  timestamp(6),
    job_id      integer REFERENCES jobs(id),
    last_name   timestamp(6),
    middle_name timestamp(6)
);

CREATE INDEX "employee_jobs" ON employees USING btree(job_id);

create table employee_documents
(
    id          integer not null primary key,
    employee_id integer REFERENCES employees(id),
    url         varchar(255)
);

CREATE INDEX "employee_document_employees" ON employee_documents USING btree(employee_id);

create table employee_requests
(
    id          integer not null primary key,
    employee_id integer REFERENCES employees(id),
    opened_at   integer,
    status      smallint,
    text        varchar(255),
    title       varchar(255)
);

CREATE INDEX "employee_document_requests" ON employee_requests USING btree(employee_id);