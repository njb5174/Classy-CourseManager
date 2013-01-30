/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.applet.Applet;
import java.sql.*;


/**
 *
 * @author Nate
 */
public class ClassyGUI extends Applet implements ChangeListener
{
    BorderLayout layout;
    
    FrontPanel frontPanel;
    AddCourse addCoursePanel;
    JTabbedPane tabbedPane;

    private Course[] courseList;
    
    private int currentTab;
 
    
    @Override
    public void init() 
    {
        try
        {
            
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                    "root",
                    "root");

            Statement stmt = myConnection.createStatement();
           
            String sql = "Select * from course ";
            ResultSet results = stmt.executeQuery("Select COUNT(*) Count from Course");
            results.next();
            
            
            int resultCount = Integer.parseInt(results.getString("Count"));
            int courseCount = 0;
            
            courseList = new Course[resultCount];

            results = stmt.executeQuery(sql);
            
            
            while(results.next())
            {
              
                courseList[courseCount] = new Course(results.getString("COURSE_NAME"), 
                        results.getString("Section"));
                 courseCount++;
            }
            
            
            for(int i = 0; i < courseList.length; i++)
            {
                results = stmt.executeQuery("Select COUNT(*) Count from DAY_TIME_LOCATION where COURSE_NAME = '" + courseList[i].getName() + "'");
                results.next();
                DayTimeAndLocation[] daysTimesLocations = new DayTimeAndLocation[Integer.parseInt(results.getString("Count"))];
                
                sql = "Select * from DAY_TIME_LOCATION where COURSE_NAME = '" + courseList[i].getName() + "'";
                results = stmt.executeQuery(sql); 
            
                int dayTimeCount = 0;
                
                while(results.next())
                {
                    Time startTime = new Time(results.getInt("START_HOUR"), 
                            results.getInt("START_MIN"), results.getBoolean("IS_PM1"));
                    Time endTime = new Time(results.getInt("END_HOUR"), 
                            results.getInt("END_MIN"), results.getBoolean("IS_PM2"));
                    daysTimesLocations[dayTimeCount] = new DayTimeAndLocation(startTime, endTime, 
                            results.getString("Day"), results.getString("Location"));

                    dayTimeCount++;
                }

                courseList[i].setDayTimeAndLocation(daysTimesLocations);
                
            
                int instructorCount = 0;
                sql = "Select * from Instructors where COURSE_NAME = '" + courseList[i].getName()+ "'"
                        + "and IS_TA = false";
                results = stmt.executeQuery(sql); 
                
                if (results.next()){
                    courseList[i].setInstructor(new Instructor(results.getString("Name"), results.getBoolean("IS_TA")), instructorCount);
                            courseList[i].getInstructors()[instructorCount].setEmail(results.getString("Email"));
                            courseList[i].getInstructors()[instructorCount].setOfficeLocation(results.getString("Office"));
                }
                
                instructorCount++;



                sql = "Select * from Instructors where COURSE_NAME = '" + courseList[i].getName()+ "'"
                        + "and IS_TA = true";
                results = stmt.executeQuery(sql); 

                while(results.next())
                {
                    courseList[i].setInstructor(new Instructor(results.getString("Name"), results.getBoolean("IS_TA")), instructorCount);
                    courseList[i].getInstructors()[instructorCount].setEmail(results.getString("Email"));
                    courseList[i].getInstructors()[instructorCount].setOfficeLocation(results.getString("Office"));


                    instructorCount++;
                }


                for(int j = 0; j < instructorCount; j++)
                    {
                        if (courseList[i].getInstructors()[j] != null){
                            results = stmt.executeQuery("Select COUNT(*) Count from DAY_TIME_LOCATION where INSTRUCTOR_NAME = '"
                                    + courseList[i].getInstructors()[j].getName() + "'");
                            results.next();
                            dayTimeCount = results.getInt("Count");

                            sql = "Select * from DAY_TIME_LOCATION where INSTRUCTOR_NAME = '" 
                                    + courseList[i].getInstructors()[j].getName() + "'";

                            results = stmt.executeQuery(sql);


                            DayAndTime[] daysTimes = new DayAndTime[dayTimeCount];
                            dayTimeCount = 0;

                            while(results.next())
                            {
                                String day = results.getString("Day");
                                Time startTime = new Time(results.getInt("START_HOUR"), 
                                        results.getInt("START_MIN"), results.getBoolean("IS_PM1"));
                                Time endTime = new Time(results.getInt("END_HOUR"), 
                                        results.getInt("END_MIN"), results.getBoolean("IS_PM2"));
                                daysTimes[dayTimeCount] = new DayAndTime(startTime, endTime, day);

                                dayTimeCount++;
                            }

                            courseList[i].getInstructors()[j].setOfficeHours(daysTimes);
                        }
                    }


                results = stmt.executeQuery("Select COUNT(*) Count from ASSIGNMENT_TYPES where COURSE_NAME = '" + courseList[i].getName() + "'");
                results.next();

                AssignmentType[] types = new AssignmentType[Integer.parseInt(results.getString("COUNT"))];

                sql = "Select * from ASSIGNMENT_TYPES where COURSE_NAME = '" + courseList[i].getName()+ "'"
                        + " and IS_EXAM = 'false'";
                results = stmt.executeQuery(sql);

                int assCount = 0;


                while(results.next())
                {
                    types[assCount] = new AssignmentType(results.getString("ASSIGNMENT_TYPE"), results.getDouble("Weight"));
                    assCount++;
                }

                sql = "Select * from ASSIGNMENT_TYPES where COURSE_NAME = '" + courseList[i].getName()+ "'"
                        + " and IS_EXAM = 'true'";
                results = stmt.executeQuery(sql);

                while(results.next())
                {
                    types[assCount] = new Exam(results.getString("ASSIGNMENT_TYPE"), results.getDouble("Weight"));
                    assCount++;
                }

                courseList[i].setWork(types);

                for(int j = 0; j < courseList[i].getAssignmentTypes().length; j++)
                {
                    sql = "Select * from Assignments where COURSE_NAME = '" + courseList[i].getName() + "' and ASSIGNMENT_TYPE = '" 
                            + courseList[i].getAssignmentTypes()[j].getType()+ "'";

                    results = stmt.executeQuery(sql);

                    assCount = 0;
                    while(results.next())
                    {                                   
                        courseList[i].getAssignmentTypes()[j].addAssignment(
                                new Assignment(results.getString("NAME_OF_ASSIGNMENT"), 
                                new Date(results.getInt("YEAR_DUE"), results.getInt("MONTH_DUE"), 
                                results.getInt("DAY_DUE"))));
                        courseList[i].getAssignmentTypes()[j].
                                getAssignments()[assCount].setGrade(results.getDouble("Grade"));

                        assCount++;
                    }
                }
            }
            
            results.close();

        }
        
