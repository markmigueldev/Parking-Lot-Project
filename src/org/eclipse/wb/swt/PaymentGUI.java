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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import backend.Booking;
import backend.ParkingSpace;
import net.miginfocom.swing.MigLayout;

public class PaymentGUI extends JFrame {

	private JPanel contentPane;
	private JTextField psn;
	
	private String username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentGUI frame = new PaymentGUI();
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
	public PaymentGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[250px,grow][244px,grow]", "[50px][][50px][50px][50px][50px][40px][][grow][][]"));
		
		JLabel lblNewLabel = new JLabel("CUSTOMER PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 2 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Pay");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1, "cell 0 1 2 1,alignx center");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open log in GUI
				CustomerGUI frame = new CustomerGUI();
				frame.setUsername(username);
				frame.setVisible(true);
				
			}
		});
		
		JLabel lblNewLabel_2_1 = new JLabel("Parking Space Number : ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2_1, "cell 0 2,alignx trailing");
		
		psn = new JTextField();
		psn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(psn, "cell 1 2,growx,aligny baseline");
		psn.setColumns(10);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String input = psn.getText().trim();
				//check if parking space number is in granted
				Booking b = new Booking();
				boolean exist = false;
				try {
					exist = b.existGranted(username, input);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("File does not exist!");
				}
				//check if integer
				boolean isInt = false;
				int number = -1;
				try {  
				    number = Integer.parseInt(input);  
				    isInt = true;
				  } catch(NumberFormatException e1){  
					  System.out.println("Input is not a number!");
				  }  
				//check if parking spot number is valid
				ParkingSpace ps = new ParkingSpace();
				boolean alreadyExist = false;
				try {
					if(isInt)
						alreadyExist = ps.alreadyExist(number);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Invalid parking spot number!");
					return;
				}
				if(input.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields must be filled!");
				}
				else if(!isInt) {
					JOptionPane.showMessageDialog(null, "Input must be a number!");
				}
				else if(!alreadyExist) {
					JOptionPane.showMessageDialog(null, "Parking space number does not exist!");
				}
				else if(!exist) {
					JOptionPane.showMessageDialog(null, "Parking spot number is not associated with your account or Officer have not yet granted request or Request was rejected!");
				}
				else {
					
					//check if username is not associated with psn
					
					
					//if valid 
					//1. calculate cost
					//2. open paymentGUI part2
					//3. display parking spot number
					//4. display amount
					
					//1.
					String time = b.getTime(input);
					double amount = b.calculateAmount(time, 2);
					
					//2.
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					//open PaymentGUIPart2
					PaymentGUIPart2 frame = new PaymentGUIPart2();
					//frame.get
					
					//3 and 4
					frame.setPsnAmount(input, Double.toString(amount));
					frame.setUsername(username);
					frame.setVisible(true);
				}
				/**
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open PaymentGUIPart2
				PaymentGUIPart2 frame = new PaymentGUIPart2();
				frame.setVisible(true);
				*/
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton, "cell 1 3,growx,aligny baseline");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 9 2 1,grow");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack, "cell 0 10,grow");
		
		JButton btnBack_1 = new JButton("SIGN OUT");
		btnBack_1.addActionListener(new ActionListener() {
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
		btnBack_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack_1, "cell 1 10,grow");
	}

	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username = username;
		System.out.println("The username is: "+this.username);
	}

}
