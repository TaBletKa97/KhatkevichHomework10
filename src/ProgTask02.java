import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgTask02 {
    public static void main(String[] args) {
        go();
    }

    private static void go() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("taskData.txt"));
            ArrayList<Task> tasks = (ArrayList<Task>) objectInputStream.readObject();
            Task.setTasks(tasks);
        } catch (Exception ex) {
            System.out.println("Сохраненных задач не найдено");
        }

        int n;
        do {
            showMenu();
            n = choose(5);
            switch (n) {
                case 1:
                    Task.addTask();
                    break;
                case 2:
                    showActual();
                    break;
                case 3:
                    showAll();
                    break;
                case 4:
                    showAll();
                    int size = Task.getTasks().size();
                    System.out.println("Введите номер задачи");
                    int num = choose(size);
                    Task.getTasks().get(num - 1).completeTask();
                    break;
                case 5:
                    save();
            }
        } while (n != 5);

    }

    private static int choose(int n) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try {
            choice = scanner.nextInt();
            if (choice < 1 || choice > n) {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Некорректный выбор. Попробуйте снова");
            choice = choose(n);
        }
        return choice;
    }

    private static void save() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("taskData.txt"));
            objectOutputStream.writeObject(Task.getTasks());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static int showActual() {
        int size = 0;
        for (int i = 0; i < Task.getTasks().size(); i++) {
            if (!Task.getTasks().get(i).status) {
                size++;
                System.out.println("Задача " + size + ":");
                System.out.println(Task.getTasks().get(i).toString());
            }
        }
        if (size == 0) {
            System.out.println("Актуальных задач нет.\n");
        }
        return size;
    }

    private static void showAll() {
        if (Task.getTasks().size() == 0) {
            System.out.println("Задач не найдено");
            return;
        }
        for (int i = 0; i < Task.getTasks().size(); i++) {
            System.out.println("Задача " + (i + 1) + ":");
            System.out.println(Task.getTasks().get(i).toString());
        }
    }

    private static void showMenu()  {
        System.out.println("Выберите нужное действие:");
        System.out.println("1. Добавить задачу:");
        System.out.println("2. Отобразить актуальные задачи:");
        System.out.println("3. Отобразить все задачи:");
        System.out.println("4. Пометить задачу выполненной:");
        System.out.println("5. Сохранить и завершить работу программы:");
    }
}
