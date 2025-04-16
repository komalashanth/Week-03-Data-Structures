import java.util.*;

class User {
    int userId, age;
    String name;
    List<Integer> friends;
    User next;

    User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friends = new ArrayList<>();
        this.next = null;
    }
}

class SocialMedia {
    User head = null;

    void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newUser;
        }
    }

    User getUserById(int id) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == id) return temp;
            temp = temp.next;
        }
        return null;
    }

    void addFriendConnection(int id1, int id2) {
        User user1 = getUserById(id1);
        User user2 = getUserById(id2);
        if (user1 != null && user2 != null && id1 != id2) {
            if (!user1.friends.contains(id2)) user1.friends.add(id2);
            if (!user2.friends.contains(id1)) user2.friends.add(id1);
        }
    }

    void removeFriendConnection(int id1, int id2) {
        User user1 = getUserById(id1);
        User user2 = getUserById(id2);
        if (user1 != null && user2 != null) {
            user1.friends.remove(Integer.valueOf(id2));
            user2.friends.remove(Integer.valueOf(id1));
        }
    }

    void findMutualFriends(int id1, int id2) {
        User user1 = getUserById(id1);
        User user2 = getUserById(id2);
        if (user1 != null && user2 != null) {
            Set<Integer> mutual = new HashSet<>(user1.friends);
            mutual.retainAll(user2.friends);
            System.out.println("Mutual Friends: " + mutual);
        }
    }

    void displayFriends(int id) {
        User user = getUserById(id);
        if (user != null) {
            System.out.println("Friends of " + user.name + ":");
            for (int friendId : user.friends) {
                User f = getUserById(friendId);
                if (f != null)
                    System.out.println("ID: " + f.userId + ", Name: " + f.name + ", Age: " + f.age);
            }
        }
    }

    void searchByName(String name) {
        User temp = head;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(name)) {
                System.out.println("ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
            }
            temp = temp.next;
        }
    }

    void searchById(int id) {
        User user = getUserById(id);
        if (user != null)
            System.out.println("ID: " + user.userId + ", Name: " + user.name + ", Age: " + user.age);
    }

    void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println("User: " + temp.name + " has " + temp.friends.size() + " friends.");
            temp = temp.next;
        }
    }

    void displayAllUsers() {
        User temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.userId + ", Name: " + temp.name + ", Age: " + temp.age);
            temp = temp.next;
        }
    }
}

public class SocialMediaApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialMedia sm = new SocialMedia();
        while (true) {
            System.out.println("1. Add User\n2. Add Friend Connection\n3. Remove Friend Connection\n4. Find Mutual Friends\n5. Display Friends\n6. Search by Name\n7. Search by ID\n8. Count Friends\n9. Display All Users\n10. Exit");
            int ch = sc.nextInt();
            switch (ch) {
                case 1:
                    System.out.print("Enter ID, Name, Age: ");
                    sm.addUser(sc.nextInt(), sc.next(), sc.nextInt());
                    break;
                case 2:
                    System.out.print("Enter User IDs to Connect: ");
                    sm.addFriendConnection(sc.nextInt(), sc.nextInt());
                    break;
                case 3:
                    System.out.print("Enter User IDs to Disconnect: ");
                    sm.removeFriendConnection(sc.nextInt(), sc.nextInt());
                    break;
                case 4:
                    System.out.print("Enter Two User IDs to Find Mutual Friends: ");
                    sm.findMutualFriends(sc.nextInt(), sc.nextInt());
                    break;
                case 5:
                    System.out.print("Enter User ID: ");
                    sm.displayFriends(sc.nextInt());
                    break;
                case 6:
                    System.out.print("Enter Name to Search: ");
                    sc.nextLine();
                    sm.searchByName(sc.nextLine());
                    break;
                case 7:
                    System.out.print("Enter ID to Search: ");
                    sm.searchById(sc.nextInt());
                    break;
                case 8:
                    sm.countFriends();
                    break;
                case 9:
                    sm.displayAllUsers();
                    break;
                case 10:
                    return;
            }
        }
    }
}

