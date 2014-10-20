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
	 * method: createSchedule()
	 * 
	 * 
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
	 * method: createSchedule()
	 * 
	 * 
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
	
	
	
	}