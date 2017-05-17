package comp2911project;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

public class Player extends ViewController {

    public Player(int x, int y) {
        super(x, y);
		ImageIcon icon = new ImageIcon("resources/player.png");
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