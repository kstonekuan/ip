import java.util.Scanner;

public class TextManager {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String LOGO_DUKE = " ____        _        " + System.lineSeparator()
            + "|  _ \\ _   _| | _____ " + System.lineSeparator()
            + "| | | | | | | |/ / _ \\" + System.lineSeparator()
            + "| |_| | |_| |   <  __/" + System.lineSeparator()
            + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();
    private static final String HORIZONTAL_LINE = "____________________________________________________________"
            + System.lineSeparator();

    private static final String MESSAGE_GREET = "Hello! I'm Duke" + System.lineSeparator()
            + "What can I do for you?" + System.lineSeparator();
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!" + System.lineSeparator();
    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:" + System.lineSeparator()
            + "  ";
    private static final String MESSAGE_TASK_COUNT_PREFIX = "Now you have ";
    private static final String MESSAGE_TASK_COUNT_SUFFIX = " tasks in the list.";
    private static final String MESSAGE_TASK_DONE = "Nice! I've marked this task as done:" + System.lineSeparator()
            + "  ";
    private static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:" + System.lineSeparator();
    private static final String MESSAGE_TASK_LIST_SEPARATOR = ".";


    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_DEADLINE_BY_SEPARATOR = " /by ";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_EVENT_AT_SEPARATOR = " /at ";

    public TextManager() {
    }

    public static String getUserInput() {
        return SCANNER.nextLine();
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
        System.out.println(LOGO_DUKE);
        printMessage(MESSAGE_GREET);
    }

    // Prints the exit message
    public static void printExitMessage() {
        printMessage(MESSAGE_EXIT);
    }

    public static void printAddTask(Task task) {
        printMessage(MESSAGE_TASK_ADDED
                + task + System.lineSeparator()
                + MESSAGE_TASK_COUNT_PREFIX + Task.getTaskCount() + MESSAGE_TASK_COUNT_SUFFIX + System.lineSeparator());
    }

    public static void printDoneTask(Task task) {
        printMessage(MESSAGE_TASK_DONE
                + task + System.lineSeparator());
    }

    public static void printTaskList(Task[] tasks) {
        printMessage(MESSAGE_TASK_LIST);
        for (int i = 0; i < Task.getTaskCount(); i++) {
            System.out.println((i + 1) + MESSAGE_TASK_LIST_SEPARATOR + tasks[i]);
        }
    }
}
