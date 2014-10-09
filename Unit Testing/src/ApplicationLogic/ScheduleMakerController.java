package ApplicationLogic;

import Storage.ClassDetailsStub;
import Storage.CourseStub;
import Storage.ScheduleStub;

import java.util.Collection;
import java.util.ArrayList;

public class ScheduleMakerController {
    public boolean conflict(Collection c) {
        ArrayList tmp1, tmp2, list = (ArrayList) c;
        
        if (list.size() <= 1)
            return false;
        
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                tmp1 = (ArrayList) list.get(i);
                tmp2 = (ArrayList) list.get(j);
                if (((ClassDetailsStub) tmp1.get(0)).hasConflict((ClassDetailsStub) tmp2.get(0)))
                    return true;
            }
    	}
        
        return false;
    }
    
    public Collection<ScheduleStub> createSchedule(ScheduleOptionsStub schOpt) {
        Collection<ScheduleStub> schedules = new ArrayList<ScheduleStub>();
        
        //get courses
        Collection<String> courses = new ArrayList<String>();
        if (schOpt.getCourse1() != null && !schOpt.getCourse1().trim().equals(""))
            courses.add(schOpt.getCourse1());
        if (schOpt.getCourse2() != null && !schOpt.getCourse2().trim().equals(""))
            courses.add(schOpt.getCourse2());
        if (schOpt.getCourse3() != null && !schOpt.getCourse3().trim().equals(""))
            courses.add(schOpt.getCourse3());
        if (schOpt.getCourse4() != null && !schOpt.getCourse4().trim().equals(""))
            courses.add(schOpt.getCourse4());
        if (schOpt.getCourse5() != null && !schOpt.getCourse5().trim().equals(""))
            courses.add(schOpt.getCourse5());
        if (schOpt.getCourse6() != null && !schOpt.getCourse6().trim().equals(""))
            courses.add(schOpt.getCourse6());
        
        if (courses.size() <= 0)
            return schedules;
        
        //get campus
        Collection<String> campus = new ArrayList<String>();
        if (schOpt.getCampus().equals("All")) {
            campus.add("Biscayne");
            campus.add("University");
        } else campus.add(schOpt.getCampus());
        
        //get term
        String term = schOpt.getTerm();
        
        //get preferred days
        String SPDays = "";
        if (schOpt.getM() != null)
            SPDays += schOpt.getM();
        else SPDays += "1";
        
        if (schOpt.getT() != null)
            SPDays += schOpt.getT();
        else SPDays += "1";
        
        if (schOpt.getW() != null)
            SPDays += schOpt.getW();
        else SPDays += "1";
        
        if (schOpt.getTh() != null)
            SPDays += schOpt.getTh();
        else SPDays += "1";
        
        if (schOpt.getF() != null)
            SPDays += schOpt.getF();
        else SPDays += "1";
        
        if (schOpt.getS() != null)
            SPDays += schOpt.getS();
        else SPDays += "1";
        
        if (schOpt.getSu() != null)
            SPDays += schOpt.getSu();
        else SPDays += "1";
        
        CourseStub course_tmp;
        Collection<Collection<ClassDetailsStub>> course_collection = new ArrayList<Collection<ClassDetailsStub>>();
        
        for (Object c : courses) {
            course_tmp = new CourseStub(((String) c).substring(0, 3), ((String) c).substring(3));
            Collection<ClassDetailsStub> classes_tmp = course_tmp.getClasses(term, campus);
            if (classes_tmp.size() > 0) {
                char[] days = SPDays.toCharArray();
                Collection<ClassDetailsStub> returnClasses = new ArrayList<ClassDetailsStub>();
                boolean daymatches;
                
                for (Object cd : classes_tmp) {
                    daymatches = false;
                    char[] cdays = ((ClassDetailsStub) cd).getDays().toCharArray();
                    for (int i = 0; i < 7; i++) {
                        if (cdays[i] == '1') {
                            if (cdays[i] == days[i])
                                daymatches = true;
                            else {
                                daymatches = false;
                                break;
                            }
                        }
                    }
                    
                    if (daymatches)
                        returnClasses.add((ClassDetailsStub) cd);
                }
                
                if (!returnClasses.isEmpty())
                    course_collection.add(returnClasses);
            }
        }
        
        if (!course_collection.isEmpty())
            findSchedule(course_collection, course_collection.size() - 1, schedules);
        
        return schedules;
    }
    
    public Collection<ScheduleStub> createSchedule(String term, Collection<String> courses, String cmp, String SPDays) {
        Collection<ScheduleStub> schedules = new ArrayList<ScheduleStub>();
        
        if (courses.size() <= 0)
            return schedules;
        
        //get campus
        Collection<String> campus = new ArrayList<String>();
        if (cmp.equals("All")) {
            campus.add("Biscayne");
            campus.add("University");
        } else campus.add(cmp);
        
        CourseStub course_tmp;
        Collection<Collection<ClassDetailsStub>> course_collection = new ArrayList<Collection<ClassDetailsStub>>();
        
        for (Object c : courses) {
            if (!((String) c).equals("")) {
                course_tmp = new CourseStub(((String) c).substring(0, 3), ((String) c).substring(3));
                Collection<ClassDetailsStub> classes_tmp = course_tmp.getClasses(term, campus);
                if (classes_tmp.size() > 0) {
                    char[] days = SPDays.toCharArray();
                    Collection<ClassDetailsStub> returnClasses = new ArrayList<ClassDetailsStub>();
                    boolean daymatches;
                    
                    for (Object cd : classes_tmp) {
                        daymatches = false;
                        char[] cdays = ((ClassDetailsStub) cd).getDays().toCharArray();
                        for (int i = 0; i < 7; i++) {
                            if (cdays[i] == '1') {
                                if (cdays[i] == days[i])
                                    daymatches = true;
                                else {
                                    daymatches = false;
                                    break;
                                }
                            }
                        }
                        
                        if (daymatches)
                            returnClasses.add((ClassDetailsStub) cd);
                        
                    }
                    if (!returnClasses.isEmpty())
                        course_collection.add(returnClasses);
                }
            }
        }
        
        if (!course_collection.isEmpty())
            findSchedule(course_collection, course_collection.size() - 1, schedules);
        
        return schedules;
    }
    
    public void findClasses() { /* NOT IMPLEMENTED */ }
    
    /**
     * @param c         Collection of courses
     * @param cursor
     * @param schedules
     */
    public void findSchedule(Collection<Collection<ClassDetailsStub>> c, int cursor, Collection<ScheduleStub> schedules) {
        ArrayList list = (ArrayList) c;
        if (sizeIsOne(list)) {
            if (!conflict(list))
                schedules.add(new ScheduleStub(list));
        } else {
            if (((ArrayList) list.get(cursor)).size() > 1) {
                for (int j = 0; j < ((ArrayList) list.get(cursor)).size(); j++) {
                    ArrayList<Collection<ClassDetailsStub>> tmp = new ArrayList<Collection<ClassDetailsStub>>(list);
                    ArrayList aux = new ArrayList();
                    ArrayList aux1 = (ArrayList) list.get(cursor);
                    aux.add(aux1.get(j));
                    
                    tmp.set(cursor, aux);
                    findSchedule(tmp, cursor, schedules);
                }
            } else {
                findSchedule(c, cursor - 1, schedules);
            }
        }
    }
    
    public void getBalance() { /* NOT IMPLEMENTED */ }
    
    public void getSavedSchedule() { /* NOT IMPLEMENTED */ }
    
    public void saveSchedules(Collection<ScheduleStub> schedules) { /* NOT IMPLEMENTED */ }
    
    public boolean sizeIsOne(Collection c) {
        for (Object item : c) {
            if (((Collection) item).size() != 1)
                return false;
        }
        
        return true;
    }
    
    public void sortSchedules() { /* NOT IMPLEMENTED */ }
}