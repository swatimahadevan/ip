package task;

import exceptions.DukeInvalidArgumentException;
import ui.UI;
import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addToList(Task t) {
        tasks.add(t);
        UI.addToListMessage(tasks);
    }

    public void deleteFromList(int index) {
        String deletedTask = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        int size = tasks.size();
        UI.deleteTaskMessage(deletedTask, size);
    }

    public void markTaskAsDone(int index) throws DukeInvalidArgumentException {
        try {
            tasks.get(index - 1).markAsDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException();
        }
        String description = tasks.get(index - 1).toString();
        UI.markTaskAsDoneMessage(description);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
