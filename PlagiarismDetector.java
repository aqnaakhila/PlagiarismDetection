import java.util.*;
import java.util.regex.*;

public class PlagiarismDetector implements Detector {
    private Map<String, String> sinonimMap;

    public PlagiarismDetector(Map<String, String> sinonimMap) {
        this.sinonimMap = sinonimMap;
    }

    @Override
    public String compareStructure(String kalimatSumber, String kalimatDuplikat) {
        String patternRegex = buatRegexDariKalimat(kalimatSumber, sinonimMap);

        Pattern pattern = Pattern.compile(patternRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(kalimatDuplikat);

        if (matcher.matches()) {
            Map<String, String> kataDiganti = cariPenggantianKata(kalimatSumber, kalimatDuplikat);
            StringBuilder laporan = new StringBuilder("Struktur mirip | Kata diganti: ");
            for (Map.Entry<String, String> entry : kataDiganti.entrySet()) {
                laporan.append(entry.getKey()).append(" -> ").append(entry.getValue()).append(" ; ");
            }
            return laporan.toString();
        } else {
            return "Struktur berbeda | Tidak terdeteksi sebagai duplikasi ringan";
        }
    }

    private String buatRegexDariKalimat(String kalimat, Map<String, String> sinonimMap) {
        StringBuilder patternBuilder = new StringBuilder();
        String[] kataArray = kalimat.split("\\s+");
        for (int i = 0; i < kataArray.length; i++) {
            String kata = kataArray[i].toLowerCase();
            if (sinonimMap.containsKey(kata)) {
                String sinonim = sinonimMap.get(kata);
                patternBuilder.append("(").append(kata).append("|").append(sinonim).append(")");
            } else {
                patternBuilder.append(kata);
            }
            if (i < kataArray.length - 1) {
                patternBuilder.append("\\s+");
            }
        }
        return patternBuilder.toString();
    }

    private Map<String, String> cariPenggantianKata(String kalimat1, String kalimat2) {
        String[] kata1 = kalimat1.split("\\s+");
        String[] kata2 = kalimat2.split("\\s+");
        Map<String, String> diganti = new HashMap<>();

        for (int i = 0; i < Math.min(kata1.length, kata2.length); i++) {
            String k1 = kata1[i].toLowerCase();
            String k2 = kata2[i].toLowerCase();
            if (!k1.equals(k2) && isSinonim(k1, k2)) {
                diganti.put(k1, k2);
            }
        }
        return diganti;
    }

    private boolean isSinonim(String a, String b) {
        return (sinonimMap.containsKey(a) && sinonimMap.get(a).equalsIgnoreCase(b)) ||
               (sinonimMap.containsKey(b) && sinonimMap.get(b).equalsIgnoreCase(a));
    }
}
