import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class WestminsterSkinConsultationManager implements  SkinConsultationManager{

    public ArrayList <Doctor> DoctorList=new ArrayList<>(10);
    private static int available_slots=10;
    public static final int max_doctors=10;
    @Override
    public  void addDoctor(Doctor doctor) {

        for (Doctor d1:DoctorList ) {
            if(doctor.getMedical_licence_num() == d1.getMedical_licence_num()){
                System.out.println("This Doctor is already in the list");
                return;
            }
        }
       if (DoctorList.size()<max_doctors){
            DoctorList.add(doctor);
            System.out.println(this.DoctorList);
            available_slots-=1;
            System.out.println();
            System.out.println("A Doctor has been added");
            System.out.println("Available slots:"+available_slots);
        }
        else{
            System.out.println("No slots Available");
        }

    }
    @Override
    public void deleteDoctor(int medical_licence_num) {
        Doctor doctor=null;
        for (Doctor d:this.DoctorList        ) {
            if (d.getMedical_licence_num()==medical_licence_num){
                doctor=d;
                break;
            }
        }
        if(doctor==null){
            System.out.println("no doctor was found with the specific license number");
        }else {
            this.DoctorList.remove(doctor);
        }
    }
    @Override
    public void printList() {
        DoctorList.sort(Comparator.comparing(Person::getSurname));


        for (Doctor d : DoctorList){
            System.out.println("Doctor name:"+d.getName()+"  "+""+d.getSurname());
            System.out.println("");
            System.out.println("Medical license number:"+d.getMedical_licence_num());
            System.out.println("Specialization: "+d.getSpecialisation());
            System.out.println("Mobile number:"+d.getMobile_number());
            System.out.println("Birth Day:"+d.getDate_of_birth());
            System.out.println("---------------------------------");
            System.out.println("");
        }
    }

    @Override
    public void saveList() {
        try  {
            FileOutputStream fos = new FileOutputStream("doctorData.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Doctor doctor : DoctorList){
                oos.writeObject(doctor);
            }
            oos.close();
        } catch (IOException e) {
            System.out.println("IOException " + e.getMessage());
        }
        System.out.println("Successfully saved data into file\n");
    }

    @Override
    public void loadData(){
        try  {
            FileInputStream fos = new FileInputStream("doctorData.dat");
            ObjectInputStream oos = new ObjectInputStream(fos);
            boolean eof = false;//to determine if rea
            while (!eof){
                try {
                    Doctor tempDoctor = (Doctor) oos.readObject();
                    DoctorList.add(tempDoctor);
                }catch (EOFException e){
                    eof = true;
                }
            }
            System.out.println("Successfully loaded data from file\n");
            oos.close();
        } catch (ClassNotFoundException e) {
            System.out.println("InvalidClassException " + e.getMessage());
        }catch (IOException e){
            System.out.println("IOException " + e.getMessage());
        }
    }

    public DefaultTableModel getDoctorsForGUI(){

        Object [][] tableData = new Object[DoctorList.size()][4];

        for(int i = 0; i < DoctorList.size(); i++){
            tableData [i][0] = DoctorList.get(i).getName() + " " + DoctorList.get(i).getSurname();
            tableData [i][1] = DoctorList.get(i).getSpecialisation();
            tableData [i][2] = DoctorList.get(i).getMobile_number();
        }
        Object [] columns = new Object[]{"Doctors Name", "Specialisation", "Mobile Number"};
        //add columns and table data to a table model and return it
        return new DefaultTableModel(tableData,columns);
    }
    //method to return doctors to display in comboBox/dropDown
    public String [] ComboListGUI(){
        String [] comboList = new String[DoctorList.size()];
        for(int i = 0; i < DoctorList.size(); i++){
            comboList[i] = DoctorList.get(i).getName() + "-" + DoctorList.get(i).getMedical_licence_num();
        }
        return comboList;
    }
    
    public Doctor getDoctorById(int idOfDoctor){
        for(Doctor d : DoctorList){
            if(d.getMedical_licence_num() == idOfDoctor)return d;
        }
        return null;
    }

    public ArrayList<Doctor> getDoctorList() {
        return DoctorList;
    }
}

