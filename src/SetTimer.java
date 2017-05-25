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
		time = new Thread(this);
		reset();
		time.start();
	}
	
	public void reset(){
		mcnt=01;	scnt=01;
		str = "  	    01:00";
		display();
	}
	
	public void display(){
		lbTime.setText(str);
	}

	public void setTimeCounter() {
		str = "   	   ";
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
		time.stop();
		// Stop the game!!!
	}
}
