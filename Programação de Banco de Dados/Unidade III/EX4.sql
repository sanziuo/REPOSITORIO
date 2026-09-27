
-- Sakila — Conjuntos
-- sql

USE sakila_pt;

-- 1. Lista única de e-mails de clientes E funcionários (UNION remove duplicatas)
SELECT email FROM cliente
UNION
SELECT email FROM funcionario;

-- 2. Clientes cujo nome E sobrenome aparecem também na tabela de atores (INTERSECT via INNER JOIN)
SELECT
    cl.primeiro_nome,
    cl.ultimo_nome
FROM cliente cl
INNER JOIN ator a
    ON cl.primeiro_nome = a.primeiro_nome
   AND cl.ultimo_nome   = a.ultimo_nome;

-- 3. Filmes do inventário que NUNCA saíram para aluguel (EXCEPT via LEFT JOIN)
SELECT DISTINCT
    f.titulo
FROM filme f
INNER JOIN inventario i  ON f.filme_id      = i.filme_id
LEFT JOIN  aluguel al    ON i.inventario_id  = al.inventario_id
WHERE al.aluguel_id IS NULL;

-- 4. Cidades cadastradas onde NÃO existe uma loja física (EXCEPT via LEFT JOIN)
SELECT
    ci.cidade
FROM cidade ci
LEFT JOIN endereco e  ON ci.cidade_id   = e.cidade_id
LEFT JOIN loja l      ON e.endereco_id  = l.endereco_id
WHERE l.loja_id IS NULL;


-- Classic Models — Conjuntos
-- sql

USE classicmodels;

-- 1. Catálogo global de endereços: escritórios e clientes (UNION)
SELECT
    'Escritório'      AS tipo,
    cidade,
    endereco_1        AS endereco,
    pais,
    codigo_postal
FROM escritorios
UNION
SELECT
    'Cliente'         AS tipo,
    cidade,
    endereco_1        AS endereco,
    pais,
    codigo_postal
FROM clientes;

-- 2. Clientes com o mesmo sobrenome de algum funcionário (INTERSECT via INNER JOIN)
SELECT DISTINCT
    c.ultimo_nome
FROM clientes c
INNER JOIN funcionarios f ON c.ultimo_nome = f.ultimo_nome;

-- 3. Produtos que NUNCA apareceram em um pedido (EXCEPT via LEFT JOIN)
SELECT
    p.produto_id,
    p.nome
FROM produtos p
LEFT JOIN detalhes_do_pedido dp ON p.produto_id = dp.produto_id
WHERE dp.produto_id IS NULL;

-- 4. Clientes da França que NUNCA tiveram pedido cancelado (EXCEPT via NOT IN)
SELECT
    nome_do_cliente
FROM clientes
WHERE pais = 'France'
  AND cliente_id NOT IN (
      SELECT Cliente_id
      FROM pedidos
      WHERE status = 'Cancelled'
  );


-- World — Conjuntos
-- sql

USE world;

-- 1. Nomes de todos os países e cidades em uma única coluna sem duplicatas (UNION)
SELECT Name AS nome, 'País'   AS tipo FROM country
UNION
SELECT Name AS nome, 'Cidade' AS tipo FROM city
ORDER BY nome;

-- 2. Idiomas oficiais na África que também são oficiais na Europa (INTERSECT via INNER JOIN)
SELECT DISTINCT cl_africa.Language AS idioma
FROM countrylanguage cl_africa
INNER JOIN country c_africa
    ON cl_africa.CountryCode = c_africa.Code
   AND c_africa.Continent    = 'Africa'
   AND cl_africa.IsOfficial  = 'T'
INNER JOIN countrylanguage cl_europa
    ON cl_africa.Language    = cl_europa.Language
   AND cl_europa.IsOfficial  = 'T'
INNER JOIN country c_europa
    ON cl_europa.CountryCode = c_europa.Code
   AND c_europa.Continent    = 'Europe';

-- 3. Países da América do Sul EXCETO os que têm Espanhol cadastrado (EXCEPT via NOT IN)
SELECT co.Name AS pais
FROM country co
WHERE co.Continent = 'South America'
  AND co.Code NOT IN (
      SELECT cl.CountryCode
      FROM countrylanguage cl
      WHERE cl.Language = 'Spanish'
  );
  
  