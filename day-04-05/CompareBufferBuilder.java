public class CompareBufferBuilder {
    public static void main(String[] args) {
        int count = 1000000;

        long startBuffer = System.nanoTime();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            stringBuffer.append("hello");
        }
        long endBuffer = System.nanoTime();
        long timeBuffer = endBuffer - startBuffer;

        long startBuilder = System.nanoTime();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append("hello");
        }
        long endBuilder = System.nanoTime();
        long timeBuilder = endBuilder - startBuilder;

        System.out.println("StringBuffer time: " + timeBuffer + " ns");
        System.out.println("StringBuilder time: " + timeBuilder + " ns");
    }
}
