package Storage;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class ClassDetailsDriver {
	
	/*
	 * Purpose: Testing the constructor for ClassDetails to see its assigning attributes correctly. 
	 * Preconditions: 
	 * 			-There is a course object cs which has a subject and catalog number.
	 * 			-There is a class number associated with the course cs.
	 * 			-These courses are mapped to the current class details object cd.
	 * Input: The input is the subject "STN" with catalog number "2123" and a class number "3"
	 * 			to be assigned as the attributes to the current class details object cd. 
	 * Expected Output: The Course and Class number are assigned correctly
	 * 					 to Class details object cd.
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
	
	/*
	 * Purpose: Testing the constructor for ClassDetails to see its assigning attributes correctly 
	 * 			even with semantically incorrect data type . 
	 * Preconditions: 
	 * 			-There is a course object cs which has a subject and catalog number.
	 * 			-There is a class number associated with the course cs.
	 * 			-These courses are mapped to the current class details object cd.
	 * Input: The input is the subject "2112" with catalog number "STN" and a class number "0"
	 * 			to be assigned as the attributes to the current class details object cd. 
	 * Expected Output: The Course and Class number are assigned correctly
	 * 					 to Class details object cd.
	 */
	
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
	 *Purpose: Testing the method setTime() in ClassDetails class.
	 *Preconditions: 
	 *			- There is a classdetails class object cd with a timestub attribute to be assigned. 
	 *Input: There is a timeStub class object "ts" which will be assigned to the class details object cd.
	 *Expected output: assertEquals(ts,cd.time); returns true.
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
	 *Purpose: Testing the method setTerm(); in classdetails class. 
	 *Preconditions:  
	 * 				- There is a classDetails object cd with term attribute to be assigned.
	 *Input: There is a String named Term = "FALL" which should be added to cd object as attribute.
	 *Expected Output: assertEquals(term,cd.term) is true. 
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
	 *Purpose: Testing the method setCampus(); 
	 *Preconditions: There is ClassDetails object cd with a campus attribute to be assigned. 
	 *Input: a string campus="MMC" 
	 *Expected Output:  assertEquals(campus,cd.campus) returns true.
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
	 *Purpose: Testing the method getTime();
	 *Preconditions: 
	 *			- There is a classdetails object cd with a time attribute, object of timestub assigned to it. 
	 *Input: a TimeStub object ts output of cd.getTime(); 
	 *Expected Output: assertEquals(ts,cd.time) returns true. 
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
	 * Purpose: Testing getDays(); method.
	 * Preconditions: 
	 * 				- There is a classdetails object cd with an assigned timestub object ts 
	 *				with the days in week the class is at session.						 
	 * Input: The days attribute of the timestub class days="0010101" saying the class is in
	 * 		session on wednesday, friday and sunday.
	 * Expected Output: the assertEquals(ts.days,cd.getDays()) returns true.
	 */
	@Test
	public void Team3_CLASSDETAILS_UT07(){
		
		TimeStub ts=new TimeStub();
		ClassDetails cd= new ClassDetails();
		cd.time=ts;
		ts.days="0010101";
		//assertEquals(ts.days,cd.getDays());
		Assert.assertEquals(ts.days, cd.getDays());		
		}
	
	/*
	 * Purpose: Testing method getinstructor(); 
	 * Preconditions:
	 * 				- There exists a classDetails object cd, class is offered by professorStub object ps assigned to cd.
	 * Input: a professorstub object ps is assigned, to be confirmed.
	 * Expected Output: assertEquals(ps,cd.getInstructor()) should return true if correctly assigned.
	 * Output: not Valid (false) since the result is false. The comparison of object with null since professorStub class is incomplete.
	 */
	@Test
	public void Team3_CLASSDETAILS_UT08(){
		ClassDetails cd= new ClassDetails();
		ProfessorStub ps=new ProfessorStub();
		cd.instructor = ps;
		Assert.assertEquals(ps,cd.getInstructor());		
		
	}
	
	/*
	 * Purpose: The method hasConflict() is Tested.
	 * Preconditions:
	 * 				- There is a ClassDetails object cd0 and cd1 for two seperate classes. 
	 * 				- There exists a TimeStub object ts0 and ts1 for both the classes respectively assigned to cd0 and cd1.
	 * 				- There exists attributes days and time respectively for each class in timeStub objects ts0 and ts1. 
	 * 				- There is no conflict with the days of the classes i.e., both classes are on different days in the week.
	 *Input: The first class is offered on days="1001010" i.e., monday, thursday and saturday at
	 *       the second class is offered on days="0110101" i.e., tuesday, wednesday , friday and sunday.
	 *Expected Output: assertFalse(cd1.hasConflict(cd0)) returns true. 
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
	 * Purpose: The method hasConflict() is Tested.
	 * Preconditions:
	 * 				- There is a ClassDetails object cd0 and cd1 for two seperate classes. 
	 * 				- There exists a TimeStub object ts0 and ts1 for both the classes respectively assigned to cd0 and cd1.
	 * 				- There exists attributes days and time respectively for each class in timeStub objects ts0 and ts1. 
	 * 				- There is conflict with the days of the classes i.e., both classes have some common days 
	 * 				but at different times on the same day.
	 *Input: The first class is offered on days="1001010" i.e., monday, thursday and saturday at 2:30 to 3:45
	 *		 the second class is offered on days="1010110" i.e., monday, wednesday , friday and saturday at 7:15 to 8:25
	 *Expected Output: assertFalse(cd1.hasConflict(cd0); returns true. 
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
	 * Purpose: The method hasConflict() is Tested.
	 * Preconditions:
	 * 				- There is a ClassDetails object cd0 and cd1 for two seperate classes. 
	 * 				- There exists a TimeStub object ts0 and ts1 for both the classes respectively assigned to cd0 and cd1.
	 * 				- There exists attributes days and time respectively for each class in timeStub objects ts0 and ts1. 
	 * 				- There is conflict with the days of the classes i.e., both classes have some common days 
	 * 				but at different times on the same day.
	 *Input: The first class is offered on days="1001010" i.e., monday, thursday and saturday at 2:30 to 3:45
	 *		 the second class is offered on days="1010110" i.e., monday, wednesday , friday and saturday at 7:15 to 8:25
	 *Expected Output: assertFalse(cd1.hasConflict(cd0); returns true. 
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
	 * Purpose: The method hasConflict() is Tested.
	 * Preconditions:
	 * 				- There is a ClassDetails object cd0 and cd1 for two seperate classes. 
	 * 				- There exists a TimeStub object ts0 and ts1 for both the classes respectively assigned to cd0 and cd1.
	 * 				- There exists attributes days and time respectively for each class in timeStub objects ts0 and ts1. 
	 * 				- There is conflict with days and conflict with time. Class 1 starts before class 2 
	 * 					and both end at the same time.
	 * Input: The first class is offered on days="1001010" i.e., monday, thursday and saturday at 1:30 to 4:30
	 *		 the second class is offered on days="1010110" i.e., monday, wednesday , friday and saturday at 2:30 to 4:30
	 * Expected Output: assertTrue(cd1.hasConflict(cd0); returns true. 
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
	 * Purpose: The method hasConflict() is Tested.
	 * Preconditions:
	 * 				- There is a ClassDetails object cd0 and cd1 for two seperate classes. 
	 * 				- There exists a TimeStub object ts0 and ts1 for both the classes respectively assigned to cd0 and cd1.
	 * 				- There exists attributes days and time respectively for each class in timeStub objects ts0 and ts1. 
	 * 				- There is conflict with days and conflict with time. Class 1 starts after class 2 
	 * 				  and both end at the same time.
	 * Input: The first class is offered on days="1001010" i.e., monday, thursday and saturday at 3:30 to 4:30
	 *		 the second class is offered on days="1010110" i.e., monday, wednesday , friday and saturday at 2:30 to 4:30
	 * Expected Output: assertTrue(cd1.hasConflict(cd0); returns true.  
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
	 * Purpose: The method hasConflict() is Tested.
	 * Preconditions:
	 * 				- There is a ClassDetails object cd0 and cd1 for two seperate classes. 
	 * 				- There exists a TimeStub object ts0 and ts1 for both the classes respectively assigned to cd0 and cd1.
	 * 				- There exists attributes days and time respectively for each class in timeStub objects ts0 and ts1. 
	 * 				- There is conflict with days and conflict with time. Class 1 starts before class 2 
	 * 				  and end before class 2, but there is conflict.
	 * Input: The first class is offered on days="1001010" i.e., monday, thursday and saturday at 3:30 to 4:30
	 *		 the second class is offered on days="1010110" i.e., monday, wednesday , friday and saturday at 2:30 to 3:45
	 * Expected Output: assertTrue(cd1.hasConflict(cd0); returns true.  
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
	 * Purpose: The method hasConflict() is Tested.
	 * Preconditions:
	 * 				- There is a ClassDetails object cd0 and cd1 for two seperate classes. 
	 * 				- There exists a TimeStub object ts0 and ts1 for both the classes respectively assigned to cd0 and cd1.
	 * 				- There exists attributes days and time respectively for each class in timeStub objects ts0 and ts1. 
	 * 				- There is conflict with days and conflict with time. Class 1 starts after class 2 
	 *				  and end after class 2, but there is conflict.
	 * Input: The first class is offered on days="1001010" i.e., monday, thursday and saturday at 3:30 to 4:30
	 *		 the second class is offered on days="1010110" i.e., monday, wednesday , friday and saturday at 3:40 to 4:45
	 * Expected Output: assertTrue(cd1.hasConflict(cd0); returns true.   
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
	 * Purpose: The method hasConflict() is Tested.
	 * Preconditions:
	 * 				- There is a ClassDetails object cd0 and cd1 for two seperate classes. 
	 * 				- There exists a TimeStub object ts0 and ts1 for both the classes respectively assigned to cd0 and cd1.
	 * 				- There exists attributes days and time respectively for each class in timeStub objects ts0 and ts1. 
	 * 				- There is conflict with days and Class 1 starts just after class 2.
	 * Input: The first class is offered on days="1001010" i.e., monday, thursday and saturday at 2:45 to 3:30
	 *		 the second class is offered on days="1010110" i.e., monday, wednesday , friday and saturday at 1:40 to 2:45
	 * Expected Output: assertTrue(cd1.hasConflict(cd0); returns true. 
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
	 * Purpose: The method isAtDay() is Tested.
	 * Preconditions:
	 * 				- There is an object cd for class ClassDetails associated with TimeStub object ts having
	 * 				  Having an attibute called days which specify on what days the classes are in session.
	 * Input: 
	 * 				- String days="1001010" specifying that the class is offered on monday, Thursday and saturday.
	 * 				- It is checked for all 7 days in week from 0-6.
	 * Expected Output: cd.isAtDay(n) is true for 0,3 &5. but false for 1,2,4 & 6.
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT17(){
		ClassDetails cd= new ClassDetails();
		TimeStub ts=new TimeStub();
		cd.time=ts;
		ts.days="1001010";
		Assert.assertTrue(cd.isAtDay(0));
		Assert.assertFalse(cd.isAtDay(1));
		Assert.assertFalse(cd.isAtDay(2));
		Assert.assertTrue(cd.isAtDay(3));
		Assert.assertFalse(cd.isAtDay(4));
		Assert.assertTrue(cd.isAtDay(5));
		Assert.assertFalse(cd.isAtDay(6));
	}
	
	
	/*
	 * method: isAtTime();
	 * The class is in session or not at given time.
	 * Purpose: The method isAtTime() is Tested.
	 * Preconditions:
	 * 				- There is an object cd for class ClassDetails associated with TimeStub object ts having
	 * 				  attributes which specify if the class is in session or not at given time.
	 * Input: 
	 * 				- String days="1001010" specifying that the class is offered on monday, Thursday and saturday at 2:30 to 4:45.
	 * 				- It is checked for the hour values of 1,2,3,4,5.
	 * Expected Output: cd.isAtTime(n) is true for 2,3 &4. but false for 1 &5.
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT18(){
		ClassDetails cd= new ClassDetails();
		TimeStub ts=new TimeStub();
		cd.time=ts;
		ts.days="1001010";
		ts.frHr=2;
		ts.frMn=30;
		ts.toHr=4;
		ts.toMn=45;
		Assert.assertFalse(cd.isAtTime(1));
		Assert.assertTrue(cd.isAtTime(2));
		Assert.assertTrue(cd.isAtTime(3));
		Assert.assertTrue(cd.isAtTime(4));
		Assert.assertFalse(cd.isAtTime(5));
		
			
	}
	
	/*
	 * purpose: Testing toString() method.
	 * Preconditions:
	 * 				- All the attributes in ClassDetails class object cd are defined.
	 * Input:  all the attributes are given values accordingly.
	 * Expected Output: The tostring String has the following value 
	 * 			STN2123
				1
				2:30-4:45
				MMC
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
	 	String tostring=cd.toString();
	  Assert.assertEquals("STN2123\n1\n2:30-4:45\nMMC\n", tostring);
	}
	
	/*
	 * Purpose: The method hasConflict() is Tested.
	 * Preconditions:
	 * 				- There is a ClassDetails object cd0 and cd1 for two seperate classes. 
	 * 				- There exists a TimeStub object ts0 and ts1 for both the classes respectively assigned to cd0 and cd1.
	 * 				- There exists attributes days and time respectively for each class in timeStub objects ts0 and ts1. 
	 * 				- There is conflict with days and conflict with time. Class 1 starts after class 2 
	 *				  and end after class 2, but there is conflict.
	 * Input: The first class is offered on days="1001010" i.e., monday, thursday and saturday at 3:30 to 4:30
	 *		 the second class is offered on days="1010110" i.e., monday, wednesday , friday and saturday at 2:40 to 3:45
	 * Expected Output: assertTrue(cd1.hasConflict(cd0); returns true.   
	 * 
	 */
	@Test
	public void Team3_CLASSDETAILS_UT21(){
		ClassDetails cd0= new ClassDetails();
		ClassDetails cd1= new ClassDetails();
		TimeStub ts0=new TimeStub();
		TimeStub ts1=new TimeStub();
		cd0.time=ts0;
		cd1.time=ts1;
		ts0.days="1001010";
		ts1.days="1010110";
		ts0.frHr=1;
		ts0.frMn=10;
		ts0.toHr=3;
		ts0.toMn=50;
		ts1.frHr=3;
		ts1.frMn=30;
		ts1.toHr=4;
		ts1.toMn=45;
		
		Assert.assertTrue(cd1.hasConflict(cd0));
		
		
	}
	
	
	
}