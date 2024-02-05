create table if not exists history
(
    primary key (entry_id),
    entry_id      serial,
    creating_time timestamp    not null,
    message       varchar(200) not null,
    encrypted     varchar(200) not null,
    algorithm     varchar(50)  not null
)
