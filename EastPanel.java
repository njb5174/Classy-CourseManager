/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package classy;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Nate
 */
public class EastPanel extends JPanel implements ItemListener, ActionListener
{
    Course[] courseArray;
    String[] courseNames;
    String[] assignmentTypeStrings;
    String[] assignmentStrings;
    

    public boolean drawCourses;
    public JComboBox namesOfCourses;
    private JLabel courseInfoLabel;
    private JScrollPane pane;
    public JTextArea courseInfoDisplay;
    
    private JLabel namesOfAssignmentTypesLabel;
    private JLabel namesOfAssignmentsLabel;
    public JComboBox namesOfAssignmentTypes;
    public JComboBox namesOfAssignments;

    private JLabel typeWeightLabel;
    
    private JLabel newAssignmentNameLabel;
    private JLabel dateDueLabel;
    private JLabel gradeLabel;
    
    public JTextField newAssignmentField;
    public JTextField dateDueField;
    public JTextField gradeField;
    
    private JButton enterAssignment;
    private JButton saveAssignment;
    private JButton deleteAssignment;
    private JLabel invalidAssignment;

    
    
    
    FlowLayout layout = new FlowLayout();
    
    public EastPanel(Course[] courseArray)
    {
        this.setLayout(layout);
        this.setPreferredSize(new Dimension(250, 600));
        this.setVisible(true);
        
        this.courseArray = courseArray;

        
        courseNames = new String[courseArray.length+1];
        courseNames[0] = "";
        for(int i = 1; i < courseArray.length+1; i++)
        {
            courseNames[i] = courseArray[i-1].getName();
        }
        
        
        
        courseInfoLabel = new JLabel("      Course Information      ");
        
        namesOfCourses = new JComboBox(courseNames);
        namesOfCourses.setPrototypeDisplayValue("XXXXXXXXXXXXXX");
       
        namesOfCourses.addItemListener(this);
        
        
        courseInfoDisplay = new JTextArea("", 60, 20);
        courseInfoDisplay.setEditable(false);
        
        pane = new JScrollPane(courseInfoDisplay);
        pane.setPreferredSize(new Dimension(240,250));
        
        namesOfAssignmentTypesLabel = new JLabel("        Assignment Type:      ");
        
        namesOfAssignmentTypes = new JComboBox();
        namesOfAssignmentTypes.setPrototypeDisplayValue("XXXXXXXXXXXXX");
        namesOfAssignmentTypes.addItemListener(this);
        
        typeWeightLabel = new JLabel();
        
        
        namesOfAssignmentsLabel = new JLabel("Assignments: ");
        namesOfAssignments = new JComboBox();
        namesOfAssignments.setPrototypeDisplayValue("XXXXXXXXXXXXXX");
        namesOfAssignments.addItemListener(this);
        
        newAssignmentNameLabel = new JLabel("Name:          ");
        dateDueLabel = new JLabel("Due Date:    ");
        gradeLabel = new JLabel("Grade:          ");
        
        newAssignmentField = new JTextField("", 10);
        dateDueField = new JTextField("", 10);
        dateDueField.setToolTipText("Valid Input:  MM/DD/YEAR");
        gradeField = new JTextField("", 10);
        
        enterAssignment = new JButton("Submit New Assignment");
        enterAssignment.setVisible(false);
        enterAssignment.addActionListener(this);
        
        saveAssignment = new JButton("Save Changes");
        saveAssignment.setVisible(false);
        saveAssignment.addActionListener(this);
        
        deleteAssignment = new JButton("Delete Assignment");
        deleteAssignment.setVisible(false);
        deleteAssignment.addActionListener(this);
        
        invalidAssignment = new JLabel("Invalid Assignment Input");
        invalidAssignment.setVisible(false);

        this.add(namesOfCourses);
        this.add(courseInfoLabel);
        this.add(pane);
        this.add(namesOfAssignmentTypesLabel);
        namesOfAssignmentTypesLabel.setVisible(false);
        
        this.add(namesOfAssignmentTypes);
        namesOfAssignmentTypes.setVisible(false);
        
        this.add(typeWeightLabel);
        typeWeightLabel.setVisible(false);
        
        this.add(namesOfAssignmentsLabel);
        namesOfAssignmentsLabel.setVisible(false);
        
        this.add(namesOfAssignments);
        namesOfAssignments.addItemListener(this);
        namesOfAssignments.setVisible(false);

        
        this.add(newAssignmentNameLabel);
        newAssignmentNameLabel.setVisible(false);
        
        this.add(newAssignmentField);
        newAssignmentField.setVisible(false);
        
        this.add(dateDueLabel);
        dateDueLabel.setVisible(false);
        
        this.add(dateDueField);
        dateDueField.setVisible(false);
        
        this.add(gradeLabel);
        gradeLabel.setVisible(false);
        
        this.add(gradeField);
        gradeField.setVisible(false);
        
        this.add(enterAssignment);
        this.add(saveAssignment);
        this.add(deleteAssignment);
        
    }
    
