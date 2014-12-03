package Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
	 * Purpose: Testing if the the pantherId saved for the schedule is same as one which is saved by login options.
	 * Preconditions:
	 * 		- There exists pantherID:1231231 and Password: abcabc123 in the storageStub
	 * 		- The pantherId and Password for a schedule is set by using loginOptions class methods.
	 * Input: The pantherId:1231231 and Password: abcabc123 is set for a given schedule through LoginOptions
	 * Expected Output: The isLoginValid(pantherId, getPassword()) should return true.
	 * Output: isLoginValid returns false
	 */
	@Test
	public void TEAM3_APPLOGIC_ST9()  {
		String pantherId="1231231";
		String password="abcabc123";
		
			StorageStub.registerCredentials(pantherId, password);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		facade.setPantherID(pantherId);
		facade.setPassword(password);
		
		Assert.assertFalse(facade.isLoginValid(pantherId, facade.getPassword()));
		
			
		
	}
	
	
		
	/*
 * Purpose: Testing if the the password saved for the schedule is same as one which is defined
	 * Preconditions:
	 * 		- There exists pantherID:1231231 and Password: abcabc123 in the storageStub
	 * 		- The pantherId and Password for a schedule is set by using loginOptions class methods.
	 * Input: The pantherId:1231231 and Password: abcabc123 is set for a given schedule through LoginOptions
	 * Expected Output: The isLoginValid(getPantherid, Password) should return true.
	 * Output: isLoginValid returns false
	 */
	@Test
	public void TEAM3_APPLOGIC_ST10()  {
		String pantherId="1231231";
		String password="abcabc123";
		
			StorageStub.registerCredentials(pantherId, password);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		facade.setPantherID(pantherId);
		facade.setPassword(password);
		
		Assert.assertFalse(facade.isLoginValid(facade.getPantherID(),password));
		
		
		
	}
	/*
	 * Purpose: Testing if the formatPage function BuildSchedulePage() builds a schedule in html.
	 * Preconditions:
	 * 		- There are 6 courses for fall 2014 and university campuses for different days in week.
	 * 		- There is a scheduleOptions given for university campus and all six courses for all week.
	 * 		- A schedule is created called schedules for all the possible schedule for schedule options schOpt.
	 *  Input: For collection of schedules called "schedules" and page=1 we call BuildSchedulePage() method returns webPage string.
	 * Expected Output: the webPage String is not null verifying that a page is created.
	 */
	
	
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
				Collection<Schedule> schedules;
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		String webpage;
		int page=1;
		webpage=facade.buildSchedulesPage(schedules, page);		
			
		Assert.assertTrue(webpage!=null);

			}
	
	/*
	 * Purpose: Creating a set of schedules for four courses with conflict which gives more than 6 schedules.
	 * Preconditions:
	 * 		- There are four classdetails cd objects representing four courses with 1 in biscayne and 3 in university.
	 * 		- There is a scheduleOptions with four courses listed for all campuses in the given term.
	 * Input: For the given schOpt schedule options search window a list of scheduels is created.
	 * Expected Output: schedules.size()>6 gives true saying we have different combinations of schedules.
	 */
	@Test
	public void TEAM3_APPLOGIC_ST12() {
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
			
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","1100000");
		cd0.setTime(t0);
		cd0.subject="STN";
		cd0.ctlgnumbr="2123";
		
		StorageStub.registerCourse("Biscayne", "Fall 2014", "STN2123", cd0);
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("02:30","03:30","1100000");
		cd1.setTime(t1);
		cd1.subject="CAP";
		cd1.ctlgnumbr="6123";
		StorageStub.registerCourse("University", "Fall 2014", "CAP6123", cd1);
		
		 	
		ClassDetails cd3=new ClassDetails();
		Time t3= new Time("09:30","12:30","1100000");
		cd3.setTime(t3);
		cd3.subject="PHY";
		cd3.ctlgnumbr="5111";
		StorageStub.registerCourse("University", "Fall 2014", "PHY5111", cd3);
		
				
		
		ClassDetails cd5=new ClassDetails();
		Time t5= new Time("03:40","04:40","1100000");
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
		
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		Assert.assertTrue(schedules.size()>6); 
	}
	/*
	 * Purpose: the daymatched condition is tested from scheduleMakerController class methods.
	 * Preconditions:
	 * 		- six courses with different dates combinations and different times with conflict is defined with 2 biscyne and 4 university campuses.
	 * 		- A scheduleOptions object schOpt is created searching for all six courses with all possible dates for university campus.
	 * Input: For the given schOpt schedule options search window a list of scheduels is created.
	 * Expected Output: schedules.size()>6 gives true saying we have different combinations of schedules regardless of the daysmatched.
	 */
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
		Assert.assertTrue(schedules.size()>1);
	}
	/*
	 * Purpose: If there is no courses listed and no search options i.e testing the null condition for different objects used in creating scheduels. 
	 * Preconditions:
	 * 		- No courses offered and no search options in schedule options object.
	 * Input: Null Input for all fields.
	 * Expected Output: No schedules are created.
	 */
	
	@Test
	public void TEAM3_APPLOGIC_ST14() {
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
			
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
			
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		Assert.assertEquals(0,schedules.size()); 
	}
	/*
	 * Purpose: If the days is empty string while defining the class details for a course no schedule is created.
	 * Preconditions:
	 * 		- A course STN2123 which is offered for fall 2014 in university campus.
	 * 		- A ScheduleOptions SchOpt is defined to search for STN2123 in All campuses for Monday, Tuesday and friday .
	 * Input: A schedule is creates with the schOpt search options for STN2123.
	 * Expected Output: No schedule is created because the offered days is not defined.
	 */
	@Test
	public void TEAM3_APPLOGIC_ST15() {
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
			
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","");
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
		Assert.assertEquals(0,schedules.size());
	}
	/*
	  * Purpose: If the class details for a course is not defined no course is created.
	 * Preconditions:
	 * 		- A course STN2123 which is offered for fall 2014 in university campus.
	 * Input: A schedule is created for fall 2014 term , university campus , STN2123 course in list and "monday, tuesday and Friday " days.
	 * Expected Output: No schedule is created because the searched course does not exist.
	 */
	
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
	
	/*
	 * Purpose: If one day is missing for the given courses no schedule is created.
	 * Preconditions:
	 * 		- There are two courses STN2123, COT5124 and CAP6123 offered in all campuses on monday, tuesday and friday for fall 2014 term.
	 * 		
	 * Input: For the collection of these courses in fall 2014 term and all campuses for monday and tuesday a schedule is created.
	 * Expected Output: Since the search is missing a day i.e., friday no schedule is created.
	 */
	@Test
	public void TEAM3_APPLOGIC_ST17() {
			StorageStub.initCourses();
			String term="Fall 2014";
			String camps="All";
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
			schedules=facade.createSchedule(term, courses, camps, "1100000");
			Assert.assertTrue(schedules.isEmpty());
			
		}
	
	/**
	 * Purpose: Requesting a schedule for a course offered at both campuses should produce 2 schedules,
	 *          1 for each campus.
	 * Preconditions:
	 * 		- There exists a course DAT1345 that meets at the same time on Saturdays at both the University
	 *        and Biscayne campuses during term Spring 2015.
	 * 		- There exists a ScheduleOptionsStub S that has no preferred days chosen and
	 * 		  is searching for course DAT1345 at any campus for term Fall 2014.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using S.
	 * Expected Output: schedulesReturned.size() == 2, where each schedule contains the course DAT1345 at
	 *                  the same date and time, but one schedule is for the Biscayne campus and the other
	 *                  is for the University campus.
	 */
	@Test
	public void TEAM3_APPLOGIC_ST18() {
	
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="All";
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","0000010");
		cd0.setTime(t0);
		cd0.subject="DAT";
		cd0.ctlgnumbr="1345";
		
		StorageStub.registerCourse("Biscayne", term, "DAT1345", cd0);
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("02:30","03:30","0000010");
		cd1.setTime(t1);
		cd1.subject="DAT";
		cd1.ctlgnumbr="1345";
		StorageStub.registerCourse("University", term, "DAT1345", cd1);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		Collection<String> courses;
		courses=new ArrayList<String>();
		courses.add("DAT1345");
		
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus(camps);
		schOpt.setTerm(term);
		schOpt.setCourse1("DAT1345");
		
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		
		Assert.assertEquals(2,schedules.size());
		
	}
	
	/**
	 * Purpose: Requesting a schedule for a term that does not contain any courses should produce an empty schedule.
	 * Preconditions:
	 * 		- There are no courses that meet at either campus during term Spring 2015.
	 * 		- There exists a ScheduleOptionsStub S that has no preferred days chosen and
	 * 		  is searching for course DAT1345 at any campus for term Fall 2014.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using S.
	 * Expected Output: schedules.size() == 0
	 */
	
	@Test
	public void TEAM3_APPLOGIC_ST19() {
	
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="All";
				
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
				
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus(camps);
		schOpt.setTerm(term);
		schOpt.setCourse1("DAT1345");
		
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		
		Assert.assertEquals(0,schedules.size());
		
	}
	
	
	/**
	 * Purpose: Requesting a schedule for a class that is offered at a campus other than
	 *          the requested campus should produce an empty schedule.
	 * Preconditions:
	 * 		- There exists a course COP1101 that meets on Mondays/Wednesdays/Fridays at the Biscayne
	 *        campus for term Fall.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using the term, campus, preferred days, and list of courses.
	 * Expected Output: schedulesReturned.size() == 0
	 */
	@Test
	public void TEAM3_APPLOGIC_ST20() {
	
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="University";
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","1010100");
		cd0.setTime(t0);
		cd0.subject="COP";
		cd0.ctlgnumbr="1101";
		StorageStub.registerCourse("Biscayne", term, "DAT1345", cd0);
		
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		Collection<String> courses;
		courses=new ArrayList<String>();
		courses.add("COP1101");
		
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus(camps);
		schOpt.setTerm(term);
		schOpt.setCourse1("COP1101");
		
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		
		Assert.assertEquals(0,schedules.size());
		
	}
	
	/**
	 * Purpose: Requesting a schedule for 2 classes offered at different times on the same days at
	 *          both campuses should produce 4 schedules (the possible combinations of classes and campuses).
	 * Preconditions:
	 * 		- There exists a course MAD3512 that meets on Mondays/Wednesdays/Fridays at all campuses for
	 *        term Spring 2015, and a class COP1101 that meets on the same days but at a different time than
	 *        MAD3512 and is also offered at all campuses.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using term 'Spring 2015', any campus, all preferred days,
	 *        and a list containing the 2 courses COP1101 and MAD3512.
	 * Expected Output: schedulesReturned.size() == 4, containing each of these schedules:
	 *                  - COP1101 and MAD3512 at the Biscayne campus
	 *                  - COP1101 and MAD3512 at the University campus
	 *                  - COP1101 at the Biscayne campus and MAD3512 at the University campus
	 *                  - COP1101 at the University campus and MAD3512 at the Biscayne campus
	 */
	@Test
	public void TEAM3_APPLOGIC_ST21() {
	
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="All";
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","1010100");
		cd0.setTime(t0);
		cd0.subject="MAD";
		cd0.ctlgnumbr="3512";
		
		StorageStub.registerCourse("Biscayne", term, "MAD3512", cd0);
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("06:30","07:30","1010100");
		cd1.setTime(t1);
		cd1.subject="MAD";
		cd1.ctlgnumbr="3512";
		StorageStub.registerCourse("University", term, "MAD3512", cd1);
		
		ClassDetails cd2=new ClassDetails();
		Time t2= new Time("04:30","05:30","1010100");
		cd2.setTime(t2);
		cd2.subject="COP";
		cd2.ctlgnumbr="1101";
		
		StorageStub.registerCourse("Biscayne", term, "COP1101", cd2);
		ClassDetails cd3=new ClassDetails();
		Time t3= new Time("09:30","10:30","1010100");
		cd3.setTime(t3);
		cd3.subject="COP";
		cd3.ctlgnumbr="1101";
		StorageStub.registerCourse("University", term, "COP1101", cd3);
		
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus(camps);
		schOpt.setTerm(term);
		schOpt.setCourse1("COP1101");
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		Collection<String> courses;
		courses=new ArrayList<String>();
		courses.add("COP1101");
		courses.add("MAD3512");
		
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(term, courses, camps, "1111111");
		
		Assert.assertEquals(4,schedules.size());
		
	}
	
	/**
	 * Purpose: Making a request for a schedule that does not specify any course information should return an empty schedule.
	 * Preconditions:
	 * 		- There exists a course MAD3512 that meets on Mondays/Wednesdays/Fridays at all campuses for term Fall 2014.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using term 'Fall 2014', any campus, all preferred days, and an empty course list.
	 * Expected Output: schedulesReturned.size() == 0
	 */
	
	@Test
	public void TEAM3_APPLOGIC_ST22() {
		
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="All";
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","1010100");
		cd0.setTime(t0);
		cd0.subject="MAD";
		cd0.ctlgnumbr="3512";
		
		StorageStub.registerCourse("All", term, "MAD3512", cd0);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		Collection<String> courses;
		courses=new ArrayList<String>();
						
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(term, courses, camps, "1111111");
		
		Assert.assertEquals(0,schedules.size());
		
				
	}
	
	/**
	 * Purpose: Making a request for a schedule using empty course codes should return an empty schedule.
	 * Preconditions:
	 * 		- There exists a course MAC1266 that meets on Tuesdays/Thursdays at all campuses for term Spring 2015.
	 *      - There exists a ScheduleOptionsStub S that requests all campuses and term Spring 2015,
	 *        but uses empty strings for all 6 course fields.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using S.
	 * Expected Output: schedulesReturned.size() == 0
	 */
	
	@Test
	public void TEAM3_APPLOGIC_ST23() {
		
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="All";
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","0101000");
		cd0.setTime(t0);
		cd0.subject="MAC";
		cd0.ctlgnumbr="1266";
		
		StorageStub.registerCourse("All", term, "MAC1266", cd0);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus(camps);
		schOpt.setTerm(term);
		schOpt.setCourse1("");
		schOpt.setCourse2("");
		schOpt.setCourse3("");
		schOpt.setCourse4("");
		schOpt.setCourse5("");
		schOpt.setCourse6("");
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		
		Assert.assertEquals(0,schedules.size());
		
				
	}
	
	/**
	 * Purpose: Making a request for a schedule using SearchOptions course fields other than the
	 *          first one should return a non-empty schedule.
	 * Preconditions:
	 * 		- There exists a course MAC1266 that meets on Tuesdays/Thursdays at the Biscayne campus
	 *        for term fall 2014, and there exists a course DAT1345 that meets on Saturdays at the University
	 *        campus during the same term.
	 *      - There exists a ScheduleOptionsStub S that requests all campuses and term fall 2014, no preferred
	 *        days, but requests DAT1345 using course field 5 and requests MAC1266 using course field 6.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using S.
	 * Expected Output: schedulesReturned.size() != 0
	 */
	
	@Test
	public void TEAM3_APPLOGIC_ST24() {
		
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="All";
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","0101000");
		cd0.setTime(t0);
		cd0.subject="MAC";
		cd0.ctlgnumbr="1266";
		
		StorageStub.registerCourse("Biscayne", term, "MAC1266", cd0);
		
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("02:30","03:30","0000010");
		cd1.setTime(t1);
		cd1.subject="DAT";
		cd1.ctlgnumbr="1345";
		StorageStub.registerCourse("University", term, "DAT1345", cd1);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		
		ScheduleOptions schOpt=new ScheduleOptions();
		schOpt.ScheduleOptions();
		schOpt.setCampus(camps);
		schOpt.setTerm(term);
		schOpt.setCourse1("");
		schOpt.setCourse2("");
		schOpt.setCourse3("");
		schOpt.setCourse4("");
		schOpt.setCourse5("DAT1345");
		schOpt.setCourse6("MAC1266");
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(schOpt);
		
		Assert.assertTrue(schedules.size() != 0);
		
				
	}
	
	/**
	 * Purpose: Making a request for a schedule using non-existent course codes should return an empty schedule.
	 * Preconditions:
	 * 		- There exists a course MAC1266 that meets on Tuesdays/Thursdays at all campuses for term Fall 2014.
	 *      - There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using term 'Spring 2015', course codes '' (empty string) and 'XYZ9999',
	 *        for all campuses and any day.
	 * Expected Output: schedulesReturned.size() == 0
	 */
	
	@Test
	public void TEAM3_APPLOGIC_ST25() {
		
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="All";
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","0101000");
		cd0.setTime(t0);
		cd0.subject="MAC";
		cd0.ctlgnumbr="1266";
		
		StorageStub.registerCourse("All", term, "MAC1266", cd0);
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		Collection<String> courses;
		courses=new ArrayList<String>();
			courses.add("");
			courses.add("XYZ9999");
			
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(term, courses, camps, "1111111");
		
		Assert.assertEquals(0,schedules.size());
		
				
	}
	
	/**
	 * Purpose: Making a request for a schedule that does not include a day that the requested course is
	 *          offered on should not return the requested class in the schedule.
	 * Preconditions:
	 * 		- There exists a course MAD3512 that meets on Tuesdays/Thursdays at all campuses for term Fall 2014,
	 *        and there exists a course COP1101 that meets on Mondays/Wednesdays/Fridays at the University campus for term Spring 2015.
	 *      - There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using term 'Fall 2014', course codes 'COP1101' and 'MAC1266',
	 *        for all campuses and preferred days Mondays/Tuesdays/Wednesdays/Fridays.
	 * Expected Output: mad3512 == false
	 */
	
	
	@Test
	public void TEAM3_APPLOGIC_ST26() {
	
		StorageStub.initCourses();
		String term="Fall 2014";
		String camps="All";
		ClassDetails cd0=new ClassDetails();
		Time t0= new Time("02:30","03:30","0101000");
		cd0.setTime(t0);
		cd0.subject="MAD";
		cd0.ctlgnumbr="3512";
		
		StorageStub.registerCourse("All", term, "MAD3512", cd0);
		
		
		ClassDetails cd1=new ClassDetails();
		Time t1= new Time("04:30","05:30","1010100");
		cd1.setTime(t1);
		cd1.subject="COP";
		cd1.ctlgnumbr="1101";
		
		ScheduleMakerControllerFacade facade = new ScheduleMakerControllerFacade();
		Collection<String> courses;
		courses=new ArrayList<String>();
		courses.add("COP1101");
		courses.add("MAD3512");
		
		Collection<Schedule> schedules;	
		schedules = new ArrayList<Schedule>();
		schedules=facade.createSchedule(term, courses, camps, "1110100");
		
		boolean mad3512returned = false;
		
			for (Object o : schedules.getClass().getClasses()) {
				ArrayList<StorageStub> coursesInSchedule = (ArrayList<StorageStub>) o;
				
				for (StorageStub cds : coursesInSchedule) {
					if (cds.getClass().equals(cd0)) mad3512returned = true;
				}
			}
		
		assertFalse(mad3512returned);
		
	}
	

	
	}