package duke.task;

public class ToDo extends Task {
    public static final String TODO_ICON = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TODO_ICON + super.toString();
    }
}
