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
        String line = null;
        Scanner in = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        // Greet upon starting
        textManager.printGreetMessage();

        // Loop until the user inputs "bye"
        do {
            // Print previous line, nothing if first iteration
            textManager.printMessage(line);
            line = in.nextLine();

            if (line.equals("list")) {
                // List the tasks with numbers
                line = "";
                for (int i = 0; i < taskCount; i++) {
                    line += (i + 1) + ". " + tasks[i] + "\n";
                }
            } else {
                // Add the task to the list
                tasks[taskCount++] = line;
                line = "added: " + line + "\n";
            }
        } while (!line.equals("added: bye\n"));

        // Exit the program
        textManager.printExitMessage();
    }
}
