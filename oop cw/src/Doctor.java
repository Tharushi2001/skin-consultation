
import java.io.Serializable;
import java.util.Objects;

public class Doctor extends Person implements Serializable {
    int medical_licence_num;
    String specialisation;

    public Doctor( int medical_licence_num,String name, String surname, String date_of_birth, int mobile_number , String specialisation) {
        super(name, surname, String.valueOf(date_of_birth), mobile_number);
        this.medical_licence_num = medical_licence_num;
        this.specialisation = specialisation;
    }

    public int getMedical_licence_num() {
        return medical_licence_num;
    }
    public void setMedical_licence_num(int medical_licence_num) {
        this.medical_licence_num = medical_licence_num;
    }
    public String getSpecialisation() {
        return specialisation;
    }
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "medical_licence_num=" + medical_licence_num +
                ", specialisation='" + specialisation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return medical_licence_num == doctor.medical_licence_num && specialisation.equals(doctor.specialisation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medical_licence_num, specialisation);
    }


}
