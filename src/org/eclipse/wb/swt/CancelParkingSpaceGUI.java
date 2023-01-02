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
import net.miginfocom.swing.MigLayout;

public class CancelParkingSpaceGUI extends JFrame {

	private JPanel contentPane;
	private JTextField ID;
	
	private String username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelParkingSpaceGUI frame = new CancelParkingSpaceGUI();
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
	public CancelParkingSpaceGUI() {
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
		
		JLabel lblNewLabel_1 = new JLabel("Cancellations");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1, "cell 0 1 2 1,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Booking ID : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 2,alignx trailing,aligny baseline");
		
		ID = new JTextField();
		ID.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(ID, "cell 1 2,growx,aligny baseline");
		ID.setColumns(10);
		
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
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookingID = ID.getText().trim();
				if(bookingID.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields must be filled!");
					return;
				}
				
				Booking b = new Booking();
				String booking = "";
				try {
					booking = b.customerCancelValid(username.trim(), bookingID);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(booking == null) {
					JOptionPane.showMessageDialog(null, "Booking ID is not associated with your username!");
					return;
				}
				
				boolean before = b.beforeExpiry(booking);
				if(before == false) {
					JOptionPane.showMessageDialog(null, "Expiry time already passed!");
					return;
				}
				else {
					
					boolean wait = true;
					try {
						b.remove(booking);
						wait = false;
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					while(wait) {
						try {
							Thread.sleep(250);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					try {
						System.out.println("addcancellation: "+b.addCancellations(booking));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Cancellation successfully Requested! Please, wait for Officer Aproval.");
					
					//Close JPanel window
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					//open log in GUI
					CustomerGUI frame = new CustomerGUI();
					frame.setUsername(username);
					frame.setVisible(true);
					
				}
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
	}

}
