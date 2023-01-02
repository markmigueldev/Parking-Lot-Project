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

import backend.ParkingSpace;
import net.miginfocom.swing.MigLayout;

public class RemoveSpaceGUI extends JFrame {

	private JPanel contentPane;
	private JTextField psn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoveSpaceGUI frame = new RemoveSpaceGUI();
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
	public RemoveSpaceGUI() {
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
		
		JLabel lblNewLabel_1_1 = new JLabel("Remove Space");
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
		
		JLabel lblNewLabel_2 = new JLabel("Parking Space Number : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 3,alignx trailing,aligny baseline");
		
		psn = new JTextField();
		psn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(psn, "cell 1 3,growx");
		psn.setColumns(10);
		
		JButton viewBookings = new JButton("SUBMIT");
		viewBookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = psn.getText().trim();
				//must be filled
				if(s.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Fields cannot be empty!");
					return;
				}
				boolean isInt = false;
				int parkingSpotNumber = 0;
				try {
					parkingSpotNumber = Integer.parseInt(s);
					isInt = true;
					System.out.println(parkingSpotNumber);
				}catch(NumberFormatException e1) { 
			        System.out.println("Not a number!");
			        
			    } catch(NullPointerException e1) {
			    	System.out.println("no input!");
			    }
				if(!isInt) {
					JOptionPane.showMessageDialog(null, "Input must be a number!");
				}
				else if (parkingSpotNumber < 0){
					JOptionPane.showMessageDialog(null, "Input must be a postive number!");
				}
				else {
					ParkingSpace ps = new ParkingSpace();
					boolean vacant = false;
					try {
						vacant = ps.removeSpace(parkingSpotNumber);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(vacant) {
						JOptionPane.showMessageDialog(null, "Parking Space Removed!");
						//Close JPanel window
						JComponent comp = (JComponent) e.getSource();
						Window win = SwingUtilities.getWindowAncestor(comp);
						win.dispose();
						//open manageParkingGUI
						manageParkingGUI frame = new manageParkingGUI();
						frame.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Parking Space is Occupied!");
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
