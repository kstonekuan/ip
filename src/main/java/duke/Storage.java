package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private static final String DIR_CURRENT_PATH = System.getProperty("user.dir");
    private static final Path DIR_DATA_PATH = Paths.get(DIR_CURRENT_PATH, "data");
    private Path filePath;
    private static final String LOAD_DEADLINE_BY_SEPARATOR = " \\(by: ";
    public static final String LOAD_EVENT_AT_SEPARATOR = " \\(at: ";
    public static final int LOAD_TASK_DESCRIPTION_INDEX = 7;
    public static final int LOAD_TASK_TYPE_INDEX_START = 0;
    public static final int LOAD_TASK_TYPE_INDEX_END = 3;
    public static final int LOAD_TASK_STATUS_INDEX_START = 3;
    public static final int LOAD_TASK_STATUS_INDEX_END = 6;

    public Storage(String filePath) {
        this.filePath = Paths.get(DIR_CURRENT_PATH, filePath.split("/"));
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        ArrayList<String> tasksAsStringList = new ArrayList<>();

        for (Task task: tasks) {
            tasksAsStringList.add(task.toString());
        }

        Files.write(filePath, tasksAsStringList);
    }

    public ArrayList<Task> load() throws IOException, DukeException {
        createFileIfNotExists();

        ArrayList<String> tasksAsStringList = (ArrayList<String>) Files.readAllLines(filePath);
        ArrayList<Task> tasks = new ArrayList<>();

        for (String taskAsString: tasksAsStringList) {
            String description = taskAsString.substring(LOAD_TASK_DESCRIPTION_INDEX);
            String taskType = taskAsString.substring(LOAD_TASK_TYPE_INDEX_START, LOAD_TASK_TYPE_INDEX_END);
            String statusIcon = taskAsString.substring(LOAD_TASK_STATUS_INDEX_START, LOAD_TASK_STATUS_INDEX_END);
            Task task;

            task = getTaskFromString(description, taskType);

            loadDone(statusIcon, task);

            tasks.add(task);
        }

        return tasks;
    }

    private static Task getTaskFromString(String description, String taskType) throws DukeException {
        switch (taskType) {
        case ToDo.TODO_ICON:
            return loadToDo(description);
        case Deadline.DEADLINE_ICON:
            return loadDeadline(description);
        case Event.EVENT_ICON:
            return loadEvent(description);
        default:
            throw new DukeException();
        }
    }

    private static void loadDone(String statusIcon, Task task) {
        if (statusIcon.equals(Task.TICK_ICON)) {
            task.markAsDone();
        }
    }

    private void createFileIfNotExists() throws IOException {
        boolean fileExists = Files.exists(filePath);
        if (!fileExists) {
            Files.createDirectories(DIR_DATA_PATH);
            Files.createFile(filePath);
        }
    }

    private static Task loadToDo(String description) {
        Task task;
        task = new ToDo(description);
        return task;
    }

    private static Task loadEvent(String description) {
        Task task;
        String[] eventInputs = description.split(LOAD_EVENT_AT_SEPARATOR);
        String eventDescription = eventInputs[Ui.INPUT_EVENT_INDEX_DESC];
        String eventAtMessageWithBracket = eventInputs[Ui.INPUT_EVENT_INDEX_AT];
        String eventAtMessage = eventAtMessageWithBracket.substring(0, eventAtMessageWithBracket.length() - 1);
        task = new Event(eventDescription, eventAtMessage);
        return task;
    }

    private static Task loadDeadline(String description) {
        Task task;
        String[] deadlineInputs = description.split(LOAD_DEADLINE_BY_SEPARATOR);
        String deadlineDescription = deadlineInputs[Ui.INPUT_DEADLINE_INDEX_DESC];
        String doByMessageWithBracket = deadlineInputs[Ui.INPUT_DEADLINE_INDEX_BY];
        String doByMessage = doByMessageWithBracket.substring(0, doByMessageWithBracket.length() - 1);
        task = new Deadline(deadlineDescription, doByMessage);
        return task;
    }
}
