Web Scraper
Este projeto é um scraper desenvolvido em Spring Boot para baixar arquivos PDF de um site, extrair dados relevantes e salvá-los em um arquivo CSV. O CSV gerado é então compactado em um arquivo ZIP.

Funcionalidades
Baixa arquivos PDF de um site específico

Extrai texto dos PDFs

Processa os dados e gera um arquivo CSV

Compacta o CSV em um arquivo ZIP

Tecnologias
Java 17

Spring Boot

Apache PDFBox (para extração de texto)

Estrutura
css
Copiar
Editar
web-scraper/
│── src/
│   ├── main/
│   │   ├── java/com/IntCare/WebScraper/
│   │   │   ├── WebScraperApplication.java
│   │   │   ├── PdfExtractor.java
│   │   │   ├── CsvGenerator.java
│   │   │   ├── ZipCompressor.java
│   ├── resources/
│── downloads/ (diretório para arquivos gerados)
│── pom.xml
│── README.md
Como Executar
Clone o repositório:
git clone https://github.com/igornayan/IntuitiveCareTests.git
cd web-scraper
Compile e execute o projeto com Maven:
mvn spring-boot:run
Os arquivos gerados estarão no diretório downloads/.