-- ============================================================
-- EXERCICIOS - VIEWS (VISOES)
-- ALUNO: GABRIEL SANZIO MACEDO PORTO
-- MATRICULA: 2025101410840142
-- ============================================================
 
 
-- ============================================================
-- BANCO DE DADOS: Sakila_pt
-- ============================================================
 
USE Sakila_pt;
 
SHOW TABLES;
 
SELECT * FROM FILME;
SELECT * FROM FILME_CATEGORIA;
SELECT * FROM CATEGORIA;
SELECT * FROM CLIENTE;
SELECT * FROM PAGAMENTO;
 
 
-- ------------------------------------------------------------
-- SAKILA - VIEW 1
-- View chamada VIEW_FILMES_ALUGADOS que exibe o titulo do
-- filme e o nome da categoria.
-- LOGICA: FILME se liga a CATEGORIA atraves da tabela
--         intermediaria FILME_CATEGORIA. Fazemos dois JOINs
--         para trazer o titulo e o nome da categoria.
-- ------------------------------------------------------------
 
DROP VIEW IF EXISTS VIEW_FILMES_ALUGADOS;
 
CREATE VIEW VIEW_FILMES_ALUGADOS AS
SELECT F.TITULO        AS TITULO_DO_FILME,
       C.NOME          AS CATEGORIA
FROM FILME F
INNER JOIN FILME_CATEGORIA FC ON F.FILME_ID = FC.FILME_ID
INNER JOIN CATEGORIA C        ON FC.CATEGORIA_ID = C.CATEGORIA_ID;
 
-- CONSULTANDO A VIEW
SELECT * FROM VIEW_FILMES_ALUGADOS;
 
 
-- ------------------------------------------------------------
-- SAKILA - VIEW 2
-- View chamada VIEW_RECEITA_POR_CLIENTE que mostra o nome
-- do cliente e o valor total pago por ele.
-- LOGICA: Unimos CLIENTE com PAGAMENTO e somamos os valores
--         com SUM() agrupando por cliente.
-- ------------------------------------------------------------
 
DROP VIEW IF EXISTS VIEW_RECEITA_POR_CLIENTE;
 
CREATE VIEW VIEW_RECEITA_POR_CLIENTE AS
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
       SUM(P.VALOR)                                 AS TOTAL_PAGO
FROM CLIENTE C
INNER JOIN PAGAMENTO P ON C.CLIENTE_ID = P.CLIENTE_ID
GROUP BY C.CLIENTE_ID;
 
-- CONSULTANDO A VIEW
SELECT * FROM VIEW_RECEITA_POR_CLIENTE;
SELECT * FROM VIEW_RECEITA_POR_CLIENTE ORDER BY TOTAL_PAGO DESC;
 
 
-- ------------------------------------------------------------
-- SAKILA - VIEW 3
-- View VIEW_CLIENTES_ATIVOS que exibe apenas clientes
-- onde ATIVO = 1.
-- LOGICA: Simples filtro WHERE na tabela CLIENTE para
--         retornar apenas os registros com cliente ativo.
-- ------------------------------------------------------------
 
DROP VIEW IF EXISTS VIEW_CLIENTES_ATIVOS;
 
CREATE VIEW VIEW_CLIENTES_ATIVOS AS
SELECT CLIENTE_ID,
       CONCAT(PRIMEIRO_NOME, ' ', ULTIMO_NOME) AS NOME_DO_CLIENTE,
       EMAIL,
       ATIVO
FROM CLIENTE
WHERE ATIVO = 1;
 
-- CONSULTANDO A VIEW
SELECT * FROM VIEW_CLIENTES_ATIVOS;
 
 
-- ============================================================
-- BANCO DE DADOS: ModeloClassico
-- ============================================================
 
USE ModeloClassico;
 
SHOW TABLES;
 
SELECT * FROM ESCRITORIOS;
SELECT * FROM FUNCIONARIOS;
SELECT * FROM CLIENTES;
SELECT * FROM PEDIDOS;
SELECT * FROM DETALHES_DO_PEDIDO;
SELECT * FROM PRODUTOS;
 
 
-- ------------------------------------------------------------
-- CLASSICMODELS - VIEW 4
-- View VIEW_VENDAS_POR_ESCRITORIO que lista a cidade do
-- escritorio, o pais e a soma total das vendas realizadas
-- pelos funcionarios daquele escritorio.
-- LOGICA: O caminho e: ESCRITORIOS -> FUNCIONARIOS -> CLIENTES
--         -> PEDIDOS -> DETALHES_DO_PEDIDO.
--         Funcionarios pertencem a um escritorio.
--         Clientes sao atendidos por funcionarios.
--         Pedidos pertencem a clientes.
--         O total e calculado como: QUANTIDADE * PRECO.
-- ------------------------------------------------------------
 