        catch(SQLException ex)
        {
            System.out.println("SQL Exception extracting database records.");
            System.out.println(ex.getMessage());
            
            System.out.println("Program will continue as if no information has been extracted.");
            courseList = new Course[0];
            
        }
        
   catch(Exception ex)
   {
       System.out.println("Generic Exception extracting database records.");
       System.out.println(ex.getMessage());
       
       System.out.println("Program will continue as if no information has been extracted.");
       courseList = new Course[0];
   }
        
    int tabIndex = 0;
    if (courseList.length == 0)
    {
        courseList= new Course[0];
        tabIndex = 1;
    }

    tabbedPane = new JTabbedPane();
    
    frontPanel = new FrontPanel(courseList);
   addCoursePanel = new AddCourse(courseList); 

   frontPanel.setSize(1200, 700);
   tabbedPane.addTab("Calendar", null, frontPanel, "View Calendar Panel");
   tabbedPane.addTab("Add/Edit Courses", null, addCoursePanel, "View Add/Edit Courses Panel");
   
   tabbedPane.addChangeListener(this);

   currentTab = 0;

   


   this.setSize(1200, 800);
   layout = new BorderLayout();
   this.setLayout(layout); 


   add(tabbedPane, layout.CENTER);
   
   tabbedPane.setSelectedIndex(tabIndex);
   


}

    @Override
    public void stateChanged(ChangeEvent e) {
        if (e.getSource() == tabbedPane) {
            if (tabbedPane.getSelectedIndex() == 0 && currentTab != 0) {
                currentTab = 0;
                tabbedPane.removeAll();
                frontPanel = new FrontPanel(addCoursePanel.returnCourses());
                tabbedPane.addTab("Calendar", null, frontPanel, "View Calendar Panel");
                tabbedPane.addTab("Add/Edit Courses", null, addCoursePanel, "View Add/Edit Courses Panel");
                tabbedPane.addChangeListener(this);
                repaint();
                validate();
                }
            else if (tabbedPane.getSelectedIndex() == 1){
                currentTab = 1;
                }
        }
		
    }
}
    
