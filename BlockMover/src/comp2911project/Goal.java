package comp2911project;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Goal extends ViewController {

    public Goal(int x, int y) {
        super(x, y);
        ImageIcon icon = new ImageIcon("resources/goal.png");
        Image image = icon.getImage();
        this.setImage(image);
    }
}