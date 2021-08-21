import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public static void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreetMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDividerLine();
        System.out.println("Hello, I'm Duke\n" + "What can I do for you?");
        printDividerLine();
    }

    public static void printByeMessage() {
        printDividerLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividerLine();
    }

    public static void addTaskToList(String task) {
        tasksList.add(new Task(task));
        printDividerLine();
        System.out.println("added: " + task);
        printDividerLine();
    }

    public static void printTasksList() {
        printDividerLine();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for(Task item: tasksList) {
            System.out.println((i++) + ". " + item.getStatusIcon() + " " + item.getDescription());
        }
        printDividerLine();
    }

    public static void markTaskAsDone(int taskIndex) {
        tasksList.get(taskIndex - 1).markAsDone();
        printDividerLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasksList.get(taskIndex - 1).getDescription());
        printDividerLine();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printGreetMessage();
        String input = in.nextLine();
        while(!input.equals("bye")) {
            String[] words = input.split(" ");
            if(input.equals("list")) {
                printTasksList();
            }
            else if(words[0].equals("done")) {
                int taskIndex = Integer.parseInt(words[1]);
                markTaskAsDone(taskIndex);
            }
            else {
                addTaskToList(input);
            }
            input = in.nextLine();
        }
        printByeMessage();
        in.close();
    }
}