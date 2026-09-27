-- ============================================================
-- PRATICA NAO AVALIATIVA 7 - SUBCONSULTAS
-- Aluno: Gabriel
-- ============================================================
 
 
-- ============================================================
-- BANCO DE DADOS: ModeloClassico
-- ============================================================
 
USE MODELOCLASSICO;
 
-- VERIFICANDO AS TABELAS DISPONIVEIS
SHOW TABLES;
 
-- VISUALIZANDO OS DADOS DAS TABELAS UTILIZADAS
SELECT * FROM CLIENTES;
SELECT * FROM PAGAMENTOS;
SELECT * FROM FUNCIONARIOS;
SELECT * FROM ESCRITORIOS;
SELECT * FROM PEDIDOS;
 
 
-- ------------------------------------------------------------
-- QUESTAO 1
-- Listar o cliente que efetuou o maior pagamento.
-- LOGICA: A subconsulta retorna o maior valor de MONTANTE_PAGO.
--         A consulta externa busca o cliente cujo pagamento
--         seja igual a esse valor maximo.
-- ------------------------------------------------------------
 
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_CLIENTE,
       P.MONTANTE_PAGO AS VALOR_PAGO
FROM CLIENTES C
INNER JOIN PAGAMENTOS P ON C.CLIENTE_ID = P.CLIENTE_ID
WHERE P.MONTANTE_PAGO =
(
    SELECT MAX(MONTANTE_PAGO) FROM PAGAMENTOS
);
 
 
-- ------------------------------------------------------------
-- QUESTAO 2
-- Listar os clientes cujos pagamentos sao maiores que a media.
-- LOGICA: A subconsulta calcula a media de todos os pagamentos.
--         A consulta externa retorna os clientes que pagaram
--         acima dessa media.
-- ------------------------------------------------------------
 
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_CLIENTE,
       P.MONTANTE_PAGO AS VALOR_PAGO
FROM CLIENTES C
INNER JOIN PAGAMENTOS P ON C.CLIENTE_ID = P.CLIENTE_ID
WHERE P.MONTANTE_PAGO >
(
    SELECT AVG(MONTANTE_PAGO) FROM PAGAMENTOS
);
 
 
-- ------------------------------------------------------------
-- QUESTAO 3
-- Liste os funcionarios que trabalham nos escritorios dos EUA.
-- LOGICA: A subconsulta retorna os IDs dos escritorios onde
--         o pais e 'USA'. A consulta externa usa IN para
--         trazer os funcionarios que estao nesses escritorios.
-- ------------------------------------------------------------
 
SELECT CONCAT(F.PRIMEIRO_NOME, ' ', F.ULTIMO_NOME) AS NOME_FUNCIONARIO,
       F.ESCRITORIO_ID
FROM FUNCIONARIOS F
WHERE F.ESCRITORIO_ID IN
(
    SELECT ESCRITORIO_ID FROM ESCRITORIOS
    WHERE PAIS = 'USA'
);
 
 
-- ------------------------------------------------------------
-- QUESTAO 4
-- Listar os clientes que nao fizeram nenhum pedido.
-- LOGICA: A subconsulta retorna os IDs dos clientes que
--         possuem pedidos. NOT IN exclui esses clientes,
--         retornando apenas quem nunca fez pedido.
-- ------------------------------------------------------------
 
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_CLIENTE
FROM CLIENTES C
WHERE C.CLIENTE_ID NOT IN
(
    SELECT DISTINCT CLIENTE_ID FROM PEDIDOS
);
 
 
-- ============================================================
-- BANCO DE DADOS: Sakila_pt
-- ============================================================
 
USE Sakila_pt;
 
-- VERIFICANDO AS TABELAS DISPONIVEIS
SHOW TABLES;
 
-- VISUALIZANDO OS DADOS DAS TABELAS UTILIZADAS
SELECT * FROM FILME;
 
 
-- ------------------------------------------------------------
-- QUESTAO 5
-- Liste o titulo dos filmes com preco da locacao acima da media.
-- LOGICA: A subconsulta calcula a media de PRECO_DA_LOCACAO.
--         A consulta externa retorna os filmes cujo preco
--         seja maior que essa media.
-- ------------------------------------------------------------
 
SELECT TITULO,
       PRECO_DA_LOCACAO
FROM FILME
WHERE PRECO_DA_LOCACAO >
(
    SELECT AVG(PRECO_DA_LOCACAO) FROM FILME
);
 
 
-- ------------------------------------------------------------
-- QUESTAO 6
-- Liste o titulo dos filmes com maior duracao da locacao.
-- LOGICA: A subconsulta retorna o valor maximo de
--         DURACAO_DA_LOCACAO. A consulta externa traz
--         os filmes com esse valor.
-- ------------------------------------------------------------
 
SELECT TITULO,
       DURACAO_DA_LOCACAO
FROM FILME
WHERE DURACAO_DA_LOCACAO =
(
    SELECT MAX(DURACAO_DA_LOCACAO) FROM FILME
);
 
 
-- ------------------------------------------------------------
-- QUESTAO 7
-- Liste o titulo dos filmes com menor custo de substituicao.
-- LOGICA: A subconsulta retorna o menor valor de
--         CUSTO_DE_SUBSTITUICAO. A consulta externa traz
--         os filmes com esse valor minimo.
-- ------------------------------------------------------------
 
SELECT TITULO,
       CUSTO_DE_SUBSTITUICAO
FROM FILME
WHERE CUSTO_DE_SUBSTITUICAO =
(
    SELECT MIN(CUSTO_DE_SUBSTITUICAO) FROM FILME
);
 
 
-- ============================================================
-- FIM DA ATIVIDADE
-- ============================================================