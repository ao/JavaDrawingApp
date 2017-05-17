/**
 * File Description: Contains global variables available to the entire application.
 */

import java.awt.*;

public class Shared {

    /**
     * Main application's static instance
     */
    public static DrawingApp app;

    /**
     * Store `stroke` information for access to the entire application
     */
    public static BasicStroke stroke = new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    public static Color fillColour = new Color(255, 0, 0); //red
    public static Color strokeColour = new Color(0, 0, 255); //blue

}
