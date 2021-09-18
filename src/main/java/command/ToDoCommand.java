package command;

import storage.Storage;
import task.TaskList;
import task.ToDo;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addToList(new ToDo(description));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
