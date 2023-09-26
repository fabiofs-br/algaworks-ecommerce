DROP TABLE IF EXISTS testando;

ALTER TABLE categoria DROP FOREIGN KEY fk_categoria_categoria_pai;

ALTER TABLE cliente_contato DROP FOREIGN KEY fk_cliente_contato_cliente;

ALTER TABLE cliente_detalhe DROP FOREIGN KEY fk_cliente_detalhe_cliente;

ALTER TABLE estoque DROP FOREIGN KEY fk_estoque_produto;

ALTER TABLE item_pedido DROP FOREIGN KEY fk_item_pedido_pedido;

ALTER TABLE item_pedido DROP FOREIGN KEY fk_item_pedido_produto;

ALTER TABLE nota_fiscal DROP FOREIGN KEY fk_nota_fiscal_pedido;

ALTER TABLE pagamento DROP FOREIGN KEY fk_pagamento_pedido;

ALTER TABLE pedido DROP FOREIGN KEY fk_pedido_cliente;

ALTER TABLE produto_atributo DROP FOREIGN KEY fk_produto_atributo_produto;

ALTER TABLE produto_categoria DROP FOREIGN KEY fk_produto_categoria_categoria;

ALTER TABLE produto_categoria DROP FOREIGN KEY fk_produto_categoria_produto;

ALTER TABLE produto_tag DROP FOREIGN KEY fk_produto_tag_produto;

DROP TABLE IF EXISTS categoria;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS cliente_contato;
DROP TABLE IF EXISTS cliente_detalhe;
DROP TABLE IF EXISTS estoque;
DROP TABLE IF EXISTS item_pedido;
DROP TABLE IF EXISTS nota_fiscal;
DROP TABLE IF EXISTS pagamento;
DROP TABLE IF EXISTS pedido;
DROP TABLE IF EXISTS produto;
DROP TABLE IF EXISTS produto_atributo;
DROP TABLE IF EXISTS produto_categoria;
DROP TABLE IF EXISTS produto_tag;
