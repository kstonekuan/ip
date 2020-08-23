public class TextManager {
    // Horizontal line to wrap messages with
    private String horizontalLine = "____________________________________________________________\n";

    // Message to greet users.
    private String greetMessage = "Hello! I'm Duke\n"
            + "What can I do for you?\n";

    // Message upon exiting program.
    private String exitMessage = "Bye. Hope to see you again soon!\n";

    public TextManager() {
    }

    // Wrap message with lines and print
    public void printMessage(String message) {
        if (message == null) {
            return;
        }
        System.out.println(horizontalLine + message + horizontalLine);
    }

    // Print the greeting message
    public void printGreetMessage() {
        printMessage(greetMessage);
    }

    // Print the exit message
    public void printExitMessage() {
        printMessage(exitMessage);
    }
}
