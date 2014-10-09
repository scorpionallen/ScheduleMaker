package Storage;

import java.util.Collection;

public class CourseStub {
	private String catlgNbr, subject;
	
	public CourseStub(String subject, String catlgNbr) {
		this.subject = subject;
        this.catlgNbr = catlgNbr;
	}
	
	public String getCatlgNbr() { return catlgNbr; }
	
	public Collection<ClassDetailsStub> getClasses(String term, Collection<String> campuses) {
		// TODO
		throw new UnsupportedOperationException("Not yet implemented!");
	}

    public String getSubject() { return subject; }
    
	@Override
	public String toString() {
		return getSubject() + getCatlgNbr();
	}
}