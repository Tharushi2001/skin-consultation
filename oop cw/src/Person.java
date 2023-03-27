import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Person implements Serializable {
    private String name;
    private String surname;
    private String date_of_birth;
    private int mobile_number;

    public Person(String name, String surname, String date_of_birth, int mobile_number) {
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
        this.mobile_number = mobile_number;
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

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number = mobile_number;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", mobile_number=" + mobile_number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return date_of_birth == person.date_of_birth && mobile_number == person.mobile_number && name.equals(person.name) && surname.equals(person.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, date_of_birth, mobile_number);
    }
}
