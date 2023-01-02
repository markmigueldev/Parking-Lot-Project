package org.eclipse.wb.swt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import backend.Authenticator;
import net.miginfocom.swing.MigLayout;

public class AdminLogInGUI extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminLogInGUI frame = new AdminLogInGUI();
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
	public AdminLogInGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[155px][10px][329px,grow]", "[50px][40px][40px][40px][40px][][][][][][][grow][]"));
		
		JLabel lblNewLabel = new JLabel("ADMINISTRATOR");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 3 1,grow");
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		contentPane.add(usernameField, "cell 2 1,grow");
		
		JLabel lblNewLabel_1_2 = new JLabel("Username :");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_2, "cell 0 1,alignx right,aligny top");
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Password :");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1_1, "cell 0 2,alignx right,aligny top");
		
		passwordField = new JPasswordField();
		contentPane.add(passwordField, "cell 2 2,grow");
		
		JButton btnNewButton_1 = new JButton("SIGN IN");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText().trim();
				String password = new String(passwordField.getPassword()).trim();
				
				//check if field are empty
				if(username.isEmpty() || password.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields must be filled!");
				}
				else {
					Authenticator log = new Authenticator();
					boolean valid = log.verifyAdmin(username, password);
					if(!valid) {
						JOptionPane.showMessageDialog(null, "Invalid Username and/or Password!");
					}
					else {
						//Close JPanel window
						JComponent comp = (JComponent) e.getSource();
						Window win = SwingUtilities.getWindowAncestor(comp);
						win.dispose();
						//open system admin GUI
						SystemAdminGUI frame = new SystemAdminGUI();
						frame.setVisible(true);
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnNewButton_1, "cell 2 3,grow");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
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
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 11 3 1,grow");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack, "cell 0 12 3 1,grow");
	}

}
