import java.time.LocalDate;

public class Patient extends Person{
    private String patientID;



    public Patient(String name, String surname, String date_of_birth, int mobile_number, String patientID) {
        super(name, surname, date_of_birth, mobile_number);
        this.patientID = patientID;
    }
    
    public String getPatientID(){return patientID;}
    
}


