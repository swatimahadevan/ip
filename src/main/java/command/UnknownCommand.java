package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

public class UnknownCommand extends Command {
    String description;

    public UnknownCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        UI.printUnknownCommandMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
