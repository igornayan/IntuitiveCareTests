from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
import pandas as pd
from fastapi.responses import JSONResponse

# Carregar o CSV
df = pd.read_csv('Relatorio_cadop.csv', sep=';', encoding='utf-8')

app = FastAPI()

# Configurar CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/search/")
async def search_operadoras(query: str):
    # Filtrar registros que contêm o texto pesquisado em qualquer coluna
    result = df[df.apply(lambda row: row.astype(str).str.contains(query, case=False).any(), axis=1)]
    
    # Substituindo NaN por uma string vazia ou algum outro valor padrão
    result = result.fillna("N/A")

    # Converter os resultados filtrados para um formato legível
    records = result.to_dict(orient="records")
    return JSONResponse(content={"results": records})
