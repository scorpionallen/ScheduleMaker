package Storage;

public class TimeStub {
    public int frHr;
    public int frMn;
    public int toHr;
    public int toMn;
    public String days;
    
    public TimeStub() {  }
    
    @Override
    public String toString() {
        return frHr + ":" + frMn + "-" + toHr + ":" + toMn;
    }
}