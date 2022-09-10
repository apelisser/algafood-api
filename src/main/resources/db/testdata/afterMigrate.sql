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
delete from grupo_permissao;
delete from usuario_grupo;
delete from restaurante_usuario_responsavel;
delete from pedido;
delete from item_pedido;
delete from foto_produto;
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
alter table pedido auto_increment = 1;
alter table item_pedido auto_increment = 1;


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

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, true, true, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (2, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (3, 'Tuk Tuk Comida Indiana', 15, 2, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, ativo, aberto) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp, true, true);   
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, ativo, aberto) values (7, 'Restaurante da baixada', 0.5, 1,  utc_timestamp, utc_timestamp, 'Vila Industrial', '19013-370', 'Residência', 'Rua Antonio Furtado de Miranda', '195', 3, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, ativo, aberto) values (8, 'Restaurante do pico', 0.7, 2, utc_timestamp, utc_timestamp, 'Vila Industrial', '19013-370', 'Residência', 'Rua Antonio Furtado de Miranda', '195', 3, true, true);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, ativo, aberto) values (9, 'Restaurante da amizade', 10.7, 1, utc_timestamp, utc_timestamp, 'Vila Industrial', '19013-370', 'Residência', 'Rua Antonio Furtado de Miranda', '195', 3, true, true);

insert into produto(id, nome, descricao, preco, ativo, restaurante_id) values (1, 'Batata Recheada', 'Descrição da batata', 19.5, false, 3);
insert into produto(id, nome, descricao, preco, ativo, restaurante_id) values (2, 'Lanche Gigante', 'Lanchão', 13.8, true, 2);
insert into produto(id, nome, descricao, preco, ativo, restaurante_id) values (3, 'Porção de Cebola', 'Não vale a pena', 39.5, true, 1);

insert into forma_pagamento (id, descricao, data_atualizacao) values (1, 'Cartão de crédito', utc_timestamp);
insert into forma_pagamento (id, descricao, data_atualizacao) values (2, 'Cartão de débito', utc_timestamp);
insert into forma_pagamento (id, descricao, data_atualizacao) values (3, 'Dinheiro', utc_timestamp);

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into usuario (id, nome, email, senha, data_cadastro) values
(1, 'Abner J Pelisser', 'abner.pelisser@gmail.com', '$2a$12$EeLCriyXJjsqwFereyGUtu.1VQKBfG7/ITdCZJFL4VVCpt92pcC2e', utc_timestamp),
(2, 'Maria Joaquina', 'abner_pelisser@hotmail.com', '$2a$12$EeLCriyXJjsqwFereyGUtu.1VQKBfG7/ITdCZJFL4VVCpt92pcC2e', utc_timestamp),
(3, 'José Souza', 'jose.aux@algafood.com', '$2a$12$EeLCriyXJjsqwFereyGUtu.1VQKBfG7/ITdCZJFL4VVCpt92pcC2e', utc_timestamp),
(4, 'Sebastião Martins', 'sebastiao.cad@algafood.com', '$2a$12$EeLCriyXJjsqwFereyGUtu.1VQKBfG7/ITdCZJFL4VVCpt92pcC2e', utc_timestamp);

insert into grupo (id, nome) values (1, 'Gerente'), (2, 'Vendedor'), (3, 'Secretária'), (4, 'Cadastrador');

insert into grupo_permissao (grupo_id, permissao_id) values (1, 1), (1, 2), (2, 1), (2, 2), (3, 1); 

insert into usuario_grupo (usuario_id, grupo_id) values (1, 1), (1, 2), (2, 2);

insert into usuario (id, nome, email, senha, data_cadastro) values (5, 'Manoel Lima', 'manoel.loja@gmail.com', '$2a$12$EeLCriyXJjsqwFereyGUtu.1VQKBfG7/ITdCZJFL4VVCpt92pcC2e', utc_timestamp);

insert into restaurante_usuario_responsavel (restaurante_id, usuario_id) values (1, 5), (3, 5);

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
    endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (1, '609abd53-913c-48a3-bd0a-ae95317facba', 1, 1, 1, 1, '38400-000', 'Rua Floriano Peixoto', '500', 'Apto 801', 'Brasil', 'CRIADO', utc_timestamp, 298.90, 10, 308.90);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values (1, 1, 1, 1, 78.9, 78.9, null);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values (2, 1, 2, 2, 110, 220, 'Menos picante, por favor');

insert into pedido (id, codigo, restaurante_id, usuario_cliente_id, forma_pagamento_id, endereco_cidade_id, endereco_cep, 
        endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, status, data_criacao, subtotal, taxa_frete, valor_total)
values (2, '8bba9df7-cdca-4fa5-94b3-98f27875f9ec', 4, 2, 2, 1, '38400-111', 'Rua Acre', '300', 'Casa 2', 'Centro', 'CRIADO', utc_timestamp, 79, 0, 79);

insert into item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario, preco_total, observacao) values (3, 2, 3, 1, 79, 79, 'Ao ponto');
