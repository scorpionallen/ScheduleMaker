package Storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

public class CourseStub {
	// for each campus, define a mapping from TERM/COURSE to a list of CLASSDETAILS
	private static final TreeMap<String,ArrayList<ClassDetailsStub>> BBC_COURSES = new TreeMap<>();
	private static final TreeMap<String,ArrayList<ClassDetailsStub>> MMC_COURSES = new TreeMap<>();
	
	public static void initializeCourses() {
		BBC_COURSES.clear();
		MMC_COURSES.clear();
	}
	
	public static void registerCourse(String campus, String term, ClassDetailsStub cds) {
		if (campus == null || !campus.matches("^All|Biscayne|University$")) throw new IllegalArgumentException("Invalid campus value: " + campus);
		if (term == null || !term.matches("^(Spring|Summer|Fall) [0-9]+$")) throw new IllegalArgumentException("Invalid term value: " + term);
		if (cds == null) throw new IllegalArgumentException("ClassDetailsStub paramater cannot be null");
		
		String searchKey = term + " " + cds.getCourse();
		
		if (campus.matches("^All|Biscayne$")) {
			ArrayList<ClassDetailsStub> termCourses = BBC_COURSES.get(searchKey);
			if (termCourses == null) {
				termCourses = new ArrayList<ClassDetailsStub>();
				BBC_COURSES.put(searchKey, termCourses);
			}
			
			ClassDetailsStub toAdd;
			if (cds.campus.equals("Biscayne")) toAdd = cds;
			else {
				toAdd = cds.clone();
				toAdd.campus = "Biscayne";
			}
			
			termCourses.add(toAdd);
		}
		
		if (campus.matches("^All|University$")) {
			ArrayList<ClassDetailsStub> termCourses = MMC_COURSES.get(searchKey);
			if (termCourses == null) {
				termCourses = new ArrayList<ClassDetailsStub>();
				MMC_COURSES.put(searchKey, termCourses);
			}
			
			ClassDetailsStub toAdd;
			if (cds.campus.equals("University")) toAdd = cds;
			else {
				toAdd = cds.clone();
				toAdd.campus = "University";
			}
			
			termCourses.add(toAdd);
		}
	}
	
	private String catlgNbr, subject;
	
	public CourseStub(String subject, String catlgNbr) {
		this.subject = subject;
        this.catlgNbr = catlgNbr;
	}
	
	public String getCatlgNbr() { return catlgNbr; }
	
	public Collection<ClassDetailsStub> getClasses(String term, Collection<String> campuses) {
		ArrayList<ClassDetailsStub> courses = new ArrayList<>();
		String searchKey = term + " " + this.toString();
		
		if (campuses.contains("Biscayne") && BBC_COURSES.get(searchKey) != null) {
			for (ClassDetailsStub cds : BBC_COURSES.get(searchKey)) {
				courses.add(cds);
			}
		}
		
		if (campuses.contains("University") && MMC_COURSES.get(searchKey) != null) {
			for (ClassDetailsStub cds : MMC_COURSES.get(searchKey)) {
				courses.add(cds);
			}
		}
		
		return courses;
	}

    public String getSubject() { return subject; }
    
	@Override
	public String toString() {
		return getSubject() + getCatlgNbr();
	}
}