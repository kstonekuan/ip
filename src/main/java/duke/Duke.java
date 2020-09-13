package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.textmanager.ErrorTextManager;
import duke.textmanager.TextManager;

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
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DATA_FILE);
            return;
        }

        // Greet upon starting
        TextManager.printGreetMessage();

        // Loop until the user inputs "bye"
        while (isNotBye) {
            inputMessage = TextManager.getUserInput();
            isNotBye = processInputMessage(inputMessage, tasks);
        }

        // Exit the program
        TextManager.printExitMessage();
    }

    private static boolean processInputMessage(String inputMessage, ArrayList<Task> tasks) {
        String command = "";
        String description = "";

        try {
            String[] inputWords = inputMessage.split(TextManager.INPUT_DELIMITER);

            command = inputWords[TextManager.INPUT_RAW_INDEX_COMMAND];
            description = getDescription(inputWords);

            return processCommand(tasks, command, description);
        } catch (IndexOutOfBoundsException | DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_NOT_COMMAND);
        } catch (IOException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DATA_FILE);
        }

        return true; // Still has not exited so return true
    }

    private static boolean processCommand(ArrayList<Task> tasks, String command, String description)
            throws DukeException, IOException {
        switch (command) {
        case TextManager.COMMAND_BYE:
            return false; // Return false immediately to end the loop
        case TextManager.COMMAND_LIST:
            TextManager.printTaskList(tasks);
            break;
        case TextManager.COMMAND_DONE:
            markTaskAsDone(tasks, description);
            break;
        case TextManager.COMMAND_TODO:
            addToDoToTasks(tasks, description);
            break;
        case TextManager.COMMAND_DEADLINE:
            addDeadlineToTasks(tasks, description);
            break;
        case TextManager.COMMAND_EVENT:
            addEventToTasks(tasks, description);
            break;
        case TextManager.COMMAND_DELETE:
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
            TextManager.printDeleteTask(tasks.get(taskDeleteIndex));
            tasks.remove(taskDeleteIndex);
        } catch (NumberFormatException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_INDEX_NOT_NUMBER);
        } catch (DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_DELETE);
        } catch (IndexOutOfBoundsException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_INDEX_OUT_OF_BOUNDS);
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

            String[] eventInputs = description.split(TextManager.COMMAND_EVENT_AT_SEPARATOR);
            String eventDescription = eventInputs[TextManager.INPUT_EVENT_INDEX_DESC];
            String eventAtMessage = eventInputs[TextManager.INPUT_EVENT_INDEX_AT];

            tasks.add(new Event(eventDescription, eventAtMessage));

            TextManager.printAddTask(tasks.get(getLastTaskIndex(tasks)));
        } catch (DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_EVENT);
        } catch (IndexOutOfBoundsException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_EVENT_AT);
        }
    }

    private static void addDeadlineToTasks(ArrayList<Task> tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            String[] deadlineInputs = description.split(TextManager.COMMAND_DEADLINE_BY_SEPARATOR);
            String deadlineDescription = deadlineInputs[TextManager.INPUT_DEADLINE_INDEX_DESC];
            String doByMessage = deadlineInputs[TextManager.INPUT_DEADLINE_INDEX_BY];

            tasks.add(new Deadline(deadlineDescription, doByMessage));

            TextManager.printAddTask(tasks.get(getLastTaskIndex(tasks)));
        } catch (DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_DEADLINE);
        } catch (IndexOutOfBoundsException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_DEADLINE_BY);
        }
    }

    private static void addToDoToTasks(ArrayList<Task> tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            tasks.add(new ToDo(description));

            TextManager.printAddTask(tasks.get(getLastTaskIndex(tasks)));
        } catch (DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_TODO);
        }
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }

            int taskDoneIndex = Integer.parseInt(description) - 1;
            tasks.get(taskDoneIndex).markAsDone();
            TextManager.printDoneTask(tasks.get(taskDoneIndex));
        } catch (NumberFormatException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_INDEX_NOT_NUMBER);
        } catch (DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_DONE);
        } catch (IndexOutOfBoundsException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_INDEX_OUT_OF_BOUNDS);
        }
    }

    private static String getDescription(String[] inputWords) {
        if (inputWords.length > TextManager.INPUT_RAW_INDEX_DESC) {
            String description = inputWords[TextManager.INPUT_RAW_INDEX_DESC];
            for (int i = 2; i < inputWords.length; i++) {
                description = String.join(TextManager.INPUT_DELIMITER, description, inputWords[i]);
            }
            return description;
        } else {
            return "";
        }

    }
}
