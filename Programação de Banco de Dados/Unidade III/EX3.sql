
-- EX 1 
-- Classic Models — Junções
use modeloclassico;
-- sql
show tables;

-- 1. Primeiro nome do cliente e nome do funcionário que o atende
SELECT
    c.nome_do_cliente,
    CONCAT(f.primeiro_nome, ' ', f.ultimo_nome) AS funcionario
FROM clientes c
INNER JOIN funcionarios f ON c.funcionario_id = f.funcionario_id;

-- 2. Nome do funcionário e nome do seu superior
SELECT
    CONCAT(f.primeiro_nome, ' ', f.ultimo_nome)  AS funcionario,
    CONCAT(s.primeiro_nome, ' ', s.ultimo_nome)  AS superior
FROM funcionarios f
INNER JOIN funcionarios s ON f.reportar_para = s.funcionario_id;

-- 3. Nome do funcionário e cidade do escritório onde trabalha
SELECT
    CONCAT(f.primeiro_nome, ' ', f.ultimo_nome) AS funcionario,
    e.cidade
FROM funcionarios f
INNER JOIN escritorios e ON f.escritorio_id = e.escritorio_id;

-- 4. Nome do cliente, número e status do pedido
SELECT
    c.nome_do_cliente,
    p.pedido_id,
    p.status
FROM clientes c
INNER JOIN pedidos p ON c.cliente_id = p.Cliente_id;

-- 5. Nome do cliente, data do pagamento e montante pago
SELECT
    c.nome_do_cliente,
    pg.data_do_pagamento,
    pg.montante_pago
FROM clientes c
INNER JOIN pagamentos pg ON c.cliente_id = pg.cliente_id;

-- 6. Nome do produto e valor total da venda (quantidade * preço)
SELECT
    p.nome,
    SUM(dp.quantidade_encomendada * dp.preco_da_unidade) AS valor_total_venda
FROM produtos p
INNER JOIN detalhes_do_pedido dp ON p.produto_id = dp.produto_id
GROUP BY p.nome;

-- Sakila — Junções
-- sql

USE SAKILA_PT
-- 1. Título do filme e nome da categoria
SELECT
    f.titulo,
    cat.nome AS categoria      -- alias 'cat' mais semântico e sem conflito
FROM filme f
INNER JOIN filme_categoria fc  ON f.filme_id      = fc.filme_id
INNER JOIN categoria cat       ON fc.categoria_id  = cat.categoria_id
ORDER BY cat.nome, f.titulo;

-- 2. Primeiro nome dos clientes ativos e seus respectivos endereços
SELECT
    cl.primeiro_nome,
    e.endereco
FROM cliente cl
INNER JOIN endereco e ON cl.endereco_id = e.endereco_id
WHERE cl.ativo = 1;

-- 3. Nome e cidade dos clientes residentes no Brazil
SELECT
    cl.primeiro_nome,
    cl.ultimo_nome,
    ci.cidade
FROM cliente cl
INNER JOIN endereco e  ON cl.endereco_id  = e.endereco_id
INNER JOIN cidade ci   ON e.cidade_id     = ci.cidade_id
INNER JOIN pais pa     ON ci.pais_id      = pa.pais_id
WHERE pa.pais = 'Brazil';

-- 4. Título do filme e primeiro/último nome dos atores, ordenado pelo título
SELECT
    f.titulo,
    a.primeiro_nome,
    a.ultimo_nome
FROM filme f
INNER JOIN filme_ator fa ON f.filme_id  = fa.filme_id
INNER JOIN ator a        ON fa.ator_id  = a.ator_id
ORDER BY f.titulo;

-- 5. Títulos dos filmes com participação de uma atriz específica (ex: SCARLETT)
SELECT
    f.titulo
FROM filme f
INNER JOIN filme_ator fa ON f.filme_id = fa.filme_id
INNER JOIN ator a        ON fa.ator_id = a.ator_id
WHERE a.primeiro_nome LIKE '%SCARLETT%';

-- 6. Cidades do Brasil cadastradas no banco de dados
SELECT
    ci.cidade
FROM cidade ci
INNER JOIN pais pa ON ci.pais_id = pa.pais_id
WHERE pa.pais = 'Brazil';

-- 7. Atores que trabalharam no filme 'ADAPTATION HOLES'
SELECT
    a.primeiro_nome,
    a.ultimo_nome
FROM ator a
INNER JOIN filme_ator fa ON a.ator_id  = fa.ator_id
INNER JOIN filme f        ON fa.filme_id = f.filme_id
WHERE f.titulo = 'ADAPTATION HOLES';


-- World — Junções
SELECT
    ci.Name  AS cidade,
    co.Name  AS pais
FROM city ci
INNER JOIN country co ON ci.CountryCode = co.Code
ORDER BY co.Name, ci.Name;

-- 2. Nome do país e sua língua oficial
SELECT
    co.Name        AS pais,
    cl.Language    AS lingua_oficial
FROM country co
INNER JOIN countrylanguage cl ON co.Code = cl.CountryCode
WHERE cl.IsOfficial = 'T'
ORDER BY co.Name;

