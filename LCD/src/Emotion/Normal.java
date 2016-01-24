package Emotion;

import lejos.robotics.RegulatedMotor;
/**
 * 
 * Stillstehen, blinzeln, Kopf auf und ab
 * @return
 */
public class Normal extends Emotion{

	public Normal(RegulatedMotor left, RegulatedMotor right, RegulatedMotor head) {
		super(left, right, head);
	}

	@Override
	public boolean showEmotion() {
		terminated = false;
		//motorLeft.setSpeed(300);
		//motorRight.setSpeed(300);
		motorHead.setSpeed(900);
		driveRight();
		sound.start();
		while(!terminated){
			System.out.println(motorHead.getTachoCount());
			

			motorHead.rotate(1000);
			if(terminated){
				return false;
			}
			motorHead.rotate(-2000);
			if(terminated){
				return false;
			}
			motorHead.rotate(1000);

		}
		return true;
	}
	private void driveRight(){
		motorRight.setSpeed(400);
		motorLeft.setSpeed(400);
		motorLeft.backward();
		motorRight.forward();
	}

}
