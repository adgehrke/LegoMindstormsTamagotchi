package Emotion;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
/**
 * @return
 * Display: Augen groß
 * Sound: Wuiiuuuu
 * Kopf: hoch-rutner-schnell
 * Motor: vor+zurück
 * er will spielen
 */
public class Bored extends Emotion{

	public Bored(RegulatedMotor left, RegulatedMotor right, RegulatedMotor head) {
		super(left, right, head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean showEmotion() {
		terminated = false;
		//motorLeft.setSpeed(300);
		//motorRight.setSpeed(300);
		while(!terminated){
			motorLeft.forward();
			motorRight.stop();
			Delay.msDelay(150);
			motorLeft.stop();
			motorRight.backward();
			Delay.msDelay(150);
			motorLeft.backward();
			motorRight.stop();
			Delay.msDelay(150);
			motorLeft.stop();
			motorRight.forward();
			Delay.msDelay(150);
		}
		return true;
		
	}

}
