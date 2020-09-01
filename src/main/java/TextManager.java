public class TextManager {
    // Duke's Logo
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    // Horizontal line to wrap messages with
    private static final String HORIZONTAL_LINE = "____________________________________________________________"
            + System.lineSeparator();

    // Message to greet users.
    private static final String GREET_MESSAGE = "Hello! I'm Duke" + System.lineSeparator()
            + "What can I do for you?" + System.lineSeparator();

    // Message upon exiting program.
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!" + System.lineSeparator();

    public TextManager() {
    }

    // Wraps message with lines and print
    public static void printMessage(String message) {
        if (message == null) {
            return;
        }
        System.out.println(HORIZONTAL_LINE + message + HORIZONTAL_LINE);
    }

    // Prints the greeting message
    public static void printGreetMessage() {
        System.out.println("Hello from" + System.lineSeparator() + logo);
        printMessage(GREET_MESSAGE);
    }

    // Prints the exit message
    public static void printExitMessage() {
        printMessage(EXIT_MESSAGE);
    }

    public static void printAddTask(Task task) {
        printMessage("Got it. I've added this task:" + System.lineSeparator()
                + "  " + task + System.lineSeparator()
                + "Now you have " + Task.getTaskCount() + " tasks in the list." + System.lineSeparator());
    }

    public static void printDoneTask(Task task) {
        printMessage("Nice! I've marked this task as done:" + System.lineSeparator()
                + "  " + task + System.lineSeparator());
    }

    public static void printTaskList(Task[] tasks) {
        printMessage("Here are the tasks in your list:" + System.lineSeparator());
        for (int i = 0; i < Task.getTaskCount(); i++) {
            printMessage((i + 1) + "." + tasks[i] + System.lineSeparator());
        }
    }
}
