import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawingApp extends Frame {

    public DrawingApp() {
        super("Java Drawing App");
        this.setSize(800, 600);

        addMainMenu();
    private int x, y, x2, y2;

        this.setVisible(true);
        this.setLocationRelativeTo(null);
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

    protected void addButtons(JToolBar toolBar) {
        JButton btnRectangle = null;
        JButton btnCircle = null;
        JButton btnTriangle = null;

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

    protected JButton makeSidenavBar(String altText) {
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

    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
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

        if(null == this.getMenuBar()) this.setMenuBar(menuBar);
    public void setStartPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setEndPoint(int x, int y) {
        x2 = (x);
        y2 = (y);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        drawPerfectRect(g, x, y, x2, y2);
    }

}