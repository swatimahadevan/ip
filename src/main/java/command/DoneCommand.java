package command;

import exceptions.DukeInvalidArgumentException;
import storage.Storage;
import task.TaskList;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeInvalidArgumentException {
        try {
            tasks.markTaskAsDone(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
