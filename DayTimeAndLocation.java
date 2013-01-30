package classy;

/**
 * Name:  Nathan Bialas
 * Section:  1
 * Program: Classy Project
 * Date:  10/2/2012
 */
 
/**
 * This is the "CourseTimes" class function for the "Classy Project"
 * It contains four members, a start time and an end time for a given course, the day of the week, and the location for that day.
 *
 * @author Nathan Bialas
 * @author Robert Dick
 * @version 1.0:  10/2/2012 
 * 
 * 11/5/2012, added getters
 */
 public class DayTimeAndLocation extends DayAndTime
 {
	private String location;		// the location of the class meeting
	
	public DayTimeAndLocation(Time startTime, Time endTime, String dayOfWeek, String location)
	{
		super(startTime, endTime, dayOfWeek);
		this.location = location;
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	
	public void setLocation(String location)
	{
		this.location = location;
	}
        
        public String getTimes()
        {
            return super.toString();
        }
	
	public String toString()
	{
		return super.toString() + "\nLocation:  " + this.location;
	}
 }