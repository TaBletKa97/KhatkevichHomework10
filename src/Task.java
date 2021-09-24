import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Task implements Serializable {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private String name;
    private String description;
    private int priority;
    private Calendar creationDate;
    private Calendar endingDate;
    private String owner;
    boolean status;

    public Task(String name, String description, int priority, int duration, String owner) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.creationDate = GregorianCalendar.getInstance();
        this.endingDate = GregorianCalendar.getInstance();
        this.endingDate.add(Calendar.DAY_OF_MONTH, duration);
        this.owner = owner;
    }

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    public static void setTasks(ArrayList<Task> tasks) {
        Task.tasks = tasks;
    }

    public static void addTask() {
        Scanner scanner = new Scanner(System.in);
        String name;
        String description;
        int priority;
        int duration;
        String owner;
        System.out.println("Введите название задачи");
        name = correctString();
        System.out.println("Введите описание задачи");
        description = correctString();
        System.out.println("Введите приоритет задачи в диапазоне от 1 до 10");
        priority = correctInt();
        while (priority < 1 || priority > 10) {
            System.out.println("Неверное значение.");
            System.out.println("Введите приоритет задачи в диапазоне от 1 до 10");
            priority = correctInt();
        }
        System.out.println("Введите количество дней на выполнение задачи");
        duration = correctInt();
        System.out.println("Введите имя ответственного за задачу");
        owner = correctString();
        Task task = new Task(name, description, priority, duration, owner);
        tasks.add(task);
    }

    private static String correctString() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextLine();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ошибка ввода попробуйте еще раз");
            return correctString();
        }
    }

    private static int correctInt() {
        try {
            Scanner scanner = new Scanner(System.in);
            return scanner.nextInt();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ошибка ввода, попробуйте еще раз");
            return correctInt();
        }
    }

    public void completeTask() {
        status = true;
        System.out.println("Задача помечена выполненной");
    }

    @Override
    public String toString() {
        String result = "";
        result += "Название задачи: " + name + ".\n";
        result += "Описание задачи: " + description + ".\n";
        result += "Приоритет задачи: " + priority + ".\n";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
        result += "Дата создания: " + simpleDateFormat.format(creationDate.getTime()) + ".\n";
        result += "Дата окончания: " + simpleDateFormat.format(endingDate.getTime()) + ".\n";
        result += "Ответственное лицо: " + owner + ".\n";
        String status;
        if (this.status) {
            status = "выполнена";
        } else {
            status = "выполняется";
        }
        result += "Cтатус задачи: " + status + ".\n";
        return result;
    }
}
