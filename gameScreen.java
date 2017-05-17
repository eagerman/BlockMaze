package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

@SuppressWarnings("serial")
public class gameScreen extends JFrame implements ActionListener {

	JPanel panel;
	JButton tempButton;
	boolean sign;
	URL cb = null;
	File f = new File("C:\\Users\\May\\workspace\\Level\\src\\cello.wav");
	AudioClip aau;
	
	gameScreen(JPanel panel, JButton musicButton){
		this.panel = panel;
		this.tempButton = musicButton;
		tempButton.addActionListener(this);
		soundLoad();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == tempButton){
			if(!isplay()){
				soundLoad();tempButton.setLabel("Open");
			} else {
				stopMusic();tempButton.setLabel("Close");
			}
		}
		panel.requestFocus();
	}
	
	@SuppressWarnings("deprecation")
	private void soundLoad(){
		
		try {
			cb = f.toURL();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		aau = Applet.newAudioClip(cb);
		aau.loop();
		sign = true;
	}
	
	private void stopMusic(){
		aau.stop();
		sign = false;
	}
	
	private boolean isplay(){
		return sign;
	} 

}
