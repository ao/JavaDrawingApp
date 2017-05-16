import javafx.scene.shape.Circle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPane extends JPanel {
    private DrawingInternalFrame difInstance;
    private DrawingApp daInstance;
    private DrawClasses drawClasses;

    public BufferedImage OSC;

    public boolean isMouseDown = false;

    public DrawPane(DrawingInternalFrame difInstance, DrawingApp daInstance) {
        this.difInstance = difInstance;
        this.daInstance = daInstance;
        this.drawClasses = new DrawClasses(difInstance, daInstance);

        addMouseListener(new MouseListener() {
            @Override
            public void mousePressed(MouseEvent e) {
                isMouseDown = true;
                difInstance.prevX = e.getX();
                difInstance.prevY = e.getY();

                if (difInstance.currentShape.equals("Eraser")) {
                    Graphics2D g2 = (Graphics2D) difInstance.drawPane.OSC.getGraphics();
                    g2.setColor(Color.WHITE);
                    g2.fillRect(e.getX(), e.getY(), 10, 10);
//                        g2.clearRect(e.getX(), e.getY(), 5, 5);
                }
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                isMouseDown = false;
                mouseRepeatCode(e);
            }
            public void mouseClicked(MouseEvent e) { }
            public void mouseEntered(MouseEvent e) {

                if (difInstance.currentShape.equals("FreeDraw")) {
                    setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        Toolkit.getDefaultToolkit().getImage( getClass().getResource("/resources/paintbrush.png") ),
                        new Point(0,10),
                        "freedraw"
                    ));
                } else if (difInstance.currentShape.equals("Eraser")) {
                    setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        Toolkit.getDefaultToolkit().getImage( getClass().getResource("/resources/eraser.png") ),
                        new Point(0,10),
                        "eraser"
                    ));
                } else if (difInstance.currentShape.equals("Rectangle")) {
                    setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        Toolkit.getDefaultToolkit().getImage( getClass().getResource("/resources/cross.png") ),
                        new Point(0,10),
                        "rectangle"
                    ));
                } else if (difInstance.currentShape.equals("Circle")) {
                    setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        Toolkit.getDefaultToolkit().getImage( getClass().getResource("/resources/cross.png") ),
                        new Point(0,10),
                        "circle"
                    ));
                }

            }
            public void mouseExited(MouseEvent e) { }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isMouseDown) {
                    if (difInstance.currentShape.equals("FreeDraw")) {
                        Graphics2D g2 = (Graphics2D) difInstance.drawPane.OSC.getGraphics();
                        g2.setColor(daInstance.strokeColour);
                        g2.setStroke(daInstance.stroke);
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g2.drawLine(difInstance.prevX, difInstance.prevY, e.getX(), e.getY());
                        g2.dispose();
                        repaint();
                        difInstance.prevX = e.getX();
                        difInstance.prevY = e.getY();
                    } else if (difInstance.currentShape.equals("Eraser")) {
                        Graphics2D g2 = (Graphics2D) difInstance.drawPane.OSC.getGraphics();
                        g2.setColor(Color.WHITE);
                        g2.fillRect(e.getX(), e.getY(), 10, 10);
//                        g2.clearRect(e.getX(), e.getY(), 5, 5);
                        g2.dispose();
                        repaint();
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
    }

    public void makeDrawable() {
        add(new DrawPane(difInstance, daInstance));
    }

    public void mouseRepeatCode(MouseEvent e) {
        if (difInstance.currentShape.equals("Rectangle")) {
            //draw a rectangle
            Rectangle r = new Rectangle(difInstance.prevX, difInstance.prevY, e.getX(), e.getY());
            difInstance.rectangleList.add(r);
        } else if (difInstance.currentShape.equals("Circle")) {
            //draw a circle
            Circle c = new Circle(difInstance.prevX, difInstance.prevY, e.getX()/2);
            difInstance.circleList.add(c);
        }
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);

        checkImage();
        g.drawImage(OSC,0,0,null);

        for (Rectangle r : difInstance.rectangleList) {
            drawClasses.drawRectangle(r,g);
        }

        for (Circle c : difInstance.circleList) {
            drawClasses.drawCircle(c,g);
        }

        drawClasses.drawFreeDraw(difInstance.freeDrawPath,g);
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