package duke.task;

import duke.DukeException;
import duke.ui.ErrorUi;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents a list of tasks. A <code>TaskList</code> object corresponds to
 * a list of tasks, a Ui object and an ErrorUi object.
 */
public class TaskList {

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
     * @param description Description from event command.
     */
    public void addEventToTasks(String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            String[] eventInputs = description.split(Ui.COMMAND_EVENT_AT_SEPARATOR);
            String eventDescription = eventInputs[Ui.INPUT_EVENT_INDEX_DESC];
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
     * @param description Description from deadline command.
     */
    public void addDeadlineToTasks(String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            String[] deadlineInputs = description.split(Ui.COMMAND_DEADLINE_BY_SEPARATOR);
            String deadlineDescription = deadlineInputs[Ui.INPUT_DEADLINE_INDEX_DESC];
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
}
