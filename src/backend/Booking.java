package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.Calendar;

//used for customer booking
public class Booking {
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	//KINDLY PLEASE VERIFY THE FILE PATH, THANK YOU!!!!!!!
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	private static final String CUSTOMERS_FILEPATH = "customers.txt";
	private static final String OFFICERS_FILEPATH = "officers.txt";
	private static final String REQUESTS_FILEPATH = "requests.txt";
	private static final String REJECTED_FILEPATH = "rejected.txt";
	private static final String GRANTED_FILEPATH = "granted.txt";
	private static final String PAID_FILEPATH = "paid.txt";
	private static final String CONFIRMED_FILEPATH = "confirmed.txt";
	private static final String CANCELLATIONS_FILEPATH = "cancellations.txt";
	private static final String PARKINGSPOTS_FILEPATH = "parkingspots.txt";
	
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	//KINDLY PLEASE VERIFY THE FILE PATH, THANK YOU!!!!!!!
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================	
	
	public Booking() {
	}
	
	//check if request exist
		public boolean requestExist(String bookingID) {
			
			File file = new File(REQUESTS_FILEPATH);
			try {
			    Scanner scanner = new Scanner(file);
			    String line = "";
			    while (scanner.hasNextLine()) {
			        line = scanner.nextLine();
			        if(line.endsWith(","+bookingID)) {
			        	scanner.close();
			        	return true;
			        }
			        	
			    }
			    scanner.close();
			} catch(FileNotFoundException e) { 
				//System.out.println("File not found!");
			}
			return false;
		}
	
		
		//get psn from booking ID
		/**
		 * 
		 * @param bookingID
		 * @return parking spot number, -1 if invalid
		 * @throws IOException
		 */
		public int getParkingSpotNumber(String bookingID) throws IOException {
			
			//lets get the parking spot number from requests.txt
			Scanner scanner = new Scanner(new File(REQUESTS_FILEPATH));
			scanner.useDelimiter("[,\n]");
			
			String psn ="";
			String id = "";
			
			boolean check = false;
			
			while(scanner.hasNext()) {
				//fn
				scanner.next();
				//ln
				scanner.next();
				//email
				scanner.next();
				//username
				scanner.next();
				//psn
				String temp=scanner.next();
				//booking time
				scanner.next();
				//lpn
				scanner.next();
				//booking id
				id = scanner.next();
				//if bookingID = id break 
				if(id.trim().equals(bookingID)) {
					psn = temp;
					//parking spot number successfully found
					check = true;
					break;
				}
			}		
			scanner.close();
			if(check)
				return Integer.parseInt(psn);
			else
				return -1;
		}
		
