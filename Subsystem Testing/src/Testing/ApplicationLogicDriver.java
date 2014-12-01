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
		schedule.addClass("STN;2123;1010101;02:30;04:30");
		schedules.add(schedule);
		int page=0;
		String webpage=facade.buildSchedulesPage(schedules, page);
		String expected="<form id=\"form1\" method=\"post\" action=\"\">"+"<h2>Schedule"+ (page+1) + "</h2>"+"<table>"+ "<tr>"+"<td>" + "02" + "</td>"+"<td>" + "30"+ "</td>"+"<tr>"+"<td>" + "04" + "</td>"+"<td>" + "30"+ "</td>"+"</table>";
		Assert.assertEquals(expected,webpage);

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
		Assert.assertTrue(schedules.isEmpty());
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
		Assert.assertTrue(schedules.isEmpty());
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
	
	
	}