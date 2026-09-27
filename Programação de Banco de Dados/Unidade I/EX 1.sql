CREATE DATABASE EX;
USE EX;

-- 1. Tabela: Clientes
CREATE TABLE Clientes (
    id_cliente INT          PRIMARY KEY AUTO_INCREMENT,
    nome       VARCHAR(100) NOT NULL,
    email      VARCHAR(150) NOT NULL UNIQUE
);

-- 2. Tabela: Produtos
CREATE TABLE Produtos (
    id_produto   INT          PRIMARY KEY AUTO_INCREMENT,
    nome_produto VARCHAR(100),
    preco        DECIMAL(10, 2)
);

-- 3. Tabela: Pedidos
CREATE TABLE Pedidos (
    id_pedido   INT  PRIMARY KEY AUTO_INCREMENT,
    data_pedido DATE,
    id_cliente  INT,
    CONSTRAINT fk_pedido_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES Clientes(id_cliente)
);

-- 4. Tabela: Itens_Pedido (com CASCADE DELETE)
CREATE TABLE Itens_Pedido (
    id_item    INT PRIMARY KEY AUTO_INCREMENT,
    id_pedido  INT,
    id_produto INT,
    quantidade INT,
    CONSTRAINT fk_item_pedido
        FOREIGN KEY (id_pedido)
        REFERENCES Pedidos(id_pedido)
        ON DELETE CASCADE,
    CONSTRAINT fk_item_produto
        FOREIGN KEY (id_produto)
        REFERENCES Produtos(id_produto)
);

SHOW TABLES;

DELETE FROM Pedidos WHERE id_pedido = 1;