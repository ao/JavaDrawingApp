import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawingApp extends Frame {

    public DrawingApp() {
        super("Java Drawing App");
        this.setSize(800, 600);
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
        app.addMainMenu();
    }

    private void addMainMenu() {
        MenuBar menuBar = new MenuBar();

        Menu file = new Menu("File");
        Menu shape = new Menu("Shapes");
        Menu about = new Menu("About");

        file.add(new MenuItem("Exit"));

        shape.add(new MenuItem("Rectangle"));
        shape.add(new MenuItem("Circle"));
        shape.add(new MenuItem("Triangle"));


        about.add(new MenuItem("About"));

        menuBar.add(file);
        menuBar.add(shape);
        menuBar.add(about);

        if(null == this.getMenuBar()) this.setMenuBar(menuBar);
    }
}