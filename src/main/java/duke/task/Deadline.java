package duke.task;

/**
 * Represents a deadline task. A <code>Deadline</code> object corresponds to
 * a deadline with a description, done status and do by message.
 */
public class Deadline extends Task{
    public static final String DEADLINE_ICON = "[D]";
    private String doByMessage;

    public Deadline(String description, String doByMessage) {
        super(description);
        this.doByMessage = doByMessage;
    }

    @Override
    public String toString() {
        return DEADLINE_ICON + super.toString() + " (by: " + doByMessage + ")";
    }
}
