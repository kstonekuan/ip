package duke.task;

/**
 * Represents an event task. An <code>Event</code> object corresponds to
 * an event with a description, done status and event at message.
 */
public class Event extends Task{
    public static final String EVENT_ICON = "[E]";
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
