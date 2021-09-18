package ui;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private static Scanner in = new Scanner(System.in);

    private static void printDividerLine() {
        System.out.println("____________________________________________________________");
    }

    public static String getUserInput() {
        return in.nextLine();
    }

    public static void printGreetMessage() {
        String logo = " _____    ______      \n"
                + "|_   _|   |  _  \\     \n"
                + "  | | ___ | | | |___  \n"
                + "  | |/ _ \\| | | / _ \\ \n"
                + "  | | (_) | |/ / (_) |\n"
                + "  \\_/\\___/|___/ \\___/ \n";
        System.out.println(logo);
        printDividerLine();
        System.out.println("Hello, I'm task.ToDo, your personal planner!\n" + "What's on today's agenda?");
        printDividerLine();
    }

    public static void printByeMessage() {
        printDividerLine();
        System.out.println("Bye!");
        printDividerLine();
    }

    public static void printAddTaskMessage(ArrayList<Task> tasksList) {
        printDividerLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasksList.get(tasksList.size() - 1));
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        printDividerLine();
    }

    public static void printUnknownCommandMessage() {
        printDividerLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDividerLine();
    }

    public static void printTasksList(ArrayList<Task> tasksList) {
        printDividerLine();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task item: tasksList) {
            System.out.println((i++) + ". " + item);
        }
        printDividerLine();
    }

    public static void printNoDescriptionFoundMessage() {
        printDividerLine();
        System.out.println("☹ OOPS!!! The description cannot be empty.");
        printDividerLine();
    }

    public static void printInvalidNumberOfParametersMessage() {
        printDividerLine();
        System.out.println("☹ I cannot help you unless you provide the details!");
        printDividerLine();
    }

    public static void printDoneMessage(String description) {
        printDividerLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(description);
        printDividerLine();
    }

    public static void printTaskNotInListMessage() {
        printDividerLine();
        System.out.println("☹ OOPS!!! No such item in the list :-(");
        printDividerLine();
    }

    public static void printDeleteTaskMessage(String description, int size) {
        printDividerLine();
        System.out.println("Got it! I've removed this task:");
        System.out.println(description);
        System.out.println("Now you have " + size + " tasks in the list.");
        printDividerLine();
    }

    public static void printFailToWriteMessage() {
        printDividerLine();
        System.out.println("Failed to write data");
        printDividerLine();
    }

    public static void printFileNotFoundMessage() {
        printDividerLine();
        System.out.println("Error: save file not found");
        printDividerLine();
    }

    public static void printInvalidInputMessage() {
        printDividerLine();
        System.out.println("Invalid input!");
        printDividerLine();
    }
    
    public static void printFindTasksList(ArrayList<Task> tasks) {
        printDividerLine();
        if (!tasks.isEmpty()) {
            int i = 1;
            for (Task item : tasks) {
                System.out.println((i++) + ". " + item);
            }
        } else {
            System.out.println("Could not find matching tasks!");
        }
        printDividerLine();
    }
}
