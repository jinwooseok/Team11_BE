use golajuma;

create table comment_tb
(
    comment_id      bigint auto_increment
        primary key,
    created_date    datetime(6)  not null,
    deleted         bit          not null,
    updated_date    datetime(6)  not null,
    comment_content varchar(255) not null,
    comment_user_id bigint       not null,
    comment_vote_id bigint       not null
);

create table decision_tb
(
    decision_id      bigint auto_increment
        primary key,
    created_date     datetime(6) not null,
    deleted          bit         not null,
    updated_date     datetime(6) not null,
    decision_user_id bigint      not null,
    decision_option_id bigint      not null
);

create table option_tb
(
    option_id      bigint auto_increment
        primary key,
    created_date   datetime(6)  not null,
    deleted        bit          not null,
    updated_date   datetime(6)  not null,
    option_count   bigint       null,
    option_image   varchar(255) null,
    option_name    varchar(255) not null,
    option_vote_id bigint       not null
);

create table user_entity
(
    user_id       bigint auto_increment
        primary key,
    created_date  datetime(6)  not null,
    deleted       bit          not null,
    updated_date  datetime(6)  not null,
    user_email    varchar(255) not null,
    user_nickname varchar(255) not null,
    user_password varchar(255) not null
);

create table auth_info_entity
(
    auth_info_id    bigint auto_increment
        primary key,
    created_date    datetime(6)  not null,
    deleted         bit          not null,
    updated_date    datetime(6)  not null,
    auth_info_token varchar(255) not null,
    auth_info_type  varchar(255) not null,
    user_id         bigint       null,
    constraint FKh6sbxhkdcpohqbb8o42r34h1p
        foreign key (user_id) references user_entity (user_id)
);

create table vote_tb
(
    vote_id          bigint auto_increment
        primary key,
    created_date     datetime(6)   not null,
    deleted          bit           not null,
    updated_date     datetime(6)   not null,
    vote_category    varchar(255)  not null,
    user_id          bigint        not null,
    vote_active      varchar(255)  not null,
    vote_content     varchar(1000) null,
    vote_end_date    datetime(6)   not null,
    vote_title       varchar(256)  not null,
    vote_type        varchar(255)  null,
    vote_total_count bigint        not null
);

alter table auth_info_entity convert to character set utf8;
alter table comment_tb convert to character set utf8;
alter table decision_tb convert to character set utf8;
alter table option_tb convert to character set utf8;
alter table user_entity convert to character set utf8;
alter table vote_tb convert to character set utf8;