import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.HTML;


//import Game1.GameView.kb_input;

public class SplashScreen extends JFrame{

	private Image bgImage;
	private JPanel gamescreen;
	private JPanel info;
	private JPanel menu;
	private GameModel model; 
	public JLabel level_won;
	private Sound soundMenu;
	private Sound sound;
	private Movement move;
	private JPanel controls;
	private JButton btnmic;
	private JLabel movement;
//	private JLabel timer;
	private JLabel levelShow = new JLabel();
	private int passLevel;
	private final int MENU_PANEL = 2;
	private final int GAME_PANEL = 3;
	private Font myfont;
	private JPanel menuPanel;
	private ImageIcon comFlag;
	//private JLabel info-test;

    public SplashScreen() throws IOException{

        ImageIcon webIcon = new ImageIcon("resources/images/box.png");
        setIconImage(webIcon.getImage());
        
		this.level_won = new JLabel("LEVEL COMPLETE!", SwingConstants.CENTER);
		this.level_won.setFont(myfont); 
		this.level_won.setOpaque(true);

		myfont = new Font("Arial", 1, 20);

    	bgImage = Toolkit.getDefaultToolkit().createImage("resources/images/main.png");
    	
    	model = new GameModel(); 
    	
		//addKeyListener(new ka());
    //	setBackground(Color.BLACK);
        initUI();
    }
    
    private void makeInfoScreen(int source) {
    	
    	String how_to = "<html>How to play: Warehouse Boss!<br>"+
    	    	"You\'re the boss of a warehouse and all your employees are on on strike! It\'s up to you to swiftly<br>"+
    	    			" move all the unsorted goods into place, but the boxes are too heavy to pull, you can only push them."+
    	    	"But be careful not to get any boxes stuck against a wall!<br>"+
    	    			"Use the direction arrows to move boxes onto goal squares, once all boxes are on a goal square the level is complete. <br>"+
    	    	"Press \'r\' to reset the level.<br>"+
    	    			 "Press \'n\' to navigate to the next level, or \'p\' for the previous level.<br>"+
    	    	"Complete all levels in order to win the game.<br>"+"</html>";
    	
	   JLabel info_text = new JLabel(how_to);
	   info_text.setPreferredSize(new Dimension(600,600));
	    	    	
    	    	    	
    	info = new JPanel();
    	info.setPreferredSize(new Dimension(800,600));
    	info_text.setFont(new Font("Arial", Font.PLAIN, 20));
    	
    	JButton back_to_menu;
    	if( source == MENU_PANEL){
    		back_to_menu = new JButton("Back To Menu");
    	} else {
    		back_to_menu = new JButton("Back To Game");
    	}
    	
        back_to_menu.addActionListener((ActionEvent event) -> {
        	if(source == MENU_PANEL){
        		setContentPane(this.menu);
        	} else {
        		model.prev_level(); 
        		makeGameScreen();
            	setContentPane(gamescreen);
            	model.view.requestFocus();
        	}
        	//makeGameScreen();
        	//setContentPane(this.menu);
        	revalidate();
        	pack();
        });
        
        info.add(info_text, BorderLayout.CENTER);
        info.add(back_to_menu, BorderLayout.SOUTH);
    	
    }
    