DROP VIEW IF EXISTS VIEW_VENDAS_POR_ESCRITORIO;
 
CREATE VIEW VIEW_VENDAS_POR_ESCRITORIO AS
SELECT E.CIDADE                                                      AS CIDADE,
       E.PAIS                                                        AS PAIS,
       SUM(DP.QUANTIDADE_ENCOMENDADA * DP.PRECO_DA_UNIDADE)          AS TOTAL_VENDAS
FROM ESCRITORIOS E
INNER JOIN FUNCIONARIOS F  ON E.ESCRITORIO_ID  = F.ESCRITORIO_ID
INNER JOIN CLIENTES     C  ON F.FUNCIONARIO_ID = C.FUNCIONARIO_ID
INNER JOIN PEDIDOS      PD ON C.CLIENTE_ID     = PD.CLIENTE_ID
INNER JOIN DETALHES_DO_PEDIDO DP ON PD.PEDIDO_ID = DP.PEDIDO_ID
GROUP BY E.ESCRITORIO_ID;
 
-- CONSULTANDO A VIEW
SELECT * FROM VIEW_VENDAS_POR_ESCRITORIO;
SELECT * FROM VIEW_VENDAS_POR_ESCRITORIO ORDER BY TOTAL_VENDAS DESC;
 
 
-- ------------------------------------------------------------
-- CLASSICMODELS - VIEW 5
-- View VIEW_CLIENTES_INATIVOS que seleciona o nome do cliente
-- e seu representante de vendas (FUNCIONARIO_ID).
-- Criterio: nao realizou nenhum pedido nos ultimos 12 meses
-- considerando a data atual como 01/01/2006, ou seja,
-- sem pedidos a partir de 01/01/2005.
-- LOGICA: A subconsulta retorna os CLIENTE_ID que fizeram
--         pedidos a partir de 01/01/2005. NOT IN exclui esses
--         clientes, retornando apenas os inativos no periodo.
-- ------------------------------------------------------------
 
DROP VIEW IF EXISTS VIEW_CLIENTES_INATIVOS;
 
CREATE VIEW VIEW_CLIENTES_INATIVOS AS
SELECT NOME_DO_CLIENTE,
       FUNCIONARIO_ID AS REPRESENTANTE_DE_VENDAS
FROM CLIENTES
WHERE CLIENTE_ID NOT IN
(
    SELECT DISTINCT CLIENTE_ID FROM PEDIDOS
    WHERE DATA_DO_PEDIDO >= '2005-01-01'
);
 
-- CONSULTANDO A VIEW
SELECT * FROM VIEW_CLIENTES_INATIVOS;
 
 
-- ------------------------------------------------------------
-- CLASSICMODELS - VIEW 6
-- View VIEW_ESTOQUE_CRITICO que exibe o nome do produto e
-- a quantidade em estoque, considerado critico se o estoque
-- for inferior a 10% do total de unidades vendidas.
-- LOGICA: A subconsulta agrupa as vendas por produto somando
--         QUANTIDADE_ENCOMENDADA. A consulta principal compara
--         o estoque atual com 10% desse total usando WHERE.
--         Se QUANTIA_EM_ESTOQUE < TOTAL_VENDIDO * 0.10,
--         o produto esta em estoque critico.
-- ------------------------------------------------------------
 
DROP VIEW IF EXISTS VIEW_ESTOQUE_CRITICO;
 
CREATE VIEW VIEW_ESTOQUE_CRITICO AS
SELECT P.NOME                AS NOME_DO_PRODUTO,
       P.QUANTIA_EM_ESTOQUE  AS ESTOQUE_ATUAL,
       V.TOTAL_VENDIDO        AS TOTAL_VENDIDO,
       V.TOTAL_VENDIDO * 0.10 AS LIMITE_CRITICO
FROM PRODUTOS P
INNER JOIN (
    SELECT PRODUTO_ID,
           SUM(QUANTIDADE_ENCOMENDADA) AS TOTAL_VENDIDO
    FROM DETALHES_DO_PEDIDO
    GROUP BY PRODUTO_ID
) AS V ON P.PRODUTO_ID = V.PRODUTO_ID
WHERE P.QUANTIA_EM_ESTOQUE < (V.TOTAL_VENDIDO * 0.10);
 
-- CONSULTANDO A VIEW
SELECT * FROM VIEW_ESTOQUE_CRITICO;
SELECT * FROM VIEW_ESTOQUE_CRITICO ORDER BY ESTOQUE_ATUAL ASC;
 
 
-- ============================================================
-- FIM DO EXERCICIO
-- ============================================================