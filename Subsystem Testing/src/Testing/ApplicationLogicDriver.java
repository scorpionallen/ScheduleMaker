package Testing;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import Storage.*;
import ApplicationLogic.ScheduleMakerControllerFacade;
import ApplicationLogic.ScheduleOptions;

public class ApplicationLogicDriver {
	/*
	 * Purpose: Testing the method buildSchedulesPage() in the subsystem facade
	 * Precondition: There exists a collection of Schedules which is a collection of ClassDetails objects specifying the classes in the Schedule.
	 * Input: a Schedule is defined which will be checked.
	 * Expected output: Assert.assertEquals(expected,webpage) should return true
	 * 
	 */
	@Test
	public void TEAM3_APPLOGIC_ST01() {
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
				
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		Schedule schedule=new Schedule("1231231");
		schedule.addClass("STN;2123;1111111;02:30;04:30");
		schedules.add(schedule);
	
		String webpage;
		int page=1;
		webpage=facade.buildSchedulesPage(schedules, page);		
		
		
		Assert.assertTrue(webpage!=null);

			}
	
	/*
	 * purpose: Testing the method createSchedule(schOpt);
	 * Preconditions: 
	 * 				- The Classdetails, coursedetails , campus, term and subject are defined in the storagestub.
	 * 				- There are ScheduleOptions schOpt object mentioning the search attributes.
	 * Input: The SchOpt object attributes are defined by calling its memeber functions eg: SchOpt.setTerm(); 
	 * Expected Output: A Schedule is added to the collection after calling createSchedule() method.
	 * 
	 */

	@Test
public void TEAM3_APPLOGIC_ST02() {
		StorageStub.initCourses();
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","1100100");
		cd0.setTime(t0);
		cd0.subject="STN";
		cd0.ctlgnumbr="2123";
		
		StorageStub.registerCourse("University", "Fall 2014", "STN2123", cd0);
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("01:30","02:20","1100100");
		cd1.setTime(t1);
		cd1.subject="CAP";
		cd1.ctlgnumbr="6123";
		StorageStub.registerCourse("University", "Fall 2014", "CAP6123", cd1);
		
		ClassDetails cd2=new ClassDetails();
		Time t2= new Time("05:30","07:30","1100100");
		cd2.setTime(t2);
		cd2.subject="COT";
		cd2.ctlgnumbr="5124";
		StorageStub.registerCourse("University", "Fall 2014", "COT5124", cd2);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus("University");
		schOpt.setCourse1("STN2123");
		schOpt.setCourse3("CAP6123");
		schOpt.setCourse2("COT5124");
		schOpt.setTerm("Fall 2014");
		schOpt.setM("1");
		schOpt.setF("1");
		schOpt.setT("1");
				
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		//Assert.assertTrue(schedules.isEmpty());
		schedules=facade.createSchedule(schOpt);
		Assert.assertFalse(schedules.isEmpty());
		
	
		
	}
	
	/*
	 * purpose: Testing the method createSchedule(term,collection<String> courses,campus,Days);
	 * Preconditions: 
	 * 				- The Classdetails, coursedetails , campus, term, time and subject are defined in the storagestub.
	 * 				- The object attributes for calling the function are defined.
	 * Input: The different subjects wanting to enrroll and there attibutes are given i.e., University Fall 2014 STN2123,CAP6123,COT5124 and days 1100100 is given as input.  
	 * Expected Output: A Schedule is added to the collection after calling createSchedule() method. Assert.assertFalse(schedules.isEmpty()); gives true.
	 * 
	 */

