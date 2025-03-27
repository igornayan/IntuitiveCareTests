-- Criação da tabela 'demonstracoes_contabeis' para armazenar os dados extraídos dos arquivos CSV
-- Relação com o teste: Tarefa 3.3 - Estruturar a tabela necessária para os dados do CSV.
CREATE TABLE demonstracoes_contabeis (
    data DATE,                         -- Coluna para armazenar a data
    reg_ans VARCHAR(10),               -- Coluna para armazenar o código do registro da operadora (REG_ANS)
    cd_conta_contabil VARCHAR(20),     -- Coluna para armazenar o código da conta contábil
    descricao TEXT,                    -- Coluna para armazenar a descrição do evento (DESCRICAO)
    vl_saldo_inicial TEXT,             -- Coluna para armazenar o saldo inicial (VL_SALDO_INICIAL)
    vl_saldo_final TEXT                -- Coluna para armazenar o saldo final (VL_SALDO_FINAL)
);

-- Importação dos dados dos arquivos CSV para a tabela 'demonstracoes_contabeis'
-- Relação com o teste: Tarefa 3.4 - Importação dos dados CSV para a tabela
COPY demonstracoes_contabeis(data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final) 
FROM 'C:\TesteSQL\1T2023.csv' DELIMITER ';' CSV HEADER;
COPY demonstracoes_contabeis(data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final) 
FROM 'C:\TesteSQL\2T2023.csv' DELIMITER ';' CSV HEADER;
COPY demonstracoes_contabeis(data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final) 
FROM 'C:\TesteSQL\3T2023.csv' DELIMITER ';' CSV HEADER;
COPY demonstracoes_contabeis(data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final) 
FROM 'C:\TesteSQL\4T2023.csv' DELIMITER ';' CSV HEADER;
COPY demonstracoes_contabeis(data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final) 
FROM 'C:\TesteSQL\1T2024.csv' DELIMITER ';' CSV HEADER;
COPY demonstracoes_contabeis(data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final) 
FROM 'C:\TesteSQL\2T2024.csv' DELIMITER ';' CSV HEADER;
COPY demonstracoes_contabeis(data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final) 
FROM 'C:\TesteSQL\3T2024.csv' DELIMITER ';' CSV HEADER;
COPY demonstracoes_contabeis(data, reg_ans, cd_conta_contabil, descricao, vl_saldo_inicial, vl_saldo_final) 
FROM 'C:\TesteSQL\4T2024.csv' DELIMITER ';' CSV HEADER;

-- Alteração da tabela para adicionar novas colunas para armazenar valores numéricos (para saldo inicial e final)
-- Relação com o teste: Tarefa 3.4 - Adicionar e preparar as colunas necessárias para cálculos numéricos.
ALTER TABLE demonstracoes_contabeis 
ADD COLUMN vl_saldo_inicial_num NUMERIC(15,2),
ADD COLUMN vl_saldo_final_num NUMERIC(15,2);

-- Atualizar os valores das colunas 'vl_saldo_inicial' e 'vl_saldo_final' para formato numérico
-- (substituindo as vírgulas por pontos) e armazenar em 'vl_saldo_inicial_num' e 'vl_saldo_final_num'
-- Relação com o teste: Tarefa 3.4 - Garantir que os valores estejam no formato adequado para cálculo.
UPDATE demonstracoes_contabeis 
SET 
  vl_saldo_inicial_num = CAST(REPLACE(vl_saldo_inicial, ',', '.') AS NUMERIC(15,2)),
  vl_saldo_final_num = CAST(REPLACE(vl_saldo_final, ',', '.') AS NUMERIC(15,2));

-- Remover as colunas originais de saldo (com valores em texto) da tabela
-- Relação com o teste: Tarefa 3.4 - Limpeza e preparação da tabela para o cálculo final.
ALTER TABLE demonstracoes_contabeis 
DROP COLUMN vl_saldo_inicial,
DROP COLUMN vl_saldo_final;

-- Renomear as novas colunas numéricas para manter o nome original das colunas
-- Relação com o teste: Tarefa 3.4 - Organizar os dados para facilitar a consulta posterior.
ALTER TABLE demonstracoes_contabeis 
RENAME COLUMN vl_saldo_inicial_num TO vl_saldo_inicial;

