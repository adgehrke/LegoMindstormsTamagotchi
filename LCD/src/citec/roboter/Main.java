package citec.roboter;
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
	private static RegulatedMotor m = new EV3LargeRegulatedMotor(MotorPort.D);
	private static RegulatedMotor m1 = new EV3LargeRegulatedMotor(MotorPort.A);
	private static RegulatedMotor m2 = new EV3LargeRegulatedMotor(MotorPort.C);
	

	/**
	 * @param args
	 */
	public static void main(String[] args){
		//new Tamagotchi();
		//testUltrasonicSensor();
		testEmotion();
		//finishAfterButtonPress();
		
	}
	
	private static void finishAfterButtonPress(){
		EV3TouchSensor sensor = new EV3TouchSensor(SensorPort.S1);
		SimpleTouch touch=new SimpleTouch(sensor);
		
		while (true) {
	    	if (touch.isPressed()){
	    		break;
	    	}
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
