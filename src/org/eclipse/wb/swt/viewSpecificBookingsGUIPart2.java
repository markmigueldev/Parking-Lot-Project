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
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import backend.Booking;
import net.miginfocom.swing.MigLayout;

public class viewSpecificBookingsGUIPart2 extends JFrame {

	private JPanel contentPane;

	JTextArea currentBookings = new JTextArea("");
	JLabel lblNewLabel_2 = new JLabel("Current Bookings : ");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewSpecificBookingsGUIPart2 frame = new viewSpecificBookingsGUIPart2();
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
	public viewSpecificBookingsGUIPart2() {
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setBounds(100, 100, 1500, 750);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[250px,grow][244px,grow]", "[50px][][50px][50px,grow][50px][50px][40px][][grow][][]"));
		
		JLabel lblNewLabel = new JLabel("CUSTOMER PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 2 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("View Bookings");
		lblNewLabel_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1, "cell 0 1 2 1,alignx center");
		
		
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 2,alignx leading,aligny baseline");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				
				//open viewBookingOfficer();
				viewSpecificBookingsGUI frame = new viewSpecificBookingsGUI();
				frame.setVisible(true);
				
			}
		});
		
		//EDIT CURRENTBOOKINGS
		currentBookings.setEditable(false);
		currentBookings.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(currentBookings, "cell 0 3 2 6,grow");
		
		
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

	
	//DISPLAY THE APPROPRIATE BOOKINGS FOR GIVE USERNAME
	public void displayBookings(String username) throws IOException {
		//currentBookings.append("Display Bookings: " +username);
		Booking b = new Booking();
		List<String> requests = new ArrayList<String>();
		List<String> rejected =  new ArrayList<String>();
		List<String> granted = new ArrayList<String>();
		List<String> paid = new ArrayList<String>();
		List<String> confirmed = new ArrayList<String>();
		List<String> cancellations = new ArrayList<String>();
		
		requests = b.getCustomerRequests(username);
		rejected = b.getCustomerRejected(username);
		granted = b.getCustomerGranted(username);
		paid = b.getCustomerPaid(username);
		confirmed = b.getCustomerConfirmed(username);
		cancellations = b.getCustomerCancellations(username);
		
		if(rejected.size() != 0) {
			currentBookings.append("REMOVED REQUESTS (Check The Reason for Details): \n");
			for(int i = 0; i<rejected.size(); i++) {
				List<String> l = Arrays.asList(rejected.get(i).split(","));
				String s = String.format("Firstname: %-5s     Lastname: %-5s     Email: %-5s     Username: %-5s     Parking Number: %-5s     Expiry Time: %-5s     License Plate: %s     Booking ID: %s     Reason: %s%n",l.get(0),l.get(1),l.get(2),l.get(3),l.get(4),l.get(5),l.get(6),l.get(7),l.get(8));
				currentBookings.append(s);
			}
			currentBookings.append("\n\n");
		}
		if(cancellations.size() != 0) {
			currentBookings.append("CANCELLATIONS REQUESTS (Pending Officer Aproval):\n");
			for(int i = 0; i<cancellations.size(); i++) {
				List<String> l = Arrays.asList(cancellations.get(i).split(","));
				String s = String.format("Firstname: %-5s     Lastname: %-5s     Email: %-5s     Username: %-5s     Parking Number: %-5s     Expiry Time: %-5s     License Plate: %s     Booking ID: %s%n",l.get(0),l.get(1),l.get(2),l.get(3),l.get(4),l.get(5),l.get(6),l.get(7));
				currentBookings.append(s);
			}
			currentBookings.append("\n\n");
		}
		if(requests.size() != 0) {
			currentBookings.append("REQUESTED REQUESTS (Pending Officer Aproval): \n");
			for(int i = 0; i<requests.size(); i++) {
				List<String> l = Arrays.asList(requests.get(i).split(","));
				String s = String.format("Firstname: %-5s     Lastname: %-5s     Email: %-5s     Username: %-5s     Parking Number: %-5s     Expiry Time: %-5s     License Plate: %s     Booking ID: %s%n",l.get(0),l.get(1),l.get(2),l.get(3),l.get(4),l.get(5),l.get(6),l.get(7));
				currentBookings.append(s);
			}
			currentBookings.append("\n\n");
		}
		if(granted.size() != 0) {
			currentBookings.append("GRANTED REQUESTS (Pending Customer Payment):\n");
			for(int i = 0; i<granted.size(); i++) {
				List<String> l = Arrays.asList(granted.get(i).split(","));
				String s = String.format("Firstname: %-5s     Lastname: %-5s     Email: %-5s     Username: %-5s     Parking Number: %-5s     Expiry Time: %-5s     License Plate: %s     Booking ID: %s%n",l.get(0),l.get(1),l.get(2),l.get(3),l.get(4),l.get(5),l.get(6),l.get(7));
				currentBookings.append(s);
			}
			currentBookings.append("\n\n");
		}
		if(paid.size() != 0) {
			currentBookings.append("PAID REQUESTS (Pending Admin Confirmation):\n");
			for(int i = 0; i<paid.size(); i++) {
				List<String> l = Arrays.asList(paid.get(i).split(","));
				String s = String.format("Firstname: %-5s     Lastname: %-5s     Email: %-5s     Username: %-5s     Parking Number: %-5s     Expiry Time: %-5s     License Plate: %s     Booking ID: %s%n",l.get(0),l.get(1),l.get(2),l.get(3),l.get(4),l.get(5),l.get(6),l.get(7));
				currentBookings.append(s);
			}
			currentBookings.append("\n\n");
		}
		if(confirmed.size() != 0) {
			currentBookings.append("CONFIRMED REQUESTS:\n");
			for(int i = 0; i<confirmed.size(); i++) {
				List<String> l = Arrays.asList(confirmed.get(i).split(","));
				String s = String.format("Firstname: %-5s     Lastname: %-5s     Email: %-5s     Username: %-5s     Parking Number: %-5s     Expiry Time: %-5s     License Plate: %s     Booking ID: %s%n",l.get(0),l.get(1),l.get(2),l.get(3),l.get(4),l.get(5),l.get(6),l.get(7));
				currentBookings.append(s);
			}
			currentBookings.append("\n\n");
		}


	}

	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.lblNewLabel_2.setText(username+"'s Current Bookings: ");
		
	}

}
