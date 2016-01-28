package citec.Threads;
import java.io.File;

import Emotion.Emotions;
import citec.roboter.Actions;

import lejos.hardware.Sound;
import lejos.utility.Delay;


public class Sounds extends Thread{
	
	
	
	
	private Emotions emotion = Emotions.Normal;
	private Actions action = Actions.None;
	private int wait = 3000;
	private boolean changed;
	
	public Sounds(){
		Sound.setVolume(80);
	}
	

	public void setEmotion(Emotions e){
		
		if (action == Actions.None && e != emotion){
			emotion = e;
			changed = true;
		}
	}
	
	public void setAction(Actions a){
		if (a != action){
			action = a;
			changed = true;
		}
	}
	
	public void run(){
		while(true){
			if (changed == true){
				changed = false;
				switch(action){
					case None:
						break;
					case Sleeping:
						this.sleeping();
						break;
					case Eating:
						break;
					case Healing:
						break;
					case Playing:
						break;
					default:
						break;
				}
				if (action == Actions.None){
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
		
		wait = Sound.playSample(new File("dog_bark.wav"))+1000;
		
		
	}
	
	private void sleeping() {
		wait = Sound.playSample(new File("growl.wav"))+1000;

	}

	private void ill() {
	wait = Sound.playSample(new File("dog_bark.wav"))+1000;

	}

	private void hungry() {
		// TODO Auto-generated method stub
		wait = Sound.playSample(new File("dog_bark.wav"))+1000;
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
