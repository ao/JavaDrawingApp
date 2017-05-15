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
import javax.swing.plaf.basic.BasicToolBarUI;
import java.awt.Color;

public class DrawingApp extends JPanel {

    public JFrame mainFrame;
    public DrawPane drawPane;

    public static DrawingApp daInstance;

    public List<JDesktopPane> documents;
    public JDesktopPane document;

    private boolean Am_I_In_FullScreen = false;
    private int PrevX, PrevY, PrevWidth, PrevHeight;

    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
        daInstance = app;
        app.mainFrame = new JFrame("Java Drawing App");

        app.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        app.addLayersToolbar();

        app.mainFrame.setVisible(true);

        app.mainFrame.setExtendedState( app.mainFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH );
    }

    public void createFrame() {
        DrawingInternalFrame frame = new DrawingInternalFrame();
        frame.setVisible(true); //necessary as of 1.3
        document.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {}
    }

    public DrawingApp() {
        super();
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

    private void addMainMenu() {
        MenuBar menuBar = new MenuBar();

        Menu mnuFile = new Menu("File");
            mnuFile.add(new MenuItem("New")).addActionListener(new MenuBarGeneralHandler(this));
            mnuFile.add(new MenuItem("Save")).addActionListener(new MenuBarGeneralHandler(this));
            mnuFile.add(new MenuItem("Exit")).addActionListener(new MenuBarGeneralHandler(this));
            menuBar.add(mnuFile);

        Menu mnuAbout = new Menu("About");
            mnuAbout.add(new MenuItem("About")).addActionListener(new MenuBarGeneralHandler(this));
            menuBar.add(mnuAbout);

        if(null == mainFrame.getMenuBar()) mainFrame.setMenuBar(menuBar);
    }

}