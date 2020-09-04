package duke.task;

public class Event extends Task{
    private static final String EVENT_ICON = "[E]";
    private String eventAtMessage;

    public Event(String description, String eventAtMessage) {
        super(description);
        this.eventAtMessage = eventAtMessage;
    }

    @Override
    public String toString() {
        return EVENT_ICON + super.toString() + " (at: " + eventAtMessage + ")";
    }
}
