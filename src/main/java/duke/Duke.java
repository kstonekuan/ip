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

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    private void start() {
        ui.printGreetMessage();
    }

    private void exit() {
        ui.printExitMessage();
        System.exit(0);
    }

    private void runCommandLoopUntilByeCommand() {
        boolean isBye = false;

        while (!isBye) {
            String inputMessage = ui.getUserInput();
            isBye = processInputMessage(inputMessage);
        }
    }

    private boolean processInputMessage(String inputMessage) {
        Command command = new Command();
        try {
            command = Parser.parseCommand(inputMessage);
            command.execute(tasks, storage);
        } catch (IndexOutOfBoundsException | DukeException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_NOT_COMMAND);
        } catch (IOException e) {
            errorUi.printErrorMessage(ErrorUi.ERROR_DATA_LOAD);
        }

        return command.isBye();
    }



}