	@Test
public void TEAM3_APPLOGIC_ST03() {
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="University";
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","1100100");
		cd0.setTime(t0);
		cd0.subject="STN";
		cd0.ctlgnumbr="2123";
		
		StorageStub.registerCourse(camps, term, "STN2123", cd0);
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("01:30","02:20","1100100");
		cd1.setTime(t1);
		cd1.subject="CAP";
		cd1.ctlgnumbr="6123";
		StorageStub.registerCourse(camps, term, "CAP6123", cd1);
		
		ClassDetails cd2=new ClassDetails();
		Time t2= new Time("05:30","07:30","1100100");
		cd2.setTime(t2);
		cd2.subject="COT";
		cd2.ctlgnumbr="5124";
		StorageStub.registerCourse(camps, term, "COT5124", cd2);
		
		
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		Collection<String> courses;
		courses=new ArrayList<String>();
		courses.add("STN2123");
		courses.add("CAP6123");
		courses.add("COT5124");
		
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		//Assert.assertTrue(schedules.isEmpty());
		schedules=facade.createSchedule(term, courses, camps, "1100100");
		Assert.assertFalse(schedules.isEmpty());
		
	}
	/*
	 * Purpose: Testing the isLoginValid() method to check the credentials.
	 * PreConditions:
	 * 				- There are pantherId and password stored in the StorageStub for implementing Login Credentials.
	 * 				- The defined PantherId: "1231231" and Password:"abcabc123" stored in storageStub.
	 * Input: The input is PantherId: "1231231" and password: "abcabc123".
	 * Expected Output: assertTrue(facade.isLoginValid(pantherId, password)) give true.
	 */
	@Test
	public void TEAM3_APPLOGIC_ST04() {
		String pantherId="1231231";
		String password="abcabc123";
		StorageStub.registerCredentials(pantherId, password);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		
		Assert.assertTrue(facade.isLoginValid(pantherId, password));
		
	}
	/*
	 * Purpose: Testing the isLoginValid() method to check the credentials.
	 * PreConditions:
	 * 				- There are pantherId and password stored in the StorageStub for implementing Login Credentials.
	 * 				- The defined PantherId: "1231231" and Password:"abcabc123" stored in storageStub.
	 * Input: The input is PantherId: null and password: "abcabc123".
	 * Expected Output: Assert.assertFalse(facade.isLoginValid(null, password)); give true. 
	 * 
	 * 
	 */
	@Test
	public void TEAM3_APPLOGIC_ST05() {
		String pantherId="1231231";
		String password="abcabc123";
		StorageStub.registerCredentials(pantherId, password);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		
		Assert.assertFalse(facade.isLoginValid(null, password));
		
	}
	
	/*
	 * Purpose: Testing the isLoginValid() method to check the credentials.
	 * PreConditions:
	 * 				- There are pantherId and password stored in the StorageStub for implementing Login Credentials.
	 * 				- The defined PantherId: "1231231" and Password:"abcabc123" stored in storageStub.
	 * Input: The input is PantherId: "1231231" and password: null.
	 * Expected Output: Assert.assertFalse(facade.isLoginValid(pantherId, null)); give true. 
	 * 
	 */
	@Test
	public void TEAM3_APPLOGIC_ST06() {
		String pantherId="1231231";
		String password="abcabc123";
		StorageStub.registerCredentials(pantherId, password);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		
		Assert.assertFalse(facade.isLoginValid(pantherId, null));
		
	}
	
	/*
	 * Purpose: Testing the isLoginValid() method to check the credentials.
	 * PreConditions:
	 * 				- There are pantherId and password stored in the StorageStub for implementing Login Credentials.
	 * 				- The defined PantherId: "1231231" and Password:"abcabc123" stored in storageStub.
	 * Input: The input is PantherId: "1242142" and password: abcabc123.
	 * Expected Output: Assert.assertFalse(facade.isLoginValid("1242142", password)); give true.  
	 * 
	 * 
	 * 
	 */
	@Test
	public void TEAM3_APPLOGIC_ST07() {
		String pantherId="1231231 ";
		String password="abcabc123";
		StorageStub.registerCredentials(pantherId, password);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		Assert.assertFalse(facade.isLoginValid("1242142", password));
		
	}
	/*
	 * Purpose: Testing the isLoginValid() method to check the credentials.
	 * PreConditions:
	 * 				- There are pantherId and password stored in the StorageStub for implementing Login Credentials.
	 * 				- The defined PantherId: "1231231" and Password:"abcabc123" stored in storageStub.
	 * Input: The input is PantherId: "1231231" and password: abcbac123.
	 * Expected Output: Assert.assertFalse(facade.isLoginValid(pantherId, "abcbac123")); give true.  
	 */
	@Test
	public void TEAM3_APPLOGIC_ST08() {
		String pantherId="1231231";
		String password="abcabc123";
		StorageStub.registerCredentials(pantherId, password);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		
		Assert.assertFalse(facade.isLoginValid(pantherId, "abcbac123"));
		
	}
	
	/*
	 * Test cases : testing LoginOptions class functions.
	 * 
	 * 
	 * 
	 */
	@Test
	public void TEAM3_APPLOGIC_ST9()  {
		String pantherId="1231231";
		String password="abcabc123";
		
			StorageStub.registerCredentials(pantherId, password);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		facade.setPantherID(pantherId);
		facade.setPassword(password);
		
		Assert.assertFalse(facade.isLoginValid(pantherId, facade.getPantherID()));
		
		//Assert.assertEquals(password, facade.getPassword());
		//Assert.assertEquals(pantherId, facade.getPantherID());
		
		
	}
	
	
	/*
	 * Test cases : testing LoginOptions class functions.
	 * 
	 * 
	 * 
	 */
	
	@Test
	public void TEAM3_APPLOGIC_ST10()  {
		String pantherId="1231231";
		String password="abcabc123";
		
			StorageStub.registerCredentials(pantherId, password);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		facade.setPantherID(pantherId);
		facade.setPassword(password);
		
		Assert.assertFalse(facade.isLoginValid(password, facade.getPassword()));
		
		//Assert.assertEquals(password, facade.getPassword());
		//Assert.assertEquals(pantherId, facade.getPantherID());
		
		
	}
	
	
	
