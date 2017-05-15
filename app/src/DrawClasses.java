import javafx.scene.shape.Circle;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

public class DrawClasses {
    private DrawingInternalFrame daInstance;

    public DrawClasses(DrawingInternalFrame daInstance) {
        this.daInstance = daInstance;
    }
    public void drawRectangle(Rectangle r, Graphics gr) {
        Graphics2D g;
        if (gr instanceof Graphics2D) {
            g = (Graphics2D) gr;
        } else return;

        Double _x =  r.getX();
        Double _y = r.getY();
        Double _x2 = r.getWidth()-r.getX();
        Double _y2 = r.getHeight()-r.getY();

        g.setStroke(new BasicStroke(3));
        // TODO: FIX THIS STUFF BELOW
//        g.setColor(daInstance.fillColour);
//        g.fill(r);
        // TODO: FIX THIS STUFF ABOVE
        g.setColor(daInstance.strokeColour);

        g.drawRect(_x.intValue(), _y.intValue(), _x2.intValue(), _y2.intValue());

        System.out.println(_x.intValue()+":"+_y.intValue()+":"+_x2.intValue()+":"+_y2.intValue());
    }

    public void drawCircle(Circle c, Graphics gr) {
        Graphics2D g;
        if (gr instanceof Graphics2D) {
            g = (Graphics2D) gr;
        } else return;

        g.setStroke(new BasicStroke(3));
        g.setColor(daInstance.strokeColour);
        g.setBackground(daInstance.fillColour);

        Shape theCircle = new Ellipse2D.Double(c.getCenterX() - c.getRadius(), c.getCenterY() - c.getRadius(), 2.0 * c.getRadius(), 2.0 * c.getRadius());
        g.draw(theCircle);
    }

    public void drawFreeDraw(List<Point> p, Graphics gr) {
        System.out.println(p);
        Graphics2D g;
        if (gr instanceof Graphics2D) {
            g = (Graphics2D) gr;
        } else return;

        Point last = null;

        if (p==null) return;

        g.setColor(daInstance.strokeColour);
        g.setStroke(daInstance.stroke);

        for (Point item : p) {
            if (item != null) {
                Point lastPoint = last==null ? new Point(item.x,item.y) : last;
                g.drawLine(lastPoint.x, lastPoint.y, item.x, item.y);
            }
            last = item;
        }
    }
}