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

import backend.Authenticator;
import backend.Booking;
import backend.ParkingSpace;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;

public class CustomerBookingGUI extends JFrame {

	private JPanel contentPane;
	private JTextField parkingSpaceNumber;
	private JTextField licensePlateNumber;
	private JTextField bookingTime;

	private String info;
	private String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerBookingGUI frame = new CustomerBookingGUI();
					frame.setVisible(true);
					//frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CustomerBookingGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[250px,grow][244px,grow]", "[50px][][50px][50px][50px][50px][50px][50px][50px][][]"));
		
		JLabel lblNewLabel = new JLabel("CUSTOMER PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 2 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Book Space");
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
		
		JLabel lblNewLabel_2 = new JLabel("Parking Space Number : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 2,alignx trailing,aligny baseline");
		
		parkingSpaceNumber = new JTextField();
		parkingSpaceNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(parkingSpaceNumber, "cell 1 2,growx,aligny baseline");
		parkingSpaceNumber.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Expiry Date & Time : ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2_1, "cell 0 3,alignx trailing,aligny baseline");
		
		bookingTime = new JTextField();
		bookingTime.setText("yyyy-MM-dd HH:mm:ss");
		bookingTime.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bookingTime.setColumns(10);
		contentPane.add(bookingTime, "cell 1 3,growx,aligny baseline");
		
		JLabel lblNewLabel_2_1_1 = new JLabel("License Plate Number : ");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2_1_1, "cell 0 4,alignx trailing,aligny baseline");
		
		licensePlateNumber = new JTextField();
		licensePlateNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		licensePlateNumber.setColumns(10);
		contentPane.add(licensePlateNumber, "cell 1 4,growx,aligny baseline");
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String psn = parkingSpaceNumber.getText().trim();	
				
				String bt = bookingTime.getText().trim();
				String lpn = licensePlateNumber.getText().trim();

				//check if fields are empty
				if(psn.isEmpty() || bt.isEmpty() || lpn.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields must be filled!");
				}
				
				else {
					
					//check if psn is a number
					int isInt;
					try {
						isInt = Integer.parseInt(psn);
					}
					catch(Exception e1){
						JOptionPane.showMessageDialog(null, "Parking Space Number must be a number!");
						return;
					}
					
					ParkingSpace ps = new ParkingSpace();
					
					//2. check if parking spot number is valid
					boolean alreadyExist;
					try {
						alreadyExist = ps.alreadyExist(isInt);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error!: alreadyExist = ps.alreadyExist(isInt)");
						return;
					}
					
					Booking booking = new Booking();
					//3.
					//check if expire time inputed is valid
					boolean validTime = booking.validateTime(bt);
					//System.out.println(bt);
					
					//4. minimum length of time is 1hour
					//check if time length is less than 1 hour
					boolean minTime = booking.minTime(bt);
					
					//5. check if customer already requested the parking spot
					boolean spotRequested = false;
					try {
						List<String> requests = booking.getCustomerRequests(username);
						for(int i=0;i<requests.size();i++) {
							String s = username + "," + psn + ",";
							if (requests.get(i).contains(s)) spotRequested = true;
						}
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					//2.
					if(!alreadyExist) {
						JOptionPane.showMessageDialog(null, "Parking spot number does not exist!");
					}
					//3.
					else if(!validTime) {
						JOptionPane.showMessageDialog(null, "Expiry time must be a valid future time! (yyyy-MM-dd HH:mm:ss)");
					}
					else if(!minTime) {
						JOptionPane.showMessageDialog(null, "The minimum expiry time should atleast 1 hour!");
					}
					else if(spotRequested) {
						JOptionPane.showMessageDialog(null, "Parking spot number already requested!");
					}
					//we add the request into requests.txt
					else {
						
						//6. customer can only book a maximum of 3 times.
						
						int req=0;
						int rej=0;
						int g=0,p=0,c=0;
						//System.out.println(req+rej+g+p+c);
						
						try {
							req = booking.getCustomerRequests(username).size();
							//rej = booking.getCustomerRejected(username).size();
							g = booking.getCustomerGranted(username).size();
							p = booking.getCustomerPaid(username).size();
							c = booking.getCustomerConfirmed(username).size();
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						if((req+rej+g+p+c) >= 3) {
							JOptionPane.showMessageDialog(null, "Customers can only book up to 3 parking spaces!");
						}
						else {
							//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
							//SKIPPING THIS FOR NOW.
							//IMPLEMENT LATER
							//check if person requested 3 times already
							
							List<String> split = Arrays.asList(info.split(","));
							String information = split.get(0)+","+split.get(1)+","+split.get(2)+","+split.get(3);
							booking.addRequests(information, psn, bt, lpn);
							
							JOptionPane.showMessageDialog(null, "Space successfully requested! Please wait for an Officer to grant your request!");

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
					
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton, "cell 1 5,growx,aligny baseline");
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
	
	
	public void setCustomerInfo(String info) {
		this.info = info;
		System.out.println("The info is: "+this.info);
	}
	public void setUsername(String username) {
		this.username =username;
		System.out.println("The username is: "+this.username);
	}

}
