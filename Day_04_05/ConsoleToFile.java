import java.io.*;

public class ConsoleToFile {
    public static void main(String[] args) {
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            FileWriter fw = new FileWriter("output.txt");
            String input;

            System.out.println("Enter text (type 'exit' to quit):");

            while (true) {
                input = br.readLine();
                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }
                fw.write(input + "\n");
            }

            fw.close();
            br.close();
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

