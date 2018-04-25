/*
 * TCSS 305 - Autumn 2017
 * Assignment 5 - PowerPaint
 */

package shapes;

import java.awt.Color;
import java.awt.Shape;

/**
 * Class that holds values for a painted shape.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public abstract class AbstractPaintShape implements PaintShape
{   
    /** Default fill color. */
    private static final Color DEFAULT_FILL_COLOR = new Color(232, 211, 162);
    
    /** Ability of the shape to be filled. */
    private final boolean myShapeIsFillable;
    
    /** Stroke color. */
    private final Color myDrawColor;
    
    /** The shape geometry. */
    private Shape myShape;
    
    /** Stroke thickness. */
    private final int myThickness; 
    
    /**
     * Constructor for a filled shape.
     * 
     * Note: Shapes that are not actually filled, such as lines or paths, can still be set to
     * filled and have a fill color in this constructor.  
     * 
     * @param theShape the geometry of the shape
     * @param theDrawColor the stroke color
     * @param theThickness the stroke thickness
     * @param theFillableAnswer true if the shape can be filled
     */
    protected AbstractPaintShape(final Shape theShape, final Color theDrawColor, 
                                 final int theThickness, final boolean theFillableAnswer)
    {
        myShape = theShape;
        myDrawColor = theDrawColor;
        myThickness = theThickness;
        myShapeIsFillable = theFillableAnswer;
    }
    
    // Setters
    
    /** 
     * Sets the shape geometry.
     * 
     * @param theShape the geometry
     */
    @Override
    public void setShape(final Shape theShape)
    {
        myShape = theShape;
    }
    
    /**
     * Returns the shape geometry.
     * 
     * @return shape geometry
     */
    @Override
    public Shape getShape()
    {
        return myShape;
    }   
    
    /**
     * Returns the stroke color.
     * 
     * @return stroke color
     */
    @Override
    public Color getDrawColor()
    {
        return myDrawColor;
    }
    
    /**
     * Default implementation for retrieving the fill color.
     * Override in shape classes that can be filled.
     * 
     * @return Default color
     */
    @Override
    public Color getFillColor()
    {
        return DEFAULT_FILL_COLOR;
    }
    
    /**
     * Returns the stroke thickness. 
     * 
     * @return stroke thickness
     */
    @Override
    public int getThickness()
    {
        return myThickness;
    }
    
    /**
     * Default implementation to check if the shape is filled. 
     * Override in shape classes that can be filled.
     * 
     * @return false
     */
    @Override
    public boolean isFilled()
    {
        return false;
    }

    /**
     * Returns whether the shape is fillable or not.
     * 
     * @return true if the shape can be filled
     */
    @Override
    public boolean isFillable()
    {
        return myShapeIsFillable;
    }
}
