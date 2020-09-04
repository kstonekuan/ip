public class ErrorTextManager extends TextManager {

    private static final String ERROR_PREFIX = "\u2639 OOPS!!! ";
    public static final String ERROR_NOT_COMMAND = "I'm sorry, but I don't know what that means :-(.";
    public static final String ERROR_DESCRIPTION_TODO = "The description of a todo cannot be empty.";
    public static final String ERROR_DESCRIPTION_DEADLINE = "The description of a deadline cannot be empty.";
    public static final String ERROR_DESCRIPTION_DEADLINE_BY = "The description of a deadline must have /by (by).";
    public static final String ERROR_DESCRIPTION_EVENT = "The description of an event cannot be empty.";
    public static final String ERROR_DESCRIPTION_EVENT_AT = "The description of an event must have /at (at).";
    public static final String ERROR_NO_TASKS = "You have no tasks in your list.";

    public ErrorTextManager() {
        super();
    }

    public static void printErrorMessage(String errorMessage) {
        printMessage(ERROR_PREFIX + errorMessage);
    }
}
