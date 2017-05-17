package comp2911project;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Wall extends ViewController {

    private Image image;

    public Wall(int x, int y) {
        super(x, y);

        ImageIcon icon = new ImageIcon("resources/wall.png");
        image = icon.getImage();
        this.setImage(image);

    }
}