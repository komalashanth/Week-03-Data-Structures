import java.util.*;

public class SearchComparison1 {

    
    public static boolean arraySearch(int[] arr, int key) {
        for (int val : arr) {
            if (val == key) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] datasetSizes = {1_000, 100_000, 1_000_000};
        Random random = new Random();

        for (int size : datasetSizes) {
            System.out.println("Dataset Size: " + size);

            int[] array = new int[size];
            HashSet<Integer> hashSet = new HashSet<>();
            TreeSet<Integer> treeSet = new TreeSet<>();

            for (int i = 0; i < size; i++) {
                int val = i; 
                array[i] = val;
                hashSet.add(val);
                treeSet.add(val);
            }

            int keyToSearch = size - 1; 

            long startArray = System.nanoTime();
            arraySearch(array, keyToSearch);
            long endArray = System.nanoTime();
            System.out.println("Array Search Time: " + (endArray - startArray) / 1_000_000.0 + " ms");

            long startHashSet = System.nanoTime();
            hashSet.contains(keyToSearch);
            long endHashSet = System.nanoTime();
            System.out.println("HashSet Search Time: " + (endHashSet - startHashSet) / 1_000_000.0 + " ms");

            long startTreeSet = System.nanoTime();
            treeSet.contains(keyToSearch);
            long endTreeSet = System.nanoTime();
            System.out.println("TreeSet Search Time: " + (endTreeSet - startTreeSet) / 1_000_000.0 + " ms");

            System.out.println("-----------------------------------");
        }
    }
}

