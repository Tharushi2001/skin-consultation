import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GUI_Frame2Controller {

    private ArrayList <Doctor> doctors;
    LinkedList <Consultation> consultations = new LinkedList<>();
    private static int Id = 100;

    public GUI_Frame2Controller(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    public int addConsultation(Doctor doctor, Patient patient, String notes, LocalTime consulTime, int noOfHours, LocalDate consulDate, ImageIcon image){

        double cost;

        if(isPatientFirstTime(patient.getPatientID())){
            cost = noOfHours * 25;
        }else {
            cost = noOfHours * 15;
        }

        if(availabilityCheck(consulDate, consulTime,noOfHours,doctor.getMedical_licence_num())){
            Consultation c = new Consultation(consulDate, consulTime, noOfHours, cost, notes, image, doctor, patient);
            consultations.add(c);
            return c.getConsultationId();
        }else {
            for(Doctor d : doctors){
                if(availabilityCheck(consulDate, consulTime,noOfHours,doctor.getMedical_licence_num())){
                    Consultation c = new Consultation(consulDate, consulTime, noOfHours, cost, notes, image, doctor, patient);
                    consultations.add(c);
                    return c.getConsultationId();
                }
            }
        }
        return -1;

    }



    public boolean isPatientFirstTime(String patientID){
        for(Consultation c: consultations){
            if (c.getPatient().getPatientID().equals(patientID)) {
               return true;
            }else {
                return false;
            }
        }
        return false;
    }

    public boolean availabilityCheck(LocalDate consultationDate, LocalTime consultationTime, int noOfHours, int doctorLicenceNumber){

        for (Consultation consultation : consultations){
            if(consultation.getDocLi() == doctorLicenceNumber){

                boolean condition1 = (consultationTime.isAfter(consultation.getTime())) && (consultationTime.isBefore(consultation.getTime().plusHours(consultation.getNoOfHours())));
                boolean condition2 = ( consultationTime.plusHours(noOfHours).isAfter(consultation.getTime()) ) && ( consultationTime.plusHours(noOfHours).isBefore(consultation.getTime().plusHours(consultation.getNoOfHours())) );
                boolean condition3 = ( consultationTime.isBefore(consultation.getTime()) && (consultationTime.plusHours(noOfHours).isAfter(consultation.getTime().plusHours(consultation.getNoOfHours()))) );
                boolean condition4 = (consultationTime.equals(consultation.getTime()) ||
                                      consultationTime.equals(consultation.getTime().plusHours(consultation.getNoOfHours())) ||
                                      consultationTime.plusHours(noOfHours).equals(consultation.getTime()) ||
                                      consultationTime.plusHours(noOfHours).equals(consultation.getTime().plusHours(consultation.getNoOfHours())));

                if( (consultationDate.equals(consultation.getConsulDate())) && ( (condition1) || (condition2) || (condition3) || (condition4) ) ){
                    return false;
                }
            }
        }
        return true;
    }

    public Consultation getConsultation(int consultId){
        System.out.println(consultId);
        for (Consultation c : consultations){
            System.out.println(c.getConsultationId());
            if(c.getConsultationId() == consultId){
                return c;
            }
        }
        return null;
    }

    public void saveToFile() {
        try  {
            FileOutputStream fos = new FileOutputStream("consultations.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Consultation c : consultations){
                oos.writeObject(c);
            }
            oos.close();
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
    }

    public int loadFile() {
        try  {
            FileInputStream fos = new FileInputStream("consultations.dat");
            ObjectInputStream oos = new ObjectInputStream(fos);
            boolean eof = false;
            while (!eof){
                try {
                    Consultation c = (Consultation) oos.readObject();
                    consultations.add(c);
                }catch (EOFException e){
                    eof = true;
                }
            }
            oos.close();
            return 1;
        } catch (ClassNotFoundException | IOException e) {
            return -1;
        }
    }
}
