package duke;

import duke.task.TaskList;

import java.io.IOException;

/**
 * Represents a command to be executed. A <code>Command</code> object corresponds to
 * the command's type and description along with whether it is the bye command
 */
public class Command {
    private String type;
    private String description;
    private boolean isBye;

    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DEADLINE_BY_SEPARATOR = " /by ";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_EVENT_AT_SEPARATOR = " /at ";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_FIND = "find";

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
        case COMMAND_BYE:
            isBye = true;
            break;
        case COMMAND_LIST:
            tasks.listTasks();
            break;
        case COMMAND_DONE:
            tasks.markTaskAsDone(description);
            break;
        case COMMAND_TODO:
            tasks.addToDoToTasks(description);
            break;
        case COMMAND_DEADLINE:
            String[] deadlineInputs = description.split(COMMAND_DEADLINE_BY_SEPARATOR);
            tasks.addDeadlineToTasks(deadlineInputs);
            break;
        case COMMAND_EVENT:
            String[] eventInputs = description.split(COMMAND_EVENT_AT_SEPARATOR);
            tasks.addEventToTasks(eventInputs);
            break;
        case COMMAND_DELETE:
            tasks.deleteFromTasks(description);
            break;
        case COMMAND_FIND:
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
