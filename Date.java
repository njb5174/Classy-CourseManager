package classy;

/**
 * Name:  Robert Dick
 * Section:  1
 * Program: Classy Project
 * Date:  10/6/2012
 */

import java.util.GregorianCalendar;

/**
 * This class represents a date.
 * 
 * @author Robert Dick
 * @version 1.1:  10/6/2012
 */
public class Date extends GregorianCalendar
{	
	public Date(int year, int month, int dayOfMonth)
	{
		// PRE: year is initialized; 1 <= month <= 12; 1 <= dayOfMonth <= 31
		// POST: The superclass constructor is called, initializing members year, month, and dayOfMonth to the para
		super(year, month-1, dayOfMonth);		// subtract 1 for month because it is 0-based
	}
	
	/**
	 * Resets the date to the parameter values.
	 *
	 * @param year the year part of the date
	 * @param month the month part of the date
	 * @param day the day part of the date
	 */
	public void resetDate(int year, int month, int dayOfMonth)
	{
		// PRE: year is initialized; 1 <= month <= 12; 1 <= dayOfMonth <= 31
		// POST: The date is reset to the the parameter values.
		super.set(year, month-1, dayOfMonth);		// call the set method on the Calendar class; subtract 1 from month because it is 0-based
	}
	
	/**
	 * Returns the year.
	 */
	public int getYear()
	{
		// POST: The member year is returned.
		return super.get(this.YEAR);
	}
	
	/**
	 * Returns the month.
	 */
	public int getMonth()
	{
		// POST: The member month is returned.
		return super.get(this.MONTH) + 1;		// add 1 because month is 0-based
	}
	
	/**
	 * Returns the day of the month.
	 */
	public int getDayOfMonth()
	{
		// POST: The member dayOfMonth is returned.
		return super.get(this.DAY_OF_MONTH);
	}
	
	/**
	 * Returns a string representation of the date.
	 */
	@Override
	public String toString()
	{
		// POST: A string representation of date is returned as 'Month/Day/Year'
		return this.getMonth() + "/" + this.getDayOfMonth() + "/" + this.getYear();
	}
}