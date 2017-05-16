import java.awt.*;
import java.awt.image.BufferedImage;

public class Shared {

    public static SizedStack<BufferedImage> undoStack = new SizedStack<>(12);

    public static DrawingApp app;

    public static Graphics2D graphics;

//    public static void undo() {
//        if (undoStack.size() > 0) {
//            setImage(undoStack.pop());
//        }
//    }
//
//    public static void saveToStack(Image img) {
//        undoStack.push(copyImage(img));
//    }
//
////    public static void setImage(Image img) {
////        graphics = (Graphics2D) img.getGraphics();
////        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
////        graphics.setPaint(Color.black);
//////        image = img;
////        repaint();
////    }
//
//    public static BufferedImage copyImage(Image img) {
//        BufferedImage copyOfImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
//        Graphics g = copyOfImage.createGraphics();
//        g.drawImage(img, 0, 0, getWidth(), getHeight(), null);
//        return copyOfImage;
//    }
}
