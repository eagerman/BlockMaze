import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameModel {

	private LevelManager levels;
	private Level current;
	private char[][] model;
	private Point player_location;
	private ArrayList<Point> goal_locations;
	private int goals;
	protected GameView view;
	private int rows;
	private int cols; 
	
	public GameModel() throws IOException {

		this.levels = new LevelManager();
		//this.player_location = new Point();
		//this.goal_locations = new ArrayList<Point>();
		//this.controls = new GameController(this); //////////////////

	}
	
	public void set_level_done() {
		
		this.levels.set_level_as_won();
		
	}
	
	public void load_first_level() {
		
		this.current = this.levels.load_first_level();
		
		if(this.current == null) return;
		
		this.goal_locations = this.current.get_goal_locations();
		char[][] game_model = this.current.get_level();
		this.rows = this.current.get_rows();
		this.cols = this.current.get_cols();
		this.model = new char[this.rows][this.cols];
		copy_model(game_model);
		this.goals = this.current.get_num_goals();
		this.player_location = new Point(this.current.get_player_pos().x,this.current.get_player_pos().y);
		this.view = new GameView(this.model,this.rows,this.cols,this); //currently creates new window as GameView is JFrame
		//init_view(this.model);
		
	}
	
	public void get_next_level() {
		
		this.current = this.levels.get_next_level();
		
		if(this.current == null) return;
		
		this.goal_locations = this.current.get_goal_locations();
		char[][] game_model = this.current.get_level();
		this.rows = this.current.get_rows();
		this.cols = this.current.get_cols();
		this.model = new char[this.rows][this.cols];
		copy_model(game_model);
		this.goals = this.current.get_num_goals();
		this.player_location = new Point(this.current.get_player_pos().x,this.current.get_player_pos().y);
		this.view = new GameView(this.model,this.rows,this.cols,this); //currently creates new window as GameView is JFrame
		//init_view(this.model);
		
	}
	
	public void reload_level() {
		
		this.current = this.levels.reload_current_level();
		this.goal_locations = this.current.get_goal_locations();
		char[][] game_model = this.current.get_level();
		this.rows = this.current.get_rows();
		this.cols = this.current.get_cols();
		this.model = new char[this.rows][this.cols];
		copy_model(game_model);
		this.goals = this.current.get_num_goals();
		this.player_location = new Point(this.current.get_player_pos().x,this.current.get_player_pos().y);
		this.view = new GameView(this.model,this.rows,this.cols,this); //currently creates new window as GameView is JFrame
		//init_view(this.model);
		
	}
	
	public void prev_level() {
		
		this.current = this.levels.get_prev_level();
		this.goal_locations = this.current.get_goal_locations();
		char[][] game_model = this.current.get_level();
		this.rows = this.current.get_rows();
		this.cols = this.current.get_cols();
		this.model = new char[this.rows][this.cols];
		copy_model(game_model);
		this.goals = this.current.get_num_goals();
		this.player_location = new Point(this.current.get_player_pos().x,this.current.get_player_pos().y);
		this.view = new GameView(this.model,this.rows,this.cols,this); //currently creates new window as GameView is JFrame
		//init_view(this.model);
		
	}
	

	private void copy_model(char[][] view) {
		
		for(int i=0; i < this.rows; i++) {
			
			for(int j=0; j < this.cols; j++) {
				
				this.model[i][j] = view[i][j];
				
			}
			
		}
		
	}

	public void init_view(char[][] model) {
		
		this.view.initTiles(model);
		
	}
	
	public char get_tile(int row, int col) {
		
		return this.model[row][col];
		
	}
	
	public void open_file(String filename)  { 
		
		Scanner sc = null;
		Scanner sc2 = null;
		
		try {
			
			sc = new Scanner(new FileReader(filename)); 
			get_level_properties(sc);
;
			sc2 = new Scanner(new FileReader(filename));
			read_file(sc2); 
			
		}catch (FileNotFoundException e) {
			
			System.out.println("FILE NOT FOUND"); 
			
		}finally{
			
			if(sc != null) sc.close();
			if(sc2 != null) sc2.close();
			
		}
		
	}
	
	public int get_rows() {
		
		return this.rows;
		
	}
	
	public int get_cols() {
		
		return this.cols;
		
	}
	
	private void get_level_properties(Scanner sc) { // dimensions of the level
		
		int rows = 0;
		String line;
		
		while(sc.hasNext()) {
			
			line = sc.nextLine();
			
			if(rows==0) this.cols = line.toCharArray().length;
			
			rows++;
			
		}
		
		this.rows = rows;

	}

	private void read_file(Scanner sc) {
		
		
		int line_count = 0;
		String line;
		char[] line_chars;
		char[][] level = new char[this.rows][this.cols]; 
		
		while(sc.hasNext()) {
			
			line = sc.nextLine();
			line_chars = line.toCharArray();

			for(int i=0; i<this.cols; i++) level[line_count][i] = line_chars[i];
			
			line_count++;
			
		}
		
		this.model = new char[this.rows][this.cols]; 
		populate_model(level);
		
	}
	
	public void populate_model(char[][] level) {
		
		for(int i=0; i<this.rows; i++) {
			
			for(int j=0; j<this.cols; j++) {
				
				if(level[i][j] == '^') 
					this.player_location.move(i, j);
				
				if(level[i][j] == '!') {
					 
					this.goal_locations.add(new Point(i,j)); 
					this.goals++;
					
				}
					
				this.model[i][j] = level[i][j];
				
			}
			
		}
		
		//set_goal_number();
		this.view = new GameView(this.model,this.rows,this.cols,this);
		init_view(this.model);
		
	}
	
	public boolean is_goal_square(int row, int col) {
		
		return this.goal_locations.contains(new Point(row,col));
		
	}
	
	public void set_goal_number() {
		
		int num_goals = 0;
		
		for(int i=0; i<this.rows; i++) {
			
			for(int j=0; j<this.cols; j++) {
				
				if(model[i][j] == '!') num_goals++;
				
			}
			
		}
		
		this.goals = num_goals;
		
	}
	
	/*
	 * Here x is rows, y is columns, its a bit mixed up because of the way arrays are laid out but 
	 * doesnt really matter too much so long as it's consistent
	 */
	public void update_player_position(int value) { //0=up, 1=left, 2=down, 3=right
		
		switch(value) {
		
			case 0:
				
				moveUp();
				break;
				
			case 1: 
				
				moveLeft();
				break;
				
			case 2:
				
				moveDown();
				break;
				
			case 3:
				
				moveRight();
				break;
		
		}
		
		update_view(this.model);
		
	}
	
	private int getRow() {
		
		return player_location.x;
				
	}
	
	private int getCol() {
		
		return player_location.y;
		
	}

private void moveUp() {
		
		if(isObstacle(this.model[getRow()-1][getCol()])) return;
		
		if(isBox(this.model[getRow()-1][getCol()])) {
			
			updateBoxPosition(0);
			return;			
		}else if(isBoxOnGoal(this.model[getRow()-1][getCol()])) {
			
			moveBoxUpOffGoal();
			return;
			
		}else if(isGoal(this.model[getRow()-1][getCol()]) && !isPlayerOnGoal()) {
			
			playerUpOntoGoal();
			return;
			
		}else if(isPlayerOnGoal()) {
			
			playerUpOffGoal();
			return;
			
		}
		
		
		this.model[getRow()-1][getCol()] = '^';
		this.model[getRow()][getCol()] = ' ';
		this.player_location.move(getRow()-1, getCol());
		
	}
	
	
	private void playerUpOntoGoal() { 
		
		this.model[getRow()-1][getCol()] = '&';
		this.model[getRow()][getCol()] = ' ';
		this.player_location.move(getRow()-1, getCol());
		
	}
	
	private void playerUpOffGoal() { 
		
		this.model[getRow()-1][getCol()] = '^';
		this.model[getRow()][getCol()] = '!';
		this.player_location.move(getRow()-1, getCol());
		
	}
	
	private void moveDown() {
		
		if(isObstacle(this.model[getRow()+1][getCol()])) return;
		
		if(isBox(this.model[getRow()+1][getCol()])) {
			
			updateBoxPosition(2);
			return;
			
		}else if(isBoxOnGoal(this.model[getRow()+1][getCol()])) {
			
			moveBoxDownOffGoal(); //TODO
			return;
			
		}else if(isGoal(this.model[getRow()+1][getCol()]) && !isPlayerOnGoal()) {
			
			playerDownOntoGoal();
			return;
			
		}else if(isPlayerOnGoal()){
			
			playerDownOffGoal();
			return;
			
		}

		this.model[getRow()+1][getCol()] = '^';
		this.model[getRow()][getCol()] = ' ';
		this.player_location.move(getRow()+1,getCol());
		
	}
	
	private void playerDownOntoGoal() { 
		
		this.model[getRow()+1][getCol()] = '&';
		this.model[getRow()][getCol()] = ' ';
		this.player_location.move(getRow()+1, getCol());
		
	}
	
	private void playerDownOffGoal() { 
		
		System.out.println("playerDownOffGoal");
		if (this.model[getRow()+1][getCol()] == '!' ){
			this.model[getRow()+1][getCol()] = '&';
		} else {
			this.model[getRow()+1][getCol()] = '^';
		}
		this.model[getRow()][getCol()] = '!';
		this.player_location.move(getRow()+1, getCol());	
	}
	
	private void moveLeft() {
		
		if(isObstacle(this.model[getRow()][getCol()-1])) return;
		
		if(isBox(this.model[getRow()][getCol()-1])) {
			
			updateBoxPosition(1);
			return;
			
		}else if(isBoxOnGoal(this.model[getRow()][getCol()-1])) {
			
			moveBoxLeftOffGoal(); 
			return;
			
		}else if(isGoal(this.model[getRow()][getCol()-1]) && !isPlayerOnGoal()) {

			playerLeftOntoGoal();
			return;
			
		}else if(isPlayerOnGoal()) {
			
			playerLeftOffGoal();
			return;
			
		}
		
		this.model[getRow()][getCol()-1] = '^';
		this.model[getRow()][getCol()] = ' ';
		this.player_location.move(getRow(),getCol()-1);
		
	}
	
	private void playerLeftOntoGoal() {
		
		System.out.println("LEFT ONTO GOAL");
		
		this.model[getRow()][getCol()-1] = '&';
		this.model[getRow()][getCol()] = ' ';
		this.player_location.move(getRow(), getCol()-1);
	}
	
	private void playerLeftOffGoal() { 
		
		System.out.println("LEFT OFF GOAL");
		
		if ( this.model[getRow()][getCol()-1] == '!'){
			this.model[getRow()][getCol()-1] = '&';
		} else {
			this.model[getRow()][getCol()-1] = '^';
		}
			this.model[getRow()][getCol()] = '!';
			this.player_location.move(getRow(), getCol()-1);
	}
	
	private void moveRight() {
		
		if(isObstacle(this.model[getRow()][getCol()+1])) return;
		
		if(isBox(this.model[getRow()][getCol()+1])) {
			
			updateBoxPosition(3);
			return;
			
		}else if(isBoxOnGoal(this.model[getRow()][getCol()+1])) {
			
			moveBoxRightOffGoal(); //TODO
			return;
			
		}else if(isGoal(this.model[getRow()][getCol()+1]) && !isPlayerOnGoal()) {
			
			playerRightOntoGoal();
			return;
			
		}else if(isPlayerOnGoal()) {
			
			playerRightOffGoal();
			return;
			
		}
		
		this.model[getRow()][getCol()+1] = '^';
		this.model[getRow()][getCol()] = ' ';
		this.player_location.move(getRow(),getCol()+1);
		
	}
	
	private void playerRightOntoGoal() { 
		System.out.println("player Right ONTO Goal");
		this.model[getRow()][getCol()+1] = '&';
		this.model[getRow()][getCol()] = ' ';
		this.player_location.move(getRow(), getCol()+1);
		
	}
	
	private void playerRightOffGoal() { 
		System.out.println("player Right OFF Goal");
		this.model[getRow()][getCol()+1] = '^';
		this.model[getRow()][getCol()] = '!';
		this.player_location.move(getRow(), getCol()+1);
		
	}
	
	private void update_view(char[][] game_model) {
		
		this.view.initTiles(game_model);
		this.view.initUI();
		
	}
	
	private boolean isGoal(char ch) {
		
		return ch == '!';
		
	}
	
	private boolean isBoxOnGoal(char ch) {
		
		return ch == '@';
		
	}
	
	private boolean isPlayerOnGoal() {
		
		return this.goal_locations.contains(new Point(getRow(),getCol()));
		
	}
	
	private void keepGoals(){
		if (isPlayerOnGoal()){
			this.model[getRow()][getCol()] = '!';				
		} else {
			this.model[getRow()][getCol()] = ' ';
		}
	}
	
	private void updateBoxPosition(int value) { //0=up, 1=left, 2=down, 3=right

		switch(value) {
		
			case 0:

				if(isBox(this.model[getRow()-2][getCol()])) return; // cant push two boxes
				moveBoxUp();
				break;
			
			case 1: 
				
				if(this.model[getRow()][getCol()-2] == '#') return;
				moveBoxLeft();
				break;
			
			case 2:

				if(this.model[getRow()+2][getCol()] == '#') return;
				moveBoxDown();
				break;
			
			case 3:
				
				if(this.model[getRow()][getCol()+2] == '#') return;
				moveBoxRight();
				break;
	
		}
		
	}
	
	private void moveBoxUp() {
		
		if(isObstacle(this.model[getRow()-2][getCol()])) return;
		
		if(is_goal_square(getRow()-2,getCol())) { 
			
			moveBoxUpToGoal(); 
			return;
			
		}else if(this.model[getRow()][getCol()] == '&') {
			
			moveBoxUpWhenOnGoal();
			return;
			
		}

		this.model[getRow()-2][getCol()] = '#';
		this.model[getRow()-1][getCol()] = '^';
		this.model[getRow()][getCol()] = ' ';
		
		this.player_location.move(getRow()-1,getCol());
		
	}
	
	private void moveBoxUpToGoal() {
		
		if (this.model[getRow()-2][getCol()] == '@') return;
		char tmp = (this.model[getRow()][getCol()] == '&') ? '!' : ' ' ;

		this.model[getRow()-2][getCol()] = '@';
		this.model[getRow()-1][getCol()] = '^';
		this.model[getRow()][getCol()] = tmp; 
		
		this.player_location.move(getRow()-1,getCol());
		
		this.goals--;
		
	}

	private void moveBoxUpOffGoal() {
		
		if(isObstacle(this.model[getRow()-2][getCol()])) return;
		if (this.model[getRow()-2][getCol()] == '@') return;
		
		if (this.model[getRow()-2][getCol()] == '!'){
			this.model[getRow()-2][getCol()] = '@';
		} else if (this.model[getRow()-2][getCol()] == ' ') {
			this.model[getRow()-2][getCol()] = '#';
		} else if (this.model[getRow()+2][getCol()] == '#') {
			return;
		}
		this.model[getRow()-1][getCol()] = '&';
		keepGoals();
		
		this.player_location.move(getRow()-1,getCol());
		
		this.goals++;		
	}
	
	public void moveBoxUpWhenOnGoal() {
		
		if(isObstacle(this.model[getRow()-2][getCol()])) return;
		
		this.model[getRow()-2][getCol()] = '#';
		this.model[getRow()-1][getCol()] = '^';
		this.model[getRow()][getCol()] = '!';
		
		this.player_location.move(getRow()-1,getCol());
		
	}
	
	private void moveBoxDown() {
		
		if(isObstacle(this.model[getRow()+2][getCol()])) return;
		
		if(is_goal_square(getRow()+2,getCol())) { 
			
			moveBoxDownToGoal(); 
			return;
			
		}else if(this.model[getRow()][getCol()] == '&') {
			
			moveBoxDownWhenOnGoal();
			return;
			
		}

		
		this.model[getRow()+2][getCol()] = '#';
		this.model[getRow()+1][getCol()] = '^';
		this.model[getRow()][getCol()] = ' ';
		
		this.player_location.move(getRow()+1,getCol());
		
	}

	private void moveBoxDownToGoal() {
		
		if (this.model[getRow()+2][getCol()] == '@') return;
		char tmp = (this.model[getRow()][getCol()] == '&') ? '!' : ' ' ;

		this.model[getRow()+2][getCol()] = '@';
		this.model[getRow()+1][getCol()] = '^';
		this.model[getRow()][getCol()] = tmp; 
		
		this.player_location.move(getRow()+1,getCol());
		
		this.goals--;
		
	}

	private void moveBoxDownOffGoal() {
		
		if(isObstacle(this.model[getRow()+2][getCol()])) return;
		if (this.model[getRow()+2][getCol()] == '@') return;
		
		if (this.model[getRow()+2][getCol()] == '!'){
			this.model[getRow()+2][getCol()] = '@';

		} else if (this.model[getRow()+2][getCol()] == ' ') {
			this.model[getRow()+2][getCol()] = '#';
		} else if (this.model[getRow()+2][getCol()] == '#') {
			return;
		}
		this.model[getRow()+1][getCol()] = '&';
		keepGoals();
		
		this.player_location.move(getRow()+1,getCol());
		
		this.goals++;
	}
	
	public void moveBoxDownWhenOnGoal() {
		
		if(isObstacle(this.model[getRow()+2][getCol()])) return;
		
		this.model[getRow()+2][getCol()] = '#';
		this.model[getRow()+1][getCol()] = '^';
		this.model[getRow()][getCol()] = '!';
		
		this.player_location.move(getRow()+1,getCol());
		
	}
	
	private void moveBoxLeft() {

		if(isObstacle(this.model[getRow()][getCol()-2])) return;
		
		if(is_goal_square(getRow(),getCol()-2)) { 
			
			moveBoxLeftToGoal(); 
			return;
			
		}else if(this.model[getRow()][getCol()] == '&') {
			
			moveBoxLeftWhenOnGoal();
			return;
			
		}
		
		this.model[getRow()][getCol()-2] = '#';
		this.model[getRow()][getCol()-1] = '^';
		this.model[getRow()][getCol()] = ' ';
		
		this.player_location.move(getRow(),getCol()-1);
		
	}
	
	
	private void moveBoxLeftToGoal() {
		
		if (this.model[getRow()][getCol()-2] == '@') return;
		char tmp = (this.model[getRow()][getCol()] == '&') ? '!' : ' ' ;

		this.model[getRow()][getCol()-2] = '@';
		this.model[getRow()][getCol()-1] = '^';
		this.model[getRow()][getCol()] = tmp; 
		
		this.player_location.move(getRow(),getCol()-1);
		
		this.goals--;
		
	}

	private void moveBoxLeftOffGoal() {
		
		System.out.println("box left OFF Goal");
		if(isObstacle(this.model[getRow()][getCol()-2])) return;
		if (this.model[getRow()][getCol()-2] == '@') return;
		
		if ( this.model[getRow()][getCol()-2] == '!' ){	//into another goal
			this.model[getRow()][getCol()-2] = '@';
		} else if(this.model[getRow()][getCol()-2] == ' ') { //into empty space
	    	this.model[getRow()][getCol()-2] = '#';
		} else if(this.model[getRow()][getCol()-2] == '#') {
			return;
		}
    	this.model[getRow()][getCol()-1] = '&';
    	keepGoals();
		
		this.player_location.move(getRow(),getCol()-1);
		
		this.goals++;
		
	}
	
	public void moveBoxLeftWhenOnGoal() {
		
		if(isObstacle(this.model[getRow()][getCol()-2])) return;
		if (this.model[getRow()][getCol()-2] == '@') return;

		if ( this.model[getRow()][getCol()-2] == '!' ){
			this.model[getRow()][getCol()-2] = '@';
			this.model[getRow()][getCol()-1] = '^';
			this.model[getRow()][getCol()] = '!';
		} else if ( this.model[getRow()][getCol()-2] == ' ' ){
			this.model[getRow()][getCol()-2] = '#';
			this.model[getRow()][getCol()-1] = '^';
			this.model[getRow()][getCol()] = '!';
		} else {
			
		}
		
		this.player_location.move(getRow(),getCol()-1);
		
	}
	
	private void moveBoxRight() {
		
		if(isObstacle(this.model[getRow()][getCol()+2])) return;
		
		if(is_goal_square(getRow(),getCol()+2)) { 
			
			moveBoxRightToGoal(); 
			return;
			
		}else if(this.model[getRow()][getCol()] == '&') {

			moveBoxRightWhenOnGoal();
			return;
			
		}
		
		this.model[getRow()][getCol()+2] = '#';
		this.model[getRow()][getCol()+1] = '^';
		this.model[getRow()][getCol()] = ' ';
		
		this.player_location.move(getRow(),getCol()+1);
		
	}
	
	private void moveBoxRightToGoal() {
		
		if (this.model[getRow()][getCol()+2] == '@') return;
		char tmp = (this.model[getRow()][getCol()] == '&') ? '!' : ' ' ;

		this.model[getRow()][getCol()+2] = '@';
		this.model[getRow()][getCol()+1] = '^';
		this.model[getRow()][getCol()] = tmp; 
		
		this.player_location.move(getRow(),getCol()+1);
		
		this.goals--;
		
	}

	private void moveBoxRightOffGoal() {
		
		if(isObstacle(this.model[getRow()][getCol()+2])) return;
		if (this.model[getRow()][getCol()+2] == '@') return;
		
		if (this.model[getRow()][getCol()+2] == '!'){
			this.model[getRow()][getCol()+2] = '@';
		} else if (this.model[getRow()][getCol()+2] == '#' ){
			this.model[getRow()][getCol()+2] = '#';
		} 
		
		this.model[getRow()][getCol()+1] = '&';
		keepGoals();
		
		this.player_location.move(getRow(),getCol()+1);
		
		this.goals++;
	}
	
	public void moveBoxRightWhenOnGoal() {
		
		if(isObstacle(this.model[getRow()][getCol()+2])) return;
		
		this.model[getRow()][getCol()+2] = '#';
		this.model[getRow()][getCol()+1] = '^';
		this.model[getRow()][getCol()] = '!';
		
		this.player_location.move(getRow(),getCol()+1);
		
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
	
	public int print_goals() {
		
		return this.goals;
		
	}
	/*
	public void level_finished() {
		
		this.view.level_done();
		
	}*/
	/*
	public static void main(String[] args) throws IOException {
		
		GameModel m = new GameModel(); 
		//m.open_file("test2.txt");
		m.init_game_model();
		
	}
	*/

}
