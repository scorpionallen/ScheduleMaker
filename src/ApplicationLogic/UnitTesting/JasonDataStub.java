package ApplicationLogic.UnitTesting;

import ApplicationLogic.ScheduleOptions;

public class JasonDataStub extends ScheduleOptions {
	private JasonDataStub() { }
	
	public static ScheduleOptions getScheduleOptions_Test1() {
		ScheduleOptions ops = new JasonDataStub();
		ops.setCampus("Biscayne");
		ops.setCourse1("COP2210");
		ops.setTerm("Spring 2007");
		ops.setSu("0");
		ops.setM("1");
		ops.setT("0");
		ops.setW("1");
		ops.setTh("0");
		ops.setF("0");
		ops.setS("0");
		
		return ops;
	}
}