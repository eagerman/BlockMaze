package gui;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;

@SuppressWarnings("serial")
public class Sound extends JFrame implements ActionListener {

	JPanel panel;
	JButton tempButton;
	boolean sign;
	URL cb = null, keycb = null;
	File gameF;
	File keyF;
	AudioClip aau, keyaau;
	
	Sound(JPanel panel, JButton musicButton, String musicName){
		this.panel = panel;
		this.tempButton = musicButton;
		tempButton.addActionListener(this);
		gameF = new File(musicName);
		soundLoad();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == tempButton){
			if(!isplay()){
				soundLoad();tempButton.setLabel("Close Music");
			} else {
				stopMusic();tempButton.setLabel("Open Music");
			}
		}
		panel.requestFocus();
	}
	
	@SuppressWarnings("deprecation")
	void soundLoad(){
		try {
			cb = gameF.toURL();
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		aau = Applet.newAudioClip(cb);
		aau.loop();
		sign = true;
	}
	
	void stopMusic(){aau.stop();sign = false;}
	boolean isplay(){return sign;}
	
	@SuppressWarnings("deprecation")
	void keyboardSound(String direction){
		if(direction == "up"){keyF = new File("up.wav");}
		if(direction == "down"){keyF = new File("down.wav");}
		if(direction == "left"){keyF = new File("left.wav");}
		if(direction == "right"){keyF = new File("right.wav");}
		try{
			keycb = keyF.toURL(); 
		} catch (MalformedURLException e){e.printStackTrace();}
		keyaau = Applet.newAudioClip(keycb);
		keyaau.play();
	}
}