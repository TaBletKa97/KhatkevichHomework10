import java.io.*;
import java.util.*;

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
                    Contact cont = search();
                    if (cont != null) {
                        System.out.println(cont);
                    }
                    break;
                case 4:
                    edit();
                    saveData();
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
            String phoneNumber = correctNumberInput();
           addContact(nickname, phoneNumber);
        } catch (Exception ex) {
            System.out.println("Некорректный ввод. Попробуйте снова");
            newContact();
        }
    }

    private Contact searchNumber() {
        try {
            Scanner scanner = new Scanner(System.in);
            String number = correctNumberInput();
            return contacts.get(number);
        } catch (Exception ex) {
            System.out.println("Произошла ошибка. Попробуйте еще раз");
            return searchNumber();
        }

    }

    private Contact searchNickname() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите прозвище для поиска");
            String name = scanner.nextLine();
            String result = "";
            for (Map.Entry<String, Contact> entry:contacts.entrySet()) {
                if (entry.getValue().getNickname().equalsIgnoreCase(name)) {
                    return entry.getValue();
                }
            }
            return null;
        } catch (Exception ex) {
            System.out.println("Произошла ошибка. Попробуйте еще раз");
            return searchNickname();
        }
    }

    private Contact search() {
        System.out.println("1. Поиск по прозвищу");
        System.out.println("2. Поиск по номеру");
        System.out.println("3. Назад");
        Contact contact;
        switch (choose(3)) {
            case 1:
                contact = searchNickname();
                if (contact == null) {
                    System.out.println("Контактов с таким прозвищем не найдено");
                }
                return contact;
            case 2:
                contact = searchNumber();
                if (contact == null) {
                    System.out.println("Контактов с таким номером не найдено");
                }
                return contact;
            case 3:
                break;
        }
        return null;
    }

    private void edit() {
        System.out.println("Выберите контакт, который нужно изменить или дополнить:");
        Contact contact = search();
        if (contact == null) {
            return;
        }
        System.out.println("Выберите пункт для изменения:");
        System.out.println("1. Изменить имя");
        System.out.println("2. Изменить фамилию");
        System.out.println("3. Добавить номер телефона");
        System.out.println("4. Добавить домашний номер телефона");
        System.out.println("5. Добавить рабочий номер телефона");
        System.out.println("6. Добавить номер факса");
        System.out.println("7. Изменить email");
        System.out.println("8. Изменить дату рождения");
        System.out.println("9. Назад");
        Scanner scanner = new Scanner(System.in);
        String number;
        switch (choose(8)) {
            case 1:
                try {
                    System.out.println("Введите имя:");
                    String name = scanner.nextLine();
                    contact.setName(name);
                } catch (Exception ex) {
                    System.out.println("Ошибка ввода.");
                }
                break;
            case 2:
                try {
                    System.out.println("Введите фамилию");
                    String surname = scanner.nextLine();
                    contact.setSurname(surname);
                } catch (Exception ex) {
                    System.out.println("Ошибка ввода.");
                }
                break;
            case 3:
                number = correctNumberInput();
                if (checkNumberInMap(number, contact)) {
                    contact.setPhoneNumbers(number);
                    contacts.put(number, contact);
                }
                break;
            case 4:
                number = correctNumberInput();
                if (checkNumberInMap(number, contact)) {
                    contact.setHomeNumbers(number);
                    contacts.put(number, contact);
                }
                break;
            case 5:
                number = correctNumberInput();
                if (checkNumberInMap(number, contact)) {
                    contact.setWorkNumbers(number);
                    contacts.put(number, contact);
                }
                break;
            case 6:
                number = correctNumberInput();
                if (checkNumberInMap(number, contact)) {
                    contact.setFaxNumbers(number);
                    contacts.put(number, contact);
                }
                break;
            case 7:
                try {
                    String email = scanner.nextLine();
                    contact.setEmail(email);
                } catch (Exception ex) {
                    System.out.println("Ошибка добавления");
                }
                break;
            case 8:
                try {
                    System.out.println("Введите год рождения");
                    int year = scanner.nextInt();
                    while (year < 1900 || year > 2200) {
                        System.out.println("Некорректный год. Попробуйте еще раз");
                        year = scanner.nextInt();
                    }
                    System.out.println("Введите месяц");
                    int month = scanner.nextInt();
                    while (month < 1 || month > 12) {
                        System.out.println("Некорректный месяц. Попробуйте еще раз");
                        month = scanner.nextInt();
                    }
                    System.out.println("Введите день");
                    int day = scanner.nextInt();
                    while (day < 1 || day > 31) {
                        System.out.println("Некорректный день. Попробуйте еще раз");
                        day = scanner.nextInt();
                    }
                    Calendar date = new GregorianCalendar(year, month-1, day);
                    contact.setDateOfBirthday(date);
                } catch (Exception ex) {
                    System.out.println("Ошибка ввода");
                }
                break;
            case 9:
                break;
        }
    }

    private String correctNumberInput() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите номер телефона в формате +375*********");
            String phoneNumber = scanner.nextLine();
            while (!phoneNumber.startsWith("+375") || phoneNumber.length() != 13) {
                System.out.println("Введен некорректный номер. Попробуйте снова");
                phoneNumber = scanner.nextLine();
            }
            return phoneNumber;
        } catch (Exception ex) {
            System.out.println("Ошибка ввода. Попробуйте снова");
            return correctNumberInput();
        }
    }

    private boolean checkNumberInMap (String number, Contact contact) {
        boolean allOk = true;
        if (contacts.containsKey(number)) {
            allOk = false;
            System.out.println("Данный номер уже привязан к контакту \"" + contact.getNickname() + "\"");
        }
        return allOk;
    }
}
