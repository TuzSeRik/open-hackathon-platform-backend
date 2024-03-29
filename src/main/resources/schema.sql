-- Authorization module start
create table if not exists teams (
    id     varchar_ignorecase(64)  default random_uuid() not null primary key,
    name   varchar_ignorecase(64)  default ''            not null unique,
    github varchar_ignorecase(256) default ''            not null,
    info   varchar_ignorecase(512) default ''            not null

);

create table if not exists users (
    id          varchar_ignorecase(64)  default random_uuid() not null primary key,
    team_id     varchar_ignorecase(64)  references teams(id)  ,
    username    varchar_ignorecase(64)                        not null unique,
    password    varchar_ignorecase(512)                       not null,
    authorities varchar_ignorecase(64)  default 'ROLE_USER'   not null

);

create table if not exists invites (
    id        varchar_ignorecase(64)  default random_uuid() not null primary key,
    team_id   varchar_ignorecase(64)  references teams(id)  not null unique,
    is_active boolean                 default true

);
-- Authorization module end

-- Information module start
create table if not exists information (
    id             varchar_ignorecase(64) default random_uuid() not null primary key,
    data           longnvarchar           default ''            not null,
    is_public      boolean                default false         not null unique

);
-- Information module end

-- Hackathon module start
create table if not exists hackathons (
    id         varchar_ignorecase(64)   default random_uuid()       not null primary key,
    start_time timestamp with time zone default current_timestamp() not null,
    end_time   timestamp with time zone default current_timestamp() not null,
    is_ready   boolean                  default false               not null unique

);

create table if not exists stages (
    id           varchar_ignorecase(64)   default random_uuid()       not null primary key,
    hackathon_id varchar_ignorecase(64)   references hackathons(id)   not null,
    start_time   timestamp with time zone default current_timestamp() not null,
    end_time     timestamp with time zone default current_timestamp() not null,
    is_ready     boolean                  default false               not null

);

create table if not exists tasks (
    id       varchar_ignorecase(64) default random_uuid() not null primary key,
    stage_id varchar_ignorecase(64) references stages(id) not null,
    is_ready boolean                default false         not null

);

create table if not exists task_problems (
    id      varchar_ignorecase(64)  default random_uuid() not null primary key,
    task_id varchar_ignorecase(64)  references tasks(id)  not null,
    type    varchar_ignorecase(64)  default 'TEXT'        not null,
    problem varchar_ignorecase(512) default ''            not null

);

create table if not exists results (
    id              varchar_ignorecase(64)  default random_uuid()        not null primary key,
    task_problem_id varchar_ignorecase(64)  references task_problems(id) not null,
    team_id         varchar_ignorecase(64)  references teams(id)         not null,
    answer          varchar_ignorecase(512) default ''                   not null,
    is_accepted     boolean                 default false                not null

);
-- Hackathon module end

-- Scoring module start
create table if not exists scores (
   id      varchar_ignorecase(64)  default random_uuid() not null primary key,
   team_id varchar_ignorecase(64)  references teams(id)  not null,
   score   integer                 default 0             not null,
   comment varchar_ignorecase(512) default ''            not null

);
-- Scoring module end
