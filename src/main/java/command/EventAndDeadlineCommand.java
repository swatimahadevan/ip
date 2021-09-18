package command;

import storage.Storage;
import task.Event;
import task.Deadline;
import task.TaskList;

public class EventAndDeadlineCommand extends Command {
    private String type;
    private String description;
    private String timeOfEvent;

    public EventAndDeadlineCommand(String type, String description, String timeOfEvent) {
        this.type = type;
        this.description = description;
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public void execute(TaskList tasks, Storage storage){
        if (type.equals("event")) {
            Event event = new Event(description, timeOfEvent);
            tasks.addToList(event);
        } else {
            Deadline deadline = new Deadline(description, timeOfEvent);
            tasks.addToList(deadline);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

