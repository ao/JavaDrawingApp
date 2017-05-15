import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class MenuBarSubGeneralHandler extends WindowAdapter implements ActionListener {

    public DrawingInternalFrame daInstance;

    public MenuBarSubGeneralHandler(DrawingInternalFrame daInstance) {
        this.daInstance = daInstance;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Show")) {

        } else if (e.getActionCommand().equals("Don't Show")) {

        }
        //e.getActionCommand()
    }
}