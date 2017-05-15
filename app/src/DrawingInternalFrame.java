import javafx.scene.shape.Circle;
import javax.swing.*;
import javax.swing.plaf.basic.BasicToolBarUI;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class DrawingInternalFrame extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    public DrawPane drawPane;

    public int x, y, x2, y2, prevX, prevY;

    public String currentShape = "FreeDraw";

    public ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();
    public ArrayList<Circle> circleList = new ArrayList<Circle>();
    public java.util.List<Point> freeDrawPath = new ArrayList<>(25);

    public Color fillColour = new Color(255, 0, 0); //red
    public Color strokeColour = new Color(0, 0, 255); //blue

    public DrawingApp daInstance;

    public ImageIcon imageBytes;

    public JToolBar toolBar;

    public BasicStroke stroke;

    public DrawingInternalFrame(DrawingApp daInstance) {
        super("Drawing #" + (++openFrameCount),
                true, //resizable
                true, //closable
                true, //maximizable
                true); //iconifiable

        subConstructor(daInstance);
    }

    public DrawingInternalFrame(DrawingApp daInstance, byte[] bytes) {
        imageBytes = new ImageIcon(bytes);
        subConstructor(daInstance);
    }

    public void subConstructor(DrawingApp daInstance) {
        this.daInstance = daInstance;

        x = y = x2 = y2 = 0;

        drawPane = new DrawPane(this);
        this.add(drawPane);

        addToolbar(this);

        setSize(640,480);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

        if (imageBytes!=null) {
            JLabel label = new JLabel("", imageBytes, JLabel.CENTER);
            drawPane.add( label, BorderLayout.CENTER );
        }

        stroke = new BasicStroke(3,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
    }

    public void addToolbar(DrawingInternalFrame mif) {
        BasicToolBarUI ui = new BasicToolBarUI();
        toolBar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
        toolBar.setUI(ui);
        addToolbarButtons(toolBar);
        toolBar.setFloatable(true);
        mif.add(toolBar, BorderLayout.PAGE_START);
    }
    protected void addToolbarButtons(JToolBar toolBar) {
        ImageIcon freedrawIcon = new ImageIcon( DrawingInternalFrame.class.getResource("/resources/paintbrush.png") );
        Action freedrawAction = new AbstractAction("freedraw", freedrawIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = "FreeDraw";
            }
        };
        toolBar.add(freedrawAction);

        ImageIcon rectangleIcon = new ImageIcon( DrawingInternalFrame.class.getResource("/resources/rectangle.png") );
        Action rectangleAction = new AbstractAction("rectangle", rectangleIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = "Rectangle";
            }
        };
        toolBar.add(rectangleAction);

        ImageIcon circleIcon = new ImageIcon( DrawingInternalFrame.class.getResource("/resources/circle.png") );
        Action circleAction = new AbstractAction("circle", circleIcon) {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentShape = "Circle";
            }
        };
        toolBar.add(circleAction);

        ButtonGroup group = new ButtonGroup();
        toolBar.add( makeColourButton("stroke", strokeColour, group, true) );
        toolBar.add( makeColourButton("fill", fillColour, group, true) );
    }

    private JRadioButton makeColourButton(String strokeOrFill, final Color c, ButtonGroup grp, boolean selected) {

        final String finalStrokeOrFill = (strokeOrFill==null) ? "stroke" : strokeOrFill;

        BufferedImage image = new BufferedImage(20,20,BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,0,30,30);
        g.setColor(c);
        g.fill3DRect(1, 1, 24, 24, true);
        g.dispose();
        Icon unselectedIcon = new ImageIcon(image);

        image = new BufferedImage(20,20,BufferedImage.TYPE_INT_RGB);
        g = image.getGraphics();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,30,30);
        g.setColor(c);
        g.fill3DRect(3, 3, 24, 24, false);
        g.dispose();
//        Icon selectedIcon = new ImageIcon(image);

        JRadioButton button = new JRadioButton(unselectedIcon);
//        button.setSelectedIcon(selectedIcon);
        button.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (finalStrokeOrFill.equals("stroke")) {
                    strokeColour = JColorChooser.showDialog(null, "Choose a stroke color", strokeColour);
                } else {
                    fillColour = JColorChooser.showDialog(null, "Choose a fill color", fillColour);
                }
            }
        });
        grp.add(button);
//        if (selected)  button.setSelected(true);

        return button;
    }
}