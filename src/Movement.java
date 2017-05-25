import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Movement {
	
	JLabel movement;
	int move = 0;
	String movStr = "     Movement: ";

	public Movement(JLabel movement){
		this.movement = movement;
		resetMovement();
	}
	
	void resetMovement(){
		this.move = 0;
		movStr = "     Movement: 0";
		displayMove();
	}
	
	void displayMove(){
		movStr = "     Movement: " + move;
		movement.setText(movStr);
	}
	
	void addMove(){
		this.move++;
	}
}
