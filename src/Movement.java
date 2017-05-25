import javax.swing.JButton;

public class Movement {
	
	JButton movement;
	int move = 0;
	String movStr = "Movement: ";

	public Movement(JButton movement){
		this.movement = movement;
		resetMovement();
	}
	
	void resetMovement(){
		this.move = 0;
		movStr = "Movement: 0";
		displayMove();
	}
	
	void displayMove(){
		movStr = "Movement: " + move;
		movement.setText(movStr);
	}
	
	void addMove(){
		this.move++;
	}
}