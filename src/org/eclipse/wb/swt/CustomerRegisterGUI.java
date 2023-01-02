package org.eclipse.wb.swt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import backend.Register;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Window;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class CustomerRegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField usernameTextField;
	private JTextField email;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerRegisterGUI frame = new CustomerRegisterGUI();
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
	public CustomerRegisterGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[155px][68px][244px,grow]", "[50px][30px][30px][30px][30px][30px][40px][][][][][grow][]"));
		
		JLabel lblNewLabel = new JLabel("CUSTOMER REGISTRATION");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 3 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("First Name :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1, "cell 0 1,alignx right,growy");
		
		firstName = new JTextField();
		contentPane.add(firstName, "cell 1 1 2 1,grow");
		firstName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1, "cell 0 2,alignx right,growy");
		
		lastName = new JTextField();
		lastName.setColumns(10);
		contentPane.add(lastName, "cell 1 2 2 1,grow");
		
		JLabel lblNewLabel_1_2 = new JLabel("Username :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_2, "cell 0 4,alignx right,growy");
		
		usernameTextField = new JTextField();
		usernameTextField.setColumns(10);
		contentPane.add(usernameTextField, "cell 1 4 2 1,grow");
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1_1, "cell 0 5,alignx right,growy");
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField, "cell 1 5 2 1,grow");
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Email :");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1_2, "cell 0 3,grow");
		
		email = new JTextField();
		email.setColumns(10);
		contentPane.add(email, "cell 1 3 2 1,grow");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open log in GUI
				CustomerLogInGUI frame = new CustomerLogInGUI();
				frame.setVisible(true);
				
			}
		});
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 11 3 1,grow");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack, "flowx,cell 0 12 2 1,grow");
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//trim the string in case there are spaces, making sure it is truly empty in human sense
				String fName = firstName.getText().trim();
				String lName = lastName.getText().trim();
				String em = email.getText().trim();
				String username = usernameTextField.getText().trim();
				String password = new String(passwordField.getPassword()).trim();
				
				//Check if inputs contains "," 
				//Commas are not allowed since it used as a separator in the database
				boolean containsComma = fName.contains(",") || lName.contains(",") || em.contains(",") || username.contains(",") || password.contains(",");
				if(containsComma) {
					JOptionPane.showMessageDialog(null, "The use of character ',' is forbidden!");
				}
				//Check if some fields are empty
				else if(fName.isEmpty() || lName.isEmpty() || em.isEmpty() || username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields must be filled!");
				}
				else {
					Register reg = new Register();
					//check if customer email or username is already taken
					if(reg.customerUsernameEmailExist(username, em)) {
						JOptionPane.showMessageDialog(null, "Email and/or Username already taken!");
					}
					else {
						reg.registerCustomer(fName, lName, em, username, password);
						
						JOptionPane.showMessageDialog(null, "Registration successful!");
						
						//Close JPanel window
						JComponent comp = (JComponent) e.getSource();
						Window win = SwingUtilities.getWindowAncestor(comp);
						win.dispose();
						//open log in GUI
						CustomerLogInGUI frame = new CustomerLogInGUI();
						frame.setVisible(true);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton, "cell 2 12,grow");
		
	}

}
