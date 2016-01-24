package citec.Threads;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import Emotion.Emotions;

public class Motor extends Thread {

	private Emotions emotion = Emotions.Normal;
	private boolean terminated = false;

	protected static RegulatedMotor motorLeft = new EV3LargeRegulatedMotor(
			MotorPort.D);
	protected static RegulatedMotor motorRight = new EV3LargeRegulatedMotor(
			MotorPort.A);
	protected static RegulatedMotor motorHead = new EV3LargeRegulatedMotor(
			MotorPort.C);

	public void setEmotion(Emotions e) {
		emotion = e;
		terminated=true;
		this.toInitialPosition();

	}

	public void toInitialPosition() {
		System.out.println(motorHead.getTachoCount());
		motorLeft.stop();
		motorRight.stop();
		motorHead.stop();
		System.out.println(motorHead.getTachoCount());
		motorHead.rotateTo(0);
		System.out.println(motorHead.getTachoCount());
	}

	public void run() {
		while (true) {
			switch (emotion) {
			case Bored:
				this.bored();
				break;
			case Dirty:
				this.dirty();
				break;
			case Dying:
				this.dying();
				break;
			case Excited:
				this.happy();
				break;
			case Hungry:
				this.hungry();
				break;
			case Ill:
				this.ill();
				break;
			case Normal:
				this.normal();
				break;
			case Offended:
				this.offended();
				break;
			case Tired:
				this.tired();
				break;
			}
		}
	}

	private void tired() {
		motorHead.rotate(2000);
		motorHead.setSpeed(200);
		
		while(!terminated){
			motorHead.rotate(-300);
			if(terminated){
				return;
			}
			motorHead.rotate(300);
		}
		return;
	}

	private void offended() {
		// TODO Auto-generated method stub

	}

	private void normal() {
		terminated = false;
		//motorLeft.setSpeed(300);
		//motorRight.setSpeed(300);
		motorHead.setSpeed(900);
		driveRight();
		while(!terminated){
			System.out.println(motorHead.getTachoCount());
			

			motorHead.rotate(1000);
			if(terminated){
				return;
			}
			motorHead.rotate(-2000);
			if(terminated){
				return;
			}
			motorHead.rotate(1000);

		}
	}
	private void driveRight(){
		motorRight.setSpeed(400);
		motorLeft.setSpeed(400);
		motorLeft.backward();
		motorRight.forward();
	}

	private void ill() {
		// TODO Auto-generated method stub

	}

	private void hungry() {
		// TODO Auto-generated method stub

	}

	private void happy() {
		// TODO Auto-generated method stub

	}

	private void dying() {
		// TODO Auto-generated method stub

	}

	private void dirty() {
		// TODO Auto-generated method stub

	}

	private void bored() {
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

	}
}