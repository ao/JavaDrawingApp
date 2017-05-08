import javax.swing.*;
import java.awt.*;

public class DrawClasses {

}
class DrawPane extends JPanel {
    private String color = "BLACK";
    public DrawPane() {

    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
//        if (color.equals("BLACK")) g.setColor(Color.BLACK);
//        else g.setColor(Color.WHITE);
//        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}