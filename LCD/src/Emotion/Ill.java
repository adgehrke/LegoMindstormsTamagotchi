package Emotion;

import lejos.robotics.RegulatedMotor;
/*
 * er will Medizin
 * 
 * Display: Augen halb geschlossen
 * Sound: halb am sterben
 * Motor: Langsame Schlangen-Bewegung
 * Kopf: Kopf nach unten
 */
public class Ill extends Emotion{

	public Ill(RegulatedMotor left, RegulatedMotor right, RegulatedMotor head) {
		super(left, right, head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean showEmotion() {
		return false;
		// TODO Auto-generated method stub
		
	}

}
