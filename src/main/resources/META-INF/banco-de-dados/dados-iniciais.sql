INSERT INTO produto (id, nome, preco, data_criacao, descricao) VALUES (1, 'Kindle', 499.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
INSERT INTO produto (id, nome, preco, data_criacao, descricao) VALUES (3, 'Câmera GoPro Hero 7', 1400.0, DATE_SUB(SYSDATE(), INTERVAL 1 DAY), 'Desempenho 2x melhor.');

INSERT INTO cliente (id, nome) VALUES (1, 'Fernando Medeiros');
INSERT INTO cliente (id, nome) VALUES (2, 'Marcos Mariano');

INSERT INTO pedido (id, cliente_id, data_criacao, total, status) VALUES (1, 1, SYSDATE(), 100.0, 'AGUARDANDO');

INSERT INTO item_pedido (id, pedido_id, produto_id, preco_produto, quantidade) VALUES (1, 1, 1, 5.0, 2);

INSERT INTO categoria (id, nome) VALUES (1, 'Eletrônicos');