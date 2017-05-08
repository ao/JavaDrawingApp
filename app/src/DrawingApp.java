import javax.swing.*;

import java.awt.*;
import java.awt.Graphics;

import java.awt.event.*;

public class DrawingApp extends JPanel {

    private int x, y, x2, y2;
    private JFrame mainFrame;

    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
        app.mainFrame = new JFrame("Java Drawing App");
        app.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        app.mainFrame.setContentPane(new DrawRect("RED"));
        app.mainFrame.setSize(300, 300);
        app.mainFrame.setVisible(true);

        app.mainFrame.setSize(800, 600);

        app.mainFrame.setLocationRelativeTo(null);

        app.mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        app.addMainMenu();
        app.addToolbar();
    }

    public DrawingApp() {
        x = y = x2 = y2 = 0;

        MyMouseListener listener = new MyMouseListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
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
        btnRectangle.addActionListener(new SideBarGeneralHandler());
        toolBar.add(btnRectangle);

        btnCircle = makeSidenavBar("Circle");
        btnCircle.addActionListener(new SideBarGeneralHandler());
        toolBar.add(btnCircle);

        btnTriangle = makeSidenavBar("Triangle");
        btnTriangle.addActionListener(new SideBarGeneralHandler());
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

        Menu mnuShapes = new Menu("Shapes");
            mnuShapes.add(new MenuItem("Rectangle")).addActionListener(new MenuBarGeneralHandler());
            mnuShapes.add(new MenuItem("Circle")).addActionListener(new MenuBarGeneralHandler());
            mnuShapes.add(new MenuItem("Triangle")).addActionListener(new MenuBarGeneralHandler());
            menuBar.add(mnuShapes);

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
        x2 = (x);
        y2 = (y);
    }



    public void drawPerfectRect(Graphics g, int x, int y, int x2, int y2) {
        int px = Math.min(x,x2);
        int py = Math.min(y,y2);
        int pw=Math.abs(x-x2);
        int ph=Math.abs(y-y2);
        g.drawRect(px, py, pw, ph);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        drawPerfectRect(g, x, y, x2, y2);
    }

}