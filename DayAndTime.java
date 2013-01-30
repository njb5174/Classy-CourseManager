package classy;

/**
 * Name:  Nathan Bialas
 * Section:  1
 * Program: Classy Project
 * Date:  10/2/2012
 */
 
/**
 * This is "CourseTime" class function for the "Classy Project"
 * It contains two members, a start time and an end time for a given course
 *
 * @author Nathan Bialas
 * @author Robert Dick
 * @version 1.0:  10/2/2012 
 * 
 * 11/5/2012, added getters
 */
 public class DayAndTime
 {
	private Time startTime;		// start time of the class
	private Time endTime;		// end time of the class
	private String dayOfWeek;	//contains the day(s) the class meets
	
	public DayAndTime(Time startTime, Time endTime, String dayOfWeek)
	{
		this.startTime = startTime;
		this.endTime = endTime;
		this.dayOfWeek = dayOfWeek;
	}
        
    public String getDay()
    {
        return this.dayOfWeek;
    }
        
    public Time getStartTime()
    {
        return this.startTime;
    }
        
    public Time getEndTime()
    {
        return this.endTime;
    }
	
	public String toString()
	{
		return this.dayOfWeek + " " + this.startTime + "-" + this.endTime;
	}
 }