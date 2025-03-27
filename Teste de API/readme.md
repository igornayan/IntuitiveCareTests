Projeto FastAPI + Vue.js

Este projeto consiste em uma API desenvolvida com FastAPI para realizar buscas em um arquivo CSV e um frontend em Vue.js para interagir com a API.

Estrutura do Projeto

/ projeto
  ├── backend/       # FastAPI
  │   ├── main.py    # Código principal da API
  │   ├── Relatorio_cadop.csv  # Arquivo CSV com os dados
  ├── frontend/      # Vue.js
  │   ├── src/       # Código-fonte do frontend
  │   ├── public/    # Arquivos públicos
  │   ├── package.json # Dependências do Vue.js
  ├── README.md      # Documentação

Como executar o projeto

1. Configurar e rodar o backend (FastAPI)

Instalar dependências

Antes de rodar a API, instale o Python 3.9+ e os pacotes necessários:

cd backend
pip install fastapi uvicorn pandas

Rodar o servidor

uvicorn main:app --reload

A API estará rodando em http://127.0.0.1:8000

Você pode testar os endpoints em: 👉 http://127.0.0.1:8000/docs

2. Configurar e rodar o frontend (Vue.js)

Instalar Node.js e Vue CLI

Se ainda não tiver o Node.js instalado, baixe em: https://nodejs.org/

Instale o Vue CLI (caso ainda não tenha):

npm install -g @vue/cli

Instalar dependências

cd frontend
npm install

Rodar o servidor Vue.js

npm run dev

O frontend estará disponível em http://localhost:5173

Testando a integração

1- Inicie o backend com uvicorn main:app --reload 2- Inicie o frontend com npm run dev 3- Acesse http://localhost:5173 e faça buscas para testar a API!

Tecnologias utilizadas

✅ FastAPI - Backend Python ✅ Vue.js - Frontend ✅ Pandas - Manipulação de CSV ✅ CORS Middleware - Comunicação entre front e back ✅ Uvicorn - Servidor ASGI para FastAPI