use modeloclassico;


-- 1. Escritórios classificados por país e cidade
SELECT *
FROM escritorios
ORDER BY pais, cidade;

-- 2. Produtos cuja linha contém a palavra 'Carro'
SELECT *
FROM produtos
WHERE linha_do_produto LIKE '%Carro%';

-- 3. Pagamentos acima de $100.000
SELECT *
FROM pagamentos
WHERE montante_pago > 100000;

-- 4. Diferença entre preço de venda e preço de compra
SELECT nome, preco_sugerido AS preco_venda, preco_de_compra, (preco_sugerido - preco_de_compra) AS diferenca_preco
FROM produtos;

-- 5. Nome e cidade dos clientes sem representante de vendas
SELECT nome_do_cliente, cidade
FROM clientes
WHERE funcionario_id IS NULL;

-- 6. Funcionários que atuam como VP ou Gerente
SELECT primeiro_nome, ultimo_nome, atuacao
FROM funcionarios
WHERE atuacao LIKE '%VP%'
   OR atuacao LIKE '%Gerente%'
   OR atuacao LIKE '%Manager%';

-- 7. Pedidos com status cancelado
SELECT *
FROM pedidos
WHERE status = 'Cancelled';



use sakila_pt;

-- 1. Título, descrição e preço de locação, ordenados por descrição
SELECT titulo, descricao, preco_da_locacao
FROM filme
ORDER BY descricao;

-- 2. Título e duração dos filmes do maior para o menor
SELECT titulo, duracao_do_filme
FROM filme
ORDER BY duracao_do_filme DESC;

-- 3. Filmes com duração menor que 60 minutos
SELECT *
FROM filme
WHERE duracao_do_filme < 60;

-- 4. Clientes inativos
SELECT *
FROM cliente
WHERE ativo = 0;

-- 5. Primeiro nome e último nome dos clientes
SELECT primeiro_nome, ultimo_nome
FROM cliente;

-- 6. Cidades cujo nome inicia com 'C'
SELECT cidade
FROM cidade
WHERE cidade LIKE 'C%';

-- 7. Atores com primeiro nome 'PENELOPE'
SELECT *
FROM ator
WHERE primeiro_nome = 'PENELOPE';

-- 8. Atores com último nome 'KILMER'
SELECT *
FROM ator
WHERE ultimo_nome = 'KILMER';

-- 9. Atores cujo último nome contém 'GEN'
SELECT *
FROM ator
WHERE ultimo_nome LIKE '%GEN%';

-- 10. Atores cujo último nome contém 'LI', ordenados pelo primeiro nome
SELECT *
FROM ator
WHERE ultimo_nome LIKE '%LI%'
ORDER BY primeiro_nome;

-- 11. Filmes cujo nome contém a palavra 'DEVIL'
SELECT *
FROM filme
WHERE titulo LIKE '%DEVIL%';

-- 12. Filmes com duração entre 60 e 80 minutos
SELECT titulo, duracao_do_filme
FROM filme
WHERE duracao_do_filme BETWEEN 60 AND 80;

-- 13. Filmes com preço de locação abaixo de $1, ordenados pelo título
SELECT *
FROM filme
WHERE preco_da_locacao < 1
ORDER BY titulo;

-- 14. Filmes com custo de substituição > $20 e preço de locação < $3
SELECT *
FROM filme
WHERE custo_de_substituicao > 20
  AND preco_da_locacao < 3
ORDER BY custo_de_substituicao, preco_da_locacao;