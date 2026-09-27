-- ============================================================
-- ATIVIDADE AVALIATIVA 3 - SUBCONSULTAS E CTE
-- ALUNO: GABRIEL SANZIO MACEDO PORTO
-- MATRICULA: 2025101410840142
-- ============================================================


-- ============================================================
-- BANCO DE DADOS: Sakila_pt
-- ============================================================

USE Sakila_pt;

-- VERIFICANDO AS TABELAS DISPONIVEIS
SHOW TABLES;

-- VISUALIZANDO OS DADOS DAS TABELAS UTILIZADAS
SELECT * FROM CLIENTE;
SELECT * FROM ALUGUEL;
SELECT * FROM PAGAMENTO;
SELECT * FROM FUNCIONARIO;
SELECT * FROM INVENTARIO;
SELECT * FROM FILME;


-- ------------------------------------------------------------
-- QUESTAO 1
-- Relacao de nomes dos clientes que possuem um filme alugado
-- no momento (ou seja, ainda nao devolveram).
-- LOGICA: Filmes nao devolvidos possuem DATA_DE_DEVOLUCAO nula.
--         A subconsulta retorna os CLIENTE_ID que ainda tem
--         aluguel em aberto. IN traz os clientes correspondentes.
-- ------------------------------------------------------------

SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE
FROM CLIENTE C
WHERE C.CLIENTE_ID IN
(
    SELECT CLIENTE_ID FROM ALUGUEL
    WHERE DATA_DE_DEVOLUCAO IS NULL
);


-- ------------------------------------------------------------
-- QUESTAO 2
-- Listar qual o cliente que mais alugou filmes.
-- LOGICA: A subconsulta interna conta os alugueis por cliente.
--         A subconsulta do meio pega o maior total.
--         A consulta externa retorna o cliente com esse total.
-- ------------------------------------------------------------

SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
COUNT(A.ALUGUEL_ID) AS TOTAL_ALUGUEIS
FROM CLIENTE C
INNER JOIN ALUGUEL A ON C.CLIENTE_ID = A.CLIENTE_ID
GROUP BY C.CLIENTE_ID
HAVING TOTAL_ALUGUEIS =
(
    SELECT MAX(TOTAL) FROM
    (
        SELECT COUNT(ALUGUEL_ID) AS TOTAL
        FROM ALUGUEL
        GROUP BY CLIENTE_ID
    ) AS CONTAGEM
);

-- FIZ DE DUAS MANEIRA PARA ENCONTRAR O MESMO RESULTADO

SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
       COUNT(A.ALUGUEL_ID) AS TOTAL_ALUGUEIS
FROM CLIENTE C
INNER JOIN ALUGUEL A ON C.CLIENTE_ID = A.CLIENTE_ID
GROUP BY C.CLIENTE_ID
ORDER BY TOTAL_ALUGUEIS DESC
LIMIT 1;


-- ------------------------------------------------------------
-- QUESTAO 3
-- Listar qual foi o cliente que mais pagou por alugueis.
-- LOGICA: A subconsulta interna soma os pagamentos por cliente.
--         A subconsulta do meio pega o maior valor total.
--         A consulta externa retorna o cliente com essa soma.
-- ------------------------------------------------------------

SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
SUM(P.VALOR) AS TOTAL_PAGO
FROM CLIENTE C
INNER JOIN PAGAMENTO P ON C.CLIENTE_ID = P.CLIENTE_ID
GROUP BY C.CLIENTE_ID
HAVING TOTAL_PAGO =
(
    SELECT MAX(TOTAL) FROM
    (
        SELECT SUM(VALOR) AS TOTAL
        FROM PAGAMENTO
        GROUP BY CLIENTE_ID
    ) AS SOMATORIO
);

-- FIZ DE DUAS MANEIRA PARA ENCONTRAR O MESMO RESULTADO

SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
       SUM(P.VALOR) AS TOTAL_PAGO
FROM CLIENTE C
INNER JOIN PAGAMENTO P ON C.CLIENTE_ID = P.CLIENTE_ID
GROUP BY C.CLIENTE_ID
ORDER BY TOTAL_PAGO DESC
LIMIT 1;


-- ------------------------------------------------------------
-- QUESTAO 4
-- Listar qual o funcionario que fez mais atendimentos.
-- LOGICA: Cada registro em ALUGUEL possui FUNCIONARIO_ID.
--         Contamos os alugueis por funcionario e comparamos
--         com o maximo via subconsulta aninhada.
-- ------------------------------------------------------------

SELECT CONCAT(F.PRIMEIRO_NOME, ' ', F.ULTIMO_NOME) AS NOME_DO_FUNCIONARIO,
COUNT(A.ALUGUEL_ID) AS TOTAL_ATENDIMENTOS
FROM FUNCIONARIO F
INNER JOIN ALUGUEL A ON F.FUNCIONARIO_ID = A.FUNCIONARIO_ID
GROUP BY F.FUNCIONARIO_ID
HAVING TOTAL_ATENDIMENTOS =
(
    SELECT MAX(TOTAL) FROM
    (
        SELECT COUNT(ALUGUEL_ID) AS TOTAL
        FROM ALUGUEL
        GROUP BY FUNCIONARIO_ID
    ) AS CONTAGEM
);

