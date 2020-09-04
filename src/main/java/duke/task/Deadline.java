package duke.task;

public class Deadline extends Task{
    private static final String DEADLINE_ICON = "[D]";
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
