package comp2911project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

/* This Class initializes the UI, it creates arraylists for the walls boxes and goals
 * and fills them up by reading from the map character array. and then we put them
 * all into a an array list the map
 * we Iterate through the map list and draw the objects on the correct coordinates
 * we listen to key events and check if player movement will cause collision
 * 		- if with a wall, we do nothing 
 * 		- if with a box, we
 * if collisions check returns nothing we can move the player by updating coordinates
 * 	
 */
public class Board extends JPanel { 

    private final int TILE_SIZE = 80;	//80x80px tile of space
    private final int OFFSET = 60;
    private final int LEFT_COLLISION = 1;
    private final int RIGHT_COLLISION = 2;
    private final int TOP_COLLISION = 3;
    private final int BOTTOM_COLLISION = 4;

    private ArrayList walls = new ArrayList();
    private ArrayList boxes = new ArrayList();
    private ArrayList goals = new ArrayList();
    
    private int width = 0;
    private int height = 0;
    
    private Player mario;

    private boolean finished = false;
    
    private String level =
              "#####\n"
            + "#@  #\n"
            + "# oo# ###\n"
            + "# o # #x#\n"
            + "### ###x#\n"
            + " ##    x#\n"
            + " #   #  #\n"
    		+ " #   ####\n"
            + " ########\n";
    
    public Board() {

        addKeyListener(new ka());
        setFocusable(true);
        initUI();
    }

    public int getBoardWidth() {
        return this.width;
    }

    public int getBoardHeight() {
        return this.height;
    }

    public final void initUI() {
        
        int x = OFFSET;
        int y = OFFSET;
        
        // Collision objects types
        Wall wall;
        Box box;
        Goal goal;


        // Map characters Reading
        for (int i = 0; i < level.length(); i++) {

            char ch = level.charAt(i);

            if (ch == '\n') {
                y += TILE_SIZE;
                if (this.width < x) {
                    this.width = x;
                }

                x = OFFSET;
            } else if (ch == '#') {	      //wall
                wall = new Wall(x, y);
                walls.add(wall);
                x += TILE_SIZE;
            } else if (ch == 'o') {	      //block
                box = new Box(x, y);
                boxes.add(box);
                x += TILE_SIZE;
            } else if (ch == 'x') {
                goal = new Goal(x, y);    //Goal
                goals.add(goal);
                x += TILE_SIZE;
            } else if (ch == '@') {
                mario = new Player(x, y); //Mario the Block Mover
                x += TILE_SIZE;
            } else if (ch == ' ') {
                x += TILE_SIZE;
            }

            height = y;
        }
    }

