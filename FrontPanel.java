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
public class FrontPanel extends JPanel
{
    private CalendarPanel panel;
    private EastPanel eastPanel;

    private JScrollPane pane;
    
    public String courseToDisplay;
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    
    public FrontPanel(Course[] courseList )
    {
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();     

        panel = new CalendarPanel(courseList);
        panel.setEastPanel(courseList);
        pane = new JScrollPane(panel);
                
        pane.setPreferredSize(new Dimension(847, 700));
        add(pane);
        
        constraints.gridx = 0;                   // define constraints for button
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1; 
        constraints.anchor = GridBagConstraints.NORTHWEST;
        layout.setConstraints(pane, constraints); 
        add(pane);


        eastPanel = panel.getEastPanel();
        
        constraints.gridx = 1;                   // define constraints for button
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;  
        constraints.anchor = GridBagConstraints.NORTHEAST;

        layout.setConstraints(eastPanel, constraints); 
        add(eastPanel);
    }
    
//    public CalendarPanel getCalendarPanel() {
//    	return this.panel;
//    }
    
}
