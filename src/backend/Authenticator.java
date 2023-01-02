package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Mark
 *
 *	Authenticator will be used for log in purposes.
 *	Only takes the username and password into consideration.
 *	Upon verification the Authenticator must keep the 
 *	account information of the user who logged in.
 *
 */

public class Authenticator {
	
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	//KINDLY PLEASE VERIFY THE FILE PATH, THANK YOU!!!!!!!
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	private static final String CUSTOMERS_FILEPATH = "customers.txt";
	private static final String OFFICERS_FILEPATH = "officers.txt";
	private static final String ADMINS_FILEPATH = "admins.txt";
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	//KINDLY PLEASE VERIFY THE FILE PATH, THANK YOU!!!!!!!
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	
	//private String uType;
	
	public Authenticator() {
		//uType = "";
	}
	
	/**
	public void setUserType(String userType) {
		this.uType = userType;
	}
	
	public String getUserType() {
		return this.uType;
	}
	*/
	
	public boolean verifyCustomer(String username, String password) {
		boolean check = false;
		//firstName,LastName,email,username,password
		String fN = "", lName ="", em = ""; 
		String uName = "", pWord = "";
		
		try {
			Scanner scanner = new Scanner(new File(CUSTOMERS_FILEPATH));
			scanner.useDelimiter("[,\n]");
			
			while(scanner.hasNext() && !check) {
				fN = scanner.next();
				lName = scanner.next();
				em = scanner.next();
				uName = scanner.next();
				pWord = scanner.next();
				
				if(uName.trim().equals(username.trim()) && pWord.trim().equals(password.trim())) {
					check = true;
					//this.uType = scanner.next();
				}
			}
			scanner.close();
			//System.out.println(check);
		}
		catch(Exception e) {
			//System.out.println("VerifyCustomer(String username, String password) Method Error!");
		}
		return check;
	}
	
	public boolean verifyOfficer(String username, String password) {
		boolean check = false;
		//firstName,LastName,email,username,password,assignedID
		String fN = "", lName ="", em = ""; 
		String uName = "", pWord = "", id = "";
		
		try {
			Scanner scanner = new Scanner(new File(OFFICERS_FILEPATH));
			scanner.useDelimiter("[,\n]");
			
			while(scanner.hasNext() && !check) {
				fN = scanner.next();
				lName = scanner.next();
				em = scanner.next();
				uName = scanner.next();
				pWord = scanner.next();
				id = scanner.next();
				
				if(uName.trim().equals(username.trim()) && pWord.trim().equals(password.trim())) {
					check = true;
					//this.uType = scanner.next();
					//EDIT THIS LATER==>MUST SAVE 
				}
			}
			scanner.close();
			//System.out.println(check);
		}
		catch(Exception e) {
			//System.out.println("VerifyOfficer(String username, String password) Method Error!");
		}
		return check;
	}
	
	public boolean verifyAdmin(String username, String password) {
		boolean check = false;
		String uName = "", pWord = "";
		
		try {
			Scanner scanner = new Scanner(new File(ADMINS_FILEPATH));
			scanner.useDelimiter("[,\n]");
			
			while(scanner.hasNext() && !check) {
				uName = scanner.next();
				pWord = scanner.next();
				
				if(uName.trim().equals(username.trim()) && pWord.trim().equals(password.trim())) {
					check = true;
					//this.uType = scanner.next();
				}
			}
			scanner.close();
			//System.out.println(check);
		}
		catch(Exception e) {
			//System.out.println("VerifyCustomer(String username, String password) Method Error!");
		}
		return check;
	}
	
	//==================================================================
	//Customer Booking methods
	
	public boolean customerExist(String firstname, String lastname, String email, String username) {
		
		boolean check = false;
		
		String fn = "";
		String ln = "";
		String em = "";
		String un = "";
		@SuppressWarnings("unused")
		String pWord = "";
		
		try {
			Scanner scanner = new Scanner(new File(CUSTOMERS_FILEPATH));
			scanner.useDelimiter("[,\n]");
			
			while(scanner.hasNext() && !check) {
				fn = scanner.next();
				ln = scanner.next();
				em = scanner.next();
				un = scanner.next();
				pWord = scanner.next();
				
				if(fn.trim().equals(firstname) && ln.trim().equals(lastname) && em.trim().equals(email) && un.trim().equals(username.trim())) {
					check = true;
					//this.uType = scanner.next();
				}
			}
			scanner.close();
			//System.out.println(check);
		}
		catch(Exception e) {
			//System.out.println("VerifyCustomer(String username, String password) Method Error!");
		}
		return check;
		
	}
	/**
	public static void main(String[] args) throws FileNotFoundException {
		
		Authenticator test = new Authenticator();
		//System.out.println(test.verifyCustomer("mark", "mark"));
		
		//System.out.println(test.verifyOfficer("user", "pass"));
		
		//System.out.println(test.verifyAdmin("user", "pass"));
		
		
		System.out.println(test.customerExist("first", "last", "email@email.com", "customer"));
	}
	*/
}
