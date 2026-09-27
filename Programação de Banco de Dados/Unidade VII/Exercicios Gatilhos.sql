-- ============================================================
-- EXERCICIOS - GATILHOS (TRIGGERS)
-- ALUNO: GABRIEL SANZIO MACEDO PORTO
-- MATRICULA: 2025101410840142
-- ============================================================


-- ============================================================
-- BANCO DE DADOS: ModeloClassico
-- ============================================================

USE ModeloClassico;

SHOW TABLES;

SELECT * FROM DETALHES_DO_PEDIDO;
SELECT * FROM PRODUTOS;


-- ------------------------------------------------------------
-- CLASSICMODELS - GATILHO 1
-- Criar um gatilho para estornar os produtos que forem
-- excluidos da tabela detalhes_do_pedido.
-- LOGICA: Quando um item e excluido de DETALHES_DO_PEDIDO,
--         a quantidade que estava reservada deve voltar
--         ao estoque do produto. Usamos AFTER DELETE pois
--         a acao ja ocorreu e precisamos apenas devolver
--         a quantidade ao estoque com UPDATE em PRODUTOS.
-- ------------------------------------------------------------

DROP TRIGGER IF EXISTS AF_DEL_DETALHE_PEDIDO;

DELIMITER $$
CREATE TRIGGER AF_DEL_DETALHE_PEDIDO
    AFTER DELETE ON DETALHES_DO_PEDIDO
    FOR EACH ROW
BEGIN
    UPDATE PRODUTOS
    SET QUANTIA_EM_ESTOQUE = QUANTIA_EM_ESTOQUE + OLD.QUANTIDADE_ENCOMENDADA
    WHERE PRODUTO_ID = OLD.PRODUTO_ID;
END $$
DELIMITER ;

-- TESTE DO GATILHO 1:
-- Verificar estoque antes
SELECT PRODUTO_ID, QUANTIA_EM_ESTOQUE FROM PRODUTOS WHERE PRODUTO_ID = 'S18_1749';
-- Excluir um item do pedido
DELETE FROM DETALHES_DO_PEDIDO WHERE PEDIDO_ID = 10100 AND PRODUTO_ID = 'S18_1749';
-- Verificar se o estoque foi restaurado
SELECT PRODUTO_ID, QUANTIA_EM_ESTOQUE FROM PRODUTOS WHERE PRODUTO_ID = 'S18_1749';


-- ------------------------------------------------------------
-- CLASSICMODELS - GATILHO 2
-- Criar um gatilho para corrigir o estoque dos produtos que
-- tiverem suas quantidades atualizadas em DETALHES_DO_PEDIDO.
-- Nao permitir valores diferentes da disponibilidade em estoque.
-- LOGICA: BEFORE UPDATE verifica se a nova quantidade excede
--         o estoque disponivel. Se exceder, usa SIGNAL para
--         lancar um erro e bloquear a operacao.
--         Se for valida, ajusta o estoque subtraindo a diferenca
--         entre o novo e o antigo valor.
-- ------------------------------------------------------------

DROP TRIGGER IF EXISTS BF_UPD_DETALHE_PEDIDO;

DELIMITER $$
CREATE TRIGGER BF_UPD_DETALHE_PEDIDO
    BEFORE UPDATE ON DETALHES_DO_PEDIDO
    FOR EACH ROW
BEGIN
    DECLARE ESTOQUE_ATUAL INT;

    -- BUSCA O ESTOQUE ATUAL DO PRODUTO
    SELECT QUANTIA_EM_ESTOQUE INTO ESTOQUE_ATUAL
    FROM PRODUTOS
    WHERE PRODUTO_ID = NEW.PRODUTO_ID;

    -- VERIFICA SE A NOVA QUANTIDADE EXCEDE O ESTOQUE DISPONIVEL
    IF NEW.QUANTIDADE_ENCOMENDADA > (ESTOQUE_ATUAL + OLD.QUANTIDADE_ENCOMENDADA) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'QUANTIDADE SOLICITADA EXCEDE O ESTOQUE DISPONIVEL.';
    ELSE
        -- AJUSTA O ESTOQUE: DEVOLVE O ANTIGO E SUBTRAI O NOVO
        UPDATE PRODUTOS
        SET QUANTIA_EM_ESTOQUE = QUANTIA_EM_ESTOQUE + OLD.QUANTIDADE_ENCOMENDADA - NEW.QUANTIDADE_ENCOMENDADA
        WHERE PRODUTO_ID = NEW.PRODUTO_ID;
    END IF;
END $$
DELIMITER ;

