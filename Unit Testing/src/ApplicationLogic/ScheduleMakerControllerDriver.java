package ApplicationLogic;

import static org.junit.Assert.*;
import static Storage.ClassDetailsStub.TEST_CONFIG;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;

import org.junit.Test;

import Storage.ClassDetailsStub;
import Storage.CourseStub;
import Storage.ScheduleStub;

public class ScheduleMakerControllerDriver {
	/**
	 * Purpose: A schedule should conflict with itself.
	 * Preconditions:
	 * 		- There exists a single course C that meets on Mondays/Wednesdays/Fridays.
	 * 		- There exists a schedule S, containing course C.
	 * 		- There exists a ScheduleMakerController object SMC.
	 * Input: A Collection that contains 2 references to schedule S.
	 * Expected Output: schedulesConflict == true
	 */
	@Test
	public void TEAM3_CONTROLLER_UT01() {
		// PRECONDITIONS
		ClassDetailsStub c = new ClassDetailsStub(TEST_CONFIG.MonWedFri1A);
		
		ArrayList<ClassDetailsStub> s = new ArrayList<>();
		s.add(c);
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ArrayList<ClassDetailsStub>> sameSchedules = new ArrayList<>();
		sameSchedules.add(s);
		sameSchedules.add(s);
		
		// EXPECTED OUTPUT
		boolean schedulesConflict = smc.conflict(sameSchedules);
		assertTrue(schedulesConflict);
	}
	
	/**
	 * Purpose: A schedule containing a single course should have no conflict.
	 * Preconditions:
	 * 		- There exists a single course C that meets only on Saturdays.
	 * 		- There exists a schedule S, containing course C.
	 * 		- There exists a ScheduleMakerController object SMC.
	 * Input: A Collection containing the single schedule S.
	 * Expected Output: schedulesConflict == false
	 */
	@Test
	public void TEAM3_CONTROLLER_UT02() {
		// PRECONDITIONS
		ClassDetailsStub c = new ClassDetailsStub(TEST_CONFIG.SatOnly);
		
		ArrayList<ClassDetailsStub> s = new ArrayList<>();
		s.add(c);
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ArrayList<ClassDetailsStub>> oneSchedule = new ArrayList<>();
		oneSchedule.add(s);
		
		// EXPECTED OUTPUT
		boolean schedulesConflict = smc.conflict(oneSchedule);
		assertFalse(schedulesConflict);
	}
	
	/**
	 * Purpose: Two different schedules (containing 2 courses each) that share a common course should have a conflict.
	 * Preconditions:
	 * 		- There exist 3 courses C1, C2 and C3; course C1 meets on Saturdays, course C2 meets on Mondays/Wednesdays/Fridays,
	 * 		  and course C3 meets on Tuesdays/Thursdays.
	 * 		- There exist 2 schedules S1 and S2, where schedule S1 contains courses C1 and C3 and schedule S2 contains courses C2 and C3.
	 * 		- There exists a ScheduleMakerController object SMC.
	 * Input: A Collection containing schedules S1 and S2.
	 * Expected Output: schedulesConflict == true
	 */
	@Test
	public void TEAM3_CONTROLLER_UT03() {
		// PRECONDITIONS
		ClassDetailsStub c1 = new ClassDetailsStub(TEST_CONFIG.SatOnly),
						 c2 = new ClassDetailsStub(TEST_CONFIG.MonWedFri2),
						 c3 = new ClassDetailsStub(TEST_CONFIG.TueThu);
		
		ArrayList<ClassDetailsStub> s1 = new ArrayList<>();
		s1.add(c1);
		s1.add(c3);
		
		ArrayList<ClassDetailsStub> s2 = new ArrayList<>();
		s2.add(c2);
		s2.add(c3);
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ArrayList<ClassDetailsStub>> twoSchedules = new ArrayList<>();
		twoSchedules.add(s1);
		twoSchedules.add(s2);
		
		// EXPECTED OUTPUT
		boolean schedulesConflict = smc.conflict(twoSchedules);
		assertTrue(schedulesConflict);
	}
	
