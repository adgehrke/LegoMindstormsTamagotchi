import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


public class Emotion extends Thread {
	
	
	private static RegulatedMotor motorLeft;// = new EV3LargeRegulatedMotor(MotorPort.D);
	private static RegulatedMotor motorRight;// = new EV3LargeRegulatedMotor(MotorPort.A);
	private static RegulatedMotor motorHead;// = new EV3LargeRegulatedMotor(MotorPort.C);
	
	private boolean terminated = false;
	
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
	
	/*
	 * 
	 */
	public boolean happy(){

		return false;
		
	}
	
	public boolean unhappy(){
		
		return false;
		
	}
	
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
	
	public boolean excited(){
		
		return false;
		
	}
	
	public boolean scared(){
		
		return false;
		
	}
	
	public boolean offended(){
		
		return false;
		
	}
	
	@Override
	public void run(){
		if(bored()){
			return;
		}
	}
	
	
	
	
	

}
