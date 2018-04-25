/*
 * TCSS 305 - Autumn 2017
 * Assignment 5 - PowerPaint
 */

package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * PowerPaint GUI MenuBar class.
 * 
 * @author Nicole Kauer
 * @version 22 November 2017
 */
public class GUIMenuBar extends JMenuBar
{
    /** Generated serialization ID. */
    private static final long serialVersionUID = 2721792175805610960L;
    
    /** Maximum thickness for slider. */
    private static final int MAX_THICKNESS = 25;
    
    /** Label spacing for thickness for slider. */
    private static final int THICKNESS_LABEL_SPACING = 5;
    
    /** Drawing panel associated with JMenuBar items. */
    private final DrawingPanel myDrawPanel;
    
    /** URL for PowerPaint icon image. */
    private final URL myIconImageURL;
    
    /**
     * Constructor. 
     * 
     * @param theDrawPanel the JPanel associated with the menu bar
     * @param theButtonActions the button actions
     * @param theURL the URL for the icon image
     */
    public GUIMenuBar(final DrawingPanel theDrawPanel, 
                      final List<ToolButtonAction> theButtonActions, final URL theURL)
    {
        super();
        
        myDrawPanel = theDrawPanel;
        myIconImageURL = theURL;
        
        setup(theButtonActions);
    }
    
    // Setup methods 
    
    /**
     * Sets up the menu bar.
     * 
     * @param theButtonActions the button actions
     */
    private void setup(final List<ToolButtonAction> theButtonActions)
    {
        add(createOptionsMenu());
        add(createToolsMenu(theButtonActions));
        add(createHelpMenu(myIconImageURL));
    }
    
    /**
     * Creates the options menu for the menu bar.
     * 
     * @return the options menu
     */
    private JMenu createOptionsMenu()
    {
        final JMenu optionsMenu = new JMenu("Options");
                
        optionsMenu.add(createThicknessSlider());
        
        optionsMenu.addSeparator();
        
        optionsMenu.add(createDrawColorChooserItem());
        optionsMenu.add(createFillColorChooserItem());
        
        optionsMenu.addSeparator();
              
        optionsMenu.add(createFillItem());
        
        optionsMenu.addSeparator();
        
        optionsMenu.add(myDrawPanel.getClearItem());
             
        return optionsMenu;
    }
    
    /**
     * Creates the tools menu for the menu bar.
     * 
     * @param theButtonActions the button actions
     * @return the tool menu
     */
    private static JMenu createToolsMenu(final List<ToolButtonAction> theButtonActions)
    {
        final JMenu toolMenu = new JMenu("Tools");
        
        final ButtonGroup buttonGroup = new ButtonGroup();
        
        for (final ToolButtonAction buttonAction : theButtonActions)
        {
            final JRadioButtonMenuItem radioButton = new JRadioButtonMenuItem(buttonAction);
            buttonGroup.add(radioButton);
            toolMenu.add(radioButton);  
        }
        
        return toolMenu;
    }
    
    /**
     * Creates the help menu for the menu bar.
     * 
     * @param theURL URL for the PowerPaint icon image
     * @return the help menu
     */
    private static JMenu createHelpMenu(final URL theURL)
    {
        final JMenu helpMenu = new JMenu("Help");
        final JMenuItem about = new JMenuItem("About...");
        final URL iconURL = theURL;
        
        // Local class for listener
        /**
         * Opens an 'About' pane.
         * 
         * @author Nicole Kauer
         * @version 22 November 2017
         */
        class HelpListener implements ActionListener
        {           
            /**
             * Shows About message.
             * 
             * @param theEvent the action event for clicking the button
             */
            public void actionPerformed(final ActionEvent theEvent)
            {
                final JOptionPane aboutPane = new JOptionPane();
                aboutPane.setIcon(new ImageIcon(iconURL));
                aboutPane.setMessage("TCSS 305 PowerPaint\n"
                                     + "Autumn 2017\n"
                                     + "Nicole Kauer");
                aboutPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
                
                final JDialog dialog = aboutPane.createDialog(null, "About");
                dialog.setVisible(true);
            }
        }
        
        // Add listener to the item
        about.addActionListener(new HelpListener());
     
        // Add item to the menu
        helpMenu.add(about);
        
        return helpMenu;
    }
    