	/**
	 * Purpose: Two schedules (that contain distinct courses offered at different times) using an implementation of
	 *          java.util.Collection (other than ArrayList) should not have a conflict, and no ClassCastException should be thrown.
	 * Preconditions:
	 * 		- There exist 2 courses C1 and C2; course C1 meets on Saturdays and course C2 meets on Mondays/Wednesdays/Fridays.
	 * 		- There exist 2 schedules S1 and S2, where schedule S1 contains course C1 and schedule S2 contains course C2.
	 * 		- There exists a ScheduleMakerController object SMC.
	 * Input: A Collection containing schedules S1 and S2 (the runtime type of the Collection is NOT ArrayList).
	 * Expected Output: schedulesConflict == false and exceptionThrown == null
	 */
	@Test
	public void TEAM3_CONTROLLER_UT04() {
		// PRECONDITIONS
		ClassDetailsStub c1 = new ClassDetailsStub(TEST_CONFIG.SatOnly),
						 c2 = new ClassDetailsStub(TEST_CONFIG.MonWedFri2);
		
		ArrayList<ClassDetailsStub> s1 = new ArrayList<>();
		s1.add(c1);
		
		ArrayList<ClassDetailsStub> s2 = new ArrayList<>();
		s2.add(c2);
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ArrayList<ClassDetailsStub>> twoSchedules = new HashSet<>();
		twoSchedules.add(s1);
		twoSchedules.add(s2);
		
		// EXPECTED OUTPUT
		Exception exceptionThrown = null;
		boolean schedulesConflict = false;
		try {
			schedulesConflict = smc.conflict(twoSchedules);
		} catch (ClassCastException cce) {
			exceptionThrown = cce;
		}
		
		assertNull(exceptionThrown);
		assertFalse(schedulesConflict);
	}
	
	/**
	 * Purpose: The balance of an empty schedule should be 0 (zero).
	 * Preconditions: There exists a ScheduleMakerController SMC that has no previously saved schedules.
	 * Input: An empty Collection (indicating no schedules) is saved.
	 * Expected Output: balance == 0.0 and exceptionThrown == null
	 */
	@Test
	public void TEAM3_CONTROLLER_UT05() {
		// PRECONDITIONS
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> savedSchedules = new ArrayList<>();
		smc.saveSchedules(savedSchedules);
		
		// EXPECTED OUTPUT
		Exception exceptionThrown = null;
		double balance = 0.0d;
		try {
			Method getBalance = smc.getClass().getDeclaredMethod("getBalance", (Class<?> []) null);
			Object balanceObj = getBalance.invoke(smc, (Object[]) null);
			
			if (balanceObj == null) throw new NullPointerException("getBalance() returned null; expected a number");
			else if (!(balanceObj instanceof Number)) throw new RuntimeException("getBalance() did not return a number");
		} catch (Exception ex) {
			exceptionThrown = ex;
		}
		
		assertNull(exceptionThrown);
		assertEquals(0.0d, balance, 0.0d);
	}
	
	/**
	 * Purpose: The balance of a non-empty schedule should be greater than 0 (zero).
	 * Preconditions:
	 * 		- There exists a course C that meets on Tuesdays/Thursdays.
	 * 		- There exists a schedule S that contains course C.
	 * 		- There exists a ScheduleMakerController SMC that has no previously saved schedules.
	 * Input: A Collection containing schedule S is saved.
	 * Expected Output: balance > 0.0 and exceptionThrown == null
	 */
	@Test
	public void TEAM3_CONTROLLER_UT06() {
		// PRECONDITIONS
		ClassDetailsStub c = new ClassDetailsStub(TEST_CONFIG.TueThu);
		
		Collection<ClassDetailsStub> s = new ArrayList<>();
		s.add(c);
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> savedSchedules = new ArrayList<>();
		savedSchedules.add(new ScheduleStub(s));
		
		// EXPECTED OUTPUT
		Exception exceptionThrown = null;
		boolean balanceGreaterThanZero = false;
		try {
			Object balanceObj = null;
			Method getBalance = smc.getClass().getDeclaredMethod("getBalance", (Class<?> []) null);
			
			smc.saveSchedules(savedSchedules);
			balanceObj = getBalance.invoke(smc, (Object[]) null);
			
			if (balanceObj == null) throw new NullPointerException("getBalance() returned null; expected a number");
			else if (!(balanceObj instanceof Number)) throw new RuntimeException("getBalance() did not return a number");
			
			balanceGreaterThanZero = ((Number) balanceObj).doubleValue() > 0.0d;
		} catch (Exception ex) {
			exceptionThrown = ex;
		}
		
		assertNull(exceptionThrown);
		assertTrue(balanceGreaterThanZero);
	}
	
	/**
	 * Purpose: Test the sizeIsOne() method for a Collection that has no elements.
	 * Preconditions: There exists a ScheduleMakerController SMC.
	 * Input: An empty Collection.
	 * Expected Output: sizeIsOne == false
	 */
	@Test
	public void TEAM3_CONTROLLER_UT07() {
		// PRECONDITIONS
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<Collection<?>> emptyCollection = new ArrayList<>();
		
		// EXPECTED OUTPUT
		boolean sizeIsOne = smc.sizeIsOne(emptyCollection);
		assertFalse(sizeIsOne);
	}
	
