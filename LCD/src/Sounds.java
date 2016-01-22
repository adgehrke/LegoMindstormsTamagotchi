import java.io.File;

import lejos.hardware.Sound;


public class Sounds extends Thread{
	
	private String dog_bark = "dog_bark.wav";
	private String growl = "growl.wav";
	
	private int activeSound = 0;
	
	private boolean terminated = false;
	
	public void setActiveSound(int se){
		activeSound = se;
	}
	
	public void terminate(){
		terminated = true;
	}
	
	public int getActiveSound(){
		return activeSound;
	}
	
	private void playSound(){
		Sound.setVolume(100);
		switch(activeSound){
		case 0:
			while(!terminated){
				Sound.playSample(new File("dog_bark.wav"));
			}
			break;
		case 1:
			Sound.playSample(new File("growl.wav"));
			break;
		}
	}
	
	
	
	
	@Override
	public void run(){
		playSound();
	}
	
	
	
	

}
