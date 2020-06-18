import java.util.LinkedList;

public class TaskList extends LinkedList<Task> {
    private int priorityLevel;

    public TaskList(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }
}
