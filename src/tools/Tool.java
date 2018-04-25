/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;
import javax.swing.ImageIcon;

/**
 * Interface for PowerPaint tools.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public interface Tool
{
    /** Default point. */
    Point2D DEFAULT_START_POINT = new Point2D.Double(-5, -5);
    
    /** Default end point. */
    Point2D DEFAULT_END_POINT = new Point2D.Double(-10, -10);
    
    /**
     * Sets the initial point.
     * 
     * @param theOtherPoint the first point
     */
    void setInitialPoint(Point2D theOtherPoint);
    
    /** 
     * Sets the final point. 
     * 
     * @param theOtherPoint the last point
     */
    void setFinalPoint(Point2D theOtherPoint);
    
    /**
     * Sets the value for if the tool should be creating a new shape.
     * 
     * @param theAnswer true if the tool should be creating a new shape
     */
    void setIsNewShape(boolean theAnswer);
    
    /**
     * Returns the name of the tool.
     * 
     * @return name of the tool
     */
    String getName();
    
    /** 
     * Returns the icon.
     * 
     * @return icon
     */
    ImageIcon getIcon();
    
    /**
     * Returns the shape to paint.
     * 
     * @return shape
     */
    Shape getShape();
    
    /**
     * Gets the initial point.
     * 
     * @return the initial point
     */
    Point2D getInitialPoint();
    
    /**
     * Gets the final point.
     * 
     * @return the final point
     */
    Point2D getFinalPoint();
    
    /**
     * Returns the value for if the tool should be creating a new shape.
     * 
     * @return true if the tool should be creating a new shape
     */
    boolean isNewShape();
    
    /**
     * Returns the value for if the tool geometry allows for filling.
     * 
     * @return true if geometry can be filled
     */
    boolean isFillable();
}
