
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
	
	/*
	 *End every running Emotion and go back to Initial Position 
	 */
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

	/**
	 * 
	 * Stillstehen, blinzeln, Kopf auf und ab
	 * @return
	 */
	public boolean normal(){
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
	
	/**
	 * @return
	 * Display: Augen groß
	 * Sound: Wuiiuuuu
	 * Kopf: hoch-rutner-schnell
	 * Motor: vor+zurück
	 * er will spielen
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
	 * im Kreis drehen, kreischen
	 */
	public boolean excited(){
		
		return false;
		
	}
	
	/**
	 * @return
	 * zurückfahren+böser Blick
	 */
	public boolean offended(){
		
		return false;
		
	}
	
	
	/*
	 * er will Medizin
	 * 
	 * Display: Augen halb geschlossen
	 * Sound: halb am sterben
	 * Motor: Langsame Schlangen-Bewegung
	 * Kopf: Kopf nach unten
	 */
	public boolean ill(){
		return false;
	}
	
	/*
	 * Hunger
	 * Display: Augen auf
	 * Sound: schreien
	 * Motor: Langsame Bewegung
	 * Kopf: Kopf nach oben
	 */
	public boolean hungry(){
		return false;
	}
	
	/*
	 * Kopf: Head down 
	 * Sound: gähn-sound 
	 * Display: Augen schmaler 
	 * Bewegung: keine Bewegung
	 * Er will schlafen
	 */
	public boolean tired(){
		motorHead.rotate(2000);
		motorHead.setSpeed(200);
		
		while(!terminated){
			motorHead.rotate(-300);
			if(terminated){
				return false;
			}
			motorHead.rotate(300);
		}
		return true;
	}
	
	/*
	 * 
	 * Kopf: nach untten
	 * Display: Augen schuldbewusst, traurig
	 * 
	 */
	public boolean dirty(){
		return false;
	}
	
	
	/*
	 * Kopf: runter
	 * Augen: zu Kreuzen
	 * Bewegung: Rückartig vorwärts, dann stillstand
	 * Sound: Röcheln
	 */
	public boolean dying(){
		return false;
	}
	
	
	@Override
	public void run(){
		if(normal()){
			return;
		}
	}
	
	
	
	
	

}
