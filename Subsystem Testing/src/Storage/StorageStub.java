package Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class StorageStub {
	// for each campus, define a mapping from TERM/COURSE to a list of CLASSDETAILS
	private static final TreeMap<String,ArrayList<ClassDetails>> COURSES_BBC = new TreeMap<>();
	private static final TreeMap<String,ArrayList<ClassDetails>> COURSES_MMC = new TreeMap<>();
	
	// define the set of valid pantherID/password combinations
	private static final TreeMap<String,String> CREDENTIALS = new TreeMap<>();
	
	public Collection<ClassDetails> getClasses(String course, String term, Collection<String> campuses) {
		ArrayList<ClassDetails> courses = new ArrayList<>();
		String searchKey = term + " " + course;
		
		if (campuses.contains("BBC")) {
			for (ClassDetails cd : COURSES_BBC.get(searchKey)) {
				courses.add(cd);
			}
		}
		
		if (campuses.contains("MMC")) {
			for (ClassDetails cd : COURSES_MMC.get(searchKey)) {
				courses.add(cd);
			}
		}
		
		return courses;
	}
	
	public static void initCourses() {
		COURSES_BBC.clear();
		COURSES_MMC.clear();
	}
	
	public static void initCredentials() {
		CREDENTIALS.clear();
	}
	
	public boolean isLoginValid(String pantherID, String password) {
		return pantherID != null && CREDENTIALS.containsKey(pantherID) && CREDENTIALS.get(pantherID).equals(password);
	}
	
	public static void registerCourse(String campus, String term, String course, ClassDetails cds) {
		if (campus == null || !campus.matches("^All|Biscayne|University$")) throw new IllegalArgumentException("Invalid campus value: " + campus);
		if (term == null || !term.matches("^(Spring|Summer|Fall) [0-9]+$")) throw new IllegalArgumentException("Invalid term value: " + term);
		if (course == null || !course.matches("^[A-Z]{3}[0-9]{4}$")) throw new IllegalArgumentException("Invalid course value: " + course);
		if (cds == null) throw new IllegalArgumentException("ClassDetailsStub paramater cannot be null");
		
		String searchKey = term + " " + course;
		
		if (campus.matches("^All|Biscayne$")) {
			ArrayList<ClassDetails> termCourses = COURSES_BBC.get(searchKey);
			if (termCourses == null) {
				termCourses = new ArrayList<ClassDetails>();
				COURSES_BBC.put(searchKey, termCourses);
			}
			termCourses.add(cds);
		}
		
		if (campus.matches("^All|University$")) {
			ArrayList<ClassDetails> termCourses = COURSES_MMC.get(searchKey);
			if (termCourses == null) {
				termCourses = new ArrayList<ClassDetails>();
				COURSES_MMC.put(searchKey, termCourses);
			}
			termCourses.add(cds);
		}
	}
	
	public static void registerCredentials(String pantherID, String password) {
		if (pantherID == null || pantherID.equals("")) throw new IllegalArgumentException("PantherID cannot be null or empty");
		if (password == null || password.equals("")) throw new IllegalArgumentException("Password cannot be null or empty");
		
		CREDENTIALS.put(pantherID, password);
	}
}