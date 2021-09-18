package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        UI.printTasksList(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
