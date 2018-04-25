/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Round edged rectangle tool class.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public final class RoundRectangle extends Rectangle implements Tool
{
    /**
     * Value to divide the length of a rectangle side by to get arcLength for rounded corner.
     */
    private static final int ARC_FRACTION = 4;
    
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
     
        final double arcLength;
        
        final Point2D startPoint = super.getStartLocation(point1, point2);

        // Set length of arc to a fraction of the smallest side
        if (height < width)
        {
            arcLength = height / ARC_FRACTION;
        }
        else 
        {
            arcLength = width / ARC_FRACTION;
        }
                
        return new RoundRectangle2D.Double(startPoint.getX(), startPoint.getY(), 
                                           width, height, arcLength, arcLength);
    }
}
