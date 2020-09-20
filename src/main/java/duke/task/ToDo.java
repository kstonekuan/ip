package duke.task;

/**
 * Represents a todo task. A <code>ToDo</code> object corresponds to
 * a todo with a description and done status.
 */
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
