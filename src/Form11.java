import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.mysql.cj.protocol.Resultset;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Form11 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	
	// table - mouse listner
	private void scrolPanelMouseClicked(MouseEvent e)	{
		DefaultTableModel df = (DefaultTableModel) table1.getModel();
		
		int index1 = table1.getSelectedRow();
		
		textField1.setText(df.getValueAt(index1, 0).toString());
		textField2.setText(df.getValueAt(index1, 1).toString());
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		Form11 obj1 = new Form11();
		obj1.Prepare();
		obj1.updateTable();
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Form11 frame = new Form11();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		
	}

	private JTable table1;
	private JTextField textField;
	private JTextField textField_1;
	
	public void Prepare() {
		String[] cols = {"category code", "category description"};
		
		String[][] data = {{"b1", "b2"}};
		
		
		DefaultTableModel model = new DefaultTableModel(data, cols);
		// jtable name
		table1.setModel(model);
		
		
	}
	
	/**
	 * Create the frame.
	 */
	public Form11() {
		
		textField = new JTextField();
		getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		getContentPane().add(textField_1, BorderLayout.CENTER);
		textField_1.setColumns(10);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("New button");
		
		btnNewButton.addActionListener(new ActionListener() { //add
			public void actionPerformed(ActionEvent arg0) {
				String catcode, catdesc;
				PreparedStatement query;
				
				catCode = textfield1.getText();
				catDesc = textfield2.getText();
				
				// load the jdbc driver
				Class.forName("com.mysql.cj.jdbc.Driver");

				// connect to the database
				Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");
				
				
				// enter data
				Scanner key = new Scanner(System.in);

				System.out.println("Enger a category code ");
				catcode = key.nextLine();

				System.out.println("Enger a category description ");
				catdesc = key.nextLine();

				// create the sql statement that will add the data
				query = con1.prepareStatement("insert into category values (?,?)");
				query.setString(1, catcode);
				query.setString(2, catdesc);

				query.executeUpdate();

				JOptionPane.showMessageDialog(null, "One record added");
				
				textfield1.setText("");
				textfield2.setText("");
				
				UpdateTable(); // check???
			}
		});
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {// update
				PreparedStatement query;
				// load the jdbc driver
				Class.forName("com.mysql.cj.jdbc.Driver");

				// connect to the database
				Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost/inventory", "root", "");
				
				// create the sql statement that will add the data
				query = con1.prepareStatement("select * from category");

				ResultSet rs = query.executeQuery();
				
				
				int c;
				ResultSetMetaData res = rs.getMetaData();
				c = res.getColumnCount();
				
				DefaultTableModel dft1 = (DefaultTableModel) table1.getModel();
				dft1.setRowCount(0);
				
				while(rs.next()) {
					Vector v2 = new Vector();
					
					for(int i = 1; i<=c; i++) {
						v2.add(rs.getString("CatCode"));
						v2.add(rs.getString("Catdesc"));
					}
					dft1.addRow(v2);
				}
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(btnNewButton)
					.addGap(38)
					.addComponent(btnNewButton_1)
					.addContainerGap(208, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton_1))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
