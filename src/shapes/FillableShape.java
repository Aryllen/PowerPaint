/*
 * TCSS 305 - Autumn 2017
 * Assignment 5 - PowerPaint
 */

package shapes;

import java.awt.Color;
import java.awt.Shape;

/**
 * Class for fillable PaintShapes.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public class FillableShape extends AbstractPaintShape implements PaintShape
{
    /** These types of shapes are fillable. */
    private static final boolean FILLABLE = true;
    
    /** Fill color. */
    private final Color myFillColor;
    
    /** Shows if a shape is filled or not. */
    private final boolean myShapeIsFilled;
    
    /**
     * Constructor.
     * 
     * @param theShape the geometry of the shape
     * @param theDrawColor the stroke color
     * @param theFillColor the fill color
     * @param theThickness the stroke thickness
     * @param theFilledAnswer true if shape is filled
     */
    public FillableShape(final Shape theShape, final Color theDrawColor, 
                         final Color theFillColor, final int theThickness, 
                         final boolean theFilledAnswer)
    {
        super(theShape, theDrawColor,  theThickness, FILLABLE);
        
        myFillColor = theFillColor;
        myShapeIsFilled = theFilledAnswer;
    }
    
    /**
     * Returns the fill color.
     * 
     * @return fill color
     */
    @Override
    public Color getFillColor()
    {
        return myFillColor;
    }
    
    /**
     * Returns whether the shape is filled or not.
     * 
     * @return true if filled
     */
    @Override
    public boolean isFilled()
    {
        return myShapeIsFilled;
    }
}
