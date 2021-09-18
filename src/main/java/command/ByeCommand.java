package command;

import storage.Storage;
import task.TaskList;
import ui.UI;

import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        try {
            storage.saveData(tasks.getTasks());
            UI.printByeMessage();
        } catch (IOException e) {
            UI.failToWriteMessage();
        }

    }

    @Override
    public boolean isExit() {
        return true;
    }
}
