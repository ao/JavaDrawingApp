import javax.swing.*;
import java.awt.event.*;

class GeneralHandler extends WindowAdapter implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if(e.getActionCommand().equals("About")) {
            JOptionPane.showMessageDialog(null, "Java Drawing App", "About", JOptionPane.PLAIN_MESSAGE);
        } else {
            //do whatever other actions with the below
            //e.getActionCommand()
        }
    }
}