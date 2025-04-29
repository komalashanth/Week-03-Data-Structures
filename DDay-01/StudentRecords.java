import java.util.Scanner;

class Student {
    int rollNo;
    String name;
    int age;
    char grade;
    Student next;

    Student(int rollNo, String name, int age, char grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

public class StudentRecords {
    static Student head = null;
    static Scanner sc = new Scanner(System.in);

    static void addAtBeginning(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        newStudent.next = head;
        head = newStudent;
    }

    static void addAtEnd(int rollNo, String name, int age, char grade) {
        Student newStudent = new Student(rollNo, name, age, grade);
        if (head == null) {
            head = newStudent;
            return;
        }
        Student temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newStudent;
    }

    static void addAtPosition(int pos, int rollNo, String name, int age, char grade) {
        if (pos == 1) {
            addAtBeginning(rollNo, name, age, grade);
            return;
        }
        Student temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++)
            temp = temp.next;
        if (temp == null) {
            System.out.println("Invalid position.");
            return;
        }
        Student newStudent = new Student(rollNo, name, age, grade);
        newStudent.next = temp.next;
        temp.next = newStudent;
    }

    static void deleteByRollNo(int rollNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.rollNo == rollNo) {
            head = head.next;
            System.out.println("Record deleted.");
            return;
        }
        Student temp = head;
        while (temp.next != null && temp.next.rollNo != rollNo)
            temp = temp.next;
        if (temp.next == null) {
            System.out.println("Record not found.");
            return;
        }
        temp.next = temp.next.next;
        System.out.println("Record deleted.");
    }

    static void searchByRollNo(int rollNo) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                System.out.println("Record Found:");
                System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Record not found.");
    }

    static void displayAll() {
        Student temp = head;
        if (temp == null) {
            System.out.println("No records to display.");
            return;
        }
        System.out.println("Student Records:");
        while (temp != null) {
            System.out.println("Roll No: " + temp.rollNo + ", Name: " + temp.name + ", Age: " + temp.age + ", Grade: " + temp.grade);
            temp = temp.next;
        }
    }

    static void updateGrade(int rollNo, char newGrade) {
        Student temp = head;
        while (temp != null) {
            if (temp.rollNo == rollNo) {
                temp.grade = newGrade;
                System.out.println("Grade updated.");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Record not found.");
    }

    public static void main(String[] args) {
        int choice, rollNo, age, pos;
        String name;
        char grade;

        do {
            System.out.println("\n1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Delete by Roll No\n5. Search by Roll No\n6. Display All\n7. Update Grade\n8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    rollNo = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next().charAt(0);
                    addAtBeginning(rollNo, name, age, grade);
                    break;
                case 2:
                    System.out.print("Enter Roll No, Name, Age, Grade: ");
                    rollNo = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next().charAt(0);
                    addAtEnd(rollNo, name, age, grade);
                    break;
                case 3:
                    System.out.print("Enter Position, Roll No, Name, Age, Grade: ");
                    pos = sc.nextInt();
                    rollNo = sc.nextInt();
                    name = sc.next();
                    age = sc.nextInt();
                    grade = sc.next().charAt(0);
                    addAtPosition(pos, rollNo, name, age, grade);
                    break;
                case 4:
                    System.out.print("Enter Roll No to delete: ");
                    rollNo = sc.nextInt();
                    deleteByRollNo(rollNo);
                    break;
                case 5:
                    System.out.print("Enter Roll No to search: ");
                    rollNo = sc.nextInt();
                    searchByRollNo(rollNo);
                    break;
                case 6:
                    displayAll();
                    break;
                case 7:
                    System.out.print("Enter Roll No and new Grade: ");
                    rollNo = sc.nextInt();
                    grade = sc.next().charAt(0);
                    updateGrade(rollNo, grade);
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 8);
    }
}
