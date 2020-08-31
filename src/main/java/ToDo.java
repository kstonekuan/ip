public class ToDo extends Task {
    private static final String toDoIcon = "[T]";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return toDoIcon + super.toString();
    }
}
