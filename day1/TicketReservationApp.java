import java.util.*;

class Ticket {
    int ticketId;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    Ticket next;

    Ticket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

class TicketSystem {
    Ticket head = null, tail = null;

    void addTicket(int ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        Ticket newTicket = new Ticket(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (head == null) {
            head = tail = newTicket;
            newTicket.next = head;
        } else {
            tail.next = newTicket;
            newTicket.next = head;
            tail = newTicket;
        }
    }

    void removeTicket(int ticketId) {
        if (head == null) return;
        if (head.ticketId == ticketId && head == tail) {
            head = tail = null;
            return;
        }
        Ticket temp = head, prev = null;
        do {
            if (temp.ticketId == ticketId) {
                if (temp == head) {
                    head = head.next;
                    tail.next = head;
                } else if (temp == tail) {
                    tail = prev;
                    tail.next = head;
                } else {
                    prev.next = temp.next;
                }
                return;
            }
            prev = temp;
            temp = temp.next;
        } while (temp != head);
    }

    void displayTickets() {
        if (head == null) {
            System.out.println("No tickets booked.");
            return;
        }
        Ticket temp = head;
        do {
            printTicket(temp);
            temp = temp.next;
        } while (temp != head);
    }

    void searchByCustomerName(String name) {
        if (head == null) return;
        Ticket temp = head;
        do {
            if (temp.customerName.equalsIgnoreCase(name)) {
                printTicket(temp);
            }
            temp = temp.next;
        } while (temp != head);
    }

    void searchByMovieName(String movie) {
        if (head == null) return;
        Ticket temp = head;
        do {
            if (temp.movieName.equalsIgnoreCase(movie)) {
                printTicket(temp);
            }
            temp = temp.next;
        } while (temp != head);
    }

    int countTickets() {
        if (head == null) return 0;
        int count = 0;
        Ticket temp = head;
        do {
            count++;
            temp = temp.next;
        } while (temp != head);
        return count;
    }

    void printTicket(Ticket t) {
        System.out.println("TicketID: " + t.ticketId + ", Customer: " + t.customerName + ", Movie: " + t.movieName + ", Seat: " + t.seatNumber + ", Time: " + t.bookingTime);
    }
}

public class TicketReservationApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TicketSystem system = new TicketSystem();
        while (true) {
            System.out.println("1. Add Ticket\n2. Remove Ticket\n3. Display Tickets\n4. Search by Customer\n5. Search by Movie\n6. Count Tickets\n7. Exit");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.print("Enter TicketID, CustomerName, MovieName, SeatNumber, BookingTime: ");
                    int id = sc.nextInt(); sc.nextLine();
                    String cname = sc.nextLine();
                    String mname = sc.nextLine();
                    String seat = sc.nextLine();
                    String time = sc.nextLine();
                    system.addTicket(id, cname, mname, seat, time);
                    break;
                case 2:
                    System.out.print("Enter Ticket ID to Remove: ");
                    system.removeTicket(sc.nextInt());
                    break;
                case 3:
                    system.displayTickets();
                    break;
                case 4:
                    System.out.print("Enter Customer Name: ");
                    system.searchByCustomerName(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter Movie Name: ");
                    system.searchByMovieName(sc.nextLine());
                    break;
                case 6:
                    System.out.println("Total Booked Tickets: " + system.countTickets());
                    break;
                case 7:
                    return;
            }
        }
    }
}

