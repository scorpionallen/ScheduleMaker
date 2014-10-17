package ApplicationLogic;

import static org.junit.Assert.*;
import static Storage.ClassDetailsStub.TEST_CONFIG;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

import Storage.ClassDetailsStub;

public class ScheduleMakerControllerDriver {
	/**
	 * Purpose: A schedule should conflict with itself.
	 * Preconditions: There exists a schedule containing a single course and a ScheduleMakerController object.
	 * Input: A Collection of 2 ArrayLists; each ArrayList has the same ClassDetailsStub object.
	 * Expected Output: hasConflict() == true
	 */
	@Test
	public void TEAM3_CONTROLLER_UT01() {
		ScheduleMakerController smc = new ScheduleMakerController();
		ClassDetailsStub MWFclassSchedule = new ClassDetailsStub(TEST_CONFIG.MonWedFri1A);
		ArrayList<ArrayList<ClassDetailsStub>> sameSchedules = new ArrayList<>();
		ArrayList<ClassDetailsStub> schedule1 = new ArrayList<>(),
				schedule2 = new ArrayList<>();
		
		schedule1.add(MWFclassSchedule);
		schedule2.add(MWFclassSchedule);
		
		sameSchedules.add(schedule1);
		sameSchedules.add(schedule2);
		
		assertTrue(smc.conflict(sameSchedules));
	}
	
	/**
	 * Purpose: A schedule containing a single class should have no conflict.
	 * Preconditions: There exists a schedule containing a single course and a ScheduleMakerController object.
	 * Input: A Collection of 1 ArrayList, containing the single schedule.
	 * Expected Output: hasConflict() == false
	 */
	@Test
	public void TEAM3_CONTROLLER_UT02() {
		ScheduleMakerController smc = new ScheduleMakerController();
		ClassDetailsStub SatClassSchedule = new ClassDetailsStub(TEST_CONFIG.SatOnly);
		ArrayList<ArrayList<ClassDetailsStub>> oneSchedule = new ArrayList<>();
		ArrayList<ClassDetailsStub> schedule1 = new ArrayList<>();
		
		schedule1.add(SatClassSchedule);
		oneSchedule.add(schedule1);
		
		assertFalse(smc.conflict(oneSchedule));
	}
	
	/**
	 * Purpose: Two different schedules (containing 2 classes each) that share a common course should have a conflict.
	 * Preconditions: There exists a ScheduleMakerController object and 2 different schedules A and B, where schedule A
	 * 				  has classes X and Y (X and Y are distinct and do not conflict) and schedule B has classes Z and Y
	 * 				  (Z and Y are distinct and do not conflict).
	 * Input: A Collection of 2 ArrayLists, containing schedules A and B.
	 * Expected Output: hasConflict() == true
	 */
	@Test
	public void TEAM3_CONTROLLER_UT03() {
		ScheduleMakerController smc = new ScheduleMakerController();
		ClassDetailsStub classX = new ClassDetailsStub(TEST_CONFIG.SatOnly),
						 classY = new ClassDetailsStub(TEST_CONFIG.MonWedFri2),
						 classZ = new ClassDetailsStub(TEST_CONFIG.TueThu);
		ArrayList<ArrayList<ClassDetailsStub>> twoSchedules = new ArrayList<>();
		ArrayList<ClassDetailsStub> scheduleA = new ArrayList<>(),
									scheduleB = new ArrayList<>();
		
		scheduleA.add(classX);
		scheduleA.add(classY);
		scheduleB.add(classZ);
		scheduleB.add(classY);
		twoSchedules.add(scheduleA);
		twoSchedules.add(scheduleB);
		
		assertTrue(smc.conflict(twoSchedules));
	}
	
	/**
	 * Purpose: Two schedules (that contain distinct classes offered at different times)
	 *          using an implementation of java.util.Collection (other than ArrayList)
	 *          should not have a conflict, and no ClassCastException should be thrown.
	 * Preconditions: There exists a ScheduleMakerController object and 2 different schedules A and B, where schedule A
	 * 				  has class X and schedule B has class Y (X and Y are distinct and do not conflict).
	 * Input: A Collection of 2 ArrayLists, containing schedules A and B (the runtime type of the Collection is NOT ArrayList).
	 * Expected Output: hasConflict() == false
	 */
	@Test
	public void TEAM3_CONTROLLER_UT04() {
		boolean exceptionThrown = false,
				schedulesConflict = false;
		ScheduleMakerController smc = new ScheduleMakerController();
		ClassDetailsStub classX = new ClassDetailsStub(TEST_CONFIG.SatOnly),
						 classY = new ClassDetailsStub(TEST_CONFIG.MonWedFri2);
		HashSet<ArrayList<ClassDetailsStub>> twoSchedules = new HashSet<>();
		ArrayList<ClassDetailsStub> scheduleA = new ArrayList<>(),
									scheduleB = new ArrayList<>();
		
		scheduleA.add(classX);
		scheduleB.add(classY);
		twoSchedules.add(scheduleA);
		twoSchedules.add(scheduleB);
		
		try {
			schedulesConflict = smc.conflict(twoSchedules);
			exceptionThrown = false;
		} catch (ClassCastException cce) {
			exceptionThrown = true;
		}
		
		assertFalse(exceptionThrown);
		assertFalse(schedulesConflict);
	}
}