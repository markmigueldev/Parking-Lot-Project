package org.eclipse.wb.swt;

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

@SuppressWarnings("serial")
public class GrantGUI extends JFrame {

	private JPanel contentPane;
	private JTextField psn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrantGUI frame = new GrantGUI();
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
	public GrantGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[250px,grow][244px,grow]", "[50px][][][50px][50px][50px][50px][40px][][grow][][]"));
		
		JLabel lblNewLabel = new JLabel("OFFICER PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 2 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Manage Parking");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1, "cell 0 1 2 1,alignx center");
		
		JLabel lblNewLabel_1_1 = new JLabel("Grant Request");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1, "cell 0 2 2 1,alignx center");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open log in GUI
				manageParkingGUI frame = new manageParkingGUI();
				frame.setVisible(true);
				
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Booking ID : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 3,alignx trailing,aligny baseline");
		
		psn = new JTextField();
		psn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(psn, "cell 1 3,growx");
		psn.setColumns(10);
		
		JButton viewBookings = new JButton("SUBMIT");
		viewBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bookingID = psn.getText().trim();
				//must be filled
				if(bookingID.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
				}
				else {
					//check if booking ID exist
					ParkingSpace ps = new ParkingSpace();
					Booking b = new Booking();
					try {
						boolean exist = b.requestExist(bookingID);
						//boolean exist=true;
						if(!exist) {
							JOptionPane.showMessageDialog(null, "Booking ID does not exist!");
							
						}
						else{
							//if BOOKING ID exist
							//get parking spot number
							int psn = b.getParkingSpotNumber(bookingID);
							//check if vacant
							boolean vacant = ps.isVacant(psn);
							//1. if it is not vacant reject it
							if(!vacant) {
								JOptionPane.showMessageDialog(null, "Parking spot number is Occupied! Customer request rejected!");
								//add to rejected.txt
								//ps.removeRequest(bookingID);
								//ps.reject(bookingID, "1aff84501aff84501aff84501aff8450");
								b.reject(bookingID, "Parking Spot Number is Occupied!");
								b.removeRequest(bookingID);
								
							}
							else {
								b.addGranted(bookingID);
								b.removeRequest(bookingID);
								
								JOptionPane.showMessageDialog(null, "Booking ID successfully granted!");
								
								//Close JPanel window
								JComponent comp = (JComponent) e.getSource();
								Window win = SwingUtilities.getWindowAncestor(comp);
								win.dispose();
								//open log in GUI
								manageParkingGUI frame = new manageParkingGUI();
								frame.setVisible(true);
							}
						}
					} catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}

			}
		});
		viewBookings.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(viewBookings, "cell 1 4,growx,aligny baseline");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 10 2 1,grow");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack, "cell 0 11,grow");
		
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
		contentPane.add(btnBack_1, "cell 1 11,grow");
	}

}
