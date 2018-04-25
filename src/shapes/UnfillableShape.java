/*
 * TCSS 305 - Autumn 2017
 * Assignment 5 - PowerPaint
 */

package shapes;

import java.awt.Color;
import java.awt.Shape;

/**
 * Class for unfillable PaintShapes.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public class UnfillableShape extends AbstractPaintShape implements PaintShape
{
    /** These types of shapes are not fillable. */
    private static final boolean FILLABLE = false;
    
    /**
     * Constructor.
     * 
     * @param theShape the geometry of the shape
     * @param theDrawColor the stroke color
     * @param theThickness the stroke thickness
     */
    public UnfillableShape(final Shape theShape, final Color theDrawColor, 
                           final int theThickness)
    {
        super(theShape, theDrawColor, theThickness, FILLABLE);
    }
}
