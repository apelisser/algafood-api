set foreign_key_checks = 0;
delete from cozinha;
delete from cidade;
delete from estado;
delete from forma_pagamento;
delete from grupo;
delete from permissao;
delete from produto;
delete from restaurante;
delete from usuario;
delete from restaurante_forma_pagamento;
set foreign_key_checks = 1;

alter table cidade auto_increment = 1;
alter table cozinha auto_increment = 1;
alter table estado auto_increment = 1;
alter table forma_pagamento auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table produto auto_increment = 1;
alter table restaurante auto_increment = 1;
alter table usuario auto_increment = 1;


insert into cozinha(id, nome) values (1, 'Tailandesa');
insert into cozinha(id, nome) values (2, 'Indiana');
insert into cozinha(id, nome) values (3, 'Brasileira');
insert into cozinha(id, nome) values (4, 'Japonesa');
insert into cozinha(id, nome) values (5, 'Mexicana');

insert into estado(id, nome) values (1, 'Paraná');
insert into estado(id, nome) values (2, 'São Paulo');
insert into estado(id, nome) values (3, 'Santa Catarina');

insert into cidade(id, nome, estado_id) values (1, "Paranavaí", 1);
insert into cidade(id, nome, estado_id) values (2, "Cruzeiro do Sul", 1);
insert into cidade(id, nome, estado_id) values (3, "Presidente Prudente", 2);
insert into cidade(id, nome, estado_id) values (4, "Joinvile", 3);

insert into restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, ativo) values (1, 'Restaurante da baixada', 0.5, 1,  utc_timestamp, utc_timestamp, 'Vila Industrial', '19013-370', 'Residência', 'Rua Antonio Furtado de Miranda', '195', 3, true);
insert into restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, ativo) values (2, 'Restaurante do pico', 0.7, 2, utc_timestamp, utc_timestamp, 'Vila Industrial', '19013-370', 'Residência', 'Rua Antonio Furtado de Miranda', '195', 3, true);
insert into restaurante(id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, ativo) values (3, 'Restaurante da amizade', 10.7, 1, utc_timestamp, utc_timestamp, 'Vila Industrial', '19013-370', 'Residência', 'Rua Antonio Furtado de Miranda', '195', 3, true);

insert into produto(id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Batata Recheada', 'Descrição da batata', 19.5, true, 3);
insert into produto(id, nome, descricao, preco, ativo, restaurante_id) values (2, 'Lanche Gigante', 'Lanchão', 13.8, true, 2);
insert into produto(id, nome, descricao, preco, ativo, restaurante_id) values (3, 'Porção de Cebola', 'Não vale a pena', 39.5, true, 1);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into grupo (nome) values ('Gerente'), ('Vendedor'), ('Secretária'), ('Cadastrador');