import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyMouseListener extends MouseAdapter {

    DrawingApp parentApp;

    public MyMouseListener(DrawingApp pa) {
        parentApp = pa;
    }

    public void mousePressed(MouseEvent e) {
        parentApp.setStartPoint(e.getX(), e.getY());
        System.out.println("mousePressed: "+e.getX()+"-"+e.getY());
    }

    public void mouseDragged(MouseEvent e) {
        parentApp.setEndPoint(e.getX(), e.getY());
        parentApp.repaint();
        System.out.println("mouseDragged: "+e.getX()+"-"+e.getY());
    }

    public void mouseReleased(MouseEvent e) {
        parentApp.setEndPoint(e.getX(), e.getY());
        parentApp.repaint();
        System.out.println("mouseReleased: "+e.getX()+"-"+e.getY());
    }
}