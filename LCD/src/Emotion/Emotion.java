package Emotion;

import citec.roboter.Sounds;
import lejos.robotics.RegulatedMotor;

public abstract class Emotion {
	
	private static RegulatedMotor motorLeft;// = new EV3LargeRegulatedMotor(MotorPort.D);
	private static RegulatedMotor motorRight;// = new EV3LargeRegulatedMotor(MotorPort.A);
	private static RegulatedMotor motorHead;// = new EV3LargeRegulatedMotor(MotorPort.C);
	private static Sounds sound = new Sounds();
	private boolean terminated = false;
	
	/**
	 * @param left
	 * @param right
	 * @param head
	 */
	public Emotion(RegulatedMotor left, RegulatedMotor right,RegulatedMotor head){
		motorLeft = left;
		motorRight = right;
		motorHead = head;
	}
	
	public void terminate(){
		
	}
	
	public abstract void showEmotion();

}
