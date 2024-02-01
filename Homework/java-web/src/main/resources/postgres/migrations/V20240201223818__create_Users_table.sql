create table if not exists Users
(
    userID serial primary key,
    name   varchar(100),
    email  varchar(256)
);

insert into Users (name, email)
values ('Johnny Silverhand', 'rockerboy@nc.com');

alter table encryptions
    rename to History;
alter table History
    add primary key (id),
    add column userID int references Users (userID);
