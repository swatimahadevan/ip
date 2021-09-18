package command;

import storage.Storage;
import task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        // index - 1 will be done at task.TaskList.deleteFromList
        tasks.deleteFromList(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
