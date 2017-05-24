package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class SplashScreen extends JFrame{

	private Image bgImage;
	private JPanel gamescreen;
	private JPanel info;
	private JPanel menu;
	private GameModel model; 
	// The background sound
	private Sound soundMenu;
	private ka key;
	Sound sound;
	private final int MENU_PANEL = 2;
	private final int GAME_PANEL = 3;

	//private JLabel info-test;

    public SplashScreen() throws IOException{

    	bgImage = Toolkit.getDefaultToolkit().createImage("SimpleCrate.png");
    	model = new GameModel(); 
    	key = new ka();
		//addKeyListener(new ka());
        initUI();
    }
    
    private void makeInfoScreen(int source) {
    	
    	String how_to = "<html>This is how you play the game etc<br></html>";
    	JLabel info_text = new JLabel(how_to);
    	info_text.setPreferredSize(new Dimension(150,150));
    	
    	info = new JPanel();
    	info.setPreferredSize(new Dimension(200,200));
    	info.setFont(new Font("Serif", Font.PLAIN, 14));
    	
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
        		setContentPane(this.gamescreen);
        	}
        	//setContentPane(this.menu);
        	revalidate();
        	pack();
        });
        
        info.add(info_text, BorderLayout.CENTER);
        info.add(back_to_menu, BorderLayout.SOUTH);
    	
    }
    
    private void makeGameScreen() {
    	
    	gamescreen = new JPanel(new BorderLayout());
    	//gamescreen.setPreferredSize(new Dimension(600,500));
    	
    	JPanel controls = new JPanel(new GridLayout(8,1));
    	
    	model.init_game_model();
    	model.view.addKeyListener(key);
    	//ImageIcon down_arrow = new ImageIcon("arrow-down.png");
    	//JButton down_action = new JButton(down_arrow);

    	JButton btnReset = new JButton("Reset");
    	JButton btnPrevLev = new JButton("Prev Level");
    	JButton btnNextLev = new JButton("Next Level");
    	JButton btnmic = new JButton("Close Music");
    	JButton btnInfo = new JButton("Help");
    	JButton back_to_menu = new JButton("Back");
    	sound = new Sound(controls, btnmic, "Color X.wav");
    	JButton timer = new JButton("01:00");
    	//SetTimer t = new SetTimer(timer);
    	JButton movement = new JButton("Movement: 0");
    	//Movement move = new Movement(movement);
    	
    	if(!soundMenu.isplay()){
    		sound.stopMusic();
    		btnmic.setLabel("Open Music");
    	}
    	
        back_to_menu.addActionListener((ActionEvent event) -> {
        	sound.stopMusic();
        	if(soundMenu.isplay()){soundMenu.aau.loop();}
        	model.view.removeKeyListener(key);
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

		controls.add(timer);
		controls.add(movement);
		controls.add(btnReset);
		controls.add(btnPrevLev);
		controls.add(btnNextLev);
		controls.add(btnmic);
		controls.add(btnInfo);
		controls.add(back_to_menu);
		
		//model.view.addKeyListener(new ka());
		
    	gamescreen.add(controls, BorderLayout.EAST);
    	gamescreen.add(model.view, BorderLayout.CENTER);
    	//getContentPane().add(model.view.game_panel);
    	setVisible(true);
    	
    }
    
    private JPanel initMenu() {
    	
    	JPanel menuPanel = new JPanel();
        
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));	
        menuPanel.setBackground(new Color(0,0,0,0));
        
        ImageIcon infoIcon = new ImageIcon("questionMark2.png");
        ImageIcon newgameIcon = new ImageIcon("tick.png");
        ImageIcon quitIcon = new ImageIcon("Closepre.png");

        JButton quitBtn = new JButton("Quit", quitIcon);
        JButton infoBtn = new JButton("Info", infoIcon);
        JButton musBtn = new JButton("Close Music");
        JButton newgameBtn = new JButton("Play", newgameIcon);
        
        soundMenu = new Sound(menuPanel, musBtn, "all for you.wav");
        
        newgameBtn.addActionListener((ActionEvent event) -> {
        	makeGameScreen();
        	soundMenu.aau.stop();
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
        
        quitBtn.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        menuPanel.setPreferredSize(new Dimension(150, 250));
        
        //group layout actually has a linkSize method that could save me the bother here
        
        quitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        quitBtn.setPreferredSize(new Dimension(25, 25));
        musBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        musBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        musBtn.setPreferredSize(new Dimension(25, 25));
        infoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        infoBtn.setPreferredSize(new Dimension(25, 25));
        newgameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        newgameBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        newgameBtn.setPreferredSize(new Dimension(25, 25));
 
        menuPanel.add(Box.createRigidArea(new Dimension(50, 50)));
        menuPanel.add(newgameBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(musBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(infoBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        menuPanel.add(quitBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        return menuPanel;
    	
    }

    private void initUI() {
    	
        JPanel backgroundPanel = new MyPanel();
        this.menu = backgroundPanel;
        
        JPanel menuPanel = initMenu();
        
        backgroundPanel.setPreferredSize(new Dimension(300,300));
        backgroundPanel.add(menuPanel);

        backgroundPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        add(backgroundPanel);

        pack();
        
        setTitle("Warehouse Boss");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
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
          // if (model.game_won()) { //TODO have finished flag
          //      return;
         //   }
            
            int key = e.getKeyCode();
            

            if (key == KeyEvent.VK_LEFT) {
            	if(soundMenu.isplay()){sound.keyboardSound("left");}
            	System.out.println("LEFT");
            	model.update_player_position(1);

            } else if (key == KeyEvent.VK_RIGHT) {
            	if(soundMenu.isplay()){sound.keyboardSound("right");}
            	System.out.println("RIGHT");
            	model.update_player_position(3);
            	

            } else if (key == KeyEvent.VK_UP) {
            	if(soundMenu.isplay()){sound.keyboardSound("up");}
            	System.out.println("up");
            	model.update_player_position(0);

            } else if (key == KeyEvent.VK_DOWN) {
            	if(soundMenu.isplay()){sound.keyboardSound("down");}
            	model.update_player_position(2);

            } else if (key == KeyEvent.VK_R) {
            	
            	System.out.println("SUCESS");
               // restartLevel();   
            }
        	model.view.revalidate();
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