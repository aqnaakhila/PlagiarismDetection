
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SinonimLoader {
    public static Map<String, String> loadSinonim(String path) {
        Map<String, String> map = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("kata")) continue; // skip header

                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String a = parts[0].trim().toLowerCase();
                    String b = parts[1].trim().toLowerCase();

                    // Tambahkan dua arah
                    map.put(a, b);
                    map.put(b, a);
                }
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file sinonim: " + e.getMessage());
        }

        return map;
    }
}
