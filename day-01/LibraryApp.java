import java.util.*;

class Book {
    String title, author, genre, status;
    int id;
    Book prev, next;

    Book(String title, String author, String genre, int id, String status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.id = id;
        this.status = status;
        this.prev = null;
        this.next = null;
    }
}

class Library {
    Book head = null, tail = null;

    void addAtBeginning(String title, String author, String genre, int id, String status) {
        Book newBook = new Book(title, author, genre, id, status);
        if (head == null) {
            head = tail = newBook;
        } else {
            newBook.next = head;
            head.prev = newBook;
            head = newBook;
        }
    }

    void addAtEnd(String title, String author, String genre, int id, String status) {
        Book newBook = new Book(title, author, genre, id, status);
        if (head == null) {
            head = tail = newBook;
        } else {
            tail.next = newBook;
            newBook.prev = tail;
            tail = newBook;
        }
    }

    void addAtPosition(String title, String author, String genre, int id, String status, int pos) {
        if (pos <= 1) {
            addAtBeginning(title, author, genre, id, status);
            return;
        }
        Book temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++)
            temp = temp.next;
        if (temp == null || temp.next == null) {
            addAtEnd(title, author, genre, id, status);
            return;
        }
        Book newBook = new Book(title, author, genre, id, status);
        newBook.next = temp.next;
        newBook.prev = temp;
        temp.next.prev = newBook;
        temp.next = newBook;
    }

    void removeById(int id) {
        Book temp = head;
        while (temp != null && temp.id != id)
            temp = temp.next;
        if (temp == null)
            return;
        if (temp == head) {
            head = head.next;
            if (head != null) head.prev = null;
        } else if (temp == tail) {
            tail = tail.prev;
            if (tail != null) tail.next = null;
        } else {
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }
    }

    void searchByTitle(String title) {
        Book temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title))
                printBook(temp);
            temp = temp.next;
        }
    }

    void searchByAuthor(String author) {
        Book temp = head;
        while (temp != null) {
            if (temp.author.equalsIgnoreCase(author))
                printBook(temp);
            temp = temp.next;
        }
    }

    void updateStatus(int id, String status) {
        Book temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.status = status;
                return;
            }
            temp = temp.next;
        }
    }

    void displayForward() {
        Book temp = head;
        while (temp != null) {
            printBook(temp);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Book temp = tail;
        while (temp != null) {
            printBook(temp);
            temp = temp.prev;
        }
    }

    void countBooks() {
        int count = 0;
        Book temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        System.out.println("Total Books: " + count);
    }

    void printBook(Book b) {
        System.out.println("Title: " + b.title + ", Author: " + b.author + ", Genre: " + b.genre + ", ID: " + b.id + ", Status: " + b.status);
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library lib = new Library();
        while (true) {
            System.out.println("1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID\n5. Search by Title\n6. Search by Author\n7. Update Status\n8. Display Forward\n9. Display Reverse\n10. Count Books\n11. Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter Title, Author, Genre, ID, Status: ");
                    lib.addAtBeginning(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.next());
                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("Enter Title, Author, Genre, ID, Status: ");
                    lib.addAtEnd(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.next());
                    sc.nextLine();
                    break;
                case 3:
                    System.out.print("Enter Title, Author, Genre, ID, Status, Position: ");
                    lib.addAtPosition(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.next(), sc.nextInt());
                    sc.nextLine();
                    break;
                case 4:
                    System.out.print("Enter Book ID to Remove: ");
                    lib.removeById(sc.nextInt());
                    sc.nextLine();
                    break;
                case 5:
                    System.out.print("Enter Book Title to Search: ");
                    lib.searchByTitle(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter Author Name to Search: ");
                    lib.searchByAuthor(sc.nextLine());
                    break;
                case 7:
                    System.out.print("Enter Book ID and New Status: ");
                    lib.updateStatus(sc.nextInt(), sc.next());
                    sc.nextLine();
                    break;
                case 8:
                    lib.displayForward();
                    break;
                case 9:
                    lib.displayReverse();
                    break;
                case 10:
                    lib.countBooks();
                    break;
                case 11:
                    return;
            }
        }
    }
}

