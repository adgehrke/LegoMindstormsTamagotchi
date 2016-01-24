package citec.Threads;
import java.io.File;

import Emotion.Emotions;
import lejos.hardware.Sound;


public class Sounds extends Thread{
	
	private String dog_bark = "dog_bark.wav";
	private String growl = "growl.wav";
	
	private int activeSound = 0;
	
	private boolean terminated = false;
	
	private Emotions emotion = Emotions.Normal;
	
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
	
	
	
	public void setEmotion(Emotions e){
		emotion = e;
	}
	
	public void run(){
		while(true){
			switch(emotion){
			case Bored:
				this.bored();
				break;
			case Dirty:
				this.dirty();
				break;
			case Dying:
				this.dying();
				break;
			case Excited:
				this.happy();
				break;
			case Hungry:
				this.hungry();
				break;
			case Ill:
				this.ill();
				break;
			case Normal:
				this.normal();
				break;
			case Offended:
				this.offended();
				break;
			case Tired:
				this.tired();
				break;
			}
		}
	}

	private void tired() {
		// TODO Auto-generated method stub
		
	}

	private void offended() {
		// TODO Auto-generated method stub
		
	}

	private void normal() {
		// TODO Auto-generated method stub
		
	}

	private void ill() {
		// TODO Auto-generated method stub
		
	}

	private void hungry() {
		// TODO Auto-generated method stub
		
	}

	private void happy() {
		// TODO Auto-generated method stub
		
	}

	private void dying() {
		// TODO Auto-generated method stub
		
	}

	private void dirty() {
		// TODO Auto-generated method stub
		
	}

	private void bored() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
