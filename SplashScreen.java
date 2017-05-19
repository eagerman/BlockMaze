package game;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Game extends JFrame {

	private Image bgImage;
	private JPanel gamescreen;
	private JPanel info;
	private JPanel menu;
	private Sound soundMenu;
	//private JLabel info-test;

    public Game() {

    	bgImage = Toolkit.getDefaultToolkit().createImage("SimpleCrate.png");
        initUI();
    }
    
    private void makeInfoScreen() {
    	
    	String how_to = "<html>This is how you play the game etc<br></html>";
    	JLabel info_text = new JLabel(how_to);
    	info_text.setFont(new Font("Serif", Font.PLAIN, 22));
    	info_text.setPreferredSize(new Dimension(350,350));
    	
    	info = new JPanel();
    	info.setPreferredSize(new Dimension(600,500));
    	
    	JButton back_to_menu = new JButton("Back");
    	back_to_menu.setFont(new Font("Serif", Font.PLAIN, 22));
    	
        back_to_menu.addActionListener((ActionEvent event) -> {
        	makeGameScreen();
        	setContentPane(this.menu);
        	revalidate();
        	pack();
        });
        
        info.add(info_text, BorderLayout.CENTER);
        info.add(back_to_menu, BorderLayout.SOUTH);
    	
    }
    
	@SuppressWarnings("deprecation")
	private void makeGameScreen() {
    	
    	gamescreen = new JPanel(new BorderLayout());
    	gamescreen.setPreferredSize(new Dimension(900,800));
    	
    	JPanel controls = new JPanel();
    	JPanel game = new JPanel();
    	
    	ImageIcon down_arrow = new ImageIcon("arrow-down.png");
    	ImageIcon up_arrow = new ImageIcon("up_arrow.png");
    	//ImageIcon left_arrow = new ImageIcon("left.png");
    	//ImageIcon right_arrow = new ImageIcon("right.png");
    	
    	JButton upButton = new JButton(up_arrow);
    	JButton down_action = new JButton(down_arrow);
    	JButton leftButton = new JButton("<—");
    	JButton rightButton = new JButton("—>");
    	JButton btnReset = new JButton("Reset");
    	JButton btnUndo = new JButton("Undo");
    	JButton btnLastLev = new JButton("Last Level");
    	JButton btnNextLev = new JButton("Next Level");
    	JButton btnChooseLev = new JButton("Choose Level");
    	JButton btnmic = new JButton("Close Music");
    	JButton back_to_menu = new JButton("Back");
    	
    	Sound sound = new Sound(controls, btnmic, "Color X.wav");
    	
    	JLabel timer = new JLabel("00:00:00");
    	new SetTimer(timer);
    	
    	if(!soundMenu.isplay()){
    		sound.stopMusic();
    		btnmic.setLabel("Open Music");
    	}
        back_to_menu.addActionListener((ActionEvent event) -> {
        	sound.stopMusic();
        	if(soundMenu.isplay()){
        		soundMenu.aau.loop();
        	}
        	setContentPane(this.menu);
        	revalidate();
        	pack();
        });
        
        controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
    	
        upButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    upButton.setAlignmentY(Component.CENTER_ALIGNMENT);
	    upButton.setPreferredSize(new Dimension(150, 25));
	    leftButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    leftButton.setAlignmentY(Component.CENTER_ALIGNMENT);
	    leftButton.setPreferredSize(new Dimension(150, 25));
	    rightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	    rightButton.setAlignmentY(Component.CENTER_ALIGNMENT);
	    rightButton.setPreferredSize(new Dimension(150, 25));
	    down_action.setAlignmentX(Component.CENTER_ALIGNMENT);
	    down_action.setAlignmentY(Component.CENTER_ALIGNMENT);
	    down_action.setPreferredSize(new Dimension(150, 25));
	    back_to_menu.setAlignmentX(Component.CENTER_ALIGNMENT);
	    back_to_menu.setAlignmentY(Component.CENTER_ALIGNMENT);
	    back_to_menu.setPreferredSize(new Dimension(150, 25));
	    timer.setAlignmentX(Component.CENTER_ALIGNMENT);
	    timer.setAlignmentY(Component.CENTER_ALIGNMENT);
	    timer.setPreferredSize(new Dimension(150, 25));
	    btnReset.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnReset.setAlignmentY(Component.CENTER_ALIGNMENT);
	    btnReset.setPreferredSize(new Dimension(150, 25));
	    btnUndo.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnUndo.setAlignmentY(Component.CENTER_ALIGNMENT);
	    btnUndo.setPreferredSize(new Dimension(150, 25));
	    btnLastLev.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnLastLev.setAlignmentY(Component.CENTER_ALIGNMENT);
	    btnLastLev.setPreferredSize(new Dimension(150, 25));
	    btnNextLev.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnNextLev.setAlignmentY(Component.CENTER_ALIGNMENT);
	    btnNextLev.setPreferredSize(new Dimension(150, 25));
	    btnChooseLev.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnChooseLev.setAlignmentY(Component.CENTER_ALIGNMENT);
	    btnChooseLev.setPreferredSize(new Dimension(150, 25));
	    btnmic.setAlignmentX(Component.CENTER_ALIGNMENT);
	    btnmic.setAlignmentY(Component.CENTER_ALIGNMENT);
	    btnmic.setPreferredSize(new Dimension(150, 25));
	
	    controls.add(Box.createRigidArea(new Dimension(25, 25)));
	    controls.add(upButton);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(leftButton);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(rightButton);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(down_action);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(timer);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(btnReset);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(btnUndo);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(btnLastLev);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));	
		controls.add(btnNextLev);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(btnChooseLev);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(btnmic);
		controls.add(Box.createRigidArea(new Dimension(0, 25)));
		controls.add(back_to_menu);
		
    	game.setBackground(Color.BLUE);
    	
    	gamescreen.add(controls, BorderLayout.EAST);
    	gamescreen.add(game, BorderLayout.CENTER);
  
        
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

        menuPanel.setPreferredSize(new Dimension(600, 600));
        
        //group layout actually has a linkSize method that could save me the bother here
        
        quitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        quitBtn.setPreferredSize(new Dimension(25, 25));
        infoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        infoBtn.setPreferredSize(new Dimension(25, 25));
        musBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        musBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        musBtn.setPreferredSize(new Dimension(25, 25));
        newgameBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        newgameBtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        newgameBtn.setPreferredSize(new Dimension(25, 25));
 
        menuPanel.add(Box.createRigidArea(new Dimension(50, 50)));
        menuPanel.add(newgameBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        menuPanel.add(infoBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        menuPanel.add(musBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        menuPanel.add(quitBtn);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        
        return menuPanel;
    	
    }

    private void initUI() {
    	
        JPanel backgroundPanel = new MyPanel();
        this.menu = backgroundPanel;
        
        JPanel menuPanel = initMenu();
        
        backgroundPanel.setPreferredSize(new Dimension(600,500));
        backgroundPanel.add(menuPanel);

        backgroundPanel.setBorder(new EmptyBorder(new Insets(20, 20, 20, 20)));

        add(backgroundPanel);

        pack();

        setTitle("Warehouse Boss");
        //setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }
    
    private class MyPanel extends JPanel{
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Game ex = new Game();
            ex.setVisible(true);
        });
    }
    
}
