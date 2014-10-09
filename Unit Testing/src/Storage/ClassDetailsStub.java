package Storage;

import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

/**
 * This stub class is used for unit testing the ScheduleMakerController class; it
 * provides the minimum set of required methods and data to support that testing.
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
		/** A Monday/Wednesday/Friday schedule; conflicts with MonWedFri2. */
		MonWedFri1("1010100"),
		/** Another Monday/Wednesday/Friday schedule; conflicts with MonWedFri1. */
		MonWedFri2("1010100"),
		/** A Saturday only schedule. */
		SatOnly("0000010"),
		/** A Tuesday/Thursday schedule. */
		TueThu("0101000");
		
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
		private TEST_CONFIG(String days) {
			if (days == null || days.equals("")) {
				throw new NullPointerException("Constructor paramater cannot be null or empty");
			} else if (!days.matches("^[01]{7}$")) {
				throw new IllegalArgumentException("Expected 7-character string containing only 0s and 1s: " + days);
			}
			
			this.days = days;
		}
	}
	
	/** The configuration conflicts used by the hasConflict() method. */
	private static final TreeMap<TEST_CONFIG, List<TEST_CONFIG>> CONFLICTING_CONFIGURATIONS = new TreeMap<>();
	static {
		//TODO: manually assign conflicting course schedules
		CONFLICTING_CONFIGURATIONS.put(TEST_CONFIG.MonWedFri1, Collections.singletonList(TEST_CONFIG.MonWedFri2));
		CONFLICTING_CONFIGURATIONS.put(TEST_CONFIG.MonWedFri2, Collections.singletonList(TEST_CONFIG.MonWedFri1));
	}
	
	/** The (readonly) configuration of this particular ClassDetailsStub object. */
	private final TEST_CONFIG config;
	
	/**
	 * Sole constructor; the single parameter indicates what data the object will contain for testing.
	 * 
	 * @param config the configuration that this ClassDetailsStub object will have
	 */
	public ClassDetailsStub(TEST_CONFIG config) {
		this.config = config;
	}
	
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
		return CONFLICTING_CONFIGURATIONS.get(cds).contains(this);
	}
}