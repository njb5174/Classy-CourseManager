package classy;

/**
 * Name:  Robert Dick
 * Section:  1
 * Program: Classy Project
 * Date:  10/6/2012
 */

/**
 * This class represents an exam, which is an AssignmentType with only one assignment, as well as location and time.
 * 
 * @author Robert Dick
 * @version 1.1:  10/6/2012
 */
public class Exam extends AssignmentType
{
	private String location;	// the location of the exam
	private Time startTime;		// the start time of the exam
	private Time endTime;		// the end time of the exam
	
	public Exam(String examName, double weight, Date date, String location, Time startTime, Time endTime)
	{
		// PRE: 0.0 < weight <= 100.0; examNumber > 0; location is initialized; startTime is before endTime
		// POST: An Exam is created with type "Exam", WEIGHT weight, memory for 1 assignment, a default assignment with title "Exam [examNumber]", location location, startTime startTime, and endTime endTime
		super(examName, weight);
		this.assignments = new Assignment[1];		// For an exam, we don't need multiple assignments; overrite the array
		this.assignments[0] = new Assignment(examName, date);	// Create the assignment details of the exam
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
                super.numAssignments = 1;
	}
	
	public Exam(String examName, double weight)
	{
		this(examName, weight, null, null, null, null);
	}
	
	/**
	 * Returns the calculated weighted grade.
	 */
	@Override
	public double calculateWeightedGrade()
	{
		// POST: The weighted grade is returned.
		return this.getGrade() * this.WEIGHT;
	}
	
	/**
	 * Unused superclass method.  Exam can only have one assignment, created in the instructor.
	 */
	@Override
	public void addAssignment(Assignment assignment)
	{
		// POST: UnsupportedOperationException is thrown.
		//System.out.println("An assignment cannot be added.  Exams only have one assignment");		// An exam cannot have multiple assignments.
            this.assignments[0] = assignment;
	}
	
	/**
	 * Sets the assignment grade to grade.
	 *
	 * @param grade the grade to set
	 */
	public void setGrade(double grade)
	{
		// PRE: 0 <= grade <= 100
		// POST: The assignment's grade is set.
		this.assignments[0].setGrade(grade);
	}
	
	/**
	 * Returns the grade of the Exam assignment.
	 */
	public double getGrade()
	{
		// POST: Return the assignment's grade.
		return super.getGrade(0);
	}
	
	/**
	 * Returns a string representation of the Exam
	 */
	public String toString()
	{
		// POST: Returns a string representation of all data members, line by line.
		return String.format(this.assignments[0].getTitle() + "\n"
			+ this.assignments[0].getDate() + "\n"
			+ this.startTime + "-" + this.endTime + "\n"
			+ this.location);
	}
}