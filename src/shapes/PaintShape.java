/*
 * TCSS 305 - Autumn 2017
 * Assignment 5 - PowerPaint
 */

package shapes;

import java.awt.Color;
import java.awt.Shape;

/**
 * Interface for PaintShapes.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public interface PaintShape
{
    /**
     * Sets shape geometry.
     * 
     * @param theShape the geometry
     */
    void setShape(Shape theShape);
    
    /**
     * Returns the shape geometry.
     * 
     * @return shape geometry
     */
    Shape getShape();
 
    /**
     * Returns the stroke color.
     * 
     * @return stroke color
     */
    Color getDrawColor();
    
    /**
     * Returns the fill color.
     * 
     * @return fill color
     */
    Color getFillColor();
    
    /**
     * Returns the stroke thickness. 
     * 
     * @return stroke thickness
     */
    int getThickness();

    /**
     * Returns whether the shape is filled or not.
     * 
     * @return true if filled
     */
    boolean isFilled();
    
    /**
     * Returns whether or not the shape is fillable.
     * 
     * @return true if fillable
     */
    boolean isFillable();
}
