package duke;

import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Command {
    private String type;
    private String description;

    public Command(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public boolean execute(TaskList tasks, Storage storage) throws DukeException, IOException {
        switch (type) {
        case Ui.COMMAND_BYE:
            return false; // Return false immediately to end the loop
        case Ui.COMMAND_LIST:
            Ui ui = new Ui();
            ui.printTaskList(tasks.getTasks(), tasks.getTaskCount());
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
        return true; // Still has not exited so return true
    }
}
