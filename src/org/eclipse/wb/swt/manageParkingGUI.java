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
import net.miginfocom.swing.MigLayout;

public class manageParkingGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					manageParkingGUI frame = new manageParkingGUI();
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
	public manageParkingGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[250px,grow][244px]", "[50px][][50px][50px][50px][50px][40px][][grow][][]"));
		
		JLabel lblNewLabel = new JLabel("OFFICER PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 2 1,grow");
		
		JButton viewBookings = new JButton("Remove Space");
		viewBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open RemoveSpaceGUI
				RemoveSpaceGUI frame = new RemoveSpaceGUI();
				frame.setVisible(true);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Manage Parking");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1, "cell 0 1 2 1,alignx center");
		viewBookings.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(viewBookings, "cell 0 3 2 1,grow");
		
		JButton manageParking = new JButton("Add Space");
		manageParking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open AddSpaceGUI
				AddSpaceGUI frame = new AddSpaceGUI();
				frame.setVisible(true);
			}
		});
		manageParking.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(manageParking, "cell 0 2 2 1,grow");
		
		JButton btnCancelRequest = new JButton("Cancel Request");
		btnCancelRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open CancelParkingSpaceOfficerGUI
				CancelParkingSpaceOfficerGUI frame = new CancelParkingSpaceOfficerGUI();
				frame.setVisible(true);
			}
		});
		btnCancelRequest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnCancelRequest, "cell 0 4 2 1,grow");
		
		JButton btnGrantRequest = new JButton("Grant Request");
		btnGrantRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open GrantGUI
				GrantGUI frame = new GrantGUI();
				frame.setVisible(true);
			}
		});
		btnGrantRequest.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(btnGrantRequest, "cell 0 5 2 1,grow");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open log in GUI
				ParkingEnforcementOfficerGUI frame = new ParkingEnforcementOfficerGUI();
				frame.setVisible(true);
				
			}
		});
		
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

}
