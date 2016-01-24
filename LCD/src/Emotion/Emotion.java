package Emotion;

import citec.roboter.Sounds;
import lejos.robotics.RegulatedMotor;

public abstract class Emotion extends Thread {
	
	protected static RegulatedMotor motorLeft;// = new EV3LargeRegulatedMotor(MotorPort.D);
	protected static RegulatedMotor motorRight;// = new EV3LargeRegulatedMotor(MotorPort.A);
	protected static RegulatedMotor motorHead;// = new EV3LargeRegulatedMotor(MotorPort.C);
	protected static Sounds sound = new Sounds();
	protected boolean terminated = false;
	
	/**
	 * @param left
	 * @param right
	 * @param head
	 */
	public Emotion(RegulatedMotor left, RegulatedMotor right,RegulatedMotor head){
		motorLeft = left;
		motorRight = right;
		motorHead = head;
		terminated = false;
	}
	
	
	public void terminate(){
		System.out.println(motorHead.getTachoCount());
		terminated = true;
		sound.terminate();
		motorLeft.stop();
		motorRight.stop();
		motorHead.stop();
		System.out.println(motorHead.getTachoCount());
		motorHead.rotateTo(0);
		System.out.println(motorHead.getTachoCount());
		
	}
	
	public void run(){
		showEmotion();
	}
	
	public abstract boolean showEmotion();

}
