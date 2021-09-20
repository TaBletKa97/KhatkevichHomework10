import java.util.ArrayList;
import java.util.Date;

public class Contact {
    private String name;
    private String surname;
    private String nickname;
    private ArrayList<String> homeNumbers = new ArrayList<>();
    private ArrayList<String> phoneNumbers = new ArrayList<>();
    private ArrayList<String> workNumbers = new ArrayList<>();
    private ArrayList<String> faxNumbers = new ArrayList<>();
    private String email;
    private Date dateOfBirthday;

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

    public void setEmail(String email) {
        boolean allOk = true;
        String email1 = email;
        String[] email2 = email1.split("@");
        if (email2.length != 2) {
            System.out.println("Invalid Email");
            allOk = false;
        } else {
            String[] email3 = email2[1].split(".");
            if (email3.length !=2) {
                System.out.println("Invalid Email");
                allOk = false;
            }
        }
        if (allOk) {
            this.email = email;
        }
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public void setDateOfBirthday(Date dateOfBirthday) {
        this.dateOfBirthday = dateOfBirthday;
    }
}
