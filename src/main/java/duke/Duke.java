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
            errorUi.printErrorMessage(ErrorUi.ERROR_DATA_LOAD);
            tasks = new TaskList();
        }
    }

    public void run() {
        start();
        runCommandLoopUntilByeCommand();
        exit();
    }

    private void runCommandLoopUntilByeCommand() {
        boolean isNotBye = true;

        while (isNotBye) {
            String inputMessage = ui.getUserInput();
            isNotBye = processInputMessage(inputMessage);
        }
    }

    private void exit() {
        ui.printExitMessage();
        System.exit(0);
    }

    private void start() {
        ui.printGreetMessage();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    private boolean processInputMessage(String inputMessage) {
        try {
            Command command = Parser.parseCommand(inputMessage);
            return command.execute(tasks, storage);
        } catch (IndexOutOfBoundsException | DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_NOT_COMMAND);
        } catch (IOException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DATA_LOAD);
        }

        return true; // Still has not exited so return true
    }



}
