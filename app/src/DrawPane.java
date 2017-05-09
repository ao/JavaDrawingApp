import javafx.scene.shape.Circle;
import javax.swing.*;
import java.awt.*;

public class DrawPane extends JPanel {
    private DrawingApp daInstance;
    private DrawClasses drawClasses;

    public DrawPane(DrawingApp daInstance) {
        this.daInstance = daInstance;
        this.drawClasses = new DrawClasses();
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        System.out.println("Paint Component");

        for (Rectangle r : daInstance.rectangleList) {
            drawClasses.drawRectangle(r,g);
        }

        for (Circle c : daInstance.circleList) {
            drawClasses.drawCircle(c,g);
        }
    }
}