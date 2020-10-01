insert into Lugar(id, nome) values(1, 'San José, Uruguay');
insert into Lugar(id, nome) values(2, 'Rivera, Uruguay');
insert into Lugar(id, nome) values(3, 'Porto Alegre, Brasil');
insert into Lugar(id, nome) values(4, 'Montevideo, Uruguay');
insert into Lugar(id, nome) values(5, 'Sao Pablo, Brasil');

insert into Pessoa(id, nome, sobrenome, email, lugarid) values(1, 'Julian', 'Amuedo', 'amuedojulian05@gmail.com', 1);
insert into Pessoa(id, nome, sobrenome, email, lugarid) values(2, 'Nicolás', 'García', 'nicogarcia.com', 2);
insert into Pessoa(id, nome, sobrenome, email, lugarid) values(3, 'Vicente', 'Martinez', 'vicentem@gmail.com', 3);
insert into Pessoa(id, nome, sobrenome, email, lugarid) values(4, 'Maria', 'Romero', 'romeromaria@gmail.com', 4);
insert into Pessoa(id, nome, sobrenome, email, lugarid) values(5, 'Susana', 'Gonzales', 'suzanagon@gmail.com', 5);

insert into Destinatario(id, nome, sobrenome, email) values(1, 'Andres', 'Lopez', 'exemplo1@gmail.com');
insert into Destinatario(id, nome, sobrenome, email) values(2, 'Cristian', 'Suárez', 'exemplo2@gmail.com');
insert into Destinatario(id, nome, sobrenome, email) values(3, 'Mariaelena', 'Gutierrez', 'exemplo3@gmail.com');
insert into Destinatario(id, nome, sobrenome, email) values(4, 'Sofia', 'Herrera', 'exemplo4@gmail.com');
insert into Destinatario(id, nome, sobrenome, email) values(5, 'Juan', 'Parado', 'exemplo5@gmail.com');

insert into Postal(id, post_date, detalhes, pessoaid, destinatarioid) values(1, CURRENT_TIMESTAMP(), 'Insert Text', 1, 1);
insert into Postal(id, post_date, detalhes, pessoaid, destinatarioid) values(2, CURRENT_TIMESTAMP(), 'Insert Text', 2, 2);
insert into Postal(id, post_date, detalhes, pessoaid, destinatarioid) values(3, CURRENT_TIMESTAMP(), 'Insert Text', 3, 3);
insert into Postal(id, post_date, detalhes, pessoaid, destinatarioid) values(4, CURRENT_TIMESTAMP(), 'Insert Text', 4, 4);
insert into Postal(id, post_date, detalhes, pessoaid, destinatarioid) values(5, CURRENT_TIMESTAMP(), 'Insert Text', 5, 5);

