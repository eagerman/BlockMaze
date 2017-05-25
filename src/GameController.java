import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameController {

	private boolean finished;
	private GameModel model;
	
	public GameController(GameModel m) {
		
		this.model = m;
		this.finished = false;
		
	}
    
    public class ka extends KeyAdapter {

		@Override
        public void keyPressed(KeyEvent e) {

            if (finished) { //TODO have finished flag
                return;
            }
            
            int key = e.getKeyCode();

            	System.out.println("key is"+key);

            if (key == KeyEvent.VK_LEFT) {
 
            	System.out.println("LEFT");
            	model.update_player_position(0);

            } else if (key == KeyEvent.VK_RIGHT) {
            	
            	System.out.println("RIGHT");
            	model.update_player_position(2);


            } else if (key == KeyEvent.VK_UP) {

            	System.out.println("up");
            	model.update_player_position(3);

            } else if (key == KeyEvent.VK_DOWN) {

            	model.update_player_position(1);

            } else if (key == KeyEvent.VK_R) {
               // restartLevel();   //TODO load level from LevelManager again
            }

        }
    }

	
}
