-- 1. 객체 제거 (FK -> PK)
DROP TABLE if EXISTS board;Hibernate: 
DROP TABLE if EXISTS member_roles
DROP table if EXISTS member

-- 2. 객체 생성
CREATE TABLE board (
  hit BIGINT,
  no BIGINT NOT NULL AUTO_INCREMENT,
  update_date DATETIME(6),
  write_date DATETIME(6),
  writer VARCHAR(30) NOT NULL,
  title VARCHAR(300) NOT NULL,
  content TEXT not NULL,
  pw VARCHAR(255) NOT NULL,
  PRIMARY KEY (no)
) ENGINE=INNODB

CREATE TABLE member_roles (
member_id VARCHAR(255) NOT null,
roles VARCHAR(255)
) ENGINE=INNODB

CREATE TABLE MEMBER (
  gender VARCHAR(2) NOT NULL,
  birth DATETIME(6) NOT NULL,
  con_date DATETIME(6),
  reg_date DATETIME(6),
  name VARCHAR(30) NOT NULL,
  address VARCHAR(255),
  email VARCHAR(255) NOT NULL,
  id VARCHAR(255) NOT NULL,
  post_no VARCHAR(255),
  pw VARCHAR(255) NOT NULL,
  tel VARCHAR(255),
  PRIMARY KEY (id),
  CHECK (gender IN ('여자', '남자'))
) ENGINE=INNODB

-- 3. 제약 조건 설정
alter TABLE if exists board 
add constraint FKsds8ox89wwf6aihinar49rmfy 
foreign key (member_id) 
references member (id)

alter table if exists member_roles 
add constraint FKet63dfllh4o5qa9qwm7f5kx9x 
foreign key (member_id) 
REFERENCES MEMBER (id)