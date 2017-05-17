import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawPane extends JPanel {
    private DrawingInternalFrame difInstance;

    public BufferedImage OSC;

    public boolean isMouseDown = false;

    public DrawPane(DrawingInternalFrame difInstance) {
        this.difInstance = difInstance;

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
                    // we really should use `clearRect` here as it is supposed to be an `eraser` after all
                    // but the background is white, and `clearRect` makes it black, so it doesn't look so
                    // good for our purpose here.. maybe later..
                    // this is what I mean:  `g2.clearRect(e.getX(), e.getY(), 5, 5);`
                }
                repaint();
                saveToStack(OSC);
            }

            public void mouseReleased(MouseEvent e) {
                isMouseDown = false;

                if (difInstance.currentShape.equals("Rectangle")) {
                    Graphics2D g2 = (Graphics2D) difInstance.drawPane.OSC.getGraphics();

                    Double _x = (double) difInstance.prevX;
                    Double _y = (double) difInstance.prevY;
                    Double _x2 = (double) e.getX()-difInstance.prevX;
                    Double _y2 = (double) e.getY()-difInstance.prevY;

                    g2.setStroke(Shared.stroke);
                    g2.setColor(Shared.strokeColour);
                    g2.drawRect(_x.intValue(), _y.intValue(), _x2.intValue(), _y2.intValue());

                    g2.setColor(Shared.fillColour);
                    g2.fillRect(_x.intValue(), _y.intValue(), _x2.intValue(), _y2.intValue());

                } else if (difInstance.currentShape.equals("Circle")) {
                    Graphics2D g2 = (Graphics2D) difInstance.drawPane.OSC.getGraphics();

                    g2.setStroke(Shared.stroke);
                    g2.setColor(Shared.strokeColour);
                    g2.drawOval(difInstance.prevX, difInstance.prevY, e.getX()-difInstance.prevX, e.getY()-difInstance.prevY);

                    g2.setColor(Shared.fillColour);
                    g2.fillOval(difInstance.prevX, difInstance.prevY, e.getX()-difInstance.prevX, e.getY()-difInstance.prevY);
                }

                repaint();

                saveToStack(OSC);
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
                        g2.setColor(Shared.strokeColour);
                        g2.setStroke(Shared.stroke);
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
                        // we really should use `clearRect` here as it is supposed to be an `eraser` after all
                        // but the background is white, and `clearRect` makes it black, so it doesn't look so
                        // good for our purpose here.. maybe later..
                        // this is what I mean:  `g2.clearRect(e.getX(), e.getY(), 5, 5);`
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
        repaint();
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);

        // Let's try get a bit of a better `resolution` by enabling Anti-Aliasing! WE CAN ALWAYS HOPE!
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON));

        checkImage();
        g2.drawImage(OSC,0,0,null);
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

    public void undo() {
        if (difInstance.undoStack.size() > 0) {
            setImage(difInstance.undoStack.pop());
        }
    }

    public void saveToStack(BufferedImage img) {
        difInstance.undoStack.push(copyImage(img));
    }

    public void setImage(BufferedImage img) {
//        Graphics g = OSC.getGraphics();
        Graphics2D graphics = (Graphics2D) img.getGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics.setPaint(Color.black);
        OSC = img;
        repaint();
    }

    public BufferedImage copyImage(BufferedImage img) {
        BufferedImage copyOfImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = copyOfImage.createGraphics();
        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
        return copyOfImage;
    }

}