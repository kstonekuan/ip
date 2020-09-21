package duke;

import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

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

    public void execute(TaskList tasks, Storage storage) throws DukeException, IOException {
        switch (type) {
        case Ui.COMMAND_BYE:
            isBye = true;
            break;
        case Ui.COMMAND_LIST:
            tasks.listTasks();
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
        case Ui.COMMAND_FIND:
            tasks.findTasks(description);
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