	@Test
	public void TEAM3_APPLOGIC_ST11() {
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		
		
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","1100100");
		cd0.setTime(t0);
		cd0.subject="STN";
		cd0.ctlgnumbr="2123";
		
		StorageStub.registerCourse("University", "Fall 2014", "STN2123", cd0);
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("01:30","02:20","0011011");
		cd1.setTime(t1);
		cd1.subject="CAP";
		cd1.ctlgnumbr="6123";
		StorageStub.registerCourse("University", "Fall 2014", "CAP6123", cd1);
		
		ClassDetails cd2=new ClassDetails();
		Time t2= new Time("05:30","07:30","1100100");
		cd2.setTime(t2);
		cd2.subject="COT";
		cd2.ctlgnumbr="5124";
		StorageStub.registerCourse("University", "Fall 2014", "COT5124", cd2);
		
		ClassDetails cd3=new ClassDetails();
		Time t3= new Time("09:30","12:30","0011011");
		cd3.setTime(t3);
		cd3.subject="PHY";
		cd3.ctlgnumbr="5111";
		StorageStub.registerCourse("University", "Fall 2014", "PHY5111", cd3);
		
		ClassDetails cd4=new ClassDetails();
		Time t4= new Time("07:40","08:30","1100100");
		cd4.setTime(t4);
		cd4.subject="CHM";
		cd4.ctlgnumbr="4521";
		StorageStub.registerCourse("University", "Fall 2014", "CHM4521", cd4);
		
		
		ClassDetails cd5=new ClassDetails();
		Time t5= new Time("03:40","04:40","0011011");
		cd5.setTime(t5);
		cd5.subject="ART";
		cd5.ctlgnumbr="5110";
		StorageStub.registerCourse("University", "Fall 2014", "ART5110", cd5);
		
		
		
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus("University");
		schOpt.setCourse1("STN2123");
		schOpt.setCourse3("CAP6123");
		schOpt.setCourse2("COT5124");
		schOpt.setCourse4("PHY5111");
		schOpt.setCourse5("CHM4521");
		schOpt.setCourse6("ART5110");
		schOpt.setTerm("Fall 2014");
		schOpt.setTh("1");
		schOpt.setM("1");
		schOpt.setS("1");
		schOpt.setW("1");
		schOpt.setF("1");
		schOpt.setT("1");
		schOpt.setSu("1");
		Collection<Schedule> schedules;
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		String webpage;
		int page=1;
		webpage=facade.buildSchedulesPage(schedules, page);		
			
		Assert.assertTrue(webpage!=null);

			}
	
	
	@Test
	public void TEAM3_APPLOGIC_ST12() {
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
			
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","0011011");
		cd0.setTime(t0);
		cd0.subject="STN";
		cd0.ctlgnumbr="2123";
		
		StorageStub.registerCourse("Biscayne", "Fall 2014", "STN2123", cd0);
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("02:30","04:20","0011011");
		cd1.setTime(t1);
		cd1.subject="CAP";
		cd1.ctlgnumbr="6123";
		StorageStub.registerCourse("University", "Fall 2014", "CAP6123", cd1);
		
		 	
		ClassDetails cd3=new ClassDetails();
		Time t3= new Time("09:30","12:30","0011011");
		cd3.setTime(t3);
		cd3.subject="PHY";
		cd3.ctlgnumbr="5111";
		StorageStub.registerCourse("University", "Fall 2014", "PHY5111", cd3);
		
				
		
		ClassDetails cd5=new ClassDetails();
		Time t5= new Time("03:40","04:40","0011011");
		cd5.setTime(t5);
		cd5.subject="ART";
		cd5.ctlgnumbr="5110";
		StorageStub.registerCourse("University", "Fall 2014", "ART5110", cd5);
		
		
		
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus("University");
		schOpt.setCourse1("STN2123");
		schOpt.setCourse3("CAP6123");
		schOpt.setCourse4("PHY5111");
		schOpt.setCourse6("ART5110");
		schOpt.setTerm("Fall 2014");
		schOpt.setTh("1");
		schOpt.setS("1");
		schOpt.setW("1");
		schOpt.setSu("1");
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		Assert.assertFalse(schedules.size()==0); 
	}
	
