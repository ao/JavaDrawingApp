/**
 * File Description: This is the main entry point of the DA and handles the setup of the main menu.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawingApp extends JPanel {

    public JFrame mainFrame;
    public JFrame desktopFrame;

    public List<JDesktopPane> documents;
    public JDesktopPane document;

    public static void main(String[] args) {

        DrawingApp app = new DrawingApp();
        Shared.app = app;

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
        DrawingInternalFrame frame = new DrawingInternalFrame();
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
        DrawingInternalFrame frame = new DrawingInternalFrame(imageData, imageData.getWidth(), imageData.getHeight());
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
            mnuitemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
            mnuFile.add(mnuitemNew).addActionListener(new MenuBarHandler());

            JMenuItem mnuitemOpen = new JMenuItem("Open", KeyEvent.VK_O);
            mnuitemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
            mnuFile.add(mnuitemOpen).addActionListener(new MenuBarHandler());

            JMenuItem mnuitemSave = new JMenuItem("Save", KeyEvent.VK_S);
            mnuitemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
            mnuFile.add(mnuitemSave).addActionListener(new MenuBarHandler());

            mnuFile.add(new JMenuItem("Exit")).addActionListener(new MenuBarHandler());
            menuBar.add(mnuFile);

        JMenu mnuEdit = new JMenu("Edit");
            JMenuItem mnuitemUndo = new JMenuItem("Undo");
            mnuitemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
            mnuEdit.add(mnuitemUndo).addActionListener(new MenuBarHandler());
            menuBar.add(mnuEdit);

        JMenu mnuSet = new JMenu("Set");
            JMenuItem mnuitemStrokeSize = new JMenuItem("Stroke Size");
            mnuSet.add(mnuitemStrokeSize).addActionListener(new MenuBarHandler());

            JMenuItem mnuitemStrokeColour = new JMenuItem("Stroke Colour");
            mnuSet.add(mnuitemStrokeColour).addActionListener(new MenuBarHandler());

            JMenuItem mnuitemFillColour = new JMenuItem("Fill Colour");
            mnuSet.add(mnuitemFillColour).addActionListener(new MenuBarHandler());
            menuBar.add(mnuSet);

        JMenu mnuWindows = new JMenu("Windows");
            JMenuItem mnuitemCascade = new JMenuItem("Cascade");
            mnuWindows.add(mnuitemCascade).addActionListener(new MenuBarHandler());

            JMenuItem mnuitemTile = new JMenuItem("Tile");
            mnuWindows.add(mnuitemTile).addActionListener(new MenuBarHandler());
            menuBar.add(mnuWindows);

        JMenu mnuAbout = new JMenu("About");
            mnuAbout.add(new JMenuItem("About")).addActionListener(new MenuBarHandler());
            menuBar.add(mnuAbout);

        if(null == mainFrame.getMenuBar()) mainFrame.setJMenuBar(menuBar);
    }

}