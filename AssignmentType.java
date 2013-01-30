/**
 * Name:  Robert Dick
 * Section:  1
 * Program: Classy Project
 * Date:  10/6/2012
 */
 package classy;
 
 import java.util.ArrayList;
 
/**
 * This class represents a type of assignment and can calculate a weighted grade for all assignments of this type.
 * 
 * @author Robert Dick
 * @version 1.1:  10/6/2012
 */
 public class AssignmentType
 {
	protected final double WEIGHT;		// the grade weighting for this type of assignment
	protected Assignment[] assignments;	// collection of graded assignments of this type
	protected int numAssignments;		// the current number of assignments
	protected String type;				// the assignment type
	
	public AssignmentType(String type, double weight)
	{
		// PRE: type is initialized; 0.0 < weight <= 100.0
		// POST: An assignmentType is created with type type, WEIGHT weight, memory for 10 assignments, and numAssignments set to 0.
		this.type = type;
		this.WEIGHT = weight;
		this.assignments = new Assignment[10];
		this.numAssignments = 0;
	}
	
	/**
	 * Calculates the combined weighted grade of all assignments of this type.
	 */
	public double calculateWeightedGrade()
	{
		// POST: Returns the calculated weighted grade.
		double grade = 0;
		if (this.numAssignments > 0)
		{
			for (int i = 0; i < this.numAssignments; i++)
			{
				grade += this.assignments[i].getGrade();
			}
			grade = grade * this.WEIGHT / this.numAssignments;
		}
		return grade;
	}
	
	/**
	 * Adds an assignment to the array of assignments.
	 *
	 * @param assignment the Assignment to add to the member array assignments
	 */
	public void addAssignment(Assignment assignment)
	{
		// PRE: assignment is initialized
		// POST: assignment is added to the end of the member array assignents
		if (this.numAssignments >= this.assignments.length)
		{
			Assignment[] temp = new Assignment[this.assignments.length + 5];
			for (int i = 0; i < this.numAssignments; i++)
			{
				temp[i] = this.assignments[i];
			}
			this.assignments = temp;
		}
		this.assignments[this.numAssignments] = assignment;
		this.numAssignments++;
	}
	
	/**
	 * Returns the grade of the assignment at assignmentIndex
	 *
	 * @param asssignmentIndex the index of the assignment
	 */
        
        public double getWeight()
        {
            return this.WEIGHT;
        }
	public double getGrade(int assignmentIndex)
	{
		// PRE: 0 <= assignmentIndex < numAssignments
		// POST: The assignment at assignmentIndex is returned.
		return this.assignments[assignmentIndex].getGrade();
	}
        
        public Assignment[] getAssignments()
        {
            return this.assignments;
        }
        
        public String[] getAssignmentNames()
        {
            String[] toReturn = new String[getNumAssignments()];
            
            for(int i = 0; i < getNumAssignments()-1; i++)
            {
                toReturn[i] = this.assignments[i].getTitle();
            }
            
            return toReturn;
        }
        
        public int getNumAssignments()
        {
            return this.numAssignments;
        }
        
        public void deleteAssignment(int index)
        {

            Assignment[] temp = new Assignment[this.assignments.length];

            int count = 0;
            int count2 = 0;
                      
            while(count < this.numAssignments)
            {
                if(count2 == index)
                {
                    count2++;     
                }
                
                if(count2 == this.numAssignments)
                {
                    break;
                }
                
                temp[count] = this.assignments[count2];
                
                count++;
                count2++;
            }


            this.assignments = temp;
            this.numAssignments--;   
            
        }
        
        public void setAssignments(Assignment[] assignments)
        {
            this.assignments = assignments;
            this.numAssignments = assignments.length;
        }
	
	/**
	 * Returns the type.
	 */
	public String getType()
	{
		// POST: The member type is returned.
		return this.type;
	}
 }