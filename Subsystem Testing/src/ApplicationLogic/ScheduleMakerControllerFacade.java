package ApplicationLogic;

import java.util.Collection;

import Storage.Schedule;
import Storage.StorageStub;

public class ScheduleMakerControllerFacade {
	private final ScheduleMakerController SMC = new ScheduleMakerController();
	private final StorageStub STORAGE = new StorageStub();
	
	public void buildPage() {
		new FormatPage().buildPage();
	}
	
	public String buildSchedulesPage(Collection<Schedule> schedules, int page) {
		return new FormatPage().buildSchedulesPage(schedules, page);
	}
	
	public void createPage() {
		new FormatPage().createPage();
	}
	
	public void createPage(Schedule schedule[]) {
		new FormatPage().createPage(schedule);
	}
	
	public Collection<Schedule> createSchedule(ScheduleOptions schOpt) {
		return SMC.createSchedule(schOpt);
	}
	
	public Collection<Schedule> createSchedule(String term, Collection<String> courses, String cmp, String SPDays) {
		return SMC.createSchedule(term, courses, cmp, SPDays);
	}
	
	public void getBalance() {
		SMC.getBalance();
	}
	
	public void getSavedSchedule() {
		SMC.getSavedSchedule();
	}
	
	public boolean isLoginValid(String pantherID, String password) {
		return new Authentication().isLoginValid(pantherID, password);
	}
	
	public String printCalendar(String calendar[][]) {
		return new FormatPage().printCalendar(calendar);
	}
	
	public void requestPage(String pageName) {
		new FormatPage().requestPage(pageName);
	}
	
	public void saveSchedules(Collection<Schedule> schedules) {
		SMC.saveSchedules(schedules);
	}
	
	public void sortSchedules() {
		SMC.sortSchedules();
	}
}