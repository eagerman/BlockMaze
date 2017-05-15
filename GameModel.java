import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameModel {

	private char[][] model;
	private Point player_location;
	private int goals;
	
	public GameModel(int x, int y) {

		this.player_location = new Point(0,0);
		
	}
	
	private void open_file(String filename)  { 

		Scanner sc = null;
		
		try {
			
			sc = new Scanner(new FileReader(filename)); 
			read_file(sc);
			
		}catch (FileNotFoundException e) {
			
			System.out.println("FILE NOT FOUND"); 
			
		}finally{
			
			if (sc != null) sc.close();
			
		}
		
	}

	private void read_file(Scanner sc) {
		
		int line_count = 0;
		String line;
		char[] line_chars;
		char[][] level = new char[30][30]; // dimensions should not be hard coded
		
		while(sc.hasNext()) {
			
			line = sc.nextLine();
			line_chars = line.toCharArray();

			for(int i=0; i<line_chars.length; i++) level[line_count][i] = line_chars[i];
			
			line_count++;
			
		}
		
		this.model = new char[30][30]; // dimensions should not be hard coded
		populate_model(level);
		
	}
	
	public void populate_model(char[][] level) {
		
		for(int i=0; i<level.length; i++) {
			
			for(int j=0; j<level.length; j++) {
				
				if(level[i][j] == '^') 
					this.player_location.move(i, j);

				this.model[i][j] = level[i][j];
				
			}
			
		}
		
	}
	
	public void set_goal_number() {
		
		int num_goals = 0;
		
		for(int i=0; i<model.length; i++) {
			
			for(int j=0; j<model.length; j++) {
				
				if(model[i][j] == '!') num_goals++;
				
			}
			
		}
		
		this.goals = num_goals;
		
	}
	
	public void print_model() {
		
		for(int i=0; i<model.length; i++) {
			
			for(int j=0; j<model.length; j++) {
				
				System.out.print(model[i][j]);
				
			}
			
			System.out.print("\n");
			
		}
		
	}
	
	/*
	 * Here x is rows, y is columns, its a bit mixed up because of the way arrays are laid out but 
	 * doesnt really matter too much so long as it's consistent
	 */
	public void update_player_position(int value) { //0=up, 1=left, 2=down, 3=right

		switch(value) {
		
			// add functionality for moving boxes etc
		
			case 0:
				
				if(isObstacle(this.model[player_location.x-1][player_location.y])) return;
				if(isBox(this.model[player_location.x-1][player_location.y])) updateBoxPosition(0);
				
				this.model[player_location.x-1][player_location.y] = '^';
				this.model[player_location.x][player_location.y] = ' ';
				this.player_location.move(player_location.x-1, player_location.y);
				break;
				
			case 1: 
				
				if(isObstacle(this.model[player_location.x][player_location.y-1])) return;
				if(isBox(this.model[player_location.x][player_location.y-1])) updateBoxPosition(1);
				
				this.model[player_location.x][player_location.y-1] = '^';
				this.model[player_location.x][player_location.y] = ' ';
				this.player_location.move(player_location.x,player_location.y-1);
				break;
				
			case 2:
				
				if(isObstacle(this.model[player_location.x+1][player_location.y])) return;
				if(isBox(this.model[player_location.x+1][player_location.y])) updateBoxPosition(2);
				
				this.model[player_location.x+1][player_location.y] = '^';
				this.model[player_location.x][player_location.y] = ' ';
				this.player_location.move(player_location.x+1,player_location.y);
				break;
				
			case 3:
				
				if(isObstacle(this.model[player_location.x][player_location.y+1])) return;
				if(isBox(this.model[player_location.x][player_location.y+1])) updateBoxPosition(3);
				
				this.model[player_location.x][player_location.y+1] = '^';
				this.model[player_location.x][player_location.y] = ' ';
				this.player_location.move(player_location.x,player_location.y+1);
				break;
		
		}
		
		//System.out.println("Player position is "+this.player_location.toString());
		
	}
	
	private void updateBoxPosition(int value) {
		
		switch(value) {
		
			case 0:
				
				if(isObstacle(this.model[player_location.x-2][player_location.y])) return;
				
				if(this.model[player_location.x-2][player_location.y] == '!') this.goals--;
				
				this.model[player_location.x-2][player_location.y] = '#';
				this.model[player_location.x-1][player_location.y] = ' ';
				
				break;
			
			case 1: 
				
				if(isObstacle(this.model[player_location.x][player_location.y-2])) return;
				if(isObstacle(this.model[player_location.x][player_location.y-2])) this.goals--;
				
				this.model[player_location.x][player_location.y-2] = '#';
				this.model[player_location.x][player_location.y-1] = ' ';
				
				break;
			
			case 2:
			
				if(isObstacle(this.model[player_location.x+2][player_location.y])) return;
				if(isObstacle(this.model[player_location.x+2][player_location.y])) this.goals--;
				
				this.model[player_location.x+2][player_location.y] = '#';
				this.model[player_location.x+1][player_location.y] = ' ';
				
				break;
			
			case 3:
			
				if(isObstacle(this.model[player_location.x][player_location.y+2])) return;
				if(isObstacle(this.model[player_location.x][player_location.y+2])) this.goals--;
				
				this.model[player_location.x][player_location.y+2] = '#';
				this.model[player_location.x][player_location.y+1] = ' ';
				
				break;
	
		}
		
	}
	
	public void read_player_input() { // this is called TWICE, but one falls through the switch
		
		System.out.println("Enter a command: ");
		
		int move = -1; // default, not a command
		
		try {
			move = System.in.read();
		} catch (IOException e) {
			return;
		}
		
		if((char)move == '\n') return;
		
		switch((char)move) {
		
			case 'f':
				
				update_player_position(0);
				break;
		
			case 'l':
				
				update_player_position(1);
				break;
				
			case 'd':
				
				update_player_position(2);
				break;
				
			case 'r':
				
				update_player_position(3);
				break;
				
			default:

				return; //nothing
				
		}
		
	}
	
	private boolean isObstacle(char tile) {
		
		return tile == '*'; 
		
	}
	
	private boolean isBox(char tile) {
		
		return tile == '#';
		
	}
	
	public boolean game_won() {
		
		return this.goals == 0;
		
	}
	
	// method to arrange the view based on the model
	
	public static void main(String[] args) {
		
		GameModel m = new GameModel(30,30); // dims for test map
		m.open_file(args[0]);
		m.set_goal_number();
		
		while(! m.game_won()) {
			
			m.print_model();
			m.read_player_input(); 
			
		}
		
		System.out.println("GAME OVER!\nYOU WIN!\n");
		
	}
	
}
