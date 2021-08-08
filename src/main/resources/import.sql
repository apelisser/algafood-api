insert into cozinha(id, nome) values (1, 'Tailandesa');
insert into cozinha(id, nome) values (2, 'Indiana');

insert into estado(id, nome) values (1, 'Paraná');
insert into estado(id, nome) values (2, 'São Paulo');
insert into estado(id, nome) values (3, 'Santa Catarina');

insert into cidade(id, nome, estado_id) values (1, "Paranavaí", 1);
insert into cidade(id, nome, estado_id) values (2, "Cruzeiro do Sul", 1);
insert into cidade(id, nome, estado_id) values (3, "Presidente Prudente", 2);
insert into cidade(id, nome, estado_id) values (4, "Joinvile", 3);

insert into restaurante(nome, taxa_frete, cozinha_id, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id) values ('Restaurante da baixada', 0.5, 1, 'Vila Industrial', '19013-370', 'Residência', 'Rua Antonio Furtado de Miranda', '195', 3);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Restaurante do pico', 0.7, 2);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Restaurante da amizade', 10.7, 1);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);