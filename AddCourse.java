package classy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AddCourse extends JPanel implements ActionListener, ItemListener, KeyListener{
//////////////////////////////////////////////////////////////////////////////////////////////
////                       TEST CODE                                                     /////
	Time start1 = new Time("9:45", false);
    Time end1 = new Time("11:00", false);
    DayTimeAndLocation classDay1 = new DayTimeAndLocation(start1, end1, "Tuesday", "220 IST");
    DayTimeAndLocation classDay2 = new DayTimeAndLocation(start1, end1, "Thursday", "220 IST");

    Time start2 = new Time("1:00", true);
    Time end2 = new Time("1:50", true);

    DayTimeAndLocation class2Day1 = new DayTimeAndLocation(start2, end2, "Monday", "Eisenhower Auditorium");
    DayTimeAndLocation class2Day2 = new DayTimeAndLocation(start2, end2, "Wednesday", "Eisenhower Auditorium");
    DayTimeAndLocation class2Day3 = new DayTimeAndLocation(start2, end2, "Friday", "Eisenhower Auditorium");

    Time start3 = new Time("10:10", false);
    Time end3 = new Time("11:00", false);
    Time start4 = new Time("3:00", true);
    Time end4 = new Time("5:00", true);

    DayTimeAndLocation class3Day1 = new DayTimeAndLocation(start3, end3, "Monday", "Business 117");
    DayTimeAndLocation class3Day2 = new DayTimeAndLocation(start3, end3, "Wednesday", "Business 117");
    DayTimeAndLocation class3Day3 = new DayTimeAndLocation(start4, end4, "Thursday", "Business 117");
    DayTimeAndLocation class3Day4 = new DayTimeAndLocation(start3, end3, "Friday", "Business 117");


    Time start5 = new Time("1:00", true);
    Time end5 = new Time("4:00", true);

    DayTimeAndLocation class4Day1 = new DayTimeAndLocation(start5, end5, "Tuesday", "The Woods");

    DayTimeAndLocation[] schedule = {classDay1, classDay2};
    DayTimeAndLocation[] schedule2 = {class2Day1, class2Day2, class2Day3};
    DayTimeAndLocation[] schedule3 = {class3Day1, class3Day2, class3Day3, class3Day4};
    DayTimeAndLocation[] schedule4 = {class4Day1};
    
    Assignment ass1 = new Assignment("Lab1", new Date(2012, 10, 10));
    Assignment ass2 = new Assignment("Lab3", new Date(2012, 10, 24));
    Assignment ass3 = new Assignment("Lab4", new Date(2012, 10, 31));
    Assignment ass4 = new Assignment("Phase 1", new Date(2012, 10, 11));
    Assignment ass5 = new Assignment("Phase 2", new Date(2012, 11, 15));
    Assignment ass6 = new Assignment("Foraging", new Date(2012, 9, 28));
    Assignment ass7 = new Assignment("Catching Samon", new Date(2012, 10, 7));
    Assignment ass8 = new Assignment("Mauling Campers", new Date(2012, 11, 16));
    Assignment ass9 = new Assignment("Hibernating", new Date(2012, 12, 5)); 
    
    Course testCourse1 = new Course("CMPSC 211", "012", schedule);
    Course testCourse2 = new Course("MAGIC 101", "010", schedule2);
    Course testCourse3 = new Course("ECON 137", "001", schedule3);
    Course testCourse4 = new Course("BEAR 444", "004", schedule4);    
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
    private Course[] courseList;
	private Course newCourse;
	private int courseMenuIndex;
	private int assignmentMenuIndex;
	private int examMenuIndex;
	
	private static final String[] DAYS = {"", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};//, "Saturday", "Sunday"};
	private static final int NUM_CLASS_TIMES = 5;
	private static final int NUM_INSTRUCTOR_HOURS = 4;
	private static final int NUM_TA_HOURS = 2;
	private static final int WEIGHT_FIELD_WIDTH = 5;
	private static final int INSTRUCTOR_FIELD_WIDTH = 18;
        private static final int TIME_FIELD_WIDTH = 5;
	
	// Course elements
	private JPanel classPanel;
	private JLabel titleLabel;
	private JLabel sectionLabel;
	private JLabel meetingInfoLabel;
	private JLabel classDayLabel;
	private JLabel classBeginLabel;
	private JLabel classEndLabel;
	private JLabel classLocationLabel;
	private JLabel minRequirementLabel;
	private JComboBox titleMenu;
	private JTextField sectionField;
	private JComboBox[] classDayMenu;
	private JTextField[] classBeginFields;
	private JTextField[] classEndFields;
	private JTextField[] classLocationFields;
	private JCheckBox[] classBeginTimeCheckBox;
	private JCheckBox[] classEndTimeCheckBox;
	
	// Assignment info elements
	private JPanel assignmentPanel;
	private JLabel assignmentPanelLabel;
	private JLabel assignmentTypeLabel;
	private JLabel assignmentWeightLabel;
	private JLabel examNameLabel;
	private JLabel examWeightLabel;
	private JComboBox assignmentMenu;
	private JTextField assignmentWeightField;
	private JButton saveAssignmentTypeButton;
	private JButton deleteAssignmentTypeButton;
	private JComboBox examMenu;
	private JTextField examWeightField;
	private JButton saveExamButton;
	private JButton deleteExamButton;
	
	// Instructor elements
	private JPanel instructorPanel;
	private JLabel instructorNameLabel;
	private JLabel instructorLocationLabel;
	private JLabel instructorEmailLabel;
	private JLabel instructorHoursLabel;
	private JLabel instructorDayLabel;
	private JLabel instructorBeginLabel;
	private JLabel instructorEndLabel;
	private JTextField instructorNameField;
	private JTextField instructorLocationField;
	private JTextField instructorEmailField;
	private JComboBox[] instructorDayMenu;
	private JTextField[] instructorBeginFields;
	private JTextField[] instructorEndFields;
	private JCheckBox[] instructorBeginTimeCheckBox;
	private JCheckBox[] instructorEndTimeCheckBox;
	
	// TA1 elements
	private JPanel TA1Panel;
	private JLabel TA1NameLabel;
	private JLabel TA1LocationLabel;
	private JLabel TA1EmailLabel;
	private JLabel TA1HoursLabel;
	private JLabel TA1DayLabel;
	private JLabel TA1BeginLabel;
	private JLabel TA1EndLabel;
	private JTextField TA1NameField;
	private JTextField TA1LocationField;
	private JTextField TA1EmailField;
	private JComboBox[] TA1DayMenu;
	private JTextField[] TA1BeginFields;
	private JTextField[] TA1EndFields;
	private JCheckBox[] TA1BeginTimeCheckBox;
	private JCheckBox[] TA1EndTimeCheckBox;
	
	// TA2 elements
	private JPanel TA2Panel;
	private JLabel TA2NameLabel;
	private JLabel TA2LocationLabel;
	private JLabel TA2EmailLabel;
	private JLabel TA2HoursLabel;
	private JLabel TA2DayLabel;
	private JLabel TA2BeginLabel;
	private JLabel TA2EndLabel;
	private JTextField TA2NameField;
	private JTextField TA2LocationField;
	private JTextField TA2EmailField;
	private JComboBox[] TA2DayMenu;
	private JTextField[] TA2BeginFields;
	private JTextField[] TA2EndFields;
	private JCheckBox[] TA2BeginTimeCheckBox;
	private JCheckBox[] TA2EndTimeCheckBox;
	
	// Button elements
	private JPanel buttonPanel;
	private JButton saveButton;
	private JButton deleteButton;
	
	// Status bar elements
	private JPanel statusPanel;
	private JLabel statusLabel;
	
	// Enabling members
	private boolean instructorFieldsEnabled;
	private boolean TA1FieldsEnabled;
	private boolean TA2FieldsEnabled;
	private boolean saveButtonEnabled;
	private boolean deleteButtonEnabled;
	private boolean gradingButtonsAllowed;
	
	/**
	 * 
	 */
	public AddCourse(Course[] courses) {
		super();
		
		courseMenuIndex = 0;
		assignmentMenuIndex = 0;
		examMenuIndex = 0;
		
		// Set enabling members
		instructorFieldsEnabled = false;
		TA1FieldsEnabled = false;
		TA2FieldsEnabled = false;
		saveButtonEnabled = false;
		deleteButtonEnabled = false;
		gradingButtonsAllowed = false;
		
		courseList = courses;
//////////////////////////////////////// TEST CODE ///////////////////////////////////////////
		AssignmentType assignmentType1 = new AssignmentType("Homework", .1);
	    assignmentType1.addAssignment(ass1);
	    assignmentType1.addAssignment(ass2);
	    assignmentType1.addAssignment(ass3);

	    AssignmentType assignmentType2 = new AssignmentType("Project", .3);
	    assignmentType2.addAssignment(ass4);
	    assignmentType2.addAssignment(ass5);

	    AssignmentType assignmentType3 = new AssignmentType("Participation", .05);

	    AssignmentType assignmentType4 = new AssignmentType("Project", .2);
	    AssignmentType assignmentType5 = new AssignmentType("HomeWork", .3);
	    assignmentType5.addAssignment(ass6);
	    assignmentType5.addAssignment(ass7);
	    assignmentType5.addAssignment(ass8);
	    
	    AssignmentType assignmentType6 = new AssignmentType("Hibernating", .5);
	    assignmentType6.addAssignment(ass9);
	    
	    AssignmentType[] courseWork1 = new AssignmentType[]{assignmentType1, assignmentType2, assignmentType3};
	    AssignmentType[] courseWork2 = new AssignmentType[]{assignmentType4, assignmentType5, assignmentType6};
	    
	    testCourse1.setWork(courseWork1);
	    testCourse4.setWork(courseWork2);
		//courseList = new Course[]{testCourse1, testCourse2, testCourse3, testCourse4};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// Initialize Course components
		classPanel = new JPanel();
		titleLabel = new JLabel("*Course Title:");
		sectionLabel = new JLabel("*Section Number:");
		meetingInfoLabel = new JLabel("*Meeting Times and Places");
		classDayLabel = new JLabel("Day");
		classBeginLabel = new JLabel("Begin Time");
		classBeginLabel.setToolTipText("Ex. 2:35 or 10:15");
		classEndLabel = new JLabel("End Time");
		classEndLabel.setToolTipText("Ex. 2:35 or 10:15");
		classLocationLabel = new JLabel("Location");
		minRequirementLabel = new JLabel("* Indicates Minimum Requirement");
		titleMenu = new JComboBox(getCourseList());
		titleMenu.setEditable(true);
                titleMenu.setPrototypeDisplayValue("XXXXXXXXXXX");
		sectionField = new JTextField(5);
		classDayMenu = new JComboBox[NUM_CLASS_TIMES];
		for (int i = 0; i < classDayMenu.length; i++) {
			classDayMenu[i] = new JComboBox(DAYS);
                        classDayMenu[i].setPrototypeDisplayValue("XXXXXXXX");
		}
		classBeginFields = new JTextField[NUM_CLASS_TIMES];
		classBeginTimeCheckBox = new JCheckBox[NUM_CLASS_TIMES];
		for (int i = 0; i < classBeginFields.length; i++) {
			classBeginFields[i] = new JTextField(TIME_FIELD_WIDTH);
			classBeginTimeCheckBox[i] = new JCheckBox("PM");
		}
		classEndFields = new JTextField[NUM_CLASS_TIMES];
		classEndTimeCheckBox = new JCheckBox[NUM_CLASS_TIMES];
		for (int i = 0; i < classEndFields.length; i++) {
			classEndFields[i] = new JTextField(TIME_FIELD_WIDTH);
			classEndTimeCheckBox[i] = new JCheckBox("PM");
		}
		classLocationFields = new JTextField[NUM_CLASS_TIMES];
		for (int i = 0; i < classLocationFields.length; i++) {
			classLocationFields[i] = new JTextField(17);
		}
		titleMenu.addItemListener(this);
		titleMenu.getEditor().getEditorComponent().addKeyListener(this);
		sectionField.addKeyListener(this);
		classDayMenu[0].addItemListener(this);
		classBeginFields[0].addKeyListener(this);
		classEndFields[0].addKeyListener(this);
		classLocationFields[0].addKeyListener(this);
		
		//Initialize assignment components
		assignmentPanel = new JPanel();
		assignmentPanelLabel = new JLabel("<html><u>Course Grading Breakdown</u>");
		assignmentTypeLabel = new JLabel("Assignment Type:");
		assignmentWeightLabel = new JLabel("Weight:");
		assignmentWeightLabel.setToolTipText("Ex. 0.2 (0.0 indiates an unused type)");
		assignmentMenu = new JComboBox();
		assignmentMenu.setEditable(true);
		assignmentWeightField = new JTextField(WEIGHT_FIELD_WIDTH);
		saveAssignmentTypeButton = new JButton("<html><center>Save/Add<br/>Assignment Type</html>");
		deleteAssignmentTypeButton = new JButton("<html><center>Delete<br/>Assignment Type</html>");
		examNameLabel = new JLabel("Exam Name:");
		examWeightLabel = new JLabel("Weight:");
		examWeightLabel.setToolTipText("Ex. 0.2 (0.0 indiates an unused type)");
		examMenu = new JComboBox();
		examMenu.setEditable(true);
		examWeightField = new JTextField(WEIGHT_FIELD_WIDTH);
		saveExamButton = new JButton("<html><center>Save/Add<br/>&nbsp;&nbsp;&nbsp;Exam&nbsp;&nbsp;&nbsp;</html>");
		deleteExamButton = new JButton("<html><center>Delete<br/>&nbsp;&nbsp;&nbsp;Exam&nbsp;&nbsp;&nbsp;</html>");
		
		saveAssignmentTypeButton.setEnabled(gradingButtonsAllowed);
		deleteAssignmentTypeButton.setEnabled(gradingButtonsAllowed);
		saveExamButton.setEnabled(gradingButtonsAllowed);
		deleteExamButton.setEnabled(gradingButtonsAllowed);
		
		assignmentMenu.addItemListener(this);
		assignmentMenu.getEditor().getEditorComponent().addKeyListener(this);
		assignmentWeightField.addKeyListener(this);
		examMenu.addItemListener(this);
		examMenu.getEditor().getEditorComponent().addKeyListener(this);
		examWeightField.addKeyListener(this);
		saveAssignmentTypeButton.addActionListener(this);
		deleteAssignmentTypeButton.addActionListener(this);
		saveExamButton.addActionListener(this);
		deleteExamButton.addActionListener(this);
		
		// Initialize Instructor components
		instructorPanel = new JPanel();
		instructorNameLabel = new JLabel("Instructor:");
		instructorLocationLabel = new JLabel("Office Location:");
		instructorEmailLabel = new JLabel("Email:");
		instructorHoursLabel = new JLabel("Office Hours");
		instructorDayLabel = new JLabel("Day");
		instructorBeginLabel = new JLabel("Begin Time");
		instructorBeginLabel.setToolTipText("Ex. 2:35 or 10:15");
		instructorEndLabel = new JLabel("End Time");
		instructorEndLabel.setToolTipText("Ex. 2:35 or 10:15");
		instructorNameField = new JTextField(INSTRUCTOR_FIELD_WIDTH);
		instructorNameField.addKeyListener(this);
		instructorLocationField = new JTextField(INSTRUCTOR_FIELD_WIDTH);
		instructorLocationField.setEnabled(instructorFieldsEnabled);
		instructorEmailField = new JTextField(INSTRUCTOR_FIELD_WIDTH);
		instructorEmailField.setEnabled(instructorFieldsEnabled);
		instructorDayMenu = new JComboBox[NUM_INSTRUCTOR_HOURS];
		for (int i = 0; i < instructorDayMenu.length; i++) {
			instructorDayMenu[i] = new JComboBox(DAYS);
			instructorDayMenu[i].setEnabled(instructorFieldsEnabled);
                        instructorDayMenu[i].setPrototypeDisplayValue("XXXXXXXX");
		}
		instructorBeginFields = new JTextField[NUM_INSTRUCTOR_HOURS];
		instructorBeginTimeCheckBox = new JCheckBox[NUM_INSTRUCTOR_HOURS];
		for (int i = 0; i < instructorBeginFields.length; i++) {
			instructorBeginFields[i] = new JTextField(TIME_FIELD_WIDTH);
			instructorBeginTimeCheckBox[i] = new JCheckBox("PM");
			instructorBeginFields[i].setEnabled(instructorFieldsEnabled);
			instructorBeginTimeCheckBox[i].setEnabled(instructorFieldsEnabled);
		}
		instructorEndFields = new JTextField[NUM_INSTRUCTOR_HOURS];
		instructorEndTimeCheckBox = new JCheckBox[NUM_INSTRUCTOR_HOURS];
		for (int i = 0; i < instructorEndFields.length; i++) {
			instructorEndFields[i] = new JTextField(TIME_FIELD_WIDTH);
			instructorEndTimeCheckBox[i] = new JCheckBox("PM");
			instructorEndFields[i].setEnabled(instructorFieldsEnabled);
			instructorEndTimeCheckBox[i].setEnabled(instructorFieldsEnabled);
		}
		
		// Initialize TA1 components
		TA1Panel = new JPanel();
		TA1NameLabel = new JLabel("TA 1:");
		TA1LocationLabel = new JLabel("Office Location:");
		TA1EmailLabel = new JLabel("Email:");
		TA1HoursLabel = new JLabel("Office Hours");
		TA1DayLabel = new JLabel("Day");
		TA1BeginLabel = new JLabel("Begin Time");
		TA1BeginLabel.setToolTipText("Ex. 2:35 or 10:15");
		TA1EndLabel = new JLabel("End Time");
		TA1EndLabel.setToolTipText("Ex. 2:35 or 10:15");
		TA1NameField = new JTextField(INSTRUCTOR_FIELD_WIDTH);
		TA1NameField.addKeyListener(this);
		TA1LocationField = new JTextField(INSTRUCTOR_FIELD_WIDTH);
		TA1LocationField.setEnabled(TA1FieldsEnabled);
		TA1EmailField = new JTextField(INSTRUCTOR_FIELD_WIDTH);
		TA1EmailField.setEnabled(TA1FieldsEnabled);
		TA1DayMenu = new JComboBox[NUM_TA_HOURS];
		for (int i = 0; i < TA1DayMenu.length; i++) {
			TA1DayMenu[i] = new JComboBox(DAYS);
			TA1DayMenu[i].setEnabled(TA1FieldsEnabled);
                        TA1DayMenu[i].setPrototypeDisplayValue("XXXXXXXX");
		}
		TA1BeginFields = new JTextField[NUM_TA_HOURS];
		TA1BeginTimeCheckBox = new JCheckBox[NUM_TA_HOURS];
		for (int i = 0; i < TA1BeginFields.length; i++) {
			TA1BeginFields[i] = new JTextField(TIME_FIELD_WIDTH);
			TA1BeginTimeCheckBox[i] = new JCheckBox("PM");
			TA1BeginFields[i].setEnabled(TA1FieldsEnabled);
			TA1BeginTimeCheckBox[i].setEnabled(TA1FieldsEnabled);
		}
		TA1EndFields = new JTextField[NUM_TA_HOURS];
		TA1EndTimeCheckBox = new JCheckBox[NUM_TA_HOURS];
		for (int i = 0; i < TA1EndFields.length; i++) {
			TA1EndFields[i] = new JTextField(TIME_FIELD_WIDTH);
			TA1EndTimeCheckBox[i] = new JCheckBox("PM");
			TA1EndFields[i].setEnabled(TA1FieldsEnabled);
			TA1EndTimeCheckBox[i].setEnabled(TA1FieldsEnabled);
		}
		
		// Initialize TA2 components
		TA2Panel = new JPanel();
		TA2NameLabel = new JLabel("TA 2:");
		TA2LocationLabel = new JLabel("Office Location:");
		TA2EmailLabel = new JLabel("Email:");
		TA2HoursLabel = new JLabel("Office Hours");
		TA2DayLabel = new JLabel("Day");
		TA2BeginLabel = new JLabel("Begin Time");
		TA2BeginLabel.setToolTipText("Ex. 2:35 or 10:15");
		TA2EndLabel = new JLabel("End Time");
		TA2EndLabel.setToolTipText("Ex. 2:35 or 10:15");
		TA2NameField = new JTextField(INSTRUCTOR_FIELD_WIDTH);
		TA2NameField.addKeyListener(this);
		TA2LocationField = new JTextField(INSTRUCTOR_FIELD_WIDTH);
		TA2LocationField.setEnabled(TA2FieldsEnabled);
		TA2EmailField = new JTextField(INSTRUCTOR_FIELD_WIDTH);
		TA2EmailField.setEnabled(TA2FieldsEnabled);
		TA2DayMenu = new JComboBox[NUM_TA_HOURS];
		for (int i = 0; i < TA2DayMenu.length; i++) {
			TA2DayMenu[i] = new JComboBox(DAYS);
			TA2DayMenu[i].setEnabled(TA2FieldsEnabled);
                        TA2DayMenu[i].setPrototypeDisplayValue("XXXXXXXX");
		}
		TA2BeginFields = new JTextField[NUM_TA_HOURS];
		TA2BeginTimeCheckBox = new JCheckBox[NUM_TA_HOURS];
		for (int i = 0; i < TA2BeginFields.length; i++) {
			TA2BeginFields[i] = new JTextField(TIME_FIELD_WIDTH);
			TA2BeginTimeCheckBox[i] = new JCheckBox("PM");
			TA2BeginFields[i].setEnabled(TA2FieldsEnabled);
			TA2BeginTimeCheckBox[i].setEnabled(TA2FieldsEnabled);
		}
		TA2EndFields = new JTextField[NUM_TA_HOURS];
		TA2EndTimeCheckBox = new JCheckBox[NUM_TA_HOURS];
		for (int i = 0; i < TA2EndFields.length; i++) {
			TA2EndFields[i] = new JTextField(TIME_FIELD_WIDTH);
			TA2EndTimeCheckBox[i] = new JCheckBox("PM");
			TA2EndFields[i].setEnabled(TA2FieldsEnabled);
			TA2EndTimeCheckBox[i].setEnabled(TA2FieldsEnabled);
		}
		
		// Initialize button components
		buttonPanel = new JPanel();
		saveButton = new JButton("Save/Add Class");
		saveButton.setEnabled(saveButtonEnabled);
		deleteButton = new JButton("Delete Class");
		deleteButton.setEnabled(deleteButtonEnabled);
		
		
		// Setup class panel
		classPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagLayout courseLayout = new GridBagLayout();
		classPanel.setLayout(courseLayout);
		
		GridBagConstraints classConstraints = new GridBagConstraints();
		classConstraints.weightx = 1.0;
		classConstraints.weighty = 1.0;
		classConstraints.fill = GridBagConstraints.NONE;
		classConstraints.anchor = GridBagConstraints.WEST;
		
		classConstraints.gridx = 1;
		classConstraints.gridy = 1;
		classConstraints.gridwidth = 1;
		classConstraints.gridheight = 1;
		courseLayout.setConstraints(titleLabel, classConstraints);
		classPanel.add(titleLabel);
		
		classConstraints.gridx = 2;
		classConstraints.gridy = 1;
		classConstraints.gridwidth = 2;
		classConstraints.gridheight = 1;
		courseLayout.setConstraints(titleMenu, classConstraints);
		classPanel.add(titleMenu);
		
		classConstraints.gridx = 6;
		classConstraints.gridy = 1;
		classConstraints.gridwidth = 1;
		classConstraints.gridheight = 1;
		courseLayout.setConstraints(minRequirementLabel, classConstraints);
		classPanel.add(minRequirementLabel);
		
		classConstraints.gridx = 1;
		classConstraints.gridy = 2;
		classConstraints.gridwidth = 1;
		classConstraints.gridheight = 1;
		courseLayout.setConstraints(sectionLabel, classConstraints);
		classPanel.add(sectionLabel);
		
		classConstraints.gridx = 2;
		classConstraints.gridy = 2;
		classConstraints.gridwidth = 2;
		classConstraints.gridheight = 1;
		courseLayout.setConstraints(sectionField, classConstraints);
		classPanel.add(sectionField);
		
		classConstraints.gridx = 1;
		classConstraints.gridy = 3;
		classConstraints.gridwidth = 6;
		classConstraints.gridheight = 1;
		classConstraints.anchor = GridBagConstraints.CENTER;
		courseLayout.setConstraints(meetingInfoLabel, classConstraints);
		classPanel.add(meetingInfoLabel);
		
		classConstraints.gridx = 1;
		classConstraints.gridy = 4;
		classConstraints.gridwidth = 1;
		classConstraints.gridheight = 1;
		courseLayout.setConstraints(classDayLabel, classConstraints);
		classPanel.add(classDayLabel);
		
		classConstraints.gridx = 2;
		classConstraints.gridy = 4;
		classConstraints.gridwidth = 1;
		classConstraints.gridheight = 1;
		courseLayout.setConstraints(classBeginLabel, classConstraints);
		classPanel.add(classBeginLabel);
		
		classConstraints.gridx = 4;
		classConstraints.gridy = 4;
		classConstraints.gridwidth = 1;
		classConstraints.gridheight = 1;
		courseLayout.setConstraints(classEndLabel, classConstraints);
		classPanel.add(classEndLabel);
		
		classConstraints.gridx = 6;
		classConstraints.gridy = 4;
		classConstraints.gridwidth = 1;
		classConstraints.gridheight = 1;
		courseLayout.setConstraints(classLocationLabel, classConstraints);
		classPanel.add(classLocationLabel);
		
		for (int i = 0; i < classDayMenu.length; i++) {
			classConstraints.gridx = 1;
			classConstraints.gridy = i + 5;
			classConstraints.gridwidth = 1;
			classConstraints.gridheight = 1;
			courseLayout.setConstraints(classDayMenu[i], classConstraints);
			classPanel.add(classDayMenu[i]);
		}
		
		for (int i = 0; i < classBeginFields.length; i++) {
			classConstraints.gridx = 2;
			classConstraints.gridy = i + 5;
			classConstraints.gridwidth = 1;
			classConstraints.gridheight = 1;
			courseLayout.setConstraints(classBeginFields[i], classConstraints);
			classPanel.add(classBeginFields[i]);
		}
		
		classConstraints.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < classBeginTimeCheckBox.length; i++) {
			classConstraints.gridx = 3;
			classConstraints.gridy = i + 5;
			classConstraints.gridwidth = 1;
			classConstraints.gridheight = 1;
			courseLayout.setConstraints(classBeginTimeCheckBox[i], classConstraints);
			classPanel.add(classBeginTimeCheckBox[i]);
		}
		
		classConstraints.anchor = GridBagConstraints.CENTER;
		for (int i = 0; i < classEndFields.length; i++) {
			classConstraints.gridx = 4;
			classConstraints.gridy = i + 5;
			classConstraints.gridwidth = 1;
			classConstraints.gridheight = 1;
			courseLayout.setConstraints(classEndFields[i], classConstraints);
			classPanel.add(classEndFields[i]);
		}
		
		classConstraints.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < classEndTimeCheckBox.length; i++) {
			classConstraints.gridx = 5;
			classConstraints.gridy = i + 5;
			classConstraints.gridwidth = 1;
			classConstraints.gridheight = 1;
			courseLayout.setConstraints(classEndTimeCheckBox[i], classConstraints);
			classPanel.add(classEndTimeCheckBox[i]);
		}
		
		classConstraints.anchor = GridBagConstraints.CENTER;
		for (int i = 0; i < classLocationFields.length; i++) {
			classConstraints.gridx = 6;
			classConstraints.gridy = i + 5;
			classConstraints.gridwidth = 1;
			classConstraints.gridheight = 1;
			courseLayout.setConstraints(classLocationFields[i], classConstraints);
			classPanel.add(classLocationFields[i]);
		}
		
		// Setup assignment panel
		assignmentPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagLayout assignmentLayout = new GridBagLayout();
		assignmentPanel.setLayout(assignmentLayout);
		
		GridBagConstraints assignmentConstraints = new GridBagConstraints();
		assignmentConstraints.weightx = 1.0;
		assignmentConstraints.weighty = 0.2;
		assignmentConstraints.fill = GridBagConstraints.NONE;
		assignmentConstraints.anchor = GridBagConstraints.SOUTH;
		
		assignmentConstraints.gridx = 1;
		assignmentConstraints.gridy = 1;
		assignmentConstraints.gridwidth = 3;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(assignmentPanelLabel, assignmentConstraints);
		assignmentPanel.add(assignmentPanelLabel);
		
		assignmentConstraints.weightx = 1.0;
		assignmentConstraints.weighty = 1.0;
		
		assignmentConstraints.anchor = GridBagConstraints.SOUTH;
		assignmentConstraints.gridx = 1;
		assignmentConstraints.gridy = 2;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(assignmentTypeLabel, assignmentConstraints);
		assignmentPanel.add(assignmentTypeLabel);
		
		//assignmentConstraints.weighty = 0.5;
		assignmentConstraints.anchor = GridBagConstraints.CENTER;
		assignmentConstraints.gridx = 1;
		assignmentConstraints.gridy = 3;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(assignmentMenu, assignmentConstraints);
		assignmentPanel.add(assignmentMenu);
		
		//assignmentConstraints.weighty = 1.0;
		assignmentConstraints.anchor = GridBagConstraints.SOUTH;
		assignmentConstraints.gridx = 2;
		assignmentConstraints.gridy = 2;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(assignmentWeightLabel, assignmentConstraints);
		assignmentPanel.add(assignmentWeightLabel);
		
		//assignmentConstraints.weighty = 0.5;
		assignmentConstraints.anchor = GridBagConstraints.CENTER;
		assignmentConstraints.gridx = 2;
		assignmentConstraints.gridy = 3;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(assignmentWeightField, assignmentConstraints);
		assignmentPanel.add(assignmentWeightField);
		
		//assignmentConstraints.weighty = 1.0;
		assignmentConstraints.anchor = GridBagConstraints.NORTH;
		assignmentConstraints.gridx = 1;
		assignmentConstraints.gridy = 4;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(saveAssignmentTypeButton, assignmentConstraints);
		assignmentPanel.add(saveAssignmentTypeButton);
		
		assignmentConstraints.anchor = GridBagConstraints.NORTH;
		assignmentConstraints.gridx = 2;
		assignmentConstraints.gridy = 4;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(deleteAssignmentTypeButton, assignmentConstraints);
		assignmentPanel.add(deleteAssignmentTypeButton);
		
		assignmentConstraints.anchor = GridBagConstraints.SOUTH;
		assignmentConstraints.gridx = 1;
		assignmentConstraints.gridy = 5;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(examNameLabel, assignmentConstraints);
		assignmentPanel.add(examNameLabel);
		
		//assignmentConstraints.weighty = 0.5;
		assignmentConstraints.anchor = GridBagConstraints.CENTER;
		assignmentConstraints.gridx = 1;
		assignmentConstraints.gridy = 6;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(examMenu, assignmentConstraints);
		assignmentPanel.add(examMenu);
		
		//assignmentConstraints.weighty = 1.0;
		assignmentConstraints.anchor = GridBagConstraints.SOUTH;
		assignmentConstraints.gridx = 2;
		assignmentConstraints.gridy = 5;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(examWeightLabel, assignmentConstraints);
		assignmentPanel.add(examWeightLabel);
		
		//assignmentConstraints.weighty = 0.5;
		assignmentConstraints.anchor = GridBagConstraints.CENTER;
		assignmentConstraints.gridx = 2;
		assignmentConstraints.gridy = 6;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(examWeightField, assignmentConstraints);
		assignmentPanel.add(examWeightField);
		
		//assignmentConstraints.weighty = 1.0;
		assignmentConstraints.anchor = GridBagConstraints.NORTH;
		assignmentConstraints.gridx = 1;
		assignmentConstraints.gridy = 7;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(saveExamButton, assignmentConstraints);
		assignmentPanel.add(saveExamButton);
		
		assignmentConstraints.anchor = GridBagConstraints.NORTH;
		assignmentConstraints.gridx = 2;
		assignmentConstraints.gridy = 7;
		assignmentConstraints.gridwidth = 1;
		assignmentConstraints.gridheight = 1;
		assignmentLayout.setConstraints(deleteExamButton, assignmentConstraints);
		assignmentPanel.add(deleteExamButton);
		
		// Setup instructor panel
		instructorPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagLayout instructorLayout = new GridBagLayout();
		instructorPanel.setLayout(instructorLayout);
		
		GridBagConstraints instructorConstraints = new GridBagConstraints();
		instructorConstraints.weightx = 1.0;
		instructorConstraints.weighty = 1.0;
		instructorConstraints.fill = GridBagConstraints.NONE;
		instructorConstraints.anchor = GridBagConstraints.WEST;
		
		instructorConstraints.gridx = 1;
		instructorConstraints.gridy = 1;
		instructorConstraints.gridwidth = 1;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorNameLabel, instructorConstraints);
		instructorPanel.add(instructorNameLabel);
		
		instructorConstraints.gridx = 2;
		instructorConstraints.gridy = 1;
		instructorConstraints.gridwidth = 4;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorNameField, instructorConstraints);
		instructorPanel.add(instructorNameField);
		
		instructorConstraints.gridx = 1;
		instructorConstraints.gridy = 2;
		instructorConstraints.gridwidth = 1;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorLocationLabel, instructorConstraints);
		instructorPanel.add(instructorLocationLabel);
		
		instructorConstraints.gridx = 2;
		instructorConstraints.gridy = 2;
		instructorConstraints.gridwidth = 4;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorLocationField, instructorConstraints);
		instructorPanel.add(instructorLocationField);
		
		instructorConstraints.gridx = 1;
		instructorConstraints.gridy = 3;
		instructorConstraints.gridwidth = 1;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorEmailLabel, instructorConstraints);
		instructorPanel.add(instructorEmailLabel);
		
		instructorConstraints.gridx = 2;
		instructorConstraints.gridy = 3;
		instructorConstraints.gridwidth = 4;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorEmailField, instructorConstraints);
		instructorPanel.add(instructorEmailField);
		
		instructorConstraints.anchor = GridBagConstraints.CENTER;
		instructorConstraints.gridx = 1;
		instructorConstraints.gridy = 4;
		instructorConstraints.gridwidth = 5;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorHoursLabel, instructorConstraints);
		instructorPanel.add(instructorHoursLabel);
		
		instructorConstraints.gridx = 1;
		instructorConstraints.gridy = 5;
		instructorConstraints.gridwidth = 1;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorDayLabel, instructorConstraints);
		instructorPanel.add(instructorDayLabel);
		
		instructorConstraints.gridx = 2;
		instructorConstraints.gridy = 5;
		instructorConstraints.gridwidth = 1;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorBeginLabel, instructorConstraints);
		instructorPanel.add(instructorBeginLabel);
		
		instructorConstraints.gridx = 4;
		instructorConstraints.gridy = 5;
		instructorConstraints.gridwidth = 1;
		instructorConstraints.gridheight = 1;
		instructorLayout.setConstraints(instructorEndLabel, instructorConstraints);
		instructorPanel.add(instructorEndLabel);
		
		for (int i = 0; i < instructorDayMenu.length; i++) {
			instructorConstraints.gridx = 1;
			instructorConstraints.gridy = i + 6;
			instructorConstraints.gridwidth = 1;
			instructorConstraints.gridheight = 1;
			instructorLayout.setConstraints(instructorDayMenu[i], instructorConstraints);
			instructorPanel.add(instructorDayMenu[i]);
		}
		
		for (int i = 0; i < instructorBeginFields.length; i++) {
			instructorConstraints.gridx = 2;
			instructorConstraints.gridy = i + 6;
			instructorConstraints.gridwidth = 1;
			instructorConstraints.gridheight = 1;
			instructorLayout.setConstraints(instructorBeginFields[i], instructorConstraints);
			instructorPanel.add(instructorBeginFields[i]);
		}
		
		instructorConstraints.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < instructorBeginTimeCheckBox.length; i++) {
			instructorConstraints.gridx = 3;
			instructorConstraints.gridy = i + 6;
			instructorConstraints.gridwidth = 1;
			instructorConstraints.gridheight = 1;
			instructorLayout.setConstraints(instructorBeginTimeCheckBox[i], instructorConstraints);
			instructorPanel.add(instructorBeginTimeCheckBox[i]);
		}
		
		instructorConstraints.anchor = GridBagConstraints.CENTER;
		for (int i = 0; i < instructorEndFields.length; i++) {
			instructorConstraints.gridx = 4;
			instructorConstraints.gridy = i + 6;
			instructorConstraints.gridwidth = 1;
			instructorConstraints.gridheight = 1;
			instructorLayout.setConstraints(instructorEndFields[i], instructorConstraints);
			instructorPanel.add(instructorEndFields[i]);
		}
		
		instructorConstraints.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < instructorEndTimeCheckBox.length; i++) {
			instructorConstraints.gridx = 5;
			instructorConstraints.gridy = i + 6;
			instructorConstraints.gridwidth = 1;
			instructorConstraints.gridheight = 1;
			instructorLayout.setConstraints(instructorEndTimeCheckBox[i], instructorConstraints);
			instructorPanel.add(instructorEndTimeCheckBox[i]);
		}
		
		// Setup TA1 panel
		TA1Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagLayout TA1Layout = new GridBagLayout();
		TA1Panel.setLayout(TA1Layout);
		
		GridBagConstraints TA1Constraints = new GridBagConstraints();
		TA1Constraints.weightx = 1.0;
		TA1Constraints.weighty = 1.0;
		TA1Constraints.fill = GridBagConstraints.NONE;
		TA1Constraints.anchor = GridBagConstraints.WEST;
		
		TA1Constraints.gridx = 1;
		TA1Constraints.gridy = 1;
		TA1Constraints.gridwidth = 1;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1NameLabel, TA1Constraints);
		TA1Panel.add(TA1NameLabel);
		
		TA1Constraints.gridx = 2;
		TA1Constraints.gridy = 1;
		TA1Constraints.gridwidth = 4;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1NameField, TA1Constraints);
		TA1Panel.add(TA1NameField);
		
		TA1Constraints.gridx = 1;
		TA1Constraints.gridy = 2;
		TA1Constraints.gridwidth = 1;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1LocationLabel, TA1Constraints);
		TA1Panel.add(TA1LocationLabel);
		
		TA1Constraints.gridx = 2;
		TA1Constraints.gridy = 2;
		TA1Constraints.gridwidth = 4;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1LocationField, TA1Constraints);
		TA1Panel.add(TA1LocationField);
		
		TA1Constraints.gridx = 1;
		TA1Constraints.gridy = 3;
		TA1Constraints.gridwidth = 1;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1EmailLabel, TA1Constraints);
		TA1Panel.add(TA1EmailLabel);
		
		TA1Constraints.gridx = 2;
		TA1Constraints.gridy = 3;
		TA1Constraints.gridwidth = 4;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1EmailField, TA1Constraints);
		TA1Panel.add(TA1EmailField);
		
		TA1Constraints.anchor = GridBagConstraints.CENTER;
		TA1Constraints.gridx = 1;
		TA1Constraints.gridy = 4;
		TA1Constraints.gridwidth = 5;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1HoursLabel, TA1Constraints);
		TA1Panel.add(TA1HoursLabel);
		
		TA1Constraints.gridx = 1;
		TA1Constraints.gridy = 5;
		TA1Constraints.gridwidth = 1;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1DayLabel, TA1Constraints);
		TA1Panel.add(TA1DayLabel);
		
		TA1Constraints.gridx = 2;
		TA1Constraints.gridy = 5;
		TA1Constraints.gridwidth = 1;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1BeginLabel, TA1Constraints);
		TA1Panel.add(TA1BeginLabel);
		
		TA1Constraints.gridx = 4;
		TA1Constraints.gridy = 5;
		TA1Constraints.gridwidth = 1;
		TA1Constraints.gridheight = 1;
		TA1Layout.setConstraints(TA1EndLabel, TA1Constraints);
		TA1Panel.add(TA1EndLabel);
		
		for (int i = 0; i < TA1DayMenu.length; i++) {
			TA1Constraints.gridx = 1;
			TA1Constraints.gridy = i + 6;
			TA1Constraints.gridwidth = 1;
			TA1Constraints.gridheight = 1;
			TA1Layout.setConstraints(TA1DayMenu[i], TA1Constraints);
			TA1Panel.add(TA1DayMenu[i]);
		}
		
		for (int i = 0; i < TA1BeginFields.length; i++) {
			TA1Constraints.gridx = 2;
			TA1Constraints.gridy = i + 6;
			TA1Constraints.gridwidth = 1;
			TA1Constraints.gridheight = 1;
			TA1Layout.setConstraints(TA1BeginFields[i], TA1Constraints);
			TA1Panel.add(TA1BeginFields[i]);
		}
		
		TA1Constraints.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < TA1BeginTimeCheckBox.length; i++) {
			TA1Constraints.gridx = 3;
			TA1Constraints.gridy = i + 6;
			TA1Constraints.gridwidth = 1;
			TA1Constraints.gridheight = 1;
			TA1Layout.setConstraints(TA1BeginTimeCheckBox[i], TA1Constraints);
			TA1Panel.add(TA1BeginTimeCheckBox[i]);
		}
		
		TA1Constraints.anchor = GridBagConstraints.CENTER;
		for (int i = 0; i < TA1EndFields.length; i++) {
			TA1Constraints.gridx = 4;
			TA1Constraints.gridy = i + 6;
			TA1Constraints.gridwidth = 1;
			TA1Constraints.gridheight = 1;
			TA1Layout.setConstraints(TA1EndFields[i], TA1Constraints);
			TA1Panel.add(TA1EndFields[i]);
		}
		
		TA1Constraints.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < TA1EndTimeCheckBox.length; i++) {
			TA1Constraints.gridx = 5;
			TA1Constraints.gridy = i + 6;
			TA1Constraints.gridwidth = 1;
			TA1Constraints.gridheight = 1;
			TA1Layout.setConstraints(TA1EndTimeCheckBox[i], TA1Constraints);
			TA1Panel.add(TA1EndTimeCheckBox[i]);
		}
		
		// Setup TA2 panel
		TA2Panel.setBorder(BorderFactory.createLineBorder(Color.black));
		GridBagLayout TA2Layout = new GridBagLayout();
		TA2Panel.setLayout(TA2Layout);
		
		GridBagConstraints TA2Constraints = new GridBagConstraints();
		TA2Constraints.weightx = 1.0;
		TA2Constraints.weighty = 1.0;
		TA2Constraints.fill = GridBagConstraints.NONE;
		TA2Constraints.anchor = GridBagConstraints.WEST;
		
		TA2Constraints.gridx = 1;
		TA2Constraints.gridy = 1;
		TA2Constraints.gridwidth = 1;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2NameLabel, TA2Constraints);
		TA2Panel.add(TA2NameLabel);
		
		TA2Constraints.gridx = 2;
		TA2Constraints.gridy = 1;
		TA2Constraints.gridwidth = 4;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2NameField, TA2Constraints);
		TA2Panel.add(TA2NameField);
		
		TA2Constraints.gridx = 1;
		TA2Constraints.gridy = 2;
		TA2Constraints.gridwidth = 1;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2LocationLabel, TA2Constraints);
		TA2Panel.add(TA2LocationLabel);
		
		TA2Constraints.gridx = 2;
		TA2Constraints.gridy = 2;
		TA2Constraints.gridwidth = 4;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2LocationField, TA2Constraints);
		TA2Panel.add(TA2LocationField);
		
		TA2Constraints.gridx = 1;
		TA2Constraints.gridy = 3;
		TA2Constraints.gridwidth = 1;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2EmailLabel, TA2Constraints);
		TA2Panel.add(TA2EmailLabel);
		
		TA2Constraints.gridx = 2;
		TA2Constraints.gridy = 3;
		TA2Constraints.gridwidth = 4;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2EmailField, TA2Constraints);
		TA2Panel.add(TA2EmailField);
		
		TA2Constraints.anchor = GridBagConstraints.CENTER;
		TA2Constraints.gridx = 1;
		TA2Constraints.gridy = 4;
		TA2Constraints.gridwidth = 5;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2HoursLabel, TA2Constraints);
		TA2Panel.add(TA2HoursLabel);
		
		TA2Constraints.gridx = 1;
		TA2Constraints.gridy = 5;
		TA2Constraints.gridwidth = 1;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2DayLabel, TA2Constraints);
		TA2Panel.add(TA2DayLabel);
		
		TA2Constraints.gridx = 2;
		TA2Constraints.gridy = 5;
		TA2Constraints.gridwidth = 1;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2BeginLabel, TA2Constraints);
		TA2Panel.add(TA2BeginLabel);
		
		TA2Constraints.gridx = 4;
		TA2Constraints.gridy = 5;
		TA2Constraints.gridwidth = 1;
		TA2Constraints.gridheight = 1;
		TA2Layout.setConstraints(TA2EndLabel, TA2Constraints);
		TA2Panel.add(TA2EndLabel);
		
		for (int i = 0; i < TA2DayMenu.length; i++) {
			TA2Constraints.gridx = 1;
			TA2Constraints.gridy = i + 6;
			TA2Constraints.gridwidth = 1;
			TA2Constraints.gridheight = 1;
			TA2Layout.setConstraints(TA2DayMenu[i], TA2Constraints);
			TA2Panel.add(TA2DayMenu[i]);
		}
		
		for (int i = 0; i < TA2BeginFields.length; i++) {
			TA2Constraints.gridx = 2;
			TA2Constraints.gridy = i + 6;
			TA2Constraints.gridwidth = 1;
			TA2Constraints.gridheight = 1;
			TA2Layout.setConstraints(TA2BeginFields[i], TA2Constraints);
			TA2Panel.add(TA2BeginFields[i]);
		}
		
		TA2Constraints.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < TA2BeginTimeCheckBox.length; i++) {
			TA2Constraints.gridx = 3;
			TA2Constraints.gridy = i + 6;
			TA2Constraints.gridwidth = 1;
			TA2Constraints.gridheight = 1;
			TA2Layout.setConstraints(TA2BeginTimeCheckBox[i], TA2Constraints);
			TA2Panel.add(TA2BeginTimeCheckBox[i]);
		}
		
		TA2Constraints.anchor = GridBagConstraints.CENTER;
		for (int i = 0; i < TA2EndFields.length; i++) {
			TA2Constraints.gridx = 4;
			TA2Constraints.gridy = i + 6;
			TA2Constraints.gridwidth = 1;
			TA2Constraints.gridheight = 1;
			TA2Layout.setConstraints(TA2EndFields[i], TA2Constraints);
			TA2Panel.add(TA2EndFields[i]);
		}
		
		TA2Constraints.anchor = GridBagConstraints.WEST;
		for (int i = 0; i < TA2EndTimeCheckBox.length; i++) {
			TA2Constraints.gridx = 5;
			TA2Constraints.gridy = i + 6;
			TA2Constraints.gridwidth = 1;
			TA2Constraints.gridheight = 1;
			TA2Layout.setConstraints(TA2EndTimeCheckBox[i], TA2Constraints);
			TA2Panel.add(TA2EndTimeCheckBox[i]);
		}
		
		// Setup button panel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		saveButton.addActionListener(this);
		deleteButton.addActionListener(this);
		buttonPanel.add(saveButton);
		buttonPanel.add(deleteButton);
		
		// Setup status bar
		statusPanel = new JPanel();
		statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		statusPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	//	statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		statusLabel = new JLabel();
		statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(statusLabel);
		
		
		// Setup GUI
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		layout.setConstraints(classPanel, constraints);
		add(classPanel);
		
		constraints.gridx = 3;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		layout.setConstraints(assignmentPanel, constraints);
		add(assignmentPanel);
		
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		layout.setConstraints(instructorPanel, constraints);
		add(instructorPanel);
		
		constraints.gridx = 2;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		layout.setConstraints(TA1Panel, constraints);
		add(TA1Panel);
		
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		layout.setConstraints(TA2Panel, constraints);
		add(TA2Panel);
		
		constraints.weightx = 1.0;
		constraints.weighty = 0.0;
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.gridheight = 1;
		layout.setConstraints(statusPanel, constraints);
		add(statusPanel);
		
		constraints.gridx = 3;
		constraints.gridy = 3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		layout.setConstraints(buttonPanel, constraints);
		add(buttonPanel);
		
		this.setPreferredSize(new Dimension(1200, 800));
	}

	public boolean isValidTime(String time) {
		if (time.length() < 4 || time.length() > 5) {
			return false;
		}
		
		String[] parsedTimes = time.split(":");
		if (parsedTimes.length != 2) {
			return false;
		}
		
		if (parsedTimes[0].length() < 1 || parsedTimes[0].length() > 2 || parsedTimes[1].length() != 2) {
			return false;
		}
		
		for (int i = 0; i < parsedTimes[0].length(); i++) {
			if (parsedTimes[0].charAt(i) < '0' || parsedTimes[0].charAt(i) > '9') {
				return false;
			}
		}
		
		for (int i = 0; i < parsedTimes[1].length(); i++) {
			if (parsedTimes[1].charAt(i) < '0' || parsedTimes[1].charAt(i) > '9') {
				return false;
			}
		}
		
		if (Integer.parseInt(parsedTimes[0]) > 12 || Integer.parseInt(parsedTimes[0]) < 1) {
			return false;
		}
		
		if (Integer.parseInt(parsedTimes[1]) > 59 || Integer.parseInt(parsedTimes[1]) < 0) {
			return false;
		}		
		
		return true;
	}
	
	public boolean validateTimes(JComboBox[] dayMenu, JTextField[] beginFields, JCheckBox[] beginTimeCheckBox, JTextField[] endFields, JCheckBox[] endTimeCheckBox, String type) {
		int numTimes = 0;
		while (numTimes < dayMenu.length && dayMenu[numTimes].getSelectedIndex() != 0) {
			numTimes++;
		}
		for (int i = 0; i < numTimes; i++) {
			if (beginFields[i].getText().length() == 0) {
				statusLabel.setText("Begin time missing in " + type + " information.");
				return false;
			}
			if (endFields[i].getText().length() == 0) {
				statusLabel.setText("End time missing in " + type + " information.");
				return false;
			}
			if (!isValidTime(beginFields[i].getText())) {
				statusLabel.setText("Invalid begin time in " + type + " information.");
				return false;
			}
			if (!isValidTime(endFields[i].getText())) {
				statusLabel.setText("Invalid end time in " + type + " information.");
				return false;
			}
		}
		for (int i = 0; i < numTimes; i++) {
			if(!endsAfterStarts(new Time(beginFields[i].getText(), beginTimeCheckBox[i].isSelected()), new Time(endFields[i].getText(), endTimeCheckBox[i].isSelected()))) {
				statusLabel.setText("End times for a(n) " + type + " must be after start times.");
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidWeight(String weight){
                if (weight.length() < 3){
                    return false;
                }
                if (!(weight.charAt(1) == '.')){
                    return false;
                }
		String[] parsedWeight = {weight.substring(0, 1), weight.substring(2)};
//		String[] parsedWeight = weight.split(".");
		if (parsedWeight.length != 2) {
			return false;
		}
		if (parsedWeight[0].length() != 1) {
			return false;
		}
		if (parsedWeight[0].charAt(0) < '0' || parsedWeight[0].charAt(0) > '1') {
			return false;
		}
		for (int i = 0; i < parsedWeight[1].length(); i++) {
			if (parsedWeight[1].charAt(i) < '0' || parsedWeight[1].charAt(i) > '9') {
				return false;
			}
		}
		
		return true;
	}
	
	public Instructor createInstructor(JTextField nameField, JTextField locationField, JTextField emailField, JComboBox[] dayMenu, JTextField[] beginFields, JCheckBox[] beginTimeCheckBox, JTextField[] endFields, JCheckBox[] endTimeCheckBox, boolean isTA) {
		Instructor instructor = new Instructor(nameField.getText(), isTA);
		if (locationField.getText().length() != 0) {
			instructor.setOfficeLocation(locationField.getText());
		}
		if (emailField.getText().length() != 0) {
			instructor.setEmail(emailField.getText());
		}
		int numHours = 0;
		while (numHours < dayMenu.length && dayMenu[numHours].getSelectedIndex() != 0) {
				numHours++;
		}
		DayAndTime[] hours = new DayAndTime[numHours];
		for (int i = 0; i < numHours; i++) {
			hours[i] = new DayAndTime(new Time(beginFields[i].getText(), beginTimeCheckBox[i].isSelected()), new Time(endFields[i].getText(), endTimeCheckBox[i].isSelected()), dayMenu[i].getSelectedItem().toString());
		}
		instructor.setOfficeHours(hours);
		return instructor;
	}
	
	public boolean checkMinRequirement() {
		if (titleMenu.getSelectedItem().toString().equals("") || sectionField.getText().equals("") || classDayMenu[0].getSelectedIndex() == 0 || classBeginFields[0].getText().equals("") 
				|| classEndFields[0].getText().equals("") || classLocationFields[0].getText().equals("")) {
			
			statusLabel.setText("Please make sure to enter each of the following: title, section, and at least the top day, begin time, end time, and location.");
			return false;
		}
		return true;
	}
	
	public boolean endsAfterStarts(Time begin, Time end) {
		return (begin.militaryTimeInt() < end.militaryTimeInt());
	}
	
	private String[] getCourseList() {
		String[] courseTitles = new String[courseList.length+1];
		courseTitles[0] = "";
		for (int i = 0; i < courseList.length; i++){
			courseTitles[i+1] = courseList[i].getName();
		}
		return courseTitles;
	}
	
	private void clearAllFields(){
		sectionField.setText("");
		for (int i = 0; i < NUM_CLASS_TIMES; i++) {
			classDayMenu[i].setSelectedIndex(0);
			classBeginFields[i].setText("");
			classBeginTimeCheckBox[i].setSelected(false);
			classEndFields[i].setText("");
			classEndTimeCheckBox[i].setSelected(false);
			classLocationFields[i].setText("");
		}
		
		clearGradingInfo();
		clearInstructorFields();
		clearTA1Fields();
		clearTA2Fields();
	}
	
	private void clearGradingInfo() {
		assignmentMenu.setModel(new DefaultComboBoxModel());
		examMenu.setModel(new DefaultComboBoxModel());
		assignmentWeightField.setText("");
		examWeightField.setText("");
	}
	
	private void clearInstructorFields() {
		instructorNameField.setText("");
		instructorLocationField.setText("");
                instructorLocationField.setEnabled(false);;
		instructorEmailField.setText("");
                instructorEmailField.setEnabled(false);
		for (int i = 0; i < NUM_INSTRUCTOR_HOURS; i++) {
			instructorDayMenu[i].setSelectedIndex(0);
                        instructorDayMenu[i].setEnabled(false);
			instructorBeginFields[i].setText("");
                        instructorBeginFields[i].setEnabled(false);
			instructorBeginTimeCheckBox[i].setSelected(false);
                        instructorBeginTimeCheckBox[i].setEnabled(false);
			instructorEndFields[i].setText("");
                        instructorEndFields[i].setEnabled(false);
			instructorEndTimeCheckBox[i].setSelected(false);
                        instructorEndTimeCheckBox[i].setEnabled(false);
		}
	}
	
	private void clearTA1Fields() {
		TA1NameField.setText("");
		TA1LocationField.setText("");
                TA1LocationField.setEnabled(false);;
		TA1EmailField.setText("");
                TA1EmailField.setEnabled(false);
		for (int i = 0; i < NUM_TA_HOURS; i++) {
			TA1DayMenu[i].setSelectedIndex(0);
                        TA1DayMenu[i].setEnabled(false);
			TA1BeginFields[i].setText("");
                        TA1BeginFields[i].setEnabled(false);
			TA1BeginTimeCheckBox[i].setSelected(false);
                        TA1BeginTimeCheckBox[i].setEnabled(false);
			TA1EndFields[i].setText("");
                        TA1EndFields[i].setEnabled(false);
			TA1EndTimeCheckBox[i].setSelected(false);
                        TA1EndTimeCheckBox[i].setEnabled(false);
		}
	}
	
	private void clearTA2Fields() {
		TA2NameField.setText("");
		TA2LocationField.setText("");
                TA2LocationField.setEnabled(false);;
		TA2EmailField.setText("");
                TA2EmailField.setEnabled(false);
		for (int i = 0; i < NUM_TA_HOURS; i++) {
			TA2DayMenu[i].setSelectedIndex(0);
                        TA2DayMenu[i].setEnabled(false);
			TA2BeginFields[i].setText("");
                        TA2BeginFields[i].setEnabled(false);
			TA2BeginTimeCheckBox[i].setSelected(false);
                        TA2BeginTimeCheckBox[i].setEnabled(false);
			TA2EndFields[i].setText("");
                        TA2EndFields[i].setEnabled(false);
			TA2EndTimeCheckBox[i].setSelected(false);
                        TA2EndTimeCheckBox[i].setEnabled(false);
		}
	}
	
	private void setButtonEnablers(boolean enabled) {
		saveButtonEnabled = enabled;
		deleteButtonEnabled = enabled;
		saveButton.setEnabled(saveButtonEnabled);
		deleteButton.setEnabled(deleteButtonEnabled);
	}
	
	private void fillGradingInfo(int courseIndex) {
		int numAssignmentTypes = 0;
		int numExams = 0;
		AssignmentType[] assignmentTypes;
		AssignmentType[] exams;
		
		for (int i = 0; i < courseList[courseIndex].getAssignmentTypes().length; i++) {
			if (courseList[courseIndex].getAssignmentTypes()[i] instanceof Exam) {
				numExams++;
			}
			else {
				numAssignmentTypes++;
			}
		}
		assignmentTypes = new AssignmentType[numAssignmentTypes];
		exams = new Exam[numExams];
		
		for (int i = 0, j = 0, k = 0; i < courseList[courseIndex].getAssignmentTypes().length; i++) {
			if (courseList[courseIndex].getAssignmentTypes()[i] instanceof Exam) {
				exams[j] = courseList[courseIndex].getAssignmentTypes()[i];
				j++;
			}
			else {
				assignmentTypes[k] = courseList[courseIndex].getAssignmentTypes()[i];
				k++;
			}
		}
		
		String[] assignmentTypeList = new String[numAssignmentTypes + 1];	// +1 leaves room for empty first entry
		String[] examList = new String[numExams + 1];						// +1 leaves room for empty first entry
		assignmentTypeList[0] = "";
		examList[0] = "";
		for (int i = 0; i < numAssignmentTypes; i++) {
			assignmentTypeList[i+1] = assignmentTypes[i].getType();
		}
		for (int i = 0; i < numExams; i++) {
			examList[i+1] = exams[i].getType();
		}
		assignmentMenu.setModel(new DefaultComboBoxModel(assignmentTypeList));
		examMenu.setModel(new DefaultComboBoxModel(examList));
		assignmentWeightField.setText("");
		examWeightField.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveButton) {
			// Check minimum requirement
			if (!checkMinRequirement()) {
				return;
			}
			
			// Validate meeting times
			if (!validateTimes(classDayMenu, classBeginFields, classBeginTimeCheckBox, classEndFields, classEndTimeCheckBox, "course")) {
				return;
			}
			
			// Validate instructor hours
			if (instructorFieldsEnabled) {
				if (!validateTimes(instructorDayMenu, instructorBeginFields, instructorBeginTimeCheckBox, instructorEndFields, instructorEndTimeCheckBox, "instructor")) {
					return;
				}
			}
			
			// Validate TA 1 hours
			if (TA1FieldsEnabled) {
				if (!validateTimes(TA1DayMenu, TA1BeginFields, TA1BeginTimeCheckBox, TA1EndFields, TA1EndTimeCheckBox, "TA 1")) {
					return;
				}
			}
			
			// Validate TA 2 hours
			if (TA2FieldsEnabled) {
				if (!validateTimes(TA2DayMenu, TA2BeginFields, TA2BeginTimeCheckBox, TA2EndFields, TA2EndTimeCheckBox, "TA 2")) {
					return;
				}
			}
			
			// Clear any errors
			statusLabel.setText("");
			
			// Create a new course and add it
			
			String newTitle = new String(titleMenu.getSelectedItem().toString());
			String newSection = new String(sectionField.getText());
			
			// Store class meeting info
			int numMeetingTimes = 0;
			for (int i = 0; i < classDayMenu.length; i++) {
				if (classDayMenu[i].getSelectedIndex() != 0) {
					numMeetingTimes++;
				}
			}
			
			DayTimeAndLocation[] courseMeetingInfo = new DayTimeAndLocation[numMeetingTimes];
			for (int i = 0; i < numMeetingTimes; i++) {
				courseMeetingInfo[i] = new DayTimeAndLocation(new Time(classBeginFields[i].getText(), classBeginTimeCheckBox[i].isSelected()), new Time(classEndFields[i].getText(), classEndTimeCheckBox[i].isSelected()), classDayMenu[i].getSelectedItem().toString(), classLocationFields[i].getText());
			}
			
			newCourse = new Course(newTitle, newSection, courseMeetingInfo);
			
			// Validate instructor info
			if (instructorNameField.getText().length() != 0) {
				newCourse.setInstructor(createInstructor(instructorNameField, instructorLocationField, instructorEmailField, instructorDayMenu, 
						instructorBeginFields, instructorBeginTimeCheckBox, instructorEndFields, instructorEndTimeCheckBox, false), 0);
			}
			
			// Validate TA1 info
			if (TA1NameField.getText().length() != 0) {
				newCourse.setInstructor(createInstructor(TA1NameField, TA1LocationField, TA1EmailField, TA1DayMenu, TA1BeginFields, 
						TA1BeginTimeCheckBox, TA1EndFields, TA1EndTimeCheckBox, true), 1);
			}
			
			// Validate TA2 info
			if (TA2NameField.getText().length() != 0) {
				newCourse.setInstructor(createInstructor(TA2NameField, TA2LocationField, TA2EmailField, TA2DayMenu, TA2BeginFields, 
						TA2BeginTimeCheckBox, TA2EndFields, TA2EndTimeCheckBox, true), 2);
			}
			
                        try {
                            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                            Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                                "root",
                                "root");
                            Statement stmt = myConnection.createStatement();

                            if (courseMenuIndex == 0) {
                                    Course [] temp = courseList;
                                    courseList = new Course[temp.length + 1];
                                    for (int i = 0; i < temp.length; i++) {
                                            courseList[i] = temp[i];
                                    }
                                    courseList[temp.length] = newCourse;
                                    courseMenuIndex = courseList.length;
                                    
                                    // Update database
                                    
                                    // COURSE
                                    String sql = "Insert into COURSE (COURSE_NAME, SECTION) "
                                            + "values ('" + newCourse.getName() + "', '" + newCourse.getSectionNumber() + "')";
                                    stmt.execute(sql);
                                    
                                    // DAY_TIME_LOCATION
                                    for (int i = 0; i < newCourse.getCourseTimes().length; i++) {
                                        sql = "Insert into DAY_TIME_LOCATION (COURSE_NAME, LOCATION, DAY, START_HOUR, START_MIN, IS_PM1, END_HOUR, END_MIN, IS_PM2) "
                                                + "values ('"
                                                + newCourse.getName() + "', '"
                                                + newCourse.getCourseTimes()[i].getLocation() + "', '"
                                                + newCourse.getCourseTimes()[i].getDay() + "', "
                                                + newCourse.getCourseTimes()[i].getStartTime().getHour() + ", "
                                                + newCourse.getCourseTimes()[i].getStartTime().getMinute() + ", '"
                                                + newCourse.getCourseTimes()[i].getStartTime().isPM() + "', "
                                                + newCourse.getCourseTimes()[i].getEndTime().getHour() + ", "
                                                + newCourse.getCourseTimes()[i].getEndTime().getMinute() + ", '"
                                                + newCourse.getCourseTimes()[i].getEndTime().isPM() + "')";
                                        
                                        stmt.execute(sql);
                                    }
                                    
                                    // INSTRUCTORS
                                    for (int i = 0; i < newCourse.getInstructors().length; i++) {
                                        if (newCourse.getInstructors()[i] != null){
                                            sql = "Insert into INSTRUCTORS (COURSE_NAME, NAME, OFFICE, EMAIL, IS_TA) "
                                                    + " values ('"
                                                    + newCourse.getName() + "', '"
                                                    + newCourse.getInstructors()[i].getName() + "', '"
                                                    + newCourse.getInstructors()[i].getOfficeLocation() + "', '"
                                                    + newCourse.getInstructors()[i].getEmail() + "', '"
                                                    + newCourse.getInstructors()[i].getIsTA() + "')";

                                            stmt.execute(sql);
                                        
                                            for (int j = 0; j < newCourse.getInstructors()[i].getOfficeHours().length; j++){
                                                sql = "Insert into DAY_TIME_LOCATION (INSTRUCTOR_NAME, LOCATION, DAY, START_HOUR, START_MIN, IS_PM1, END_HOUR, END_MIN, IS_PM2) "
                                                        + " values ('"
                                                        + newCourse.getInstructors()[i].getName() + "', '"
                                                        + newCourse.getInstructors()[i].getOfficeLocation() + "', '"
                                                        + newCourse.getInstructors()[i].getOfficeHours()[j].getDay() + "', "
                                                        + newCourse.getInstructors()[i].getOfficeHours()[j].getStartTime().getHour() + ", "
                                                        + newCourse.getInstructors()[i].getOfficeHours()[j].getStartTime().getMinute() + ", '" 
                                                        + newCourse.getInstructors()[i].getOfficeHours()[j].getStartTime().isPM() + "', "
                                                        + newCourse.getInstructors()[i].getOfficeHours()[j].getEndTime().getHour() + ", "
                                                        + newCourse.getInstructors()[i].getOfficeHours()[j].getEndTime().getMinute() + ", '" 
                                                        + newCourse.getInstructors()[i].getOfficeHours()[j].getEndTime().isPM() + "')";

                                                stmt.execute(sql);
                                            }
                                        }
                                    }
                                    
                                    
                            }
                            else {
                                String sql;
                                String originalCourseName = courseList[courseMenuIndex-1].getName();
                                
                                // Remove old record
                                sql = "Delete from Instructors where COURSE_NAME =  '" + originalCourseName + "'";
                                stmt.executeUpdate(sql);

                                sql = "Delete from DAY_TIME_LOCATION where COURSE_NAME = '" + originalCourseName + "'";
                                stmt.executeUpdate(sql);
                                
                                // Create new record in its place
                                // COURSE
                                sql = "Update COURSE "
                                        + "set COURSE_NAME = '" + newCourse.getName() + "', "
                                        + "SECTION = '" + newCourse.getSectionNumber() + "' "
                                        + "where COURSE_NAME = '" + originalCourseName + "'";
                                stmt.execute(sql);

                                // DAY_TIME_LOCATION
                                for (int i = 0; i < newCourse.getCourseTimes().length; i++) {
                                    sql = "Insert into DAY_TIME_LOCATION (COURSE_NAME, LOCATION, DAY, START_HOUR, START_MIN, IS_PM1, END_HOUR, END_MIN, IS_PM2) "
                                            + "values ('"
                                            + newCourse.getName() + "', '"
                                            + newCourse.getCourseTimes()[i].getLocation() + "', '"
                                            + newCourse.getCourseTimes()[i].getDay() + "', "
                                            + newCourse.getCourseTimes()[i].getStartTime().getHour() + ", "
                                            + newCourse.getCourseTimes()[i].getStartTime().getMinute() + ", '"
                                            + newCourse.getCourseTimes()[i].getStartTime().isPM() + "', "
                                            + newCourse.getCourseTimes()[i].getEndTime().getHour() + ", "
                                            + newCourse.getCourseTimes()[i].getEndTime().getMinute() + ", '"
                                            + newCourse.getCourseTimes()[i].getEndTime().isPM() + "')";

                                    stmt.execute(sql);
                                }

                                // INSTRUCTORS
                                for (int i = 0; i < newCourse.getInstructors().length; i++) {
                                    if (newCourse.getInstructors()[i] != null){
                                        sql = "Insert into INSTRUCTORS (COURSE_NAME, NAME, OFFICE, EMAIL, IS_TA) "
                                                + " values ('"
                                                + newCourse.getName() + "', '"
                                                + newCourse.getInstructors()[i].getName() + "', '"
                                                + newCourse.getInstructors()[i].getOfficeLocation() + "', '"
                                                + newCourse.getInstructors()[i].getEmail() + "', '"
                                                + newCourse.getInstructors()[i].getIsTA() + "')";

                                        stmt.execute(sql);

                                        for (int j = 0; j < newCourse.getInstructors()[i].getOfficeHours().length; j++){
                                            sql = "Insert into DAY_TIME_LOCATION (INSTRUCTOR_NAME, LOCATION, DAY, START_HOUR, START_MIN, IS_PM1, END_HOUR, END_MIN, IS_PM2) "
                                                    + " values ('"
                                                    + newCourse.getInstructors()[i].getName() + "', '"
                                                    + newCourse.getInstructors()[i].getOfficeLocation() + "', '"
                                                    + newCourse.getInstructors()[i].getOfficeHours()[j].getDay() + "', "
                                                    + newCourse.getInstructors()[i].getOfficeHours()[j].getStartTime().getHour() + ", "
                                                    + newCourse.getInstructors()[i].getOfficeHours()[j].getStartTime().getMinute() + ", '" 
                                                    + newCourse.getInstructors()[i].getOfficeHours()[j].getStartTime().isPM() + "', "
                                                    + newCourse.getInstructors()[i].getOfficeHours()[j].getEndTime().getHour() + ", "
                                                    + newCourse.getInstructors()[i].getOfficeHours()[j].getEndTime().getMinute() + ", '" 
                                                    + newCourse.getInstructors()[i].getOfficeHours()[j].getEndTime().isPM() + "')";

                                            stmt.execute(sql);
                                        }
                                    }
                                }
                                
//                                //Change Coursname associated with ASSIGNMENTS and ASSIGNMENTTYPES if necessary
                                if (!newCourse.getName().equals(originalCourseName)){
                                    sql = "Update ASSIGNMENTS "
                                            + "set COURSE_NAME = '" + newCourse.getName()
                                            + "' where COURSE_NAME = '" + originalCourseName + "'";
                                    stmt.executeUpdate(sql);
                                    
                                    sql = "Update ASSIGNMENT_TYPES "
                                            + "set COURSE_NAME = '" + newCourse.getName()
                                            + "' where COURSE_NAME = '" + originalCourseName + "'";
                                    stmt.executeUpdate(sql);
                                }                              
                                
                                
                                // Retain the work from the original class
                                newCourse.setWork(courseList[courseMenuIndex - 1].getAssignmentTypes());
                                courseList[courseMenuIndex-1] = newCourse;
                            }
                            
                            stmt.close();
                            myConnection.close();
                        }
                        catch (Exception ex){
                            System.out.println("Exception when adding/updating course.");
                            System.out.println(ex.getMessage());
                        }
                          
			titleMenu.setModel(new DefaultComboBoxModel(getCourseList()));
			titleMenu.setSelectedIndex(courseMenuIndex);
			fillGradingInfo(courseMenuIndex-1);
		}
		
		else if (e.getSource() == deleteButton) {
                    try
                    {
                        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                        Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                            "root",
                            "root");

                        Statement stmt = myConnection.createStatement();
           
                        String sql = "Delete from ASSIGNMENTS where COURSE_NAME = '" + courseList[titleMenu.getSelectedIndex()-1].getName() + "'";
                        stmt.executeUpdate(sql);
                        
                        sql = "Delete from ASSIGNMENT_TYPES where COURSE_NAME = '" + courseList[titleMenu.getSelectedIndex()-1].getName() + "'";
                        stmt.executeUpdate(sql);
                        
                        sql = "Delete from INSTRUCTORS where COURSE_NAME =  '" + courseList[titleMenu.getSelectedIndex()-1].getName() + "'";
                        stmt.executeUpdate(sql);
                        
                        sql = "Delete from DAY_TIME_LOCATION where COURSE_NAME = '" + courseList[titleMenu.getSelectedIndex()-1].getName() + "'";
                        stmt.executeUpdate(sql);
                        
                        sql = "Delete from course where COURSE_NAME = '" + courseList[titleMenu.getSelectedIndex()-1].getName() + "'";
                        stmt.executeUpdate(sql);
                        
                        stmt.close();
                        myConnection.close();
                    }
                    
                    catch(Exception ex)
                    {
                        System.out.println("Exception when deleting Course");
                        System.out.println(ex.getMessage());
                    }

                    boolean reachedDeleteIndex = false;
                    Course [] temp = courseList;
                    courseList = new Course[temp.length - 1];
                    for (int i = 0; i < temp.length; i++) {
                            if ((courseMenuIndex-1) == i) {
                                    reachedDeleteIndex = true;
                                    i++;
                            }
                            if (!(i < temp.length)) {
                                    break;
                            }
                            if (reachedDeleteIndex) {
                                    courseList[i - 1] = temp[i];
                            }
                            else {
                                    courseList[i] = temp[i];
                            }
                    }
                    clearAllFields();
                    titleMenu.setModel(new DefaultComboBoxModel(getCourseList()));
                    titleMenu.setSelectedIndex(0);
                    courseMenuIndex = 0;
                    deleteButtonEnabled = false;
                    deleteButton.setEnabled(false);
		}
		
		else if (e.getSource() == saveAssignmentTypeButton){
			if (!isValidWeight(assignmentWeightField.getText())){
				statusLabel.setText("Invalid assignment weight.");
				return;
			}
                        try {
                            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                            Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                                  "root",
                                  "root");
                            Statement stmt = myConnection.createStatement();
                        
                            statusLabel.setText("");
                            if (assignmentMenuIndex == 0) {
                                AssignmentType [] temp = courseList[courseMenuIndex-1].getAssignmentTypes();
                                courseList[courseMenuIndex-1].setWork(new AssignmentType[temp.length + 1]);
                                int i = 0;
                                
                                // Insert the previous AssignmentTypes
                                while (i < temp.length && !(temp[i] instanceof Exam)){
                                        courseList[courseMenuIndex-1].getAssignmentTypes()[i] = temp[i];
                                        i++;
                                }
                                
                                // Insert new AssignmentType
                                courseList[courseMenuIndex-1].getAssignmentTypes()[i] = new AssignmentType(assignmentMenu.getEditor().getItem().toString(), Double.parseDouble(assignmentWeightField.getText()));
                                i++;

                                // Insert the Exams
                                for (int j = i; j < temp.length + 1; j++) {
                                        courseList[courseMenuIndex-1].getAssignmentTypes()[j] = temp[j-1];
                                }
                                String sql = "Insert into ASSIGNMENT_TYPES (COURSE_NAME, ASSIGNMENT_TYPE, WEIGHT, IS_EXAM) "
                                        + "values ('"
                                        + courseList[courseMenuIndex-1].getName() + "', '"
                                        + assignmentMenu.getEditor().getItem().toString() + "', "
                                        + Double.parseDouble(assignmentWeightField.getText()) + ""
                                        + ", 'false')";
                                stmt.execute(sql);
                            }
                            else {
                                String temp = courseList[courseMenuIndex-1].getAssignmentTypes()[assignmentMenuIndex-1].getType();
                                courseList[courseMenuIndex-1].getAssignmentTypes()[assignmentMenuIndex-1] = new AssignmentType(assignmentMenu.getEditor().getItem().toString(), Double.parseDouble(assignmentWeightField.getText()));
                                String sql = "update ASSIGNMENT_TYPES"
                                        + " set ASSIGNMENT_TYPE = '" + assignmentMenu.getEditor().getItem().toString()
                                        + "', Weight = " + Double.parseDouble(assignmentWeightField.getText())
                                        + " where COURSE_NAME = '" + courseList[courseMenuIndex-1].getName() +
                                        "' and ASSIGNMENT_TYPE = '" + temp + "'";
                                stmt.executeUpdate(sql);
                            }
                            stmt.close();
                            myConnection.close();
			}
                        catch(Exception ex){
                            System.out.println("Exception when adding/updating AssignmentType"); 
                            System.out.println(ex.getMessage());
                        }
			fillGradingInfo(courseMenuIndex-1);
			assignmentMenu.setSelectedIndex(0);
			assignmentMenuIndex = 0;
			saveAssignmentTypeButton.setEnabled(false);
			deleteAssignmentTypeButton.setEnabled(false);
		}
		
		else if (e.getSource() == saveExamButton){
                    if (!isValidWeight(examWeightField.getText())){
                            statusLabel.setText("Invalid exam weight.");
                            return;
                    }
                    try {
                        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                        Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                            "root",
                            "root");
                        Statement stmt = myConnection.createStatement();
                        
                        statusLabel.setText("");
                        if (examMenuIndex == 0) {
                                AssignmentType [] temp = courseList[courseMenuIndex-1].getAssignmentTypes();
                                courseList[courseMenuIndex-1].setWork(new AssignmentType[temp.length + 1]);
                                
                                // Insert AssignmentTypes and Exams
                                for (int i = 0; i < temp.length; i++) {
                                        courseList[courseMenuIndex-1].getAssignmentTypes()[i] = temp[i];
                                }
                                
                                // Insert new Exam
                                courseList[courseMenuIndex-1].getAssignmentTypes()[temp.length] = new Exam(examMenu.getEditor().getItem().toString(), Double.parseDouble(examWeightField.getText()));
                                
                                String sql = "Insert into ASSIGNMENT_TYPES (COURSE_NAME, ASSIGNMENT_TYPE, WEIGHT, IS_EXAM) "
                                        + "values ('"
                                        + courseList[courseMenuIndex-1].getName() + "', '"
                                        + examMenu.getEditor().getItem().toString() + "', "
                                        + Double.parseDouble(examWeightField.getText()) + ", '"
                                        + "true')";
                                stmt.execute(sql);
                                
                                sql = "Insert into ASSIGNMENTS (COURSE_NAME, ASSIGNMENT_TYPE, GRADE, NAME_OF_ASSIGNMENT, YEAR_DUE, MONTH_DUE, DAY_DUE) "
                                        + "values ('"
                                        + courseList[courseMenuIndex-1].getName() + "', '"
                                        + examMenu.getEditor().getItem().toString() + "', "
                                        + courseList[courseMenuIndex-1].getAssignmentTypes()[temp.length].getGrade(0) + ", '"
                                        + courseList[courseMenuIndex-1].getAssignmentTypes()[temp.length].getAssignments()[0].getTitle() + "', "
                                        + "null, "
                                        + "null, "
                                        + "null)";
                                        
                        }
                        else {
                            String temp = courseList[courseMenuIndex-1].getAssignmentTypes()[assignmentMenu.getItemCount()-1 + examMenuIndex-1].getType();
                            courseList[courseMenuIndex-1].getAssignmentTypes()[assignmentMenu.getItemCount()-1 + examMenuIndex-1] = new Exam(examMenu.getEditor().getItem().toString(), Double.parseDouble(examWeightField.getText()));

                            String sql = "update ASSIGNMENT_TYPES"
                                    + " set ASSIGNMENT_TYPE = '" + examMenu.getEditor().getItem().toString()
                                    + "', Weight = " + Double.parseDouble(examWeightField.getText())
                                    + " where COURSE_NAME = '" + courseList[courseMenuIndex-1].getName() +
                                    "' and ASSIGNMENT_TYPE = '" + temp + "'";
                            stmt.executeUpdate(sql);  
                        }
                        stmt.close();
                        myConnection.close();
                    }
                    catch(Exception ex)
                    {
                        System.out.println("Exception when adding/updating Exams");
                        System.out.println(ex.getMessage());
                    }
                    
                    fillGradingInfo(courseMenuIndex-1);
                    examMenu.setSelectedIndex(0);
                    examMenuIndex = 0;
                    saveExamButton.setEnabled(false);
                    deleteExamButton.setEnabled(false);
		}
		
		else if (e.getSource() == deleteAssignmentTypeButton) {
                    try
                    {
                        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                        Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                            "root",
                            "root");

                        Statement stmt = myConnection.createStatement();

                        String sql = "Delete from Assignments where COURSE_NAME = '" + courseList[titleMenu.getSelectedIndex()-1].getName() + "'"
                                + " and ASSIGNMENT_TYPE = '" + courseList[titleMenu.getSelectedIndex()-1].getAssignmentTypes()[assignmentMenu.getSelectedIndex()-1].getType()
                                + "'";

                        stmt.executeUpdate(sql);

                        sql = "Delete from ASSIGNMENT_TYPES where COURSE_NAME = '" + courseList[titleMenu.getSelectedIndex()-1].getName() + "'"
                                + " and ASSIGNMENT_TYPE = '" + courseList[titleMenu.getSelectedIndex()-1].getAssignmentTypes()[assignmentMenu.getSelectedIndex()-1].getType()
                                + "'";
                        stmt.executeUpdate(sql);


                        stmt.close();
                        myConnection.close();
                    }

                    catch(Exception ex)
                    {
                        System.out.println("Exception when deleting ASSIGNMENT_TYPES");
                        System.out.println(ex.getMessage());
                    }

                    boolean reachedDeleteIndex = false;
                    AssignmentType [] temp = courseList[courseMenuIndex-1].getAssignmentTypes();
                    courseList[courseMenuIndex-1].setWork(new AssignmentType[temp.length-1]);
                    for (int i = 0; i < temp.length; i++) {
                            if ((assignmentMenuIndex-1) == i) {
                                    reachedDeleteIndex = true;
                                    i++;
                            }
                            if (!(i < temp.length)) {
                                    break;
                            }
                            if (reachedDeleteIndex) {
                                    courseList[courseMenuIndex-1].getAssignmentTypes()[i-1] = temp[i];
                            }
                            else {
                                    courseList[courseMenuIndex-1].getAssignmentTypes()[i] = temp[i];
                            }
                    }
                    fillGradingInfo(courseMenuIndex-1);
                    assignmentMenu.setSelectedIndex(0);
                    assignmentMenuIndex = 0;
                    saveAssignmentTypeButton.setEnabled(false);
                    deleteAssignmentTypeButton.setEnabled(false);
		}
		
		else if (e.getSource() == deleteExamButton) {
                    try
                  {
                        Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                        Connection myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Classy",
                            "root",
                            "root");

                        Statement stmt = myConnection.createStatement();

                        String sql = "Delete from Assignments where COURSE_NAME = '" + courseList[titleMenu.getSelectedIndex()-1].getName() + "'"
                                + " and ASSIGNMENT_TYPE = '" + courseList[titleMenu.getSelectedIndex()-1].getAssignmentTypes()[assignmentMenu.getItemCount()-1 + examMenu.getSelectedIndex()-1].getType()
                                + "'";

                        stmt.executeUpdate(sql);

                        sql = "Delete from ASSIGNMENT_TYPES where COURSE_NAME = '" + courseList[titleMenu.getSelectedIndex()-1].getName() + "'"
                                + " and ASSIGNMENT_TYPE = '" + courseList[titleMenu.getSelectedIndex()-1].getAssignmentTypes()[assignmentMenu.getItemCount()-1 + examMenu.getSelectedIndex()-1].getType()
                                + "'";
                        
                        stmt.executeUpdate(sql);


                        stmt.close();
                        myConnection.close();
                    }

                    catch(Exception ex)
                    {
                        System.out.println("Exception when deleting ASSIGNMENT_TYPES");
                        System.out.println(ex.getMessage());
                    }

                    boolean reachedDeleteIndex = false;
                    AssignmentType [] temp = courseList[courseMenuIndex-1].getAssignmentTypes();
                    courseList[courseMenuIndex-1].setWork(new AssignmentType[temp.length-1]);
                    for (int i = 0; i < temp.length; i++) {
                            if ((examMenuIndex-1 + assignmentMenu.getItemCount()-1) == i) {
                                    reachedDeleteIndex = true;
                                    i++;
                            }
                            if (!(i < temp.length)) {
                                    break;
                            }
                            if (reachedDeleteIndex) {
                                    courseList[courseMenuIndex-1].getAssignmentTypes()[i-1] = temp[i];
                            }
                            else {
                                    courseList[courseMenuIndex-1].getAssignmentTypes()[i] = temp[i];
                            }
                    }
                    fillGradingInfo(courseMenuIndex-1);
                    examMenu.setSelectedIndex(0);
                    examMenuIndex = 0;
                    saveExamButton.setEnabled(false);
                    deleteExamButton.setEnabled(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == instructorNameField) {
			boolean temp = (instructorNameField.getText().length() != 0);
			if (temp != instructorFieldsEnabled){
				instructorFieldsEnabled = temp;
				instructorLocationField.setEnabled(instructorFieldsEnabled);
				instructorEmailField.setEnabled(instructorFieldsEnabled);
				for (int i = 0; i < instructorDayMenu.length; i++) {
					instructorDayMenu[i].setEnabled(instructorFieldsEnabled);
				}
				for (int i = 0; i < instructorBeginFields.length; i++) {
					instructorBeginFields[i].setEnabled(instructorFieldsEnabled);
					instructorBeginTimeCheckBox[i].setEnabled(instructorFieldsEnabled);
				}
				for (int i = 0; i < instructorEndFields.length; i++) {
					instructorEndFields[i].setEnabled(instructorFieldsEnabled);
					instructorEndTimeCheckBox[i].setEnabled(instructorFieldsEnabled);
				}
			}
		}
		
		else if (e.getSource() == TA1NameField) {
			boolean temp = (TA1NameField.getText().length() != 0);
			if (temp != TA1FieldsEnabled){
				TA1FieldsEnabled = temp;
				TA1LocationField.setEnabled(TA1FieldsEnabled);
				TA1EmailField.setEnabled(TA1FieldsEnabled);
				for (int i = 0; i < TA1DayMenu.length; i++) {
					TA1DayMenu[i].setEnabled(TA1FieldsEnabled);
				}
				for (int i = 0; i < TA1BeginFields.length; i++) {
					TA1BeginFields[i].setEnabled(TA1FieldsEnabled);
					TA1BeginTimeCheckBox[i].setEnabled(TA1FieldsEnabled);
				}
				for (int i = 0; i < TA1EndFields.length; i++) {
					TA1EndFields[i].setEnabled(TA1FieldsEnabled);
					TA1EndTimeCheckBox[i].setEnabled(TA1FieldsEnabled);
				}
			}
		}
		
		else if (e.getSource() == TA2NameField) {
			boolean temp = (TA2NameField.getText().length() != 0);
			if (temp != TA2FieldsEnabled){
				TA2FieldsEnabled = temp;
				TA2LocationField.setEnabled(TA2FieldsEnabled);
				TA2EmailField.setEnabled(TA2FieldsEnabled);
				for (int i = 0; i < TA2DayMenu.length; i++) {
					TA2DayMenu[i].setEnabled(TA2FieldsEnabled);
				}
				for (int i = 0; i < TA2BeginFields.length; i++) {
					TA2BeginFields[i].setEnabled(TA2FieldsEnabled);
					TA2BeginTimeCheckBox[i].setEnabled(TA2FieldsEnabled);
				}
				for (int i = 0; i < TA2EndFields.length; i++) {
					TA2EndFields[i].setEnabled(TA2FieldsEnabled);
					TA2EndTimeCheckBox[i].setEnabled(TA2FieldsEnabled);
				}
			}
		}
		
		else if (e.getSource() == titleMenu.getEditor().getEditorComponent() || e.getSource() == sectionField || e.getSource() == classBeginFields[0] || e.getSource() ==  classEndFields[0] || e.getSource() == classLocationFields[0]) {
			saveButtonEnabled = (titleMenu.getEditor().getItem().toString().length() != 0 && sectionField.getText().length() != 0
					&& classDayMenu[0].getSelectedIndex() != 0 && classBeginFields[0].getText().length() != 0
					&& classEndFields[0].getText().length() != 0 && classLocationFields[0].getText().length() != 0);
			saveButton.setEnabled(saveButtonEnabled);
		}
		
		else if (e.getSource() == assignmentMenu || e.getSource() == assignmentWeightField) {
			if (gradingButtonsAllowed) {
				saveAssignmentTypeButton.setEnabled(assignmentMenu.getEditor().getItem().toString().length() != 0 && assignmentWeightField.getText().length() != 0);
			}
		}
		
		else if (e.getSource() == examMenu || e.getSource() == examWeightField) {
			if (gradingButtonsAllowed) {
				saveExamButton.setEnabled(examMenu.getEditor().getItem().toString().length() != 0 && examWeightField.getText().length() != 0);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == titleMenu) {
			gradingButtonsAllowed = false;
			saveAssignmentTypeButton.setEnabled(gradingButtonsAllowed);
			saveExamButton.setEnabled(gradingButtonsAllowed);
			if (titleMenu.getSelectedIndex() == 0){
				clearAllFields();
				courseMenuIndex = 0;
				setButtonEnablers(false);
			}
			else if (titleMenu.getSelectedIndex() != -1) {				// selectedIndex of -1 occurs if the entry has been edited; don't do anything!
				courseMenuIndex = titleMenu.getSelectedIndex();
				int curCourseIndex = courseMenuIndex - 1;					// -1 accounts for blank first entry in menu
				Course course = courseList[curCourseIndex];
				sectionField.setText(course.getSectionNumber());
				// Set valid fields
				for (int i = 0; i < course.getCourseTimes().length; i++){
					classDayMenu[i].setSelectedItem(course.getCourseTimes()[i].getDay());
					classBeginFields[i].setText(course.getCourseTimes()[i].getStartTime().getTime());
					classBeginTimeCheckBox[i].setSelected(course.getCourseTimes()[i].getStartTime().isPM());
					classEndFields[i].setText(course.getCourseTimes()[i].getEndTime().getTime());
					classEndTimeCheckBox[i].setSelected(course.getCourseTimes()[i].getEndTime().isPM());
					classLocationFields[i].setText(course.getCourseTimes()[i].getLocation());
				}
				// Clear empty fields
				for (int i = course.getCourseTimes().length; i < NUM_CLASS_TIMES; i++) {
					classDayMenu[i].setSelectedIndex(0);
					classBeginFields[i].setText("");
					classBeginTimeCheckBox[i].setSelected(false);
					classEndFields[i].setText("");
					classEndTimeCheckBox[i].setSelected(false);
					classLocationFields[i].setText("");
				}
				setButtonEnablers(true);
				
				clearGradingInfo();
				if (course.getAssignmentTypes() != null) {
					fillGradingInfo(curCourseIndex);
				}
				gradingButtonsAllowed = true;
				
				// Fill in instructor and TA info
				if (course.getInstructors()[0] != null) {
					instructorNameField.setText(course.getInstructors()[0].getName());
					instructorLocationField.setText(course.getInstructors()[0].getOfficeLocation());
					instructorEmailField.setText(course.getInstructors()[0].getEmail());
					for (int i = 0; i < course.getInstructors()[0].getOfficeHours().length; i++) {
						instructorDayMenu[i].setSelectedItem(course.getInstructors()[0].getOfficeHours()[i].getDay());
						instructorBeginFields[i].setText(course.getInstructors()[0].getOfficeHours()[i].getStartTime().getTime());
						instructorBeginTimeCheckBox[i].setSelected(course.getInstructors()[0].getOfficeHours()[i].getStartTime().isPM());
						instructorEndFields[i].setText(course.getInstructors()[0].getOfficeHours()[i].getEndTime().getTime());
						instructorEndTimeCheckBox[i].setSelected(course.getInstructors()[0].getOfficeHours()[i].getEndTime().isPM());
                                                instructorDayMenu[i].setEnabled(true);
                                                instructorBeginFields[i].setEnabled(true);
                                                instructorBeginTimeCheckBox[i].setEnabled(true);
                                                instructorEndFields[i].setEnabled(true);
                                                instructorEndTimeCheckBox[i].setEnabled(true);
					}
					for(int i = course.getInstructors()[0].getOfficeHours().length; i < NUM_INSTRUCTOR_HOURS; i++) {
						instructorDayMenu[i].setSelectedItem(0);
						instructorBeginFields[i].setText("");
						instructorBeginTimeCheckBox[i].setSelected(false);
						instructorEndFields[i].setText("");
						instructorEndTimeCheckBox[i].setSelected(false);
                                                instructorDayMenu[i].setEnabled(true);
                                                instructorBeginFields[i].setEnabled(true);
                                                instructorBeginTimeCheckBox[i].setEnabled(true);
                                                instructorEndFields[i].setEnabled(true);
                                                instructorEndTimeCheckBox[i].setEnabled(true);
					}
                                        instructorNameField.setEnabled(true);
                                        instructorLocationField.setEnabled(true);
                                        instructorEmailField.setEnabled(true);                                      
				}
				else {
					clearInstructorFields();
				}
				
				if (course.getInstructors()[1] != null) {
					TA1NameField.setText(course.getInstructors()[1].getName());
					TA1LocationField.setText(course.getInstructors()[1].getOfficeLocation());
					TA1EmailField.setText(course.getInstructors()[1].getEmail());
					for (int i = 0; i < course.getInstructors()[1].getOfficeHours().length; i++) {
						TA1DayMenu[i].setSelectedItem(course.getInstructors()[1].getOfficeHours()[i].getDay());
						TA1BeginFields[i].setText(course.getInstructors()[1].getOfficeHours()[i].getStartTime().getTime());
						TA1BeginTimeCheckBox[i].setSelected(course.getInstructors()[1].getOfficeHours()[i].getStartTime().isPM());
						TA1EndFields[i].setText(course.getInstructors()[1].getOfficeHours()[i].getEndTime().getTime());
						TA1EndTimeCheckBox[i].setSelected(course.getInstructors()[1].getOfficeHours()[i].getEndTime().isPM());
                                                TA1DayMenu[i].setEnabled(true);
                                                TA1BeginFields[i].setEnabled(true);
                                                TA1BeginTimeCheckBox[i].setEnabled(true);
                                                TA1EndFields[i].setEnabled(true);
                                                TA1EndTimeCheckBox[i].setEnabled(true);
					}
					for(int i = course.getInstructors()[1].getOfficeHours().length; i < NUM_TA_HOURS; i++) {
						TA1DayMenu[i].setSelectedItem(0);
						TA1BeginFields[i].setText("");
						TA1BeginTimeCheckBox[i].setSelected(false);
						TA1EndFields[i].setText("");
						TA1EndTimeCheckBox[i].setSelected(false);
                                                TA1DayMenu[i].setEnabled(true);
                                                TA1BeginFields[i].setEnabled(true);
                                                TA1BeginTimeCheckBox[i].setEnabled(true);
                                                TA1EndFields[i].setEnabled(true);
                                                TA1EndTimeCheckBox[i].setEnabled(true);
					}
                                        TA1NameField.setEnabled(true);
                                        TA1EmailField.setEnabled(true);
                                        TA1LocationField.setEnabled(true);
				}
				else {
					clearTA1Fields();
				}
					
				if (course.getInstructors()[2] != null) {
					TA2NameField.setText(course.getInstructors()[2].getName());
					TA2LocationField.setText(course.getInstructors()[2].getOfficeLocation());
					TA2EmailField.setText(course.getInstructors()[2].getEmail());
					for (int i = 0; i < course.getInstructors()[2].getOfficeHours().length; i++) {
						TA2DayMenu[i].setSelectedItem(course.getInstructors()[2].getOfficeHours()[i].getDay());
						TA2BeginFields[i].setText(course.getInstructors()[2].getOfficeHours()[i].getStartTime().getTime());
						TA2BeginTimeCheckBox[i].setSelected(course.getInstructors()[2].getOfficeHours()[i].getStartTime().isPM());
						TA2EndFields[i].setText(course.getInstructors()[2].getOfficeHours()[i].getEndTime().getTime());
						TA2EndTimeCheckBox[i].setSelected(course.getInstructors()[2].getOfficeHours()[i].getEndTime().isPM());
                                                TA2DayMenu[i].setEnabled(true);
                                                TA2BeginFields[i].setEnabled(true);
                                                TA2BeginTimeCheckBox[i].setEnabled(true);
                                                TA2EndFields[i].setEnabled(true);
                                                TA2EndTimeCheckBox[i].setEnabled(true);
					}
					for(int i = course.getInstructors()[2].getOfficeHours().length; i < NUM_TA_HOURS; i++) {
						TA2DayMenu[i].setSelectedItem(0);
						TA2BeginFields[i].setText("");
						TA2BeginTimeCheckBox[i].setSelected(false);
						TA2EndFields[i].setText("");
						TA2EndTimeCheckBox[i].setSelected(false);
                                                TA2DayMenu[i].setEnabled(true);
                                                TA2BeginFields[i].setEnabled(true);
                                                TA2BeginTimeCheckBox[i].setEnabled(true);
                                                TA2EndFields[i].setEnabled(true);
                                                TA2EndTimeCheckBox[i].setEnabled(true);
					}
                                        TA2NameField.setEnabled(true);
                                        TA2EmailField.setEnabled(true);
                                        TA2LocationField.setEnabled(true);
				}
				else {
					clearTA2Fields();
				}
			}
		}
		else if (e.getSource() == classDayMenu[0]){
			saveButtonEnabled = (titleMenu.getSelectedItem().toString().length() != 0 && sectionField.getText().length() != 0
					&& classDayMenu[0].getSelectedIndex() != 0 && classBeginFields[0].getText().length() != 0
					&& classEndFields[0].getText().length() != 0 && classLocationFields[0].getText().length() != 0);
			saveButton.setEnabled(saveButtonEnabled);
		}
		
		else if (e.getSource() == assignmentMenu) {
			if (!gradingButtonsAllowed) {
				return;
			}
			if (assignmentMenu.getSelectedIndex() == 0) {
				assignmentMenuIndex = 0;
				assignmentWeightField.setText("");
				saveAssignmentTypeButton.setEnabled(false);
				deleteAssignmentTypeButton.setEnabled(false);
			}
			else if (assignmentMenu.getSelectedIndex() != -1) {		// selectedIndex of -1 occurs if the entry has been edited; don't do anything!
				assignmentMenuIndex = assignmentMenu.getSelectedIndex();
				int assignmentIndex = assignmentMenuIndex - 1;
				assignmentWeightField.setText(Double.toString(courseList[courseMenuIndex-1].getAssignmentTypes()[assignmentIndex].getWeight()));
				deleteAssignmentTypeButton.setEnabled(true);
				saveAssignmentTypeButton.setEnabled(true);
			}
		}
		
		else if (e.getSource() == examMenu) {
			if (!gradingButtonsAllowed) {
				return;
			}
			if (examMenu.getSelectedIndex() == 0) {
				examMenuIndex = 0;
				examWeightField.setText("");
				deleteExamButton.setEnabled(false);
				saveExamButton.setEnabled(false);
			}
			else if (examMenu.getSelectedIndex() != -1) {		// selectedIndex of -1 occurs if the entry has been edited; don't do anything!
				examMenuIndex = examMenu.getSelectedIndex();
				int examIndex = examMenuIndex - 1;
				examWeightField.setText(Double.toString(courseList[courseMenuIndex-1].getAssignmentTypes()[(assignmentMenu.getItemCount()-1) + examIndex].getWeight()));
				deleteExamButton.setEnabled(true);
				saveExamButton.setEnabled(true);
			}
		}
	}
	
	public Course[] returnCourses() {
		return this.courseList;
	}
}
