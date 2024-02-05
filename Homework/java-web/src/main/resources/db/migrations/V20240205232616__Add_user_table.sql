create table if not exists users
(
    primary key (user_id),
    user_id   serial,
    user_name varchar(100) not null,
    email     varchar(100)
);

insert into users (user_name, email)
values ('Johnny Silverhand', 'rockerboy@nc.net');

alter table history
    add column user_id int references users (user_id)
