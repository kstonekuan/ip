package duke.ui;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a user interface manager for inputs and outputs.
 */
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
    private static final String MESSAGE_TASK_DELETED = "Noted. I've removed this task:";
    private static final String MESSAGE_TASK_FOUND = "Here are the matching tasks in your list:";
    private static final String MESSAGE_SPACING = "  ";

    public static final int INPUT_RAW_INDEX_COMMAND = 0;
    public static final int INPUT_RAW_INDEX_DESC = 1;
    public static final int INPUT_DEADLINE_INDEX_DESC = 0;
    public static final int INPUT_DEADLINE_INDEX_BY = 1;
    public static final int INPUT_EVENT_INDEX_DESC = 0;
    public static final int INPUT_EVENT_INDEX_AT = 1;
    public static final String INPUT_DELIMITER = " ";

    public Ui() {
    }

    /**
     * Returns a line inputted by the user.
     *
     * @return String inputted without next line character.
     */
    public String getUserInput() {
        return SCANNER.nextLine();
    }

    protected static void printMessage(String message) {
        if (message == null) {
            return;
        }
        System.out.println(HORIZONTAL_LINE + message + System.lineSeparator() + HORIZONTAL_LINE);
    }

    /**
     * Prints message to greet user upon startup.
     */
    public void printGreetMessage() {
        System.out.println(LOGO_DUKE);
        printMessage(MESSAGE_GREET);
    }

    /**
     * Prints message when user exists the program.
     */
    public void printExitMessage() {
        printMessage(MESSAGE_EXIT);
    }

    /**
     * Prints message to confirm that the task was added to the list.
     *
     * @param task Task which was added.
     * @param taskCount Total number of tasks in the list.
     */
    public void printAddTask(Task task, int taskCount) {
        printMessage(MESSAGE_TASK_ADDED + System.lineSeparator()
                + MESSAGE_SPACING + task + System.lineSeparator()
                + MESSAGE_TASK_COUNT_PREFIX + taskCount + MESSAGE_TASK_COUNT_SUFFIX);
    }

    /**
     * Prints message to confirm that the task was deleted from the list.
     *
     * @param task Task which was deleted.
     * @param taskCount Total number of tasks in the list.
     */
    public void printDeleteTask(Task task, int taskCount) {
        printMessage(MESSAGE_TASK_DELETED + System.lineSeparator()
                + MESSAGE_SPACING + task + System.lineSeparator()
                + MESSAGE_TASK_COUNT_PREFIX + taskCount + MESSAGE_TASK_COUNT_SUFFIX);
    }

    /**
     * Prints message to confirm that the task was marked as done.
     *
     * @param task Task which was done.
     */
    public void printDoneTask(Task task) {
        printMessage(MESSAGE_TASK_DONE + System.lineSeparator()
                + MESSAGE_SPACING + task);
    }

    /**
     * Prints message with the tasks from the list.
     *
     * @param tasksAsNumberedList Tasks formatted in a numbered list.
     */
    public void printTaskList(String tasksAsNumberedList) {
        try {
            if (tasksAsNumberedList.equals("")) {
                throw new DukeException();
            }

            printMessage(MESSAGE_TASK_LIST + tasksAsNumberedList);
        } catch (DukeException e) {
            ErrorUi errorUi = new ErrorUi();
            errorUi.printErrorMessage(ErrorUi.ERROR_NO_TASKS);
        }
    }

    /**
     * Prints message with the list of tasks found by keyword.
     *
     * @param tasksAsNumberedList Tasks found formatted in a numbered list.
     */
    public void printFoundTasks(String tasksAsNumberedList) {
        try {
            if (tasksAsNumberedList.equals("")) {
                throw new DukeException();
            }

            printMessage(MESSAGE_TASK_FOUND + tasksAsNumberedList);
        } catch (DukeException e) {
            ErrorUi errorUi = new ErrorUi();
            errorUi.printErrorMessage(ErrorUi.ERROR_NO_TASKS_FOUND);
        }
    }
}
