public class StringConcatPerformance {

    public static void testWithString(int n) {
        long start = System.nanoTime();
        String result = "";
        for (int i = 0; i < n; i++) {
            result += "a";
        }
        long end = System.nanoTime();
        System.out.printf("String: %.2f ms\n", (end - start) / 1e6);
    }

    public static void testWithStringBuilder(int n) {
        long start = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        long end = System.nanoTime();
        System.out.printf("StringBuilder: %.2f ms\n", (end - start) / 1e6);
    }

    public static void testWithStringBuffer(int n) {
        long start = System.nanoTime();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        long end = System.nanoTime();
        System.out.printf("StringBuffer: %.2f ms\n", (end - start) / 1e6);
    }

    public static void runTest(int n) {
        System.out.println("Concatenation Count: " + n);
        if (n <= 10000) {
            testWithString(n);
        } else {
            System.out.println("String: Skipped (too slow)");
        }
        testWithStringBuilder(n);
        testWithStringBuffer(n);
        System.out.println("-------------------------------");
    }

    public static void main(String[] args) {
        runTest(1000);
        runTest(10000);
        runTest(1000000);
    }
}

