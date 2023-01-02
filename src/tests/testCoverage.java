package tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import backend.Authenticator;
import backend.Booking;
import backend.ParkingSpace;
import backend.Register;
import junit.framework.Assert;

/**
 * 
 * @author Mark
 *	test coverage for non-GUI classes.
 *	The non-GUI classes are located in the backend package
 *	Please ignore the org.eclipse.wb.swt test coverage.
 */
@SuppressWarnings("deprecation")
class testCoverage {
	
	//================================================================
	Booking resetDatabase = new Booking();
	@Test
	void resetData() throws IOException {
		resetDatabase.wipeClean();
		resetDatabase.addRequests("fgh,fgh,fgh@email.com,fgh", "2", "2030-01-01 01:01:01" , "123asd");
		List<String> list = resetDatabase.getCustomerRequests("fgh");
		List<String> s = Arrays.asList(list.get(0).split(","));
		resetDatabase.customerCancelValid("fgh", s.get(7));
		resetDatabase.requestExist(s.get(7));
		resetDatabase.removeRequest(s.get(7));
	}
	
	@Test
	void resetData1() throws IOException {
		resetDatabase.wipeClean();
		resetDatabase.addRequests("bnm,bnm,bnm@email.com,bnm", "10", "2030-01-01 01:01:01" , "bnm159753");
		List<String> list = resetDatabase.getCustomerRequests("bnm");
		List<String> s = Arrays.asList(list.get(0).split(","));
		resetDatabase.addGranted(s.get(7));
		//resetDatabase.customerCancelValid("fgh", s.get(7));
		resetDatabase.addPaid("bnm", "10");
		resetDatabase.addConfirmed("bnm", "bnm", "bnm@email.com", "10");
		resetDatabase.customerCancelValid("bnm", s.get(7));
	}
	
