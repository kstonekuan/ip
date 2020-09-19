package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.ErrorUi;
import duke.ui.Ui;

import java.util.ArrayList;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        String inputMessage;
        ArrayList<Task> tasks = Task.tasks;
        boolean isNotBye = true;

        try {
            Database.loadTasks(tasks);
        } catch (IOException | DukeException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DATA_FILE);
            return;
        }

        // Greet upon starting
        Ui.printGreetMessage();

        // Loop until the user inputs "bye"
        while (isNotBye) {
            inputMessage = Ui.getUserInput();
            isNotBye = processInputMessage(inputMessage, tasks);
        }

        // Exit the program
        Ui.printExitMessage();
    }

    private static boolean processInputMessage(String inputMessage, ArrayList<Task> tasks) {
        String command = "";
        String description = "";

        try {
            String[] inputWords = inputMessage.split(Ui.INPUT_DELIMITER);

            command = inputWords[Ui.INPUT_RAW_INDEX_COMMAND];
            description = getDescription(inputWords);

            return processCommand(tasks, command, description);
        } catch (IndexOutOfBoundsException | DukeException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_NOT_COMMAND);
        } catch (IOException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DATA_FILE);
        }

        return true; // Still has not exited so return true
    }

    private static boolean processCommand(ArrayList<Task> tasks, String command, String description)
            throws DukeException, IOException {
        switch (command) {
        case Ui.COMMAND_BYE:
            return false; // Return false immediately to end the loop
        case Ui.COMMAND_LIST:
            Ui.printTaskList(tasks);
            break;
        case Ui.COMMAND_DONE:
            markTaskAsDone(tasks, description);
            break;
        case Ui.COMMAND_TODO:
            addToDoToTasks(tasks, description);
            break;
        case Ui.COMMAND_DEADLINE:
            addDeadlineToTasks(tasks, description);
            break;
        case Ui.COMMAND_EVENT:
            addEventToTasks(tasks, description);
            break;
        case Ui.COMMAND_DELETE:
            deleteFromTasks(tasks, description);
            break;
        default:
            throw new DukeException();
        }

        Database.saveTasks(tasks);
        return true; // Still has not exited so return true
    }

    private static void deleteFromTasks(ArrayList<Task> tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            int taskDeleteIndex = Integer.parseInt(description) - 1;
            Ui.printDeleteTask(tasks.get(taskDeleteIndex));
            tasks.remove(taskDeleteIndex);
        } catch (NumberFormatException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_INDEX_NOT_NUMBER);
        } catch (DukeException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DELETE);
        } catch (IndexOutOfBoundsException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_INDEX_OUT_OF_BOUNDS);
        }
    }

    private static int getLastTaskIndex(ArrayList<Task> tasks) {
        return Task.getTaskCount() - 1;
    }

    private static void addEventToTasks(ArrayList<Task> tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            String[] eventInputs = description.split(Ui.COMMAND_EVENT_AT_SEPARATOR);
            String eventDescription = eventInputs[Ui.INPUT_EVENT_INDEX_DESC];
            String eventAtMessage = eventInputs[Ui.INPUT_EVENT_INDEX_AT];

            tasks.add(new Event(eventDescription, eventAtMessage));

            Ui.printAddTask(tasks.get(getLastTaskIndex(tasks)));
        } catch (DukeException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_EVENT);
        } catch (IndexOutOfBoundsException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_EVENT_AT);
        }
    }

    private static void addDeadlineToTasks(ArrayList<Task> tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            String[] deadlineInputs = description.split(Ui.COMMAND_DEADLINE_BY_SEPARATOR);
            String deadlineDescription = deadlineInputs[Ui.INPUT_DEADLINE_INDEX_DESC];
            String doByMessage = deadlineInputs[Ui.INPUT_DEADLINE_INDEX_BY];

            tasks.add(new Deadline(deadlineDescription, doByMessage));

            Ui.printAddTask(tasks.get(getLastTaskIndex(tasks)));
        } catch (DukeException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DEADLINE);
        } catch (IndexOutOfBoundsException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DEADLINE_BY);
        }
    }

    private static void addToDoToTasks(ArrayList<Task> tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            tasks.add(new ToDo(description));

            Ui.printAddTask(tasks.get(getLastTaskIndex(tasks)));
        } catch (DukeException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_TODO);
        }
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            int taskDoneIndex = Integer.parseInt(description) - 1;
            tasks.get(taskDoneIndex).markAsDone();
            Ui.printDoneTask(tasks.get(taskDoneIndex));
        } catch (NumberFormatException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_INDEX_NOT_NUMBER);
        } catch (DukeException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_DONE);
        } catch (IndexOutOfBoundsException e) {
            ErrorUi.printErrorMessage(ErrorUi.ERROR_DESCRIPTION_INDEX_OUT_OF_BOUNDS);
        }
    }

    private static String getDescription(String[] inputWords) {
        if (inputWords.length > Ui.INPUT_RAW_INDEX_DESC) {
            String description = inputWords[Ui.INPUT_RAW_INDEX_DESC];
            for (int i = 2; i < inputWords.length; i++) {
                description = String.join(Ui.INPUT_DELIMITER, description, inputWords[i]);
            }
            return description;
        } else {
            return "";
        }

    }
}
