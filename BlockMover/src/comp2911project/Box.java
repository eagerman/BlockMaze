package comp2911project;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import javax.swing.ImageIcon;

public class Box extends ViewController {

    public Box(int x, int y) {
        super(x, y);
        ImageIcon icon = new ImageIcon("resources/box.png");
        Image image = icon.getImage();
        this.setImage(image);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }
}