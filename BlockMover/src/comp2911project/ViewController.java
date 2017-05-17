package comp2911project;

import java.awt.Image;

public class ViewController {

    private final int tileSize = 80;

    private int x;
    private int y;
    private Image image;

    public ViewController(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image img) {
        image = img;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLeftCollision(ViewController actor) {
        if (((this.x() - tileSize) == actor.x()) &&
            (this.y() == actor.y())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isRightCollision(ViewController actor) {
        if (((this.x() + tileSize) == actor.x())
                && (this.y() == actor.y())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTopCollision(ViewController actor) {
        if (((this.y() - tileSize) == actor.y()) &&
            (this.x() == actor.x())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBottomCollision(ViewController actor) {
        if (((this.y() + tileSize) == actor.y())
                && (this.x() == actor.x())) {
            return true;
        } else {
            return false;
        }
    }
}