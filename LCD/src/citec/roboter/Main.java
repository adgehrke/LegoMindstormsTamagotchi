package citec.roboter;
import citec.Threads.ColorSensor;
import citec.Threads.Display;
import citec.Threads.Motor;
import citec.Threads.UltrasonicSensor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.RegulatedMotor;
//Farbsensor 4
// Touchs 1
// Motoren r. A
// Motor   l. D
// Kopfmotor C
import lejos.utility.Delay;


/**
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args){
		
		//new Motor();
		new Menu();
		//new Tamagotchi(1000, 50);
		//testUltrasonicSensor();
		//testEmotion();
		//finishAfterButtonPress();
		
	}
	
	private static void testDisplay(){
		Display disp = new Display();
		disp.start();
		while(true){
			int delay = 5000;
			disp.healing();
			Delay.msDelay(delay);
			
			disp.playing();
			Delay.msDelay(delay);
			disp.sleeping();
			Delay.msDelay(delay);
			
			
			
			disp.tired();
			Delay.msDelay(delay);
			disp.normal();
			Delay.msDelay(delay);
			disp.offended();
			Delay.msDelay(delay);
			disp.happy();
			Delay.msDelay(delay);
			disp.bored();
			Delay.msDelay(delay);
			disp.ill();
			Delay.msDelay(delay);
			disp.hungry();
			Delay.msDelay(delay);
			disp.dying();
			Delay.msDelay(delay);
		}
		
	}
	
	
	private static void testEmotion(){
		/*EV3TouchSensor sensor = new EV3TouchSensor(SensorPort.S1);
		SimpleTouch touch=new SimpleTouch(sensor);
		//Emotion em = new Emotion(m,m1,m2);
		//em.start();
		while (true) {
	    	if (touch.isPressed()){
	    		em.terminate();
	    		break;
	    	}
		}
		while (true) {
	    	if (touch.isPressed()){
	    		break;
	    	}
		}*/
		
	}
	
	private static void testSensors(){
		ColorSensor co = new ColorSensor("S4");
		co.start();
		co.setActive(true);
		
		UltrasonicSensor us = new UltrasonicSensor("S2");
		us.start();
		us.setActive(true);
		
		while(true){
			LCD.drawString("Color"+co.getData(), 0, 2);
			LCD.drawString("UltraSonic"+us.getData(), 0, 1);
			Delay.msDelay(1000);
		}
	}
}
