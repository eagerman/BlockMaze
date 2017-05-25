

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import Game1.GameView.kb_input;


public class SplashScreen extends JFrame{

	private Image bgImage;
	private JPanel gamescreen;
	private JPanel info;
	private JPanel menu;
	private GameModel model; 
	public JLabel level_won;

	//private JLabel info-test;

    public SplashScreen() throws IOException{
    	
		this.level_won = new JLabel("LEVEL COMPLETE!", SwingConstants.CENTER);
		this.level_won.setOpaque(true);

		Font myfont = new Font("Arial", 1, 20);
		this.level_won.setFont(myfont);


        ImageIcon webIcon = new ImageIcon("resources/images/box.png");
        setIconImage(webIcon.getImage());

    	bgImage = Toolkit.getDefaultToolkit().createImage("resources/images/SimpleCrate.png");
    	model = new GameModel(); 
		//addKeyListener(new ka());
    	setBackground(Color.BLACK);
        initUI();
    }
    
    private void makeInfoScreen() {
    	
    	String how_to = "<html>This is how you play the game etc<br></html>";
    	JLabel info_text = new JLabel(how_to);
    	info_text.setPreferredSize(new Dimension(150,150));
    	
    	info = new JPanel();
    	info.setPreferredSize(new Dimension(200,200));
    	info.setFont(new Font("Serif", Font.PLAIN, 14));
    	
    	JButton back_to_menu = new JButton("Back");
    	
        back_to_menu.addActionListener((ActionEvent event) -> {
        	makeGameScreen();
        	setContentPane(this.menu);
        	revalidate();
        	pack();
        });
        
        info.add(info_text, BorderLayout.CENTER);
        info.add(back_to_menu, BorderLayout.SOUTH);
    	
    }
    
    private void makeGameScreen() {
    	
    	gamescreen = new JPanel(new BorderLayout());
//    	/gamescreen.setPreferredSize(new Dimension(300,300));
    	
    	JPanel controls = new JPanel();
    	//JPanel game = new JPanel();
    	/*
		model.open_file("test2.txt");
		model.set_goal_number();
		model.print_goals();
    	*/
    	model.init_game_model();
    	//setContentPane(this.model.get_view())'
    	ImageIcon down_arrow = new ImageIcon("resources/images/arrow-down.png");
    	JButton down_action = new JButton(down_arrow);
    	
    	JButton back_to_menu = new JButton("Back");
    	
        back_to_menu.addActionListener((ActionEvent event) -> {
        	makeGameScreen();
        	setContentPane(this.menu);
        	revalidate();
        	pack();
        });

    	controls.add(down_action, BorderLayout.NORTH);
    	controls.add(back_to_menu, BorderLayout.SOUTH);
    	
    	//game.setBackground(Color.BLUE);
    	model.view.addKeyListener(new ka());
    	gamescreen.add(controls, BorderLayout.EAST);
    	gamescreen.add(model.view, BorderLayout.CENTER);
    	//getContentPane().add(model.view.game_panel);
    	setVisible(true);
    	
    }

    
    private JPanel initMenu() {
    	
    	JPanel menuPanel = new JPanel();
        
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));	
        menuPanel.setBackground(new Color(0,0,0,0));
        
        ImageIcon infoIcon = new ImageIcon("resources/images/questionMark2.png");
        ImageIcon newgameIcon = new ImageIcon("resources/images/tick.png");
        ImageIcon quitIcon = new ImageIcon("resources/images/Closepre.png");

        JButton quitBtn = new JButton("Quit", quitIcon);
        JButton infoBtn = new JButton("Info", infoIcon);
        JButton newgameBtn = new JButton("Play", newgameIcon);
        
        newgameBtn.addActionListener((ActionEvent event) -> {
        	makeGameScreen();
        	setContentPane(gamescreen);
        	model.view.requestFocus();
        	revalidate();
        	pack();
        });
        
        infoBtn.addActionListener((ActionEvent event) -> {
        	makeInfoScreen();
        	setContentPane(info);
        	revalidate();
        	pack();
        });
        
        quitBtn.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        menuPanel.setPreferredSize(new Dimension(150, 150));
        
        //group layout actually has a linkSize method that could save me the bother here
        
        quitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        quitBtn.setPreferredSize(new Dimension(25, 25));
        infoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        infoBtn.setPreferredSize(new Dimension(25, 25));
        newgameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        newgameBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        newgameBtn.setPreferredSize(new Dimension(25, 25));
 
        menuPanel.add(Box.createRigidArea(new Dimension(50, 50)));
        menuPanel.add(newgameBtn);
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
        setVisible(true);
        
    }
    
    private void level_done() {
    	
    	setBackground(Color.GREEN);
    	this.level_won.setOpaque(true);
    	gamescreen.add(this.level_won, BorderLayout.CENTER);
    	
    }
    
    private class MyPanel extends JPanel{
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    
    private void reset_game_view() {
    	
    	gamescreen.add(model.view, BorderLayout.CENTER);
    	//model.update_view(model.get_model());
    	//pack();
    	//setVisible(true);
    	
    }

	class ka extends KeyAdapter {

		@Override
        public void keyPressed(KeyEvent e) {

            
            int key = e.getKeyCode();
            

            if (key == KeyEvent.VK_LEFT) {
 
            	System.out.println("LEFT");
            	model.update_player_position(1);

            } else if (key == KeyEvent.VK_RIGHT) {
            	
            	System.out.println("RIGHT");
            	model.update_player_position(3);
            	

            } else if (key == KeyEvent.VK_UP) {

            	System.out.println("up");
            	model.update_player_position(0);

            } else if (key == KeyEvent.VK_DOWN) {

            	model.update_player_position(2);

            } else if (key == KeyEvent.VK_R) {
            	
            	System.out.println("RELOAD");
                model.reload_level(); 
                reset_game_view();
                //return;
                
            } else if (key == KeyEvent.VK_N) {
            	
            	System.out.println("NEXT LEVEL");
                model.next_level();  
                reset_game_view();
               // return;
                
            } else if (key == KeyEvent.VK_P) {
            	
            	System.out.println("PREV LEVEL");
                model.prev_level();  
                reset_game_view();
                //return;
                
            }
            
            if (model.game_won()) { 
         	   //TODO
         		model.level_complete();
         		model.next_level();  
         		reset_game_view();
             }
            
            pack();
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
