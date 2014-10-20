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
}