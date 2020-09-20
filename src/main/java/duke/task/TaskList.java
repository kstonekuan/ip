package duke.task;

import duke.DukeException;
import duke.ui.ErrorUi;
import duke.ui.Ui;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

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

    private int getLastTaskIndex() {
        return tasks.size() - 1;
    }

    public int getTaskCount() {
        return tasks.size();
    }

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

    public void addEventToTasks(String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            String[] eventInputs = description.split(Ui.COMMAND_EVENT_AT_SEPARATOR);
            String eventDescription = eventInputs[Ui.INPUT_EVENT_INDEX_DESC];
            String eventAtMessage = eventInputs[Ui.INPUT_EVENT_INDEX_AT];

            tasks.add(new Event(eventDescription, eventAtMessage));

            ui.printAddTask(tasks.get(getLastTaskIndex()), getTaskCount());
        } catch (DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_EVENT);
        } catch (IndexOutOfBoundsException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_EVENT_AT);
        }
    }

    public void addDeadlineToTasks(String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            String[] deadlineInputs = description.split(Ui.COMMAND_DEADLINE_BY_SEPARATOR);
            String deadlineDescription = deadlineInputs[Ui.INPUT_DEADLINE_INDEX_DESC];
            String doByMessage = deadlineInputs[Ui.INPUT_DEADLINE_INDEX_BY];

            tasks.add(new Deadline(deadlineDescription, doByMessage));

            ui.printAddTask(tasks.get(getLastTaskIndex()), getTaskCount());
        } catch (DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DEADLINE);
        } catch (IndexOutOfBoundsException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DEADLINE_BY);
        }
    }

    public void addToDoToTasks(String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            tasks.add(new ToDo(description));

            ui.printAddTask(tasks.get(getLastTaskIndex()), getTaskCount());
        } catch (DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_TODO);
        }
    }

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

    public void listTasks() {
        ui.printTaskList(getTasksAsNumberedList());
    }

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
