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
        int taskCount = 0;

        // Greet upon starting
        textManager.printGreetMessage();

        // Loop until the user inputs "bye"
        do {
            inputMessage = in.nextLine(); // get the user input
            outputMessage = ""; // reset the output message

            if (inputMessage.equals("list")) {
                // List the tasks with numbers
                for (int i = 0; i < taskCount; i++) {
                    outputMessage += (i + 1) + "." + tasks[i].getStatusIcon()
                            + " " + tasks[i].getDescription() + "\n";
                }
            } else if (inputMessage.split(" ")[0].equals("done")) {
                // Mark the task as done
                int taskDoneIndex = Integer.parseInt(inputMessage.split(" ")[1]) - 1;
                tasks[taskDoneIndex].markAsDone();

                outputMessage = "Nice! I've marked this task as done:\n  " + tasks[taskDoneIndex].getStatusIcon()
                        + " " + tasks[taskDoneIndex].getDescription() + "\n";
            } else {
                // Add the task to the list
                tasks[taskCount++] = new Task(inputMessage);
                outputMessage = "added: " + inputMessage + "\n";
            }

            textManager.printMessage(outputMessage);
        } while (!outputMessage.equals("added: bye\n"));

        // Exit the program
        textManager.printExitMessage();
    }
}