    @SuppressWarnings("deprecation")
	private void makeGameScreen() {
    	
    	gamescreen = new JPanel(new BorderLayout());
    	//gamescreen.setPreferredSize(new Dimension(600,500));
    	
    	controls = new JPanel(new GridLayout(8,1));;
    	
    	model.get_next_level();
    	
    	levelShow.setText("<html><h3>Level:  " + model.levels.level_counter+"<br> "+this.passLevel+" levels Completed <li></html>");
    	levelShow.setFont(new Font("Arial", Font.PLAIN, 20));
    	if(model.levels.won[model.levels.level_counter]){
    		comFlag = new ImageIcon("resources/images/tick.png");
    	} else {
    		comFlag = new ImageIcon("");
    	}
		levelShow.setIcon(comFlag);
    	JButton btnReset = new JButton("Reset");
    	JButton btnPrevLev = new JButton("Prev Level");
    	JButton btnNextLev = new JButton("Next Level");
    	btnmic = new JButton("Stop Music");
    	JButton btnInfo = new JButton("Help");
    	JButton back_to_menu = new JButton("Back");
    	

    	if(!soundMenu.isplay()){
    		sound.stopMusic();
    		btnmic.setLabel("Play Music");
    	}
    	
    	btnReset.addActionListener((ActionEvent event) -> {
//    		t.reset();
    		move.resetMovement();
    		System.out.println("RELOAD");
            model.prev_level();  
            reset_game_view();
            makeGameScreen();
        	setContentPane(gamescreen);
        	model.view.requestFocus();
        });
    	
    	btnPrevLev.addActionListener((ActionEvent event) -> {
//    		t.reset();
    		move.resetMovement();
    		System.out.println("PREV LEVEL");
            model.prev_level(); 
            model.prev_level();
            reset_game_view();
            makeGameScreen();
        	setContentPane(gamescreen);
        	model.view.requestFocus();
        });
    	
    	btnNextLev.addActionListener((ActionEvent event) -> {
//    		t.reset();
    		move.resetMovement();
    		System.out.println("NEXT LEVEL");
            model.reload_level(); 
            reset_game_view();
            makeGameScreen();
        	setContentPane(gamescreen);
        	model.view.requestFocus();
        });
    	
    	btnmic.addActionListener((ActionEvent event) -> {
    		if(!sound.isplay()){
				sound.soundLoad();btnmic.setLabel("Stop Music");
			} else {
				sound.stopMusic();btnmic.setLabel("Play Music");
			}
    		model.prev_level();
    		makeGameScreen();
        	setContentPane(gamescreen);
        	model.view.requestFocus();
        });
    	
        back_to_menu.addActionListener((ActionEvent event) -> {
        	sound.stopMusic();
        	if(soundMenu.isplay()){soundMenu.aau.loop();}
        	//makeGameScreen();
        	setContentPane(this.menu);
        	revalidate();
        	pack();
        });
        
        btnInfo.addActionListener((ActionEvent event) -> {
        	makeInfoScreen(GAME_PANEL);
        	setContentPane(info);
        	revalidate();
        	pack();
        });

        controls.add(levelShow);
//        controls.add(timer);
		controls.add(movement);
		controls.add(btnReset);
		controls.add(btnPrevLev);
		controls.add(btnNextLev);
		controls.add(btnmic);
		controls.add(btnInfo);
		controls.add(back_to_menu);
    	
    	model.view.addKeyListener(new ka());
    	gamescreen.add(controls, BorderLayout.EAST);
    	gamescreen.add(model.view, BorderLayout.CENTER);
    	
    	//getContentPane().add(model.view.game_panel);
    	setVisible(true);
    	
    }
    
    private void reset_game_view() {
    	
    	gamescreen.add(model.view, BorderLayout.CENTER);
    	levelShow.setText("<html><h3>Level:  " + model.levels.level_counter+"<br>" +this.passLevel+" levels Completed <li></html>");
    	levelShow.setFont(new Font("Serif", Font.PLAIN, 20));
    	if(model.levels.won[model.levels.level_counter]){
    		comFlag = new ImageIcon("resources/images/tick.png");
    	} else {
    		comFlag = new ImageIcon("");
    	}
		levelShow.setIcon(comFlag);
    	//model.update_view(model.get_model());
    	//pack();
    	//setVisible(true);
    	
    }
    
