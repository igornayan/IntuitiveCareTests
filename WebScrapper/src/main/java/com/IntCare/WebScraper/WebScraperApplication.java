package com.IntCare.WebScraper;

import com.IntCare.WebScraper.service.PdfToCsvService;
import com.IntCare.WebScraper.service.WebScraperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class WebScraperApplication implements CommandLineRunner {

	@Autowired
	private WebScraperService webScraperService;

	@Autowired
	private PdfToCsvService pdfToCsvService;

	public static void main(String[] args) {
		SpringApplication.run(WebScraperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Chama o WebScraping
		System.out.println("✅ Iniciando a busca pelos anexos através do WebScraping...");
		try {
			webScraperService.executarScraping(); // Chamando o método de scraping
			System.out.println("✅ Web scraping concluído! Verifique o diretório.");
		} catch (IOException e) {
			System.err.println("❌ Erro ao executar scraping: " + e.getMessage());
		}

		// Chama a conversão do PDF para CSV
		System.out.println("✅ Iniciando a conversão do PDF para CSV...");
		try {
			pdfToCsvService.convertPdfToCsv(); // Chamando o método de conversão de PDF para CSV
			System.out.println("✅ Conversão concluída! O arquivo ZIP foi gerado.");
		} catch (IOException e) {
			System.err.println("❌ Erro ao processar o PDF: " + e.getMessage());
		}
	}
}
