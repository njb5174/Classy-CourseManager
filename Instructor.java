package classy;

/**
 * Name:  Robert Dick
 * Section:  1
 * Program: Classy Project
 * Date:  10/6/2012
 */
 
/**
 * This class stores information about an instructor and has methods for setting and getting the information.
 * 
 * @author Robert Dick
 * @version 1.1:  10/6/2012
 */
public class Instructor
{
	private String name;				// the professor's name
	private String officeLocation;		// the office location
	private DayAndTime[] officeHours;	// the office hours
	private String email;				// the professor's email address
	private boolean isTA;				// true indicates that the instructor is a TA
	
	public Instructor(String name, boolean isTA)
	{
		// PRE: name and isTA are initialized
		// POST: creates an Instructor with member name set to name, blank officeLocation and email, empty officeHours, and member isTA set to isTA
		this(name, "", new DayAndTime[1], "", isTA);
	}
	
	public Instructor(String name, String officeLocation, DayAndTime[] officeHours, String email, boolean isTA)
	{
		// PRE: name, officeLocation, officeHours, email, and isTA are initialized
		// POST: an Instructor is created with name name, officeLocation officeLocation, officeHours officeHours, email email and isTA isTA
		this.name = name;
		this.officeLocation = officeLocation;
		this.officeHours = officeHours;
		this.email = email;
		this.isTA = isTA;
	}
	
	/**
	* Sets the office location.
	*
	* @param location the location
	*/
	public void setOfficeLocation(String location)
	{
		// PRE: location is initialized
		// POST: The member officeLocation is set to location.
		this.officeLocation = location;
	}
	
	/**
	* Sets the office hours.
	*
	* @param hours the array of office hours
	*/
	public void setOfficeHours(DayAndTime[] hours)
	{
		// PRE: hours is initialized
		// POST: The member officeHours is set to hours.
		this.officeHours = hours;
	}
	
	/**
	* Sets the instructor's email address.
	*
	* @param email the email address
	*/
	public void setEmail(String email)
	{
		// PRE: email is initialized
		// POST: The member email is set to email.
		this.email = email;
	}
	
	/**
	* Returns the insctructor's name.
	*
	*/
	public String getName()
	{
		// POST: The member name is returned.
		return this.name;
	}
	
	/**
	* Returns the office location.
	*
	*/
	public String getOfficeLocation()
	{
		// POST: The member officeLocation is returned.
		return this.officeLocation;
	}
	
	/**
	* Returns the the office hours as an array.
	*
	*/
	public DayAndTime[] getOfficeHours()
	{
		// POST: The member officeHours is returned.
		return this.officeHours;
	}
	
	/**
	* Returns the insctructor's email address.
	*
	*/
	public String getEmail()
	{
		// POST: The member email is returned.
		return this.email;
	}
	
	/**
	* Returns whether the instructor is a TA or not.
	*
	*/
	public boolean getIsTA()
	{
		// POST: The member isTA is returned.
		return this.isTA;
	}
	
	/**
	* Returns a string representation of an instructor.
	*
	*/
	public String toString()
	{
		// POST: A string containing all data members is returned.
		String info = "";
		info += this.name + "\n";
		info += "Office Hours\nLocation: " + this.officeLocation + "\n";
		for (DayAndTime time: this.officeHours)
		{
                    if (time != null)
			info += time + "\n";
		}
		info += "Email: " + this.email;
		return info;
	}
}