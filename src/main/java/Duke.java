import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String inputMessage;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];
        boolean isNotBye = true;

        // Greet upon starting
        TextManager.printGreetMessage();

        // Loop until the user inputs "bye"
        while(isNotBye) {
            inputMessage = in.nextLine(); // get the user input
            isNotBye = processInputMessage(inputMessage, tasks);
        }

        // Exit the program
        TextManager.printExitMessage();
    }

    private static boolean processInputMessage(String inputMessage, Task[] tasks) {
        String[] inputWords = inputMessage.split(" ");
        String command = inputWords[0];
        String description = getDescription(inputWords);
        Task taskDone;
        Task taskAdded;

        switch (command) {
        case "bye":
            return false; // Return false immediately to end the loop
        case "list":
            TextManager.printTaskList(tasks);
            break;
        case "done":
            taskDone = markTaskAsDone(tasks, description);
            TextManager.printDoneTask(taskDone);
            break;
        case "todo":
            taskAdded = addToDoToTasks(tasks, description);
            TextManager.printAddTask(taskAdded);
            break;
        case "deadline":
            taskAdded = addDeadlineToTasks(tasks, description);
            TextManager.printAddTask(taskAdded);
            break;
        case "event":
            taskAdded = addEventToTasks(tasks, description);
            TextManager.printAddTask(taskAdded);
            break;
        }

        // Still has not exited so return true
        return true;
    }

    private static Task addEventToTasks(Task[] tasks, String description) {
        String[] eventInputs = description.split(" /at ");
        String eventDescription = eventInputs[0];
        String eventAtMessage = eventInputs[1];

        tasks[Task.getTaskCount()] = new Event(eventDescription, eventAtMessage);
        return tasks[Task.getTaskCount() - 1];
    }

    private static Task addDeadlineToTasks(Task[] tasks, String description) {
        String[] deadlineInputs = description.split(" /by ");
        String deadlineDescription = deadlineInputs[0];
        String doByMessage = deadlineInputs[1];

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
        String description = "";
        if (inputWords.length > 1) {
            description = inputWords[1];
            for (int i = 2; i < inputWords.length; i++) {
                description = String.join(" ", description, inputWords[i]);
            }
        }
        return description;
    }
}
