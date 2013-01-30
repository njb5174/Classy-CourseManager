/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classy;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Nate
 */
public class CalendarPanel extends JPanel implements ActionListener
{
    public Course[] coursesList;
    boolean drawCourse[];

    public JButton[] courseButtonArray;

    private String[] courseNames;
    
    private DayAndTime[] courseButtonDayAndTime;   
    
    public EastPanel eastPanel;
    
    public CalendarPanel(Course[] courseList)
    {
        super();
        this.coursesList = courseList;
        
        courseNames = getCourseNames();
        
        int count = 0;
        
        for(int i = 0; i < coursesList.length; i++)
        {
            count += coursesList[i].getCourseTimes().length;
        }

        courseButtonArray = new JButton[count];
        courseButtonDayAndTime = new DayAndTime[count];
        
        count = 0;
        
        for(int i = 0; i < coursesList.length; i++)
        {
           count += coursesList[i].getNumberOfInstructors();
        }
        
        updateButtons();
        
        this.setLayout(null);
        this.setPreferredSize(new Dimension(828, 1442));
        this.setVisible(true);
    }
    
    public void updateButtons() {
    	int count = 0;
    	for(int i = 0; i < coursesList.length; i++)
        {
            for(int j = 0; j < coursesList[i].getCourseTimes().length; j++)
            {
                courseButtonArray[count] = new JButton("<html>" + coursesList[i].getName() + "<br/>" + coursesList[i].getCourseTimes()[j].getStartTime().toString() + "-" + coursesList[i].getCourseTimes()[j].getEndTime().toString() + "</html>");
                courseButtonArray[count].setHorizontalAlignment(SwingConstants.CENTER);
//                courseButtonArray[count].setLayout(new FlowLayout());
//                courseButtonArray[count].add(new JLabel(coursesList[i].getName()));
//                courseButtonArray[count].add(new JLabel
//                        (
//                        (coursesList[i].courseTimes[j].getStartTime().toString())
//                        + " - " + coursesList[i].courseTimes[j].getEndTime().toString())
//                        
//                        );
                courseButtonArray[count].setName(coursesList[i].getName());

                courseButtonArray[count].addActionListener(this);
                
                courseButtonDayAndTime[count] = new DayAndTime(
                        coursesList[i].getStartTime(coursesList[i].getCourseTimes()[j].getDay()), 
                        coursesList[i].getEndTime(coursesList[i].getCourseTimes()[j].getDay()),
                        coursesList[i].getCourseTimes()[j].getDay());
                
                count++;
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        int width = getWidth();
        int height = getHeight();
        
        BackEnd.DrawCalendar(g, height, width);        
        
        if (!compareStringArrays(courseNames, getCourseNames())){
        	updateButtons();
        	courseNames = getCourseNames();
        }
        
        for(int i = 0; i < courseButtonArray.length; i++)
        {
            this.add(courseButtonArray[i]);
            courseButtonArray[i].setSize(width / 6, 
                    BackEnd.calculateButtonHeight(
                    courseButtonDayAndTime[i].getStartTime(),courseButtonDayAndTime[i].getEndTime()));
            courseButtonArray[i].setLocation(
                    BackEnd.calculateXLocation(courseButtonDayAndTime[i].getDay()),
                    BackEnd.calculateYLocation(courseButtonDayAndTime[i].getStartTime())
                    );
        }     
    }
    
//    public void repaint(Course[] courses) {
//    	this.coursesList = courses;
//    	repaint();
//    }
    
    public void setEastPanel(Course[] courses)
    {
        this.eastPanel = new EastPanel(courses);
    }
    
    public EastPanel getEastPanel()
    {
        return this.eastPanel;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        int buttonCounter = 0;
        int courseCounter = 0;

        
        while (e.getSource() != courseButtonArray[buttonCounter])
        {
            
            buttonCounter++;
        }
        
        String name = courseButtonArray[buttonCounter].getName();
        
        while( name != coursesList[courseCounter].getName() && courseCounter < coursesList.length)
        {
            courseCounter++;
        }
        
        if(courseCounter < coursesList.length)
        {
            eastPanel.namesOfCourses.setSelectedIndex(courseCounter+1);

        }

    }
    
    public String[] getCourseNames() {
    	String[] names = new String[coursesList.length];
    	for (int i = 0; i < coursesList.length; i++) {
    		names[i] = new String(coursesList[i].getName());
    	}
    	
    	return names;
    }
    
    public boolean compareStringArrays(String[] strings1, String[] strings2) {
    	int count;
    	for (count = 0; count < strings1.length && count < strings2.length; count++) {
    		if (strings1[count].compareTo(strings2[count]) != 0) {
    			return false;
    		}
    	}
    	if (count != strings1.length || count != strings2.length) {
    		return false;
    	}
    	return true;
    }
    
}
