CREATE TABLE types (
    id serial primary key,
    name varchar (255)
);
INSERT INTO types (name) VALUES('Две машины');
INSERT INTO types (name) VALUES('Машина и человек');
INSERT INTO types (name) VALUES('Машина и велосипед');


CREATE TABLE rules (
    id serial primary key,
    name varchar (255)
);
INSERT INTO rules (name) VALUES('Статья 1.');
INSERT INTO rules (name) VALUES('Статья 2.');
INSERT INTO rules (name) VALUES('Статья 3.');


CREATE TABLE accidents (
    id serial primary key,
    name varchar(2000),
    'text' varchar (3000),
    'address' varchar (2000),
    'type_id' integer references types(id)
);
INSERT INTO accidents (name, text, address, type_id) VALUES('accident 1', 'Дождь', 'ул.Луначарского, 8', 2);
INSERT INTO accidents (name, text, address, type_id) VALUES('accident 2', 'Не справился с управлением', 'пр.Ленина, 23', 1);
INSERT INTO accidents (name, text, address, type_id) VALUES('accident 3', 'Велосипедист ехал по проезжей части', 'ул.Строителей, 4', 3);


CREATE TABLE accidents_rules (
     accident_id integer references accidents(id),
     rules_id integer references rules(id)
);
INSERT INTO accidents_rules (accident_id, rules_id) VALUES(1, 2);
INSERT INTO accidents_rules (accident_id, rules_id) VALUES(2, 1);
INSERT INTO accidents_rules (accident_id, rules_id) VALUES(3, 1);
INSERT INTO accidents_rules (accident_id, rules_id) VALUES(3, 3);
