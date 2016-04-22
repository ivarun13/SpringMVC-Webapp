
    create table application_degrees (
        application_id int8 not null,
        name varchar(255),
        school varchar(255),
        year int4
    );

    create table applications (
        id int8 not null,
        current_job_institution varchar(255),
        current_job_title varchar(255),
        current_job_year int4,
        submit_date timestamp,
        applicant_id int8,
        cv_id int8,
        job_id int8,
        research_statement_id int8,
        teaching_statement_id int8,
        primary key (id)
    );

    create table authorities (
        user_id int8 not null,
        role varchar(255)
    );

    create table files (
        id int8 not null,
        date timestamp,
        name varchar(255),
        size int8,
        type varchar(255),
        owner_id int8,
        primary key (id)
    );

    create table job_committee_members (
        job_id int8 not null,
        user_id int8 not null
    );

    create table jobs (
        id int8 not null,
        close_date timestamp,
        description varchar(255),
        publish_date timestamp,
        title varchar(255),
        committee_chair_id int8,
        primary key (id)
    );

    create table reviews (
        id int8 not null,
        comments varchar(255),
        date timestamp,
        rank int4,
        reviewer_id int8,
        round_id int8,
        primary key (id)
    );

    create table rounds (
        id int8 not null,
        round_index int4 not null,
        passed boolean not null,
        application_id int8,
        primary key (id)
    );

    create table users (
        id int8 not null,
        address varchar(255),
        email varchar(255) not null,
        enabled boolean not null,
        first_name varchar(255),
        last_name varchar(255),
        password varchar(255) not null,
        phone varchar(255),
        primary key (id)
    );

    alter table applications 
        add constraint UK_64ys5tle1oyk7oolbnglyrws3 unique (job_id, applicant_id);

    alter table users 
        add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

    alter table application_degrees 
        add constraint FK_p23g7k8rypw0ba1ekhgytb5g 
        foreign key (application_id) 
        references applications;

    alter table applications 
        add constraint FK_lmcomkxa52fc4kw1pgin4ov1i 
        foreign key (applicant_id) 
        references users;

    alter table applications 
        add constraint FK_45fx5ileqp86itptlqie2vwp0 
        foreign key (cv_id) 
        references files;

    alter table applications 
        add constraint FK_3k2w7gtt3v412p8xhjmrfdjqo 
        foreign key (job_id) 
        references jobs;

    alter table applications 
        add constraint FK_5wuipr3kwue77u3x0x6xfqnaa 
        foreign key (research_statement_id) 
        references files;

    alter table applications 
        add constraint FK_lp9a5ppf6ye4dedegq72a32t4 
        foreign key (teaching_statement_id) 
        references files;

    alter table authorities 
        add constraint FK_s21btj9mlob1djhm3amivbe5e 
        foreign key (user_id) 
        references users;

    alter table files 
        add constraint FK_5ok1awgnfwcf01537ylbycyq1 
        foreign key (owner_id) 
        references users;

    alter table job_committee_members 
        add constraint FK_8i1xpob5ig8dunf8go0gceh4b 
        foreign key (user_id) 
        references users;

    alter table job_committee_members 
        add constraint FK_d12kbfko72rgnlcp96meiykvv 
        foreign key (job_id) 
        references jobs;

    alter table jobs 
        add constraint FK_4gbcg6sweyjppsfb9tw4fk1yp 
        foreign key (committee_chair_id) 
        references users;

    alter table reviews 
        add constraint FK_bnaf4eil2cek30ntv43920vu 
        foreign key (reviewer_id) 
        references users;

    alter table reviews 
        add constraint FK_lrodxjcqcl3wfmb4bkhvrxolx 
        foreign key (round_id) 
        references rounds;

    alter table rounds 
        add constraint FK_38dtvawacp71xvc1dpfrpapin 
        foreign key (application_id) 
        references applications;

    create sequence hibernate_sequence;
