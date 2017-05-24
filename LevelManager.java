package dg;
import java.awt.Point;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.swing.*;

public class LevelManager {
	
	private ArrayList<Level> levels;
	private Level current_level;
	private File levels_folder;
	private int num_levels;
	private int level_counter;
	private int rows;
	private int cols;
	
	public LevelManager() throws IOException {
		
		this.level_counter = 0;
		this.levels = new ArrayList<Level>();
		String level_path = Paths.get("").toAbsolutePath().getParent().toString();
		System.out.println("Level path is "+level_path);
		this.levels_folder = new File(level_path+"/dg/src/levels");
		read_all_levels(this.levels_folder);
		this.num_levels = this.levels.size();
		files_dbug();//////
		
	}
	
	public void files_dbug() {
		
		for(Level l: this.levels) System.out.println("NUm goals is "+l.get_num_goals());
		
	}
	
	public int get_curr_rows() {

		return this.current_level.get_rows();
		
	}

	public int get_curr_cols() {
		
		return this.current_level.get_cols();
		
	}
	
	public Level get_next_level() {
		
		if(this.level_counter == this.num_levels) return null;
		
		this.current_level = this.levels.get(this.level_counter);
		
		Level next = current_level;
		
		this.level_counter++;
		
		return next;
		
	}
	
	public Level reload_current_level() {
		
		return this.current_level;
		
	}
	
	public char[][] get_prev_level_model() {
		
		if(this.level_counter == 0) return null;
		
		char[][] next = this.levels.get(this.level_counter-1).get_level();
		
		this.level_counter--;
		
		return next;
		
	}
	
	public void read_all_levels(File folder) throws IOException {
		
    	System.out.println("Looking at folder "+folder.getCanonicalPath());
		 
	    for (File filename : folder.listFiles()) {

	    	System.out.println("Opening "+filename);
	    	open_file(filename.toPath().toString());
	        
	    }
	    
	}
	
	public void init_levels() throws IOException {

		read_all_levels(levels_folder);
		
	}
	
	public void open_file(String filename)  { 
		
		Scanner sc = null;
		Scanner sc2 = null;
		
		try {
			
			sc = new Scanner(new FileReader(filename)); 
			get_level_properties(sc);

			sc2 = new Scanner(new FileReader(filename));
			read_file(sc2); 
			
		}catch (FileNotFoundException e) {
			
			System.out.println("FILE NOT FOUND"); 
			
		}finally{
			
			if(sc != null) sc.close();
			if(sc2 != null) sc2.close();
			
		}
		
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

		populate_level(level); 
		
	}
	
	public void populate_level(char[][] level) {
		
		ArrayList<Point> goal_locations = new ArrayList<Point>();
		Point player_location = new Point();
		int goals = 0;
		char[][] model = new char[this.rows][this.cols];
		
		for(int i=0; i<this.rows; i++) {
			
			for(int j=0; j<this.cols; j++) {
				
				if(level[i][j] == '^') 
					
					player_location.move(i, j);

				
				if(level[i][j] == '!') {
					 
					goal_locations.add(new Point(i,j)); 
					goals++;
					
				}
					
				model[i][j] = level[i][j];
				
			}
			
		}
		
		store_level(model,goals,goal_locations,this.rows,this.cols,player_location);
		
	}
	
	private void store_level(char[][] model, int goal_count, ArrayList<Point> goals, int rows, int cols, Point p) {
		
		Level new_level = new Level(model,goal_count,goals,rows,cols,p);
		this.levels.add(new_level);
		
	}

}
