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
        String[] inputWords = inputMessage.split(INPUT_DELIMITER);

        String command = inputWords[INPUT_RAW_INDEX_COMMAND];
        String description = getDescription(inputWords);

        Task taskDone;
        Task taskAdded;

        switch (command) {
        case TextManager.COMMAND_BYE:
            return false; // Return false immediately to end the loop
        case TextManager.COMMAND_LIST:
            TextManager.printTaskList(tasks);
            break;
        case TextManager.COMMAND_DONE:
            taskDone = markTaskAsDone(tasks, description);
            TextManager.printDoneTask(taskDone);
            break;
        case TextManager.COMMAND_TODO:
            taskAdded = addToDoToTasks(tasks, description);
            TextManager.printAddTask(taskAdded);
            break;
        case TextManager.COMMAND_DEADLINE:
            taskAdded = addDeadlineToTasks(tasks, description);
            TextManager.printAddTask(taskAdded);
            break;
        case TextManager.COMMAND_EVENT:
            taskAdded = addEventToTasks(tasks, description);
            TextManager.printAddTask(taskAdded);
            break;
        }

        // Still has not exited so return true
        return true;
    }

    private static Task addEventToTasks(Task[] tasks, String description) {
        String[] eventInputs = description.split(TextManager.COMMAND_EVENT_AT_SEPARATOR);
        String eventDescription = eventInputs[INPUT_EVENT_INDEX_DESC];
        String eventAtMessage = eventInputs[INPUT_EVENT_INDEX_AT];

        tasks[Task.getTaskCount()] = new Event(eventDescription, eventAtMessage);
        return tasks[Task.getTaskCount() - 1];
    }

    private static Task addDeadlineToTasks(Task[] tasks, String description) {
        String[] deadlineInputs = description.split(TextManager.COMMAND_DEADLINE_BY_SEPARATOR);
        String deadlineDescription = deadlineInputs[INPUT_DEADLINE_INDEX_DESC];
        String doByMessage = deadlineInputs[INPUT_DEADLINE_INDEX_BY];

        tasks[Task.getTaskCount()] = new Deadline(deadlineDescription, doByMessage);
        return tasks[Task.getTaskCount() - 1];
    }

    private static Task addToDoToTasks(Task[] tasks, String description) {
        tasks[Task.getTaskCount()] = new ToDo(description);
        return tasks[Task.getTaskCount() - 1];
    }

    private static Task markTaskAsDone(Task[] tasks, String description) {
        int taskDoneIndex = Integer.parseInt(description) - 1;
        tasks[taskDoneIndex].markAsDone();
        return tasks[taskDoneIndex];
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
