CREATE DATABASE IF NOT EXISTS basicjpa;

use basicjpa;
create table member
(
    member_id bigint not null auto_increment,
    login_id  varchar(255),
    name      varchar(255),
    password  varchar(255),
    role      enum ('ADMIN','STUDENT'),
    primary key (member_id)
) engine = InnoDB;

create table study_record
(
    start_time time(6),
    study_day  date,
    study_mins integer not null,
    member_id  bigint,
    study_id   bigint  not null auto_increment,
    contents   tinytext,
    primary key (study_id)
) engine = InnoDB;

alter table study_record
    add constraint FKs074m29u8nq36f23iwo2upf4x
        foreign key (member_id)
            references member (member_id);

insert into member
values (null, 'admin', '관리자', 'admin', 'ADMIN');