package duke.task;

public class Task {
    public static final String TICK_ICON = "[\u2713]";
    public static final String CROSS_ICON = "[\u2718]";
    protected String description;
    protected boolean isDone;
    protected static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        taskCount++;
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
        return taskCount;
    }
}