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
	private int time=0;
	public Sounds(){
		Sound.setVolume(90);
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
			time++;
			if (changed == true || time > 20){
				changed = false;
				time = 0;
				switch(action){
					case None:
						break;
					case Sleeping:
						this.sleeping();
						break;
					case Eating:
						this.eating();
						break;
					case Healing:
						this.healing();
						break;
					case Playing:
						this.playing();
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
						case Happy:
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
			Delay.msDelay(100);
		}
	}

	private void playing() {
	}

	private void healing() {
		Sound.playSample(new File("healing.wav"));
	}

	private void eating() {
		Sound.playSample(new File("eating.wav"));
	}

	private void tired() {
		Sound.playSample(new File("tired.wav"));
	}

	private void offended() {
		Sound.playSample(new File("offended.wav"));
	}

	private void normal() {
		Sound.playSample(new File("dog_bark.wav"));
	}
	
	private void sleeping() {
		Sound.playSample(new File("growl.wav"));
	}

	private void ill() {
		Sound.playSample(new File("ill.wav"));
	}

	private void hungry() {
		Sound.playSample(new File("hungry.wav"));
	}

	private void happy() {
		// TODO Auto-generated method stub
	}

	private void dying() {
		Sound.playSample(new File("dying.wav"));
	}

	private void dirty() {
		// TODO Auto-generated method stub
		
	}

	private void bored() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	

}
