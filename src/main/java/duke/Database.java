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
    private static boolean FILE_EXISTS = Files.exists(FILE_PATH);

    public Database() throws IOException {
    }

    public static void saveTasks(Task[] tasks) throws IOException {
        ArrayList<String> tasksAsStringList = new ArrayList<>();
        for (int i = 0; i < Task.getTaskCount(); i++) {
            tasksAsStringList.add(tasks[i].toString());
        }
        System.out.println(tasksAsStringList.get(0));
        Files.write(FILE_PATH, tasksAsStringList);
    }

    public static void loadTasks(Task[] tasks) throws IOException, DukeException {
        if (!FILE_EXISTS) {
            Files.createDirectories(DIR_DATA_PATH);
            Files.createFile(FILE_PATH);
            FILE_EXISTS = Files.exists(FILE_PATH);
        }
        ArrayList<String> tasksAsStringList = (ArrayList<String>) Files.readAllLines(FILE_PATH);
        for (String taskAsString: tasksAsStringList) {
            String description = taskAsString.substring(7);
            String taskType = taskAsString.substring(0, 3);
            String statusIcon = taskAsString.substring(3, 6);
            Task task;
            switch (taskType) {
            case ToDo.TODO_ICON:
                task = new ToDo(description);
                break;
            case Deadline.DEADLINE_ICON:
                String[] deadlineInputs = description.split(" \\(by: ");
                String deadlineDescription = deadlineInputs[TextManager.INPUT_DEADLINE_INDEX_DESC];
                String doByMessage = deadlineInputs[TextManager.INPUT_DEADLINE_INDEX_BY];
                task = new Deadline(deadlineDescription, doByMessage.substring(0, doByMessage.length() - 1));
                break;
            case Event.EVENT_ICON:
                String[] eventInputs = description.split(" \\(at: ");
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
