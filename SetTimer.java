package game;

import javax.swing.JLabel;

public class SetTimer implements Runnable {

	Thread time;
	JLabel lbTime;
	int hcnt=0,mcnt=0,scnt=0;
	String str = "";
	
	public SetTimer(JLabel lbTime){
		this.lbTime = lbTime;
		time = new Thread(this);
		reset();
		time.start();
	}
	
	public void reset(){
		hcnt=0;	mcnt=0;	scnt=0;
		str = "00:00:00";
		display();
	}
	
	public void display(){
		lbTime.setText(str);
	}

	public void setTimeCounter() {
		str = "";
		if(hcnt<10){str = "0"+hcnt;}else{str= ""+hcnt;}
		if(mcnt<10){str += ":0"+mcnt;}else{str += ":"+mcnt;}
		if(scnt<10){str += ":0"+scnt;}else{str += ":"+scnt;}
	}

	@Override
	public void run() {
		try{
			while(true){
				scnt++;
				if(scnt>59){scnt=0; mcnt++;}
				if(mcnt>59){mcnt=0; hcnt++;}
				if(hcnt>59){reset();}
				setTimeCounter();
				display();
				Thread.sleep(1000);
			}
		}catch(Exception e){}
	}
}
