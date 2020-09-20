package duke;

import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to be executed. A <code>Command</code> object corresponds to
 * the command's type and description along with whether it is the bye command
 */
public class Command {
    private String type;
    private String description;
    private boolean isBye;

    public Command() {
        isBye = false;
    }

    public Command(String type, String description) {
        this.type = type;
        this.description = description;
        isBye = false;
    }

    /**
     * Execute command based on type
     *
     * @param tasks List of tasks.
     * @param storage Storage object to save tasks.
     * @throws DukeException  If there is no such command type.
     * @throws IOException If there is an error saving to storage.
     */
    public void execute(TaskList tasks, Storage storage) throws DukeException, IOException {
        switch (type) {
        case Ui.COMMAND_BYE:
            isBye = true;
            break;
        case Ui.COMMAND_LIST:
            Ui ui = new Ui();
            ui.printTaskList(tasks);
            break;
        case Ui.COMMAND_DONE:
            tasks.markTaskAsDone(description);
            break;
        case Ui.COMMAND_TODO:
            tasks.addToDoToTasks(description);
            break;
        case Ui.COMMAND_DEADLINE:
            tasks.addDeadlineToTasks(description);
            break;
        case Ui.COMMAND_EVENT:
            tasks.addEventToTasks(description);
            break;
        case Ui.COMMAND_DELETE:
            tasks.deleteFromTasks(description);
            break;
        default:
            throw new DukeException();
        }

        storage.save(tasks.getTasks());
    }

    public boolean isBye() {
        return isBye;
    }
}
