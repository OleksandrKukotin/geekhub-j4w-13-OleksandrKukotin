create table if not exists Encryptions (
    id serial,
    time timestamp,
    message varchar(1000),
    encrypted varchar(1000),
    algorithm varchar(100)
)
