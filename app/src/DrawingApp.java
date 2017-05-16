import javafx.scene.shape.Circle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicToolBarUI;
import java.awt.Color;

public class DrawingApp extends JPanel {

    public JFrame mainFrame;
    public JFrame desktopFrame;
    public DrawPane drawPane;

    public static DrawingApp daInstance;

    public List<JDesktopPane> documents;
    public JDesktopPane document;

    public JToolBar toolBar;

    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
        daInstance = app;
        app.mainFrame = new JFrame("Java Drawing App");
        app.desktopFrame = new JFrame("Desktop Area");

        app.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.mainFrame.getContentPane().setLayout(new BorderLayout());

        app.documents = new ArrayList<JDesktopPane>();
        app.document = new JDesktopPane();
        app.createFrame();
        app.mainFrame.setContentPane(app.document);

        app.document.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        app.documents.add(app.document);

        app.mainFrame.setSize(800, 600);
        app.mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        app.mainFrame.setLocationRelativeTo(null);
        app.mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        app.addMainMenu();

        app.mainFrame.setVisible(true);
        app.mainFrame.setExtendedState( app.mainFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
    }

    public void createFrame() {
        DrawingInternalFrame frame = new DrawingInternalFrame(this);
        frame.setVisible(true);
        document.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

//    public void createFrameFromBytes(byte[] bytes) {
//        //nolonger use this....
//        System.out.println(bytes.toString());
//        DrawingInternalFrame frame = new DrawingInternalFrame(this, bytes);
//        frame.setVisible(true);
//        document.add(frame);
//        try {
//            frame.setSelected(true);
//        } catch (java.beans.PropertyVetoException e) {}
//    }
    public void createFrameFromBufferedImage(BufferedImage imageData) {
        DrawingInternalFrame frame = new DrawingInternalFrame(this, imageData);
        frame.setVisible(true);
        document.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    public DrawingApp() {
        super();
    }

    private void addMainMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu mnuFile = new JMenu("File");
            JMenuItem mnuitemNew = new JMenuItem("New", KeyEvent.VK_N);
            mnuitemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
            mnuFile.add(mnuitemNew).addActionListener(new MenuBarGeneralHandler(this));

            JMenuItem mnuitemOpen = new JMenuItem("Open", KeyEvent.VK_O);
            mnuitemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
            mnuFile.add(mnuitemOpen).addActionListener(new MenuBarGeneralHandler(this));

            JMenuItem mnuitemSave = new JMenuItem("Save", KeyEvent.VK_S);
            mnuitemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
            mnuFile.add(mnuitemSave).addActionListener(new MenuBarGeneralHandler(this));

            mnuFile.add(new JMenuItem("Exit")).addActionListener(new MenuBarGeneralHandler(this));
            menuBar.add(mnuFile);

        JMenu mnuWindows = new JMenu("Windows");
            JMenuItem mnuitemCascade = new JMenuItem("Cascade");
            mnuWindows.add(mnuitemCascade).addActionListener(new MenuBarGeneralHandler(this));

            JMenuItem mnuitemTile = new JMenuItem("Tile");
            mnuWindows.add(mnuitemTile).addActionListener(new MenuBarGeneralHandler(this));
            menuBar.add(mnuWindows);

        JMenu mnuAbout = new JMenu("About");
            mnuAbout.add(new JMenuItem("About")).addActionListener(new MenuBarGeneralHandler(this));
            menuBar.add(mnuAbout);

        if(null == mainFrame.getMenuBar()) mainFrame.setJMenuBar(menuBar);
    }

}