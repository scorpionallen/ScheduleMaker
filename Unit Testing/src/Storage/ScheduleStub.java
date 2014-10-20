package Storage;

import java.util.Collection;

public class ScheduleStub {
	private String id;
    private String pantherID;
    private Collection<ClassDetailsStub> classes;
    
    public ScheduleStub(String pantherID) {
        this.pantherID = pantherID;
    }
    
    public ScheduleStub(Collection<ClassDetailsStub> classes) {
        this.classes = classes;
    }
    
    public String getId() { return id; }
    
    public void setId(String id) { this.id = id; }
    
    public String getPantherID() { return pantherID; }
    
    public void setPantherID(String pantherID) { this.pantherID = pantherID; }
    
	public Collection<ClassDetailsStub> getClasses() { return classes; }
	
	public void combine(String id) { /* NOT IMPLEMENTED */ }
	
	public void addClass(String id) { /* NOT IMPLEMENTED */ }
	
	public void deleteClass(String id) { /* NOT IMPLEMENTED */ }
	
    public void addClass(Collection<ClassDetails> classes) { /* NOT IMPLEMENTED */ }
}