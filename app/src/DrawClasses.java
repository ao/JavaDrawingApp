import javax.swing.*;
import java.awt.*;

public class DrawClasses {

}
class DrawPane extends JPanel {
    private String color = "BLACK";
    private DrawingApp daInstance;

    public DrawPane(DrawingApp daInstance) {
        this.daInstance = daInstance;
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        System.out.println("Paint Component");

        for (Rectangle r : daInstance.rectangleList) {
            daInstance.drawRectangle(r,g);
        }
    }
}