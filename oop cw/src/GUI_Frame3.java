import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI_Frame3 extends JFrame implements ActionListener {

    private JButton check;
    private JButton goBack;
    private JTextField field;

    private JLabel consultationID_L;
    private JLabel patientName_L;
    private JLabel doctorName_L;
    private JLabel date_L;
    private JLabel time_L;
    private JLabel noOfHours_L;
    private JLabel cost_L;
    private JLabel notes_L;
    private JLabel image_L;
    private GUI_Frame2Controller controller;
    private GUI_Frame1 frame1;
    public GUI_Frame3(GUI_Frame2Controller controller,GUI_Frame1 frame1) {
        this.controller = controller;
        this.frame1 = frame1;

        this.setSize(600, 460);
        this.getContentPane().setLayout(null);

        JLabel label = new JLabel("Enter consultation ID");
        field = new JTextField();

        check = new JButton("Check");
        goBack = new JButton("Go Back");

        label.setBounds(10, 10, 150, 25);
        field.setBounds(160, 10, 150, 25);
        check.setBounds(160, 40, 120, 20);
        goBack.setBounds(160, 65, 120, 20);

        check.addActionListener(this);
        goBack.addActionListener(this);

        image_L = new JLabel();
        consultationID_L = new JLabel();
        patientName_L = new JLabel();
        doctorName_L = new JLabel();
        date_L = new JLabel();
        time_L = new JLabel();
        noOfHours_L = new JLabel();
        cost_L = new JLabel();
        notes_L = new JLabel();

        JPanel reviewData = new JPanel(new GridLayout(8,1,0,1));
        reviewData.setBounds(160,90,400,340);

        reviewData.add(image_L);
        reviewData.add(consultationID_L);
        reviewData.add(patientName_L);
        reviewData.add(doctorName_L);
        reviewData.add(date_L);
        reviewData.add(time_L);
        reviewData.add(noOfHours_L);
        reviewData.add(cost_L);
        reviewData.add(notes_L);


        this.add(label);
        this.add(field);
        this.add(check);
        this.add(goBack);
        this.add(reviewData);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == check){
            Consultation consultation = null;
            try {
                consultation = controller.getConsultation(Integer.parseInt(field.getText()));
            }catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Not an integer", "westminster",JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (consultation != null) {
                String[] consultationInformation = consultation.toStringArray();

                try {
                    image_L.setIcon(consultation.getImage());
                }catch (Exception r){
                    image_L.setText("None selected");
                }
                image_L.setIcon(consultation.getImage());
                consultationID_L.setText(consultationInformation[0]);
                patientName_L.setText(consultationInformation[1]);
                doctorName_L.setText(consultationInformation[2]);
                date_L.setText(consultationInformation[3]);
                time_L.setText(consultationInformation[4]);
                noOfHours_L.setText(consultationInformation[5]);
                cost_L.setText(consultationInformation[6]);
                notes_L.setText(consultationInformation[7]);
            }else {
                JOptionPane.showMessageDialog(null, "Invalid id", "westminster",JOptionPane.INFORMATION_MESSAGE);
            }
        }

        if(e.getSource() == goBack){
            this.setVisible(false);
            frame1.setVisible(true);
        }
    }
}
