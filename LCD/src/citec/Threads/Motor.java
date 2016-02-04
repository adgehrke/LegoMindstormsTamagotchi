package citec.Threads;

import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import Emotion.Emotions;
import citec.roboter.Actions;
import citec.Threads.TouchSensor;

public class Motor extends Thread {

	private int time = 0;

	private Emotions emotion = Emotions.Normal;
	private Actions action = Actions.None;
	private boolean terminated = false;

	protected static RegulatedMotor left = new EV3LargeRegulatedMotor(
			MotorPort.C);
	protected static RegulatedMotor right = new EV3LargeRegulatedMotor(
			MotorPort.B);
	protected static RegulatedMotor head = new EV3LargeRegulatedMotor(
			MotorPort.A);

	public void headUp(int degree) {
		head.rotate(-degree);

	}

	public void headDown(int degree) {
		head.rotate(degree);
	}

	public void setEmotion(Emotions e) {

		if (action == Actions.None && e != emotion) {
			emotion = e;
			terminate();
		}
	}

	public void setAction(Actions a) {
		if (a != action) {
			action = a;
			terminate();
		}
	}

	private void terminate() {
		terminated = true;
		toInitialPosition();
	}

	public void toInitialPosition() {
		// System.out.println(head.getTachoCount());
		left.stop();
		right.stop();
		head.stop();
		// System.out.println(head.getTachoCount());
		head.rotateTo(0);
		// System.out.println(head.getTachoCount());
	}

	public void run() {
		while (true) {
			time++;
			if (terminated == true || time > 50) {
				terminated = false;
				toInitialPosition();
				time = 0;
				switch (action) {
				case None:
					break;
				case Sleeping:
					this.sleeping();
					break;
				case Eating:
					this.eating();
					break;
				case Healing:
					this.healing();
					break;
				case Playing:
					this.playing();
					break;
				default:
					break;
				}
				if (action == Actions.None) {
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
					case Happy:
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
			Delay.msDelay(100);
		}
	}

	// Bewegung: F�hrt gradeaus, bis schwarze-Randlinie, dreht sich min 90�
	// max 270�
	
	private void playing() {
		head.rotate(1800);
		left.setSpeed(600);
		right.setSpeed(600);
		while(!terminated){
			driveForward();
		}
		// TODO Auto-generated method stub

	}

	// Kopf langsam hoch
	private void healing() {
		head.rotate(-1500);
		// TODO Auto-generated method stub

	}

	// Kopf: Kopf runter, Kopf leicht auf/ab
	private void eating() {
		head.rotate(1800);
		while (!terminated) {
			head.rotate(-300);
			if (terminated) {
				return;
			}
			head.rotate(300);
		}
		// TODO Auto-generated method stub

	}

	//
	private void sleeping() {
		// TODO Auto-generated method stub

	}

	private void tired() {
		head.rotate(2000);
		head.setSpeed(200);

		while (!terminated) {
			head.rotate(-300);
			if (terminated) {
				return;
			}
			head.rotate(300);
		}
		return;
	}

	private void offended() {
		left.setSpeed(700);
		right.setSpeed(700);
		driveBackward();
		Delay.msDelay(100);
		this.setAction(Actions.None);
		// TODO Auto-generated method stub

	}

	private void normal() {
		terminated = false;
		// motorLeft.setSpeed(300);
		// motorRight.setSpeed(300);
		head.setSpeed(900);
		// driveRight();

		head.rotate(1000);
		if (terminated) {
			return;
		}
		head.rotate(-2000);
		if (terminated) {
			return;
		}
		head.rotate(1000);

	}

	private void driveRight() {
		right.setSpeed(400);
		left.setSpeed(400);
		left.backward();
		right.forward();
	}

	private void driveForward() {
		left.forward();
		right.forward();
	}

	private void driveBackward() {

		left.backward();
		right.backward();
	}

	private void ill() {
		head.rotate(1000);
		right.setSpeed(200);
		left.setSpeed(200);
		left.backward();
		right.backward();
		// TODO Auto-generated method stub

	}

	private void hungry() {
		// TODO Auto-generated method stub

	}

	private void happy() {
		right.setSpeed(600);
		left.setSpeed(600);
		driveForward();
		Delay.msDelay(150);
		driveBackward();
		Delay.msDelay(150);
		driveRight();
		Delay.msDelay(5000);

	}

	private void dying() {
		left.setSpeed(100);
		right.setSpeed(100);
		head.rotateTo(1800);
		driveBackward();
		// TODO Auto-generated method stub

	}

	private void dirty() {
		// TODO Auto-generated method stub

	}

	private void bored() {
		right.setSpeed(400);
		left.setSpeed(400);
		driveForward();
		Delay.msDelay(150);
		driveBackward();
		Delay.msDelay(150);

	}
}
