public class Duke {
    private static final int TASK_CAPACITY = 100;
    private static final int INPUT_RAW_INDEX_COMMAND = 0;
    private static final int INPUT_RAW_INDEX_DESC = 1;
    private static final int INPUT_DEADLINE_INDEX_DESC = 0;
    private static final int INPUT_DEADLINE_INDEX_BY = 1;
    private static final int INPUT_EVENT_INDEX_DESC = 0;
    private static final int INPUT_EVENT_INDEX_AT = 1;
    private static final String INPUT_DELIMITER = " ";

    public static void main(String[] args) {
        String inputMessage;
        Task[] tasks = new Task[TASK_CAPACITY];
        boolean isNotBye = true;

        // Greet upon starting
        TextManager.printGreetMessage();

        // Loop until the user inputs "bye"
        while(isNotBye) {
            inputMessage = TextManager.getUserInput();
            isNotBye = processInputMessage(inputMessage, tasks);
        }

        // Exit the program
        TextManager.printExitMessage();
    }

    private static boolean processInputMessage(String inputMessage, Task[] tasks) {
        String command = "";
        String description = "";

        try {
            String[] inputWords = inputMessage.split(INPUT_DELIMITER);

            command = inputWords[INPUT_RAW_INDEX_COMMAND];
            description = getDescription(inputWords);
        } catch (IndexOutOfBoundsException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_NOT_COMMAND);
        }

        return processCommand(tasks, command, description);
    }

    private static boolean processCommand(Task[] tasks, String command, String description) {
        try {
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
            default:
                throw new DukeException();
            }
        } catch (DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_NOT_COMMAND);
        }

        // Still has not exited so return true
        return true;
    }

    private static int getLastTaskIndex() {
        return Task.getTaskCount() - 1;
    }

    private static void addEventToTasks(Task[] tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }
            String[] eventInputs = description.split(TextManager.COMMAND_EVENT_AT_SEPARATOR);
            String eventDescription = eventInputs[INPUT_EVENT_INDEX_DESC];
            String eventAtMessage = eventInputs[INPUT_EVENT_INDEX_AT];

            tasks[Task.getTaskCount()] = new Event(eventDescription, eventAtMessage);

            TextManager.printAddTask(tasks[getLastTaskIndex()]);
        } catch (DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_EVENT);
        } catch (IndexOutOfBoundsException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_EVENT_AT);
        }
    }

    private static void addDeadlineToTasks(Task[] tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }
            String[] deadlineInputs = description.split(TextManager.COMMAND_DEADLINE_BY_SEPARATOR);
            String deadlineDescription = deadlineInputs[INPUT_DEADLINE_INDEX_DESC];
            String doByMessage = deadlineInputs[INPUT_DEADLINE_INDEX_BY];

            tasks[Task.getTaskCount()] = new Deadline(deadlineDescription, doByMessage);

            TextManager.printAddTask(tasks[getLastTaskIndex()]);
        } catch (DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_DEADLINE);
        } catch (IndexOutOfBoundsException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_DEADLINE_BY);
        }
    }

    private static void addToDoToTasks(Task[] tasks, String description) {
        try {
            if (description.equals("")) {
                throw new DukeException();
            }
            tasks[Task.getTaskCount()] = new ToDo(description);

            TextManager.printAddTask(tasks[getLastTaskIndex()]);
        } catch (DukeException e) {
            ErrorTextManager.printErrorMessage(ErrorTextManager.ERROR_DESCRIPTION_TODO);
        }
    }

    private static void markTaskAsDone(Task[] tasks, String description) {
        int taskDoneIndex = Integer.parseInt(description) - 1;
        tasks[taskDoneIndex].markAsDone();
        TextManager.printDoneTask(tasks[taskDoneIndex]);
    }

    private static String getDescription(String[] inputWords) {
        if (inputWords.length > INPUT_RAW_INDEX_DESC) {
            String description = inputWords[INPUT_RAW_INDEX_DESC];
            for (int i = 2; i < inputWords.length; i++) {
                description = String.join(INPUT_DELIMITER, description, inputWords[i]);
            }
            return description;
        } else {
            return "";
        }

    }
}
