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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import backend.Booking;
import backend.ParkingSpace;
import backend.Register;
import net.miginfocom.swing.MigLayout;

public class managePaymentStatusGUI extends JFrame {

	private JPanel contentPane;
	private JTextField firstName;
	private JTextField lastName;
	private JTextField psn;
	private JTextField email;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managePaymentStatusGUI frame = new managePaymentStatusGUI();
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
	public managePaymentStatusGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[155px,grow][68px][244px]", "[50px][][30px][30px][30px][30px][30px][30px][grow][][][]"));
		
		JLabel lblNewLabel = new JLabel("ADMINISTRATOR PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 3 1,grow");
		
		JLabel lblNewLabel_2 = new JLabel("Manage Payment Status");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 1 3 1,alignx center,growy");
		
		JLabel lblNewLabel_1 = new JLabel("First Name :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1, "cell 0 2,alignx right,growy");
		
		firstName = new JTextField();
		contentPane.add(firstName, "cell 1 2 2 1,grow");
		firstName.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1, "cell 0 3,alignx right,growy");
		
		lastName = new JTextField();
		lastName.setColumns(10);
		contentPane.add(lastName, "cell 1 3 2 1,grow");
		
		JLabel lblNewLabel_1_2 = new JLabel("Parking Space Number : ");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_2, "cell 0 5,alignx right,growy");
		
		psn = new JTextField();
		psn.setColumns(10);
		contentPane.add(psn, "cell 1 5 2 1,grow");
		
		email = new JTextField();
		email.setColumns(10);
		contentPane.add(email, "cell 1 4 2 1,grow");
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Email :");
		lblNewLabel_1_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1_1_2, "cell 0 4,grow");
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//firstName,LastName,email,username,password,assignedID
				String fName = firstName.getText().trim();
				String lName = lastName.getText().trim();
				String em = email.getText().trim();
				String parking = psn.getText().trim();
				
				Booking b = new Booking();
				
				if(fName.isEmpty() || lName.isEmpty() || em.isEmpty() || parking.isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields must be filled!");
					return;
				}
				
				List<String> list = new ArrayList<String>();
				try {
					list = b.getCustomerPaidVersion2(fName,lName,em,parking);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(list.size() == 0) {
					JOptionPane.showMessageDialog(null, "Invalid Customer info or Request does not exist or Customer has not paid yet!");
				}
				else {
					//find booking info
					String line = "";
					for(int i=0;i<list.size();i++) {
						if(list.get(i).contains(fName+","+lName+","+em+",") && list.get(i).contains(","+parking+",")) {
							line=list.get(i);
							break;
						}
					}
					//remove booking from paid.txt
					boolean wait=true;
					try {
						b.remove(line);
						wait=false;
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
					//add to confirmed.txt
					try {
						b.addConfirmedVersion2(line);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//make the parking spot occupied
					List<String> split = Arrays.asList(line.split(","));
					ParkingSpace ps = new ParkingSpace();
					try {
						ps.makeOccupied(Integer.parseInt(split.get(4)));
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Request's payment successfully confirmed!");
					
					//START THE COUNT DOWN
					
					
					//Close JPanel window
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					//open system admin GUI
					SystemAdminGUI frame = new SystemAdminGUI();
					frame.setVisible(true);
					
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton, "cell 1 6 2 1,grow");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 9 3 1,grow");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open system admin GUI
				SystemAdminGUI frame = new SystemAdminGUI();
				frame.setVisible(true);
				
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack, "cell 0 10 3 1,grow");
		
		JButton btnSignOut = new JButton("SIGN OUT");
		btnSignOut.addActionListener(new ActionListener() {
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
		btnSignOut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnSignOut, "cell 0 11 3 1,growx");
	}

}
