package duke.ui;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String LOGO_DUKE = " ____        _" + System.lineSeparator()
            + "|  _ \\ _   _| | _____ " + System.lineSeparator()
            + "| | | | | | | |/ / _ \\" + System.lineSeparator()
            + "| |_| | |_| |   <  __/" + System.lineSeparator()
            + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();
    private static final String HORIZONTAL_LINE = "____________________________________________________________"
            + System.lineSeparator();

    private static final String MESSAGE_GREET = "Hello! I'm Duke" + System.lineSeparator()
            + "What can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_TASK_ADDED = "Got it. I've added this task:";
    private static final String MESSAGE_TASK_COUNT_PREFIX = "Now you have ";
    private static final String MESSAGE_TASK_COUNT_SUFFIX = " tasks in the list.";
    private static final String MESSAGE_TASK_DONE = "Nice! I've marked this task as done:";
    private static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:";
    private static final String MESSAGE_TASK_LIST_SEPARATOR = ".";
    private static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:";
    private static final String MESSAGE_SPACING = "  ";

    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_TODO = "todo";
    public static final String COMMAND_DEADLINE = "deadline";
    public static final String COMMAND_DEADLINE_BY_SEPARATOR = " /by ";
    public static final String COMMAND_EVENT = "event";
    public static final String COMMAND_EVENT_AT_SEPARATOR = " /at ";
    public static final String COMMAND_DELETE = "delete";

    public static final int INPUT_RAW_INDEX_COMMAND = 0;
    public static final int INPUT_RAW_INDEX_DESC = 1;
    public static final int INPUT_DEADLINE_INDEX_DESC = 0;
    public static final int INPUT_DEADLINE_INDEX_BY = 1;
    public static final int INPUT_EVENT_INDEX_DESC = 0;
    public static final int INPUT_EVENT_INDEX_AT = 1;
    public static final String INPUT_DELIMITER = " ";

    public Ui() {
    }

    public String getUserInput() {
        return SCANNER.nextLine();
    }

    // Wraps message with lines and print
    protected static void printMessage(String message) {
        if (message == null) {
            return;
        }
        System.out.println(HORIZONTAL_LINE + message + System.lineSeparator() + HORIZONTAL_LINE);
    }

    // Prints the greeting message
    public void printGreetMessage() {
        System.out.println(LOGO_DUKE);
        printMessage(MESSAGE_GREET);
    }

    // Prints the exit message
    public void printExitMessage() {
        printMessage(MESSAGE_EXIT);
    }

    public void printAddTask(Task task, int taskCount) {
        printMessage(MESSAGE_TASK_ADDED + System.lineSeparator()
                + MESSAGE_SPACING + task + System.lineSeparator()
                + MESSAGE_TASK_COUNT_PREFIX + taskCount + MESSAGE_TASK_COUNT_SUFFIX);
    }

    public void printDeleteTask(Task task, int taskCount) {
        printMessage(MESSAGE_TASK_DELETED + System.lineSeparator()
                + MESSAGE_SPACING + task + System.lineSeparator()
                + MESSAGE_TASK_COUNT_PREFIX + taskCount + MESSAGE_TASK_COUNT_SUFFIX);
    }

    public void printDoneTask(Task task) {
        printMessage(MESSAGE_TASK_DONE + System.lineSeparator()
                + MESSAGE_SPACING + task);
    }

    public void printTaskList(ArrayList<Task> tasks, int taskCount) {
        try {
            if (taskCount <= 0) {
                throw new DukeException();
            }
            String taskList = MESSAGE_TASK_LIST;
            for (int i = 0; i < taskCount; i++) {
                taskList += System.lineSeparator() + (i + 1) + MESSAGE_TASK_LIST_SEPARATOR + tasks.get(i);
            }
            printMessage(taskList);
        } catch (DukeException e) {
            ErrorUi errorUi = new ErrorUi();
            errorUi.printErrorMessage(ErrorUi.ERROR_NO_TASKS);
        }
    }
}
