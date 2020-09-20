package duke.task;

/**
 * Represents a task. A <code>Task</code> object corresponds to
 * a task with a description and done status
 */
public abstract class Task {
    public static final String TICK_ICON = "[\u2713]";
    public static final String CROSS_ICON = "[\u2718]";
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon to show if task is done or not.
     *
     * @return Status icon.
     */
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
}