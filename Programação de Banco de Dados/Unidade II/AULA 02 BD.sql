
-- NOMES DAS TABELAS: 

USE modeloclassico;

sHOW TABLES;

-- CARACTER

-- CONCAT() FUNÇÃO QUE FAZ A CONCATENAÇÃO - JUNÇÃO DE CARACTERES.
-- UPPER () TRANSFORMA TODOS COM LETRAS MAIUSCULAS
-- AS USAR O NOME PARA ORDER BY NOME - ORDEM ALFABETICA

SELECT * FROM CLIENTES;
SELECT NOME_DO_CLIENTE FROM CLIENTES;

SELECT CONCAT(PRIMEIRO_NOME," ",ULTIMO_NOME) FROM CLIENTES;

SELECT upper(CONCAT(PRIMEIRO_NOME," ",ULTIMO_NOME)) FROM CLIENTES;
SELECT upper(CONCAT(PRIMEIRO_NOME," ",ULTIMO_NOME)) AS NOME FROM CLIENTES ORDER BY NOME;

-- remover do inicio 5 primeiros
SELECT SUBSTR(produto_id,5) FROM produtos;
SELECT SUBSTR(produto_id,5), produto_id FROM produtos;

-- 4 primeirops

SELECT right(produto_id,4), produto_id FROM produtos;

-- 4 ultimos

SELECT left(produto_id,4), produto_id FROM produtos;

--

select * FROM PRODUTOS;

SELECT QUANTIDADE_ENCOMENDADA * PRECO_DA_UNIDADE AS TOTAL FROM DETALHES_DO_PEDIDO;

USE WORLD;
USE SAKILA_PT;

-- ROUND() - ARRENDONDAMENTO DOS DADOS
select ROUND(PRECO_DE_COMPRA),PRECO_DE_COMPRA FROM PRODUTOS;

-- TRUNCATE() - TRUNCA ELE REMOVE O VALOR NA VIRGULA.
select ROUND(PRECO_DE_COMPRA, 1),TRUNCATE(PRECO_DE_COMPRA,1) FROM PRODUTOS;

-- CEILING - MESMA COISA ARREDONDA PARA ACIMA

-- DATE TIME -- (AAAA--MM--DD) e como usar abaixo.

select * FROM PEDIDOS;
select DATE_FORMAT(DATA_DO_PEDIDO,"%d/%m/%y") AS DATA FROM PEDIDOS;
select DATE_FORMAT(DATA_DO_PEDIDO,"%D %M %Y") as data FROM PEDIDOS;


select year(DATA_DO_PEDIDO) AS DATA FROM PEDIDOS;

 -- datediff() sao a diferença da data
select DATA_DE_ENVIO, DATA_DO_PEDIDO, datediff(DATA_DE_ENVIO, DATA_DO_PEDIDO) FROM PEDIDOS;

 -- adddate() sao para adicionar mais dias depois da data.
select DATA_DO_PEDIDO, adddate(DATA_DO_PEDIDO, 5) FROM PEDIDOS;

-- usar data now() para o momento atual a hora atual do computador com horas do computador.
-- podendo assim fazer timediff para fazer calculo da diferença.


select * from produtos where linha_do_produto like "carro%";
-- like "carro%"; pesquisa com qualquer tipo de carro
-- like "carro"; apenas com carro.

-- OPERADORES = <> , > < >= <= NOT
-- NOT like INVERTE TUDO 
select * from produtos where linha_do_produto NOT like "carro%";


select * from produtos where QUANTIA_EM_ESTOQUE > 5000;
select * from produtos where QUANTIA_EM_ESTOQUE > 5000 AND QUANTIA_EM_ESTOQUE < 7000;
select * from produtos where QUANTIA_EM_ESTOQUE BETWEEN 5000 AND 7000;
-- select * from produtos where QUANTIA_EM_ESTOQUE >= 5000 AND QUANTIA_EM_ESTOQUE <= 7000

SELECT * FROM CLIENTES WHERE ESTADO IS NOT NULL;

SHOW TABLES