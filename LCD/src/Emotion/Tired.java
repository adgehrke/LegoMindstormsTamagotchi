package Emotion;

import lejos.robotics.RegulatedMotor;
/*
 * Kopf: Head down 
 * Sound: gähn-sound 
 * Display: Augen schmaler 
 * Bewegung: keine Bewegung
 * Er will schlafen
 */
public class Tired extends Emotion {

	public Tired(RegulatedMotor left, RegulatedMotor right, RegulatedMotor head) {
		super(left, right, head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean showEmotion() {
		motorHead.rotate(2000);
		motorHead.setSpeed(200);
		
		while(!terminated){
			motorHead.rotate(-300);
			if(terminated){
				return false;
			}
			motorHead.rotate(300);
		}
		return true;
	}

}
