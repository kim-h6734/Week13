import javax.swing.*;
import net.miginfocom.swing.*;
import java.sql.*;
/*
 * Created by JFormDesigner on Sat Aug 01 14:30:08 PDT 2020
 */



/**
 * @author unknown
 */
public class Inventory extends JFrame {
    public Inventory() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Hyeonju Kim
        label1 = new JLabel();
        textField1 = new JTextField();
        label2 = new JLabel();
        textField2 = new JTextField();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        btnAdd = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter category code");
        contentPane.add(label1, "cell 1 1");
        contentPane.add(textField1, "cell 2 1");

        //---- label2 ----
        label2.setText("Enter category desc");
        contentPane.add(label2, "cell 1 2");
        contentPane.add(textField2, "cell 2 2");

        //======== scrollPane1 ========
        {
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 2 3");

        //---- btnAdd ----
        btnAdd.setText("Add");
        contentPane.add(btnAdd, "cell 2 4");

        //---- btnEdit ----
        btnEdit.setText("Edit");
        contentPane.add(btnEdit, "cell 2 5");

        //---- btnDelete ----
        btnDelete.setText("Delete");
        contentPane.add(btnDelete, "cell 2 6");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public static void main(String[] args) {
        Inventory jj1 = new Inventory();
        jj1.updateTable();
        jj1.setVisible(true);
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Hyeonju Kim
    private JLabel label1;
    private JTextField textField1;
    private JLabel label2;
    private JTextField textField2;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    public void updateTable()   {
        PreparedStatement query;
        Class.forName("com.mysql.jdbc.Driver");
        Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");

        // create the sql statement that will add the data
        query = con1.prepareStatement("select * from category");
        Result rs = query.executeQuery();

        int c;
        ResultSetMetaData res = rs.getMetaData();
        c = res.getColumnCount();

        DefaultTableModel dft1 = (DefaultTableModel) table1.getModel();
        dft1.setRowCount(0);

        while(rs.next()){
            Vector v2 = new Vector();
            for(int i = 1; i<=c; i++) {
                v2.add(rs.getString("CatCode"));
                v2.add(rs.getString("Catdesc"));
            }
            dft1.addRow(v2);

        }


    }
}