	/**
	 * Purpose: Test the sizeIsOne() method for a Collection that has a single element of size 1.
	 * Preconditions:
	 * 		- There exists an ArrayList A that contains the integer 100.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: A Collection that contains A.
	 * Expected Output: sizeIsOne == true
	 */
	@Test
	public void TEAM3_CONTROLLER_UT08() {
		// PRECONDITIONS
		ArrayList<Integer> a = new ArrayList<>();
		a.add(100);
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<Collection<?>> singleElementCollection = new HashSet<>();
		singleElementCollection.add(a);
		
		// EXPECTED OUTPUT
		boolean sizeIsOne = smc.sizeIsOne(singleElementCollection);
		assertTrue(sizeIsOne);
	}
	
	/**
	 * Purpose: Test the sizeIsOne() method for a Collection that has 2 elements, each of size 2.
	 * Preconditions:
	 * 		- There exists an ArrayList A1 (that contains the integers 100 and 200)
	 * 		  and an ArrayList A2 (that contains the integers 150 and 250).
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: A Collection that contains A1 and A2.
	 * Expected Output: sizeIsOne == false
	 */
	@Test
	public void TEAM3_CONTROLLER_UT09() {
		// PRECONDITIONS
		ArrayList<Integer> a1 = new ArrayList<>();
		a1.add(100);
		a1.add(200);
		
		ArrayList<Integer> a2 = new ArrayList<>();
		a2.add(150);
		a2.add(250);
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<Collection<?>> doubleElementCollection = new Vector<>();
		doubleElementCollection.add(a1);
		doubleElementCollection.add(a2);
		
		// EXPECTED OUTPUT
		boolean sizeIsOne = smc.sizeIsOne(doubleElementCollection);
		assertFalse(sizeIsOne);
	}
	
	/**
	 * Purpose: Requesting a schedule using no class search criteria should produce an empty schedule.
	 * Preconditions:
	 * 		- There exists a course DAT1345 that meets on Saturdays at the Biscayne campus during Spring 2014.
	 * 		- There exists a ScheduleOptionsStub S that has no preferred days and no courses chosen
	 * 		  but is searching for courses at Biscayne during Spring 2014.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using S.
	 * Expected Output: schedulesReturned.size() == 0
	 */
	@Test
	public void TEAM3_CONTROLLER_UT10() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "Biscayne";
		String term = "Spring 2014";
		
		ClassDetailsStub dat1345 = new ClassDetailsStub(TEST_CONFIG.SatOnly);
		
		CourseStub.registerCourse(campus, term, dat1345);
		
		ScheduleOptionsStub s = new ScheduleOptionsStub();
		s.setM("0");
		s.setT("0");
		s.setW("0");
		s.setTh("0");
		s.setF("0");
		s.setS("0");
		s.setSu("0");
		s.setCampus(campus);
		s.setTerm(term);
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> schedulesReturned = smc.createSchedule(s);
		
