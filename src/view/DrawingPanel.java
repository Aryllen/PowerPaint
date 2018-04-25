/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import shapes.FillableShape;
import shapes.PaintShape;
import shapes.UnfillableShape;
import tools.Line;
import tools.Tool;

/**
 * Class for PowerPaint's drawing panel.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public final class DrawingPanel extends JPanel 
{
    /** Generated serialization ID. */
    private static final long serialVersionUID = 1L;
    
    /** Preferred panel size. */
    private static final Dimension PREFERRED_SIZE = new Dimension(200, 200);
    
    /** Default stroke thickness. */
    private static final int DEFAULT_THICKNESS = 5;
    
    /** Default drawing color. */
    private static final Color DEFAULT_DRAW_COLOR = new Color(51, 0, 111);
    
    /** Default fill color. */
    private static final Color DEFAULT_FILL_COLOR = new Color(232, 211, 162);
    
    /** List to hold all painted tools. */
    private final List<PaintShape> myPaintedShapes;
    
    /** Current tool in use. */
    private Tool myCurrentTool;
    
    /** Current stroke color. */
    private Color myCurrentStrokeColor;
    
    /** Current fill color. */
    private Color myCurrentFillColor;
    
    /** Current stroke thickness. */
    private int myCurrentThickness;
    
    /** Status for if tool is filled or not. */
    private Boolean myShapeIsFilled;
    
    /** Clear panel menu item. */
    private JMenuItem myClearItem;
    
    /** Current shape being painted. */
    private PaintShape myLatestShape;
    
    /**
     *  Constructor for the drawing panel.
     */
    public DrawingPanel()
    {
        super();
        
        myPaintedShapes = new ArrayList<PaintShape>();
        myCurrentTool = new Line(); // Default tool upon opening program should be line
        myCurrentStrokeColor = DEFAULT_DRAW_COLOR;
        myCurrentFillColor = DEFAULT_FILL_COLOR;
        myCurrentThickness = DEFAULT_THICKNESS;
        myShapeIsFilled = false;
        
        // Default shape; will change upon first mouse press
        myLatestShape = new UnfillableShape(myCurrentTool.getShape(), 
                                              myCurrentStrokeColor, myCurrentThickness);

        setup();
    }
    
    // Setup methods
    
    /**
     * Sets up the drawing panel.
     */
    private void setup()
    {
        createClearItem();
        
        addMouseListener(new PanelMouseListener());
        addMouseMotionListener(new PanelMouseListener());   
        setPreferredSize(PREFERRED_SIZE);
        setBackground(Color.WHITE);
    }
    
    /** 
     * Creates clear panel item.
     */
    private void createClearItem()
    {
        myClearItem = new JMenuItem("Clear");
        myClearItem.setEnabled(false);
        
        /**
         * Listener class for clear panel item.
         */
        class ClearListener implements ActionListener
        {
            /**
             * Clears the panel of all shapes.
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent)
            {                
                myPaintedShapes.clear();
                myClearItem.setEnabled(false);
                
                repaint();
            }
        }
        
        myClearItem.addActionListener(new ClearListener());
    }
    
    // Getters
    
    /**
     * Returns the clear panel menu item.
     * 
     * @return the clear panel menu item
     */
    public JMenuItem getClearItem()
    {
        return myClearItem;
    }
    
    /**
     * Gets the current stroke color.
     * 
     * @return the current stroke color
     */
    public Color getStrokeColor()
    {
        return myCurrentStrokeColor;
    }
    
    /**
     * Gets the current fill color.
     * 
     * @return the current fill color.
     */
    public Color getFillColor()
    {
        return myCurrentFillColor;
    }
    
    /**
     * Gets the current tool.
     * 
     * @return the current tool
     */
    public Tool getCurrentTool()
    {
        return myCurrentTool;
    }
    
    // Setters
    
    /**
     * Sets current Tool.
     * 
     * @param theTool the Tool to change to
     */
    public void setCurrentTool(final Tool theTool)
    {
        myCurrentTool = theTool;
    }
    
    /**
     * Sets stroke thickness.
     * 
     * @param theThickness the stroke thickness
     */
    public void setThickness(final int theThickness)
    {
        myCurrentThickness = theThickness;
    }
    
    /**
     * Sets current stroke color.
     *
     * @param theStrokeColor the stroke color
     */
    public void setStrokeColor(final Color theStrokeColor)
    {
        myCurrentStrokeColor = theStrokeColor;
    }
    
    /**
     * Sets current fill color.
     * 
     * @param theFillColor the fill color
     */
    public void setFillColor(final Color theFillColor)
    {
        myCurrentFillColor = theFillColor;
    }    
    
    /**
     * Set the value for whether shape is filled or not.
     * 
     * @param theAnswer true if filled
     */
    public void setIsFilled(final Boolean theAnswer)
    {
        myShapeIsFilled = theAnswer;
    }

    /**
     * Sets the clear panel menu item.
     * 
     * @param theItem the clear panel menu item
     */
    public void setClearItem(final JMenuItem theItem)
    {
        myClearItem = theItem;
    }
    
    // Graphics
    
    /**
     * Paints the current geometry on the panel.
     * 
     * @param theGraphics The graphics context to use for painting.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) 
    {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Paint previous shapes stored in myPaintedShapes ArrayList
        for (final PaintShape s : myPaintedShapes)
        {
            // Takes care of the filled shapes
            if (s.isFillable() && s.isFilled())
            {
                g2d.setPaint(s.getFillColor());
                g2d.fill(s.getShape());
            }
            
            // Takes care of the outline/line shapes
            g2d.setPaint(s.getDrawColor());
            g2d.setStroke(new BasicStroke(s.getThickness()));
            g2d.draw(s.getShape());
        }
    }    
    
    //---------------------------------------------------------------------------------------
    
    /**
    * Mouse input and motion listener class for PowerPaint.
    *  
    * @author Nicole Kauer
    * @version 22 November 2017
    */
    private class PanelMouseListener implements MouseInputListener, MouseMotionListener
    {
        /** 
         * Do nothing when the mouse is clicked. 
         */
        @Override
        public void mouseClicked(final MouseEvent theEvent)
        {   
            // Do nothing 
        }

        /** 
         * Change the cursor when the mouse enters the panel. 
         */
        @Override
        public void mouseEntered(final MouseEvent theEvent)
        {
            // Change cursor to crosshair
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }

        /** 
         * Change the cursor back to the system default when mouse leaves panel. 
         */
        @Override
        public void mouseExited(final MouseEvent theEvent)
        {
            // Set back to the default cursor
            setCursor(Cursor.getDefaultCursor());
        }

        /** 
         * Set the first point of the line to the point where the mouse was pressed.
         */
        @Override
        public void mousePressed(final MouseEvent theEvent)
        {                     
            // Only check if painted shapes was empty before adding shape to array. 
            // This makes it so the clear button should only update its enabling on the 
            // first pass of each new set of shapes.
            if (myPaintedShapes.isEmpty())
            {
                myClearItem.setEnabled(true);
            }
            
            // Set the first point location
            myCurrentTool.setInitialPoint(new Point2D.Double(theEvent.getX(), 
                                                             theEvent.getY()));
            
            myCurrentTool.setFinalPoint(new Point2D.Double(theEvent.getX(), theEvent.getY()));
            
            if (myCurrentTool.isFillable())
            {
                myLatestShape = new FillableShape(myCurrentTool.getShape(), 
                                                  myCurrentStrokeColor, myCurrentFillColor, 
                                                  myCurrentThickness, myShapeIsFilled);
            }
            else
            {
                myLatestShape = new UnfillableShape(myCurrentTool.getShape(), 
                                                        myCurrentStrokeColor, 
                                                        myCurrentThickness);
            }
            
            myPaintedShapes.add(myLatestShape);
            
            repaint();
        }

        /** 
         * Set the second point of the line to the point where the mouse was released. 
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent)
        {                               
            // Value to let tool know to create a new shape, if needed by tool.
            // Currently only used by Pencil tool.
            myCurrentTool.setIsNewShape(true);
            
            // Reset end points to default
            myCurrentTool.setInitialPoint(Tool.DEFAULT_START_POINT);
            myCurrentTool.setFinalPoint(Tool.DEFAULT_END_POINT);
        }

        /**
         * Set the second point of the line to the point where the mouse was dragged to.
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent)
        {
            // Tell tool to not create new shape. Only need to change once, though, so check
            // on first pass if the shape is new. If yes, change. If no, skip over.
            // This is only being used by the Pencil tool, at the moment.
            if (myCurrentTool.isNewShape())
            {
                myCurrentTool.setIsNewShape(false);
            }
            
            // Set end point location
            myCurrentTool.setFinalPoint(new Point2D.Double(theEvent.getX(), theEvent.getY()));
            
            myLatestShape.setShape(myCurrentTool.getShape());
            
            repaint();            
        }

        /**
         * Do nothing if the mouse is moved.
         */
        @Override
        public void mouseMoved(final MouseEvent theEvent)
        {
            // Do nothing
            
        }   
    }
}