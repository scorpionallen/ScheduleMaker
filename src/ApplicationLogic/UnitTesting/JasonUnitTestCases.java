package ApplicationLogic.UnitTesting;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import Storage.Schedule;
import ApplicationLogic.ScheduleMakerController;
import ApplicationLogic.ScheduleOptions;

public class JasonUnitTestCases {
	/**
	 * Purpose: Creating a schedule for a single class should return the given class.
	 * Precondition: No other schedules have been requested
	 * Input: Requesting a schedule with a single class, COP 2210 on MWF at 11:00 AM at MMC campus
	 */
	@Test
	public void CreateSchedule_SingleClass() {
		ScheduleOptions ops = JasonDataStub.getScheduleOptions_Test1();
		Assert.assertNotNull(ops);
		
		ScheduleMakerController controller = new ScheduleMakerController();
		Assert.assertNotNull(controller);
		
		Collection<Schedule> scheduleColl = controller.createSchedule(ops);
		Assert.assertNotNull(scheduleColl);
		Assert.assertFalse(scheduleColl.isEmpty());
		Assert.assertEquals(scheduleColl.size(), 1);
	}
}