create table if not exists users (
  id          varchar_ignorecase(50)  default random_uuid() not null primary key,
  username    varchar_ignorecase(50)                        not null unique,
  password    varchar_ignorecase(500)                       not null,
  authorities varchar_ignorecase(50)  default 'ROLE_USER'   not null
);
