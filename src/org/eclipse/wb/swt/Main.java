package org.eclipse.wb.swt;

/**
 * Driver for Running the Application
 * @author Mark
 *
 */
public class Main {
	
	public static void main(String[] args) {
		UserLoginGUI window = new UserLoginGUI();
		window.frmParkingApplication.setVisible(true);
	}
	
	/**
		Clark,Kent,ck@email.com,ClarkKent,6,2021-04-24 16:00:00,60THAM,81114e92,Parking Spot Number is Occupied!
		Clark,Kent,ck@email.com,ClarkKent,3,2021-04-18 16:00:00,60THAM,12d028f2,Time Expired!
		Map<String, Boolean> notify = new HashMap<String, Boolean>();
		notify.put("81114e92", true);
	 */
}
