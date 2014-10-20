package ApplicationLogic;

import static org.junit.Assert.*;
import static Storage.ClassDetailsStub.TEST_CONFIG;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;

import Storage.ClassDetailsStub;
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
	 * Purpose: Two schedules (that contain distinct classes offered at different times) using an implementation of
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
		assertEquals(balance, 0.0d, 0.0d);
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
}