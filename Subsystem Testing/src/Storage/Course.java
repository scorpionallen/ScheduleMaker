package Storage;

import java.util.Collection;

public class Course extends StorageStub {
	private String catlgNbr, subject;
	
	public Course(String subject, String catlgNbr) {
        this.subject = subject;
        this.catlgNbr = catlgNbr;
    }
	
	public Collection<ClassDetails> getClasses(String term, Collection<String> campuses) {
		return super.getClasses(subject + catlgNbr, term, campuses);
	}
}