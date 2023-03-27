import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);
    static WestminsterSkinConsultationManager west = new WestminsterSkinConsultationManager();

    public  static void main(String[] args) {
        mainMenu();
    }


        public static void mainMenu() {
            west.loadData();//load data from file

            System.out.println("----------------------");
            System.out.println("Westminster Medical Center");
            System.out.println("------------------------");
            System.out.println("" +
                    "1. Add a new doctor\n" +
                    "2.Delete a doctor\n" +
                    "3.Print list\n" +
                    "4.Save in a file\n" +
                    "5.Display GUI\n" +
                    "999.Exit\n" +
                    "Option>>");

            boolean e = false;

            while(!e){
                System.out.print("Enter your choice : ");
                String choice = input.next();

                switch (choice) {
                    case "1" -> addDoctor();
                    case "2" -> delDoctor();
                    case "3" -> west.printList();
                    case "4" -> west.saveList();
                    case "5" -> new GUI_Frame1(west);
                    case "999" -> e = true;
                    default -> System.out.println("Invalid Input !!");
                }
            }

            }





    private static void delDoctor() {
        System.out.println("---------Delete Doctor--------");
        System.out.print("Enter medical liscence number to delete doctor: ");
        int medical_licence_num = getIntInput();
        west.deleteDoctor(medical_licence_num);
        System.out.print("Doctor has been deleted Successfully");
    }

    private static void addDoctor() {
        System.out.println("---------Add a new Doctor-------------");

        System.out.println("Enter Doctor's medical licence number:");
        int medical_licence_num = getIntInput();


        System.out.println("Enter name of the Doctor");
        String name = input.next().toUpperCase();

        System.out.println("Enter surename  of the Doctor");
        String surname = input.next().toUpperCase();

        System.out.println("Enter the date of birth");
        String date_of_birth = input.next();


        System.out.println("Enter the mobile number of Doctor");
        int mobile_number = getIntInput();


        System.out.println("Enter Specialisation of the doctor:");
        String specialisation = input.next();
        Doctor doctor = new Doctor(medical_licence_num, name, surname, date_of_birth, mobile_number, specialisation);
        west.addDoctor(doctor);
    }


    private static int getIntInput() {
        while (!input.hasNextInt()) {                                      //Check if the input is nt a integer
            System.out.println("Invalid index!! reenter");                  //if not an integer display a error message
            input.next();
        }
        return input.nextInt();
    }




}