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

        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        JToolBar toolBar = new JToolBar("Toolbar", JToolBar.HORIZONTAL);
        addButtons(toolBar);
        toolBar.setFloatable(false);
        add(toolBar, BorderLayout.PAGE_START);

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
    }

}