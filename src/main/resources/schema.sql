create table venues
(
    venue_id   serial
        primary key,
    venue_name varchar(255) not null,
    location   varchar(255) not null
);

alter table venues
    owner to postgres;

create table events
(
    event_id   serial
        primary key,
    event_name varchar(255) not null,
    event_date timestamp    not null,
    venue_id   integer
        references venues
            on update cascade on delete cascade
);

alter table events
    owner to postgres;

create table attendees
(
    attendee_id   serial
        primary key,
    attendee_name varchar(255) not null,
    email         varchar(255) not null
        unique
);

alter table attendees
    owner to postgres;

create table event_attendee
(
    id          serial
        primary key,
    event_id    integer
        references events
            on update cascade on delete cascade,
    attendee_id integer
        references attendees
            on update cascade on delete cascade
);

alter table event_attendee
    owner to postgres;

