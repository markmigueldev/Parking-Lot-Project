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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;

public class ParkingEnforcementOfficerGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ParkingEnforcementOfficerGUI frame = new ParkingEnforcementOfficerGUI();
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
	public ParkingEnforcementOfficerGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton manageParking = new JButton("Manage Parking");
		manageParking.addActionListener(new ActionListener() {
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
		contentPane.setLayout(new MigLayout("", "[484px,grow]", "[45px][45px][45px][40px][][][][][][][][grow][]"));
		
		JLabel lblNewLabel = new JLabel("OFFICER PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0,grow");
		manageParking.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(manageParking, "cell 0 1,grow");
		
		JButton viewBookings = new JButton("View Bookings");
		viewBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open viewBookingOfficer
				viewBookingOfficerGUI frame = new viewBookingOfficerGUI();
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
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 11,grow");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack, "cell 0 12,grow");
	}

}
