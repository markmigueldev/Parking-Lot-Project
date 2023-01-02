package org.eclipse.wb.swt;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JProgressBar;
import net.miginfocom.swing.MigLayout;

public class UserLoginGUI {

	JFrame frmParkingApplication;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLoginGUI window = new UserLoginGUI();
					window.frmParkingApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserLoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmParkingApplication = new JFrame();
		frmParkingApplication.setResizable(false);
		frmParkingApplication.setTitle("Parking Application");
		frmParkingApplication.setBounds(100, 100, 500, 500);
		frmParkingApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmParkingApplication.getContentPane().setLayout(new MigLayout("", "[494px]", "[50px][50px][50px][50px][50px]"));
		
		JLabel lblNewLabel_1 = new JLabel("WELCOME!");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		frmParkingApplication.getContentPane().add(lblNewLabel_1, "cell 0 0,grow");
		
		JButton btnNewButton = new JButton("Administrator");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open customer log in GUI
				AdminLogInGUI frame = new AdminLogInGUI();
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmParkingApplication.getContentPane().add(btnNewButton, "cell 0 4,grow");
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open customer log in GUI
				CustomerLogInGUI frame = new CustomerLogInGUI();
				frame.setVisible(true);
			}
		});
		frmParkingApplication.getContentPane().add(btnCustomer, "cell 0 2,grow");
		
		JButton btnNewButton_1_1 = new JButton("Parking Officer");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open customer log in GUI
				OfficerLogInGUI frame = new OfficerLogInGUI();
				frame.setVisible(true);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		frmParkingApplication.getContentPane().add(btnNewButton_1_1, "cell 0 3,grow");
		
		JLabel lblNewLabel_1_1 = new JLabel("Select User");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frmParkingApplication.getContentPane().add(lblNewLabel_1_1, "cell 0 1,grow");
	}
}
