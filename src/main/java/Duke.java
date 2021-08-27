import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public static void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printGreetMessage() {
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
        if(task.startsWith("deadline")) {
            addDeadline(task);
        } else if(task.startsWith("event")) {
            addEvent(task);
        } else if(task.startsWith("todo")) {
            addToDo(task);
        } else {
            tasksList.add(new Task(task));
        }

        printDividerLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasksList.get(tasksList.size() - 1));
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        printDividerLine();
    }

    private static void addToDo(String input) {
        input = input.split("todo")[1].trim();
        tasksList.add(new ToDo(input));
    }

    private static void addEvent(String input) {
        input = input.split("event")[1].trim();
        String description = input.split("/at")[0].trim();
        String time = input.split("/at")[1].trim();
        tasksList.add(new Event(description, time));
    }

    private static void addDeadline(String input) {
        input = input.split("deadline")[1].trim();
        String description = input.split("/by")[0].trim();
        String by = input.split("/by")[1].trim();
        tasksList.add(new Deadline(description, by));
    }

    public static void printTasksList() {
        printDividerLine();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for(Task item: tasksList) {
            System.out.println((i++) + ". " + item);
        }
        printDividerLine();
    }

    public static void markTaskAsDone(int taskIndex) {
        if(taskIndex <= tasksList.size()) {
            tasksList.get(taskIndex - 1).markAsDone();
            printDividerLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[X] " + tasksList.get(taskIndex - 1).getDescription());
        } else {
            printDividerLine();
            System.out.println("Invalid index!");
        }
        printDividerLine();
    }

    public static String handleInput(String input) {
        String[] wordsInInputString = input.split(" ");
        if(input.equals("bye")) {
            return "End";
        }
        if(input.equals("list")) {
            printTasksList();
        }
        else if(wordsInInputString[0].equals("done")) {
            int taskIndex = Integer.parseInt(wordsInInputString[1]);
            markTaskAsDone(taskIndex);
        }
        else {
            addTaskToList(input);
        }
        return "Continue";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printGreetMessage();
        String input = in.nextLine();
        do {
            input = in.nextLine();
        } while (handleInput(input) == "Continue");
        printByeMessage();
        in.close();
    }
}