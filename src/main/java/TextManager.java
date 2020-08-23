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

    public String getGreetMessage() {
        return greetMessage;
    }

    public String getExitMessage() {
        return exitMessage;
    }

    public void printMessage(String message) {
        System.out.println(horizontalLine + message + horizontalLine);
    }
}