		// EXPECTED OUTPUT
		assertEquals(0, schedulesReturned.size());
	}
	
	/**
	 * Purpose: Requesting a schedule for 2 courses offered on specific days where only 1 course is available
	 * 			should produce a schedule that only contains the course offered on the requested days.
	 * Preconditions:
	 * 		- There exists a course CEN4012 that meets on Mondays/Wednesdays/Fridays and a course MAC1266
	 * 		  that meets on Tuesdays/Thursdays; both courses meet at the University campus during term Fall 2014.
	 * 		- There exists a ScheduleOptionsStub S that has preferred days Mondays/Wednesdays/Fridays chosen and
	 * 		  is searching for courses CEN4012 and MAC1266 at the University campus for term Fall 2014.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using S.
	 * Expected Output: schedulesReturned.size() == 1 and schedulesReturned contains only CEN4012
	 */
	@Test
	public void TEAM3_CONTROLLER_UT11() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "University";
		String term = "Fall 2014";
		
		ClassDetailsStub cen4012 = new ClassDetailsStub(TEST_CONFIG.MonWedFri1A);
		ClassDetailsStub mac1266 = new ClassDetailsStub(TEST_CONFIG.TueThu);
		
		CourseStub.registerCourse(campus, term, cen4012);
		CourseStub.registerCourse(campus, term, mac1266);
		
		ScheduleOptionsStub s = new ScheduleOptionsStub();
		s.setM("1");
		s.setT("0");
		s.setW("1");
		s.setTh("0");
		s.setF("1");
		s.setS("0");
		s.setSu("0");
		s.setCampus(campus);
		s.setTerm(term);
		s.setCourse1(cen4012.getCourse());
		s.setCourse2(mac1266.getCourse());
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> schedulesReturned = smc.createSchedule(s);
		
		// EXPECTED OUTPUT
		assertEquals(1, schedulesReturned.size());
		for (ScheduleStub ss : schedulesReturned) {
			assertEquals(ss.getClasses().size(), 1);
			Iterator<ClassDetailsStub> iterator = ss.getClasses().iterator();
			while (iterator.hasNext()) {
				assertTrue(iterator.next().equals(cen4012));
			}
		}
	}
	
	/**
	 * Purpose: Requesting a schedule for a course offered at both campuses should produce 2 schedules,
	 *          1 for each campus.
	 * Preconditions:
	 * 		- There exists a course DAT1345 that meets at the same time on Saturdays at both the University
	 *        and Biscayne campuses during term Spring 2015.
	 * 		- There exists a ScheduleOptionsStub S that has no preferred days chosen and
	 * 		  is searching for course DAT1345 at any campus for term Spring 2015.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using S.
	 * Expected Output: schedulesReturned.size() == 2, where each schedule contains the course DAT1345 at
	 *                  the same date and time, but one schedule is for the Biscayne campus and the other
	 *                  is for the University campus.
	 */
	@Test
	public void TEAM3_CONTROLLER_UT12() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "All";
		String term = "Spring 2015";
		
		ClassDetailsStub dat1345 = new ClassDetailsStub(TEST_CONFIG.SatOnly);
		
		CourseStub.registerCourse("Biscayne", term, dat1345);
		CourseStub.registerCourse("University", term, dat1345);
		
		ScheduleOptionsStub s = new ScheduleOptionsStub();
		s.setCampus(campus);
		s.setTerm(term);
		s.setCourse3(dat1345.getCourse());
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> schedulesReturned = smc.createSchedule(s);
		
		// EXPECTED OUTPUT
		assertEquals(2, schedulesReturned.size());
		
		boolean biscayneFound = false,
				universityFound = false;
		for (ScheduleStub ss : schedulesReturned) {
			assertEquals(1, ss.getClasses().size());
			
			ArrayList<ClassDetailsStub> courses = (ArrayList<ClassDetailsStub>)((ArrayList)ss.getClasses()).get(0);
			assertEquals(1, courses.size());
			
			ClassDetailsStub course = courses.get(0); 
			assertEquals(course.getCourse(), dat1345.getCourse());
			
			if (course.campus.equals("Biscayne")) biscayneFound = true;
			if (course.campus.equals("University")) universityFound = true;
		}
		assertTrue(biscayneFound);
		assertTrue(universityFound);
	}
	
	/**
	 * Purpose: Requesting a schedule for a term that does not contain any courses should produce an empty schedule.
	 * Preconditions:
	 * 		- There are no courses that meet at either campus during term Spring 2015.
	 * 		- There exists a ScheduleOptionsStub S that has no preferred days chosen and
	 * 		  is searching for course DAT1345 at any campus for term Spring 2015.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using S.
	 * Expected Output: schedulesReturned.size() == 0
	 */
	@Test
	public void TEAM3_CONTROLLER_UT13() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "All";
		String term = "Spring 2015";
		
		ClassDetailsStub dat1345 = new ClassDetailsStub(TEST_CONFIG.SatOnly);
		
		ScheduleOptionsStub s = new ScheduleOptionsStub();
		s.setCampus(campus);
		s.setTerm(term);
		s.setCourse4(dat1345.getCourse());
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> schedulesReturned = smc.createSchedule(s);
		
		// EXPECTED OUTPUT
		assertEquals(0, schedulesReturned.size());
	}
	
	/**
	 * Purpose: Requesting a schedule for a class that is offered at a campus other than
	 *          the requested campus should produce an empty schedule.
	 * Preconditions:
	 * 		- There exists a course COP1101 that meets on Mondays/Wednesdays/Fridays at the Biscayne
	 *        campus for term Spring 2015.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using the term, campus, preferred days, and list of courses.
	 * Expected Output: schedulesReturned.size() == 0
	 */
	@Test
	public void TEAM3_CONTROLLER_UT14() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "University";
		String preferredDays = "1010100";
		String term = "Spring 2015";
		
		ClassDetailsStub cop1101 = new ClassDetailsStub(TEST_CONFIG.MonWedFri1B);
		CourseStub.registerCourse("Biscayne", term, cop1101);
		
		ArrayList<String> courses = new ArrayList<>();
		courses.add(cop1101.getCourse());
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> schedulesReturned = smc.createSchedule(term, courses, campus, preferredDays);
		
		// EXPECTED OUTPUT
		assertTrue(schedulesReturned.isEmpty());
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
	public void TEAM3_CONTROLLER_UT15() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "All";
		String preferredDays = "1111111";
		String term = "Spring 2015";
		
		ClassDetailsStub cop1101 = new ClassDetailsStub(TEST_CONFIG.MonWedFri1B);
		ClassDetailsStub mad3512 = new ClassDetailsStub(TEST_CONFIG.MonWedFri2);
		CourseStub.registerCourse("All", term, cop1101);
		CourseStub.registerCourse("All", term, mad3512);
		
		ArrayList<String> courses = new ArrayList<>();
		courses.add(cop1101.getCourse());
		courses.add(mad3512.getCourse());
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		ArrayList<ScheduleStub> schedulesReturned = (ArrayList<ScheduleStub>) smc.createSchedule(term, courses, campus, preferredDays);
		
		// EXPECTED OUTPUT
		assertEquals(4, schedulesReturned.size());
		
		boolean classCombo1found = false,
				classCombo2found = false,
				classCombo3found = false,
				classCombo4found = false;
		for (ScheduleStub schedule : schedulesReturned) {
			boolean cop1101found = false,
					mad3512found = false;
			String cop1101campus = "",
				   mad3512campus = "";
			
			assertEquals(2, schedule.getClasses().size());
			
			for (Object o : schedule.getClasses()) {
				ClassDetailsStub cds = ((ArrayList<ClassDetailsStub>) o).get(0);
				if (cds.getCourse().equals(cop1101.getCourse())) {
					cop1101found = true;
					cop1101campus = cds.campus;
				}
				if (cds.getCourse().equals(mad3512.getCourse())) {
					mad3512found = true;
					mad3512campus = cds.campus;
				}
			}
			
			assertTrue(cop1101found);
			assertTrue(mad3512found);
			
			if (cop1101campus.equals("Biscayne") && mad3512campus.equals("Biscayne")) classCombo1found = true;
			if (cop1101campus.equals("University") && mad3512campus.equals("University")) classCombo2found = true;
			if (cop1101campus.equals("University") && mad3512campus.equals("Biscayne")) classCombo3found = true;
			if (cop1101campus.equals("Biscayne") && mad3512campus.equals("University")) classCombo4found = true;
		}
		assertTrue(classCombo1found);
		assertTrue(classCombo2found);
		assertTrue(classCombo3found);
		assertTrue(classCombo4found);
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
	public void TEAM3_CONTROLLER_UT16() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "All";
		String preferredDays = "1111111";
		String term = "Fall 2014";
		
		ClassDetailsStub mad3512 = new ClassDetailsStub(TEST_CONFIG.MonWedFri2);
		CourseStub.registerCourse("All", term, mad3512);
		
		ArrayList<String> courses = new ArrayList<>();
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		ArrayList<ScheduleStub> schedulesReturned = (ArrayList<ScheduleStub>) smc.createSchedule(term, courses, campus, preferredDays);
		
		// EXPECTED OUTPUT
		assertEquals(0, schedulesReturned.size());
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
	public void TEAM3_CONTROLLER_UT17() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String term = "Spring 2015";
		
		ClassDetailsStub mac1266 = new ClassDetailsStub(TEST_CONFIG.TueThu);
		CourseStub.registerCourse("All", term, mac1266);
		
		ScheduleOptionsStub s = new ScheduleOptionsStub();
		s.setCampus("All");
		s.setTerm(term);
		s.setCourse1("");
		s.setCourse2("");
		s.setCourse3("");
		s.setCourse4("");
		s.setCourse5("");
		s.setCourse6("");
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> schedulesReturned = smc.createSchedule(s);
		
		// EXPECTED OUTPUT
		assertEquals(0, schedulesReturned.size());
	}
	
	/**
	 * Purpose: Making a request for a schedule using SearchOptions course fields other than the
	 *          first one should return a non-empty schedule.
	 * Preconditions:
	 * 		- There exists a course MAC1266 that meets on Tuesdays/Thursdays at the Biscayne campus
	 *        for term Spring 2015, and there exists a course DAT1345 that meets on Saturdays at the University
	 *        campus during the same term.
	 *      - There exists a ScheduleOptionsStub S that requests all campuses and term Spring 2015, no preferred
	 *        days, but requests DAT1345 using course field 5 and requests MAC1266 using course field 6.
	 * 		- There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using S.
	 * Expected Output: schedulesReturned.size() != 0
	 */
	@Test
	public void TEAM3_CONTROLLER_UT18() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String term = "Spring 2015";
		
		ClassDetailsStub mac1266 = new ClassDetailsStub(TEST_CONFIG.TueThu),
				         dat1345 = new ClassDetailsStub(TEST_CONFIG.SatOnly);
		CourseStub.registerCourse("Biscayne", term, mac1266);
		CourseStub.registerCourse("University", term, dat1345);
		
		ScheduleOptionsStub s = new ScheduleOptionsStub();
		s.setCampus("All");
		s.setTerm(term);
		s.setCourse1("");
		s.setCourse2("");
		s.setCourse3("");
		s.setCourse4("");
		s.setCourse5(dat1345.getCourse());
		s.setCourse6(mac1266.getCourse());
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> schedulesReturned = smc.createSchedule(s);
		
		// EXPECTED OUTPUT
		assertNotEquals(0, schedulesReturned.size());
	}
	
	/**
	 * Purpose: Making a request for a schedule using non-existent course codes should return an empty schedule.
	 * Preconditions:
	 * 		- There exists a course MAC1266 that meets on Tuesdays/Thursdays at all campuses for term Spring 2015.
	 *      - There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using term 'Spring 2015', course codes '' (empty string) and 'XYZ9999',
	 *        for all campuses and any day.
	 * Expected Output: schedulesReturned.size() == 0
	 */
	@Test
	public void TEAM3_CONTROLLER_UT19() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "All",
			   preferredDays = "1111111",
			   term = "Spring 2015";
		
		ClassDetailsStub mac1266 = new ClassDetailsStub(TEST_CONFIG.TueThu);
		CourseStub.registerCourse(campus, term, mac1266);
		
		Collection<String> courses = new ArrayList<>();
		courses.add("");
		courses.add("XYZ9999");
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> schedulesReturned = smc.createSchedule(term, courses, campus, preferredDays);
		
		// EXPECTED OUTPUT
		assertEquals(0, schedulesReturned.size());
	}
	
	/**
	 * Purpose: Making a request for a schedule that does not include a day that the requested course is
	 *          offered on should not return the requested class in the schedule.
	 * Preconditions:
	 * 		- There exists a course MAC1266 that meets on Tuesdays/Thursdays at all campuses for term Spring 2015,
	 *        and there exists a course COP1101 that meets on Mondays/Wednesdays/Fridays at the University campus for term Spring 2015.
	 *      - There exists a ScheduleMakerController SMC.
	 * Input: Request a schedule from SMC using term 'Spring 2015', course codes 'COP1101' and 'MAC1266',
	 *        for all campuses and preferred days Mondays/Tuesdays/Wednesdays/Fridays.
	 * Expected Output: mac1266returned == false
	 */
	@Test
	public void TEAM3_CONTROLLER_UT20() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String requestedCampus = "All",
			   preferredDays = "1110100",
			   term = "Spring 2015";
		
		ClassDetailsStub cop1101 = new ClassDetailsStub(TEST_CONFIG.MonWedFri1B),
						 mac1266 = new ClassDetailsStub(TEST_CONFIG.TueThu);
		CourseStub.registerCourse("University", term, cop1101);
		CourseStub.registerCourse("All", term, mac1266);
		
		Collection<String> courses = new ArrayList<>();
		courses.add(cop1101.getCourse());
		courses.add(mac1266.getCourse());
		
		ScheduleMakerController smc = new ScheduleMakerController();
		
		// INPUT
		Collection<ScheduleStub> schedulesReturned = smc.createSchedule(term, courses, requestedCampus, preferredDays);
		
		// EXPECTED OUTPUT
		boolean mac1266returned = false;
		for (ScheduleStub schedule : schedulesReturned) {
			for (Object o : schedule.getClasses()) {
				ArrayList<ClassDetailsStub> coursesInSchedule = (ArrayList<ClassDetailsStub>) o;
				
				for (ClassDetailsStub cds : coursesInSchedule) {
					if (cds.getCourse().equals(mac1266.getCourse())) mac1266returned = true;
				}
			}
		}
		assertFalse(mac1266returned);
	}
	
	/**
	 * Purpose: Calling findClass() using an existing subject should return the classes matching that subject code.
	 * Preconditions:
	 * 		- There exist two courses CEN4012 and COP1101 that meet on Mondays/Wednesdays/Fridays at the
	 *        University campus during term Summer 2015.
	 *      - There exists a ScheduleMakerController SMC.
	 * Input: Search for classes in the subject 'CEN' using SMC.
	 * Expected Output: classesReturned.size() == 1 and the course is CEN4012; no exceptions are thrown
	 */
	@Test
	public void TEAM3_CONTROLLER_UT21() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "University",
			   subject = "CEN",
			   term = "Summer 2015";
		
		ClassDetailsStub cen4012 = new ClassDetailsStub(TEST_CONFIG.MonWedFri1A),
						 cop1101 = new ClassDetailsStub(TEST_CONFIG.MonWedFri1B);
		CourseStub.registerCourse(campus, term, cen4012);
		CourseStub.registerCourse(campus, term, cop1101);
		
		ScheduleMakerController smc = new ScheduleMakerController();

		// INPUT
		Exception exceptionThrown = null;
		ArrayList<ClassDetailsStub> classesReturned = null;
		try {
			// ideally, the method would accept some parameter(s) to specify what subject(s) to search for...
			Method findClasses = smc.getClass().getDeclaredMethod("findClasses", (Class<?> []) null);
			Object methodReturnObject = findClasses.invoke(smc, (Object []) null);
			
			if (methodReturnObject == null) throw new NullPointerException("findClasses() returned null; expected a Collection");
			else if (!(methodReturnObject instanceof Collection<?>)) throw new RuntimeException("findClasses() did not return a Collection");
			
			classesReturned = new ArrayList<>( ((Collection<ClassDetailsStub>) methodReturnObject) );
		} catch (Exception ex) {
			exceptionThrown = ex;
		}
		
		// EXPECTED OUTPUT
		assertNull(exceptionThrown);
		assertNotNull(classesReturned);
		assertEquals(1, classesReturned.size());
		assertEquals(cen4012.getCourse(), classesReturned.get(0).getCourse());
	}
	
	/**
	 * Purpose: After calling saveSchedule(), the same schedule should be retrieved using getSavedSchedule().
	 * Preconditions:
	 * 		- There exists a course CEN4012 that meets on Mondays/Wednesdays/Fridays at the Biscayne campus during term Spring 2015.
	 *      - There exists a ScheduleStub S that has id 'MySchedule' for student 1412412 and contains CEN4012.
	 *      - There exists a ScheduleMakerController SMC.
	 * Input: S is saved using SMC.saveSchedules(), then ScheduleStub S2 is obtained by calling SMC.getSavedSchedule()
	 * Expected Output: the id of S2 is equal to the id of S; the PantherID of S2 is equal to the PantherID of S;
	 *                  the class list of S and S2 each contain only CEN4012; no exception is thrown
	 */
	@Test
	public void TEAM3_CONTROLLER_UT22() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String campus = "Biscayne",
			   pantherID = "1412412",
			   scheduleID = "MySchedule",
			   term = "Spring 2015";
		
		ClassDetailsStub cen4012 = new ClassDetailsStub(TEST_CONFIG.MonWedFri1A);
		CourseStub.registerCourse(campus, term, cen4012);
		
		ArrayList<ClassDetailsStub> courses = new ArrayList<>();
		courses.add(cen4012);
		
		ScheduleStub s = new ScheduleStub(courses);
		s.setId(scheduleID);
		s.setPantherID(pantherID);
		
		ArrayList<ScheduleStub> scheduleList = new ArrayList<>();
		scheduleList.add(s);
		
		ScheduleMakerController smc = new ScheduleMakerController();

		// INPUT
		Exception exceptionThrown = null;
		try {
			smc.saveSchedules(scheduleList);
			
			Method getSavedSchedule = smc.getClass().getDeclaredMethod("getSavedSchedule", (Class<?> []) null);
			Object methodReturnObject = getSavedSchedule.invoke(smc, (Object []) null);
			
			// EXPECTED OUTPUT
			
			if (methodReturnObject == null) throw new NullPointerException("getSavedSchedule() returned null; expected a Collection");
			else if (!(methodReturnObject instanceof Collection<?>)) throw new RuntimeException("getSavedSchedule() did not return a Collection");
			
			ArrayList<ScheduleStub> schedulesReturned = new ArrayList<>( ((Collection<ScheduleStub>) methodReturnObject) );
			assertEquals(1, schedulesReturned.size());
			
			ScheduleStub s2 = schedulesReturned.get(0);
			assertNotNull(s2);
			assertEquals(s.getId(), s2.getId());
			assertEquals(s.getPantherID(), s2.getPantherID());
			assertEquals(1, s2.getClasses().size());
			assertEquals(cen4012.getCourse(), ((ArrayList<ClassDetailsStub>)s2.getClasses()).get(0).getCourse());
		} catch (Exception ex) {
			exceptionThrown = ex;
		}
		
		assertNull(exceptionThrown);
	}
	
	/**
	 * Purpose: Calling sortSchedules() after giving a list of unsorted schedules should return the same schedules sorted by ID.
	 * Preconditions:
	 * 		- There exists a course CEN4012 that meets on Mondays/Wednesdays/Fridays at the Biscayne campus during term Spring 2015
	 *        and there exists a course MAC1266 that meets Tuesdays/Thursdays at the University campus during term Spring 2015.
	 *      - There exists a ScheduleStub S1 that has id 'Schedule1' for student 1412412 and contains CEN4012 and
	 *        there exists a ScheduleStub S2 that has id 'Schedule2' for the same student, containing MAC1266.
	 *      - There exists a ScheduleMakerController SMC.
	 * Input: S2 and S1 are saved (in that order) using SMC.saveSchedules(), then SMC.sortSchedules() is called.
	 * Expected Output: the returned schedules list contains S1 first and then S2; no exception is thrown
	 */
	@Test
	public void TEAM3_CONTROLLER_UT23() {
		// PRECONDITIONS
		CourseStub.initializeCourses();
		
		String pantherID = "1412412",
			   term = "Spring 2015";
		
		ClassDetailsStub cen4012 = new ClassDetailsStub(TEST_CONFIG.MonWedFri1A),
						 mac1266 = new ClassDetailsStub(TEST_CONFIG.TueThu);
		CourseStub.registerCourse("Biscayne", term, cen4012);
		CourseStub.registerCourse("University", term, mac1266);
		
		ArrayList<ClassDetailsStub> courses1 = new ArrayList<>();
		courses1.add(cen4012);
		
		ArrayList<ClassDetailsStub> courses2 = new ArrayList<>();
		courses1.add(mac1266);
		
		ScheduleStub s1 = new ScheduleStub(courses1);
		s1.setId("Schedule1");
		s1.setPantherID(pantherID);
		
		ScheduleStub s2 = new ScheduleStub(courses2);
		s1.setId("Schedule2");
		s1.setPantherID(pantherID);
		
		ArrayList<ScheduleStub> scheduleList = new ArrayList<>();
		scheduleList.add(s2);
		scheduleList.add(s1);
		
		ScheduleMakerController smc = new ScheduleMakerController();

		// INPUT
		Exception exceptionThrown = null;
		try {
			smc.saveSchedules(scheduleList);
			
			Method sortSchedules = smc.getClass().getDeclaredMethod("sortSchedules", (Class<?> []) null);
			Object methodReturnObject = sortSchedules.invoke(smc, (Object []) null);
			
			// EXPECTED OUTPUT
			
			if (methodReturnObject == null) throw new NullPointerException("sortSchedules() returned null; expected a Collection");
			else if (!(methodReturnObject instanceof Collection<?>)) throw new RuntimeException("sortSchedules() did not return a Collection");
			
			ArrayList<ScheduleStub> schedulesReturned = new ArrayList<>( ((Collection<ScheduleStub>) methodReturnObject) );
			assertEquals(2, schedulesReturned.size());
			
			ScheduleStub returnedSchedule1 = schedulesReturned.get(0),
						 returnedSchedule2 = schedulesReturned.get(1);
			assertNotNull(returnedSchedule1);
			assertNotNull(returnedSchedule2);
			assertEquals(s1.getId(), returnedSchedule1.getId());
			assertEquals(s2.getId(), returnedSchedule2.getId());
			assertEquals(s1.getPantherID(), returnedSchedule1.getPantherID());
			assertEquals(s2.getPantherID(), returnedSchedule2.getPantherID());
			assertEquals(1, returnedSchedule1.getClasses().size());
			assertEquals(1, returnedSchedule2.getClasses().size());
			assertEquals(cen4012.getCourse(), ((ArrayList<ClassDetailsStub>)returnedSchedule1.getClasses()).get(0).getCourse());
			assertEquals(mac1266.getCourse(), ((ArrayList<ClassDetailsStub>)returnedSchedule2.getClasses()).get(0).getCourse());
		} catch (Exception ex) {
			exceptionThrown = ex;
		}
		
		assertNull(exceptionThrown);
	}
}