import java.util.Scanner;

class Movie {
    String title, director;
    int year;
    double rating;
    Movie prev, next;

    Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
        this.prev = this.next = null;
    }
}

class MovieList {
    Movie head, tail;

    void addAtBeginning(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (head == null) {
            head = tail = newMovie;
        } else {
            newMovie.next = head;
            head.prev = newMovie;
            head = newMovie;
        }
    }

    void addAtEnd(String title, String director, int year, double rating) {
        Movie newMovie = new Movie(title, director, year, rating);
        if (tail == null) {
            head = tail = newMovie;
        } else {
            tail.next = newMovie;
            newMovie.prev = tail;
            tail = newMovie;
        }
    }

    void addAtPosition(String title, String director, int year, double rating, int pos) {
        if (pos <= 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        Movie temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++)
            temp = temp.next;
        if (temp == null || temp.next == null) {
            addAtEnd(title, director, year, rating);
            return;
        }
        Movie newMovie = new Movie(title, director, year, rating);
        newMovie.next = temp.next;
        newMovie.prev = temp;
        temp.next.prev = newMovie;
        temp.next = newMovie;
    }

    void removeByTitle(String title) {
        Movie temp = head;
        while (temp != null && !temp.title.equalsIgnoreCase(title))
            temp = temp.next;
        if (temp == null)
            return;
        if (temp == head)
            head = temp.next;
        if (temp == tail)
            tail = temp.prev;
        if (temp.prev != null)
            temp.prev.next = temp.next;
        if (temp.next != null)
            temp.next.prev = temp.prev;
    }

    void searchByDirector(String director) {
        Movie temp = head;
        while (temp != null) {
            if (temp.director.equalsIgnoreCase(director))
                printMovie(temp);
            temp = temp.next;
        }
    }

    void searchByRating(double rating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.rating == rating)
                printMovie(temp);
            temp = temp.next;
        }
    }

    void updateRating(String title, double newRating) {
        Movie temp = head;
        while (temp != null) {
            if (temp.title.equalsIgnoreCase(title)) {
                temp.rating = newRating;
                return;
            }
            temp = temp.next;
        }
    }

    void displayForward() {
        Movie temp = head;
        while (temp != null) {
            printMovie(temp);
            temp = temp.next;
        }
    }

    void displayReverse() {
        Movie temp = tail;
        while (temp != null) {
            printMovie(temp);
            temp = temp.prev;
        }
    }

    void printMovie(Movie m) {
        System.out.println("Title: " + m.title + ", Director: " + m.director + ", Year: " + m.year + ", Rating: " + m.rating);
    }
}

public class MovieManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieList list = new MovieList();
        while (true) {
            System.out.println("1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by Title\n5. Search by Director\n6. Search by Rating\n7. Update Rating\n8. Display Forward\n9. Display Reverse\n10. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    list.addAtBeginning(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("Enter Title, Director, Year, Rating: ");
                    list.addAtEnd(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble());
                    sc.nextLine();
                    break;
                case 3:
                    System.out.print("Enter Title, Director, Year, Rating, Position: ");
                    list.addAtPosition(sc.nextLine(), sc.nextLine(), sc.nextInt(), sc.nextDouble(), sc.nextInt());
                    sc.nextLine();
                    break;
                case 4:
                    System.out.print("Enter Title to Remove: ");
                    list.removeByTitle(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Director to Search: ");
                    list.searchByDirector(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter Rating to Search: ");
                    list.searchByRating(sc.nextDouble());
                    sc.nextLine();
                    break;
                case 7:
                    System.out.print("Enter Title and New Rating: ");
                    list.updateRating(sc.nextLine(), sc.nextDouble());
                    sc.nextLine();
                    break;
                case 8:
                    list.displayForward();
                    break;
                case 9:
                    list.displayReverse();
                    break;
                case 10:
                    return;
            }
        }
    }
}

