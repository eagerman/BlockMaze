package comp2911project;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public final class Main extends JFrame {

    private final int offest = 60;

    public Main() {
        InitUI();
    }

    public void InitUI() {
        Board board = new Board();
        add(board);

        ImageIcon webIcon = new ImageIcon("resources/box.png");
        setIconImage(webIcon.getImage());
       
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(board.getBoardWidth() + offest,
                board.getBoardHeight() + 2*offest);
        setLocationRelativeTo(null);
        setTitle("Block Mover");
    }


    public static void main(String[] args) {
        Main mover = new Main();
        mover.setVisible(true);
    }
}