import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import ui.UI;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static ArrayList<Task> tasksList = new ArrayList<>();
    private static Storage storage;

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
            UI.addToListMessage(tasksList);
        }
    }

    private static void addToDo(String input) throws DukeInvalidArgumentException {
        String todoDescription;
        try {
            todoDescription = input.trim().split("todo")[1];
        } catch (IndexOutOfBoundsException e) {
            UI.noDescriptionFoundMessage();
            throw new DukeInvalidArgumentException();
        }
        tasksList.add(new ToDo(todoDescription));
    }

    private static void addEvent(String input) throws DukeInvalidArgumentException {
        String eventInput;
        try {
            eventInput = input.trim().split("event")[1];
        } catch (IndexOutOfBoundsException e) {
            UI.noDescriptionFoundMessage();
            throw new DukeInvalidArgumentException();
        }
        String eventDescription, eventTime;
        try {
            eventDescription = eventInput.split("/at")[0].trim();
            eventTime = eventInput.split("/at")[1].trim();
        } catch (IndexOutOfBoundsException e) {
            UI.invalidNumberOfParametersMessage();
            throw new DukeInvalidArgumentException();
        }
        tasksList.add(new Event(eventDescription, eventTime));
    }


    private static void addDeadline(String input) throws DukeInvalidArgumentException {
        String deadlineInput;
        try {
            deadlineInput = input.trim().split("deadline")[1];
        } catch (IndexOutOfBoundsException e) {
            UI.noDescriptionFoundMessage();
            throw new DukeInvalidArgumentException();
        }
        String deadlineDescription, deadlineTime;
        try {
            deadlineDescription = deadlineInput.split("/by")[0].trim();
            deadlineTime = deadlineInput.split("/by")[1].trim();
        } catch (IndexOutOfBoundsException e) {
            UI.invalidNumberOfParametersMessage();
            throw new DukeInvalidArgumentException();
        }
        tasksList.add(new Deadline(deadlineDescription, deadlineTime));
    }

    public static void deleteTask(int index) {
        UI.deleteTaskMessage(tasksList.get(index - 1).getDescription(), tasksList.size()-1);
        tasksList.remove(index - 1);
    }

    public static void markTaskAsDone(int index) throws DukeInvalidArgumentException {
        try {
            tasksList.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException();
        }
        UI.markTaskAsDoneMessage(tasksList.get(index - 1).getDescription());
    }

    public static String handleInput(String input) {
        String[] wordsInInputString = input.split(" ");
        if (input.equals("bye")) {
            return "End";
        }
        if (input.equals("list")) {
            UI.printTasksList(tasksList);
        } else if (wordsInInputString[0].equals("done")) {
            int index;
            try {
                index = Integer.parseInt(input.split("done")[1].trim());
                markTaskAsDone(index);
            } catch (DukeInvalidArgumentException e) {
                UI.taskNotInListMessage();
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
        UI.handleUnknownCommand();
        throw new DukeInvalidArgumentException();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        UI.printGreetMessage();
        String input;
        try {
            tasksList = Storage.loadData();
        } catch (FileNotFoundException f) {
            UI.failToWriteMessage();
        }
        do {
            input = in.nextLine();
            try {
                Storage.saveData(tasksList);
            } catch (IOException e){
                UI.fileNotFoundMessage();
            }
        } while (handleInput(input) == "Continue");
        UI.printByeMessage();
        in.close();
    }
}