-- TESTE DO GATILHO 2:
SELECT PRODUTO_ID, QUANTIA_EM_ESTOQUE FROM PRODUTOS WHERE PRODUTO_ID = 'S18_1749';
-- Tentar atualizar com valor acima do estoque (deve gerar erro)
-- UPDATE DETALHES_DO_PEDIDO SET QUANTIDADE_ENCOMENDADA = 99999 WHERE PRODUTO_ID = 'S18_1749';


-- ------------------------------------------------------------
-- CLASSICMODELS - GATILHO 3
-- Criar uma tabela de auditoria para registrar as alteracoes
-- de PRECO_DE_COMPRA e PRECO_SUGERIDO da tabela PRODUTOS.
-- LOGICA: Criamos a tabela AUDIT_PRECO_PRODUTOS para guardar
--         o historico. O gatilho BEFORE UPDATE captura os
--         valores antigos (OLD) e novos (NEW) sempre que
--         um preco for alterado e registra na auditoria.
-- ------------------------------------------------------------

-- CRIANDO A TABELA DE AUDITORIA
CREATE TABLE IF NOT EXISTS AUDIT_PRECO_PRODUTOS (
    ID               INT AUTO_INCREMENT PRIMARY KEY,
    PRODUTO_ID       VARCHAR(15)    NOT NULL,
    PRECO_COMPRA_OLD DECIMAL(10,2)  NOT NULL,
    PRECO_COMPRA_NEW DECIMAL(10,2)  NOT NULL,
    PRECO_SUGERIDO_OLD DECIMAL(10,2) NOT NULL,
    PRECO_SUGERIDO_NEW DECIMAL(10,2) NOT NULL,
    DATA_ALTERACAO   DATETIME       DEFAULT NULL,
    ACAO             VARCHAR(50)    DEFAULT 'UPDATE'
);

SELECT * FROM AUDIT_PRECO_PRODUTOS;

DROP TRIGGER IF EXISTS BF_UPD_PRECO_PRODUTO;

DELIMITER $$
CREATE TRIGGER BF_UPD_PRECO_PRODUTO
    BEFORE UPDATE ON PRODUTOS
    FOR EACH ROW
BEGIN
    -- REGISTRA NA AUDITORIA SEMPRE QUE PRECO FOR ALTERADO
    IF OLD.PRECO_DE_COMPRA <> NEW.PRECO_DE_COMPRA
    OR OLD.PRECO_SUGERIDO  <> NEW.PRECO_SUGERIDO THEN
        INSERT INTO AUDIT_PRECO_PRODUTOS
        VALUES (
            NULL,
            OLD.PRODUTO_ID,
            OLD.PRECO_DE_COMPRA,
            NEW.PRECO_DE_COMPRA,
            OLD.PRECO_SUGERIDO,
            NEW.PRECO_SUGERIDO,
            NOW(),
            'UPDATE'
        );
    END IF;
END $$
DELIMITER ;

-- TESTE DO GATILHO 3:
SELECT PRODUTO_ID, PRECO_DE_COMPRA, PRECO_SUGERIDO FROM PRODUTOS WHERE PRODUTO_ID = 'S10_1678';
UPDATE PRODUTOS SET PRECO_SUGERIDO = 999.99 WHERE PRODUTO_ID = 'S10_1678';
SELECT * FROM AUDIT_PRECO_PRODUTOS;


-- ============================================================
-- BANCO DE DADOS: Sakila_pt
-- ============================================================

USE Sakila_pt;

SHOW TABLES;

SELECT * FROM ALUGUEL;
SELECT * FROM CLIENTE;
SELECT * FROM PAGAMENTO;
SELECT * FROM INVENTARIO;
SELECT * FROM FILME;


-- ------------------------------------------------------------
-- SAKILA - GATILHO 1
-- Gatilho que nao deixa alugar um filme se ele nao estiver
-- devolvido (DATA_DE_DEVOLUCAO IS NULL).
-- LOGICA: BEFORE INSERT em ALUGUEL verifica se o INVENTARIO_ID
--         ja possui um aluguel em aberto (sem devolucao).
--         Se sim, usa SIGNAL para bloquear e exibir mensagem.
-- ------------------------------------------------------------

DROP TRIGGER IF EXISTS BF_INS_ALUGUEL_DISPONIVEL;

DELIMITER $$
CREATE TRIGGER BF_INS_ALUGUEL_DISPONIVEL
    BEFORE INSERT ON ALUGUEL
    FOR EACH ROW
