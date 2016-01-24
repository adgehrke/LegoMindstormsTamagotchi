package Emotion;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
/*
 * 
 * Kopf: nach untten
 * Display: Augen schuldbewusst, traurig
 * 
 */
public class Dirty extends Emotion{

	public Dirty(RegulatedMotor left, RegulatedMotor right, RegulatedMotor head) {
		super(left, right, head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean showEmotion() {
		return false;
		
	}

}
