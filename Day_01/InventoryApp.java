import java.util.*;

class Item {
    String name;
    int id, quantity;
    double price;
    Item next;

    Item(String name, int id, int quantity, double price) {
        this.name = name;
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    Item head = null;

    void addAtBeginning(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    void addAtEnd(String name, int id, int quantity, double price) {
        Item newItem = new Item(name, id, quantity, price);
        if (head == null) {
            head = newItem;
        } else {
            Item temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newItem;
        }
    }

    void addAtPosition(String name, int id, int quantity, double price, int pos) {
        if (pos <= 1) {
            addAtBeginning(name, id, quantity, price);
            return;
        }
        Item temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++)
            temp = temp.next;
        if (temp == null) {
            addAtEnd(name, id, quantity, price);
            return;
        }
        Item newItem = new Item(name, id, quantity, price);
        newItem.next = temp.next;
        temp.next = newItem;
    }

    void removeById(int id) {
        if (head == null)
            return;
        if (head.id == id) {
            head = head.next;
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.id != id)
            temp = temp.next;
        if (temp.next != null)
            temp.next = temp.next.next;
    }

    void updateQuantity(int id, int quantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                temp.quantity = quantity;
                return;
            }
            temp = temp.next;
        }
    }

    void searchById(int id) {
        Item temp = head;
        while (temp != null) {
            if (temp.id == id) {
                printItem(temp);
                return;
            }
            temp = temp.next;
        }
    }

    void searchByName(String name) {
        Item temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name))
                printItem(temp);
            temp = temp.next;
        }
    }

    void calculateTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.quantity * temp.price;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: " + total);
    }

    void sortByName(boolean ascending) {
        for (Item i = head; i != null; i = i.next) {
            for (Item j = i.next; j != null; j = j.next) {
                if ((ascending && i.name.compareTo(j.name) > 0) || (!ascending && i.name.compareTo(j.name) < 0)) {
                    swap(i, j);
                }
            }
        }
    }

    void sortByPrice(boolean ascending) {
        for (Item i = head; i != null; i = i.next) {
            for (Item j = i.next; j != null; j = j.next) {
                if ((ascending && i.price > j.price) || (!ascending && i.price < j.price)) {
                    swap(i, j);
                }
            }
        }
    }

    void swap(Item a, Item b) {
        String tempName = a.name;
        int tempId = a.id;
        int tempQty = a.quantity;
        double tempPrice = a.price;

        a.name = b.name;
        a.id = b.id;
        a.quantity = b.quantity;
        a.price = b.price;

        b.name = tempName;
        b.id = tempId;
        b.quantity = tempQty;
        b.price = tempPrice;
    }

    void display() {
        Item temp = head;
        while (temp != null) {
            printItem(temp);
            temp = temp.next;
        }
    }

    void printItem(Item item) {
        System.out.println("Name: " + item.name + ", ID: " + item.id + ", Qty: " + item.quantity + ", Price: " + item.price);
    }
}

public class InventoryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inv = new Inventory();
        while (true) {
            System.out.println("1. Add at Beginning\n2. Add at End\n3. Add at Position\n4. Remove by ID\n5. Update Quantity\n6. Search by ID\n7. Search by Name\n8. Display All\n9. Total Inventory Value\n10. Sort by Name\n11. Sort by Price\n12. Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter Name, ID, Qty, Price: ");
                    inv.addAtBeginning(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                    break;
                case 2:
                    System.out.print("Enter Name, ID, Qty, Price: ");
                    inv.addAtEnd(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble());
                    break;
                case 3:
                    System.out.print("Enter Name, ID, Qty, Price, Position: ");
                    inv.addAtPosition(sc.next(), sc.nextInt(), sc.nextInt(), sc.nextDouble(), sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter ID to Remove: ");
                    inv.removeById(sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter ID and New Qty: ");
                    inv.updateQuantity(sc.nextInt(), sc.nextInt());
                    break;
                case 6:
                    System.out.print("Enter ID to Search: ");
                    inv.searchById(sc.nextInt());
                    break;
                case 7:
                    System.out.print("Enter Name to Search: ");
                    inv.searchByName(sc.nextLine());
                    break;
                case 8:
                    inv.display();
                    break;
                case 9:
                    inv.calculateTotalValue();
                    break;
                case 10:
                    System.out.print("1 for Ascending, 0 for Descending: ");
                    inv.sortByName(sc.nextInt() == 1);
                    break;
                case 11:
                    System.out.print("1 for Ascending, 0 for Descending: ");
                    inv.sortByPrice(sc.nextInt() == 1);
                    break;
                case 12:
                    return;
            }
        }
    }
}

