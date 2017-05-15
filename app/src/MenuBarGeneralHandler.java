import javax.swing.*;
import java.awt.event.*;

public class MenuBarGeneralHandler extends WindowAdapter implements ActionListener {

    public DrawingApp daInstance;

    public MenuBarGeneralHandler(DrawingApp daInstance) {
        this.daInstance = daInstance;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("New")) {

            daInstance.createFrame();

        } else if(e.getActionCommand().equals("Exit")) {

            System.exit(0);

        } else if(e.getActionCommand().equals("Open")) {

            //open a file

        } else if(e.getActionCommand().equals("Save")) {

            //save a file

        } else if(e.getActionCommand().equals("About")) {

            JOptionPane.showMessageDialog(null, "Java Drawing App", "About", JOptionPane.PLAIN_MESSAGE);

        } else {

            //e.getActionCommand()

        }
    }
}