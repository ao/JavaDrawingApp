import javafx.scene.shape.Circle;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class DrawClasses {
    public void drawRectangle(Rectangle r, Graphics gr) {
        Graphics2D g;
        if (gr instanceof Graphics2D) {
            g = (Graphics2D) gr;
        } else return;

        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);

        Double _x =  r.getX();
        Double _y = r.getY();
        Double _x2 = r.getX()+r.getWidth();
        Double _y2 = r.getY()+r.getHeight();
        g.drawRect(_x.intValue(), _y.intValue(), _x2.intValue(), _y2.intValue());

        System.out.println("Drawn rectangle");
    }

    public void drawCircle(Circle c, Graphics gr) {
        Graphics2D g;
        if (gr instanceof Graphics2D) {
            g = (Graphics2D) gr;
        } else return;

        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);

        Shape theCircle = new Ellipse2D.Double(c.getCenterX() - c.getRadius(), c.getCenterY() - c.getRadius(), 2.0 * c.getRadius(), 2.0 * c.getRadius());
        g.draw(theCircle);
    }
}