    public void itemStateChanged(ItemEvent e)
    {
        
        if (e.getSource() == namesOfCourses)
        {
            if(namesOfCourses.getSelectedIndex() == 0)
            {
                courseInfoDisplay.setText("");
                
                namesOfAssignmentTypesLabel.setVisible(false);
                typeWeightLabel.setVisible(false);
                namesOfAssignmentTypes.setVisible(false);
                namesOfAssignmentsLabel.setVisible(false);
                namesOfAssignments.setVisible(false);
            }
            
            else
            {
                typeWeightLabel.setVisible(false);
                namesOfAssignmentTypes.setVisible(false);
                namesOfAssignmentsLabel.setVisible(false);
                namesOfAssignments.setVisible(false);
                newAssignmentNameLabel.setVisible(false);
                newAssignmentField.setVisible(false);
                dateDueLabel.setVisible(false);
                dateDueField.setVisible(false);
                gradeLabel.setVisible(false);
                gradeField.setVisible(false);
                enterAssignment.setVisible(false);
                saveAssignment.setVisible(false);
                deleteAssignment.setVisible(false);
                
                courseInfoDisplay.setText(courseArray[namesOfCourses.getSelectedIndex()-1].toString());
                
                assignmentTypeStrings = new String[courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes().length+1];
                assignmentTypeStrings[0] = "";
                
                for(int i = 1; i < courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes().length+1; i++)
                {
                    assignmentTypeStrings[i] = courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[i-1].getType();
                }
                
                namesOfAssignmentTypes.setModel(new DefaultComboBoxModel(assignmentTypeStrings));
                namesOfAssignmentTypesLabel.setVisible(true);
                namesOfAssignmentTypes.setVisible(true);
                
                
                namesOfAssignmentsLabel.setVisible(false);
                namesOfAssignments.setVisible(false);
                
                
            }
            
            
          
        }
        
        if (e.getSource() == namesOfAssignmentTypes)
        {
            if(namesOfAssignmentTypes.getSelectedIndex() == 0)
            {
                namesOfAssignmentsLabel.setVisible(false);
                namesOfAssignments.setVisible(false);
                typeWeightLabel.setVisible(false);
                newAssignmentNameLabel.setVisible(false);
                newAssignmentField.setVisible(false);
                dateDueLabel.setVisible(false);
                dateDueField.setVisible(false);
                gradeLabel.setVisible(false);
                gradeField.setVisible(false);
                enterAssignment.setVisible(false);
                saveAssignment.setVisible(false);
                deleteAssignment.setVisible(false);
                invalidAssignment.setVisible(false);
                
            }
            
            else
            {
                newAssignmentNameLabel.setVisible(false);
                newAssignmentField.setVisible(false);
                dateDueLabel.setVisible(false);
                dateDueField.setVisible(false);
                gradeLabel.setVisible(false);
                gradeField.setVisible(false);
                enterAssignment.setVisible(false);
                saveAssignment.setVisible(false);
                deleteAssignment.setVisible(false);
                invalidAssignment.setVisible(false);                
                typeWeightLabel.setVisible(true);
                typeWeightLabel.setText("Weight:  " + 
                        courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                        [namesOfAssignmentTypes.getSelectedIndex()-1].getWeight());

                
                if(courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1] instanceof Exam)
                {
                    assignmentStrings = new String[
                        courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[
                        namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()+1];
                   assignmentStrings[0] = "";
                   assignmentStrings[1] = courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[
                        namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[0].getTitle();
                   
                }
                else
                {
                assignmentStrings = new String[
                        courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[
                        namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()+2];
                assignmentStrings[0] = "";
                assignmentStrings[1] = "Create New";
                
                for(int i = 2; i < courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[
                        namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()+2; i++ )
                        {
                            assignmentStrings[i] = courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[
                        namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[i-2].getTitle();
                        }

                }
                

                

                namesOfAssignments.setModel(new DefaultComboBoxModel(assignmentStrings));


                namesOfAssignmentsLabel.setVisible(true);
                namesOfAssignments.setVisible(true);
            }
        }
        
        if(e.getSource() == namesOfAssignments)
        {
            if (namesOfAssignments.getSelectedIndex() == 0)
            {
                newAssignmentNameLabel.setVisible(false);
                newAssignmentField.setVisible(false);
                dateDueLabel.setVisible(false);
                dateDueField.setVisible(false);
                gradeLabel.setVisible(false);
                gradeField.setVisible(false);
                enterAssignment.setVisible(false);
                saveAssignment.setVisible(false);
                deleteAssignment.setVisible(false);
                invalidAssignment.setVisible(false);            
            }
            
            // 1 is create new for assignment types and the exam for exams
            else if (namesOfAssignments.getSelectedIndex() == 1)
            {
                if (!(courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1] instanceof Exam)){
                    newAssignmentNameLabel.setVisible(true);
                    dateDueLabel.setVisible(true);
                    gradeLabel.setVisible(true);
                    enterAssignment.setVisible(true);
                    saveAssignment.setVisible(false);
                    deleteAssignment.setVisible(false);

                    newAssignmentField.setText("");
                    newAssignmentField.setVisible(true);

                    dateDueField.setText("");
                    dateDueField.setVisible(true);

                    gradeField.setText("");
                    gradeField.setVisible(true);
                }
                else
                {
                newAssignmentNameLabel.setVisible(true);
                dateDueLabel.setVisible(true);
                gradeLabel.setVisible(true);
                enterAssignment.setVisible(true);
                saveAssignment.setVisible(false);
                deleteAssignment.setVisible(false);
                
                newAssignmentField.setText(courseArray[namesOfCourses.getSelectedIndex()-1]
                        .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1]
                        .getAssignments()[0].getTitle());
                newAssignmentField.setVisible(true);
                
                if(courseArray[namesOfCourses.getSelectedIndex()-1]
                        .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1]
                        .getAssignments()[0].getDate() != null)
                {
                    
                dateDueField.setText(courseArray[namesOfCourses.getSelectedIndex()-1]
                        .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1]
                        .getAssignments()[0].getDate().toString());
                }
                else
                {
                    dateDueField.setText("");
                }
                dateDueField.setVisible(true);
                
                if(courseArray[namesOfCourses.getSelectedIndex()-1]
                        .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1]
                        .getAssignments()[0] != null){
                    
                
                gradeField.setText("" + courseArray[namesOfCourses.getSelectedIndex()-1]
                        .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1]
                        .getAssignments()[0].getGrade());
                }
                else{
                    gradeField.setText("");
                }
                
                gradeField.setVisible(true);
                }
            }
            
