package org.eclipse.wb.swt;

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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import backend.Booking;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class PaymentGUIPart3Paypal extends JFrame {

	private JPanel contentPane;
	private JTextField email;
	private JPasswordField password;

	JLabel psn = new JLabel("display psn");
	JLabel amount = new JLabel("display amount");
	
	private String username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentGUIPart3Paypal frame = new PaymentGUIPart3Paypal();
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
	public PaymentGUIPart3Paypal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[250px,grow][244px,grow]", "[50px][50px][50px][50px][50px][50px][50px][50px][][]"));
		
		JLabel lblNewLabel = new JLabel("CUSTOMER PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 2 1,grow");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open paymentGUI
				PaymentGUI frame = new PaymentGUI();
				frame.setUsername(username);
				frame.setVisible(true);
				
			}
		});
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Paying With PayPal");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2_1_1, "cell 0 1 2 1,alignx center");
		
		JButton btnNewButton_1_1 = new JButton("SIGN IN");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String em = email.getText().trim();
				@SuppressWarnings("deprecation")
				String pass = password.getText().trim();
				if(em.isEmpty() || pass.isEmpty())
					JOptionPane.showMessageDialog(null, "All fields must be filled!");
				else {
					
					
					//1.move request from granted.txt to paid.txt
					Booking b = new Booking();
					List<String> list = new ArrayList<String>();
					try {
						list = b.getCustomerGranted(username.trim());
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					String line = "";
					for(int i=0;i<list.size();i++) {
						if(list.get(i).contains(","+username+","+psn.getText().trim()+",")) {
							line = list.get(i);
							System.out.println(line);
							break;
						}
					}
					
					boolean wait = true;
					try {
						b.remove(line);
						wait=false;
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
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
						b.addPaidVersion2(line);
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Payment received! Please wait for Admin to confirm your payment.");
					
					//Close JPanel window
					JComponent comp = (JComponent) e.getSource();
					Window win = SwingUtilities.getWindowAncestor(comp);
					win.dispose();
					
					CustomerGUI frame = new CustomerGUI();
					frame.setUsername(username);
					frame.setVisible(true);
				}
			}
		});
		
		JLabel lblNewLabel_2_2 = new JLabel("Parking Spot Number : ");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2_2, "cell 0 2,alignx trailing");
		
		
		psn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(psn, "cell 1 2,alignx center");
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Amout : ");
		lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2_2_1, "cell 0 3,alignx trailing");
		
		
		amount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(amount, "cell 1 3,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Email : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 4,alignx trailing");
		
		email = new JTextField();
		email.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(email, "cell 1 4,growx");
		email.setColumns(10);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password : ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2_1, "cell 0 5,alignx trailing");
		
		password = new JPasswordField();
		password.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(password, "cell 1 5,growx");
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton_1_1, "cell 1 6,growx,aligny baseline");
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 8 2 1,grow");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnBack, "cell 0 9,grow");
		
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
		contentPane.add(btnBack_1, "cell 1 9,grow");
	}
	
	public void setPsnAmount(String psn1, String amount1) {
		psn.setText(psn1);
		amount.setText(amount1);
	}

	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username=username;
		System.out.println("The username is: "+this.username);
	}

}
