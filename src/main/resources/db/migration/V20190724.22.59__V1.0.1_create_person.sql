create table personal (
    id bigint not null AUTO_INCREMENT ,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL default 0,
    email VARCHAR(100),
    birthday timestamp NOT NULL default NOW() COMMENT '生日',
    PRIMARY KEY (id)
);