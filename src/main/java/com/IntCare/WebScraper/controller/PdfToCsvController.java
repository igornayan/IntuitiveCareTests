package com.IntCare.WebScraper.controller;

import com.IntCare.WebScraper.service.PdfToCsvService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfToCsvController {

    private final PdfToCsvService pdfToCsvService;

    public PdfToCsvController(PdfToCsvService pdfToCsvService) {
        this.pdfToCsvService = pdfToCsvService;
    }

    @GetMapping("/convert")
    public ResponseEntity<String> convertPdfToCsv() {
        try {
            pdfToCsvService.convertPdfToCsv();
            return ResponseEntity.ok("Conversão concluída! O arquivo ZIP foi gerado em downloads/Teste_Igor_Nayan.zip");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao processar o PDF: " + e.getMessage());
        }
    }
}
