import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class SetTimer implements Runnable {

	Thread time;
	JLabel lbTime;
	int mcnt=0,scnt=0;
	String str = "   	   ";
	
	public SetTimer(JLabel lbTime){
		this.lbTime = lbTime;
		this.time = new Thread(this);
		reset();
	}
	
	public void reset(){
		this.mcnt=01;	this.scnt=01;
		this.str = "  	    01:00";
		this.time.start();
		display();
	}
	
	public void display(){
		this.lbTime.setText(str);
	}

	public void setTimeCounter() {
		this.str = "   	   ";
		if(mcnt<10){str += "0"+mcnt;}else{str += ":"+mcnt;}
		if(scnt<10){str += ":0"+scnt;}else{str += ":"+scnt;}
	}

	@Override
	public void run() {
		try{
			while(true){
				scnt--;
				if(scnt==0&&mcnt==0){reset();runOutTime();}
				if(scnt==0){scnt=59; mcnt--;}
				setTimeCounter();
				display();
				Thread.sleep(1000);
			}
		}catch(Exception e){}
	}
	
	@SuppressWarnings("deprecation")
	private void runOutTime(){
		JOptionPane.showMessageDialog(null, "You have to RETRY or EXIT this level.","Out of Time", 
				JOptionPane.YES_NO_OPTION);
		reset();
		this.time.start();
		// Stop the game!!!
	}
}
