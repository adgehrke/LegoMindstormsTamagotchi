package Emotion;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
/**
 * @return
 * zurückfahren+böser Blick
 */
public class Offended extends Emotion{

	public Offended(RegulatedMotor left, RegulatedMotor right, RegulatedMotor head) {
		super(left, right, head);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean showEmotion() {
		return false;
		
	}

}
