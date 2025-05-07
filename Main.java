package tugas_plagiarism;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Masukkan kalimat sumber:");
        String kalimatSumber = input.nextLine();
        System.out.println("Masukkan kalimat salinan:");
        String kalimatSalinan = input.nextLine();

        // Simpan ke CSV
        FileHandler.simpanKeCSV("dataset.csv", kalimatSumber, kalimatSalinan);

        // Muat sinonim
        Map<String, String> sinonim = SinonimLoader.loadSinonim("sinonim.csv");

        // Cek plagiarisme
        PlagiarismDetector pendeteksi = new PlagiarismDetector(sinonim);
        String laporan = pendeteksi.bandingkanStruktur(kalimatSumber, kalimatSalinan);

        System.out.println(laporan);
        input.close();
    }
}
