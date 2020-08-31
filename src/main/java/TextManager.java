public class TextManager {
    // Horizontal line to wrap messages with
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";

    // Message to greet users.
    private static final String GREET_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    // Message upon exiting program.
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!\n";

    public TextManager() {
    }

    // Wraps message with lines and print
    public void printMessage(String message) {
        if (message == null) {
            return;
        }
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }

    // Prints the greeting message
    public void printGreetMessage() {
        printMessage(GREET_MESSAGE);
    }

    // Prints the exit message
    public void printExitMessage() {
        printMessage(EXIT_MESSAGE);
    }
}
