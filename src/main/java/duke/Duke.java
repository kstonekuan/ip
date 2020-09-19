package duke;

import duke.task.*;
import duke.ui.ErrorUi;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private ErrorUi errorUi;

    public Duke(String filePath) {
        ui = new Ui();
        errorUi = new ErrorUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            //ui.showLoadingError();
            errorUi.printErrorMessage(ErrorUi.ERROR_DATA_FILE);
            tasks = new TaskList();
        }
    }

    public void run() {
        String inputMessage;
        boolean isNotBye = true;

        // Greet upon starting
        ui.printGreetMessage();

        // Loop until the user inputs "bye"
        while (isNotBye) {
            inputMessage = ui.getUserInput();
            isNotBye = processInputMessage(inputMessage);
        }

        // Exit the program
        ui.printExitMessage();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    private boolean processInputMessage(String inputMessage) {
        try {
            Command command = Parser.parseCommand(inputMessage);

            return executeCommand(command);
        } catch (IndexOutOfBoundsException | DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_NOT_COMMAND);
        } catch (IOException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DATA_FILE);
        }

        return true; // Still has not exited so return true
    }

    private boolean executeCommand(Command command)
            throws DukeException, IOException {
        switch (command.getType()) {
        case Ui.COMMAND_BYE:
            return false; // Return false immediately to end the loop
        case Ui.COMMAND_LIST:
            ui.printTaskList(tasks.getTasks(), tasks.getTaskCount());
            break;
        case Ui.COMMAND_DONE:
            tasks.markTaskAsDone(command.getDescription());
            break;
        case Ui.COMMAND_TODO:
            tasks.addToDoToTasks(command.getDescription());
            break;
        case Ui.COMMAND_DEADLINE:
            tasks.addDeadlineToTasks(command.getDescription());
            break;
        case Ui.COMMAND_EVENT:
            tasks.addEventToTasks(command.getDescription());
            break;
        case Ui.COMMAND_DELETE:
            tasks.deleteFromTasks(command.getDescription());
            break;
        default:
            throw new DukeException();
        }

        storage.save(tasks.getTasks());
        return true; // Still has not exited so return true
    }

}
