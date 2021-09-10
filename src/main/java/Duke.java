import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();
    private static Storage storage;

    public static void printDividerLine() {
        System.out.println("____________________________________________________________");
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

    public static void addTaskToList(String task) {
        boolean isValidCommand = true;
        try {
            if (task.startsWith("deadline")) {
                addDeadline(task);
            } else if (task.startsWith("event")) {
                addEvent(task);
            } else if (task.startsWith("todo")) {
                addToDo(task);
            } else {
                handleUnknownCommand(task);
            }
        } catch (DukeInvalidArgumentException e) {
            isValidCommand = false;
        }

        if (isValidCommand) {
            printDividerLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(tasksList.get(tasksList.size() - 1));
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
            printDividerLine();
        }
    }

    private static void addToDo(String input) throws DukeInvalidArgumentException {
        String todoDescription;
        try {
            todoDescription = input.trim().split("todo")[1];
        } catch (IndexOutOfBoundsException e) {
            printDividerLine();
            System.out.println("☹ OOPS!!! The description of todo cannot be empty.");
            printDividerLine();
            throw new DukeInvalidArgumentException();
        }
        tasksList.add(new ToDo(todoDescription));
    }

    private static void addEvent(String input) throws DukeInvalidArgumentException {
        String eventInput;
        try {
            eventInput = input.trim().split("event")[1];
        } catch (IndexOutOfBoundsException e) {
            printDividerLine();
            System.out.println("☹ OOPS!!! The description of deadline cannot be empty.");
            printDividerLine();
            throw new DukeInvalidArgumentException();
        }
        String eventDescription, eventTime;
        try {
            eventDescription = eventInput.split("/at")[0].trim();
            eventTime = eventInput.split("/at")[1].trim();
        } catch (IndexOutOfBoundsException e) {
            printDividerLine();
            System.out.println("☹ I cannot help you unless you provide the details!");
            printDividerLine();
            throw new DukeInvalidArgumentException();
        }
        tasksList.add(new Event(eventDescription, eventTime));
    }


    private static void addDeadline(String input) throws DukeInvalidArgumentException {
        String deadlineInput;
        try {
            deadlineInput = input.trim().split("deadline")[1];
        } catch (IndexOutOfBoundsException e) {
            printDividerLine();
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            printDividerLine();
            throw new DukeInvalidArgumentException();
        }
        String deadlineDescription, deadlineTime;
        try {
            deadlineDescription = deadlineInput.split("/by")[0].trim();
            deadlineTime = deadlineInput.split("/by")[1].trim();
        } catch (IndexOutOfBoundsException e) {
            printDividerLine();
            System.out.println("☹ I cannot help you unless you provide the details!");
            printDividerLine();
            throw new DukeInvalidArgumentException();
        }
        tasksList.add(new Deadline(deadlineDescription, deadlineTime));
    }

    public static void printTasksList() {
        printDividerLine();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task item: tasksList) {
            System.out.println((i++) + ". " + item);
        }
        printDividerLine();
    }

    public static void deleteTask(int index) {
        printDividerLine();
        System.out.println("Got it! I've removed this task:");
        System.out.println(tasksList.get(index - 1));
        tasksList.remove(index - 1);
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        printDividerLine();
    }

    public static void markTaskAsDone(int index) throws DukeInvalidArgumentException {
        try {
            tasksList.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException();
        }
        printDividerLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasksList.get(index - 1).getDescription());
        printDividerLine();
    }

    public static String handleInput(String input) {
        String[] wordsInInputString = input.split(" ");
        if (input.equals("bye")) {
            return "End";
        }
        if (input.equals("list")) {
            printTasksList();
        } else if (wordsInInputString[0].equals("done")) {
            int index;
            try {
                index = Integer.parseInt(input.split("done")[1].trim());
                markTaskAsDone(index);
            } catch (DukeInvalidArgumentException e) {
                printDividerLine();
                System.out.println("☹ OOPS!!! No such item in the list :-(");
                printDividerLine();
            }
        } else if (input.startsWith("delete")){
            int index;
            index = Integer.parseInt(input.split("delete")[1].trim());
            deleteTask(index);
        } else {
            addTaskToList(input);
        }
        return "Continue";
    }

    private static void handleUnknownCommand(String input) throws DukeInvalidArgumentException {
        printDividerLine();
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        printDividerLine();
        throw new DukeInvalidArgumentException();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        printGreetMessage();
        String input;
        try{
            tasksList = Storage.loadData();
        } catch(FileNotFoundException f) {
            System.out.println("Error: save file not found");
        }
        do {
            input = in.nextLine();
            try {
                Storage.saveData(tasksList);
            } catch (IOException e){
                System.out.println("Failed to write data");
            }
        } while (handleInput(input) == "Continue");
        printByeMessage();
        in.close();
    }
}