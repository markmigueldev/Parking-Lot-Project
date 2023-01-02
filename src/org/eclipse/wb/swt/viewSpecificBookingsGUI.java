package org.eclipse.wb.swt;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import backend.Booking;
import backend.ParkingSpace;
import net.miginfocom.swing.MigLayout;

public class viewSpecificBookingsGUI extends JFrame {

	private JPanel contentPane;

	private JTextField username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewSpecificBookingsGUI frame = new viewSpecificBookingsGUI();
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
	public viewSpecificBookingsGUI() {
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
		
		JLabel lblNewLabel_1 = new JLabel("View Bookings");
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
				viewBookingOfficerGUI frame = new viewBookingOfficerGUI();
				frame.setVisible(true);
				
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("Customer Username : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 3,alignx trailing,aligny baseline");
		
		username = new JTextField();
		username.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(username, "cell 1 3,growx");
		username.setColumns(10);
		
		JButton viewBookings = new JButton("SUBMIT");
		viewBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String um = username.getText().trim();
				//must be filled
				if(um.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
				}
				else {
					
					Booking b = new Booking();
					boolean exist = false;
					try {
						exist = b.customerExist(um);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					if(exist) {
						//Close JPanel window
						JComponent comp = (JComponent) e.getSource();
						Window win = SwingUtilities.getWindowAncestor(comp);
						win.dispose();
						//open log in GUI
						viewSpecificBookingsGUIPart2 frame = new viewSpecificBookingsGUIPart2();
						try {
							frame.displayBookings(um);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						frame.setUsername(username.getText().trim());
						frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Username does not exist!");
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
