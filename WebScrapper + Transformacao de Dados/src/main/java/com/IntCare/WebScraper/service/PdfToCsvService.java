package com.IntCare.WebScraper.service;

import com.opencsv.CSVWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.stereotype.Service;
import technology.tabula.*;
import technology.tabula.extractors.BasicExtractionAlgorithm;
import technology.tabula.extractors.ExtractionAlgorithm;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class PdfToCsvService {

    public void convertPdfToCsv() throws IOException {
        // Caminho do PDF na pasta Downloads
        String pdfPath = "downloads/Anexo_I_Rol_2021RN_465.2021_RN627L.2024.pdf";
        String csvPath = "downloads/rol_procedimentos.csv";
        String zipPath = "downloads/Teste_Igor_Nayan.zip";

        File pdfFile = new File(pdfPath);
        if (!pdfFile.exists()) {
            throw new IOException("Arquivo PDF não encontrado: " + pdfPath);
        }

        try (PDDocument document = PDDocument.load(pdfFile);
             FileWriter fileWriter = new FileWriter(csvPath);
             CSVWriter csvWriter = new CSVWriter(fileWriter)) {

            ObjectExtractor extractor = new ObjectExtractor(document);
            PageIterator pageIterator = extractor.extract();

            while (pageIterator.hasNext()) {
                Page page = pageIterator.next();
                ExtractionAlgorithm algorithm = new SpreadsheetExtractionAlgorithm();
                List<Table> tables = new ArrayList<>(algorithm.extract(page));

                if (tables.isEmpty()) {
                    algorithm = new BasicExtractionAlgorithm(); // Se não for tabela pura, tenta extrair baseada em células
                    tables = new ArrayList<>(algorithm.extract(page));
                }

                for (Table table : tables) {
                    for (List<RectangularTextContainer> row : table.getRows()) {
                        String[] rowData = row.stream()
                                .map(cell -> replaceAbbreviation(cell.getText().trim()))
                                .toArray(String[]::new);
                        csvWriter.writeNext(rowData);
                    }
                }
            }

            System.out.println("Conversão concluída! Arquivo CSV salvo em: " + csvPath);

            // Compactar CSV em ZIP
            zipCsvFile(csvPath, zipPath);
            System.out.println("Arquivo ZIP gerado em: " + zipPath);
        }
    }

    // Substituir as abreviações no texto
    private String replaceAbbreviation(String text) {
        if (text.equals("OD")) {
            return "Seg. Odontológica";
        } else if (text.equals("AMB")) {
            return "Seg. Ambulatorial";
        }
        return text;
    }

    // Função para compactar o arquivo CSV em um ZIP
    private void zipCsvFile(String csvPath, String zipPath) throws IOException {
        try (FileInputStream fis = new FileInputStream(csvPath);
             FileOutputStream fos = new FileOutputStream(zipPath);
             ZipOutputStream zipOut = new ZipOutputStream(fos)) {

            ZipEntry zipEntry = new ZipEntry("rol_procedimentos.csv");
            zipOut.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zipOut.write(buffer, 0, length);
            }
            zipOut.closeEntry();
        }
    }
}