		//grant the bookingID
		//assuming you have the conditions in the GUI class
		public boolean addGranted(String bookingID) throws IOException {
			//move request from requests.txt to granted.txt
			boolean exist = false;
			File file = new File(REQUESTS_FILEPATH);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = reader.readLine()) != null) {
				if(line.endsWith(","+bookingID)) {
					exist = true;
					break;
				}
			}
			reader.close();
			
			System.out.println(line);
			
			//add to granted.txt
			FileWriter fileWriter = new FileWriter(GRANTED_FILEPATH, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			
			bufferedWriter.close();
			fileWriter.close();
			
			System.out.println("Booking ID "+bookingID+" granted!");
			return exist;
		}	
		
		public void addRejected(String booking, String reason) throws IOException {
			FileWriter fileWriter = new FileWriter(REJECTED_FILEPATH, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			//System.out.println(line);
			//add the line + ,reason
			bufferedWriter.write(booking+","+reason);
			bufferedWriter.newLine();
			
			bufferedWriter.close();
			fileWriter.close();

			System.out.println("Request added to rejected.txt!");
			//return true;
		}
		
		public void addRejectedVersion2(String booking) throws IOException {
			FileWriter fileWriter = new FileWriter(REJECTED_FILEPATH, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			//System.out.println(line);
			//add the line + ,reason
			bufferedWriter.write(booking);
			bufferedWriter.newLine();
			
			bufferedWriter.close();
			fileWriter.close();

			System.out.println("Request added to rejected.txt!");
			//return true;
		}
			
		
		
		//cancel or reject request
		public void reject(String bookingID, String reason) throws IOException, InterruptedException {
			//1. remove the request
			//2. move to rejected.txt
			File file = new File(REQUESTS_FILEPATH);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = "";
			while((line = reader.readLine()) != null) {
				if(line.endsWith(","+bookingID)) {
					break;
				}
			}
			reader.close();
			
			if(line != null) {
				//add to rejected.txt
				FileWriter fileWriter = new FileWriter(REJECTED_FILEPATH, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				//System.out.println(line);
				//add the line + ,reason
				bufferedWriter.write(line+","+reason);
				bufferedWriter.newLine();
				
				bufferedWriter.close();
				fileWriter.close();
				
				System.out.println("Booking ID "+bookingID+" added to rejected!");
			}
			else {
				System.out.println("Rejection unsuccessful!");
			}
			
		}
		

		
	//check if String time is valid
	//must be a valid FUTURE DATE
	/**
	 * 
	 * @param time
	 * @return true if time is valid (valid = future time and date)
	 */
	public boolean validateTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		
		//boolean valid = false;
		try{
	    	Date date = sdf.parse(time);
	    	//System.out.println("checking:!!! "+date);
	    	return new Date().before(date);
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Invalid Time!");
	        return false;
	    }
	}
	
	//Returns the customer firstname,lastname,email,username,password
	public String getCustomerInfo(String username) throws IOException {
		File file = new File(CUSTOMERS_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line ="";
		try {
			while((line = reader.readLine()) != null) {
				if(line.contains(","+username+",")) {
					reader.close();
					return line;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		reader.close();
		return null;
	}
	
	/**
	//check if customer exist
	public boolean customerExist(String info) throws IOException {
		File file = new File(CUSTOMERS_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line ="";
		try {
			while((line = reader.readLine()) != null) {
				if(line.contains(info)) {
					reader.close();
					return true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			reader.close();
		}
		reader.close();
		return false;
	}
	*/
	
	//input time must be minimum of 1hour
	public boolean minTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(time);
			Date now = Calendar.getInstance().getTime();
			long difference = date.getTime()-now.getTime();
			if(difference >= 3540000)
				return true;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		return false;
	}
	
	public boolean addRequests(String info, String psn, String bookingTime, String lpn) {
		boolean check = false;
		try {
			//open file
            FileWriter fileWriter = new FileWriter(REQUESTS_FILEPATH, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            //format
            //firstName,LastName,email,username,password,assignedID
            bufferedWriter.write(info);
            bufferedWriter.write(",");
            bufferedWriter.write(psn);
            bufferedWriter.write(",");
            bufferedWriter.write(bookingTime);
            bufferedWriter.write(",");
            bufferedWriter.write(lpn);
            bufferedWriter.write(",");
            
            //BOOKING ID
            //We use java util UUID to generate a random string
            String bookingID = java.util.UUID.randomUUID().toString();
            //We will only take the first 8 characters
    		bufferedWriter.write(bookingID.substring(0, 8));
    		
            bufferedWriter.newLine();
           
            //close file
            bufferedWriter.close();
            System.out.println("New Request Added.");
            check = true;
        }
        catch(IOException ex) {
            //System.out.println("Error! Booking.addCustomerBooking");
        }
		return check;
	}
	
	//check psn exist in granted.txt
	public boolean existGranted(String username, String psn) throws IOException {
		File file = new File(GRANTED_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line ="";
		try {
			while((line = reader.readLine()) != null) {
				if(line.contains(","+username+","+psn+",")) {
					reader.close();
					return true;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return false;
	}

	public String getTime(String parkingSpotNumber) {
		//1. get time of input from granted.txt
		//2. subtract expiry time - time now
		//3. divide by 1hour. rate will be $1/hour

		String time = "";
		try {
			Scanner scanner = new Scanner(new File(GRANTED_FILEPATH));
			scanner.useDelimiter("[,\n]");
			
			String psn = "";
			
			while(scanner.hasNext()) {
				scanner.next();
				scanner.next();
				scanner.next();
				scanner.next();
				psn=scanner.next();
				time=scanner.next();
				scanner.next();
				scanner.next();
				if(psn.trim().equals(parkingSpotNumber.trim())) {
					scanner.close();
					break;
				}
			}
			scanner.close();		
		}
		catch(Exception e) {
			//System.out.println("Method Error!");
		}
		return time;
	}
	
	public double calculateAmount(String time, double ratePerHour) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		try {
			Date date = sdf.parse(time);
			Date now = Calendar.getInstance().getTime();
			//difference
			long difference = date.getTime()-now.getTime();

			double x = (difference/3600000.0)*ratePerHour;
			//System.out.println(x);
			double money = Math.round(x*100.0)/100.0;
			return money;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	
	
	public boolean removeGranted(String username, String psn) throws IOException {
		File file = new File(GRANTED_FILEPATH);
		File temp = new File("temp.txt");
		temp.createNewFile();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		
		String line = "";
		while((line = reader.readLine()) != null) {
			if(!(line.contains(","+username+","+psn+","))) {
				writer.write(line);
				writer.newLine();
				continue;
			}
		}
		
		reader.close();
		writer.close();
		file.delete();
		
		boolean check = temp.renameTo(file);
		return check;
	}
	
	public boolean addPaid(String username, String psn) throws IOException {
		File file = new File(GRANTED_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.contains(","+username+","+psn+",")) {
				break;
			}
		}
		reader.close();
		if(line != null) {
			//add to rejected.txt
			FileWriter fileWriter = new FileWriter(PAID_FILEPATH, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			//System.out.println(line);
			//add the line + ,reason
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			
			bufferedWriter.close();
			fileWriter.close();

			System.out.println("Request added to paid.txt!");
			return true;
		}
		else {
			System.out.println("Request was not added to paid.txt!");
			return false;
		}
	}
	
	public boolean addPaidVersion2(String booking) throws IOException {
		FileWriter fileWriter = new FileWriter(PAID_FILEPATH, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		//System.out.println(line);
		//add the line + ,reason
		bufferedWriter.write(booking);
		bufferedWriter.newLine();
		
		bufferedWriter.close();
		fileWriter.close();

		System.out.println("Request added to cancellations.txt!");
		return true;
	}
	
	@SuppressWarnings("resource")
	public boolean customerPaid(String fistname, String lastname, String email, String psn) throws IOException {
		File file = new File(PAID_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.startsWith(fistname+","+lastname+","+email+",") && line.contains(","+psn+",")) {
				return true;
			}
		}
		
		reader.close();
		
		return false;
	}
	
	public boolean removePaid(String firstname, String lastname, String email, String psn) throws IOException {
		File file = new File(PAID_FILEPATH);
		File temp = new File("temp.txt");
		temp.createNewFile();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		
		String line = "";
		String s = firstname+","+lastname+","+email+",";
		while((line = reader.readLine()) != null) {
			if(!(line.contains(s) && line.contains(","+psn+","))) {
				writer.write(line);
				writer.newLine();
				continue;
			}
		}
		
		boolean wait = true;
		while(wait) {
			reader.close();
			writer.close();
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			wait = false;
		}
		
		System.out.println("file.delete success: " +file.delete());
		
		boolean check = temp.renameTo(file);
		return check;
		
	}
	
	public boolean addConfirmed(String fistname, String lastname, String email, String psn) throws IOException {
		File file = new File(PAID_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.startsWith(fistname+","+lastname+","+email+",") && line.contains(","+psn+",")) {
				break;
			}
		}
		reader.close();
		if(line != null) {
			//add to rejected.txt
			FileWriter fileWriter = new FileWriter(CONFIRMED_FILEPATH, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			//System.out.println(line);
			//add the line + ,reason
			bufferedWriter.write(line);
			bufferedWriter.newLine();
			
			bufferedWriter.close();
			fileWriter.close();
			
			System.out.println("Request added to paid.txt!");
			return true;
		}
		else {
			System.out.println("Request was not added to paid.txt!");
			return false;
		}
	}
	
	public boolean addConfirmedVersion2(String booking) throws IOException {
		FileWriter fileWriter = new FileWriter(CONFIRMED_FILEPATH, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		//System.out.println(line);
		//add the line + ,reason
		bufferedWriter.write(booking);
		bufferedWriter.newLine();
		
		bufferedWriter.close();
		fileWriter.close();

		System.out.println("Request added to cancellations.txt!");
		return true;
	}
	
	public List<String> getCustomerRequests(String username) throws IOException {
		File file = new File(REQUESTS_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.contains(","+username+","))
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getCustomerRejected(String username) throws IOException {
		File file = new File(REJECTED_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
	
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.contains(","+username+","))
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getCustomerGranted(String username) throws IOException {
		File file = new File(GRANTED_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.contains(","+username+","))
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getCustomerPaid(String username) throws IOException {
		File file = new File(PAID_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.contains(","+username+","))
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getCustomerPaidVersion2(String firstname, String lastname, String em, String psn) throws IOException {
		File file = new File(PAID_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.contains(firstname+","+lastname+","+em+",") && line.contains(","+psn+","))
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getCustomerConfirmed(String username) throws IOException {
		File file = new File(CONFIRMED_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.contains(","+username+","))
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getCustomerCancellations(String username) throws IOException {
		File file = new File(CANCELLATIONS_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.contains(","+username+","))
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	//view all-------------------------------------------------
	
	public List<String> getAllCustomerRequests() throws IOException {
		File file = new File(REQUESTS_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getAllCustomerRejected() throws IOException {
		File file = new File(REJECTED_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getAllCustomerGranted() throws IOException {
		File file = new File(GRANTED_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getAllCustomerPaid() throws IOException {
		File file = new File(PAID_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
				list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getAllCustomerConfirmed() throws IOException {
		File file = new File(CONFIRMED_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			list.add(line);
		}
		reader.close();
		return list;
	}
	
	public List<String> getAllCustomerCancellations() throws IOException {
		File file = new File(CANCELLATIONS_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			list.add(line);
		}
		reader.close();
		return list;
	}
	
	public boolean customerExist(String username) throws IOException {
		File file = new File(CUSTOMERS_FILEPATH);
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		//List<String> list = new ArrayList<String>();
		String line = "";
		while((line = reader.readLine()) != null) {
			if(line.contains(","+username+","))
				return true;
		}
		reader.close();
		return false;
	}
	
	public boolean removeRequest(String bookingID) throws IOException {
		//1. remove the line from requests.txt
		File file = new File(REQUESTS_FILEPATH);
		File temp = new File("temp.txt");
		temp.createNewFile();
		
		String line = "";
		//boolean check = false;
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		
		while((line = reader.readLine()) != null) {
			
			//if line does not equal it means line is occupied
			//we need to add the line to the temp file
			if(!(line.endsWith(bookingID))) {
				writer.write(line);
				writer.newLine();
				continue;
			}
			//if equal then we need to remove it
			//check = true;
			
		}

		
		reader.close();
		writer.close();
		file.delete();
		return temp.renameTo(file);
	}
	
	
	//check if bookingId is associated with username
	public String customerCancelValid(String username, String bookingID) throws IOException {
		
		List<String> requests = new ArrayList<String>();
		List<String> granted = new ArrayList<String>();
		List<String> paid = new ArrayList<String>();
		List<String> confirmed = new ArrayList<String>();
		
		requests = getCustomerRequests(username);
		granted = getCustomerGranted(username);
		paid = getCustomerPaid(username);
		confirmed = getCustomerConfirmed(username);
		
		if(requests.size() != 0) {
			for(int i=0; i<requests.size();i++) {
				String s = requests.get(i);
				if(s.contains(","+username+",") && s.endsWith(","+bookingID)) {
					return s;
				}
			}
		}
		
		if(granted.size() != 0) {
			for(int i=0; i<granted.size();i++) {
				String s = granted.get(i);
				if(s.contains(","+username+",") && s.endsWith(","+bookingID)) {
					return s;
				}		
			}
		}
		
		if(paid.size() != 0) {
			for(int i=0; i<paid.size();i++) {
				String s = paid.get(i);
				if(s.contains(","+username+",") && s.endsWith(","+bookingID)) {
					return s;
				}	
			}
		}
		
		if(confirmed.size() != 0) {
			for(int i=0; i<confirmed.size();i++) {
				String s = confirmed.get(i);
				if(s.contains(","+username+",") && s.endsWith(","+bookingID)) {
					return s;
				}	
			}
		}
		return null;
	}
	
	//check if time is before expiry
	public boolean beforeExpiry(String booking) {
		//get the time
		List<String> list = Arrays.asList(booking.split(","));
		String time = list.get(5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		boolean before = false;
		try {
			Date date = sdf.parse(time);
			Date now = Calendar.getInstance().getTime();
			before = now.before(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return before;
	}

	//customer cancel request
	//add to cancellations.txt
	public boolean addCancellations(String booking) throws IOException {
		FileWriter fileWriter = new FileWriter(CANCELLATIONS_FILEPATH, true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		//System.out.println(line);
		//add the line + ,reason
		bufferedWriter.write(booking);
		bufferedWriter.newLine();
		
		bufferedWriter.close();
		fileWriter.close();

		System.out.println("Request added to cancellations.txt!");
		return true;
	}
	
	//remove from all except cancellations.txt
	public boolean remove(String booking) throws IOException {
		List<String> requests = new ArrayList<String>();
		List<String> granted = new ArrayList<String>();
		List<String> paid = new ArrayList<String>();
		List<String> confirmed = new ArrayList<String>();
		
		requests = getAllCustomerRequests();
		granted = getAllCustomerGranted();
		paid = getAllCustomerPaid();
		confirmed = getAllCustomerConfirmed();
		
		List<String> split = Arrays.asList(booking.split(","));
		
		if(requests.size() != 0) {
			for(int i=0; i<requests.size();i++) {
				String s = requests.get(i);
				if(s.contains(booking)) {
					return removeRequest(split.get(7));
				}
			}
		}
		
		if(granted.size() != 0) {
			for(int i=0; i<granted.size();i++) {
				String s = granted.get(i);
				if(s.contains(booking)) {
					return removeGranted(split.get(3), split.get(4));
				}		
			}
		}
		
		if(paid.size() != 0) {
			for(int i=0; i<paid.size();i++) {
				String s = paid.get(i);
				if(s.contains(booking)) {
					//removePaid(String firstname, String lastname, String email, String psn)
					return removePaid(split.get(0), split.get(1), split.get(2), split.get(4));
				}	
			}
		}
		
		if(confirmed.size() != 0) {
			for(int i=0; i<confirmed.size();i++) {
				String s = confirmed.get(i);
				if(s.contains(booking)) {
					return removeConfirmed(booking);
				}	
			}
		}
		return false;
	}
	
	/**
	public boolean removeV2(String bookingID) throws IOException {
		List<String> requests = new ArrayList<String>();
		List<String> granted = new ArrayList<String>();
		List<String> paid = new ArrayList<String>();
		List<String> confirmed = new ArrayList<String>();
		List<String> cancellations = new ArrayList<String>();
		
		requests = getAllCustomerRequests();
		granted = getAllCustomerGranted();
		paid = getAllCustomerPaid();
		confirmed = getAllCustomerConfirmed();
		cancellations = getAllCustomerCancellations();
		
		
		if(requests.size() != 0) {
			for(int i=0; i<requests.size();i++) {
				String s = requests.get(i);
				List<String> split = Arrays.asList(s.split(","));
				if(s.endsWith(","+bookingID)) {
					return removeRequest(split.get(7));
				}
			}
		}
		
		if(granted.size() != 0) {
			for(int i=0; i<granted.size();i++) {
				String s = granted.get(i);
				List<String> split = Arrays.asList(s.split(","));
				if(s.endsWith(","+bookingID)) {
					return removeGranted(split.get(3), split.get(4));
				}		
			}
		}
		
		if(paid.size() != 0) {
			for(int i=0; i<paid.size();i++) {
				String s = paid.get(i);
				List<String> split = Arrays.asList(s.split(","));
				if(s.endsWith(","+bookingID)) {
					return removePaid(split.get(0), split.get(1), split.get(2), split.get(4));
				}	
			}
		}
		
		if(confirmed.size() != 0) {
			for(int i=0; i<confirmed.size();i++) {
				String s = confirmed.get(i);
				if(s.endsWith(","+bookingID)) {
					return removeConfirmed(s);
				}	
			}
		}
		
		if(cancellations.size() != 0) {
			for(int i=0; i<cancellations.size();i++) {
				String s = cancellations.get(i);
				List<String> split = Arrays.asList(s.split(","));
				if(s.endsWith(","+bookingID)) {
					return removeCancellations(s);
				}
			}
		}
		return false;
	}
	*/
	
	public boolean removeRejected(String booking) throws IOException {
		File file = new File(REJECTED_FILEPATH);
		File temp = new File("temp.txt");
		temp.createNewFile();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		
		String line = "";
		while((line = reader.readLine()) != null) {
			if(!(line.contains(booking))) {
				writer.write(line);
				writer.newLine();
				continue;
			}
		}
		
		reader.close();
		writer.close();
		file.delete();
		
		boolean check = temp.renameTo(file);
		return check;
	}
	
	public boolean removeCancellations(String bookingID) throws IOException {
		File file = new File(CANCELLATIONS_FILEPATH);
		File temp = new File("temp.txt");
		temp.createNewFile();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		
		String line = "";
		while((line = reader.readLine()) != null) {
			if(!(line.endsWith(","+bookingID))) {
				writer.write(line);
				writer.newLine();
				continue;
			}
		}
		
		reader.close();
		writer.close();
		file.delete();
		
		boolean check = temp.renameTo(file);
		return check;
	}
	
	public boolean removeConfirmed(String booking) throws IOException {
		File file = new File(CONFIRMED_FILEPATH);
		File temp = new File("temp.txt");
		temp.createNewFile();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		
		String line = "";
		while((line = reader.readLine()) != null) {
			if(!(line.contains(booking))) {
				writer.write(line);
				writer.newLine();
				continue;
			}
		}
		
		reader.close();
		writer.close();
		file.delete();
		
		boolean check = temp.renameTo(file);
		return check;
	}
	
	//4.7.3-REQ-3: The system must notify the customer when their parking space booking is expired
	//check if time has passed
	public boolean expired(String booking) {
		List<String> list = Arrays.asList(booking.split(","));
		String time = list.get(5);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		boolean after = false;
		try {
			Date date = sdf.parse(time);
			Date now = Calendar.getInstance().getTime();
			after = now.after(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return after;
	}
	
	/**
	 * clear the database to default database:
	 * default database contains:
	 * 1) f,l,e,c,c) customer
	 * 2) f,l,e,o,o) officer
	 * 3) 10 Parking Spots
	 * @throws IOException 
	 */
	//CAUTION!!!
	//Be careful invoking this method
	public void wipeClean() throws IOException {

		System.out.println("Executing wipeClean()...");
		
		PrintWriter file0 = new PrintWriter(CUSTOMERS_FILEPATH);
		file0.close();
		PrintWriter file1 = new PrintWriter(OFFICERS_FILEPATH);
		file1.close();
		PrintWriter file2 = new PrintWriter(REQUESTS_FILEPATH);
		file2.close();
		PrintWriter file3 = new PrintWriter(REJECTED_FILEPATH);
		file3.close();
		PrintWriter file4 = new PrintWriter(GRANTED_FILEPATH);
		file4.close();
		PrintWriter file5 = new PrintWriter(PAID_FILEPATH);
		file5.close();
		PrintWriter file6 = new PrintWriter(CONFIRMED_FILEPATH);
		file6.close();
		PrintWriter file7 = new PrintWriter(CANCELLATIONS_FILEPATH);
		file7.close();
		PrintWriter file8 = new PrintWriter(PARKINGSPOTS_FILEPATH);
		file8.close();
		
		//1. add (f,l,e,c,c) customer
		Register r = new Register();
		r.registerCustomer("firstname", "lastname", "customer@email.com", "c", "c");
		//2. add f,l,e,o,o) officer
		r.registerOfficer("firstname", "lastname", "officer@email.com", "o", "o", "0123456789");
		//3. add 10 parking spots
		ParkingSpace ps = new ParkingSpace();
		ps.addSpace(1);
		ps.addSpace(2);
		ps.addSpace(3);
		ps.addSpace(4);
		ps.addSpace(5);
		ps.addSpace(6);
		ps.addSpace(7);
		ps.addSpace(8);
		ps.addSpace(9);
		ps.addSpace(10);
		
		System.out.println("wipeClean() was executed!\nDatabase successfully restored to default state!");
		
	}
	
	/**
	public static void main(String[] args) throws ParseException, IOException {
		Booking test = new Booking();
		//System.out.println(test.expired("f,l,e,c,2,2021-04-17 17:36:00,qwe123,89b3f27e"));
		
		List<String> t = test.getAllCustomerRequests();
		System.out.println(t.toString());
		test.wipeClean();
	}
	*/
	
	
	/*
	 * 
	 * 
		//System.out.println(test.addRequests("mark", "miguel", "mark@gmail.com", "markmiguel", "1", "7:00", "bscn615"));
		
		//System.out.print(test.minTime("2021-04-11 21:51:00"));
		
		//System.out.println(test.getTime("3"));
		
		//System.out.println(test.calculateAmount(test.getTime("3"),2));
		//System.out.println(test.addPaid("5"));
		//System.out.println(test.removeGranted("5"));
		
		//System.out.println(test.removePaid("f", "l", "e", "3"));
		//System.out.println(test.addConfirmed("f", "l", "e", "3"));
		
		//String t = "firstname,lastname,email@email.com,customer,1,2022-01-01 00:00:00,123456,c22819ca";
		//System.out.println(t.indexOf("customer"));
		//String username = "customer";
		//System.out.println(t.substring(0, t.indexOf(username))+username);
		//System.out.println(test.customerCancelValid("c", "89b3f27e"));
		//System.out.println(test.beforeExpiry("f,l,e,c,2,2021-04-16 17:36:00,qwe123,89b3f27e"));
		//System.out.println(test.getCustomerInfo("customer"));
		//System.out.println(test.remove("f,l,e,c,2,2021-04-15 23:00:00,qwe123,89b3f27e"));
		
		String str = "f,l,e,c,3,2021-04-16 23:00:00,asdqwe123,d552c32e";
		
		List<String> l = Arrays.asList(str.split(","));
		String s = String.format("Firstname: %-5s |Lastname: %-5s |Email: %-5s |Username: %-5s |Parking Spot Number: %-5s |Expiry Time: %-5s |License Plate: %-5s |Booking ID: %-5s|%n",l.get(0),l.get(1),l.get(2),l.get(3),l.get(4),l.get(5),l.get(6),l.get(7));
		System.out.println(s);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setLenient(false);
		try{
	    	Date date = sdf.parse("2021-04-11 20:05:00");
	    	System.out.println("checking:!!! "+date);
		}
	    catch(Exception e)
	    {
	    	System.out.println("Invalid Time!");
	    }
		Date date = sdf.parse("2021-04-11 21:42:00");
		Date now = Calendar.getInstance().getTime();
		System.out.println(date.getTime()-now.getTime());
		
		Date date1 = sdf.parse("2021-04-11 23:05:00");
		long diff = date1.getTime() - date.getTime();
		System.out.println(diff);
		System.out.println(date.toString().charAt(12));
		
		
		//Close JPanel window
				JComponent comp = (JComponent) e.getSource();
				Window win = SwingUtilities.getWindowAncestor(comp);
				win.dispose();
				//open log in GUI
				UserLoginGUI window = new UserLoginGUI();
				window.frmParkingApplication.setVisible(true);
		
		//SimpleDateFormat temp = new SimpleDateFormat(format);
		//System.out.println(temp.parse(time));
		
		System.out.println(java.util.UUID.randomUUID().toString().subSequence(0, 8));
		System.out.println(java.util.UUID.randomUUID().toString().substring(0, 8));
		System.out.println(java.util.UUID.randomUUID().toString());
		
		Random random = new Random();
		int r = random.nextInt(10000000);
		System.out.println(r+"customer");	
			
		//String format = "yyyy-MM-dd HH:mm:ss";
		//System.out.println(test.validateTime("2021-12-10 14:00:00"));
		
	
	GregorianCalendar gcalendar = new GregorianCalendar();


		  String hr = ("" + gcalendar.get(Calendar.HOUR) + ":");
		  String min = ("" + gcalendar.get(Calendar.MINUTE) + ":");
		  String sec = ("" + gcalendar.get(Calendar.SECOND));
		  System.out.println(hr + min + sec);
		  
		  
	
	 */
	
}
