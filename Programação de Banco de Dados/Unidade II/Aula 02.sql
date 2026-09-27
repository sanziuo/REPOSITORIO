-- use modeloclassico

select *
from clientes;

select primeiro_nome, telefone, limite_de_credito
from clientes;

select primeiro_nome, telefone, limite_de_credito
from clientes
where limite_de_credito > 50000;

select primeiro_nome, telefone, limite_de_credito
from clientes
where limite_de_credito between 50000 and 100000;

select primeiro_nome, telefone, limite_de_credito
from clientes
where limite_de_credito > 50000 
and limite_de_credito < 100000;

select primeiro_nome
from clientes
where primeiro_nome like 'A%';