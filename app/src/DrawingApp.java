import javafx.scene.shape.Circle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

public class DrawingApp extends JPanel {

    public int x, y, x2, y2;
    public JFrame mainFrame;
    public DrawPane drawPane;
    public JPanel itemsPanel;
    public String currentShape = "Rectangle";
    public static DrawingApp daInstance;

    public ArrayList<Rectangle> rectangleList = new ArrayList<Rectangle>();
    public ArrayList<Shape> triangleList = new ArrayList<Shape>();
    public ArrayList<Circle> circleList = new ArrayList<Circle>();
    public List<Point> freeDrawPath = new ArrayList<>(25);

    public Color fillColour = new Color(255, 0, 0); //red
    public Color strokeColour = new Color(0, 0, 255); //blue

    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
        daInstance = app;
        app.mainFrame = new JFrame("Java Drawing App");

        app.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.drawPane = new DrawPane(app);
        app.itemsPanel = new JPanel();

        app.mainFrame.add(app.drawPane);

        app.mainFrame.setSize(800, 600);

        app.mainFrame.setLocationRelativeTo(null);

        app.mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        app.addMainMenu();
        app.addToolbar();
        app.addLayersToolbar();

        app.mainFrame.setVisible(true);

        app.addTheMouseListener();
    }

    public void addTheMouseListener() {
        drawPane.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                repaint();

                if (currentShape.equals("FreeDraw")) {
//                    freeDrawPath = new ArrayList<>(25);
                    freeDrawPath.add(e.getPoint());
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (currentShape.equals("FreeDraw")) {
                    freeDrawPath.add(e.getPoint());
                    repaint();
                }
            };

            @Override
            public void mouseReleased(MouseEvent e) {
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

    public DrawingApp() {
        super();
        x = y = x2 = y2 = 0;
    }

    private void addToolbar() {
        JToolBar toolBar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
        addToolbarButtons(toolBar);
        toolBar.setFloatable(true);
        mainFrame.add(toolBar, BorderLayout.PAGE_START);
    }
    protected void addToolbarButtons(JToolBar toolBar) {
        JButton btnRectangle = makeSidenavBar("Rectangle");
        SideBarGeneralHandler listenerRectangle = new SideBarGeneralHandler();
        listenerRectangle.setInstance(daInstance);
        btnRectangle.addActionListener(listenerRectangle);
        toolBar.add(btnRectangle);

        JButton btnCircle = makeSidenavBar("Circle");
        SideBarGeneralHandler listenerCircle = new SideBarGeneralHandler();
        listenerCircle.setInstance(daInstance);
        btnCircle.addActionListener(listenerCircle);
        toolBar.add(btnCircle);

        JButton btnTriangle = makeSidenavBar("Triangle");
        SideBarGeneralHandler listenerTriangle = new SideBarGeneralHandler();
        listenerTriangle.setInstance(daInstance);
        btnTriangle.addActionListener(listenerTriangle);
        toolBar.add(btnTriangle);

        JButton btnFreeDraw = makeSidenavBar("FreeDraw");
        SideBarGeneralHandler listenerFreeDraw = new SideBarGeneralHandler();
        listenerFreeDraw.setInstance(daInstance);
        btnFreeDraw.addActionListener(listenerFreeDraw);
        toolBar.add(btnFreeDraw);

        JButton btnChooseFillColour = makeSidenavBar("Choose Fill Colour");
        SideBarGeneralHandler listenerChooseFillColour = new SideBarGeneralHandler();
        listenerChooseFillColour.setInstance(daInstance);
        btnChooseFillColour.addActionListener(listenerChooseFillColour);
        toolBar.add(btnChooseFillColour);

        JButton btnChooseStrokeColour = makeSidenavBar("Choose Stroke Colour");
        SideBarGeneralHandler listenerChooseStrokeColour = new SideBarGeneralHandler();
        listenerChooseStrokeColour.setInstance(daInstance);
        btnChooseStrokeColour.addActionListener(listenerChooseStrokeColour);
        toolBar.add(btnChooseStrokeColour);


    }
    private void addLayersToolbar() {
//        JToolBar layerToolBar = new JToolBar("Layer Toolbar", JToolBar.VERTICAL);
//        addLayerToolbarButtons(layerToolBar);
//        layerToolBar.setFloatable(true);
//        mainFrame.add(layerToolBar, BorderLayout.PAGE_START);
    }
    protected void addLayerToolbarButtons(JToolBar layerToolBar) {
        //
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

    private void addMainMenu() {
        MenuBar menuBar = new MenuBar();

        Menu mnuFile = new Menu("File");
            mnuFile.add(new MenuItem("Exit")).addActionListener(new MenuBarGeneralHandler());
            menuBar.add(mnuFile);

        Menu mnuAbout = new Menu("About");
            mnuAbout.add(new MenuItem("About")).addActionListener(new MenuBarGeneralHandler());
            menuBar.add(mnuAbout);

        if(null == mainFrame.getMenuBar()) mainFrame.setMenuBar(menuBar);
    }

}