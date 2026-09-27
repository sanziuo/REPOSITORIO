-- ============================================================
-- ATIVIDADE AVALIATIVA 2 - CONSULTAS AVANCADAS
-- ALUNO: GABRIEL SANZIO MACEDO PORTO
-- MATRICULA: 2025101410840142
-- ============================================================
 
 
-- ============================================================
-- BANCO DE DADOS: Sakila_pt
-- ============================================================
 
USE Sakila_pt;
 
-- Verificando as tabelas disponíveis no banco Sakila_pt
SHOW TABLES;
 
-- Visualizando a estrutura das tabelas utilizadas
SELECT * FROM cliente;
SELECT * FROM pagamento;
SELECT * FROM aluguel;
SELECT * FROM funcionario;
 
 
-- ------------------------------------------------------------
-- QUESTAO 1
-- Retorne o nome do cliente e quanto ele gastou em suas locacoes.
-- Logica: Unimos CLIENTE com PAGAMENTO pelo cliente_id,
--         somamos todos os valores pagos por cada cliente.
-- ------------------------------------------------------------
 
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
SUM(P.VALOR) AS TOTAL_GASTO
FROM CLIENTE C
JOIN PAGAMENTO P ON C.CLIENTE_ID = P.CLIENTE_ID
GROUP BY C.CLIENTE_ID;
 
-- ------------------------------------------------------------
-- QUESTAO 2
-- Valor total de vendas para cada loja (ID da loja + total).
-- Logica: PAGAMENTO nao tem loja diretamente, mas tem FUNCIONARIO_ID.
--         FUNCIONARIO tem LOJA_ID. Entao unimos as duas tabelas
--         e somamos os pagamentos agrupados por loja.
-- ------------------------------------------------------------

SELECT F.LOJA_ID, SUM(P.VALOR) AS TOTAL_DE_PAGAMENTOS
FROM PAGAMENTO P
JOIN FUNCIONARIO F ON P.FUNCIONARIO_ID = F.FUNCIONARIO_ID
GROUP BY F.LOJA_ID;
 

-- ------------------------------------------------------------
-- QUESTAO 3
-- Nome do cliente e o valor maximo pago, apenas os que pagaram
-- mais de R$10,00 em algum pagamento.
-- Logica: Unimos CLIENTE e PAGAMENTO, usamos MAX() para pegar
--         o maior valor e filtramos com HAVING > 10.00.
-- ------------------------------------------------------------
 
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
       MAX(P.VALOR) AS MAIOR_PAGAMENTO
FROM CLIENTE C
INNER JOIN PAGAMENTO P ON C.CLIENTE_ID = P.CLIENTE_ID
WHERE P.VALOR > 10.00
GROUP BY C.CLIENTE_ID;


-- ------------------------------------------------------------
-- QUESTAO 4
-- Nome do cliente e a data do seu ultimo aluguel.
-- Logica: Unimos CLIENTE com ALUGUEL e usamos MAX() na coluna
--         DATA_DE_ALUGUEL para obter a data mais recente.
-- ------------------------------------------------------------
 
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
MAX(A.DATA_DE_ALUGUEL) AS ULTIMO_ALUGUEL
FROM CLIENTE C
INNER JOIN ALUGUEL A ON C.CLIENTE_ID = A.CLIENTE_ID
GROUP BY C.CLIENTE_ID;
 
 
-- ============================================================
-- BANCO DE DADOS: ModeloClassico
-- ============================================================
 
USE ModeloClassico;
 
-- Verificando as tabelas disponíveis no banco ModeloClassico
SHOW TABLES;
 
-- Visualizando a estrutura das tabelas utilizadas
SELECT * FROM clientes;
SELECT * FROM pagamentos;
SELECT * FROM pedidos;
SELECT * FROM detalhes_do_pedido;
SELECT * FROM produtos;
 
 
-- ------------------------------------------------------------
-- QUESTAO 5
-- Nome do cliente e o valor total dos pagamentos feitos por ele.
-- SUM()    - SOMATORIA DE MONTANTE_PAGO POR CLIENTE
-- CONCAT() - JUNTA PRIMEIRO E ULTIMO NOME DO CLIENTE
-- ------------------------------------------------------------
 
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
SUM(P.MONTANTE_PAGO) AS TOTAL_PAGO
FROM CLIENTES C
INNER JOIN PAGAMENTOS P ON C.CLIENTE_ID = P.CLIENTE_ID
GROUP BY C.CLIENTE_ID;
 
 
-- ------------------------------------------------------------
-- QUESTAO 6
-- Nome e valor total da receita gerada por cada produto.
-- A RECEITA E CALCULADA COMO: QUANTIDADE_ENCOMENDADA * PRECO_DA_UNIDADE
-- SUM() - SOMA A RECEITA DE TODAS AS VENDAS DE CADA PRODUTO
-- ------------------------------------------------------------
 
