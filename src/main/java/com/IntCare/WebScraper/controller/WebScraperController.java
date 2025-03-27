package com.IntCare.WebScraper.controller;

import com.IntCare.WebScraper.service.WebScraperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/scraper")
public class WebScraperController {

    private final WebScraperService webScraperService;

    public WebScraperController(WebScraperService webScraperService) {
        this.webScraperService = webScraperService;
    }

    @GetMapping("/executar")
    public String executarScraping() {
        try {
            webScraperService.executarScraping();
            return "✅ Web scraping concluído! Verifique o diretório.";
        } catch (IOException e) {
            return "❌ Erro ao executar scraping: " + e.getMessage();
        }
    }
}