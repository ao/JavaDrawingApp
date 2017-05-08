import javax.swing.*;
import java.awt.*;

public class DrawClasses {

}
class DrawRect extends JPanel{
    private String color = "BLACK";
    public DrawRect(String b) {
        color = b ;
    }

    @Override
    protected void paintComponent (Graphics g){
        super.paintComponent(g);
        if (color.equals("BLACK"))
            g.setColor(Color.BLACK);
        else
            g.setColor(Color.WHITE);
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        //add the square with the specified color

    }
}