import java.io.*;

public class FileReadComparison {

    public static void readWithFileReader(String filePath) throws IOException {
        long startTime = System.currentTimeMillis();
        FileReader reader = new FileReader(filePath);
        while (reader.read() != -1) {
            // Reading character by character
        }
        reader.close();
        long endTime = System.currentTimeMillis();
        System.out.println("FileReader Time: " + (endTime - startTime) + " ms");
    }

    public static void readWithInputStreamReader(String filePath) throws IOException {
        long startTime = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(filePath);
        InputStreamReader reader = new InputStreamReader(fis);
        while (reader.read() != -1) {
            // Reading character by character (but via bytes)
        }
        reader.close();
        fis.close();
        long endTime = System.currentTimeMillis();
        System.out.println("InputStreamReader Time: " + (endTime - startTime) + " ms");
    }

    public static void main(String[] args) {
        String filePath = "path_to_your_large_file.txt"; // replace with actual file path

        try {
            System.out.println("Reading using FileReader:");
            readWithFileReader(filePath);

            System.out.println("Reading using InputStreamReader:");
            readWithInputStreamReader(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

