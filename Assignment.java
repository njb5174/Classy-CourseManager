/**
 * Name:  Robert Dick
 * Section:  1
 * Program: Classy Project
 * Date:  10/6/2012
 */
 package classy;
/**
 * This class stores data related to an assignment and has methods for setting and getting the data.
 * 
 * @author Robert Dick
 * @version 1.1:  10/6/2012
 */
public class Assignment
{
	private Date date;		// The date of the assignment
	private String title;	// The title of the assignment
	private double grade;	// The user's grade on the assignment
	
	public Assignment(String title, Date date)
	{
		// PRE: title and date are initialized
		// POST: An assignment is created with member title set to title, member date set to date, and a default grade of 0.
		this.title = title;
		this.date = date;
		this.grade = 0.0;
	}
        
        public void setDate(Date newDate)
        {
            this.date = newDate;
        }
        
        public void setName(String name)
        {
            this.title = name;
        }
	
	/**
	 * Sets the grade associated with the assignment.
	 *
	 * @param grade the grade received on the assignment.
	 */
	public void setGrade(double grade)
	{
		// PRE: 0 <= grade <= 100
		// POST: The member grade is set to grade.
		this.grade = grade;
	}
	
	/**
	 * Returns the title of the assignment.
	 */
	public String getTitle()
	{
		// POST: The member title is returned.
		return this.title;
	}
        
        
	
	/**
	 * Returns the grade received on the assignment.
	 */
	public double getGrade()
	{
		// POST: The member grade is returned.
		return this.grade;
	}
	
	/**
	 * Returns the date of the assignment.
	 */
	public Date getDate()
	{
		// POST: The member date is returned.
		return this.date;
	}
}