-- FIZ DE DUAS MANEIRA PARA ENCONTRAR O MESMO RESULTADO

SELECT CONCAT(F.PRIMEIRO_NOME, ' ', F.ULTIMO_NOME) AS NOME_DO_FUNCIONARIO,
       COUNT(A.ALUGUEL_ID) AS TOTAL_ATENDIMENTOS
FROM FUNCIONARIO F
INNER JOIN ALUGUEL A ON F.FUNCIONARIO_ID = A.FUNCIONARIO_ID
GROUP BY F.FUNCIONARIO_ID
ORDER BY TOTAL_ATENDIMENTOS DESC
LIMIT 1;


-- ------------------------------------------------------------
-- QUESTAO 5
-- Listar o nome do filme mais alugado.
-- LOGICA: ALUGUEL se liga ao INVENTARIO pelo INVENTARIO_ID,
--         e INVENTARIO se liga ao FILME pelo FILME_ID.
--         Contamos os alugueis por filme e comparamos com
--         o maximo via subconsulta aninhada.
-- ------------------------------------------------------------

SELECT F.TITULO, COUNT(A.ALUGUEL_ID) AS TOTAL_ALUGUEIS
FROM FILME F
INNER JOIN INVENTARIO I ON F.FILME_ID = I.FILME_ID
INNER JOIN ALUGUEL A ON I.INVENTARIO_ID = A.INVENTARIO_ID
GROUP BY F.FILME_ID
HAVING TOTAL_ALUGUEIS =
(
    SELECT MAX(TOTAL) FROM
    (
        SELECT COUNT(A2.ALUGUEL_ID) AS TOTAL
        FROM INVENTARIO I2
        INNER JOIN ALUGUEL A2 ON I2.INVENTARIO_ID = A2.INVENTARIO_ID
        GROUP BY I2.FILME_ID
    ) AS CONTAGEM
);

-- FIZ DE DUAS MANEIRA PARA ENCONTRAR O MESMO RESULTADO

SELECT F.TITULO,
       COUNT(A.ALUGUEL_ID) AS TOTAL_ALUGUEIS
FROM FILME F
INNER JOIN INVENTARIO I ON F.FILME_ID = I.FILME_ID
INNER JOIN ALUGUEL A ON I.INVENTARIO_ID = A.INVENTARIO_ID
GROUP BY F.FILME_ID
ORDER BY TOTAL_ALUGUEIS DESC
LIMIT 1;

-- ============================================================
-- BANCO DE DADOS: ModeloClassico
-- ============================================================

USE ModeloClassico;

-- VERIFICANDO AS TABELAS DISPONIVEIS
SHOW TABLES;

-- VISUALIZANDO OS DADOS DAS TABELAS UTILIZADAS
SELECT * FROM CLIENTES;
SELECT * FROM FUNCIONARIOS;
SELECT * FROM ESCRITORIOS;
SELECT * FROM PEDIDOS;
SELECT * FROM DETALHES_DO_PEDIDO;
SELECT * FROM PRODUTOS;


-- ------------------------------------------------------------
-- QUESTAO 6
-- Lista o nome da cidade que possui mais funcionarios.
-- LOGICA: FUNCIONARIOS tem ESCRITORIO_ID que referencia
--         ESCRITORIOS onde esta a CIDADE.
--         Contamos funcionarios por escritorio e comparamos
--         com o maximo via subconsulta aninhada.
-- ------------------------------------------------------------

SELECT E.CIDADE, COUNT(F.FUNCIONARIO_ID) AS TOTAL_FUNCIONARIOS
FROM ESCRITORIOS E
INNER JOIN FUNCIONARIOS F ON E.ESCRITORIO_ID = F.ESCRITORIO_ID
GROUP BY E.ESCRITORIO_ID
HAVING TOTAL_FUNCIONARIOS =
(
    SELECT MAX(TOTAL) FROM
    (
        SELECT COUNT(FUNCIONARIO_ID) AS TOTAL
        FROM FUNCIONARIOS
        GROUP BY ESCRITORIO_ID
    ) AS CONTAGEM
);

-- FIZ DE DUAS MANEIRA PARA ENCONTRAR O MESMO RESULTADO

SELECT E.CIDADE,
       COUNT(F.FUNCIONARIO_ID) AS TOTAL_FUNCIONARIOS
FROM ESCRITORIOS E
INNER JOIN FUNCIONARIOS F ON E.ESCRITORIO_ID = F.ESCRITORIO_ID
GROUP BY E.ESCRITORIO_ID
ORDER BY TOTAL_FUNCIONARIOS DESC
LIMIT 1;

