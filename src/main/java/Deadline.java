public class Deadline extends Task{
    private static final String deadlineIcon = "[D]";
    private String doByMessage;

    public Deadline(String description) {
        super(description);
    }

    public void setDoByMessage(String doByMessage) {
        this.doByMessage = doByMessage;
    }

    @Override
    public String toString() {
        return deadlineIcon + super.toString() + " (by: " + doByMessage + ")";
    }
}
