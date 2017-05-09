import javax.swing.*;

import java.awt.*;
import java.awt.Graphics;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Map;

public class DrawingApp extends JPanel {

    public int x, y, x2, y2;
    public JFrame mainFrame;
    public ArrayList<Shape> shapes = new ArrayList<Shape>();
    public DrawPane drawPane;
    public String currentShape = "Rectangle";
    public static DrawingApp daInstance;

    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
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

        daInstance = app;

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

                Shape r = app.makeRectangle(app.x, app.y, app.x2, app.y2);
                app.shapes.add(r);
                System.out.println("2");

                Graphics2D g = (Graphics2D) null;
                Rectangle2D newRectangle = new Rectangle2D.Double();

                newRectangle.setFrameFromDiagonal(app.x, app.y,app.x2, app.y2);

                g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
                g.setColor(Color.BLUE);
                g.fill(newRectangle);
                g.draw(newRectangle);

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
    public void paint(Graphics g) {
        System.out.println("OH HI");
    }


    public void drawRect(Graphics g, int x, int y, int x2, int y2) {
        int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int pw=Math.abs(x-x2);
        int ph=Math.abs(y-y2);
        g.drawRect(px, py, pw, ph);
    }

    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

}