-- ------------------------------------------------------------
-- QUESTAO 7
-- Lista qual o nome do produto que vendeu mais unidades.
-- LOGICA: Somamos QUANTIDADE_ENCOMENDADA por produto em
--         DETALHES_DO_PEDIDO e comparamos com o maximo
--         via subconsulta aninhada.
-- ------------------------------------------------------------

SELECT P.NOME AS NOME_DO_PRODUTO, SUM(DP.QUANTIDADE_ENCOMENDADA) AS TOTAL_VENDIDO
FROM PRODUTOS P
INNER JOIN DETALHES_DO_PEDIDO DP ON P.PRODUTO_ID = DP.PRODUTO_ID
GROUP BY P.PRODUTO_ID
HAVING TOTAL_VENDIDO =
(
    SELECT MAX(TOTAL) FROM
    (
        SELECT SUM(QUANTIDADE_ENCOMENDADA) AS TOTAL
        FROM DETALHES_DO_PEDIDO
        GROUP BY PRODUTO_ID
    ) AS SOMATORIO
);

-- FIZ DE DUAS MANEIRA PARA ENCONTRAR O MESMO RESULTADO

SELECT P.NOME, SUM(DP.QUANTIDADE_ENCOMENDADA) AS TOTAL_VENDIDO
FROM PRODUTOS P
INNER JOIN DETALHES_DO_PEDIDO DP ON P.PRODUTO_ID = DP.PRODUTO_ID
GROUP BY P.PRODUTO_ID
ORDER BY TOTAL_VENDIDO DESC
LIMIT 1;

-- ------------------------------------------------------------
-- QUESTAO 8
-- Listar qual o cliente que comprou mais produtos.
-- LOGICA: CLIENTES -> PEDIDOS -> DETALHES_DO_PEDIDO.
--         Somamos a quantidade total de produtos comprados
--         por cliente e comparamos com o maximo.
-- ------------------------------------------------------------

SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
       SUM(DP.QUANTIDADE_ENCOMENDADA) AS TOTAL_PRODUTOS
FROM CLIENTES C
INNER JOIN PEDIDOS PD ON C.CLIENTE_ID = PD.CLIENTE_ID
INNER JOIN DETALHES_DO_PEDIDO DP ON PD.PEDIDO_ID = DP.PEDIDO_ID
GROUP BY C.CLIENTE_ID
HAVING TOTAL_PRODUTOS =
(
    SELECT MAX(TOTAL) FROM
    (
        SELECT SUM(DP2.QUANTIDADE_ENCOMENDADA) AS TOTAL
        FROM PEDIDOS PD2
        INNER JOIN DETALHES_DO_PEDIDO DP2 ON PD2.PEDIDO_ID = DP2.PEDIDO_ID
        GROUP BY PD2.CLIENTE_ID
    ) AS SOMATORIO
);

-- FIZ DE DUAS MANEIRA PARA ENCONTRAR O MESMO RESULTADO
 
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
       SUM(DP.QUANTIDADE_ENCOMENDADA) AS TOTAL_PRODUTOS
FROM CLIENTES C
INNER JOIN PEDIDOS PD ON C.CLIENTE_ID = PD.CLIENTE_ID
INNER JOIN DETALHES_DO_PEDIDO DP ON PD.PEDIDO_ID = DP.PEDIDO_ID
GROUP BY C.CLIENTE_ID
ORDER BY TOTAL_PRODUTOS DESC
LIMIT 1;

-- ============================================================
-- BANCO DE DADOS: world
-- ============================================================

USE world;

-- VERIFICANDO AS TABELAS DISPONIVEIS
SHOW TABLES;

-- VISUALIZANDO OS DADOS DAS TABELAS UTILIZADAS
SELECT * FROM country;


-- ------------------------------------------------------------
-- QUESTAO 9
-- Lista os paises cuja populacao total e maior que a media
-- da populacao de todos os paises da Europa.
-- LOGICA: A subconsulta calcula a media de populacao apenas
--         dos paises onde Continent = 'Europe'.
--         A consulta externa retorna os paises acima dessa media.
-- ------------------------------------------------------------

SELECT Name AS NOME_DO_PAIS, Population AS POPULACAO FROM country
WHERE Population >
(
    SELECT AVG(Population) FROM country
    WHERE Continent = 'Europe'
)
ORDER BY Population DESC;


-- ------------------------------------------------------------
-- QUESTAO 10
-- Lista o continente com a menor media de PIB (GNP),
-- excluindo a Antarctica.
-- LOGICA: Agrupamos por continente, calculamos AVG(GNP),
--         excluimos Antarctica com WHERE, ordenamos ASC
--         e pegamos apenas o primeiro com LIMIT 1.
-- ------------------------------------------------------------

SELECT Continent AS CONTINENTE, AVG(GNP) AS MEDIA_GNP FROM country
WHERE Continent <> 'Antarctica'
AND GNP IS NOT NULL
GROUP BY Continent
ORDER BY MEDIA_GNP ASC
LIMIT 1;


-- ============================================================
-- FIM DA ATIVIDADE
-- ============================================================
