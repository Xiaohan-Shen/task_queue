import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Task {
    private double priority;
    private String name;
    private int taskConsumption;
    private long completionTime;

    public Task(String name, int taskConsumption, long preferredCompletionTime) {
        this.name = name;
        this.taskConsumption = taskConsumption;
        this.completionTime = preferredCompletionTime + System.currentTimeMillis();
        this.priority = (this.completionTime - System.currentTimeMillis()) / (double)(taskConsumption);
    }

    public double updatePriority() {
        priority = (completionTime - System.currentTimeMillis()) / (double)(taskConsumption);
        return priority;
    }

    public void run() throws InterruptedException {
        Thread.currentThread().sleep(taskConsumption);
        System.out.println("task " + name + " gets run.");
    }


}
