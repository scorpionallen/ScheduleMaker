package Storage;

import java.util.Calendar;

public class ClassDetails {
	private Time time;
	public String subject;
	public String ctlgnumbr;
	
	public Time getTime() {
        return time;
        
    }
	
	public boolean hasConflict(ClassDetails cd) {
		for (int i = 0; i < getDays().length(); i++) {
			if (!(getDays().charAt(i) == '1' && cd.getDays().charAt(i) == '1')) continue;
			
			Calendar thisStart = Calendar.getInstance();
			thisStart.set(Calendar.HOUR_OF_DAY, this.time.frHr);
			thisStart.set(Calendar.MINUTE, this.time.frMn);
			
			Calendar thisEnd = (Calendar) thisStart.clone();
			thisEnd.set(Calendar.HOUR_OF_DAY, this.time.toHr);
			thisEnd.set(Calendar.MINUTE, this.time.toMn);
			
			Calendar otherStart = (Calendar) thisStart.clone();
			otherStart.set(Calendar.HOUR_OF_DAY, cd.time.frHr);
			otherStart.set(Calendar.MINUTE, cd.time.frMn);
			
			Calendar otherEnd = (Calendar) thisStart.clone();
			otherEnd.set(Calendar.HOUR_OF_DAY, cd.time.toHr);
			otherEnd.set(Calendar.MINUTE, cd.time.toMn);
			
			// no overlap
			if (thisStart.after(otherEnd) || thisEnd.before(otherStart)) return false;
			
			// same start/end times
			if (thisStart.equals(otherStart) || thisEnd.equals(otherEnd)) return true;
			
			// one starts within the other
			if ((thisStart.after(otherStart) && thisStart.before(otherEnd)) || (otherStart.after(thisStart) && otherStart.before(thisEnd))) return true;
			
			// one ends within the other
			if ((thisEnd.after(otherStart) && thisEnd.before(otherEnd)) || (otherEnd.after(thisStart) && otherEnd.before(thisEnd))) return true;
		}
		
		return false;
	}
	
    public void setTime(Time t) {
        time = t;
    }
    
	public String getDays() {
		return time.days;
	}
}