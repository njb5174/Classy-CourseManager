package classy;

/**
 * Name:  Nathan Bialas
 * Section:  1
 * Program: Classy Project
 * Date:  9/24/2012
 */
/**
 * This is main class function for the "Classy Project"
 * It contains instances of various subclasses, as well as methods for calculating 
 * current grades from stored assignments
 *
 * @author Nathan Bialas
 * @version 1.0:  9/25/2012
 * 
 * 11/5/2012, added getters
 */
 public class Course
 {
	private String courseTitle; // the name of the class
	private String sectionNumber;
	private DayTimeAndLocation[] courseTimes; // contains the start and end time, and the day(s) of the class
	private Instructor[] teachers = new Instructor[3]; //contains name, office hours, email address and TA status of a class professor
	private AssignmentType[] work; // an array to be filled with assignments
	private double grade; //grade calculated via method from Assignments array
	private boolean workInstantiated;
        private int numberOfInstructors;
	
	
	//default constructor
	/*
		@param courseTitle - the name of the course
		@param sectionNumber - the sectionNumber of the course
		@param courseTime - start/end times of the class
		
	
	*/
	public Course( String courseTitle, String sectionNumber, DayTimeAndLocation[] courseTimes)
	{
	
            this.courseTitle = courseTitle; // name of class
            this.sectionNumber = sectionNumber; //section number of class
            this.courseTimes = courseTimes; // time and days of week class takes place
            this.workInstantiated = false;
            this.numberOfInstructors = 0;
            this.grade = 0;
	}
        
        public Course(String courseTitle, String sectionNumber)
        {
            this.courseTitle = courseTitle;
            this.sectionNumber = sectionNumber;
            
            this.workInstantiated = false;
            this.numberOfInstructors = 0;
            this.grade = 0;
        }
	
	//init constructor
	/*
		@param courseTitle - the name of the course
		@param sectionNumber - the sectionNumber of the course
		@param courseTime - start/end times of the class
		@param teachers - the course instructor and their location/officehours/email
		@param work - an array filled by the user with their assignments
	
	*/
	public Course ( String courseTitle, String sectionNumber, DayTimeAndLocation[] courseTimes, Instructor[] teachers, AssignmentType[] work)
	{
		//need constructors
		this.courseTitle = courseTitle; // name of class
		this.sectionNumber = sectionNumber; //section number of class
		this.courseTimes = courseTimes; // time and days of week class takes place
		this.teachers = teachers;
                this.numberOfInstructors = teachers.length;
		this.work = work;
		
		this.grade = calculateGrade();
	}
	
	
	/**
	 * Sets the teachers/instructor associated with the course.
	 *
	 * @param teachers the instructors of the course.
	 */
	public void setInstructor(Instructor[] teachers)
	{
            this.teachers = teachers;
	}
	
	public void setInstructor(Instructor instructor, int index) {
            teachers[index] = instructor;
	}
	
	/**
	 * Sets the work associated with the course.
	 *
	 * @param work - the graded work from the course.
	 */
	public void setWork(AssignmentType[] work)
	{
            this.work = work;
            this.workInstantiated = true;
	}
        
        public void setDayTimeAndLocation(DayTimeAndLocation[] timePlaces)
        {
            this.courseTimes = timePlaces;
        }
        
        public int getNumberOfInstructors()
        {
            return this.numberOfInstructors;
        }
	
	
	/**
	 * Sets the grade associated with the Course.
	 *
	 * Pre:  The course already has its work initialized
	 */
	public void setGrade()
	{
            this.grade = calculateGrade();
	}
	
	//Sets Class's grade parameter
	//PRE:  Class's Assignments array parameter has at least one initialized value
	public double calculateGrade()
	{
            double totalGrade = 0;
		
            if (work != null) {
                for( AssignmentType stuff: work)
                {
                    totalGrade += stuff.calculateWeightedGrade();
                }
            }
		
            return totalGrade;
	}
        
    //Returns a String containing the "courseTitle" data member
    public String getName()
    {
        return this.courseTitle;
    }
    
    public String getInstructorString()
    {
       String text = "";
       int count = 0;
       while(count < teachers.length)
       {
           if (teachers[count] != null) {
                if(teachers[count].getIsTA() == false)
                {
                    text += "Professor: " + this.teachers[count].toString()+"\n";
                }
                else
                {
                    text += "\nTA: " + this.teachers[count].toString()+"\n";
                }
           }
           count++;
       }
       
       
       return text;
    }
    
    public Instructor[] getInstructors() {
    	return this.teachers;
    }
    
    public AssignmentType[] getWork() {
    	return this.workInstantiated ? this.work : null;
    }
    
   public Time getStartTime(String day)
    {
        
        for(DayTimeAndLocation array: courseTimes)
        {
            if (array.getDay() == day)
            {
                return array.getStartTime();
            }
        }
        
        return (new Time(-1, -1, false));
    }
    
    public Time getEndTime(String day)
    {
        for(DayTimeAndLocation array: courseTimes)
        {
            if (array.getDay() == day)
            {
                return array.getEndTime();
            }
        }
        
        return (new Time(-1, -1, false));
    }
	
    public String getSectionNumber(){
    	return this.sectionNumber;
    }
    
    public DayTimeAndLocation[] getCourseTimes(){
    	return this.courseTimes;
    }
    
    public AssignmentType[] getAssignmentTypes()
    {
        if(this.workInstantiated)
        {
            return this.work;
        }
        
        else
        {
            return new AssignmentType[0];
        }
    }
    
    public void addAssignment(int assignmentTypeIndex, Assignment newAssignment)
    {
        this.work[assignmentTypeIndex].addAssignment(newAssignment);
    }
    
	//Creates and Returns a string containing information about all class data members
    
    public String toString ()
    {
	
        String dataMembers = "";
        dataMembers += ("Course:  " + courseTitle + "\nSection:  " + sectionNumber + "\n\n");
		
		//output every stored time for the course
        for(DayTimeAndLocation calendar: courseTimes)
        {
            dataMembers += (calendar.toString() + "\n\n");
        }
		             
        dataMembers += this.getInstructorString();
                
		
        dataMembers += "\nGrade:  " + this.calculateGrade();
		
        return dataMembers;
    }
        
        
	
	
 }