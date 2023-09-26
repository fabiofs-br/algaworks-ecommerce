CREATE TABLE testando (id INTEGER NOT NULL AUTO_INCREMENT, PRIMARY KEY (id)) ENGINE = InnoDB;

CREATE TABLE categoria(categoria_pai_id INTEGER, id INTEGER NOT NULL AUTO_INCREMENT, nome VARCHAR(100) NOT NULL, PRIMARY KEY (id)) ENGINE = InnoDB;

CREATE TABLE cliente(id INTEGER NOT NULL AUTO_INCREMENT, cpf VARCHAR(14)  NOT NULL, nome VARCHAR(100) NOT NULL, PRIMARY KEY (id)) ENGINE = InnoDB;

CREATE TABLE cliente_contato(cliente_id INTEGER NOT NULL, descricao VARCHAR(255), tipo VARCHAR(255) NOT NULL, PRIMARY KEY (cliente_id, tipo)) ENGINE = InnoDB;

CREATE TABLE cliente_detalhe(cliente_id INTEGER NOT NULL, data_nascimento DATE, sexo VARCHAR(30) NOT NULL, PRIMARY KEY (cliente_id)) ENGINE = InnoDB;

CREATE TABLE estoque(id INTEGER NOT NULL AUTO_INCREMENT, produto_id INTEGER NOT NULL, quantidade INTEGER, PRIMARY KEY (id)) ENGINE = InnoDB;

CREATE TABLE item_pedido(pedido_id INTEGER NOT NULL, preco_produto DECIMAL(38, 2) NOT NULL, produto_id INTEGER NOT NULL, quantidade INTEGER NOT NULL, PRIMARY KEY (pedido_id, produto_id)) ENGINE = InnoDB;

CREATE TABLE nota_fiscal(pedido_id INTEGER NOT NULL, data_emissao DATETIME(6) NOT NULL, xml BLOB NOT NULL, PRIMARY KEY (pedido_id)) ENGINE = InnoDB;

CREATE TABLE pagamento(pedido_id INTEGER NOT NULL, status VARCHAR(30) NOT NULL, tipo_pagamento VARCHAR(31) NOT NULL, numero_cartao  VARCHAR(50), codigo_barras VARCHAR(100), PRIMARY KEY (pedido_id)) ENGINE = InnoDB;

CREATE TABLE pedido(cliente_id INTEGER NOT NULL, estado VARCHAR(2), id INTEGER NOT NULL AUTO_INCREMENT, total DECIMAL(38, 2) NOT NULL, data_conclusao DATETIME(6), data_criacao DATETIME(6) NOT NULL, data_ultima_atualziacao DATETIME(6), cep VARCHAR(9), numero VARCHAR(10), status VARCHAR(30) NOT NULL, bairro VARCHAR(50), cidade VARCHAR(50), complemento VARCHAR(50), logradouro VARCHAR(100), PRIMARY KEY (id)) ENGINE = InnoDB;

CREATE TABLE produto(id INTEGER NOT NULL AUTO_INCREMENT, preco DECIMAL(38, 2), data_criacao DATETIME(6) NOT NULL, data_ultima_atualziacao DATETIME(6), nome VARCHAR(100) NOT NULL, descricao LONGTEXT, foto BLOB, PRIMARY KEY (id)) ENGINE = InnoDB;

CREATE TABLE produto_atributo(produto_id INTEGER NOT NULL, nome VARCHAR(100) NOT NULL, valor VARCHAR(255)) ENGINE = InnoDB;

CREATE TABLE produto_categoria(categoria_id INTEGER NOT NULL, produto_id INTEGER NOT NULL) ENGINE = InnoDB;

CREATE TABLE produto_tag(produto_id INTEGER NOT NULL, tag VARCHAR(50) NOT NULL) ENGINE = InnoDB;

ALTER TABLE categoria ADD CONSTRAINT unq_nome UNIQUE (nome);

CREATE INDEX idx_nome ON cliente (nome);

ALTER TABLE cliente ADD CONSTRAINT unq_cpf UNIQUE (cpf);

ALTER TABLE estoque ADD CONSTRAINT UK_g72w2sa50w9a647f0eyhogus5 UNIQUE (produto_id);

CREATE INDEX idx_nome ON produto (nome);

ALTER TABLE produto ADD CONSTRAINT unq_nome UNIQUE (nome);

ALTER TABLE categoria ADD CONSTRAINT fk_categoria_categoria_pai FOREIGN KEY (categoria_pai_id) REFERENCES categoria (id);

ALTER TABLE cliente_contato ADD CONSTRAINT fk_cliente_contato_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE cliente_detalhe ADD CONSTRAINT fk_cliente_detalhe_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE estoque ADD CONSTRAINT fk_estoque_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE item_pedido ADD CONSTRAINT fk_item_pedido_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE item_pedido ADD CONSTRAINT fk_item_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE nota_fiscal ADD CONSTRAINT fk_nota_fiscal_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE pagamento ADD CONSTRAINT fk_pagamento_pedido FOREIGN KEY (pedido_id) REFERENCES pedido (id);

ALTER TABLE pedido ADD CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);

ALTER TABLE produto_atributo ADD CONSTRAINT fk_produto_atributo_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE produto_categoria ADD CONSTRAINT fk_produto_categoria_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (id);

ALTER TABLE produto_categoria ADD CONSTRAINT fk_produto_categoria_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

ALTER TABLE produto_tag ADD CONSTRAINT fk_produto_tag_produto FOREIGN KEY (produto_id) REFERENCES produto (id);