
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameView extends JPanel {

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
		
		//addKeyListener(new kb_input());
		setFocusable(true);
		this.setBackground(Color.GREEN);
		initTiles(model);
		initUI();
		
		
	}
	
	public void initTiles(char[][] model) { 
		
		this.tiles = new JLabel[this.rows][this.cols];

		for(int i=0; i < this.rows; i++) {
			
			for(int j=0; j < this.cols; j++) {
				
				if(model[i][j] == ' ') {
					
					ImageIcon whitebox = new ImageIcon("resources/images/blank.png");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(40,40));
					
				}else if(model[i][j] == '@') {
					
					ImageIcon whitebox = new ImageIcon("resources/images/arrived.png");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(40,40));
					
				}else if(model[i][j] == '#') {
					
					ImageIcon whitebox = new ImageIcon("resources/images/box.png");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(40,40));
					
				}else if(model[i][j] == '^' || model[i][j] == '&') {
					
					ImageIcon whitebox = new ImageIcon("resources/images/player.png");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(40,40));
					
				}else if(model[i][j] == '!') {
					
					ImageIcon whitebox = new ImageIcon("resources/images/goal.png");
					this.tiles[i][j] = new JLabel(whitebox);
					this.tiles[i][j].setPreferredSize(new Dimension(40,40));
					
				}else{

					ImageIcon blackbox = new ImageIcon("resources/images/wall.png");
					this.tiles[i][j] = new JLabel(blackbox);
					this.tiles[i][j].setPreferredSize(new Dimension(40,40));
					
				}
				
			}
			
		}
		//this.setPreferredSize(new Dimension(40*this.cols,40*this.rows));

		this.validate();
	}
	
	public void initUI() {


			this.removeAll();
			this.setLayout(new GridLayout(this.rows,this.cols));
			
			for(int i=0; i < this.rows; i++) {
				
				for(int j=0; j < this.cols; j++) {

					this.add(this.tiles[i][j]);
					
				}
				
			}
		
	}
	
}
