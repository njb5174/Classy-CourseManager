package classy;

/**
 * Name:  Robert Dick
 * Section:  1
 * Program: Classy Project
 * Date:  10/6/2012
 */

/**
 * This class represents a simple hour and minute time with AM or PM indicated by a boolean.
 * 
 * @author Robert Dick
 * @version 1.1:  10/6/2012
 */
public class Time
{
	private int hour;		// the hour part
	private int minute;		// the minute part
	private boolean PM;		// true means AM, false means PM
	
	public Time(String strTime, boolean PM)
	{
		this(Integer.parseInt(strTime.split(":")[0]), Integer.parseInt(strTime.split(":")[1]), PM);
	}
	
	public Time(int hour, int minute, boolean PM)
	{
		// PRE: 1 <= hour <= 12; 0 <= minute <= 59; AM is initialized
		// POST: Members hour, minute, and AM are set to the parameter values.
		this.hour = hour;
		this.minute = minute;
		this.PM = PM;
	}
	
	public int getHour() {
		return this.hour;
	}
	
	public int getHourTwentyFour() {
		if (this.PM && this.hour < 12) {
			return this.hour + 12;
		}
		else {
			return this.hour;
		}
	}
	
	public int getMinute() {
		return this.minute;
	}
	
	public boolean isPM() {
		return this.PM;
	}
	
	/**
	 * Returns a string representation of a Time.
	 */
	public String toString()
	{
		// POST: Returns a string in the format [Hour]:[Minute][AM/PM]
		return String.format("%d:%02d" + (this.PM ? "PM" : "AM"), this.hour, this.minute);
	}
	
	public int militaryTimeInt(){
		int milTime = 0;
		if (this.hour < 12 && this.PM){
			milTime += (this.hour + 12) * 100;
		}
		else if (this.hour == 12 && !this.PM) {
			milTime += 0;
		}
		else {
			milTime += this.hour * 100;
		}
		
		milTime += this.minute;		
		
		return milTime;
	}

	/**
	 * Returns a string of the time without the AM/PM.
	 */
	public String getTime()
	{
		return String.format("%d:%02d", this.hour, this.minute);
	}
}