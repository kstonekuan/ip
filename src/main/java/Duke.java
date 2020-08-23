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

        textManager.printGreetMessage();

        do {
            textManager.printMessage(line);
            line = in.nextLine() + "\n";
        } while (!line.equals("bye\n"));

        textManager.printExitMessage();
    }
}
