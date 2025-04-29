import java.util.*;

class Process {
    int id, burstTime, remainingTime, priority;
    Process next;

    Process(int id, int burstTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.next = null;
    }
}

class RoundRobinScheduler {
    Process head = null;

    void addProcess(int id, int burstTime, int priority) {
        Process newProcess = new Process(id, burstTime, priority);
        if (head == null) {
            head = newProcess;
            head.next = head;
        } else {
            Process temp = head;
            while (temp.next != head)
                temp = temp.next;
            temp.next = newProcess;
            newProcess.next = head;
        }
    }

    void removeProcess(int id) {
        if (head == null) return;
        if (head.id == id && head.next == head) {
            head = null;
            return;
        }
        Process curr = head, prev = null;
        do {
            if (curr.id == id) {
                if (curr == head) {
                    Process last = head;
                    while (last.next != head)
                        last = last.next;
                    head = head.next;
                    last.next = head;
                } else {
                    prev.next = curr.next;
                }
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);
    }

    void simulate(int quantum) {
        if (head == null) return;
        Map<Integer, Integer> waitingTime = new HashMap<>();
        Map<Integer, Integer> turnaroundTime = new HashMap<>();
        Map<Integer, Integer> startTime = new HashMap<>();
        int time = 0;
        Process curr = head;
        while (head != null) {
            if (curr.remainingTime > 0) {
                if (!startTime.containsKey(curr.id)) startTime.put(curr.id, time);
                int execTime = Math.min(quantum, curr.remainingTime);
                curr.remainingTime -= execTime;
                time += execTime;
                displayProcesses();
                if (curr.remainingTime == 0) {
                    turnaroundTime.put(curr.id, time);
                    removeProcess(curr.id);
                    if (head == null) break;
                    curr = curr.next;
                } else {
                    curr = curr.next;
                }
            } else {
                curr = curr.next;
            }
        }
        for (int id : turnaroundTime.keySet())
            waitingTime.put(id, turnaroundTime.get(id) - getBurstTime(id));
        double avgWT = 0, avgTAT = 0;
        for (int id : turnaroundTime.keySet()) {
            avgWT += waitingTime.get(id);
            avgTAT += turnaroundTime.get(id);
        }
        int n = turnaroundTime.size();
        System.out.println("Average Waiting Time: " + (avgWT / n));
        System.out.println("Average Turnaround Time: " + (avgTAT / n));
    }

    int getBurstTime(int id) {
        Process temp = head;
        if (temp == null) return 0;
        do {
            if (temp.id == id) return temp.burstTime;
            temp = temp.next;
        } while (temp != head);
        return 0;
    }

    void displayProcesses() {
        if (head == null) {
            System.out.println("No processes in queue");
            return;
        }
        Process temp = head;
        do {
            System.out.println("PID: " + temp.id + ", Remaining: " + temp.remainingTime + ", Priority: " + temp.priority);
            temp = temp.next;
        } while (temp != head);
        System.out.println("------");
    }
}

public class RoundRobinApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        RoundRobinScheduler scheduler = new RoundRobinScheduler();
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter PID, Burst Time, Priority: ");
            scheduler.addProcess(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();
        scheduler.simulate(quantum);
    }
}

