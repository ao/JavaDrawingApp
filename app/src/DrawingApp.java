import javax.swing.*;

import java.awt.*;
import java.awt.Graphics;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DrawingApp extends JPanel {

    public int x, y, x2, y2;
    public JFrame mainFrame;
    public DrawPane drawPane;
    public String currentShape = "Rectangle";
    public static DrawingApp daInstance;

    public ArrayList<Rectangle> rectangleList;

    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
        daInstance = app;
        app.mainFrame = new JFrame("Java Drawing App");
        app.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.drawPane = new DrawPane();
//        app.mainFrame.setContentPane(new DrawPane());
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

//        MyMouseListener listener = new MyMouseListener(app);
//        app.mainFrame.addMouseListener(listener);
//        app.mainFrame.addMouseMotionListener(listener);

        app.drawPane.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                app.x = e.getX();
                app.y = e.getY();
                System.out.println("1");
                app.repaint();
            }

            public void mouseReleased(MouseEvent e) {
                app.x2 = e.getX();
                app.y2 = e.getY();
                app.repaint();

                System.out.println("2");

                if (app.currentShape.equals("Rectangle")) {
                    //draw a rectangle
                    Rectangle r = new Rectangle(app.x, app.y, app.x2, app.y2);
                    app.rectangleList.add(r);
                    app.revalidate();
                } else if (app.currentShape.equals("Circle")) {
                    //draw a circle
                } else if (app.currentShape.equals("Triangle")) {
                    //draw a triangle
                }



                app.repaint();
            }
        });

//        app.drawPane.addMouseMotionListener(new MouseMotionAdapter() {
//            public void mouseDragged(MouseEvent e) {
//                app.x2 = e.getX();
//                app.y2 = e.getY();
//                System.out.println("3");
//                app.repaint();
//            }
//        });
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

    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        this.x2 = (x);
        this.y2 = (y);
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Paint Component");

        for (Rectangle r : rectangleList) {
            drawRectangle(r,g);
        }
    }


    public void drawRectangle(Rectangle r, Graphics gr) {
        Graphics2D g;
        if (gr instanceof Graphics2D) {
            g = (Graphics2D) gr;
        } else return;

        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);

        Double _x =  r.getX();
        Double _y = r.getY();
        Double _x2 = r.getX()+r.getWidth();
        Double _y2 = r.getY()+r.getHeight();
        g.drawRect(_x.intValue(), _y.intValue(), _x2.intValue(), _y2.intValue());

        System.out.println("Drawn rectangle");
    }

}