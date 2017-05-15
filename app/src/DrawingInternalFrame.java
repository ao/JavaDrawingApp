import javafx.scene.shape.Circle;
import javax.swing.*;
import javax.swing.plaf.basic.BasicToolBarUI;
import java.awt.event.*;
import java.awt.*;
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

    public int x, y, x2, y2;

    public String currentShape = "Rectangle";

    public ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();
    public ArrayList<Shape> triangleList = new ArrayList<Shape>();
    public ArrayList<Circle> circleList = new ArrayList<Circle>();
    public java.util.List<Point> freeDrawPath = new ArrayList<>(25);

    public Color fillColour = new Color(255, 0, 0); //red
    public Color strokeColour = new Color(0, 0, 255); //blue

    public boolean isMouseDown = false;

    public DrawingApp daInstance;

    public ImageIcon imageBytes;

    public JToolBar toolBar;

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

        JMenuBar menuBar = new JMenuBar();

        JMenu mnuFile = new JMenu("Show");
        mnuFile.add(new JMenuItem("Item1")).addActionListener(new MenuBarSubGeneralHandler(this));
        menuBar.add(mnuFile);

        JMenu mnuAbout = new JMenu("Dont Show");
        mnuAbout.add(new JMenuItem("item2")).addActionListener(new MenuBarSubGeneralHandler(this));
        menuBar.add(mnuAbout);

        if(null == this.getMenuBar()) this.setMenuBar(menuBar);

        addTheMouseListener();
        addMouseMotionListener();


        setSize(640,480);

        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);

        if (imageBytes!=null) {
            JLabel label = new JLabel("", imageBytes, JLabel.CENTER);
            drawPane.add( label, BorderLayout.CENTER );
        }
    }

    public void addMouseMotionListener() {
        drawPane.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (isMouseDown) {
                    if (currentShape.equals("Rectangle")) {
                        //draw a rectangle
                        Rectangle r = new Rectangle(x, y, x2, y2);
                        rectangleList.add(r);
                    } else if (currentShape.equals("Circle")) {
                        //draw a circle
                        Circle c = new Circle(x, y, x2/2);
                        circleList.add(c);
                    } else if (currentShape.equals("Triangle")) {
                        //draw a triangle
                    }

                    if (currentShape.equals("FreeDraw")) {
                        freeDrawPath.add(e.getPoint());
                        repaint();
                    }
                }
            }
        });
    }
    public void addTheMouseListener() {
        drawPane.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                isMouseDown = true;

                x = e.getX();
                y = e.getY();
                repaint();

                if (currentShape.equals("FreeDraw")) {
//                    freeDrawPath = new ArrayList<>(25);
                    freeDrawPath.add(e.getPoint());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMouseDown = false;

                x2 = e.getX();
                y2 = e.getY();

                if (currentShape.equals("Rectangle")) {
                    //draw a rectangle
                    Rectangle r = new Rectangle(x, y, x2, y2);
                    rectangleList.add(r);
                } else if (currentShape.equals("Circle")) {
                    //draw a circle
                    Circle c = new Circle(x, y, x2/2);
                    circleList.add(c);
                } else if (currentShape.equals("Triangle")) {
                    //draw a triangle
                }
                drawPane.setVisible(false);
                drawPane.setVisible(true);
                repaint();

                if (currentShape.equals("FreeDraw")) {
//                    app.freeDrawPath = null;
                }
            }
        });
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
        JButton btnRectangle = makeSidenavBar("Rectangle");
        SideBarGeneralHandler listenerRectangle = new SideBarGeneralHandler();
        listenerRectangle.setInstance(this);
        btnRectangle.addActionListener(listenerRectangle);
        toolBar.add(btnRectangle);

        JButton btnCircle = makeSidenavBar("Circle");
        SideBarGeneralHandler listenerCircle = new SideBarGeneralHandler();
        listenerCircle.setInstance(this);
        btnCircle.addActionListener(listenerCircle);
        toolBar.add(btnCircle);

        JButton btnTriangle = makeSidenavBar("Triangle");
        SideBarGeneralHandler listenerTriangle = new SideBarGeneralHandler();
        listenerTriangle.setInstance(this);
        btnTriangle.addActionListener(listenerTriangle);
        toolBar.add(btnTriangle);

        JButton btnFreeDraw = makeSidenavBar("FreeDraw");
        SideBarGeneralHandler listenerFreeDraw = new SideBarGeneralHandler();
        listenerFreeDraw.setInstance(this);
        btnFreeDraw.addActionListener(listenerFreeDraw);
        toolBar.add(btnFreeDraw);

        JButton btnChooseFillColour = makeSidenavBar("Choose Fill Colour");
        SideBarGeneralHandler listenerChooseFillColour = new SideBarGeneralHandler();
        listenerChooseFillColour.setInstance(this);
        btnChooseFillColour.addActionListener(listenerChooseFillColour);
        toolBar.add(btnChooseFillColour);

        JButton btnChooseStrokeColour = makeSidenavBar("Choose Stroke Colour");
        SideBarGeneralHandler listenerChooseStrokeColour = new SideBarGeneralHandler();
        listenerChooseStrokeColour.setInstance(this);
        btnChooseStrokeColour.addActionListener(listenerChooseStrokeColour);
        toolBar.add(btnChooseStrokeColour);

    }

    private JButton makeSidenavBar(String altText) {
        JButton button = new JButton();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //
            }
        });
        button.setText(altText);
        return button;
    }
}