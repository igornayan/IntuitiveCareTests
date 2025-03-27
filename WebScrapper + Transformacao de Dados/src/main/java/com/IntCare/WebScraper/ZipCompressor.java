package com.IntCare.WebScraper;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ZipCompressor {
    public static void compactarCSV(String caminhoCSV, String nomeZip) throws IOException {
        File arquivoZip = new File(nomeZip);
        try (ZipArchiveOutputStream zipOut = new ZipArchiveOutputStream(new FileOutputStream(arquivoZip))) {
            File csvFile = new File(caminhoCSV);
            try (FileInputStream fis = new FileInputStream(csvFile)) {
                ZipArchiveEntry entry = new ZipArchiveEntry(csvFile.getName());
                zipOut.putArchiveEntry(entry);
                zipOut.write(fis.readAllBytes());
                zipOut.closeArchiveEntry();
            }
        }
        System.out.println("✅ ZIP criado: " + nomeZip);
    }
}
