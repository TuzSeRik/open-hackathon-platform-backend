create table if not exists users (
  id          varchar_ignorecase(50)  default random_uuid() not null primary key,
  username    varchar_ignorecase(50)                        not null unique,
  password    varchar_ignorecase(500)                       not null,
  authorities varchar_ignorecase(50)  default 'ROLE_USER'   not null
);

insert into users values ('root', 'admin', '$2a$12$QO2WJBaNvxEfqmglRkV9T.j0Qjtkbe2mD0woI1gr6OF9s1aMRICJO', 'ROLE_USER,ROLE_ADMIN');