    /**
     * Creates the thickness slider submenu for the options menu.
     * 
     * @return the thickness slider submenu
     */
    private JMenu createThicknessSlider()
    {
        final JMenu thicknessSubMenu = new JMenu("Thickness");
        final JSlider thicknessSlider = new JSlider(SwingConstants.HORIZONTAL, 
                                                    0, MAX_THICKNESS, 
                                                    THICKNESS_LABEL_SPACING);
        
        // Slider labeling
        thicknessSlider.createStandardLabels(THICKNESS_LABEL_SPACING);
        thicknessSlider.setMajorTickSpacing(THICKNESS_LABEL_SPACING);
        thicknessSlider.setMinorTickSpacing(1);
        thicknessSlider.setPaintTicks(true);
        thicknessSlider.setPaintLabels(true);
        
        // Listener for slider
        /**
         * Listener for stroke thickness slider.
         * 
         * @author Nicole Kauer
         * @version 22 Novemeber 2017
         */
        class SlideListener implements ChangeListener 
        {
            /**
             * Changes the current tool stroke size.
             * 
             * @param theEvent the ChangeEvent
             */
            @Override
            public void stateChanged(final ChangeEvent theEvent)
            {               
                myDrawPanel.setThickness(thicknessSlider.getValue());   
            }
            
        }        
        
        // Add listener to slider
        thicknessSlider.addChangeListener(new SlideListener());
                
        // Add slider to menu
        thicknessSubMenu.add(thicknessSlider);       
        
        return thicknessSubMenu;
    }
    
    /**
     * Creates draw color chooser and adds button listener.
     * 
     * @return JMenuItem color chooser menu item
     */
    private JMenuItem createDrawColorChooserItem()
    {
        final JMenuItem item = new JMenuItem("Draw Color...");
        
        item.addActionListener(new AbstractAction() 
        {

            /** A generated serialization ID. */
            private static final long serialVersionUID = -3641127125217134863L;
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) 
            {
                final Color result = JColorChooser.showDialog(null, "Draw Color Chooser", 
                                            myDrawPanel.getStrokeColor());

                myDrawPanel.setStrokeColor(result);
            }
        });
        
        return item;
    }
    
    /**
     * Creates fill color chooser and adds button listener.
     * 
     * @return JMenuItem color chooser menu item
     */
    private JMenuItem createFillColorChooserItem()
    {
        final JMenuItem item = new JMenuItem("Fill Color...");
        
        item.addActionListener(new AbstractAction() 
        {

            /** A generated serialization ID. */
            private static final long serialVersionUID = -3641127125217134863L;
            
            @Override
            public void actionPerformed(final ActionEvent theEvent) 
            {
                final Color result = JColorChooser.showDialog(null, "Fill Color Chooser", 
                                             myDrawPanel.getFillColor());

                myDrawPanel.setFillColor(result);
            }
        });
        
        return item;
    }
    
    /**
     * Creates checkbox menuItem for Fill.
     * 
     * @return the menu item
     */
    private JCheckBoxMenuItem createFillItem()
    {
        final JCheckBoxMenuItem item = new JCheckBoxMenuItem("Fill");
        
        /**
         * Action listener for the fill checkbox menu item.
         * 
         * @author Nicole Kauer
         * @version 22 November 2017
         */
        class FillListener implements ActionListener
        {
            /**
             * Changes shapes to filled.
             * 
             * @param theEvent the action event for clicking the button
             */
            public void actionPerformed(final ActionEvent theEvent)
            {
                final AbstractButton box = (AbstractButton) theEvent.getSource();
                final boolean checked = box.getModel().isSelected();
                    
                if (checked)
                {
                    myDrawPanel.setIsFilled(true);
                }
                else
                {
                    myDrawPanel.setIsFilled(false);
                }
            }
        }
        
        item.addActionListener(new FillListener());
        
        return item;
    }
}
