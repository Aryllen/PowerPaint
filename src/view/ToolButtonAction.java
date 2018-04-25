/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import tools.Tool;

/**
 * Class for button listeners.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public class ToolButtonAction extends AbstractAction
{    
    /**
     * Generated serialization ID.
     */
    private static final long serialVersionUID = 8341326342363158255L;
    
    /** 
     * DrawingPanel tools will be used on.
     */
    private final DrawingPanel myDrawPanel;
    
    /**
     * Tool associated with button action.
     */
    private final Tool myTool;
       
    /**
     * Constructs a button action for a tool.
     * 
     * @param theTool the Tool for the button
     * @param thePanel the DrawingPanel associated with the button action
     */
    public ToolButtonAction(final Tool theTool, final DrawingPanel thePanel)
    {
        super();
        
        myTool = theTool;
        myDrawPanel = thePanel;
        
        // Store name and icon
        putValue(Action.NAME, theTool.getName());
        putValue(Action.SMALL_ICON, theTool.getIcon());
        
        // Coordinate buttons
        putValue(Action.SELECTED_KEY, true); 
    }
    
    /**
     * Action to be completed when selected.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent)
    {
        // Change current tool
        myDrawPanel.setCurrentTool(myTool);
    }
}
