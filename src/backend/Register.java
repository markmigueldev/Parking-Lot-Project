package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * @author Mark
 *	Register class
 *	Use to register customers and officers
 *	Use to remove officers
 */
public class Register {
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	//KINDLY PLEASE VERIFY THE FILE PATH, THANK YOU!!!!!!!
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	private static final String CUSTOMERS_FILEPATH = "customers.txt";
	private static final String OFFICERS_FILEPATH = "officers.txt";
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	//KINDLY PLEASE VERIFY THE FILE PATH, THANK YOU!!!!!!!
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	public Register() {
	}
	
	//check if customer user name exist or email exist
	/**
	 * 
	 * @param username
	 * @param email
	 * @return true if customer email or username already exist
	 */
	public boolean customerUsernameEmailExist(String username, String email) {
		boolean check = false;
		@SuppressWarnings("unused")
		String fN = "", lName ="", em = ""; 
		@SuppressWarnings("unused")
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
				
				//4.2.3-REQ-1: The system must accept entries with duplicate first and last names
				//4.2.3-REQ-2: The system must allow unique email addresses when registering
				if(uName.trim().equals(username.trim()) || em.trim().equals(email.trim())) {
					scanner.close();
					//System.out.println("Customer email or username already exist!");
					return true;
				}
			}
			scanner.close();
			return check;
		}
		catch(Exception e) {
			//System.out.println("Method Error!");
		}
		return check;
	}
	//register user
	//firstName,LastName,email,username,password
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param username
	 * @param password
	 * @return true if customer registered, false otherwise
	 */
	public boolean registerCustomer(String firstName, String lastName, String email, String username, String password) {
		
		//check if username or email already exist
		boolean exist = customerUsernameEmailExist(username, email);
		//System.out.println(exist);
		
		if(!exist) {
			try {
				//open file
	            FileWriter fileWriter = new FileWriter(CUSTOMERS_FILEPATH, true);
	            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	            
	            //format
	            //firstName,LastName,email,username,password
	            
	            bufferedWriter.write(firstName);
	            bufferedWriter.write(",");
	            bufferedWriter.write(lastName);
	            bufferedWriter.write(",");
	            bufferedWriter.write(email);
	            bufferedWriter.write(",");
	            bufferedWriter.write(username);
	            bufferedWriter.write(",");
	            bufferedWriter.write(password);
	            bufferedWriter.newLine();

	            //close file
	            bufferedWriter.close();
	            System.out.println("New customer added.");
	            return true;
	        }
	        catch(Exception ex) {
	            System.out.println("Error!");
	        }
		}
		return false;
	}
	
	/**
	 * 
	 * @param username
	 * @param email
	 * @param assignedID
	 * @return true if officer email, username or assignedID already Exist
	 */
	public boolean officerUsernameEmailIDExist(String username, String email, String assignedID) {
		boolean check = false;
		@SuppressWarnings("unused")
		String fN = "", lName ="", em = ""; 
		@SuppressWarnings("unused")
		String uName = "", pWord = "", id = "";
		
		try {
			Scanner scanner = new Scanner(new File(OFFICERS_FILEPATH));
			scanner.useDelimiter("[,\n]");
			
			while(scanner.hasNext() && !check) {
				
				//format in database
				//firstName,LastName,email,username,password,assignedID
				fN = scanner.next();
				lName = scanner.next();
				em = scanner.next();
				uName = scanner.next();
				pWord = scanner.next();
				id = scanner.next();
				
				if(uName.trim().equals(username.trim()) || em.trim().equals(email.trim()) || id.trim().equals(assignedID.trim())) {
					scanner.close();
					//System.out.println("Officer email, username or assignedID already exist!");
					return true;
				}
			}
			scanner.close();
			//System.out.println(check);
			return check;
		}
		catch(Exception e) {
			//System.out.println("VerifyOfficer(String username, String password) Method Error!");
		}
		return check;
	}
	
	//register parking officer
	//This method will be used by the administrators
	/**
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param username
	 * @param password
	 * @param assignedID
	 * @return true if officer registered, false otherwise
	 */
	public boolean registerOfficer(String firstName, String lastName, String email, String username, String password, String assignedID) {
			
			//first we need to check if user name already exist
			boolean exist = officerUsernameEmailIDExist(username, email, assignedID);
			//System.out.println(exist);
			
			if(!exist) {
				try {
					//open file
		            FileWriter fileWriter = new FileWriter(OFFICERS_FILEPATH, true);
		            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		            
		            //format
		            //firstName,LastName,email,username,password,assignedID
		            bufferedWriter.write(firstName);
		            bufferedWriter.write(",");
		            bufferedWriter.write(lastName);
		            bufferedWriter.write(",");
		            bufferedWriter.write(email);
		            bufferedWriter.write(",");
		            bufferedWriter.write(username);
		            bufferedWriter.write(",");
		            bufferedWriter.write(password);
		            bufferedWriter.write(",");
		            bufferedWriter.write(assignedID);
		            bufferedWriter.newLine();
		           
		            //close file
		            bufferedWriter.close();
		            System.out.println("New officer added.");
		            return true;
		        }
		        catch(Exception ex) {
		            System.out.println("Error!");
		        }
			}
			return false;
		}

	public boolean removeOfficer(String firstName, String lastName, String email, String username, String assignedID) throws IOException {
		
		boolean check = false;
		
		//The officer's file path
		File file = new File(OFFICERS_FILEPATH);
		//temporary file that will replace the original officer file
        File temp = new File("temp.txt");
        
        String line = "";
        
        //read the original officer file
        BufferedReader reader = new BufferedReader(new FileReader(file));
        //write the temporary file
        BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
        //create new the temp
        temp.createNewFile();
        //read each line
        while((line = reader.readLine()) != null) {
        	//trim the line
            //String trimmedLine = line.trim();
            //officer account database format:
            //firstName,LastName,email,username,password,assignedID
            //split [firstName,LastName,email,username,] and [,assignedID] for checking precision
            String s = firstName+","+lastName+","+email+","+username+",";
            String id = ","+assignedID;
            //System.out.println(s);
            //System.out.println(id);
            
            //if line does not contain then write the line to the temp file
            if(!(line.contains(s) && line.endsWith(id))) {
                //System.out.println("does not contain!");
                writer.write(line);
                writer.newLine();
                continue;
            }
            //writer.write(line + System.getProperty("line.seprator"));
            check = true;
        }
        
        
        //close reader and writer for memory
        reader.close(); 
        writer.close(); 
        //delete the original
        file.delete();
        //check if override successful
        temp.renameTo(file);
        
        if(check)
        	System.out.println("Officer Successfully Removed!");
        else
        	System.out.println("Officer does not exist!");
        return check;
		
	}
	/**
	public static void main(String[] args) throws IOException {
		Register test = new Register();
		boolean check =test.removeOfficer("mark", "miguel", "mark@gmail.com", "markamiguel", "1234");
		System.out.println(check);
		
	}
	*/
	
	/**
	
	String t = "mark,miguel,mark@gmail.com,markamiguel,123456789";
		String s = "mark@gmail.com123";
		System.out.println(t.indexOf(s));
	
	boolean check =test.customerUsernameEmailExist("customer", "mark@gmail.com");
		boolean check1 =test.customerUsernameEmailExist("hell", "mark@gmail.com");
		boolean check2 =test.customerUsernameEmailExist("customer", "uniqe@gmail.com");
		boolean check3 =test.customerUsernameEmailExist("admin", "uniqe@gmail.com");
		System.out.println(check);
		System.out.println(check1);
		System.out.println(check2);
		System.out.println(check3);
	
	//The method below are actually not needed since administrators already have accounts in the system
	//check if customer user name exist
		public boolean AdminUsernameExist(String username) {
			boolean check = false;
			String uName = "", pWord = "";
			
			try {
				Scanner scanner = new Scanner(new File(ADMINS_FILEPATH));
				scanner.useDelimiter("[,\n]");
				
				while(scanner.hasNext() && !check) {
					uName = scanner.next();
					pWord = scanner.next();
					
					if(uName.trim().equals(username.trim())) {
						check = true;
					}
				}
				scanner.close();
				//System.out.println(check);
				return check;
			}
			catch(Exception e) {
				//System.out.println("Method Error!");
			}
			return check;
		}
	//The method is actually not needed since administrator already have account in the system
	//register administrator
	public boolean registerAdmin(String username, String password) {
			
			//first we need to check if user name already exist
			boolean exist = customerUsernameExist(username);
			//System.out.println(usernameExist);
			
			if(!exist) {
				try {
					//open file
		            FileWriter fileWriter = new FileWriter(ADMINS_FILEPATH, true);
		            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		            
		            //add name
		            bufferedWriter.write(username);
		            //add comma
		            bufferedWriter.write(",");
		            //add password
		            bufferedWriter.write(password);
		            //enter new line
		            bufferedWriter.newLine();
	
		            //close file
		            bufferedWriter.close();
		        }
		        catch(Exception ex) {
		            System.out.println("Error!");
		        }
			}
			return false;
		}
*/
}