BEGIN
    DECLARE TOTAL_ABERTO INT;

    -- VERIFICA SE O ITEM DO INVENTARIO JA ESTA ALUGADO
    SELECT COUNT(*) INTO TOTAL_ABERTO
    FROM ALUGUEL
    WHERE INVENTARIO_ID = NEW.INVENTARIO_ID
    AND DATA_DE_DEVOLUCAO IS NULL;

    IF TOTAL_ABERTO > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'FILME NAO DISPONIVEL: O ITEM DO INVENTARIO ESTA ALUGADO E AINDA NAO FOI DEVOLVIDO.';
    END IF;
END $$
DELIMITER ;


-- ------------------------------------------------------------
-- SAKILA - GATILHO 2
-- Aprimorar o gatilho anterior para tambem verificar se
-- o cliente esta ativo (ATIVO = 1).
-- LOGICA: Alem de checar a devolucao, agora o gatilho tambem
--         verifica se o CLIENTE_ID possui ATIVO = 1.
--         Caso inativo, bloqueia com mensagem especifica.
-- ------------------------------------------------------------

DROP TRIGGER IF EXISTS BF_INS_ALUGUEL_DISPONIVEL;

DELIMITER $$
CREATE TRIGGER BF_INS_ALUGUEL_DISPONIVEL
    BEFORE INSERT ON ALUGUEL
    FOR EACH ROW
BEGIN
    DECLARE TOTAL_ABERTO INT;
    DECLARE CLIENTE_ATIVO TINYINT;

    -- VERIFICA SE O ITEM DO INVENTARIO JA ESTA ALUGADO
    SELECT COUNT(*) INTO TOTAL_ABERTO
    FROM ALUGUEL
    WHERE INVENTARIO_ID = NEW.INVENTARIO_ID
    AND DATA_DE_DEVOLUCAO IS NULL;

    -- VERIFICA SE O CLIENTE ESTA ATIVO
    SELECT ATIVO INTO CLIENTE_ATIVO
    FROM CLIENTE
    WHERE CLIENTE_ID = NEW.CLIENTE_ID;

    IF TOTAL_ABERTO > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'FILME NAO DISPONIVEL: O ITEM DO INVENTARIO ESTA ALUGADO E AINDA NAO FOI DEVOLVIDO.';
    END IF;

    IF CLIENTE_ATIVO = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'CLIENTE INATIVO: NAO E POSSIVEL REALIZAR ALUGUEL PARA UM CLIENTE INATIVO.';
    END IF;
END $$
DELIMITER ;


-- ------------------------------------------------------------
-- SAKILA - GATILHO 3
-- Gatilho para verificar se o valor de um pagamento a ser
-- cadastrado e menor que o preco de aluguel do filme.
-- LOGICA: BEFORE INSERT em PAGAMENTO busca o PRECO_DA_LOCACAO
--         do filme atraves do caminho:
--         PAGAMENTO -> ALUGUEL -> INVENTARIO -> FILME.
--         Se o valor do pagamento for menor que o preco
--         da locacao, o INSERT e bloqueado com SIGNAL.
-- ------------------------------------------------------------

DROP TRIGGER IF EXISTS BF_INS_PAGAMENTO_VALOR;

DELIMITER $$
CREATE TRIGGER BF_INS_PAGAMENTO_VALOR
    BEFORE INSERT ON PAGAMENTO
    FOR EACH ROW
BEGIN
    DECLARE PRECO_LOCACAO DECIMAL(4,2);

    -- BUSCA O PRECO DA LOCACAO DO FILME PELO CAMINHO:
    -- PAGAMENTO -> ALUGUEL -> INVENTARIO -> FILME
    SELECT F.PRECO_DA_LOCACAO INTO PRECO_LOCACAO
    FROM ALUGUEL A
    INNER JOIN INVENTARIO I ON A.INVENTARIO_ID = I.INVENTARIO_ID
    INNER JOIN FILME F ON I.FILME_ID = F.FILME_ID
    WHERE A.ALUGUEL_ID = NEW.ALUGUEL_ID;

    IF NEW.VALOR < PRECO_LOCACAO THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'VALOR DO PAGAMENTO INVALIDO: O VALOR INFORMADO E MENOR QUE O PRECO DE ALUGUEL DO FILME.';
    END IF;
END $$
DELIMITER ;

-- TESTE DO GATILHO 3:
-- Verificar o preco de locacao de um filme
SELECT F.TITULO, F.PRECO_DA_LOCACAO FROM FILME F
INNER JOIN INVENTARIO I ON F.FILME_ID = I.FILME_ID
INNER JOIN ALUGUEL A ON I.INVENTARIO_ID = A.INVENTARIO_ID
WHERE A.ALUGUEL_ID = 1;


-- ============================================================
-- FIM DO EXERCICIO
-- ============================================================