            else if (namesOfAssignments.getSelectedIndex() > 1)
            {
                saveAssignment.setVisible(true);
                deleteAssignment.setVisible(true);
                newAssignmentNameLabel.setVisible(true);
                newAssignmentField.setVisible(true);
                enterAssignment.setVisible(false);
                dateDueLabel.setVisible(true);
                gradeLabel.setVisible(true);
                invalidAssignment.setVisible(false);
                
                newAssignmentField.setText(courseArray[namesOfCourses.getSelectedIndex()-1]
                        .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1]
                        .getAssignments()[namesOfAssignments.getSelectedIndex()-2].getTitle());
                dateDueField.setText(
                    courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                        [namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()
                            [namesOfAssignments.getSelectedIndex()-2].getDate().toString() + "        ");
                
                gradeField.setText( "" +
                    courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                        [namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()
                            [namesOfAssignments.getSelectedIndex()-2].getGrade());                
                
                dateDueField.setVisible(true);
                gradeField.setVisible(true);    
            }
        }
        
        validate();
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == enterAssignment)
        {
            
            if(BackEnd.validAssignmentName(newAssignmentField.getText()) && BackEnd.validDate(dateDueField.getText())
                    && BackEnd.validGrade(gradeField.getText()))
            {
                
               
                courseArray[namesOfCourses.getSelectedIndex()-1].addAssignment( namesOfAssignmentTypes.getSelectedIndex()-1,
                        new Assignment(newAssignmentField.getText(), BackEnd.convertDate(dateDueField.getText())));
               
                int mostRecentAssignment = courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments();
                
                if(courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1] instanceof Exam){
                    courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[0].setGrade(BackEnd.convertGrade(gradeField.getText()));
                    courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[0].setDate(BackEnd.convertDate(dateDueField.getText()));
                    courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[0].setName(newAssignmentField.getText());
                }
                else{
                    courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[mostRecentAssignment-1].setGrade(BackEnd.convertGrade(gradeField.getText()));
                    newAssignmentField.setText("");
                    dateDueField.setText("");
                    gradeField.setText("");
                }
                try
                {

                    Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                    Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                            "root",
                            "root");

                    Statement stmt = myConnection.createStatement();


                    String sql = "Insert into assignments ( COURSE_NAME, ASSIGNMENT_TYPE, Grade, NAME_OF_ASSIGNMENT,"
                            + " YEAR_DUE, MONTH_DUE, DAY_DUE ) "
                            + "values ('";
                            
                    sql+= courseArray[namesOfCourses.getSelectedIndex()-1].getName()
                            + "', '";
                    
                    sql+=courseArray[namesOfCourses.getSelectedIndex()-1].
                                getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getType() + "', ";
                    
                    sql+= courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                            [namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[
                                courseArray[namesOfCourses.getSelectedIndex()-1]
                                .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()-1
                            ].getGrade() + ", '";
                            
                    sql+= courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                            [namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[
                                courseArray[namesOfCourses.getSelectedIndex()-1]
                                .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()-1
                            ].getTitle()
                            + "', ";
                    sql+= courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                            [namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[
                                courseArray[namesOfCourses.getSelectedIndex()-1]
                                .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()-1
                            ].getDate().getYear()
                            + ", ";
                    sql+= courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                            [namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[
                                courseArray[namesOfCourses.getSelectedIndex()-1]
                                .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()-1
                            ].getDate().getMonth()
                            + ", ";
                    sql+= courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                            [namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[
                                courseArray[namesOfCourses.getSelectedIndex()-1]
                                .getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()-1
                            ].getDate().getDayOfMonth()
                            + ")"; 
                    
                    
                    stmt.execute(sql);
                    
                    stmt.close();
                    myConnection.close();
                    
                            
                }

                catch(SQLException ex)
                {
                    System.out.println(ex.getMessage());
                }

                catch(Exception ex)
                {
                    System.out.println("Exception when adding assignments");
                }
                
                int temp = namesOfAssignmentTypes.getSelectedIndex();
                
                namesOfAssignmentTypes.setSelectedIndex(0);
                namesOfAssignmentTypes.setSelectedIndex(temp);
                newAssignmentNameLabel.setVisible(false);
                newAssignmentField.setVisible(false);
                dateDueLabel.setVisible(false);
                dateDueField.setVisible(false);
                gradeLabel.setVisible(false);
                gradeField.setVisible(false);
                enterAssignment.setVisible(false);
                invalidAssignment.setVisible(false);
                namesOfAssignments.setSelectedIndex(0);
                namesOfCourses.setSelectedIndex(namesOfCourses.getSelectedIndex()-1);
                namesOfCourses.setSelectedIndex(namesOfCourses.getSelectedIndex()+1);
                
            }
            
            else
            {
                invalidAssignment.setVisible(true);
            }
        }
            
        else if (e.getSource() == saveAssignment)
        {
            String[] tempString = new String[(courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments())+2];
            tempString[0] = "";
            tempString[1] = "Create New";
            tempString[(courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments())+1] 
                    = (courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[
                    courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()-1].getTitle());
            
            try
            {

                Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                        "root",
                        "root");

                Statement stmt = myConnection.createStatement();


              
                Date tempDate = BackEnd.convertDate(dateDueField.getText());
                String sql = "Update assignments set NAME_OF_ASSIGNMENT = '" + newAssignmentField.getText() + "', "
                        + "Grade = " + BackEnd.convertGrade(gradeField.getText())
                        +", YEAR_DUE = " + tempDate.getYear() + ", MONTH_DUE = " + tempDate.getMonth()
                        +", DAY_DUE = " + tempDate.getDayOfMonth() 
                        + " where COURSE_NAME = '" + courseArray[namesOfCourses.getSelectedIndex()-1].getName()
                        + "' and ASSIGNMENT_TYPE = '" + courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getType()
                        + "' and NAME_OF_ASSIGNMENT = '" +
                        courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                            [namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[
                                namesOfAssignments.getSelectedIndex()-2]
                        .getTitle() + "'";

                stmt.execute(sql);

                stmt.close();
                myConnection.close();


            }

            catch(SQLException ex)
            {
                System.out.println(ex.getMessage());
            }

            catch(Exception ex)
            {
                System.out.println("Exception when editing assignments");
                System.out.println(ex.getMessage());
            }
            
            courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[namesOfAssignments.getSelectedIndex()-2].setName(newAssignmentField.getText());        
            courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[namesOfAssignments.getSelectedIndex()-2].setDate(BackEnd.convertDate(dateDueField.getText()));
            courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[namesOfAssignments.getSelectedIndex()-2].setGrade(BackEnd.convertGrade(gradeField.getText()));
            

            if( namesOfAssignments.getSelectedIndex() == 
                    (courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments())+1)
            {

                int temporary = tempString.length;
                tempString[temporary-1] = newAssignmentField.getText();        
                
            }
            
            for(int i = 0; i < courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getNumAssignments()-1; i++)
            {
                tempString[i+2] = courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getAssignmentNames()[i];

            }
            
            
            
            
            
            namesOfAssignments.setModel(new DefaultComboBoxModel(tempString));
            namesOfAssignments.setSelectedIndex(0);
            namesOfCourses.setSelectedIndex(namesOfCourses.getSelectedIndex()-1);
            namesOfCourses.setSelectedIndex(namesOfCourses.getSelectedIndex()+1);

           

            newAssignmentNameLabel.setVisible(false);
            newAssignmentField.setVisible(false);
            dateDueLabel.setVisible(false);
            dateDueField.setVisible(false);
            gradeLabel.setVisible(false);
            gradeField.setVisible(false);
            enterAssignment.setVisible(false);
            saveAssignment.setVisible(false);
            deleteAssignment.setVisible(false);
            invalidAssignment.setVisible(false);
            

        
        }
        
        else if (e.getSource() == deleteAssignment)
        {
            try
            {

                Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                        "root",
                        "root");

                Statement stmt = myConnection.createStatement();


              
                String sql = "Delete from assignments where COURSE_NAME = '"+ courseArray[namesOfCourses.getSelectedIndex()-1].getName() +
                        "' and ASSIGNMENT_TYPE = '"+ courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()[namesOfAssignmentTypes.getSelectedIndex()-1].getType()
                        + "' and NAME_OF_ASSIGNMENT = '" +
                        courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                            [namesOfAssignmentTypes.getSelectedIndex()-1].getAssignments()[
                                namesOfAssignments.getSelectedIndex()-2]
                        .getTitle() + "'";

                stmt.execute(sql);

                
                

            }

            catch(Exception ex)
            {
                System.out.println("Exception when deleting assignments");
                System.out.println(ex.getMessage());
            }
            
            courseArray[namesOfCourses.getSelectedIndex()-1].getAssignmentTypes()
                            [namesOfAssignmentTypes.getSelectedIndex()-1].deleteAssignment(namesOfAssignments.getSelectedIndex()-2);

            namesOfAssignmentTypes.setSelectedIndex(0);
            namesOfCourses.setSelectedIndex(namesOfCourses.getSelectedIndex()-1);
            namesOfCourses.setSelectedIndex(namesOfCourses.getSelectedIndex()+1);
           

            newAssignmentNameLabel.setVisible(false);
            newAssignmentField.setVisible(false);
            dateDueLabel.setVisible(false);
            dateDueField.setVisible(false);
            gradeLabel.setVisible(false);
            gradeField.setVisible(false);
            enterAssignment.setVisible(false);
            saveAssignment.setVisible(false);
            deleteAssignment.setVisible(false);
            invalidAssignment.setVisible(false);
        }
            
        
            
            
            validate();
            
        }
    }
    



