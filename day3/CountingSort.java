class CountingSort {
    void countingSort(int[] arr) {
        int max = 18; 
        int min = 10; 
        int range = max - min + 1;
        
        int[] count = new int[range];
        for (int num : arr) {
            count[num - min]++;
        }
        
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                arr[index++] = i + min;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        CountingSort sorter = new CountingSort();
        int[] studentAges = {15, 10, 18, 12, 13, 15, 17, 16};
        sorter.countingSort(studentAges);
        System.out.println("Sorted Student Ages: " + java.util.Arrays.toString(studentAges));
    }
}