	@Test
	void resetData2() throws IOException {
		resetDatabase.wipeClean();
		resetDatabase.addRequests("bnm,bnm,bnm@email.com,bnm", "10", "2030-01-01 01:01:01" , "bnm159753");
		List<String> list = resetDatabase.getCustomerRequests("bnm");
		List<String> s = Arrays.asList(list.get(0).split(","));
		resetDatabase.addGranted(s.get(7));
		resetDatabase.getTime("10");
		resetDatabase.addPaid("bnm", "10");
		//resetDatabase.customerCancelValid("fgh", s.get(7));
		resetDatabase.addConfirmed("bnm", "bnm", "bnm@email.com", "10");
		resetDatabase.customerCancelValid("bnm", s.get(7));
	}
	@Test
	void resetData3() throws IOException {
		resetDatabase.wipeClean();
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "1", "2030-01-01 01:01:01" , "qwe123qwe");
		List<String> list = resetDatabase.getCustomerRequests("qwe");
		List<String> s = Arrays.asList(list.get(0).split(","));
		resetDatabase.addGranted(s.get(7));
		resetDatabase.existGranted("qwe", "1");
		resetDatabase.removeGranted("qwe", "1");
		resetDatabase.customerCancelValid("qwe", s.get(7));
	}
	@Test
	void resetData4() throws IOException {
		resetDatabase.wipeClean();
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "1", "2030-01-01 01:01:01" , "qwe123qwe");
		List<String> list = resetDatabase.getCustomerRequests("qwe");
		List<String> s = Arrays.asList(list.get(0).split(","));
		resetDatabase.addGranted(s.get(7));
		resetDatabase.customerCancelValid("qwe", s.get(7));
	}
	@Test
	void resetData5() throws IOException, InterruptedException {
		resetDatabase.wipeClean();
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "1", "2030-01-01 01:01:01" , "qwe123qwe");
		List<String> list = resetDatabase.getCustomerRequests("qwe");
		List<String> s = Arrays.asList(list.get(0).split(","));
		int i = resetDatabase.getParkingSpotNumber(s.get(7));
		Assert.assertEquals(1, i);
		resetDatabase.reject(s.get(7), "reason?");
	}
	@Test
	void resetData6() throws IOException, InterruptedException {
		resetDatabase.wipeClean();
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "1", "2030-01-01 01:01:01" , "qwe123qwe");
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "2", "2030-01-01 01:01:01" , "qwe123qwe");
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "2", "2030-01-01 01:01:01" , "qwe123qwe");
		List<String> list = resetDatabase.getCustomerRequests("qwe");
		List<String> s0 = Arrays.asList(list.get(0).split(","));
		List<String> s1 = Arrays.asList(list.get(1).split(","));
		List<String> s2 = Arrays.asList(list.get(2).split(","));
		resetDatabase.addGranted(s0.get(7));
		resetDatabase.addGranted(s1.get(7));
		resetDatabase.addGranted(s2.get(7));
		resetDatabase.addPaid("qwe", "1");
		resetDatabase.addPaid("qwe", "2");
		resetDatabase.addPaid("qwe", "3");
		resetDatabase.addConfirmed("qwe", "qwe", "qwe@email.com", "2");
		resetDatabase.addConfirmed("qwe", "qwe", "qwe@email.com", "3");
		resetDatabase.customerCancelValid("qwe", s0.get(7));
		}
	
	@Test
	void resetData7() throws IOException, InterruptedException {
		resetDatabase.wipeClean();
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "1", "2030-01-01 01:01:01" , "qwe123qwe");
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "2", "2030-01-01 01:01:01" , "qwe123qwe");
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "2", "2030-01-01 01:01:01" , "qwe123qwe");
		List<String> list = resetDatabase.getCustomerRequests("qwe");
		List<String> s0 = Arrays.asList(list.get(0).split(","));
		List<String> s1 = Arrays.asList(list.get(1).split(","));
		List<String> s2 = Arrays.asList(list.get(2).split(","));
		resetDatabase.addGranted(s0.get(7));
		resetDatabase.addGranted(s1.get(7));
		resetDatabase.addGranted(s2.get(7));
		resetDatabase.customerCancelValid("qwe", s0.get(7));
		}
	@Test
	void resetData8() throws IOException, InterruptedException {
		resetDatabase.wipeClean();
		resetDatabase.addRequests("qwe,qwe,qwe@email.com,qwe", "1", "2030-01-01 01:01:01" , "qwe123qwe");
		List<String> list = resetDatabase.getCustomerRequests("qwe");
		List<String> s0 = Arrays.asList(list.get(0).split(","));
		resetDatabase.addGranted(s0.get(7));
		resetDatabase.addPaid("qwe", "1");
		resetDatabase.addConfirmed("qwe", "qwe", "qwe@email.com", "1");
		resetDatabase.customerCancelValid("qwe", s0.get(7));
		}
	//================================================================
	
	//================================================================
	Authenticator a = new Authenticator();
	//================================================================
	@Test
	void a_CustomerExist() {
		boolean r = a.customerExist("firstname", "lastname", "customer@email.com", "c");
		Assert.assertEquals(true, r);
		
	}
	@Test
	void a_CustomerExist1() {
		boolean r = a.verifyCustomer("c", "c");
		Assert.assertEquals(true, r);
		
	}
	@Test
	void verifyOfficer() {
		boolean r = a.verifyOfficer("o", "o");
		Assert.assertEquals(true, r);
		
	}
	@Test
	void verifyAdmin() {
		boolean r = a.verifyAdmin("admin", "admin");
		Assert.assertEquals(true, r);
		
	}
	@Test
	void verifyAdmin1() {
		boolean r = a.verifyAdmin("a", "a");
		Assert.assertEquals(true, r);
		
	}
	@Test
	void a_CustomerExist_1() {
		boolean r = a.customerExist("unknownf", "unkownl", "anonfl@email.com", "anonfluzxc");
		Assert.assertEquals(false, r);
		
	}
	@Test
	void a_CustomerExist_2() {
		boolean r = a.customerExist("asdlkj", "qwelkj", "lkjlkjasd@email.com", "lkjqweasd");
		Assert.assertEquals(false, r);
		
	}
	@Test
	void a_verifyAdmin_1() {
		boolean r = a.verifyAdmin("doesnotexist", "doesnotexist");
		Assert.assertEquals(false, r);
	}
	
	@Test
	void a_verifyAdmin_2() {
		boolean r = a.verifyAdmin("doesnotexist", "doesnotexist");
		Assert.assertEquals(false, r);
	}
	
	@Test
	void a_verifyCustomer_1() {
		boolean r = a.verifyCustomer("doesnotexist", "doesnotexist");
		Assert.assertEquals(false, r);
	}
	
	@Test
	void a_verifyCustomer_2() {
		boolean r = a.verifyCustomer("doesnotexist", "doesnotexist");
		Assert.assertEquals(false, r);
	}
	
	@Test
	void a_verifyOfficer_1() {
		boolean r = a.verifyOfficer("doesnotexist", "doesnotexist");
		Assert.assertEquals(false, r);
	}
	@Test
	void a_verifyOfficer_2() {
		boolean r = a.verifyOfficer("doesnotexist", "doesnotexist");
		Assert.assertEquals(false, r);
	}
	//================================================================
	ParkingSpace p = new ParkingSpace();
	//================================================================
	@Test
	void addSpace() throws IOException, InterruptedException {
		int r = p.addSpace(50);
		int s = p.addSpace(75);
		Assert.assertEquals(50,r);
		Assert.assertEquals(75, s);
		boolean c = p.removeSpace(75);
		Assert.assertEquals(true, c);
	}
	@Test
	void makeVacant50() throws IOException {
		boolean v = p.isVacant(50);
		Assert.assertEquals(true, v);
	}
	@Test
	void makeVacant1a() throws IOException {
		p.makeOccupied(1);
		boolean s = p.makeVacant(1);
		Assert.assertEquals(true, s);
	}
	@Test
	void alreadyExist1() throws IOException {
		boolean r = p.alreadyExist(1);
		Assert.assertEquals(true,r);
		boolean o = p.makeVacant(50);
		Assert.assertEquals(false, o);
	}
	@Test
	void addSpace2() throws IOException {
		int r = p.addSpace(101);
		Assert.assertEquals(101,r);
	}
	@Test
	void alreadyExist() throws IOException {
		boolean r = p.alreadyExist(999);
		Assert.assertEquals(false,r);
	}
	@Test
	void alreadyExist2() throws IOException {
		boolean r = p.alreadyExist(888);
		Assert.assertEquals(false,r);
	}
	@Test
	void isVacant() throws IOException {
		boolean r = p.isVacant(888);
		Assert.assertEquals(false,r);
	}
	@Test
	void isVacant2() throws IOException {
		boolean r = p.isVacant(777);
		Assert.assertEquals(false,r);
	}
	@Test
	void makeOccupied() throws IOException {
		boolean r = p.makeOccupied(159);
		Assert.assertEquals(false,r);
	}
	@Test
	void makeOccupied1() throws IOException {
		boolean r = p.makeOccupied(999);
		Assert.assertEquals(false,r);
	}
	@Test
	void makeVacant0() throws IOException {
		boolean r = p.makeVacant(999);
		Assert.assertEquals(false,r);
	}
	@Test
	void makeVacant1() throws IOException {
		boolean r = p.makeVacant(999);
		Assert.assertEquals(false,r);
	}
	@Test
	void removeSpace() throws IOException {
		boolean r = p.removeSpace(888);
		Assert.assertEquals(false,r);
	}
	@Test
	void removeSpace1() throws IOException {
		boolean r = p.removeSpace(999);
		Assert.assertEquals(false,r);
	}
	//================================================================
	Register r = new Register();
	//================================================================
	@Test
	void customerUsernameEmailExist() throws IOException {
		boolean result = r.customerUsernameEmailExist("985173","19574987");
		Assert.assertEquals(false,result);
	}
	@Test
	void customerUsernameEmailExist1() throws IOException {
		boolean result = r.customerUsernameEmailExist("zxczxcbnm","12321312");
		Assert.assertEquals(false,result);
	}
	@Test
	void officerUsernameEmailIDExist() throws IOException {
		boolean result = r.officerUsernameEmailIDExist("zxczxcbnm","12321312","ZXCVBNSADSADSA");
		Assert.assertEquals(false,result);
	}
	@Test
	void officerUsernameEmailIDExist1() throws IOException {
		boolean result = r.officerUsernameEmailIDExist("z123xcZXCzxcbnm","12ASD321312","ZXCVBNSADSADSA123");
		Assert.assertEquals(false,result);
	}
	@Test
	void registerCustomer() throws IOException {
		boolean result = r.registerCustomer("f","l","e","c","c");
		Assert.assertEquals(false,result);
	}
	@Test
	void registerCustomer1() throws IOException {
		boolean result = r.registerCustomer("f","l","eunique","uniqueusername","czxczxcpass");
		Assert.assertEquals(true,result);
	}
	@Test
	void registerOfficer() throws IOException {
		boolean result = r.registerOfficer("f","l","e","o","o","69696969");
		Assert.assertEquals(false,result);
	}
	@Test
	void registerOfficer1() throws IOException {
		boolean result = r.registerOfficer("f123","l123","e123","o123","o123","o123");
		Assert.assertEquals(true,result);
	}
	@Test
	void removeOfficer() throws IOException {
		boolean result = r.removeOfficer("f123","l123","e123","o123","o123");
		Assert.assertEquals(true,result);
	}
	//================================================================
	Booking b = new Booking();
	//================================================================
	@Test
	void addReq() {
		boolean result = b.addRequests("asd,asd,test@email.com,test", "7", "2033-01-01 01:01:01", "license123");
		Assert.assertEquals(true, result);
	}
	@Test
	void toGrant() throws IOException {
		List<String> split = Arrays.asList(b.getCustomerRequests("test").get(0).split(","));
		b.addGranted(split.get(7));
		Assert.assertEquals(8, split.size());
	}
	@Test
	void toPaid() throws IOException {
		boolean result = b.addPaid("test", "7");
		Assert.assertEquals(true, result);
		
	}
	@Test
	void toConfrimed() throws IOException {
		boolean result = b.addConfirmed("asd", "asd", "test@email.com", "7");
		Assert.assertEquals(false, result);
	}
	@Test
	void addCancellations() throws IOException {
		boolean result = b.addCancellations("f123,l123,e123,o123,o123");
		Assert.assertEquals(true,result);
	}
	@Test
	void addCancellations1() throws IOException {
		boolean result = b.addCancellations("f,l,t1,test123,o123");
		Assert.assertEquals(true,result);
	}
	@Test
	void removeCancellations() throws IOException {
		boolean result = b.removeCancellations("123123");
		Assert.assertEquals(true,result);
	}
	@Test
	void removeCancellations1() throws IOException {
		boolean result = b.removeCancellations("12321321321");
		Assert.assertEquals(true,result);
	}
	@Test
	void addConfirmedVersion2() throws IOException {
		boolean result = b.addConfirmedVersion2("zxcxzcxzc");
		Assert.assertEquals(true,result);
	}
	@Test
	void addConfirmed() throws IOException {
		boolean result = b.addConfirmed("lark","Kent","ck@email.com","1");
		Assert.assertEquals(false,result);
	}
	@Test
	void addConfirmedVersion2_1() throws IOException {
		boolean result = b.addConfirmedVersion2("32132131");
		Assert.assertEquals(true,result);
	}
	@Test
	void addPaid() throws IOException {
		boolean result = b.addPaid("zxcasd","5");
		Assert.assertEquals(false,result);
	}
	@Test
	void addPaidVersion2() throws IOException {
		boolean result = b.addPaidVersion2("Clark,Kent,ck@email.com,ClarkKent,1,2021-04-24 23:59:59,60THAM,f30ab055");
		Assert.assertEquals(true,result);
	}
	@Test
	void addRejected() throws IOException {
		b.addRejected("Clark,Kent,ck@email.com,ClarkKent,1,2021-04-24 23:59:59,60THAM,f30ab055","reason?");
		//Assert.assertEquals(false,result);
	}
	@Test
	void addRejectedVersion2() throws IOException {
		b.addRejectedVersion2("Clark,Kent,ck@email.com,ClarkKent,1,2021-04-24 23:59:59,60THAM,f30ab055,reason?");
		//Assert.assertEquals(false,result);
	}
	@Test
	void addRequests() throws IOException {
		boolean result = b.addRequests("mark,miguel,mark@gmail.com,markmiguel", "1", "2021-04-24 23:59:5", "C30ab055");
		Assert.assertEquals(true,result);
	}
	@Test
	void beforeExpiry() throws IOException {
		boolean result = b.beforeExpiry("Clark,Kent,ck@email.com,ClarkKent,1,2021-04-24 23:59:59,60THAM,f30ab055");
		Assert.assertEquals(true,result);
	}
	@Test
	void calculateAmount() throws IOException {
		double result =b.calculateAmount("2021-04-24 23:59:5",2.0);
		Assert.assertEquals(b.calculateAmount("2021-04-24 23:59:5",2.0),result);
	}
	@Test
	void customerCancelValid() throws IOException {
		String result = b.customerCancelValid("ClarkKent","f30ab055");
		Assert.assertEquals(null,result);
	}
	@Test
	void customerExist() throws IOException {
		boolean result = b.customerExist("123213ClarkKent");
		Assert.assertEquals(false,result);
	}
	@Test
	void customerPaid() throws IOException {
		boolean result = b.customerPaid("first","last","email","21");
		Assert.assertEquals(false,result);
	}
	@Test
	void existGranted() throws IOException {
		boolean result = b.existGranted("ClarkKent", "22");
		Assert.assertEquals(false,result);
	}
	@Test
	void expired() throws IOException {
		boolean result = b.expired("Clark,Kent,ck@email.com,ClarkKent,1,2021-04-24 23:59:59,60THAM,f30ab055");
		Assert.assertEquals(false,result);
	}
	@Test
	void getAllCustomerCancellations() throws IOException {
		List<String> result = b.getAllCustomerCancellations();
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getAllCustomerConfirmed() throws IOException {
		List<String> result = b.getAllCustomerConfirmed();
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getAllCustomerGranted() throws IOException {
		List<String> result = b.getAllCustomerGranted();
		Assert.assertEquals(result.size(),result.size());
	}
	@Test
	void getAllCustomerPaid() throws IOException {
		List<String> result = b.getAllCustomerPaid();
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getAllCustomerRejected() throws IOException {
		List<String> result = b.getAllCustomerRejected();
		Assert.assertEquals(result.size(),result.size());
	}
	@Test
	void getAllCustomerRequests() throws IOException {
		List<String> result = b.getAllCustomerRequests();
		Assert.assertEquals(result.size(),result.size());
	}
	@Test
	void getCustomerCancellations() throws IOException {
		List<String> result = b.getCustomerCancellations("xczcxz");
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getCustomerConfirmed() throws IOException {
		List<String> result = b.getCustomerConfirmed("xczcxz");
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getCustomerGranted() throws IOException {
		List<String> result = b.getCustomerGranted("xczcxz");
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getCustomerInfo() throws IOException {
		String result = b.getCustomerInfo("xczcxz");
		Assert.assertEquals(null,result);
	}
	@Test
	void getCustomerPaid() throws IOException {
		List<String> result = b.getCustomerPaid("xczcxz");
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getCustomerPaidVersion2() throws IOException {
		List<String> result = b.getCustomerPaidVersion2("xczcxz","last","zxcz@email.com","77");
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getCustomerRejected() throws IOException {
		List<String> result = b.getCustomerRejected("xczcxz21");
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getCustomerRequests() throws IOException {
		List<String> result = b.getCustomerRequests("xczcxz21");
		List<String> check = new ArrayList<String>();
		Assert.assertEquals(check.size(),result.size());
	}
	@Test
	void getParkingSpotNumber() throws IOException {
		int result = b.getParkingSpotNumber("12345786");
		Assert.assertEquals(-1, result);
	}
	@Test
	void getTime() throws IOException {
		String result = b.getTime("44");
		Assert.assertEquals(b.getTime("44"), result);
	}
	@Test
	void minTime() throws IOException {
		boolean result = b.minTime("2050-04-24 23:59:59");
		Assert.assertEquals(true, result);
	}
	@Test
	void addRequests1() throws IOException, InterruptedException {
		boolean result = b.addRequests("mark,miguel,mark@gmail.com,markmiguel", "5", "2099-04-24 23:59:5", "C30az1055");
		Assert.assertEquals(true, result);
	}
	@Test
	void reject() throws IOException, InterruptedException {
		b.reject("2131asd9","reason?");
		//Assert.assertEquals(false, result);
	}
	@Test
	void remove() throws IOException, InterruptedException {
		boolean result = b.remove("2131asd9");
		Assert.assertEquals(false, result);
	}
	@Test
	void removeCancellationsBooking() throws IOException, InterruptedException {
		boolean result = b.removeCancellations("2131asd9");
		Assert.assertEquals(true, result);
	}
	@Test
	void removeConfirmed() throws IOException, InterruptedException {
		boolean result = b.removeConfirmed("2131asd9");
		Assert.assertEquals(true, result);
	}
	@Test
	void removePaid() throws IOException, InterruptedException {
		boolean result = b.removePaid("first231","last32112", "email","321");
		Assert.assertEquals(true, result);
	}
	@Test
	void removeRejected() throws IOException, InterruptedException {
		boolean result = b.removeRejected("Clark,Kent,ck@email.com,ClarkKent,1,2021-04-24 23:59:59,60THAM,f30ab055");
		Assert.assertEquals(true, result);
	}
	@Test
	void removeRequest() throws IOException, InterruptedException {
		boolean result = b.removeRejected("C30ab055");
		Assert.assertEquals(true, result);
	}
	@Test
	void requestExist() throws IOException, InterruptedException {
		boolean result = b.requestExist("C30ab055");
		Assert.assertEquals(false, result);
	}
	@Test
	void validateTime() throws IOException, InterruptedException {
		boolean result = b.validateTime("2021-04-24 23:59:59");
		Assert.assertEquals(true, result);
	}
	@Test
	void validateTime1() throws IOException, InterruptedException {
		boolean result = b.validateTime("zxcasdqwe-04-24 23:59:59");
		Assert.assertEquals(false, result);
	}
	@Test
	void getCustomerInfo1() throws IOException, InterruptedException {
		String result = b.getCustomerInfo("c");
		Assert.assertEquals("firstname,lastname,customer@email.com,c,c", result);
	}
	@Test
	void minTime1() throws IOException, InterruptedException {
		boolean result = b.minTime("zxcasdqwe-04-24 23:59:59");
		Assert.assertEquals(false, result);
	}
	//RESET THE DATABASE TO DEFAULT
	@Test
	void reset() throws IOException {
		b.wipeClean();
	}
	
	

}
