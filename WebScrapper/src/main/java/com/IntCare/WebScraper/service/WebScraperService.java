package com.IntCare.WebScraper.service;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebScraperService {

    private static final String ANS_URL = "https://www.gov.br/ans/pt-br/acesso-a-informacao/participacao-da-sociedade/atualizacao-do-rol-de-procedimentos";
    private static final String DOWNLOAD_DIR = "downloads";
    private static final String ZIP_FILE = "downloads/anexos.zip";

    public void executarScraping() throws IOException {
        // Baixar página HTML
        Document doc = Jsoup.connect(ANS_URL).get();

        // Filtrar links dos PDFs
        Elements links = doc.select("a[href$=.pdf]");
        List<File> pdfsBaixados = new ArrayList<>();

        for (Element link : links) {
            String url = link.absUrl("href");
            if (url.contains("Anexo_I") || url.contains("Anexo_II")) { // Filtrar os anexos corretos
                File pdfFile = baixarPDF(url);
                if (pdfFile != null) {
                    pdfsBaixados.add(pdfFile);
                }
            }
        }

        // Compactar os PDFs em um único ZIP
        compactarArquivos(pdfsBaixados);
    }

    private File baixarPDF(String fileURL) throws IOException {
        String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
        Path downloadPath = Path.of(DOWNLOAD_DIR);
        Files.createDirectories(downloadPath); // Criar diretório se não existir

        File destino = new File(DOWNLOAD_DIR, fileName);
        try (InputStream in = new URL(fileURL).openStream()) {
            Files.copy(in, destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("📥 Download concluído: " + destino.getAbsolutePath());
            return destino;
        } catch (IOException e) {
            System.err.println("❌ Erro ao baixar: " + fileURL);
            return null;
        }
    }

    private void compactarArquivos(List<File> arquivos) throws IOException {
        File zipFile = new File(ZIP_FILE);
        try (ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(new FileOutputStream(zipFile))) {
            for (File arquivo : arquivos) {
                try (FileInputStream fis = new FileInputStream(arquivo)) {
                    ZipArchiveEntry entry = new ZipArchiveEntry(arquivo.getName());
                    zipOut.putArchiveEntry(entry);
                    zipOut.write(fis.readAllBytes());
                    zipOut.closeArchiveEntry();
                    System.out.println("📦 Adicionado ao ZIP: " + arquivo.getName());
                }
            }
        }
        System.out.println("✅ Arquivos compactados em: " + zipFile.getAbsolutePath());
    }
}