package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.textmanager.TextManager;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Database {
    private static final String DIR_CURRENT_PATH = System.getProperty("user.dir");
    // inserts correct file path separator on *nix and Windows
    // works on *nix
    // works on Windows
    private static final Path DIR_DATA_PATH = Paths.get(DIR_CURRENT_PATH, "data");
    private static final Path FILE_PATH = Paths.get(DIR_CURRENT_PATH, "data", "duke.text");
    private static final String LOAD_DEADLINE_BY_SEPARATOR = " \\(by: ";
    public static final String LOAD_EVENT_AT_SEPARATOR = " \\(at: ";
    public static final int LOAD_TASK_DESCRIPTION_INDEX = 7;
    public static final int LOAD_TASK_TYPE_INDEX_START = 0;
    public static final int LOAD_TASK_TYPE_INDEX_END = 3;
    public static final int LOAD_TASK_STATUS_INDEX_START = 3;
    public static final int LOAD_TASK_STATUS_INDEX_END = 6;
    private static boolean fileExists = Files.exists(FILE_PATH);

    public Database() throws IOException {
    }

    public static void saveTasks(Task[] tasks) throws IOException {
        ArrayList<String> tasksAsStringList = new ArrayList<>();
        for (int i = 0; i < Task.getTaskCount(); i++) {
            tasksAsStringList.add(tasks[i].toString());
        }
        Files.write(FILE_PATH, tasksAsStringList);
    }

    public static void loadTasks(Task[] tasks) throws IOException, DukeException {
        if (!fileExists) {
            Files.createDirectories(DIR_DATA_PATH);
            Files.createFile(FILE_PATH);
            fileExists = Files.exists(FILE_PATH);
        }
        ArrayList<String> tasksAsStringList = (ArrayList<String>) Files.readAllLines(FILE_PATH);
        for (String taskAsString: tasksAsStringList) {
            String description = taskAsString.substring(LOAD_TASK_DESCRIPTION_INDEX);
            String taskType = taskAsString.substring(LOAD_TASK_TYPE_INDEX_START, LOAD_TASK_TYPE_INDEX_END);
            String statusIcon = taskAsString.substring(LOAD_TASK_STATUS_INDEX_START, LOAD_TASK_STATUS_INDEX_END);
            Task task;
            switch (taskType) {
            case ToDo.TODO_ICON:
                task = new ToDo(description);
                break;
            case Deadline.DEADLINE_ICON:
                String[] deadlineInputs = description.split(LOAD_DEADLINE_BY_SEPARATOR);
                String deadlineDescription = deadlineInputs[TextManager.INPUT_DEADLINE_INDEX_DESC];
                String doByMessage = deadlineInputs[TextManager.INPUT_DEADLINE_INDEX_BY];
                task = new Deadline(deadlineDescription, doByMessage.substring(0, doByMessage.length() - 1));
                break;
            case Event.EVENT_ICON:
                String[] eventInputs = description.split(LOAD_EVENT_AT_SEPARATOR);
                String eventDescription = eventInputs[TextManager.INPUT_EVENT_INDEX_DESC];
                String eventAtMessage = eventInputs[TextManager.INPUT_EVENT_INDEX_AT];
                task = new Event(eventDescription, eventAtMessage.substring(0, eventAtMessage.length() - 1));
                break;
            default:
                throw new DukeException();
            }
            if (statusIcon.equals(Task.TICK_ICON)) {
                task.markAsDone();
            }
            tasks[Task.getTaskCount() - 1] = task;
        }
    }
}
