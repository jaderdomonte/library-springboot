INSERT INTO AUTHOR (id, name) VALUES (1, 'Author 1')
INSERT INTO AUTHOR (id, name) VALUES (2, 'Author 2')
INSERT INTO AUTHOR (id, name) VALUES (3, 'Author 3')

INSERT INTO BOOK (id, title, id_author) VALUES (1, 'Crianças Dinamarquesas', 1)
INSERT INTO BOOK (id, title, id_author) VALUES (2, 'Código Limpo', 2)
INSERT INTO BOOK (id, title, id_author) VALUES (3, 'Use a Cabeça', 3)

INSERT INTO USER (id, username, password, name, admin) VALUES (1, 'jader', '$2a$10$s9fhcu9DZc1NVxCYZH4yHeaIalJY.0nXOFF3IzzpP.r245gp2cJsy', 'Jader Augusto', true)
INSERT INTO USER (id, username, password, name, admin) VALUES (2, 'taise', '$2a$10$s9fhcu9DZc1NVxCYZH4yHeaIalJY.0nXOFF3IzzpP.r245gp2cJsy', 'Taíse Souza', false)