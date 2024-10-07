import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Task {
    private int id;
    private String name;
    private boolean completed;

    public Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.completed = false;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void complete() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return String.format("Task{id=%d, name='%s', completed=%s}", id, name, completed);
    }
}

class TaskManager {
    private List<Task> tasks;
    private int nextId;

    public TaskManager() {
        tasks = new ArrayList<>();
        nextId = 1;
    }

    public void addTask(String name) {
        tasks.add(new Task(nextId++, name));
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public boolean completeTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.complete();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}

public class TaskApp {
    private static TaskManager taskManager = new TaskManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            System.out.println("Enter command (add, list, complete, delete, exit):");
            command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.println("Enter task name:");
                    String name = scanner.nextLine();
                    taskManager.addTask(name);
                    break;
                case "list":
                    for (Task task : taskManager.getTasks()) {
                        System.out.println(task);
                    }
                    break;
                case "complete":
                    System.out.println("Enter task ID to complete:");
                    int completeId = Integer.parseInt(scanner.nextLine());
                    if (taskManager.completeTask(completeId)) {
                        System.out.println("Task completed.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case "delete":
                    System.out.println("Enter task ID to delete:");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    if (taskManager.deleteTask(deleteId)) {
                        System.out.println("Task deleted.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;
                case "exit":
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
