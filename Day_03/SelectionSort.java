class SelectionSort {
    void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        SelectionSort sorter = new SelectionSort();
        int[] examScores = {85, 70, 90, 60, 80};
        sorter.selectionSort(examScores);
        System.out.println("Sorted Exam Scores: " + java.util.Arrays.toString(examScores));
    }
}

