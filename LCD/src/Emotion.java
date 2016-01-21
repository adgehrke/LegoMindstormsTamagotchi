
import java.io.File;

import lejos.hardware.Sound;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


/**
 *
 */
public class Emotion extends Thread {
	
	
	private static RegulatedMotor motorLeft;// = new EV3LargeRegulatedMotor(MotorPort.D);
	private static RegulatedMotor motorRight;// = new EV3LargeRegulatedMotor(MotorPort.A);
	private static RegulatedMotor motorHead;// = new EV3LargeRegulatedMotor(MotorPort.C);
	
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
	
	/*
	 *End every running Emotion and go back to Initial Position 
	 */
	public void terminate(){
		terminated = true;
	}

	/**
	 * @return
	 */
	public boolean happy(){
		terminated = false;
		//motorLeft.setSpeed(300);
		//motorRight.setSpeed(300);
		motorHead.setSpeed(700);
		driveRight();
		while(!terminated){
			Sound.setVolume(100);
			Sound.playSample(new File("dog_bark.wav"));
			

			motorHead.rotate(1000);
			motorHead.rotate(-2000);
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
	
	private void driveLeft(){
		motorRight.setSpeed(700);
		motorLeft.setSpeed(100);
		motorRight.backward();
		motorLeft.forward();
	}
	
	/**
	 * @return
	 */
	public boolean unhappy(){
		
		return false;
		
	}
	
	/**
	 * @return
	 */
	public boolean bored(){
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
	
	/**
	 * @return
	 */
	public boolean excited(){
		
		return false;
		
	}
	
	/**
	 * @return
	 */
	public boolean scared(){
		
		return false;
		
	}
	
	/**
	 * @return
	 */
	public boolean offended(){
		
		return false;
		
	}
	
	@Override
	public void run(){
		if(happy()){
			return;
		}
	}
	
	
	
	
	

}