    private JPanel initMenu() {
    	
    	menuPanel = new JPanel();
        
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));	
        menuPanel.setBackground(new Color(0,0,0,0));
 
        ImageIcon icon = new ImageIcon("resources/images/main-menu-buttons.png");
        Font myFont = new Font ("Courier New", 1, 20);
        
        JButton newgameBtn = createMainLabel("New Game", myFont, icon);
        JButton infoBtn = createMainLabel("Info", myFont, icon);
        JButton musBtn = createMainLabel("Play Music", myFont, icon);
        JButton quitBtn = createMainLabel("Quit", myFont, icon);
  
        
		soundMenu = new Sound(menuPanel, "resources/sound/all for you.wav");
        newgameBtn.addActionListener((ActionEvent event) -> {
//        	timer = new JLabel("01:00");
//        	t = new SetTimer(timer);
        	movement = new JLabel("     Movement: 0");
        	move = new Movement(movement);
        	sound = new Sound(gamescreen, "resources/sound/Color X.wav");
        	soundMenu.aau.stop();
        	if(!soundMenu.isplay()){sound.stopMusic();}
        	makeGameScreen();
        	setContentPane(gamescreen);
        	model.view.requestFocus();
        	revalidate();
        	pack();
        });
        
        infoBtn.addActionListener((ActionEvent event) -> {
        	makeInfoScreen(MENU_PANEL);
        	setContentPane(info);
        	revalidate();
        	pack();
        });
        
        musBtn.addActionListener((ActionEvent event) -> {
    		if(!soundMenu.isplay()){
				soundMenu.soundLoad();musBtn.setLabel("Stop Music");
			} else {
				soundMenu.stopMusic();musBtn.setLabel("Play Music");
			}
    		setContentPane(this.menu);
        	revalidate();
        	pack();
        });
        
        quitBtn.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        menuPanel.setPreferredSize(new Dimension(900, 900));
        
     
        menuPanel.add(Box.createRigidArea(new Dimension(50, 70)));
        menuPanel.add(newgameBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(musBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(infoBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        menuPanel.add(quitBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        return menuPanel;
    	
    }
    
    private JButton createMainLabel(String txt, Font mFont, ImageIcon ic){
        JButton label = new JButton();
//        label.setPreferredSize(new Dimension(40, 60));
        label.setBackground(new Color(0,0,0,0));
        label.setIcon(ic);
        label.setText(txt);
        label.setFont(mFont);
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
        label.setBorder(emptyBorder);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.CENTER);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setAlignmentY(Component.LEFT_ALIGNMENT);
        
        menuPanel.add(label);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        return label;
    }

    private void initUI() {
    	
        JPanel backgroundPanel = new MyPanel();
        this.menu = backgroundPanel;
        
        JPanel menuPanel = initMenu();
        
        backgroundPanel.setPreferredSize(new Dimension(800,600));
        backgroundPanel.add(menuPanel);

        backgroundPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        add(backgroundPanel);

        pack();

        setTitle("Warehouse Boss");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    private void level_done() {

    	if(!model.levels.won[model.levels.level_counter]){
        	this.passLevel++;
        	model.levels.won[model.levels.level_counter] = true;
        	
    		
    	}	
    	
    	this.level_won.setOpaque(true);
    	gamescreen.add(this.level_won, BorderLayout.CENTER);
//    	t.reset();
    	move.resetMovement();
    }

    private class MyPanel extends JPanel{
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

	class ka extends KeyAdapter {

		@Override
        public void keyPressed(KeyEvent e) {
                   
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
            	if(sound.isplay()){sound.keyboardSound("left");}
            	System.out.println("LEFT");
            	model.update_player_position(1);
            	move.addMove();
            } else if (key == KeyEvent.VK_RIGHT) {
            	if(sound.isplay()){sound.keyboardSound("right");}
            	System.out.println("RIGHT");
            	model.update_player_position(3);
            	move.addMove();

            } else if (key == KeyEvent.VK_UP) {
            	if(sound.isplay()){sound.keyboardSound("up");}
            	System.out.println("up");
            	model.update_player_position(0);
            	move.addMove();
            } else if (key == KeyEvent.VK_DOWN) {
            	if(sound.isplay()){sound.keyboardSound("down");}
            	model.update_player_position(2);
            	move.addMove();
            } else if (key == KeyEvent.VK_R) {
//            	t.reset();
        		move.resetMovement();
        		System.out.println("RELOAD");
                model.reload_level(); 
                reset_game_view();
                //return;
                
            } else if (key == KeyEvent.VK_N) {
//            	t.reset();
        		move.resetMovement();
            	System.out.println("NEXT LEVEL");
                model.get_next_level();  
                reset_game_view();
               // return;
                
            } else if (key == KeyEvent.VK_P) {
//            	t.reset();
        		move.resetMovement();
            	System.out.println("PREV LEVEL");
                model.prev_level();  
                reset_game_view();
                //return;                               
            } else if ((key == KeyEvent.VK_ESCAPE)){
            	System.exit(0);
            }
            
			if (model.game_won()) { 
	        	   //TODO
	        		level_done();
	        		levelShow.setText("     Level:	"+model.levels.level_counter+" complete");
	               	System.out.println("lEVEL COMPLETED");
	                model.get_next_level();  
	                reset_game_view();
	            	next_level();
	            }
			move.displayMove();
            pack();
        	model.view.revalidate();
        	
        }
    }

	private void next_level(){
		try {
	        Robot robot = new Robot();
	        
	        // Simulate a key press
	        System.out.println("Pressing n");
	        robot.keyPress(KeyEvent.VK_N);
	        robot.keyRelease(KeyEvent.VK_N);
	
		} catch (AWTException e) {
		        e.printStackTrace();
		}
	}
	
    public static void main(String[] args) throws IOException{
    	
//        SwingUtilities.invokeLater(() -> {
//            SplashScreen ex = new SplashScreen();
//            ex.setVisible(true);
//        });
    	JFrame s = new SplashScreen();
    	s.setVisible(true);
    }
    
}
