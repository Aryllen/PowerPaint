/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Line tool class.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public final class Line extends AbstractTool implements Tool
{
    /** Line tool geometry is not fillable. */
    private static final boolean FILLABLE = false;
    
    /**
     * Constructor.
     */
    public Line()
    {
        super(FILLABLE);
    }
    
    /**
     * Returns the shape to be painted.
     * 
     * @return shape to paint
     */
    @Override
    public Shape getShape()
    {
        return new Line2D.Double(super.getInitialPoint(), super.getFinalPoint());
    }   
}
