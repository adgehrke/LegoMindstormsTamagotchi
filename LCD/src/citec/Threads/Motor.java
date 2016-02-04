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

	
	//Max = -2160 = 6x(-360)
	//Min = 1800 = 5*(360)
	public Motor() {
		TouchSensor touch = new TouchSensor("S4");
		touch.start();
		while (true) {
			while (!touch.isPressed()) {
				head.rotate(360);
			}
			toInitialPosition();

		}
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
		System.out.println(head.getTachoCount());
		left.stop();
		right.stop();
		head.stop();
		System.out.println(head.getTachoCount());
		head.rotateTo(0);
		System.out.println(head.getTachoCount());
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
			Delay.msDelay(100);
		}
	}

	// Bewegung: F�hrt gradeaus, bis schwarze-Randlinie, dreht sich min 90�
	// max 270�
	private void playing() {
		// TODO Auto-generated method stub

	}

	// Kopf langsam hoch
	private void healing() {
		// TODO Auto-generated method stub

	}

	// Kopf: Kopf runter, Kopf leicht auf/ab
	private void eating() {
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
		// motorLeft.setSpeed(300);
		// motorRight.setSpeed(300);
		while (!terminated) {
			left.forward();
			right.stop();
			Delay.msDelay(150);
			left.stop();
			right.backward();
			Delay.msDelay(150);
			left.backward();
			right.stop();
			Delay.msDelay(150);
			left.stop();
			right.forward();
			Delay.msDelay(150);
		}

	}
}
