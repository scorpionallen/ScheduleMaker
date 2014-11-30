package Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * This stub class is used for unit testing the ScheduleMakerController class; it
 * provides the minimum set of required methods and data to satisfy the dependencies the
 * controller requires to operate. 
 * 
 * Functionally, this class represents a course being offered at the university on specific
 * days at the same time each day it occurs.
 * 
 * To control the size of the testing input domain, this class has a preset number of
 * configurations; the single parameter to the constructor will automatically pre-populate
 * the object with the necessary information to support one or more specific test cases.
 * 
 * @author Jason Allen
 */
public class ClassDetailsStub {
	/**
	 * Represents a pre-defined configuration of test data used during unit testing.
	 * Each constant defines a course schedule and what other configurations it conflicts with.
	 */
	public static enum TEST_CONFIG {
		/** A Monday/Wednesday/Friday class that occurs at the same time as MonWedFri1B. */
		MonWedFri1A("CEN4012", "1010100"),
		/** Another Monday/Wednesday/Friday class that occurs at the same time as MonWedFri1A. */
		MonWedFri1B("COP1101", "1010100"),
		/** Another Monday/Wednesday/Friday schedule, offered at a time that does not overlap the other MWF classes. */
		MonWedFri2("MAD3512", "1010100"),
		/** A Saturday only class. */
		SatOnly("DAT1345", "0000010"),
		/** A Tuesday/Thursday class. */
		TueThu("MAC1266", "0101000");
		
		/** Internal storage for the course a particular configuration represents. */
		private final String course;
		
		/** Internal storage for the days a particular configuration represents. */
		private final String days;
		
		/**
		 * Internal constructor for the TEST_CONFIG enumeration.
		 * Creates a configuration object that represents a weekly course schedule.
		 * 
		 * @param days flags for the days of the week a course is offered on
		 * @throws NullPointerException if the parameter is null or empty
		 * @throws IllegalArgumentException if the parameter does not match the regex ^[01]{7}$
		 */
		private TEST_CONFIG(String course, String days) {
			if (days == null || days.equals("")) {
				throw new NullPointerException("Constructor paramater cannot be null or empty");
			} else if (!days.matches("^[01]{7}$")) {
				throw new IllegalArgumentException("Expected 7-character string containing only 0s and 1s: " + days);
			} else if (course == null || course.equals("")) {
				throw new IllegalArgumentException("Course parameter cannot be empty or null");
			}
			
			this.course = course;
			this.days = days;
		}
	}
	
	/** The configuration conflicts used by the hasConflict() method. */
	private static final TreeMap<TEST_CONFIG, List<TEST_CONFIG>> CONFLICTING_CONFIGURATIONS = new TreeMap<>();
	static {
		// declare that every schedule conflicts with itself
		for (TEST_CONFIG config : TEST_CONFIG.values()) {
			ArrayList<TEST_CONFIG> selfConflictList = new ArrayList<>();
			selfConflictList.add(config);
			CONFLICTING_CONFIGURATIONS.put(config, selfConflictList);
		}
		
		// in addition, schedule MonWedFri1A conflicts with MonWedFri1B; due to reflexive nature,
		// add it both ways into the CONFLICTING_CONFIGURATIONS object
		CONFLICTING_CONFIGURATIONS.get(TEST_CONFIG.MonWedFri1A).add(TEST_CONFIG.MonWedFri1B);
		CONFLICTING_CONFIGURATIONS.get(TEST_CONFIG.MonWedFri1B).add(TEST_CONFIG.MonWedFri1A);
	}
	
	/** Optional field for specifying which campus this course is offered at. */
	public String campus = "";
	
	/** Optional field for specifying which term this course is offered during. */
	public String term = "";
	
	/** The (readonly) configuration of this particular ClassDetailsStub object. */
	private final TEST_CONFIG config;
	
	/**
	 * Sole constructor; the single parameter indicates what data the object will contain for testing.
	 * 
	 * @param config the configuration that this ClassDetailsStub object will have
	 * @throws NullPointerException if the parameter is null
	 */
	public ClassDetailsStub(TEST_CONFIG config) {
		if (config == null) throw new NullPointerException("Config parameter cannot be null");
		
		this.config = config;
	}
	
	@Override
	public ClassDetailsStub clone() {
		ClassDetailsStub theClone = new ClassDetailsStub(this.config);
		
		theClone.campus = this.campus;
		theClone.term = this.term;
		
		return theClone;
	}
	
	/**
	 * Accessor method for the course; format is the 3-character subject area and the 4-digit catalog number.
	 * 
	 * @return a String representing the course being offered
	 */
	public String getCourse() { return this.config.course; }
	
	/**
	 * This method enumerates (using a single String object) the days of the week a course
	 * is being offered on. It uses 1 to indicate that the course is offered on a day and 0
	 * otherwise. The first character represents Monday, and the 7th (the last) character
	 * represents Sunday. As an example, if a course is being offered on only Mondays and
	 * Wednesdays, the String returned would be "1010000".
	 *  
	 * @return a String of length 7 representing what days the course is being offered on
	 */
	public String getDays() {
		return this.config.days;
	}
	
	/**
	 * Checks for a scheduling conflict between the current ClassDetails and the supplied one.
	 * 
	 * @param cds a ClassDetailsStub to check for conflicts with
	 * @return true iff there is a conflict between the course details of this course and the supplied one
	 */
	public boolean hasConflict(ClassDetailsStub cds) {
		return CONFLICTING_CONFIGURATIONS.get(cds.config).contains(this.config)
				||
			   CONFLICTING_CONFIGURATIONS.get(this.config).contains(cds.config);
	}
}