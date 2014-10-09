package Storage;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Implemented by the testing team to provide a stub implementation.
 * Currently returns empty collections until usable testing data can be produced.
 * @author jallen
 */
public class StubDB {
	public Collection<ClassDetails> getClasses1(Course c) {
		ArrayList<ClassDetails> classDetails = new ArrayList<ClassDetails>();
		
		return classDetails;
	}

	public Collection<ClassDetails> getClasses2(Course c) {
		ArrayList<ClassDetails> classDetails = new ArrayList<ClassDetails>();
		
		return classDetails;
	}
}