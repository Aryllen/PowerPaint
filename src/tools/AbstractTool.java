/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.geom.Point2D;
import java.net.URL;
import java.util.Locale;
import javax.swing.ImageIcon;

/**
 * Abstract class for PowerPoint tools.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public abstract class AbstractTool implements Tool
{       
    /** Initial point.   */
    private final Point2D myInitialPoint;
    
    /** Final point. */
    private final Point2D myFinalPoint;
    
    /** Tells if a tool geometry allows for filling. */
    private final boolean myShapeIsFillable;
    
    /** Value for the tool to create a new shape. */
    private boolean myIsNewShape;
    
    /**
     * Constructor.
     * 
     * @param theFillableAnswer true if the tool geometry allows for filling
     */
    protected AbstractTool(final boolean theFillableAnswer)
    {        
        myInitialPoint = DEFAULT_START_POINT;
        myFinalPoint = DEFAULT_END_POINT;
        
        myShapeIsFillable = theFillableAnswer;
        
        // On initialization, shape would always be new
        myIsNewShape = true;  
    }
    
    // Setters
    
    /**
     * Sets initial point.
     * 
     * @param theOtherPoint the point
     */
    @Override
    public void setInitialPoint(final Point2D theOtherPoint)
    {
        // Only called with a default point or a new point that's been created. 
        // If called with a point that could change, would need to create defensive
        // copy first. However, since this isn't the case, this should reduce some lag.
        myInitialPoint.setLocation(theOtherPoint);
    }
    
    /**
     * Sets final point.
     * 
     * @param theOtherPoint the point
     */
    @Override
    public void setFinalPoint(final Point2D theOtherPoint)
    {
        // Only called with a default point or a new point that's been created. 
        // If called with a point that could change, would need to create defensive
        // copy first. However, since this isn't the case, this should reduce some lag.
        myFinalPoint.setLocation(theOtherPoint);
    }
    
    /**
     * Sets the value for if the tool should be creating a new shape.
     * 
     * @param theAnswer true if the tool should be creating a new shape
     */
    @Override
    public void setIsNewShape(final boolean theAnswer)
    {
        myIsNewShape = theAnswer;
    }    
    
    // Getters
    
    /**
     * Returns the name of the tool.
     * 
     * @return tool name
     */
    @Override
    public String getName()
    {
        return getClass().getSimpleName();
    }
    
    /**
     * Returns tool icon.
     * 
     * @return tool icon
     */
    @Override
    public ImageIcon getIcon()
    {
        final StringBuilder builder = new StringBuilder(128);
        
        builder.append(getName().toLowerCase(Locale.ENGLISH));
        builder.append(".gif");
        
        final URL url = getClass().getResource(builder.toString());
                
        return new ImageIcon(url);
    }
    
    /**
     * Returns the initial point.
     * 
     * @return initial point
     */
    @Override
    public Point2D getInitialPoint()
    {        
        return new Point2D.Double(myInitialPoint.getX(), myInitialPoint.getY());
    }
    
    /**
     * Returns the final point.
     * 
     * @return final point
     */
    @Override
    public Point2D getFinalPoint()
    {
        return new Point2D.Double(myFinalPoint.getX(), myFinalPoint.getY());
    }
    
    /**
     * Returns the value for if the tool should be creating a new shape.
     * 
     * @return true if the tool should be creating a new shape
     */
    public boolean isNewShape()
    {
        return myIsNewShape;
    }
    
    /**
     * Returns the value for if the tool geometry allows for filling.
     * 
     * @return true if geometry can be filled
     */
    public boolean isFillable()
    {
        return myShapeIsFillable;
    }
   
    /**
     * Returns location of upper left corner for shape.
     * 
     * @param thePoint1 the first point
     * @param thePoint2 the second point
     * @return upper left corner location
     */
    protected Point2D getStartLocation(final Point2D thePoint1, final Point2D thePoint2)
    {
        final double startX;
        final double startY;
        
        if (thePoint2.getX() < thePoint1.getX())
        {
            if (thePoint2.getY() < thePoint1.getY())
            {
                startX = thePoint2.getX();
                startY = thePoint2.getY();
            }
            else 
            {
                startX = thePoint2.getX();
                startY = thePoint1.getY();
            }
        }
        else
        {
            if (thePoint2.getY() < thePoint1.getY())
            {
                startX = thePoint1.getX();
                startY = thePoint2.getY();
            }
            else
            {
                startX = thePoint1.getX();
                startY = thePoint1.getY();
            }
        }
        return new Point2D.Double(startX, startY);
    }
}
