import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

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
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().toString();
                try {
                    byte data[] = Files.readAllBytes(Paths.get(filePath));
                    daInstance.createFrameFromBytes(data);
                } catch (Exception e1) {}
            }

        } else if(e.getActionCommand().equals("Save")) {

            try {
                byte data[] = daInstance.document.getSelectedFrame().getContentPane().createImage(800, 600).toString().getBytes();

                BufferedImage imagebuf = new Robot().createScreenCapture(daInstance.document.getSelectedFrame().getContentPane().bounds());

                //save a file
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().toString();
//                    Files.write(Paths.get(filePath), data);

                    //need to hide the toolbar first before rendering to an image

                    Graphics2D graphics2D = imagebuf.createGraphics();
                    daInstance.document.getSelectedFrame().getContentPane().paint(graphics2D);
                    ImageIO.write(imagebuf,"jpeg", new File(filePath));
                }

            } catch (Exception e2) {}

        } else if(e.getActionCommand().equals("About")) {

            JOptionPane.showMessageDialog(null, "Java Drawing App", "About", JOptionPane.PLAIN_MESSAGE);

        } else {

            //e.getActionCommand()

        }
    }
}