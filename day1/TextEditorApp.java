import java.util.*;

class State {
    String text;
    State prev, next;

    State(String text) {
        this.text = text;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    State head = null, tail = null, current = null;
    int size = 0, maxSize = 10;

    void addState(String newText) {
        State newState = new State(newText);
        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
            tail = current;
        }
        if (tail != null) {
            tail.next = newState;
            newState.prev = tail;
        } else {
            head = newState;
        }
        tail = newState;
        current = newState;
        size++;
        if (size > maxSize) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    void redo() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.text);
        } else {
            System.out.println("Editor is empty.");
        }
    }
}

public class TextEditorApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TextEditor editor = new TextEditor();
        while (true) {
            System.out.println("1. Type/Add Text\n2. Undo\n3. Redo\n4. Show Current State\n5. Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter Text: ");
                    editor.addState(sc.nextLine());
                    break;
                case 2:
                    editor.undo();
                    break;
                case 3:
                    editor.redo();
                    break;
                case 4:
                    editor.displayCurrentState();
                    break;
                case 5:
                    return;
            }
        }
    }
}

