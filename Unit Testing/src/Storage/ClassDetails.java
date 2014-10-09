package Storage;

import java.util.ArrayList;
import java.util.Iterator;

public class ClassDetails {
    private CourseStub course;
    private String classNbr;
    private ProfessorStub instructor;
    private String bldg_room;
    private String campus;
    private String term;
    private TimeStub time;
    
    //constructors
    public ClassDetails() { }
    
    public ClassDetails(CourseStub course, String classNbr) {
        this.course = course;
        this.classNbr = classNbr;
    }
    
    public String getDays() { return getTime().days; }
    
    public ProfessorStub getInstructor() { return null; }
    
    public TimeStub getTime() { return time; }
    
    public boolean hasConflict(ClassDetails cd) {
        TimeStub c1 = getTime();
        TimeStub c2 = cd.getTime();
        
        //compare times
        String daysofC1 = c1.days;
        String daysofC2 = c2.days;
        
        ArrayList<String> A1, A2;
        A1 = new ArrayList<String>();
        A2 = new ArrayList<String>();
        String[] days = new String[7];
        days[0] = "Mon";
        days[1] = "Tues";
        days[2] = "Wed";
        days[3] = "Thurs";
        days[4] = "Fri";
        days[5] = "Sat";
        days[6] = "Sun";
        
        for (int i = 0; i < 7; i++) {
            if (daysofC1.charAt(i) == '1') {
                String day = new String(days[i]);
                A1.add(day);
            }
        }
        
        for (int i = 0; i < 7; i++) {
            if (daysofC2.charAt(i) == '1') {
                String day = new String(days[i]);
                A2.add(day);
            }
        }
        
        Iterator<String> it1 = A1.iterator();
        Iterator<String> it2 = A2.iterator();
        while (it1.hasNext()) {
            String d1 = it1.next();
            while (it2.hasNext()) {
                String d2 = it2.next();
                
                if (d1.equals(d2)) {
                    if (c1.frHr == c2.frHr) { // both classes starts at same hour
                        if (c1.frMn >= c2.frMn)
                            return true;
                    } else if (c1.frHr < c2.frHr) { // c1 starts before c2
                        if (c1.toHr == c2.frHr) {
                            if (c1.toMn >= c2.frMn)
                                return true;
                        } else if (c1.toHr > c2.frHr)
                            return true;
                    } else if (c1.frHr > c2.frHr) { //c1 starts after c2
                        if (c2.toHr == c1.frHr) {
                            if (c2.toMn >= c1.frMn)
                                return true;
                        } else if (c2.toHr > c1.frHr)
                            return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean isAtDay(int day) {
        if (getTime().days.substring(day, day+1).equals("1"))
            return true;
        else
            return false;
    }
    
    public boolean isAtTime(int hour) {
        if (getTime().frHr <= hour && hour <= getTime().toHr)
            return true;
        else
            return false;
    }
    
    public void setCampus(String campus) { this.campus = campus; }
    
    public void setTerm(String term) { this.term = term; }
    
    public void setTime(TimeStub t) { time = t; }
    
    @Override
    public String toString() {
        String result = "";
        
        result += course.toString() + "\n" + classNbr + "\n";
        
        if (this.getTime() != null)
            result += "" + this.getTime().toString() + "\n";
        
        if (campus != null)
            result += "" + campus + "\n";
        
        return result;
    }
}