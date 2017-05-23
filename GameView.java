import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameView extends JFrame {

	private static final long serialVersionUID = 1L; 
	private JPanel game_panel;
	private JLabel[][] tiles;
	private int rows;
	private int cols;
	private GameModel model;
	public JLabel level_won;
	
	public GameView(char[][] model, int rows, int cols, GameModel m) { 

		this.model = m;
		this.rows = rows;
		this.cols = cols;
		
		this.level_won = new JLabel("LEVEL COMPLETE!", SwingConstants.CENTER);
		Font myfont = new Font("Arial", 1, 20);
		this.level_won.setFont(myfont);
		
		addKeyListener(new kb_input());
		setFocusable(true);
		setBackground(Color.GREEN);
		initTiles(model);
		initUI();
		
		
	}
	
	public void initTiles(char[][] model) { 
		
		this.tiles = new JLabel[this.rows][this.cols];
		
		for(int i=0; i < this.rows; i++) {
			
			for(int j=0; j < this.cols; j++) {
				
				if(model[i][j] == ' ') {
					
					ImageIcon whitebox = new ImageIcon("bigwhite.jpeg");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(20,20));
					
				}else if(model[i][j] == '@') {
					
					ImageIcon whitebox = new ImageIcon("arrived.png");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(20,20));
					
				}else if(model[i][j] == '#') {
					
					ImageIcon whitebox = new ImageIcon("box.png");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(20,20));

				}else if(model[i][j] == '^' || model[i][j] == '&') {
					
					ImageIcon whitebox = new ImageIcon("player.png");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(20,20));
					
				}else if(model[i][j] == '!') {
					
					ImageIcon whitebox = new ImageIcon("goal.png");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(20,20));
					
				}else{

					ImageIcon blackbox = new ImageIcon("wall.png");
					this.tiles[i][j] = new JLabel(blackbox);
					this.tiles[i][j].setPreferredSize(new Dimension(20,20));
					
				}
				
			}
			
		}
		
	}
	
	public void level_done() {
		
		add(this.level_won);
		this.level_won.setPreferredSize(new Dimension(300,300));
		setContentPane(this.level_won);
		
	}
	
	public void initUI() {
		
		if(!this.model.game_won()) {

		this.game_panel = new JPanel(new GridLayout(this.rows,this.cols)); 
		
			for(int i=0; i < this.rows; i++) {
			
				for(int j=0; j < this.cols; j++) {

					this.game_panel.add(this.tiles[i][j]);
				
				}
			
			}
		
			getContentPane().add(this.game_panel);
		
		}else{
			
			level_done();
			
		}
        pack();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
		
		setVisible(true);
		
	}
	
	class kb_input extends KeyAdapter {

		
		@Override
        public void keyPressed(KeyEvent e) {

            
            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {
 
            	//System.out.println("LEFT");
            	model.update_player_position(1);

            } else if (key == KeyEvent.VK_RIGHT) {
            	
            	//System.out.println("RIGHT");
            	model.update_player_position(3);


            } else if (key == KeyEvent.VK_UP) {

            //	System.out.println("up");
            	model.update_player_position(0);

            } else if (key == KeyEvent.VK_DOWN) {

            	model.update_player_position(2);

            } else if (key == KeyEvent.VK_R) {

               // restartLevel();   
            	
            } else if (key == KeyEvent.VK_N) {

                model.init_game_model();   
             	
             } 

        }
    }

}
