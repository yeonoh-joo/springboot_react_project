-- 1. 객체 제거 (FK -> PK)
DROP table if exists board;

DROP table if exists MEMBER;

DROP table if exists member_roles;

DROP table if exists product;

DROP table if exists user;

DROP table if exists user_roles;


-- 2. 객체 생성
create table board (
  hit bigint,
  no bigint not null auto_increment,
  update_date datetime(6),
  write_date datetime(6),
  writer varchar(30) not null,
  title varchar(300) not null,
  content text not null,
  member_id varchar(255),
  pw varchar(255) not null,
  primary key (no)
) engine=INNODB;

create table member (
  gender varchar(2) not null,
  birth datetime(6) not null,
  con_date datetime(6),
  reg_date datetime(6),
  name varchar(30) not null,
  address varchar(255),
  email varchar(255) not null,
  id varchar(255) not null,
  post_no varchar(255),
  pw varchar(255) not null,
  tel varchar(255),
  primary key (id),
  check (gender in ('여자', '남자'))
) engine=INNODB;

create table member_roles (
  member_id varchar(255) not null,
  roles varchar(255)
) engine=InnoDB

create table product (
  price integer not null,
  stock integer not null,
  create_at datetime(6),
  number bigint not null auto_increment,
  update_at datetime(6),
  name varchar(255) not null,
  primary key (number)
) engine=INNODB;

 create table user (
     id bigint not null auto_increment,
     name varchar(255) not null,
     password varchar(255) not null,
     uid varchar(255) not null,
     primary key (id)
 ) engine=InnoDB

 create table user_roles (
     user_id bigint not null,
     roles varchar(255)
 ) engine=INNODB;
    
    
-- 3. 제약 조건 설정
ALTER table if exists user 
 add constraint UKa7hlm8sj8kmijx6ucp7wfyt31 unique (uid);

alter table if exists board 
 add constraint FKsds8ox89wwf6aihinar49rmfy 
 foreign key (member_id) 
 references member (id);

alter table if exists member_roles 
 add constraint FKet63dfllh4o5qa9qwm7f5kx9x 
 foreign key (member_id) 
 references member (id);

alter table if exists user_roles 
 add constraint FK55itppkw3i07do3h7qoclqd4k 
 foreign key (user_id) 
 REFERENCES user (id);