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

Como Executar
Compile e execute o projeto com Maven:
mvn spring-boot:run
Os arquivos gerados estarão no diretório downloads/.
