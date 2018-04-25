/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package view;

import java.awt.EventQueue;

/**
 * Driver for PowerPaint program.
 * 
 * @author Nicole Kauer
 * @version 11 November 2017
 */
public final class PowerPaintMain
{
    /**
     * Private constructor to prevent instantiation.
     */
    private PowerPaintMain()
    {
        // Do nothing
    }
    
    /**
     * Starts the PowerPaint GUI.
     * 
     * @param theArgs not utilized
     */
    public static void main(final String[] theArgs)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new PowerPaintGUI();
            }
        });

    }

}
