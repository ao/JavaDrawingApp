import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
import java.io.File;
import java.nio.Buffer;
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
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "gif", "bmp");
            fileChooser.setFileFilter(filter);
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().toString();
                try {
                    BufferedImage imageData = ImageIO.read(new File(filePath));
//                    byte data[] = Files.readAllBytes();
                    daInstance.createFrameFromBufferedImage(imageData);
                } catch (Exception e1) {}
            }

        } else if(e.getActionCommand().equals("Save")) {

            try {
                byte data[] = daInstance.document.getSelectedFrame().getContentPane().createImage(
                        daInstance.document.getSelectedFrame().getContentPane().getWidth(),
                        daInstance.document.getSelectedFrame().getContentPane().getHeight())
                        .toString().getBytes();

                BufferedImage imagebuf = new Robot().createScreenCapture(daInstance.document.getSelectedFrame().getContentPane().bounds());

                //save a file
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "gif", "bmp");
                fileChooser.setFileFilter(filter);
                if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().toString();
                    filePath = filePath.replace(".png", "")+".png";
//                    Files.write(Paths.get(filePath), data);

                    //TODO: need to hide the toolbar first before rendering to an image
//                    daInstance.document.getSelectedFrame().get
//                    difInstance.getToolBarInstance().hide();

                    Graphics2D graphics2D = imagebuf.createGraphics();
                    daInstance.document.getSelectedFrame().getContentPane().paint(graphics2D);
                    ImageIO.write(imagebuf,"jpeg", new File(filePath));
                }

            } catch (Exception e2) {}

        } else if(e.getActionCommand().equals("About")) {

            JOptionPane.showMessageDialog(null, "<html><h2>Java Drawing App</h2><br/>Created by Andrew Odendaal</html>", "About", JOptionPane.PLAIN_MESSAGE);

        } else if(e.getActionCommand().equals("Cascade")) {

            JInternalFrame ifs[] = daInstance.document.getAllFrames();
            for (int i = 0; i < ifs.length; i++) {
                ifs[i].setBounds(
                        i * 20
                        , i * 20
                        , 800
                        , 600
                );
            }

        } else if(e.getActionCommand().equals("Tile")) {
            // How many frames do we have?
            JInternalFrame[] allframes = daInstance.document.getAllFrames();
            int count = allframes.length;
            if (count == 0) return;

            // Determine the necessary grid size
            int sqrt = (int)Math.sqrt(count);
            int rows = sqrt;
            int cols = sqrt;
            if (rows * cols < count) {
                cols++;
                if (rows * cols < count) {
                    rows++;
                }
            }

            // Define some initial values for size & location.
            Dimension size = daInstance.document.getSize();

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
                        } catch (PropertyVetoException ignored) {}
                    }

                    daInstance.document.getDesktopManager().resizeFrame(f, x, y, w, h);
                    x += w;
                }
                y += h; // start the next row
                x = 0;
            }
        } else {

            //e.getActionCommand()

        }
    }
}