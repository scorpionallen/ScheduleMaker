package Storage;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class ClassDetailsDriver {
	
	/*
	 * TestCase method: ClassDetails();
	 * 
	 * 
	 * 
	 * 
	 */
	//TODO: Unit test methods go here
	@Test
	public void TEAM3_CLASSDETAILS_UT01(){
		String Subject="STN";
		String ctlgnumbr="2123";
		String classnumbr="3";
		CourseStub cs=new CourseStub(Subject,ctlgnumbr);
		ClassDetails cd=new ClassDetails(cs,classnumbr);
	    assertEquals(cs, cd.course);		
	    assertEquals(classnumbr,cd.classNbr);
	}
	
	@Test
	public void TEAM3_CLASSDETAILS_UT02(){
		String Subject="2112";
		String ctlgnumbr="STN";
		String classnumbr="0";
		CourseStub cs=new CourseStub(Subject,ctlgnumbr);
		ClassDetails cd=new ClassDetails(cs,classnumbr);
	    assertEquals(cs, cd.course);		
	    assertEquals(classnumbr,cd.classNbr);
	}
	

	/*
	 *Test Method: setTime(); 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *
	 */
	
	@Test
	public void Team3_CLASSDETAILS_UT03(){
		
		TimeStub ts=new TimeStub();
		ClassDetails cd= new ClassDetails();
		cd.setTime(ts);
		assertEquals(ts, cd.time);
				
		}
	/*
	 *Test Method: setTerm(); 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *
	 */
	@Test
	public void Team3_CLASSDETAILS_UT04(){
		
		String term="FALL";
		ClassDetails cd=new ClassDetails();
		cd.setTerm(term);
		assertEquals(term, cd.term);
						
		}
	/*
	 *Test Method: setCampus(); 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *
	 */
	@Test
	public void Team3_CLASSDETAILS_UT05(){
		ClassDetails cd= new ClassDetails();
		String campus="MMC";
		cd.setCampus(campus);
		assertEquals(campus, cd.campus);
				
		}
	
	
	
	/*
	 *Test Method: getTime(); 
	 * 
	 * 
	 * 
	 * 
	 * 
	 *
	 */
	@Test
	public void Team3_CLASSDETAILS_UT06(){
		
		TimeStub ts=new TimeStub();
		ClassDetails cd= new ClassDetails();
		ts=cd.getTime();
		assertEquals(ts, cd.time);
				
		}
	
	/*
	 * TestMethod:getDays();
	 * 
	 * 
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT07(){
		
		TimeStub ts=new TimeStub();
		ClassDetails cd= new ClassDetails();
		cd.time=ts;
		//assertEquals(ts.days,cd.getDays());
		Assert.assertEquals(ts.days, cd.getDays());		
		}
	
	/*
	 * Method: getInstuctor
	 * 
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT08(){
		ClassDetails cd= new ClassDetails();
		ProfessorStub ps=new ProfessorStub();
		cd.instructor = ps;
		Assert.assertEquals(ps,cd.getInstructor());		
		
	}
	
	/*
	 * method: hasConflict();
	 * There is no conflict with days.
	 * 
	 * 
	 */
	
	@Test
	public void Team3_CLASSDETAILS_UT09(){
		ClassDetails cd0= new ClassDetails();
		ClassDetails cd1= new ClassDetails();
		TimeStub ts0=new TimeStub();
		TimeStub ts1=new TimeStub();
		cd0.time=ts0;
		cd1.time=ts1;
		ts0.days="1001010";
		ts1.days="0110101";
		ts0.frHr=2;
		ts1.frHr=7;
		ts0.toHr=3;
		ts1.toHr=8;
		ts1.frMn=1;
		ts1.toMn=2;
		ts0.frMn=3;
		ts0.toMn=4;
		Assert.assertFalse(cd1.hasConflict(cd0));
		
		
	}	
	/*
	 * method: hasConflict();
	 * There is conflict with days but no conflict with time.
	 * 
	 * 
	 */
	
	@Test
	public void Team3_CLASSDETAILS_UT10(){
		ClassDetails cd0= new ClassDetails();
		ClassDetails cd1= new ClassDetails();
		TimeStub ts0=new TimeStub();
		TimeStub ts1=new TimeStub();
		cd0.time=ts0;
		cd1.time=ts1;
		ts0.days="1001010";
		ts1.days="1010110";
		ts0.frHr=2;
		ts0.frMn=30;
		ts0.toHr=3;
		ts0.toMn=45;
		ts1.frHr=7;
		ts1.frMn=15;
		ts1.toHr=8;
		ts1.toMn=25;
		
		Assert.assertFalse(cd1.hasConflict(cd0));
		
		
	}
	
	/*
	 * method: hasConflict();
	 * There is conflict with days and conflict with time. Both classes start
	 *  and end at same time.
	 * 
	 * 
	 */
	
	@Test
	public void Team3_CLASSDETAILS_UT11(){
		ClassDetails cd0= new ClassDetails();
		ClassDetails cd1= new ClassDetails();
		TimeStub ts0=new TimeStub();
		TimeStub ts1=new TimeStub();
		cd0.time=ts0;
		cd1.time=ts1;
		ts0.days="1001010";
		ts1.days="1010110";
		ts0.frHr=2;
		ts0.frMn=30;
		ts0.toHr=4;
		ts0.toMn=30;
		ts1.frHr=2;
		ts1.frMn=30;
		ts1.toHr=4;
		ts1.toMn=30;
		
		Assert.assertTrue(cd1.hasConflict(cd0));
		
		
	}
	/*
	 * method: hasConflict();
	 * There is conflict with days and conflict with time. Class 1 starts before class 2 
	 * and both end at the same time.
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT12(){
		ClassDetails cd0= new ClassDetails();
		ClassDetails cd1= new ClassDetails();
		TimeStub ts0=new TimeStub();
		TimeStub ts1=new TimeStub();
		cd0.time=ts0;
		cd1.time=ts1;
		ts0.days="1001010";
		ts1.days="1010110";
		ts0.frHr=1;
		ts0.frMn=30;
		ts0.toHr=4;
		ts0.toMn=30;
		ts1.frHr=2;
		ts1.frMn=30;
		ts1.toHr=4;
		ts1.toMn=30;
		
		Assert.assertTrue(cd1.hasConflict(cd0));
		
		
	}
	
	/*
	 * method: hasConflict();
	 * There is conflict with days and conflict with time. Class 1 starts after class 2 
	 * and both end at the same time.
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT13(){
		ClassDetails cd0= new ClassDetails();
		ClassDetails cd1= new ClassDetails();
		TimeStub ts0=new TimeStub();
		TimeStub ts1=new TimeStub();
		cd0.time=ts0;
		cd1.time=ts1;
		ts0.days="1001010";
		ts1.days="1010110";
		ts0.frHr=3;
		ts0.frMn=30;
		ts0.toHr=4;
		ts0.toMn=30;
		ts1.frHr=2;
		ts1.frMn=30;
		ts1.toHr=4;
		ts1.toMn=30;
		
		Assert.assertTrue(cd1.hasConflict(cd0));
		
		
	}
	
	/*
	 * method: hasConflict();
	 * There is conflict with days and conflict with time. Class 1 starts before class 2 
	 * and end before class 2, but there is conflict.
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT14(){
		ClassDetails cd0= new ClassDetails();
		ClassDetails cd1= new ClassDetails();
		TimeStub ts0=new TimeStub();
		TimeStub ts1=new TimeStub();
		cd0.time=ts0;
		cd1.time=ts1;
		ts0.days="1001010";
		ts1.days="1010110";
		ts0.frHr=3;
		ts0.frMn=30;
		ts0.toHr=4;
		ts0.toMn=30;
		ts1.frHr=2;
		ts1.frMn=30;
		ts1.toHr=3;
		ts1.toMn=45;
		
		Assert.assertTrue(cd1.hasConflict(cd0));
		
		
	}
	/*
	 * method: hasConflict();
	 * There is conflict with days and conflict with time. Class 1 starts after class 2 
	 * and end after class 2, but there is conflict.
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT15(){
		ClassDetails cd0= new ClassDetails();
		ClassDetails cd1= new ClassDetails();
		TimeStub ts0=new TimeStub();
		TimeStub ts1=new TimeStub();
		cd0.time=ts0;
		cd1.time=ts1;
		ts0.days="1001010";
		ts1.days="1010110";
		ts0.frHr=3;
		ts0.frMn=30;
		ts0.toHr=4;
		ts0.toMn=30;
		ts1.frHr=3;
		ts1.frMn=40;
		ts1.toHr=4;
		ts1.toMn=45;
		
		Assert.assertTrue(cd1.hasConflict(cd0));
		
		
	}
	
	/*
	 * method: hasConflict();
	 * There is conflict with days and Class 1 starts just after class 2.
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT16(){
		ClassDetails cd0= new ClassDetails();
		ClassDetails cd1= new ClassDetails();
		TimeStub ts0=new TimeStub();
		TimeStub ts1=new TimeStub();
		cd0.time=ts0;
		cd1.time=ts1;
		ts0.days="1001010";
		ts1.days="1010110";
		ts0.frHr=2;
		ts0.frMn=45;
		ts0.toHr=3;
		ts0.toMn=30;
		ts1.frHr=1;
		ts1.frMn=40;
		ts1.toHr=2;
		ts1.toMn=45;
		
		Assert.assertTrue(cd1.hasConflict(cd0));
		
		
	}
	
	/*
	 * method: isAtDay();
	 * The class is at which days.all three days are checked,
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT17(){
		ClassDetails cd= new ClassDetails();
		TimeStub ts=new TimeStub();
		cd.time=ts;
		ts.days="1001010";
		Assert.assertTrue(cd.isAtDay(0));
		Assert.assertTrue(cd.isAtDay(3));
		Assert.assertTrue(cd.isAtDay(5));
	}
	
	/*
	 * method: isAtDay();
	 * The class is at which days.all three days are checked.
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT18(){
		ClassDetails cd= new ClassDetails();
		TimeStub ts=new TimeStub();
		cd.time=ts;
		ts.days="1001010";
		Assert.assertFalse(cd.isAtDay(1));
		Assert.assertFalse(cd.isAtDay(2));
		Assert.assertFalse(cd.isAtDay(4));
		Assert.assertFalse(cd.isAtDay(6));
	}
	
	/*
	 * method: isAtTime();
	 * The class is in session or not at given time.
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT19(){
		ClassDetails cd= new ClassDetails();
		TimeStub ts=new TimeStub();
		cd.time=ts;
		ts.days="1001010";
		ts.frHr=2;
		ts.frMn=30;
		ts.toHr=4;
		ts.toMn=45;
		Assert.assertTrue(cd.isAtTime(2));
		Assert.assertTrue(cd.isAtTime(3));
		Assert.assertTrue(cd.isAtTime(4));
		Assert.assertFalse(cd.isAtTime(1));
		Assert.assertFalse(cd.isAtTime(5));
		
			
	}
	
	/*
	 * method: toString();
	 * The class Details as output display.
	 * 
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT20(){
		String campus="MMC";
		String Subject="STN";
		String ctlgnumbr="2123";
		String classnumbr="1";
		String term="FALL";
		
		CourseStub cs=new CourseStub(Subject,ctlgnumbr);
		ClassDetails cd=new ClassDetails(cs,classnumbr);
		TimeStub ts=new TimeStub();
		cd.time=ts;
		ts.days="1001010";
		ts.frHr=2;
		ts.frMn=30;
		ts.toHr=4;
		ts.toMn=45;
		cd.setTerm(term);
		cd.setCampus(campus);
	 	
	 
	}
	
}