class InsertionSort {
    void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        InsertionSort sorter = new InsertionSort();
        int[] employeeIDs = {1002, 1001, 1005, 1003, 1004};
        sorter.insertionSort(employeeIDs);
        System.out.println("Sorted Employee IDs: " + java.util.Arrays.toString(employeeIDs));
    }
}

