import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI_Frame1 extends JFrame implements ActionListener {
    private JButton sortB;
    private JButton addB;
    private JButton reviewB;
    private JButton saveDataB;

    private JTable doctorTable;
    private WestminsterSkinConsultationManager west;
    private GUI_Frame2Controller controller;

    public GUI_Frame1(WestminsterSkinConsultationManager west) {

        this.west = west;
        this.controller = new GUI_Frame2Controller(west.getDoctorList());
        controller.loadFile();//load previous data

        this.setSize(600,460);
        this.getContentPane().setLayout(null);

        doctorTable = new JTable(west.getDoctorsForGUI());
        JScrollPane doctorTableScrollPane = new JScrollPane(doctorTable);
        doctorTableScrollPane.setBounds(10,10,400,400);

        sortB = new JButton("sort table");
        addB = new JButton("add consultation");
        reviewB = new JButton("review consultation");
        saveDataB = new JButton("save data");

        sortB.setBounds(420,10,150,50);
        addB.setBounds(420,70,150,50);
        reviewB.setBounds(420,130,150,50);
        saveDataB.setBounds(420,190,150,50);

        sortB.addActionListener(this);
        addB.addActionListener(this);
        reviewB.addActionListener(this);
        saveDataB.addActionListener(this);

        this.add(doctorTableScrollPane);
        this.add(sortB);
        this.add(addB);
        this.add(reviewB);
        this.add(saveDataB);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == addB){
            this.setVisible(false);
            new GUI_Frame2(this.west,this.controller,this);
        }

        if(e.getSource() == reviewB){
            new GUI_Frame3(controller,this);
            this.setVisible(false);
        }

        if(e.getSource() == saveDataB){
            controller.saveToFile();//save data to object file
            JOptionPane.showMessageDialog(null, "Successfully saved data to file", "westminster",JOptionPane.INFORMATION_MESSAGE);
        }

        if(e.getSource() == sortB){

            TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(doctorTable.getModel());
            doctorTable.setRowSorter(sorter);

            ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
            sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));//set column one as sort key and ascending order
            sorter.setSortKeys(sortKeys);
            doctorTable.repaint();//refresh the table after sorting
            JOptionPane.showMessageDialog(null, "Successfully sorted doctors according to their names", "westminster",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
