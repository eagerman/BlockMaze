import java.awt.Point;
import java.util.ArrayList;

public class Level {

	private ArrayList<Point> goal_locations;
	private char[][] level_model;
	private int max_moves;
	private double time_limit;
	private int num_goals;
	
	public Level(char[][] m, int goals, ArrayList<Point> goal_pos) {
		
		this.level_model = m;
		this.num_goals = goals;
		this.goal_locations = goal_pos;
		
	}
	
	public char[][] get_level() {
		
		return this.level_model;
		
	}

	
}
