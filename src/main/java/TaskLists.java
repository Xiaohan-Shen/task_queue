import java.util.ArrayList;
import java.util.LinkedList;

public class TaskLists {
    private ArrayList<TaskList> taskLists;
    private final int NUM_OF_LISTS = 7;

    public TaskLists() {
        this.taskLists = new ArrayList<TaskList>();
        for (int i = 0; i < NUM_OF_LISTS; ++i) {
            taskLists.add(new TaskList(i));
        }
    }

    public TaskLists(ArrayList<TaskList> taskLists) {
        this.taskLists = taskLists;
    }

    public void popAndRun() throws InterruptedException {
        for (TaskList list: taskLists) {
            if (list.size() == 0)
                continue;
            list.pop().run();
            break;
        }
    }

    public void reOrder() {
        for (TaskList list: taskLists){
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                if ((int)task.getPriority() != (int)task.updatePriority() && task.getPriority() < NUM_OF_LISTS) {
                    list.remove(i);
                    i--;
                    insert(task);
                }
            }
        }
    }

    public void insert(Task task) {
        task.updatePriority();
        if (task.getPriority() < 1) {
            // 优先级比1小，直接插入到最优先队列的队头
            taskLists.get(0).push(task);
        } else if (task.getPriority() > NUM_OF_LISTS) {
            taskLists.get(NUM_OF_LISTS - 1).addLast(task);
        } else {
            // 优先级比1大，插入到对应队列的队尾
            taskLists.get((int) task.getPriority() - 1).addLast(task);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task1 = new Task("task1", 3000, 700000);
        Task task2 = new Task("task2", 1000, 7000);
        Task task3 = new Task("task3", 1000, 3000);
        TaskLists lists = new TaskLists();
        lists.insert(task1);
        lists.insert(task2);
        lists.reOrder();
        lists.popAndRun();
        lists.insert(task3);
        lists.reOrder();
    }
}
