import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Contact implements Serializable {
    private String name;
    private String surname;
    private String nickname;
    private ArrayList<String> homeNumbers = new ArrayList<>();
    private ArrayList<String> phoneNumbers = new ArrayList<>();
    private ArrayList<String> workNumbers = new ArrayList<>();
    private ArrayList<String> faxNumbers = new ArrayList<>();
    private String email;
    private Calendar dateOfBirthday;

    public Contact(String nickname, String phone) {
        this.nickname = nickname;
        this.phoneNumbers.add(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public ArrayList<String> getHomeNumbers() {
        return homeNumbers;
    }

    public void setHomeNumbers(String number) {
        this.homeNumbers.add(number);
    }

    public ArrayList<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String number) {
        this.phoneNumbers.add(number);
    }

    public ArrayList<String> getWorkNumbers() {
        return workNumbers;
    }

    public void setWorkNumbers(String number) {
        this.workNumbers.add(number);
    }

    public ArrayList<String> getFaxNumbers() {
        return faxNumbers;
    }

    public void setFaxNumbers(String number) {
        this.faxNumbers.add(number);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws IllegalArgumentException {
        boolean allOk = true;
        String email1 = email;
        String[] email2 = email1.split("@");
        if (email2.length != 2) {
            allOk = false;
        } else {
            String[] email3 = email2[1].split("\\.");
            if (email3.length !=2) {
                allOk = false;
            }
        }
        if (allOk) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    public Calendar getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Calendar dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }

    @Override
    public String toString() {
        String result = "";
        if (name != null) {
            result += ("Имя: " + name + ";\n");
        }
        if (surname != null) {
            result += ("Фамилия: " + surname + ";\n");
        }
        if (nickname != null) {
            result += ("Прозвище: " + nickname + ";\n");
        }
        if (email != null) {
            result += ("Email: " + email + ";\n");
        }
        if (dateOfBirthday != null) {
            SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyy");
            result += ("Дата рождения: " + form.format(dateOfBirthday.getTime()) + ";\n");
        }
        if (homeNumbers.size() > 0) {
            result += ("Домашние номера: " + homeNumbers.toString() + ";\n");
        }
        if (phoneNumbers.size() > 0) {
            result += ("Мобильные номера: " + phoneNumbers.toString() + ";\n");
        }
        if (workNumbers.size() > 0) {
            result += ("Рабочие номера: " + workNumbers.toString() + ";\n");
        }
        return result;
    }
}