SELECT P.NOME AS NOME_DO_PRODUTO,
SUM(DP.QUANTIDADE_ENCOMENDADA * DP.PRECO_DA_UNIDADE) AS RECEITA_TOTAL
FROM PRODUTOS P
INNER JOIN DETALHES_DO_PEDIDO DP ON P.PRODUTO_ID = DP.PRODUTO_ID
GROUP BY P.PRODUTO_ID;
 
 
-- ------------------------------------------------------------
-- QUESTAO 7
-- Nome do cliente e a quantidade de pedidos feitos por ele.
-- COUNT() - CONTA A QUANTIDADE DE PEDIDOS POR CLIENTE
-- ------------------------------------------------------------
 
SELECT CONCAT(C.PRIMEIRO_NOME, ' ', C.ULTIMO_NOME) AS NOME_DO_CLIENTE,
COUNT(PD.PEDIDO_ID) AS TOTAL_DE_PEDIDOS
FROM CLIENTES C
INNER JOIN PEDIDOS PD ON C.CLIENTE_ID = PD.CLIENTE_ID
GROUP BY C.CLIENTE_ID;
 
 
-- ============================================================
-- BANCO DE DADOS: world
-- ============================================================
 
USE world;
 
-- VERIFICANDO AS TABELAS DISPONIVEIS
SHOW TABLES;
 
-- VISUALIZANDO OS DADOS DAS TABELAS UTILIZADAS
SELECT * FROM country;
SELECT * FROM city;
SELECT * FROM countrylanguage;
 
 
-- ------------------------------------------------------------
-- QUESTAO 8
-- Nome do pais e a quantidade de cidades que ele possui.
-- COUNT() - CONTA AS CIDADES DE CADA PAIS
-- JOIN FEITO PELO CODIGO DO PAIS (Code / CountryCode)
-- ------------------------------------------------------------
 
SELECT CO.Name AS NOME_DO_PAIS,
COUNT(CI.ID) AS TOTAL_DE_CIDADES
FROM country CO
INNER JOIN city CI ON CO.Code = CI.CountryCode
GROUP BY CO.Code;
 
 
-- ------------------------------------------------------------
-- QUESTAO 9
-- Nome dos paises com mais de 10 idiomas na tabela countrylanguage.
-- COUNT()  - CONTA OS IDIOMAS POR PAIS
-- HAVING   - FILTRA APOS O AGRUPAMENTO (PAISES COM MAIS DE 10 IDIOMAS)
-- OBS: Usamos HAVING e nao WHERE pois o filtro e aplicado sobre
--      o resultado do GROUP BY, como ensinado em aula.
--      O HAVING utiliza o resultado dos dados ja agrupados.
-- ------------------------------------------------------------
 
SELECT CO.Name AS NOME_DO_PAIS,
COUNT(CL.Language) AS TOTAL_DE_IDIOMAS
FROM country CO
INNER JOIN countrylanguage CL ON CO.Code = CL.CountryCode
GROUP BY CO.Code
HAVING COUNT(CL.Language) > 10;
 
 
-- ------------------------------------------------------------
-- QUESTAO 10
-- Nome dos paises cuja cidade mais populosa tem mais de
-- 5 milhoes de habitantes.
-- MAX()   - RETORNA A MAIOR POPULACAO ENTRE AS CIDADES DO PAIS
-- HAVING  - FILTRA OS PAISES COM CIDADE ACIMA DE 5.000.000 HAB.
-- ------------------------------------------------------------
 
SELECT CO.Name AS NOME_DO_PAIS,
MAX(CI.Population) AS POPULACAO_DA_CIDADE_MAIS_POPULOSA
FROM country CO
INNER JOIN city CI ON CO.Code = CI.CountryCode
GROUP BY CO.Code
HAVING MAX(CI.Population) > 5000000;
 
 
-- ============================================================
-- FIM DA ATIVIDADE
-- ============================================================