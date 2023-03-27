import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;

public class GUI_Frame2 extends JFrame implements ActionListener {
    private JTextField name_Field1;
    private JTextField name_Field2;
    private JTextField name_Field3_1;
    private JTextField id_Field;
    private JTextField name_Field4;
    private JTextArea name_Field5;
    private JTextField name_Field6;
    private JTextField name_Field7;

    private JTextField name_Field8;
    private JTextField name_Field9;
    private JTextField name_Field10;
    private JButton button_1;
    private JButton button_2;
    private JButton button_3;
    private JButton goBack;
    private ImageIcon image;

    private JComboBox doctorsDropDown;

    private WestminsterSkinConsultationManager west;
    private GUI_Frame2Controller guiFrame2Controller;

    private GUI_Frame1 frame1;

    public GUI_Frame2(WestminsterSkinConsultationManager west,GUI_Frame2Controller guiFrame2Controller,GUI_Frame1 frame1){

        this.west = west;
        this.guiFrame2Controller = guiFrame2Controller;
        this.frame1 = frame1;
        JLabel name_1 = new JLabel("Enter patient's name :");
        name_Field1= new JTextField();

        name_1.setBounds(20,230,150,30);
        name_Field1.setBounds(250,230,150,30);

        JLabel name_2 = new JLabel("Enter patient's Surname :");
        name_Field2= new JTextField();

        name_2.setBounds(20,270,150,30);
        name_Field2.setBounds(250,270,150,30);

        JLabel name_3 = new JLabel("Enter patient's DOB :");
        name_Field3_1 = new JTextField();

        name_3.setBounds(20,310,150,30);
        name_Field3_1.setBounds(250,310,150,30);

        //name_Field3_2.setBounds(440,400,90,20);
        //name_Field3_3.setBounds(550,400,50,20);

        JLabel name_4 = new JLabel("Enter patient's Mobile number :");
        name_Field4 = new JTextField();


        name_4.setBounds(20,350,250,30);
        name_Field4.setBounds(250,350,150,30);

        JLabel idLabel = new JLabel("Enter patient's ID :");
        id_Field = new JTextField();

        idLabel.setBounds(20,390,250,30);
        id_Field.setBounds(250,390,150,30);

        JLabel name_5 = new JLabel("Add notes  :");
        name_Field5 = new JTextArea();

        name_5.setBounds(20,430,250,30);
        name_Field5.setBounds(250,430,150,100);



        JLabel name_6 = new JLabel("Add image  :");
        name_Field6 = new JTextField();

        name_6.setBounds(20,540,250,30);
        name_Field6.setBounds(2500,540,150,30);


        JLabel name_7 = new JLabel("Select a doctor :");
        name_Field7 = new JTextField();
        doctorsDropDown = new JComboBox<>(west.ComboListGUI());

        name_7.setBounds(20,10,150,30);
        doctorsDropDown.setBounds(250,15,150,30);

        JLabel name_8 = new JLabel("Enter consultation date:");
        name_Field8= new JTextField();

        name_8.setBounds(20,50,150,30);
        name_Field8.setBounds(250,55,150,30);


        JLabel name_9 = new JLabel("Enter consultation time :");
        name_Field9= new JTextField();

        name_9.setBounds(20,90,150,30);
        name_Field9.setBounds(250,95,150,30);

        JLabel name_10 = new JLabel("Enter number of hours :");
        name_Field10= new JTextField();

        name_10.setBounds(20,130,150,30);
        name_Field10.setBounds(250,135,150,30);



        button_1 = new JButton("Add consultation");
        button_1.setBounds(250,580,150,20);
        button_1.addActionListener(this);

        button_2 = new JButton("Check availability");
        button_2.setBounds(250,170,150,20);
        button_2.addActionListener(this);

        button_3 = new JButton("Add Image");
        button_3.setBounds(250,540,150,20);
        button_3.addActionListener(this);

        goBack = new JButton("Go back");
        goBack.setBounds(250,610,150,20);
        goBack.addActionListener(this);


        this.add(name_1);
        this.add(name_2);
        this.add(name_3);
        this.add(name_4);
        this.add(name_5);
        this.add(name_6);
        this.add(name_7);
        this.add(name_8);
        this.add(name_9);
        this.add(name_10);
        this.add(idLabel);
        this.add(id_Field);
        this.add(name_Field1);
        this.add(name_Field2);
        this.add(name_Field3_1);
        this.add(name_Field4);
        this.add(name_Field5);
        this.add(name_Field6);
        this.add(name_Field7);
        this.add(name_Field8);
        this.add(name_Field9);
        this.add(name_Field10);
        this.add(button_1);
        this.add(button_2);
        this.add(button_3);
        this.add(goBack);
        this.add(doctorsDropDown);


        this.setTitle("Westminster Skin Care Center");
        this.setSize(600,720);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setVisible(true);
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        String path;
        File selectedFile;


        if(e.getSource() == button_1){

            //consultation date
            LocalDate lConsultationDate;
            try {
                String [] conDate = name_Field8.getText().split("\\.");
                lConsultationDate = LocalDate.of(Integer.parseInt(conDate[0]),Integer.parseInt(conDate[1]),Integer.parseInt(conDate[2]));
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Invalid date format or it is empty", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            //consultation time

            LocalTime lLocalTime;
            try {
                String [] timeL = name_Field9.getText().split("\\.");
                lLocalTime = LocalTime.of(Integer.parseInt(timeL[0]), Integer.parseInt(timeL[1]));
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Invalid time format or it is empty", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            //Get the selected doctor
            int idOfSelectedDoctor = Integer.parseInt(doctorsDropDown.getSelectedItem().toString().split("-")[1]);
            Doctor tempDoctor = west.getDoctorById(idOfSelectedDoctor);

            int hours = -1;
            try {
                hours = Integer.parseInt(name_Field10.getText());
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "No of hours is not a integer", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            //patient information
            if(name_Field1.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Patient name is required", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(name_Field2.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Patient surname is required", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(name_Field3_1.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Patient date of birth is required", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if(name_Field4.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Patient mobile number is required", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            int patientMobileNo = -1;

            try {
                patientMobileNo = Integer.parseInt(name_Field4.getText());
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Enter valid mobile number", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if(id_Field.getText().isBlank()){
                JOptionPane.showMessageDialog(null, "Patient ID is required", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            Patient tempPatient = new Patient(name_Field1.getText(),name_Field2.getText(),name_Field3_1.getText(),patientMobileNo,id_Field.getText());

            int id = guiFrame2Controller.addConsultation(tempDoctor,tempPatient,name_Field5.getText(),lLocalTime,hours,lConsultationDate,image);
            if(id < 0){
                JOptionPane.showMessageDialog(null, "No available doctors", "westminster",JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Successfully added consultation ID is : " + id, "westminster",JOptionPane.INFORMATION_MESSAGE);
            }


        }
        if(e.getSource() == button_2){

            //consultation date
            LocalDate lConsultationDate;
            try {
                String [] conDate = name_Field8.getText().split("\\.");
                lConsultationDate = LocalDate.of(Integer.parseInt(conDate[0]),Integer.parseInt(conDate[1]),Integer.parseInt(conDate[2]));
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Invalid date format or it is empty", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            //consultation time

            LocalTime lLocalTime;
            try {
                String [] timeL = name_Field9.getText().split("\\.");
                lLocalTime = LocalTime.of(Integer.parseInt(timeL[0]), Integer.parseInt(timeL[1]));
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Invalid time format or it is empty", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }


            //Get the selected doctor
            int idOfSelectedDoctor = Integer.parseInt(doctorsDropDown.getSelectedItem().toString().split("-")[1]);
            Doctor tempDoctor = west.getDoctorById(idOfSelectedDoctor);

            int hours = -1;
            try {
                hours = Integer.parseInt(name_Field10.getText());
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Not a integer", "westminster",JOptionPane.INFORMATION_MESSAGE);
            }

            boolean check = guiFrame2Controller.availabilityCheck(lConsultationDate,lLocalTime,hours,idOfSelectedDoctor);

            if(check){
                JOptionPane.showMessageDialog(null, "This doctor is available for the time slot", "westminster",JOptionPane.INFORMATION_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "This doctor is unavailable for the time slot", "westminster",JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if(e.getSource() == button_3){

            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File("user.home"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("*.images","jpg","gif","png");
            file.addChoosableFileFilter(filter);
            int result = file.showSaveDialog(null);

            if(result == JFileChooser.APPROVE_OPTION){
                selectedFile = file.getSelectedFile();
                path = selectedFile.getAbsolutePath();
                image = resizeImage(path);

            } else if (result == JFileChooser.CANCEL_OPTION) {
                System.out.println("None selected");
            }
        }

        if(e.getSource() == goBack){
            this.setVisible(false);
            frame1.setVisible(true);
        }


    }
    private ImageIcon resizeImage(String imagePath){
        ImageIcon MyImage = new ImageIcon(imagePath);
        Image image = MyImage.getImage();
        Image newImage = image.getScaledInstance(480,300, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
}