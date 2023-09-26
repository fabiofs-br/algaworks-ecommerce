INSERT INTO produto (id, nome, preco, data_criacao, descricao) VALUES (1, 'Kindle', 499.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
INSERT INTO produto (id, nome, preco, data_criacao, descricao) VALUES (3, 'Câmera GoPro Hero 7', 1400.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Desempenho 2x melhor.');

INSERT INTO cliente (id, nome, cpf) VALUES (1, 'Fernando Medeiros', '000');
INSERT INTO cliente (id, nome, cpf) VALUES (2, 'Marcos Mariano', '111');

INSERT INTO cliente_detalhe (cliente_id, sexo, data_nascimento) VALUES (1, 'MASCULINO', DATE_SUB(SYSDATE(), INTERVAL 27 YEAR));
INSERT INTO cliente_detalhe (cliente_id, sexo, data_nascimento) VALUES (2, 'MASCULINO', DATE_SUB(SYSDATE(), INTERVAL 30 YEAR));

INSERT INTO pedido (id, cliente_id, data_criacao, total, status) VALUES (1, 1, SYSDATE(), 998.0, 'AGUARDANDO');
INSERT INTO pedido (id, cliente_id, data_criacao, total, status) VALUES (2, 1, SYSDATE(), 499.0, 'AGUARDANDO');

INSERT INTO item_pedido (pedido_id, produto_id, preco_produto, quantidade) VALUES (1, 1, 499, 2);
INSERT INTO item_pedido (pedido_id, produto_id, preco_produto, quantidade) VALUES (2, 1, 499, 1);

INSERT INTO pagamento (pedido_id, status, numero_cartao, tipo_pagamento) VALUE (2, 'PROCESSANDO', '123', 'cartao');

INSERT INTO categoria (id, nome) VALUES (1, 'Eletrodomésticos');
