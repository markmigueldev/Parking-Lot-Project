package backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

//database format
//parkingnumber,vacant (eg. 1,vacant or 1,occupied)
public class ParkingSpace {
	
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	//KINDLY PLEASE VERIFY THE FILE PATH, THANK YOU!!!!!!!
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	private static final String PARKINGSPOTS_FILEPATH = "parkingspots.txt";
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================
	//KINDLY PLEASE VERIFY THE FILE PATH, THANK YOU!!!!!!!
	//====================================================
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//====================================================	
	
	public ParkingSpace(){
		
	}
	
	//check if space already exist
	public boolean alreadyExist(int psn) throws IOException {
		
		//File file = new File(PARKINGSPOTS_FILEPATH);
		//BufferedReader reader = new BufferedReader(new FileReader(file));
		//String line = "";
		
		Scanner scanner = new Scanner(new File(PARKINGSPOTS_FILEPATH));
		scanner.useDelimiter("[,\n]");
		
		String pn = "";
		//String status =""; //vacant or occupied
		
		while(scanner.hasNext()) {
			pn = scanner.next();
			//status = scanner.next();
			scanner.next();
			if(Objects.equals(pn, Integer.toString(psn))) {
				scanner.close();
				return true;
			}
		}
		scanner.close();
		return false;
	}
	
	//add parking space
	public int addSpace(int psn) throws IOException {
		
		FileWriter fileWriter = new FileWriter(PARKINGSPOTS_FILEPATH, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
        //format of parkingspots.txt
        //parkingnumber,vacant (eg. 1,vacant or 1,occupied)

        bufferedWriter.write(Integer.toString(psn));
        bufferedWriter.write(",");
        bufferedWriter.write("vacant"); //vacant or occuped
        bufferedWriter.newLine();
        
        
        bufferedWriter.close();
        fileWriter.close();
        System.out.println("New parking space added to parkingspots.txt");
        
        return psn;
	}
	
	//remove parking space
	public boolean removeSpace(int psn) throws IOException {
		
		boolean check = false;
		
		File file = new File(PARKINGSPOTS_FILEPATH);
		File temp = new File("temp.txt");
		temp.createNewFile();
		
		String line = "";
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		
		while((line = reader.readLine()) != null) {
			
			//if line does not equal it means line is occupied
			//we need to add the line to the temp file
			if(!(Objects.equals(line, Integer.toString(psn)+",vacant"))) {
				writer.write(line);
				writer.newLine();
				continue;
			}
			//if equal then we need to remove it
			check = true;
		}
		
		reader.close();
		writer.close();
		file.delete();
		temp.renameTo(file);
		
		if(check)
        	System.out.println("Space Successfully Removed!");
		else
			System.out.println("Space is occupied!");
		
		return check;
		
	}
	
	
	//check if parkingspot number is vacant
	//NOTE: method does not check if it psn exist
	@SuppressWarnings("resource")
	public boolean isVacant(int psn) throws IOException {
		
		File file = new File(PARKINGSPOTS_FILEPATH);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
		String line ="";
		while((line = reader.readLine()) != null) {
			if((Objects.equals(line, Integer.toString(psn)+",vacant"))) {
				return true;
			}
		}
		reader.close();
		return false;
		
	}
	
	//occupied -> vacant
	public boolean makeVacant(int psn) throws IOException {
		
		File file = new File(PARKINGSPOTS_FILEPATH);
		File temp = new File("temp.txt");
		temp.createNewFile();
		
		String line = "";
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		
		boolean check=false;
		while((line = reader.readLine()) != null) {
			//if found replace occupied to vacant
			if((Objects.equals(line, Integer.toString(psn)+",occupied"))) {
				writer.write(psn+",vacant");
				writer.newLine();
				check=true;
				continue;
			}
			//add others to temp.txt
			writer.write(line);
			writer.newLine();
		}
		
		reader.close();
		writer.close();
		file.delete();
		temp.renameTo(file);
		
		if(check)
        	System.out.println("Space is now Vacant!");
		else
			System.out.println("Space is already vacant!");
		
		return check;
	}

	//vacant -> occupied
	public boolean makeOccupied(int psn) throws IOException {
		
		File file = new File(PARKINGSPOTS_FILEPATH);
		File temp = new File("temp.txt");
		temp.createNewFile();
		
		String line = "";
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
		
		boolean check=false;
		while((line = reader.readLine()) != null) {
			//if found replace occupied to vacant
			if(Objects.equals(line, Integer.toString(psn)+",vacant")) {
				writer.write(Integer.toString(psn)+",occupied");
				writer.newLine();
				check=true;
				continue;
			}
			//add others to temp.txt
			writer.write(line);
			writer.newLine();
		}
		
		reader.close();
		writer.close();
		file.delete();
		temp.renameTo(file);
		
		if(check)
        	System.out.println("Space is now occupied!");
		else
			System.out.println("Space is already occcupied!");
		
		return check;
	}
	/**
	public static void main(String[] args) throws IOException, InterruptedException {
		ParkingSpace p = new ParkingSpace();
		//System.out.println(p.makeVacant(1));
		//System.out.println(p.makeOccupied(1));
		//System.out.println(p.alreadyExist(1));
		//p.addSpace(2);
		//boolean s = p.removeSpace(1);
		//System.out.println(p.requestExist("123e5307e88"));
		//System.out.println("test:"+p.removeRequest("!@#"));
		//p.grant("1aff8450");
		//p.grant("1aff8450");
		//System.out.println(p.removeRequest("1aff8450"));
		//System.out.println(p.removeRequest("1aff8450"));
		
	}
	*/
}
