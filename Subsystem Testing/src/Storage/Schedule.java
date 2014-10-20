package Storage;

import java.util.ArrayList;
import java.util.Collection;

public class Schedule {
	
	
	private  String id;
    private  String pantherID;
    private Collection<ClassDetails> classes;

    public Schedule(String pantherID)
    {
    	classes = new ArrayList<ClassDetails>();
        this.pantherID = pantherID;
    }

    public Schedule(Collection classes) {
        this.classes = classes;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPantherID()
    {
        return pantherID;
    }

    public void setPantherID(String pantherID)
    {
        this.pantherID = pantherID;
    }

	public Collection<ClassDetails> getClasses()
	{
        return classes;
    }
	
	public void combine (String id)
	{}
	
	public void addClass (String id)
	{
		ClassDetails cd=new ClassDetails();
		String subject=new String(id.substring(0,3));
		String catlgnumbr=new String(id.substring(4,8));
		String days=new String(id.substring(9,16));
		String from=new String(id.substring(17,22));
		String to=new String(id.substring(23,28));
		Time t=new Time(from,to,days);
		cd.setTime(t);
		cd.subject=subject;
		cd.ctlgnumbr=catlgnumbr;
		classes.add(cd);
		}
	
	public void deleteClass (String id)
	{}

    public void addClass(Collection<ClassDetails> classes) {
    }

}
