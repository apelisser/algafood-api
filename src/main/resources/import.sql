insert into cozinha(id, nome) values (1, 'Tailandesa');
insert into cozinha(id, nome) values (2, 'Indiana');

insert into restaurante(nome, taxa_frete, cozinha_id) values ('Restaurante da baixada', 0.5, 1);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Restaurante do pico', 0.7, 2);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Restaurante da amizade', 10.7, 1);

insert into forma_pagamento (id, descricao) values (1, 'Cartão de crédito');
insert into forma_pagamento (id, descricao) values (2, 'Cartão de débito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into estado(nome) values ('Paraná');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);