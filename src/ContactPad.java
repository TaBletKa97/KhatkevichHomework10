import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactPad {
    Map<String , Contact> contacts = new HashMap<>();

    public ContactPad() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("D:\\JPR\\KhatkevicHomework10\\data.txt"));
            this.contacts = (HashMap) inputStream.readObject();
        } catch (IOException ex) {
            System.out.println("Не найден файл \"data.txt\". Работа программы продолжится c новой записной книжкой");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    private void addContact(String nickname, String phoneNumber) {
        if (contacts.containsKey(phoneNumber)) {
            Contact existsContact = (Contact) contacts.get(phoneNumber);
            System.out.println("Данный номер уже привязан к контакту: \"" + existsContact.getNickname() + "\"");
        } else {
            Contact contact = new Contact(nickname, phoneNumber);
            this.contacts.put(phoneNumber, contact);
            saveData();
        }
    }

    private void saveData() {
        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("data.txt"));
            writer.writeObject(contacts);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void viewContacts() {
        contacts.values().forEach(e -> System.out.println(e.toString()));
    }

     private void showMenu() {
         System.out.println("1. Создать контакт");
         System.out.println("2. Просмотреть контакты");
         System.out.println("3. Поиск по контактам");
         System.out.println("4. Изменить или добавить информацию о контакте");
         System.out.println("5. завершить работу");
    }

    public void go() {
        int choice = 0;
        do {
            showMenu();
            switch (choice = choose(5)) {
                case 1:
                    newContact();
                    break;
                case 2:
                    viewContacts();
                    break;
                case 3:
                    search();
                    break;
                case 4:
                    //edit
                    break;
                case 5:
                    break;
            }

        } while (choice != 5);

    }

    private int choose(int n) {
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

    private void newContact() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите прозвище");
            String nickname = scanner.nextLine();
            System.out.println("Введите номер телефона в формате +375*********");
            String phoneNumber = scanner.nextLine();
            while (!phoneNumber.startsWith("+375") || phoneNumber.length() != 13) {
                System.out.println("Введен некорректный номер. Попробуйте снова");
                phoneNumber = scanner.nextLine();
            }
           addContact(nickname, phoneNumber);
        } catch (Exception ex) {
            System.out.println("Некорректный ввод. Попробуйте снова");
            newContact();
        }
    }

    private String searchNumber() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер для поиска в формате +375********* ");
            String number = scanner.nextLine();
            return contacts.get(number).toString();
        } catch (Exception ex) {
            System.out.println("Произошла ошибка. Попробуйте еще раз");
            return searchNumber();
        }

    }

    private String searchNickname() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите прозвище для поиска");
            String name = scanner.nextLine();
            String result = "";
            contacts.values().forEach(con -> {
                if (con.getNickname().equalsIgnoreCase(name)) {
                    System.out.println(con);
                    System.out.println("");
                }
            });
            return result;
        } catch (Exception ex) {
            System.out.println("Произошла ошибка. Попробуйте еще раз");
            return searchNickname();
        }
    }

    private void search() {
        System.out.println("1. Поиск по прозвищу");
        System.out.println("2. Поиск по номеру");
        System.out.println("3. Назад");
        switch (choose(3)) {
            case 1:
                searchNickname();
                break;
            case 2:
                System.out.println(searchNumber());
                break;
            case 3:
                break;
        }
    }

}
