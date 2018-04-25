/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Ellipse tool class.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public final class Ellipse extends AbstractTool implements Tool
{    
    /** Ellipse tool geometry is fillable. */
    private static final boolean FILLABLE = true;
    
    /**
     * Constructor.
     */
    public Ellipse()
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
        final Point2D point1 = super.getInitialPoint();
        final Point2D point2 = super.getFinalPoint();
        
        final double height = Math.abs(point2.getY() - point1.getY());
        final double width = Math.abs(point2.getX() - point1.getX());
     
        final Point2D startPoint = super.getStartLocation(point1, point2);
        
        return new Ellipse2D.Double(startPoint.getX(), startPoint.getY(), width, height);
    }
}
