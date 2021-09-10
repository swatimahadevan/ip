package task;

public class ToDo extends Task {
    public static final String type = "T";
    public ToDo(String description) {
        super(description);
    }
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }
    @Override
    public String toString() {
        return "[" + type + "]" + super.toString();
    }
    @Override
    public String getTaskType() {
        return type;
    }
}