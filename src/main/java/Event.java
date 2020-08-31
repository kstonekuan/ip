public class Event extends Task{
    private static final String eventIcon = "[E]";
    private String eventAtMessage;

    public Event(String description, String eventAtMessage) {
        super(description);
        this.eventAtMessage = eventAtMessage;
    }

    @Override
    public String toString() {
        return eventIcon + super.toString() + " (at: " + eventAtMessage + ")";
    }
}
