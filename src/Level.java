
import java.awt.Point;
import java.util.ArrayList;

public class Level {

	private ArrayList<Point> goal_locations;
	private Point player_at;
	private char[][] level_model;
	private int num_goals;
	private int rows;
	private int cols;
	
	public Level(char[][] m, int goals, ArrayList<Point> goal_pos, int rows, int cols, Point p) {
		
		this.level_model = m;
		this.num_goals = goals;
		this.goal_locations = goal_pos;
		this.rows = rows;
		this.cols = cols;
		this.player_at = p;
		
	}
	
	public Point get_player_pos() {
		
		return this.player_at;
		
	}
	
	public int get_rows() {
		
		return this.rows;
		
	}
	
	public int get_cols() {
		
		return this.cols;
		
	}
	
	public int get_num_goals() {
		
		return this.num_goals;
		
	}
	
	public ArrayList<Point> get_goal_locations() {
		
		return this.goal_locations;
		
	}
	
	public char[][] get_level() {
		
		return this.level_model;
		
	}

	
}
