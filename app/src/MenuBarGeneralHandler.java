import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;

public class MenuBarGeneralHandler extends WindowAdapter implements ActionListener {

    public MenuBarGeneralHandler() {}
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")) {

            Shared.app.createFrame();

        } else if (e.getActionCommand().equals("Exit")) {

            System.exit(0);

        } else if (e.getActionCommand().equals("Open")) {

            //open a file
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "gif", "bmp");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().toString();
                try {
                    BufferedImage imageData = ImageIO.read(new File(filePath));
//                    byte data[] = Files.readAllBytes();
                    Shared.app.createFrameFromBufferedImage(imageData);
                } catch (Exception e1) {}
            }

        } else if (e.getActionCommand().equals("Save")) {

            try {
                byte data[] = Shared.app.document.getSelectedFrame().getContentPane().createImage(
                        Shared.app.document.getSelectedFrame().getContentPane().getWidth(),
                        Shared.app.document.getSelectedFrame().getContentPane().getHeight())
                        .toString().getBytes();

                BufferedImage imagebuf = new Robot().createScreenCapture(Shared.app.document.getSelectedFrame().getContentPane().bounds());

                //save a file
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "gif", "bmp");
                fileChooser.setFileFilter(filter);
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().toString();
                    filePath = filePath.replace(".png", "")+".png";
//                    Files.write(Paths.get(filePath), data);

                    //TODO: need to hide the toolbar first before rendering to an image
//                    Shared.app.document.getSelectedFrame().get
//                    difInstance.getToolBarInstance().hide();

                    Graphics2D graphics2D = imagebuf.createGraphics();
                    Shared.app.document.getSelectedFrame().getContentPane().paint(graphics2D);
                    ImageIO.write(imagebuf,"jpeg", new File(filePath));
                }

            } catch (Exception e2) {}

        } else if (e.getActionCommand().equals("Undo")) {

            JOptionPane.showMessageDialog(null, "Still implementing this.. You're welcome to look at the code though!", "Undo", JOptionPane.PLAIN_MESSAGE);
//            Shared.app.document.getSelectedFrame().getContentPane()
//            DrawPane.undo(); //TODO: Need to expose this to the Shared class so we can use it here

        } else if (e.getActionCommand().equals("About")) {

            JOptionPane.showMessageDialog(null, "<html><h2>Java Drawing App</h2><br/>Created by Andrew Odendaal</html>", "About", JOptionPane.PLAIN_MESSAGE);

        } else if( e.getActionCommand().equals("Cascade")) {

            JInternalFrame ifs[] = Shared.app.document.getAllFrames();
            for (int i = 0; i < ifs.length; i++) {
                ifs[i].setBounds(
                    i * 20
                    , i * 20
                    , 800
                    , 600
                );
            }

        } else if (e.getActionCommand().equals("Tile")) {
            // How many frames do we have?
            JInternalFrame[] allframes = Shared.app.document.getAllFrames();
            int count = allframes.length;
            if (count == 0) return;

            // Determine the necessary grid size
            int sqrt = (int) Math.sqrt(count);
            int rows = sqrt;
            int cols = sqrt;
            if (rows * cols < count) {
                cols++;
                if (rows * cols < count) {
                    rows++;
                }
            }

            // Define some initial values for size & location.
            Dimension size = Shared.app.document.getSize();

            int w = size.width / cols;
            int h = size.height / rows;
            int x = 0;
            int y = 0;

            // Iterate over the frames, deiconifying any iconified frames and then
            // relocating & resizing each.
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols && ((i * cols) + j < count); j++) {
                    JInternalFrame f = allframes[(i * cols) + j];

                    if (!f.isClosed() && f.isIcon()) {
                        try {
                            f.setIcon(false);
                        } catch (PropertyVetoException ignored) {
                        }
                    }

                    Shared.app.document.getDesktopManager().resizeFrame(f, x, y, w, h);
                    x += w;
                }
                y += h; // start the next row
                x = 0;
            }
        } else if (e.getActionCommand().equals("Stroke Size")) {
            String size = JOptionPane.showInputDialog(null, "Choose a stroke size", Shared.app.stroke.getLineWidth());
            Shared.app.stroke = new BasicStroke(Integer.valueOf(size),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
        } else if (e.getActionCommand().equals("Stroke Colour")) {
            Shared.app.strokeColour = JColorChooser.showDialog(null, "Choose a stroke color", Shared.app.strokeColour);
        } else if (e.getActionCommand().equals("Fill Colour")) {
            Shared.app.fillColour = JColorChooser.showDialog(null, "Choose a fill color", Shared.app.fillColour);
        } else {

            JOptionPane.showMessageDialog(null, "Unknown Action Command: "+e.getActionCommand().toString(), "404", JOptionPane.PLAIN_MESSAGE);

        }
    }
}