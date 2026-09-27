DELIMITER $$
CREATE PROCEDURE consulta_preco ( IN id smallint)
BEGIN
SELECT preco_da_locacao AS preço
from filme
WHERE filme_id = id;
END $$
DELIMITER ;