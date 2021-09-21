import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ContactPad {
    Map contacts = new HashMap<String , Contact>();

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
            switch (choice = choose()) {
                case 1:
//                    newContact
            }

        } while (choice != 5);

    }

    private int choose() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        try {
            choice = scanner.nextInt();
            if (choice < 1 || choice > 5) {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.out.println("Некорректный выбор. Попробуйте снова");
            choice = choose();
        }
        return choice;
    }

}
