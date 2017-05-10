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

public class DrawingApp extends JPanel {

    public int x, y, x2, y2;
    public JFrame mainFrame;
    public DrawPane drawPane;
    public JPanel itemsPanel;
    public String currentShape = "Rectangle";
    public static DrawingApp daInstance;

    public ArrayList<Rectangle> rectangleList;
    public ArrayList<Shape> triangleList;
    public ArrayList<Circle> circleList;
    public List<Point> freeDrawPath;

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

        app.mainFrame.setVisible(true);

        app.rectangleList = new ArrayList<Rectangle>();
        app.triangleList = new ArrayList<Shape>();
        app.circleList = new ArrayList<Circle>();
        app.freeDrawPath = new ArrayList<>(25);

        app.drawPane.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                app.x = e.getX();
                app.y = e.getY();
                app.repaint();

                if (app.currentShape.equals("FreeDraw")) {
//                    app.freeDrawPath = new ArrayList<>(25);
                    app.freeDrawPath.add(e.getPoint());
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (app.currentShape.equals("FreeDraw")) {
                    app.freeDrawPath.add(e.getPoint());
                    app.repaint();
                }
            };

            @Override
            public void mouseReleased(MouseEvent e) {
                app.x2 = e.getX();
                app.y2 = e.getY();

                if (app.currentShape.equals("Rectangle")) {
                    //draw a rectangle
                    Rectangle r = new Rectangle(app.x, app.y, app.x2, app.y2);
                    app.rectangleList.add(r);
                } else if (app.currentShape.equals("Circle")) {
                    //draw a circle
                    Circle c = new Circle(app.x, app.y, app.x2/2);
                    app.circleList.add(c);
                } else if (app.currentShape.equals("Triangle")) {
                    //draw a triangle
                }
                app.drawPane.setVisible(false);
                app.drawPane.setVisible(true);
                app.repaint();

                if (app.currentShape.equals("FreeDraw")) {
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
        toolBar.setFloatable(false);
        mainFrame.add(toolBar, BorderLayout.PAGE_START);
    }
    protected void addToolbarButtons(JToolBar toolBar) {

        JButton btnRectangle;
        JButton btnCircle;
        JButton btnTriangle;
        JButton btnFreeDraw;

        btnRectangle = makeSidenavBar("Rectangle");
        SideBarGeneralHandler listenerRectangle = new SideBarGeneralHandler();
        listenerRectangle.setInstance(daInstance);
        btnRectangle.addActionListener(listenerRectangle);
        toolBar.add(btnRectangle);

        btnCircle = makeSidenavBar("Circle");
        SideBarGeneralHandler listenerCircle = new SideBarGeneralHandler();
        listenerCircle.setInstance(daInstance);
        btnCircle.addActionListener(listenerCircle);
        toolBar.add(btnCircle);

        btnTriangle = makeSidenavBar("Triangle");
        SideBarGeneralHandler listenerTriangle = new SideBarGeneralHandler();
        listenerTriangle.setInstance(daInstance);
        btnTriangle.addActionListener(listenerTriangle);
        toolBar.add(btnTriangle);

        btnFreeDraw = makeSidenavBar("FreeDraw");
        SideBarGeneralHandler listenerFreeDraw = new SideBarGeneralHandler();
        listenerFreeDraw.setInstance(daInstance);
        btnFreeDraw.addActionListener(listenerFreeDraw);
        toolBar.add(btnFreeDraw);
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