package duke.task;

import duke.DukeException;
import duke.ui.ErrorUi;
import duke.ui.Ui;

import java.util.ArrayList;
import static java.util.stream.Collectors.toList;

/**
 * Represents a list of tasks. A <code>TaskList</code> object corresponds to
 * a list of tasks, a Ui object and an ErrorUi object.
 */
public class TaskList {

    private static final String NUMBERED_LIST_SEPARATOR = ".";

    private ArrayList<Task> tasks;
    private Ui ui;
    private ErrorUi errorUi;

    public TaskList() {
        ui = new Ui();
        errorUi = new ErrorUi();
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        ui = new Ui();
        errorUi = new ErrorUi();
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list currently.
     *
     * @return Number of tasks.
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the last task added to the list.
     * Usually used for printing an added task.
     *
     * @return Last task.
     */
    private Task getLastTask() {
        return tasks.get(getTaskCount() - 1);
    }

    /**
     * Deletes a task from the list based on the index.
     *
     * @param description Description from delete command.
     */
    public void deleteFromTasks(String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            int taskDeleteIndex = Integer.parseInt(description) - 1;
            Task taskRemoved = tasks.remove(taskDeleteIndex);
            ui.printDeleteTask(taskRemoved, getTaskCount());
        } catch (NumberFormatException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_INDEX_NOT_NUMBER);
        } catch (DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DELETE);
        } catch (IndexOutOfBoundsException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_INDEX_OUT_OF_BOUNDS);
        }
    }

    /**
     * Adds an event task to the list.
     *
     * @param eventInputs Array of inputs from event command.
     */
    public void addEventToTasks(String[] eventInputs) {
        try {
            String eventDescription = eventInputs[Ui.INPUT_EVENT_INDEX_DESC];

            if (eventDescription.equals("")) {
                throw new DukeException();
            }

            String eventAtMessage = eventInputs[Ui.INPUT_EVENT_INDEX_AT];

            tasks.add(new Event(eventDescription, eventAtMessage));

            ui.printAddTask(getLastTask(), getTaskCount());
        } catch (DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_EVENT);
        } catch (IndexOutOfBoundsException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_EVENT_AT);
        }
    }

    /**
     * Adds a deadline task to the list.
     *
     * @param deadlineInputs Array of inputs from deadline command.
     */
    public void addDeadlineToTasks(String[] deadlineInputs) {
        try {
            String deadlineDescription = deadlineInputs[Ui.INPUT_DEADLINE_INDEX_DESC];

            if (deadlineDescription.equals("")) {
                throw new DukeException();
            }

            String doByMessage = deadlineInputs[Ui.INPUT_DEADLINE_INDEX_BY];

            tasks.add(new Deadline(deadlineDescription, doByMessage));

            ui.printAddTask(getLastTask(), getTaskCount());
        } catch (DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DEADLINE);
        } catch (IndexOutOfBoundsException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DEADLINE_BY);
        }
    }

    /**
     * Adds a todo task to the list.
     *
     * @param description Description from todo command.
     */
    public void addToDoToTasks(String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            tasks.add(new ToDo(description));

            ui.printAddTask(getLastTask(), getTaskCount());
        } catch (DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_TODO);
        }
    }

    /**
     * Marks a task in the list as done based on the index.
     *
     * @param description Description from done command.
     */
    public void markTaskAsDone(String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            int taskDoneIndex = Integer.parseInt(description) - 1;
            tasks.get(taskDoneIndex).markAsDone();
            ui.printDoneTask(tasks.get(taskDoneIndex));
        } catch (NumberFormatException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_INDEX_NOT_NUMBER);
        } catch (DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DONE);
        } catch (IndexOutOfBoundsException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_INDEX_OUT_OF_BOUNDS);
        }
    }

    private String getTasksAsNumberedList() {
        String tasksAsNumberedList = "";
        for (int i = 0; i < getTaskCount(); i++) {
            tasksAsNumberedList += System.lineSeparator() + (i + 1) + NUMBERED_LIST_SEPARATOR + getTasks().get(i);
        }
        return tasksAsNumberedList;
    }

    /**
     * Prints the tasks in the list with their corresponding numbers.
     * Numbered list starts from index 1.
     */
    public void listTasks() {
        ui.printTaskList(getTasksAsNumberedList());
    }

    /**
     * Finds the tasks in the list based on a keyword.
     * Prints the tasks found as a numbered list starting from index 1.
     *
     * @param description Description from find command.
     */
    public void findTasks(String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            ArrayList<Task> tasksFound = (ArrayList<Task>) getTasks().stream()
                    .filter(task -> task.toString().contains(description))
                    .collect(toList());

            String tasksFoundAsNumberedList = new TaskList(tasksFound).getTasksAsNumberedList();

            ui.printFoundTasks(tasksFoundAsNumberedList);
        } catch (DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_FIND);
        }
    }
}
