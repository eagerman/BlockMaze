package button;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SplashScreen extends JFrame {

	// a
	private Image bgImage;
	private JPanel gamescreen;
	private JPanel info;
	private JPanel menu;
	//private JLabel info-test;

    public SplashScreen() {

    	bgImage = Toolkit.getDefaultToolkit().createImage("SimpleCrate.png");
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
    	gamescreen.setPreferredSize(new Dimension(600,500));
    	
    	JPanel controls = new JPanel();
    	JPanel game = new JPanel();
    	
    	ImageIcon down_arrow = new ImageIcon("arrow-down.png");
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
        JButton newgameBtn = new JButton("Play", newgameIcon);
        
        newgameBtn.addActionListener((ActionEvent event) -> {
        	makeGameScreen();
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
        
    }
    
    private class MyPanel extends JPanel{
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
    

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            SplashScreen ex = new SplashScreen();
            ex.setVisible(true);
        });
    }
    
}
