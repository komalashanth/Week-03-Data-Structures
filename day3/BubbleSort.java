class BubbleSort {
    void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            if (!swapped) break;
        }
    }

    public static void main(String[] args) {
        BubbleSort sorter = new BubbleSort();
        int[] marks = {45, 78, 12, 90, 56, 34};
        sorter.bubbleSort(marks);
        System.out.println("Sorted Student Marks: " + java.util.Arrays.toString(marks));
    }
}
