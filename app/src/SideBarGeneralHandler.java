import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

class SideBarGeneralHandler extends WindowAdapter implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if (e.getActionCommand().equals("Rectangle")) {
            //
        } else if (e.getActionCommand().equals("Circle")) {
            //
        } else if (e.getActionCommand().equals("Triangle")) {
            //
        }
    }
}