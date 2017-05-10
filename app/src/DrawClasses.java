import javafx.scene.shape.Circle;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

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
        Double _x2 = r.getWidth()-r.getX();
        Double _y2 = r.getHeight()-r.getY();
        g.drawRect(_x.intValue(), _y.intValue(), _x2.intValue(), _y2.intValue());

        System.out.println(_x.intValue()+":"+_y.intValue()+":"+_x2.intValue()+":"+_y2.intValue());
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

    public void drawFreeDraw(List<Point> p, Graphics gr) {
        System.out.println(p);
        Graphics2D g;
        if (gr instanceof Graphics2D) {
            g = (Graphics2D) gr;
        } else return;

        Point last = null;

        if (p==null) return;

        for (Point item : p) {
            if (item != null) {
                Point lastPoint = last==null ? new Point(item.x,item.y) : last;
                g.drawLine(lastPoint.x, lastPoint.y, item.x, item.y);
            }
            last = item;
        }
    }
}