INSERT INTO produto (id, nome, preco, data_criacao, descricao) VALUES (1, 'Kindle', 799.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
INSERT INTO produto (id, nome, preco, data_criacao, descricao) VALUES (3, 'Câmera GoPro Hero 7', 1500.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Desempenho 2x melhor.');
INSERT INTO produto (id, nome, preco, data_criacao, descricao) VALUES (4, 'Câmera Canon 80D', 3500.0, SYSDATE(), 'O melhor ajuste de foco.');
INSERT INTO produto (id, nome, preco, data_criacao, descricao) VALUES (5, 'Microfone de Lapela', 50.0, SYSDATE(), 'Produto massa.');

INSERT INTO cliente (id, nome, cpf) VALUES (1, 'Fernando Medeiros', '000');
INSERT INTO cliente (id, nome, cpf) VALUES (2, 'Marcos Mariano', '111');

INSERT INTO cliente_detalhe (cliente_id, sexo, data_nascimento) VALUES (1, 'MASCULINO', DATE_SUB(SYSDATE(), INTERVAL 27 YEAR));
INSERT INTO cliente_detalhe (cliente_id, sexo, data_nascimento) VALUES (2, 'MASCULINO', DATE_SUB(SYSDATE(), INTERVAL 30 YEAR));

INSERT INTO pedido (id, cliente_id, data_criacao, total, status) VALUES (1, 1, DATE_SUB(SYSDATE(), INTERVAL 32 DAY), 2398.0, 'AGUARDANDO');
INSERT INTO pedido (id, cliente_id, data_criacao, total, status) VALUES (2, 1, DATE_SUB(SYSDATE(), INTERVAL 5 DAY), 499.0, 'AGUARDANDO');
INSERT INTO pedido (id, cliente_id, data_criacao, total, status) VALUES (3, 1, DATE_SUB(SYSDATE(), INTERVAL 4 DAY), 3500.0, 'PAGO');
INSERT INTO pedido (id, cliente_id, data_criacao, total, status) VALUES (4, 2, DATE_SUB(SYSDATE(), INTERVAL 2 DAY), 499.0, 'PAGO');
INSERT INTO pedido (id, cliente_id, data_criacao, total, status) VALUES (5, 1, DATE_SUB(SYSDATE(), INTERVAL 2 day), 799.0, 'PAGO');
INSERT INTO pedido (id, cliente_id, data_criacao, total, status) VALUES (6, 2, DATE_SUB(SYSDATE(), INTERVAL 2 day), 799.0, 'AGUARDANDO');

INSERT INTO item_pedido (pedido_id, produto_id, preco_produto, quantidade) VALUES (1, 1, 499, 2);
INSERT INTO item_pedido (pedido_id, produto_id, preco_produto, quantidade) VALUES (1, 3, 1400, 1);
INSERT INTO item_pedido (pedido_id, produto_id, preco_produto, quantidade) VALUES (2, 1, 499, 1);
INSERT INTO item_pedido (pedido_id, produto_id, preco_produto, quantidade) VALUES (3, 4, 3500, 1);
INSERT INTO item_pedido (pedido_id, produto_id, preco_produto, quantidade) VALUES (4, 1, 499, 1);
INSERT INTO item_pedido (pedido_id, produto_id, preco_produto, quantidade) VALUES (5, 1, 799, 1);
INSERT INTO item_pedido (pedido_id, produto_id, preco_produto, quantidade) VALUES (6, 1, 799, 1);

INSERT INTO pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) VALUES (1, 'RECEBIDO', 'cartao', '0123', NULL);
INSERT INTO pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) VALUES (2, 'PROCESSANDO', 'cartao', '4567', NULL);
INSERT INTO pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras, data_vencimento) VALUES (3, 'RECEBIDO', 'boleto', NULL, '8910', DATE_SUB(SYSDATE(), INTERVAL 2 DAY));
INSERT INTO pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras) VALUES (4, 'PROCESSANDO', 'cartao', '1112', NULL);
INSERT INTO pagamento (pedido_id, status, tipo_pagamento, numero_cartao, codigo_barras, data_vencimento) VALUES (6, 'PROCESSANDO', 'boleto', NULL, '456', DATE_ADD(SYSDATE(), INTERVAL 2 DAY));

INSERT INTO nota_fiscal (pedido_id, xml, data_emissao) VALUES (2, '<xml />', SYSDATE());

INSERT INTO categoria (id, nome) VALUES (1, 'Eletrodomésticos');
INSERT INTO categoria (id, nome) VALUES (2, 'Livros');
INSERT INTO categoria (id, nome) VALUES (3, 'Esportes');
INSERT INTO categoria (id, nome) VALUES (4, 'Futebol');
INSERT INTO categoria (id, nome) VALUES (5, 'Natação');
INSERT INTO categoria (id, nome) VALUES (6, 'Notebooks');
INSERT INTO categoria (id, nome) VALUES (7, 'Smartphones');
INSERT INTO categoria (id, nome) VALUES (8, 'Câmeras');

INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (1, 2);
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (3, 8);
INSERT INTO produto_categoria (produto_id, categoria_id) VALUES (4, 8);
