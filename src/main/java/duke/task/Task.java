package duke.task;

import java.util.ArrayList;

public class Task {
    private static final String TICK_ICON = "[\u2713]";
    private static final String CROSS_ICON = "[\u2718]";
    public static ArrayList<Task> tasks = new ArrayList<>();

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? TICK_ICON : CROSS_ICON); //return tick or X symbols
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public static int getTaskCount() {
        return tasks.size();
    }
}