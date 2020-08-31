public class Event extends Task{
    private static final String eventIcon = "[E]";
    private String eventAtMessage;

    public Event(String description) {
        super(description);
    }

    public void setEventAtMessage(String eventAtMessage) {
        this.eventAtMessage = eventAtMessage;
    }

    @Override
    public String toString() {
        return eventIcon + super.toString() + " (at: " + eventAtMessage + ")";
    }
}
