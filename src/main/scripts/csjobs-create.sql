set client_min_messages=WARNING;
set default_text_search_config=english;

create sequence hibernate_sequence minvalue 1000;

create table users (
    id          bigint primary key,
    email       varchar(255) unique not null,
    password    varchar(255) not null,
    enabled     boolean not null default 't',
    first_name  varchar(255),
    last_name   varchar(255),
    address     varchar(255),
    phone       varchar(255)
);

insert into users (id, email, password, first_name, last_name) values
    (1, 'admin@localhost.localdomain', 'abcd', 'Admin', 'User');
insert into users (id, email, password, first_name, last_name) values
    (2, 'reviewer1@localhost.localdomain', 'abcd', 'Reviewer1', 'User');
insert into users (id, email, password, first_name, last_name) values
    (3, 'reviewer2@localhost.localdomain', 'abcd', 'Reviewer2', 'User');
insert into users (id, email, password, first_name, last_name) values
    (4, 'reviewer3@localhost.localdomain', 'abcd', 'Reviewer3', 'User');
insert into users (id, email, password, first_name, last_name) values
    (5, 'reviewer4@localhost.localdomain', 'abcd', 'Reviewer4', 'User');
insert into users (id, email, password, first_name, last_name) values
    (6, 'applicant1@localhost.localdomain', 'abcd', 'Applicant1', 'User');
insert into users (id, email, password, first_name, last_name) values
    (7, 'applicant2@localhost.localdomain', 'abcd', 'Applicant2', 'User');
    
create table authorities (
    user_id bigint not null references users(id),
    role    varchar(255)
);

insert into authorities values (1, 'ROLE_ADMIN');
insert into authorities values (2, 'ROLE_REVIEWER');
insert into authorities values (3, 'ROLE_REVIEWER');
insert into authorities values (4, 'ROLE_REVIEWER');
insert into authorities values (5, 'ROLE_REVIEWER');

create table files (
    id          bigint primary key,
    name        varchar(255),
    type        varchar(255),
    content     text,
    size        bigint,
    date        timestamp,
    owner_id    bigint references users(id)
);

alter table files add column tsv tsvector;

create function files_ts_trigger_function() returns trigger as $$
begin
    new.tsv := to_tsvector(new.content);
    return new;
end
$$ language plpgsql;

create trigger files_ts_trigger
    before insert or update on files
    for each row execute procedure files_ts_trigger_function();

create index files_ts_index on files using gin(tsv);



create table jobs (
    id                  bigint primary key,
    title               varchar(255),
    description         varchar(80000),
    publish_date        timestamp,
    close_date          timestamp,
    committee_chair_id  bigint
);


insert into jobs (id, title, description, publish_date, committee_chair_id) values
    (1, 'Tenure-Track Faculty Position (Start Fall 2015)', 'Description ...', current_timestamp, 2);
insert into jobs (id, title, description, publish_date, committee_chair_id) values
    (2, 'Tenure-Track Faculty Position (Start Fall 2016)', 'Description ...', current_timestamp, 3);





    
create table job_committee_members (
    job_id  bigint not null references jobs(id),
    user_id bigint not null references users(id)
);

insert into job_committee_members (job_id, user_id) values (1, 2);
insert into job_committee_members (job_id, user_id) values (1, 3);
insert into job_committee_members (job_id, user_id) values (1, 4);
insert into job_committee_members (job_id, user_id) values (2, 3);
insert into job_committee_members (job_id, user_id) values (2, 4);
insert into job_committee_members (job_id, user_id) values (2, 5);

create table applications (
    id                      bigint primary key,
    job_id                  bigint references jobs(id),
    applicant_id            bigint references users(id),
    submit_date             timestamp,
    current_job_title       varchar(255),
    current_job_institution varchar(255),
    current_job_year        integer,
    cv_id                   bigint references files(id),
    research_statement_id   bigint references files(id),
    teaching_statement_id   bigint references files(id),
  unique (job_id, applicant_id)
);

insert into applications (id, job_id, applicant_id) values (1, 1, 6);
insert into applications (id, job_id, applicant_id) values (2, 1, 7);

create table application_degrees (
    application_id  bigint not null references applications(id),
    name            varchar(255),
    school          varchar(255),
    year            integer
);

create table rounds (
    id              bigint primary key,
    application_id  bigint references applications(id),
    round_index     integer not null,
    passed          boolean not null default 'f'
);

create table reviews (
    id          bigint primary key,
    round_id    bigint references rounds(id),
    reviewer_id bigint references users(id),
    comments    varchar(80000),
    rank        integer,
    date        timestamp default current_timestamp
);
