package org.eclipse.wb.swt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import backend.Register;
import net.miginfocom.swing.MigLayout;

public class OfficerRemoveGUI extends JFrame {

	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField usernameTextField;
	private JTextField assignedID;
	private JTextField email;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfficerRemoveGUI frame = new OfficerRemoveGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OfficerRemoveGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[155px,grow][27px][68px][244px]", "[50px][][30px][30px][30px][30px][30px][30px][grow][][][]"));
		
		JLabel lblNewLabel = new JLabel("ADMINISTRATOR PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 4 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Remove Officer");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 1 4 1,alignx center,growy");
		
		JLabel lblNewLabel_1 = new JLabel("First Name :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1, "cell 0 2,alignx right,growy");
		
		firstName = new JTextField();
		contentPane.add(firstName, "cell 2 2 2 1,grow");
		firstName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1, "cell 0 3,alignx right,growy");
		
		lastName = new JTextField();
		lastName.setColumns(10);
		contentPane.add(lastName, "cell 2 3 2 1,grow");
		
		JLabel lblNewLabel_1_2 = new JLabel("Username :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_2, "cell 0 5,alignx right,growy");
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		contentPane.add(usernameTextField, "cell 2 5 2 1,grow");
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Assigned ID :");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1_1_1, "cell 0 6,alignx right,growy");
		
		assignedID = new JTextField();
		assignedID.setColumns(10);
		contentPane.add(assignedID, "cell 2 6 2 1,grow");
		
		email = new JTextField();
		email.setColumns(10);
		contentPane.add(email, "cell 2 4 2 1,grow");
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Email :");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1_2, "cell 0 4,grow");
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//firstName,LastName,email,username,password,assignedID
				String fName = firstName.getText().trim();
				String lName = lastName.getText().trim();
				String em = email.getText().trim();
				String username = usernameTextField.getText().trim();
				
				String ID = assignedID.getText().trim();
				
				//Check if inputs contains "," 
				//Commas are not allowed since it used as a separator in the database
				boolean containsComma = fName.contains(",") || lName.contains(",") || em.contains(",") || username.contains(",") || ID.contains(",");
				if(containsComma) {
					JOptionPane.showMessageDialog(null, "The use of character ',' is forbidden!");
				}
				//Check if some fields are empty
				else if(fName.isEmpty() || lName.isEmpty() || em.isEmpty() || username.isEmpty() || ID.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields must be filled!");
				}
				else {
					Register reg = new Register();
					
					boolean temp = false;
					try {
						temp = reg.removeOfficer(fName, lName, em, username, ID);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//temp == true, officer exist and successfully removed
					if(temp) {
						//Close JPanel window
						
						JOptionPane.showMessageDialog(null, "Officer successfully removed!");
						
						JComponent comp = (JComponent) e.getSource();
						Window win = SwingUtilities.getWindowAncestor(comp);
						win.dispose();
						//open system admin GUI
						SystemAdminGUI frame = new SystemAdminGUI();
						frame.setVisible(true);
					}
					//temp == false, office does not exist!
					else {
						JOptionPane.showMessageDialog(null, "Officer does not exist!");
					}
					//System.out.println(temp);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton, "cell 2 7 2 1,grow");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 9 4 1,grow");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open system admin GUI
				SystemAdminGUI frame = new SystemAdminGUI();
				frame.setVisible(true);
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack, "cell 0 10 4 1,grow");
		
		JButton btnSignOut = new JButton("SIGN OUT");
		btnSignOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open log in GUI
				UserLoginGUI window = new UserLoginGUI();
				window.frmParkingApplication.setVisible(true);
			}
		});
		btnSignOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnSignOut, "cell 0 11 4 1,growx");
	}

}
