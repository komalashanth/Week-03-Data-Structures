import java.io.*;

public class PerformanceComparison {
    public static void main(String[] args) throws Exception {
       
        StringBuilder sb = new StringBuilder();
        long startSB = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            sb.append("hello");
        }
        long endSB = System.currentTimeMillis();
        System.out.println("StringBuilder time: " + (endSB - startSB) + " ms");

        StringBuffer sbuf = new StringBuffer();
        long startSBuf = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            sbuf.append("hello");
        }
        long endSBuf = System.currentTimeMillis();
        System.out.println("StringBuffer time: " + (endSBuf - startSBuf) + " ms");

        long startFR = System.currentTimeMillis();
        int wordCountFR = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("largefile.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordCountFR += line.split("\\s+").length;
            }
        }
        long endFR = System.currentTimeMillis();
        System.out.println("FileReader word count: " + wordCountFR);
        System.out.println("FileReader time: " + (endFR - startFR) + " ms");

        long startISR = System.currentTimeMillis();
        int wordCountISR = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("largefile.txt"), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                wordCountISR += line.split("\\s+").length;
            }
        }
        long endISR = System.currentTimeMillis();
        System.out.println("InputStreamReader word count: " + wordCountISR);
        System.out.println("InputStreamReader time: " + (endISR - startISR) + " ms");
    }
}