	@Test
	public void TEAM3_APPLOGIC_ST13() {
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
			
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","1100100");
		cd0.setTime(t0);
		cd0.subject="STN";
		cd0.ctlgnumbr="2123";
		
		StorageStub.registerCourse("Biscayne", "Fall 2014", "STN2123", cd0);
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("01:30","02:20","0011011");
		cd1.setTime(t1);
		cd1.subject="CAP";
		cd1.ctlgnumbr="6123";
		StorageStub.registerCourse("University", "Fall 2014", "CAP6123", cd1);
		
		ClassDetails cd2=new ClassDetails();
		Time t2= new Time("05:30","07:30","1100100");
		cd2.setTime(t2);
		cd2.subject="COT";
		cd2.ctlgnumbr="5124";
		StorageStub.registerCourse("University", "Fall 2014", "COT5124", cd2);
		
		ClassDetails cd3=new ClassDetails();
		Time t3= new Time("09:30","12:30","0011011");
		cd3.setTime(t3);
		cd3.subject="PHY";
		cd3.ctlgnumbr="5111";
		StorageStub.registerCourse("University", "Fall 2014", "PHY5111", cd3);
		
		ClassDetails cd4=new ClassDetails();
		Time t4= new Time("07:40","08:30","1100100");
		cd4.setTime(t4);
		cd4.subject="CHM";
		cd4.ctlgnumbr="4521";
		StorageStub.registerCourse("University", "Fall 2014", "CHM4521", cd4);
		
		
		ClassDetails cd5=new ClassDetails();
		Time t5= new Time("03:40","04:40","0011011");
		cd5.setTime(t5);
		cd5.subject="ART";
		cd5.ctlgnumbr="5110";
		StorageStub.registerCourse("University", "Fall 2014", "ART5110", cd5);
		
		
		
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus("University");
		schOpt.setCourse1("STN2123");
		schOpt.setCourse3("CAP6123");
		schOpt.setCourse2("COT5124");
		schOpt.setCourse4("PHY5111");
		schOpt.setCourse5("CHM4521");
		schOpt.setCourse6("ART5110");
		schOpt.setTerm("Fall 2014");
		schOpt.setTh("1");
		schOpt.setM("1");
		schOpt.setS("1");
		schOpt.setW("1");
		schOpt.setF("1");
		schOpt.setT("1");
		schOpt.setSu("1");
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		Assert.assertFalse(schedules.size()==0); 
	}
	
	
	@Test
	public void TEAM3_APPLOGIC_ST14() {
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
			
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
			
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		Assert.assertTrue(schedules.size()==0); 
	}
	
	@Test
	public void TEAM3_APPLOGIC_ST15() {
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
			
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","0");
		cd0.setTime(t0);
		cd0.subject="STN";
		cd0.ctlgnumbr="2123";
		
		StorageStub.registerCourse("University", "Fall 2014", "STN2123", cd0);
		
		
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus("ALL");
		schOpt.setCourse1("STN2123");
		schOpt.setM("1");
		schOpt.setF("1");
		schOpt.setT("1");
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		Assert.assertTrue(schedules.size()==0); 
	}
	
	
	@Test
	public void TEAM3_APPLOGIC_ST16() {
			StorageStub.initCourses();
			String term="Fall 2014";
			String camps="University";
					
			ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
			Collection<String> courses;
			courses=new ArrayList<String>();
					
			Collection<Schedule> schedules;	
			schedules = new ArrayList<Schedule>();
			//Assert.assertTrue(schedules.isEmpty());
			schedules=facade.createSchedule(term, courses, camps, "1100100");
			Assert.assertTrue(schedules.isEmpty());
			
		}
	
	
	@Test
	public void TEAM3_APPLOGIC_ST17() {
			StorageStub.initCourses();
			String term="Fall 2014";
			String camps="ALL";
			ClassDetails cd0=new ClassDetails();
			Time t0= new Time("02:30","03:30","1100100");
			cd0.setTime(t0);
			cd0.subject="STN";
			cd0.ctlgnumbr="2123";
			
			StorageStub.registerCourse(camps, term, "STN2123", cd0);
			ClassDetails cd1=new ClassDetails();
			Time t1= new Time("01:30","02:20","1100100");
			cd1.setTime(t1);
			cd1.subject="CAP";
			cd1.ctlgnumbr="6123";
			StorageStub.registerCourse(camps, term, "CAP6123", cd1);
			
			ClassDetails cd2=new ClassDetails();
			Time t2= new Time("05:30","07:30","1100100");
			cd2.setTime(t2);
			cd2.subject="COT";
			cd2.ctlgnumbr="5124";
			StorageStub.registerCourse(camps, term, "COT5124", cd2);
			
			
			
			ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
			Collection<String> courses;
			courses=new ArrayList<String>();
			courses.add("STN2123");
			courses.add("CAP6123");
			courses.add("COT5124");
			
			Collection<Schedule> schedules;	
			schedules = new ArrayList<Schedule>();
			//Assert.assertTrue(schedules.isEmpty());
			schedules=facade.createSchedule(term, courses, camps, "0011000");
			Assert.assertTrue(schedules.isEmpty());
			
		}
	}