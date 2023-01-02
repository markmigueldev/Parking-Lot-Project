package org.eclipse.wb.swt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import backend.Booking;
import backend.ParkingSpace;
import net.miginfocom.swing.MigLayout;

public class CustomerGUI extends JFrame {

	private JPanel contentPane;
	
	private String username; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI();
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
	public CustomerGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton manageParking = new JButton("Book Space");
		manageParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Booking b = new Booking();
				
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open CustomerBookingGUI
				CustomerBookingGUI frame = new CustomerBookingGUI();
				frame.setUsername(username);
				try {
					frame.setCustomerInfo(b.getCustomerInfo(username));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.print("frame.setCustomerInfo Error! line: 77");
				}
				frame.setVisible(true);
			}
		});
		contentPane.setLayout(new MigLayout("", "[470px,grow]", "[45px][45px][45px][45px][45px][38px][33px][][][][][grow][]"));
		
		JLabel lblNewLabel = new JLabel("CUSTOMER PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0,grow");
		manageParking.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(manageParking, "cell 0 1,grow");
		
		JButton viewBookings = new JButton("Cancellations");
		viewBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open CancelParkingSpaceGUI
				CancelParkingSpaceGUI frame = new CancelParkingSpaceGUI();
				frame.setUsername(username);
				frame.setVisible(true);
				
			}
		});
		viewBookings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(viewBookings, "cell 0 2,grow");
		
		JButton btnBack = new JButton("SIGN OUT");
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
		
		JButton btnViewBookings_1 = new JButton("Pay");
		btnViewBookings_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open PaymentGUI
				PaymentGUI frame = new PaymentGUI();
				frame.setUsername(username);
				
				frame.setVisible(true);
			}
		});
		btnViewBookings_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnViewBookings_1, "cell 0 3,grow");
		
		JButton btnViewBookings = new JButton("View Bookings");
		btnViewBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Booking b = new Booking();
				ParkingSpace ps = new ParkingSpace();
				
				//4.4.3-REQ-7: The system must display an error message if the parking space the customer booked is occupied in the system
				List<String> rejected = new ArrayList<String>();
				try {
					rejected = b.getCustomerRejected(username);
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					System.out.println("Please restart the app and Sign IN!");
				}
				if(rejected.size() != 0) {
					for(int i=0;i<rejected.size();i++) {
						List<String> l = Arrays.asList(rejected.get(i).split(","));
						if(rejected.get(i).endsWith("Occupied!")) {
							JOptionPane.showMessageDialog(null, "Parking Spot Number "+l.get(4)+" is Occupied! Booking ID "+l.get(7)+" was removed!");
							try {
								b.removeRejected(rejected.get(i));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							//wait
							//add to rejected.txt
							try {
								b.addRejectedVersion2(rejected.get(i)+" ");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						}
					}
				}
				
				//4.7.3-REQ-3: The system must notify the customer when their parking space booking is expired
				List<String> confirmed = new ArrayList<String>();
				try {
					confirmed = b.getCustomerConfirmed(username);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					System.out.println("Please restart the app and Sign IN!");
				}
				if(confirmed.size() != 0) {
					for(int i=0; i<confirmed.size();i++) {
						//check if expired
						boolean expired = b.expired(confirmed.get(i));
						if(expired) {
							//if expired: confirmed.txt -> rejected.txt
							try {
								b.removeConfirmed(confirmed.get(i));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							//wait
							//add to rejected.txt
							try {
								b.addRejected(confirmed.get(i),"Time Expired!");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							List<String> l = Arrays.asList(confirmed.get(i).split(","));
							
							//free parking spot
							try {
								ps.makeVacant(Integer.parseInt(l.get(4)));
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							JOptionPane.showMessageDialog(null, "Booking ID "+l.get(7)+" has expired! Please vacate Parking Spot Number "+l.get(4)+" Thank You!");
						}
					}
				}
				
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open viewBookingGUI
				viewBookingGUI frame = new viewBookingGUI();
				
				frame.setUsername(username);
				//DISPLAY THE BOOKINGS
				
				try {
					frame.displayBookings(username);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				
				frame.setVisible(true);
				
				
				

			}
		});
		btnViewBookings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnViewBookings, "cell 0 4,grow");
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, "cell 0 11,grow");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack, "cell 0 12,growx,aligny top");
	}
	
	public void setUsername(String username) {
		this.username = username;
		System.out.println("The username is: "+this.username);
	}
	public String getUsername() {
		return this.username;
	}

}
