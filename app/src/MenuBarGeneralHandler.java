import javax.swing.*;
import java.awt.event.*;

class MenuBarGeneralHandler extends WindowAdapter implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if(e.getActionCommand().equals("About")) {
            JOptionPane.showMessageDialog(null, "Java Drawing App", "About", JOptionPane.PLAIN_MESSAGE);
        } else {
            //e.getActionCommand()
            if (e.getActionCommand().equals("Rectangle")) {
                //
            } else if (e.getActionCommand().equals("Circle")) {
                //
            } else if (e.getActionCommand().equals("Triangle")) {
                //
            }
        }
    }
}