# Como Rodar o SQL no PostgreSQL

Este repositório contém os scripts SQL necessários para criar as tabelas e importar os dados para o banco de dados PostgreSQL.

## Pré-requisitos

Antes de rodar os scripts SQL, certifique-se de ter o PostgreSQL instalado em seu sistema. Você pode baixar o PostgreSQL em: https://www.postgresql.org/download/

Além disso, você precisará dos arquivos CSV mencionados nos scripts. Certifique-se de ter os arquivos:

- `1T2023.csv`
- `2T2023.csv`
- `3T2023.csv`
- `4T2023.csv`
- `1T2024.csv`
- `2T2024.csv`
- `3T2024.csv`
- `4T2024.csv`
- `Relatorio_cadop.csv`

Esses arquivos devem estar localizados no diretório `C:\TesteSQL\` ou altere o caminho no script conforme necessário.

## Passo 1: Conecte-se ao PostgreSQL

Abra o terminal ou o cliente de sua preferência (como pgAdmin ou psql) e conecte-se ao banco de dados PostgreSQL:

psql -U seu_usuario -d seu_banco_de_dados
Substitua seu_usuario pelo seu nome de usuário no PostgreSQL e seu_banco_de_dados pelo nome do banco de dados onde você deseja executar os scripts.

Passo 2: Execute os Scripts SQL
Copie e cole o conteúdo do arquivo SQL no terminal ou no seu cliente de banco de dados. O script irá criar as tabelas, importar os dados e realizar as transformações necessárias.

Detalhes dos Scripts:
Criação das Tabelas: As tabelas demonstracoes_contabeis e operadoras são criadas para armazenar os dados dos arquivos CSV.

Importação de Dados: Os dados dos arquivos CSV são carregados nas tabelas.

Transformação dos Dados: As colunas de saldo (inicial e final) são convertidas para formato numérico.

Consultas: Duas consultas SQL são fornecidas para obter as 10 operadoras com as maiores despesas, em um intervalo de 3 meses e 12 meses.

Passo 3: Verifique os Resultados
Após executar os scripts, você pode verificar as tabelas e os resultados das consultas diretamente no PostgreSQL.

Se você estiver utilizando o psql, você pode rodar as consultas diretamente:

SELECT * FROM demonstracoes_contabeis;
SELECT * FROM operadoras;