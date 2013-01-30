/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 */
package classy;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Nate
 */
public class BackEnd 
{
    public static void DrawCalendar(Graphics g, int height, int width)
    {
        int widthIncrement = width /6;
        
        g.drawString("Monday", 5*widthIncrement / 4 , 25);
        g.drawString("Tuesday", 9*widthIncrement / 4, 25);
        g.drawString("Wednesday", 13*widthIncrement / 4 - 5, 25);
        g.drawString("Thursday", 17 * widthIncrement / 4, 25);
        g.drawString("Friday", 21 * widthIncrement / 4 + 5, 25);
        //g.drawString("Saturday", 25 * widthIncrement / 4, 25);
        //g.drawString("Sunday", 29 * widthIncrement / 4, 25);
       
        g.drawLine(0, 35, width, 35);
        
        for(int i = 0; i <= 7; i++)
        {
            g.drawRect((i+1/4) * widthIncrement, 0, widthIncrement, height-5);
       
        }
        
        DrawTimes(g, widthIncrement / 4 + 10, 55);
    }
    
    public static void DrawTimes(Graphics g, int startX, int startY)
    {
      
        int Yincrement = 100;
        g.drawString("8:00A", startX, startY);
        startY += Yincrement;
        
        g.drawLine(0, startY-20, 1111, startY-20);
        g.drawString("9:00A", startX, startY);
        startY += Yincrement;

        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("10:00A", startX, startY);
        startY += Yincrement;

        g.drawLine(0, startY-20, 1111, startY-20);
                
        g.drawString("11:00A", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);
                        
        g.drawString("12:00P", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("1:00P", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("2:00P", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("3:00P", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("4:00P", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("5:00P", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("6:00P", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("7:00P", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("8:00P", startX, startY);
        startY += Yincrement;
        g.drawLine(0, startY-20, 1111, startY-20);

        g.drawString("9:00P", startX, startY);

    }
    
    public static int calculateXLocation(String day)
    {
        int increment = 1111/8;
        
        
        if (day.equals("Monday"))
            return increment;
        if (day.equals("Tuesday"))
            return 2*increment;
        if (day.equals("Wednesday"))
            return 3*increment;
        if (day.equals("Thursday"))
            return 4*increment;
        if (day.equals("Friday"))
            return 5*increment;
//        if (day == "Saturday")
//            return 6*increment;
//        if (day == "Sunday")
//            return 7*increment;
        
        else
            return 0;
    }
    
    public static int calculateYLocation(Time startTime)
    {
        int starts = 33;
        
        
        if (startTime.isPM() == false)
        {
            return ((100*(startTime.getHour()-8))+starts+(startTime.getMinute()*100/60));
        }
        
        
        
        else
        {
            if (startTime.getHour() == 12)
            {
                
                return 400+starts+((startTime.getMinute()*100/60));
            }
            
           return  500+(100*(startTime.getHour()-1))+(starts+(startTime.getMinute()*100/60));
        }
        
        
        
    }
    
    public static int calculateButtonHeight(Time startTime, Time endTime)
    {
        //100 pixels == 1 hour
        
        
        int startHour = startTime.getHourTwentyFour();
        int startMinute = startTime.getMinute();
        
        int endHour = endTime.getHourTwentyFour();
        int endMinute = endTime.getMinute();
        
        int minuteDifference = endMinute - startMinute;
        
        if (minuteDifference < 0)
        {
            minuteDifference += 60;
            return((endHour-startHour-1) * 100) + minuteDifference*100/60 + 5;
        }
        
        else
        {
            return ((endHour-startHour) * 100) + minuteDifference*100/60 + 5;
        }
        
        
    }
    
    public static boolean validAssignmentName(String name)
    {
        if( name.length() < 0  || name.length() > 30)
            return false;
        else
            return true;
    }
    
    public static boolean validDate(String dateString)
    {
        
        if ((dateString.length() != 10) || dateString.charAt(2) != '/' || dateString.charAt(5) != '/')
            return false;
        
        if(dateString.charAt(0) < '0' || dateString.charAt(0) > '1')
            return false;
        if(dateString.charAt(1) < '0' || dateString.charAt(1) > '2')
            return false;
        if(dateString.charAt(3) < '0' || dateString.charAt(3) > '3')
            return false;
        if(dateString.charAt(4) < '0' || dateString.charAt(4) > '9')
            return false;
        if(dateString.charAt(6) < '0' || dateString.charAt(6) > '9')
            return false;
        if(dateString.charAt(7) < '0' || dateString.charAt(7) > '9')
            return false;
        if(dateString.charAt(8) < '0' || dateString.charAt(8) > '9')
            return false;
        if(dateString.charAt(9) < '0' || dateString.charAt(9) > '9')
            return false;

        
        return true;

    }
    
    public static boolean validGrade(String grade)
    {

       for(int i = 0; i < grade.length(); i++)
       {
           if(grade.charAt(i) < '0' || grade.charAt(i) > '9')
           {
               if(grade.charAt(i) != '.')
                   return false;
           }
       }
       
       return true;
    }
    
    public static double convertGrade(String grade)
    {
        return Double.parseDouble(grade);  
    }
    
    public static Date convertDate(String date)
    {
        
        int month = ((date.charAt(0) - '0') * 10) + (date.charAt(1) - '0');
        int day = (date.charAt(3) - '0')*10 + (date.charAt(4) - '0');
        
        int year = (date.charAt(6) - '0')*1000 + (date.charAt(7) - '0') * 100 +
                (date.charAt(8) - '0') * 10 + (date.charAt(9) - '0');
        
        Date covertedDate = new Date(year, month, day);
        return covertedDate;
    }
    

    
    
}
