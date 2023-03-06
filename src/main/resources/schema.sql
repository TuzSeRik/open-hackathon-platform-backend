create table if not exists users (
  id          varchar_ignorecase(50)  default random_uuid() not null primary key,
  username    varchar_ignorecase(50)                        not null unique,
  password    varchar_ignorecase(500)                       not null,
  authorities varchar_ignorecase(50)  default 'ROLE_USER'   not null
);

create table if not exists teams (
    id     varchar_ignorecase(64)  default random_uuid() not null primary key,
    name   varchar_ignorecase(64)                        not null unique,
    github varchar_ignorecase(256)                       not null,
    info   varchar_ignorecase(512)
);
