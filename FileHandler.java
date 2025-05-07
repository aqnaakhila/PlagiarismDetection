package tugas_plagiarism;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public static void simpanKeCSV(String namaFile, String kalimat1, String kalimat2) {
        try (FileWriter writer = new FileWriter(namaFile, true)) {
            writer.append("\"").append(kalimat1).append("\",");
            writer.append("\"").append(kalimat2).append("\"\n");
        } catch (IOException e) {
            System.out.println("Gagal menyimpan ke CSV: " + e.getMessage());
        }
    }
}

