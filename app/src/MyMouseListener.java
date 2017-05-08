import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyMouseListener extends MouseAdapter {

    public MyMouseListener

    public void mousePressed(MouseEvent e) {
        setStartPoint(e.getX(), e.getY());
    }

    public void mouseDragged(MouseEvent e) {
        setEndPoint(e.getX(), e.getY());
        repaint();
    }

    public void mouseReleased(MouseEvent e) {
        setEndPoint(e.getX(), e.getY());
        repaint();
    }
}