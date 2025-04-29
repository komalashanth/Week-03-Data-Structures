import java.util.*;

class StackSorter {
    void sort(Stack<Integer> s) {
        if (!s.isEmpty()) {
            int x = s.pop();
            sort(s);
            insertSorted(s, x);
        }
    }

    void insertSorted(Stack<Integer> s, int x) {
        if (s.isEmpty() || s.peek() <= x) {
            s.push(x);
        } else {
            int temp = s.pop();
            insertSorted(s, x);
            s.push(temp);
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(3);
        stack.push(8);
        stack.push(1);

        System.out.println("Original Stack: " + stack);
        StackSorter sorter = new StackSorter();
        sorter.sort(stack);
        System.out.println("Sorted Stack: " + stack);
    }
}


