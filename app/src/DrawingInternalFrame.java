import javafx.scene.shape.Circle;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.plaf.basic.BasicToolBarUI;

public class DrawingInternalFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public DrawPane drawPane;

    public int x, y, x2, y2, prevX, prevY;

    public String currentShape = "FreeDraw";

    public ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();
    public ArrayList<Circle> circleList = new ArrayList<Circle>();
    public java.util.List<Point> freeDrawPath = new ArrayList<>(25);

    public ImageIcon imageBytes;
    public ImageIcon imageData;

    public JToolBar toolBar;
    public JToolBar toolBarInstance;

    public DrawingInternalFrame() {
        super("Drawing #" + (++openFrameCount),
                true, //resizable
                true, //closable
                true, //maximizable
                true); //iconifiable

        subConstructor();
    }

    //no longer used
//    public DrawingInternalFrame(DrawingApp daInstance, byte[] bytes) {
//        imageBytes = new ImageIcon(bytes);
//        subConstructor(daInstance);
//    }

    public DrawingInternalFrame(BufferedImage imageData) {
        this.imageData = new ImageIcon(imageData);
        subConstructor();
    }

    public void subConstructor() {
        x = y = x2 = y2 = 0;

        drawPane = new DrawPane(this);

        setSize(640,480);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

        if (imageBytes!=null) {
            JLabel label = new JLabel("", imageBytes, JLabel.CENTER);
            drawPane.add( label, BorderLayout.CENTER );
        }

        if (imageData!=null) {
            JLabel jLabel = new JLabel();
            jLabel.setIcon(imageData);
            drawPane.add(jLabel, BorderLayout.CENTER);
            drawPane.makeDrawable();
        }

        addToolbar(this);

        this.add(drawPane);
    }

    public void addToolbar(DrawingInternalFrame dif) {
        BasicToolBarUI ui = new BasicToolBarUI();
        toolBar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
        toolBar.setUI(ui);
        addToolbarButtons(toolBar);
        toolBar.setFloatable(true);
        dif.add(toolBar, BorderLayout.PAGE_START);
        dif.setToolbarInstance(toolBar);
    }

    public void setToolbarInstance(JToolBar tb) {
        this.toolBarInstance = tb;
    }
    public JToolBar getToolBarInstance() {
        return this.toolBarInstance;
    }

    protected void addToolbarButtons(JToolBar toolBar) {
        ImageIcon freedrawIcon = new ImageIcon(DrawingInternalFrame.class.getResource("/resources/paintbrush.png"));
        Action freedrawAction = new AbstractAction("freedraw", freedrawIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = "FreeDraw";
            }
        };
        toolBar.add(freedrawAction);

        ImageIcon eraserIcon = new ImageIcon(DrawingInternalFrame.class.getResource("/resources/eraser.png"));
        Action eraserAction = new AbstractAction("eraser", eraserIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = "Eraser";
            }
        };
        toolBar.add(eraserAction);

        ImageIcon rectangleIcon = new ImageIcon(DrawingInternalFrame.class.getResource("/resources/rectangle.png"));
        Action rectangleAction = new AbstractAction("rectangle", rectangleIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = "Rectangle";
            }
        };
        toolBar.add(rectangleAction);

        ImageIcon circleIcon = new ImageIcon(DrawingInternalFrame.class.getResource("/resources/circle.png"));
        Action circleAction = new AbstractAction("circle", circleIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = "Circle";
            }
        };
        toolBar.add(circleAction);
    }
}