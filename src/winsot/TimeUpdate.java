/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package winsot;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JLabel;

/**
 *
 * @author kevin
 */
public class TimeUpdate extends Thread
{
    public static javax.swing.JLabel TimeLabel;
    boolean wanttorun = true;
    
    boolean systemcheckwanttorun = true;
    
    
    
    public TimeUpdate (JLabel x)
    {
        TimeLabel = x;
    }
    
    
    
    public void run()
    {
        systemcheckwanttorun = true;

        
        
        Kernel32.SYSTEM_POWER_STATUS batteryStatus = new Kernel32.SYSTEM_POWER_STATUS(); //new instance of class
        String SystemPowerStatus = batteryStatus.getACLineStatusString();
        
        
        
    while (systemcheckwanttorun == true)
    {
        if (SystemPowerStatus == "Online")
        {
            wanttorun = false;
        }
        
        if (SystemPowerStatus == "Offline")
        {
            wanttorun = true;
        }
        
        while (wanttorun == true)
        {
        
        //count seconds
        try
        {
            Thread.sleep(1000); 
        }
        catch(InterruptedException exc)
                {
                    System.out.print("Could not sleep");
                }

        if (TimeStats.second <61) //add a second
        {
            TimeStats.second = TimeStats.second+1;
        }
        
        
        if (TimeStats.second == 61)
        {
            TimeStats.minute = TimeStats.minute + 1;
            TimeStats.second = 0;
        }
        
        
        if (TimeStats.minute == 61)
        {
            TimeStats.hour = TimeStats.hour+1;
            TimeStats.minute = 0;
        }
        
                
                
                
                
                
        
        TimeLabel.setText(TimeStats.hour+" hours "+TimeStats.minute+" minutes "+ +TimeStats.second +" seconds " + "on Battery so far.");
        //System.out.print(hour+" hours "+minute+" minutes "+ " seconds "+second + "on Battery so far.");
        
        }
        
        

        }
    }
        public void stoptime()
    {
        wanttorun = false;
        systemcheckwanttorun = false;
    }
        
    }
   