    public void generateMap(Graphics g) {

        g.setColor(new Color(45, 45, 47));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList map = new ArrayList();
        map.addAll(walls);
        map.addAll(goals);
        map.addAll(boxes);
        map.add(mario);

        for (int i = 0; i < map.size(); i++) {

            ViewController item = (ViewController) map.get(i);
            
            g.drawImage(item.getImage(), item.x(), item.y(), this);

            if (finished) {
                g.setColor(new Color(255, 255, 255));
                Font myFont = new Font ("Courier New", 1, 30);
                g.setFont(myFont);
                g.drawString("Good Job!", 180, 40);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        generateMap(g);
    }

    class ka extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (finished) {
                return;
            }

            
            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {
                if (checkWallCollision(mario,
                        LEFT_COLLISION)) {
                    return;
                }

                if (checkBoxCollision(LEFT_COLLISION)) {
                    return;
                }

                mario.move(-TILE_SIZE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {

                if (checkWallCollision(mario,
                        RIGHT_COLLISION)) {
                    return;
                }

                if (checkBoxCollision(RIGHT_COLLISION)) {
                    return;
                }

                mario.move(TILE_SIZE, 0);

            } else if (key == KeyEvent.VK_UP) {

                if (checkWallCollision(mario,
                        TOP_COLLISION)) {
                    return;
                }

                if (checkBoxCollision(TOP_COLLISION)) {
                    return;
                }

                mario.move(0, -TILE_SIZE);

            } else if (key == KeyEvent.VK_DOWN) {

                if (checkWallCollision(mario,
                        BOTTOM_COLLISION)) {
                    return;
                }

                if (checkBoxCollision(BOTTOM_COLLISION)) {
                    return;
                }

                mario.move(0, TILE_SIZE);

            } else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }

    private boolean checkWallCollision(ViewController actor, int type) {

        if (type == LEFT_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
                Wall wall = (Wall) walls.get(i);
                if (actor.isLeftCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
                Wall wall = (Wall) walls.get(i);
                if (actor.isRightCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
                Wall wall = (Wall) walls.get(i);
                if (actor.isTopCollision(wall)) {
                    return true;
                }
            }
            return false;

        } else if (type == BOTTOM_COLLISION) {

            for (int i = 0; i < walls.size(); i++) {
                Wall wall = (Wall) walls.get(i);
                if (actor.isBottomCollision(wall)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    private boolean checkBoxCollision(int type) {

        if (type == LEFT_COLLISION) {

            for (int i = 0; i < boxes.size(); i++) {

                Box box = (Box) boxes.get(i);
                if (mario.isLeftCollision(box)) {

                    for (int j=0; j < boxes.size(); j++) {
                        Box item = (Box) boxes.get(j);
                        if (!box.equals(item)) {
                            if (box.isLeftCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(box,
                                LEFT_COLLISION)) {
                            return true;
                        }
                    }
                    box.move(-TILE_SIZE, 0);
                    isfinished();
                }
            }
            return false;

        } else if (type == RIGHT_COLLISION) {

            for (int i = 0; i < boxes.size(); i++) {

                Box box = (Box) boxes.get(i);
                if (mario.isRightCollision(box)) {
                    for (int j=0; j < boxes.size(); j++) {

                        Box item = (Box) boxes.get(j);
                        if (!box.equals(item)) {
                            if (box.isRightCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(box,
                                RIGHT_COLLISION)) {
                            return true;
                        }
                    }
                    box.move(TILE_SIZE, 0);
                    isfinished();                   
                }
            }
            return false;

        } else if (type == TOP_COLLISION) {

            for (int i = 0; i < boxes.size(); i++) {

                Box box = (Box) boxes.get(i);
                if (mario.isTopCollision(box)) {
                    for (int j = 0; j < boxes.size(); j++) {

                        Box item = (Box) boxes.get(j);
                        if (!box.equals(item)) {
                            if (box.isTopCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(box,
                                TOP_COLLISION)) {
                            return true;
                        }
                    }
                    box.move(0, -TILE_SIZE);
                    isfinished();
                }
            }

            return false;

        } else if (type == BOTTOM_COLLISION) {
        
            for (int i = 0; i < boxes.size(); i++) {

                Box box = (Box) boxes.get(i);
                if (mario.isBottomCollision(box)) {
                    for (int j = 0; j < boxes.size(); j++) {

                        Box item = (Box) boxes.get(j);
                        if (!box.equals(item)) {
                            if (box.isBottomCollision(item)) {
                                return true;
                            }
                        }
                        if (checkWallCollision(box,
                                BOTTOM_COLLISION)) {
                            return true;
                        }
                    }
                    box.move(0, TILE_SIZE);
                    isfinished();
                }
            }
        }

        return false;
    }

    public void isfinished() {

        int num = boxes.size();
        int delivered = 0;

        for (int i = 0; i < num; i++) {
            Box box = (Box) boxes.get(i);
            for (int j = 0; j < num; j++) {
                Goal g = (Goal) goals.get(j);
                if (box.x() == g.x()
                        && box.y() == g.y()) {
                    delivered += 1;
                }
            }
        }

        if (delivered == num) {
            finished = true;
            repaint();
        }
    }

    public void restartLevel() {

        goals.clear();
        boxes.clear();
        walls.clear();
        initUI();
        if (finished) {
            finished = false;
        }
    }
}