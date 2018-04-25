/*
 * TCSS 305 - Autumn 2017 
 * Assignment 5 - PowerPaint
 */

package tools;

import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;

/**
 * Pencil tool class.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public class Pencil extends AbstractTool implements Tool
{
    /** Pencil tool geometry is not fillable. */
    private static final boolean FILLABLE = false;
    
    /**
     * Pencil path.
     */
    private Path2D myPath;
      
    /**
     * Constructor.
     */
    public Pencil()
    {
        super(FILLABLE);
        
        myPath = new Path2D.Double();
    }
    
    /**
     * Returns the shape to be painted.
     * 
     * @return shape to paint
     */
    @Override
    public Shape getShape()
    {      
        if (myPath.getCurrentPoint() == null)
        {
            myPath.moveTo(super.getInitialPoint().getX(), super.getInitialPoint().getY());
        }
        else if (super.isNewShape())
        {
            myPath = new Path2D.Double();
        }
        else
        {
            myPath.append(new Line2D.Double(myPath.getCurrentPoint(), super.getFinalPoint()), 
                                                false);
        }
                
        return myPath;
    }
}
