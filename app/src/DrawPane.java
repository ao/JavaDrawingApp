import javafx.scene.shape.Circle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPane extends JPanel {
    private DrawingInternalFrame daInstance;
    private DrawClasses drawClasses;

    public BufferedImage OSC;

    public boolean isMouseDown = false;

    public DrawPane(DrawingInternalFrame daInstance) {
        this.daInstance = daInstance;
        this.drawClasses = new DrawClasses(daInstance);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                isMouseDown = true;
                daInstance.prevX = e.getX();
                daInstance.prevY = e.getY();
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                isMouseDown = false;
                mouseRepeatCode(e);
            }
            public void mouseClicked(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) { }
            public void mouseExited(MouseEvent e) { }
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isMouseDown) {
                    if (daInstance.currentShape.equals("FreeDraw")) {
                        Graphics2D g2 = (Graphics2D) daInstance.drawPane.OSC.getGraphics();
                        g2.setColor(daInstance.strokeColour);
                        g2.setStroke(daInstance.stroke);
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.drawLine(daInstance.prevX, daInstance.prevY, e.getX(), e.getY());
                        g2.dispose();
                        repaint();
                        daInstance.prevX = e.getX();
                        daInstance.prevY = e.getY();
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
    }

    public void mouseRepeatCode(MouseEvent e) {
        if (daInstance.currentShape.equals("Rectangle")) {
            //draw a rectangle
            Rectangle r = new Rectangle(daInstance.prevX, daInstance.prevY, e.getX(), e.getY());
            daInstance.rectangleList.add(r);
        } else if (daInstance.currentShape.equals("Circle")) {
            //draw a circle
            Circle c = new Circle(daInstance.prevX, daInstance.prevY, e.getX()/2);
            daInstance.circleList.add(c);
        }
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);

        checkImage();
        g.drawImage(OSC,0,0,null);

        for (Rectangle r : daInstance.rectangleList) {
            drawClasses.drawRectangle(r,g);
        }

        for (Circle c : daInstance.circleList) {
            drawClasses.drawCircle(c,g);
        }

        drawClasses.drawFreeDraw(daInstance.freeDrawPath,g);
    }

    public void checkImage() {  // create or resize OSC if necessary
        if (OSC == null) {
            // Create the OSC, with a size to match the size of the panel.
            OSC = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
            clear();
        }
        else if (OSC.getWidth() != getWidth() || OSC.getHeight() != getHeight()) {
            // OSC size does not match the panel's size, so create a new OSC and
            // copy the picture in the old OSC to the new one.  This will scale
            // the current image to fit the new size.
            BufferedImage newOSC;
            newOSC = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
            Graphics g = newOSC.getGraphics();
            g.drawImage(OSC,0,0,getWidth(),getHeight(),null);
            g.dispose();
            OSC = newOSC;
        }
    }

    public void clear() { // clear the drawing area by filling it with white
        if (OSC != null) {
            Graphics g = OSC.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0,0,getWidth(),getHeight());
            g.dispose();
            repaint();
        }
    }



}