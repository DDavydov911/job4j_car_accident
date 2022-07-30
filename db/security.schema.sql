CREATE TABLE users (
                       username VARCHAR(50) NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       PRIMARY KEY (username)
);

CREATE TABLE authorities (
                             username VARCHAR(50) NOT NULL,
                             authority VARCHAR(50) NOT NULL,
                             FOREIGN KEY (username) REFERENCES users(username)
);

insert into users(username, password) values('user', '123456');
insert into users(username, password) values('admin', '123456');

insert into authorities(username, authority) values('user', 'USER');
insert into authorities(username, authority) values('admin', 'ADMIN');
