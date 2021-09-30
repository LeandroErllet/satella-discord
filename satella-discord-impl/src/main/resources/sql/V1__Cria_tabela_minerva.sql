create table if not exists server
(
    id_server BIGINT      not null,
    name      varchar(80) not null
    );

alter table server
    add constraint pk_servers primary key (id_server);


create table if not exists member
(
    id_member BIGINT      not null,
    name      varchar(80) not null,
    id_server BIGINT      not null,
    role_type smallint    not null
    );

alter table member
    add constraint pk_members primary key (id_member);
alter table member
    add constraint fk_member foreign key (id_server) references server (id_server) on delete cascade on update cascade;



create table if not exists channel
(
    id_channel   BIGINT   not null,
    id_server    BIGINT   not null,
    channel_type smallint not null
);

alter table channel
    add constraint pk_channel primary key (id_channel);
alter table channel
    add constraint fk_channel foreign key (id_server) references server (id_server) on delete cascade on update cascade;

create table if not exists reaction
(
    id_reaction BIGINT not null,
    id_server   BIGINT not null
);

alter table reaction
    add constraint pk_reaction primary key (id_reaction);
alter table reaction
    add constraint fk_reaction foreign key (id_server) references server (id_server) on delete cascade on update cascade;

create table if not exists role_group
(
    id_role_group  BIGINT      not null,
    role_source_id BIGINT      not null,
    role_result_id BIGINT      not null,
    image_link     varchar(80) not null,
    id_server      BIGINT      not null
    );

alter table role_group
    add constraint pk_role_group primary key (id_role_group);
alter table role_group
    add constraint fk_role_group foreign key (id_server) references server (id_server) on delete cascade on update cascade;