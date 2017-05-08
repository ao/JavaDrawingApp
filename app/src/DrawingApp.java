import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawingApp extends Frame {

    public DrawingApp() {
        super("Java Drawing App");
        this.setSize(800, 600);

        addMainMenu();

        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        DrawingApp app = new DrawingApp();
    }

    private void addMainMenu() {
        MenuBar menuBar = new MenuBar();

        Menu mnuFile = new Menu("File");
            mnuFile.add(new MenuItem("Exit")).addActionListener(new GeneralHandler());
            menuBar.add(mnuFile);

        Menu mnuShapes = new Menu("Shapes");
            mnuShapes.add(new MenuItem("Rectangle")).addActionListener(new GeneralHandler());
            mnuShapes.add(new MenuItem("Circle")).addActionListener(new GeneralHandler());
            mnuShapes.add(new MenuItem("Triangle")).addActionListener(new GeneralHandler());
            menuBar.add(mnuShapes);

        Menu mnuAbout = new Menu("About");
            mnuAbout.add(new MenuItem("About")).addActionListener(new GeneralHandler());
            menuBar.add(mnuAbout);

        if(null == this.getMenuBar()) this.setMenuBar(menuBar);
    }

}