import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class SideBarGeneralHandler extends WindowAdapter implements ActionListener {
    DrawingInternalFrame daInstance;

    public void setInstance(DrawingInternalFrame daInstance) {
        this.daInstance = daInstance;
    }
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Rectangle")) {
            daInstance.currentShape = e.getActionCommand();
        } else if (e.getActionCommand().equals("Circle")) {
            daInstance.currentShape = e.getActionCommand();
        } else if (e.getActionCommand().equals("FreeDraw")) {
            daInstance.currentShape = e.getActionCommand();
        }
    }
}