package Emotion;

import lejos.robotics.RegulatedMotor;
/*
 * Hunger
 * Display: Augen auf
 * Sound: schreien
 * Motor: Langsame Bewegung
 * Kopf: Kopf nach oben
 */
public class Hungry extends Emotion {

	public Hungry(RegulatedMotor left, RegulatedMotor right, RegulatedMotor head) {
		super(left, right, head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean showEmotion() {
		
		return false;
		// TODO Auto-generated method stub
		
	}

}