ALTER TABLE demonstracoes_contabeis 
RENAME COLUMN vl_saldo_final_num TO vl_saldo_final;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Criação da tabela 'operadoras' para armazenar os dados das operadoras de planos de saúde
-- Relação com o teste: Tarefa 3.3 - Estruturar a tabela necessária para os dados do CSV (relatório de operadoras).
CREATE TABLE operadoras (
    registro_ans VARCHAR(10),           -- Código do registro da operadora (REG_ANS)
    cnpj VARCHAR(20),                  -- CNPJ da operadora (CNPJ)
    razao_social TEXT,                 -- Razão Social da operadora (RAZAO_SOCIAL)
    nome_fantasia TEXT,                -- Nome Fantasia da operadora (NOME_FANTASIA)
    modalidade TEXT,                   -- Modalidade da operadora (MODALIDADE)
    logradouro TEXT,                   -- Endereço (LOGRADOURO)
    numero TEXT,                       -- Número do endereço (NUMERO)
    complemento TEXT,                  -- Complemento do endereço (COMPLEMENTO)
    bairro TEXT,                       -- Bairro (BAIRRO)
    cidade TEXT,                       -- Cidade (CIDADE)
    uf VARCHAR(2),                     -- Estado (UF)
    cep VARCHAR(10),                   -- CEP (CEP)
    ddd VARCHAR(3),                    -- DDD (DDD)
    telefone VARCHAR(25),              -- Telefone (TELEFONE)
    fax VARCHAR(15),                   -- Fax (FAX)
    endereco_eletronico TEXT,          -- Endereço eletrônico (ENDERECO_ELETRONICO)
    representante TEXT,                -- Representante da operadora (REPRESENTANTE)
    cargo_representante TEXT,          -- Cargo do representante (CARGO_REPRESENTANTE)
    regiao_de_comercializacao TEXT,    -- Região de comercialização (REGIAO_DE_COMERCIALIZACAO)
    data_registro_ans DATE             -- Data de registro da operadora (DATA_REGISTRO_ANS)
);

-- Importação dos dados das operadoras de planos de saúde do arquivo CSV para a tabela 'operadoras'
-- Relação com o teste: Tarefa 3.4 - Importação dos dados CSV para a tabela
COPY operadoras(registro_ans, cnpj, razao_social, nome_fantasia, modalidade, logradouro, numero, complemento, bairro, cidade, uf, cep, ddd, telefone, fax, endereco_eletronico, representante, cargo_representante, regiao_de_comercializacao, data_registro_ans) 
FROM 'C:\TesteSQL\Relatorio_cadop.csv' 
DELIMITER ';' 
CSV HEADER;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Consulta para encontrar as 10 operadoras com as maiores despesas em "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MÉDICO HOSPITALAR" no último trimestre
-- Relação com o teste: Tarefa 3.5 - Consultar as operadoras com as maiores despesas no último trimestre.
SELECT o.nome_fantasia, o.razao_social, SUM(dc.vl_saldo_final) AS total_despesas
FROM demonstracoes_contabeis dc
JOIN operadoras o ON dc.reg_ans = o.registro_ans
WHERE dc.descricao = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR '
AND dc.data >= (SELECT MAX(data) - INTERVAL '3 months' FROM demonstracoes_contabeis) -- Filtra o último trimestre
GROUP BY o.nome_fantasia, o.razao_social
ORDER BY total_despesas DESC
LIMIT 10;

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Consulta para encontrar as 10 operadoras com as maiores despesas em "EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS DE ASSISTÊNCIA A SAÚDE MÉDICO HOSPITALAR" no último ano
-- Relação com o teste: Tarefa 3.5 - Consultar as operadoras com as maiores despesas no último ano.
SELECT o.nome_fantasia, o.razao_social, SUM(dc.vl_saldo_final) AS total_despesas
FROM demonstracoes_contabeis dc
JOIN operadoras o ON dc.reg_ans = o.registro_ans
WHERE dc.descricao = 'EVENTOS/ SINISTROS CONHECIDOS OU AVISADOS  DE ASSISTÊNCIA A SAÚDE MEDICO HOSPITALAR '
AND dc.data >= (SELECT MAX(data) - INTERVAL '12 months' FROM demonstracoes_contabeis) -- Filtra o último ano
GROUP BY o.nome_fantasia, o.razao_social
ORDER BY total_despesas DESC
LIMIT 10;
