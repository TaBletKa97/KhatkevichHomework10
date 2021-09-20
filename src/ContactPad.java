import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ContactPad {
    Map contacts = new HashMap<String , Contact>();

    public ContactPad() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("data.txt"));
            this.contacts = (Map) inputStream.readObject();
        } catch (IOException ex) {
            System.out.println("Не найден файл \"data.txt\". Работа программы продолжится c новой записной книжкой");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    public void addContact(String nickname, String phoneNumber) {
        if (contacts.containsKey(phoneNumber)) {
            Contact existsContact = (Contact) contacts.get(phoneNumber);
            System.out.println("Данный номер уже привязан к контакту: " + existsContact.getNickname());
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
}
