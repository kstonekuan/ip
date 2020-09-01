import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Duke's Logo
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        TextManager textManager = new TextManager();
        String inputMessage;
        String outputMessage;
        Scanner in = new Scanner(System.in);
        Task[] tasks = new Task[100];

        // Greet upon starting
        textManager.printGreetMessage();

        boolean hasNotExited = true;

        // Loop until the user inputs "bye"
        while(hasNotExited) {
            inputMessage = in.nextLine(); // get the user input
            outputMessage = ""; // reset the output message

            String[] inputWords = inputMessage.split(" ");
            String command = inputWords[0];
            String description = "";
            if (inputWords.length > 1) {
                description = inputWords[1];
                for (int i = 2; i < inputWords.length; i++) {
                    description = String.join(" ", description, inputWords[i]);
                }
            }

            switch (command) {
            case "bye":
                hasNotExited = false;
                break;
            case "list":
                // List the tasks with numbers
                outputMessage = "Here are the tasks in your list:" + System.lineSeparator();
                for (int i = 0; i < Task.getTaskCount(); i++) {
                    outputMessage += (i + 1) + "." + tasks[i] + System.lineSeparator();
                }
                textManager.printMessage(outputMessage);
                break;
            case "done":
                // Mark the task as done
                int taskDoneIndex = Integer.parseInt(description) - 1;
                tasks[taskDoneIndex].markAsDone();

                outputMessage = "Nice! I've marked this task as done:\n  " + tasks[taskDoneIndex] + "\n";
                textManager.printMessage(outputMessage);
                break;
            case "todo":
                // Add the task to the list
                tasks[Task.getTaskCount()] = new ToDo(description);
                outputMessage = "Got it. I've added this task:\n  " + tasks[Task.getTaskCount() - 1]
                        + "\nNow you have " + Task.getTaskCount() + " tasks in the list.\n";
                textManager.printMessage(outputMessage);
                break;
            case "deadline":
                // Add the task to the list
                String[] deadlineInputs = description.split(" /by ");
                String deadlineDescription = deadlineInputs[0];
                String doByMessage = deadlineInputs[1];
                tasks[Task.getTaskCount()] = new Deadline(deadlineDescription, doByMessage);
                outputMessage = "Got it. I've added this task:\n  " + tasks[Task.getTaskCount() - 1]
                        + "\nNow you have " + Task.getTaskCount() + " tasks in the list.\n";
                textManager.printMessage(outputMessage);
                break;
            case "event":
                // Add the task to the list
                String[] eventInputs = description.split(" /at ");
                String eventDescription = eventInputs[0];
                String eventAtMessage = eventInputs[1];
                tasks[Task.getTaskCount()] = new Event(eventDescription, eventAtMessage);
                outputMessage = "Got it. I've added this task:\n  " + tasks[Task.getTaskCount() - 1]
                        + "\nNow you have " + Task.getTaskCount() + " tasks in the list.\n";
                textManager.printMessage(outputMessage);
                break;
            }
        }

        // Exit the program
        textManager.printExitMessage();
    }
}
