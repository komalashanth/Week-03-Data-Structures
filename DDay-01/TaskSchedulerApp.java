import java.util.Scanner;

class Task {
    int id;
    String name;
    int priority;
    String dueDate;
    Task next;

    Task(int id, String name, int priority, String dueDate) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
        this.next = null;
    }
}

class TaskScheduler {
    Task head = null;
    Task current = null;

    void addAtBeginning(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head)
                temp = temp.next;
            newTask.next = head;
            temp.next = newTask;
            head = newTask;
        }
    }

    void addAtEnd(int id, String name, int priority, String dueDate) {
        Task newTask = new Task(id, name, priority, dueDate);
        if (head == null) {
            head = newTask;
            newTask.next = head;
            current = head;
        } else {
            Task temp = head;
            while (temp.next != head)
                temp = temp.next;
            temp.next = newTask;
            newTask.next = head;
        }
    }

    void addAtPosition(int id, String name, int priority, String dueDate, int pos) {
        if (pos <= 1) {
            addAtBeginning(id, name, priority, dueDate);
            return;
        }
        Task newTask = new Task(id, name, priority, dueDate);
        Task temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++)
            temp = temp.next;
        newTask.next = temp.next;
        temp.next = newTask;
    }

    void removeById(int id) {
        if (head == null)
            return;
        if (head.id == id && head.next == head) {
            head = null;
            current = null;
            return;
        }
        Task temp = head, prev = null;
        do {
            if (temp.id == id) {
                if (temp == head) {
                    Task last = head;
                    while (last.next != head)
                        last = last.next;
                    head = head.next;
                    last.next = head;
                } else {
                    prev.next = temp.next;
                }
                if (current == temp)
                    current = temp.next;
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    void viewCurrentTask() {
        if (current == null)
            return;
        printTask(current);
    }

    void moveToNextTask() {
        if (current != null)
            current = current.next;
    }

    void displayAllTasks() {
        if (head == null)
            return;
        Task temp = head;
        do {
            printTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    void searchByPriority(int priority) {
        if (head == null)
            return;
        Task temp = head;
        do {
            if (temp.priority == priority)
                printTask(temp);
            temp = temp.next;
        } while (temp != head);
    }

    void printTask(Task t) {
        System.out.println("ID: " + t.id + ", Name: " + t.name + ", Priority: " + t.priority + ", Due: " + t.dueDate);
    }
}

public class TaskSchedulerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskScheduler scheduler = new TaskScheduler();
        while (true) {
            System.out.println("1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID\n5. View Current Task\n6. Move to Next Task\n7. Display All Tasks\n8. Search by Priority\n9. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    scheduler.addAtBeginning(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 2:
                    System.out.print("Enter ID, Name, Priority, Due Date: ");
                    scheduler.addAtEnd(sc.nextInt(), sc.next(), sc.nextInt(), sc.next());
                    break;
                case 3:
                    System.out.print("Enter ID, Name, Priority, Due Date, Position: ");
                    scheduler.addAtPosition(sc.nextInt(), sc.next(), sc.nextInt(), sc.next(), sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter Task ID to Remove: ");
                    scheduler.removeById(sc.nextInt());
                    break;
                case 5:
                    scheduler.viewCurrentTask();
                    break;
                case 6:
                    scheduler.moveToNextTask();
                    break;
                case 7:
                    scheduler.displayAllTasks();
                    break;
                case 8:
                    System.out.print("Enter Priority to Search: ");
                    scheduler.searchByPriority(sc.nextInt());
                    break;
                case 9:
                    return;
            }
        }
    }
}

