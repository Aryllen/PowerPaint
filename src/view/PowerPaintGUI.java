/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package view;

import java.awt.BorderLayout;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import tools.Ellipse;
import tools.Line;
import tools.Pencil;
import tools.Rectangle;
import tools.RoundRectangle;

/**
 * Graphical user interface for PowerPaint.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public final class PowerPaintGUI extends JFrame 
{
    /** Generated serialization ID. */
    private static final long serialVersionUID = 3650553337266286532L;
    
    /**
     * Actions for the tool and menu bar buttons.
     */
    private static final List<ToolButtonAction> BUTTON_ACTIONS 
                                                    = new ArrayList<ToolButtonAction>();
    
    /** URL for icon image. */
    private final URL myIconURL;
    
    /** Drawing panel. */
    private final DrawingPanel myDrawPanel;    
    
    /**
     * Constructor for PowerPaintGUI class.
     */
    public PowerPaintGUI()
    {
        super(); // Calls JFrame constructor
        
        myDrawPanel = new DrawingPanel();
        myIconURL = getClass().getResource("powerpainticontransparent.png");
        
        setup(); // Sets up the GUI
    }
    
    /**
     * Sets up the GUI.
     */
    private void setup()
    {
        // Set title and icon
        setTitle("PowerPaint");
        final ImageIcon iconImage = new ImageIcon(myIconURL);
        setIconImage(iconImage.getImage());
                
        // Create drawing panel, add mouse listener, add to frame
        add(myDrawPanel, BorderLayout.CENTER); 
        
        // Create button actions
        createButtonActions();
        
        // Add menu and toolbar to frame
        setJMenuBar(new GUIMenuBar(myDrawPanel, BUTTON_ACTIONS, myIconURL)); 
        add(createToolBar(), BorderLayout.SOUTH); 
        
        
        // Set a minimum size for the window via pack
        pack();  
        setMinimumSize(this.getSize());
        
        // Set starting sizes and location; could be done better
        setLocationRelativeTo(null);
        
        // Close on exit
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Make window visible
        setVisible(true);
    }
    
    /**
     * Creates actions for the tool and menu bar buttons.
     */
    private void createButtonActions()
    {
        BUTTON_ACTIONS.add(new ToolButtonAction(new Line(), myDrawPanel));
        BUTTON_ACTIONS.add(new ToolButtonAction(new Pencil(), myDrawPanel));
        BUTTON_ACTIONS.add(new ToolButtonAction(new Rectangle(), myDrawPanel));
        BUTTON_ACTIONS.add(new ToolButtonAction(new RoundRectangle(), myDrawPanel));
        BUTTON_ACTIONS.add(new ToolButtonAction(new Ellipse(), myDrawPanel));
    }
    
    /**
     * Creates the PowerPaint toolbar.
     * 
     * @return the toolbar
     */
    private JToolBar createToolBar()
    {        
        // The toolbar to be created
        final JToolBar toolbar = new JToolBar();
        
        final ButtonGroup buttonGroup = new ButtonGroup();
        
        for (final ToolButtonAction buttonAction : BUTTON_ACTIONS)
        {
            final JToggleButton toggleButton = new JToggleButton(buttonAction);
            buttonGroup.add(toggleButton);
            toolbar.add(toggleButton);            
        }
        
        return toolbar;
    }
}
