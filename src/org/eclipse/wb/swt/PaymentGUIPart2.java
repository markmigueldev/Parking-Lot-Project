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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class PaymentGUIPart2 extends JFrame {

	private JPanel contentPane;
	
	JLabel psn = new JLabel("displace number here");
	JLabel amount = new JLabel("display amount here");

	private String username;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentGUIPart2 frame = new PaymentGUIPart2();
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
	public PaymentGUIPart2() {
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[250px,grow][244px,grow]", "[50px][][50px][50px][50px][50px][50px][50px][][]"));
		
		JLabel lblNewLabel = new JLabel("CUSTOMER PORTAL");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		contentPane.add(lblNewLabel, "cell 0 0 2 1,grow");
		
		JLabel lblNewLabel_1 = new JLabel("Pay");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_1, "cell 0 1 2 1,alignx center");
		
		JLabel lblNewLabel_2 = new JLabel("Parking Space Number : ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2, "cell 0 2,alignx trailing,aligny baseline");
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open log in GUI
				PaymentGUI frame = new PaymentGUI();
				frame.setUsername(username);
				frame.setVisible(true);
				
			}
		});
		
		
		JLabel lblNewLabel_2_1 = new JLabel("Amount ($2.00/hour) : ");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2_1, "cell 0 3,alignx trailing");
		
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Select Payment Method : ");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblNewLabel_2_1_1, "cell 0 4,alignx trailing");
		
		JButton btnNewButton = new JButton("Paypal");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//hide the panel
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open PaymentGUIPart3Paypal
				PaymentGUIPart3Paypal frame = new PaymentGUIPart3Paypal();
				frame.setUsername(username);
				frame.setPsnAmount(psn.getText(), amount.getText());
				frame.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnNewButton, "cell 1 4,growx,aligny baseline");
		
		JButton btnCredit = new JButton("Credit");
		btnCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open PaymentGUIPart3Credit 
				PaymentGUIPart3Credit frame = new PaymentGUIPart3Credit();
				frame.setUsername(username);
				frame.setPsnAmount(psn.getText(), amount.getText());
				
				frame.setVisible(true);
				
				
			}
		});
		btnCredit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(btnCredit, "cell 1 5,growx,aligny baseline");
		
		JButton btnNewButton_1_1 = new JButton("Debit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open PaymentGUIPart3Credit 
				PaymentGUIPart3Debit frame = new PaymentGUIPart3Debit();
				frame.setPsnAmount(psn.getText(), amount.getText());
				frame.setUsername(username);
				frame.setVisible(true);
			}
		});
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
	
	//METHOD
	//set parking spot number
	public void setPsnAmount(String psn1, String amount1) {
		psn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(psn, "cell 1 2,alignx center,aligny center");
		psn.setText(psn1);
		
		amount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(amount, "cell 1 3,alignx center,aligny center");
		amount.setText(amount1);
	}

	public void setUsername(String username) {
		// TODO Auto-generated method stub
		this.username=username;
		System.out.println("The username is: "+this.username);
	}

}
