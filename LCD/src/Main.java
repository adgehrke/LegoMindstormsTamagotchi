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
		
		
		Emotion em = new Emotion(m,m1,m2);
		em.start();
	    EV3TouchSensor sensor = new EV3TouchSensor(SensorPort.S1);

	    SimpleTouch touch=new SimpleTouch(sensor);
		
		while (true) {
	    	if (touch.isPressed()){
	    		em.terminate();
	    		break;
	    	}
		}
		
		/*
		m.setSpeed(300);
		m1.setSpeed(300);

		
		 

	    EV3TouchSensor sensor = new EV3TouchSensor(SensorPort.S1);

	    SimpleTouch touch=new SimpleTouch(sensor);
	    
	    boolean drive = false;
	    while (true) {
	    	if (touch.isPressed()){
	    		if (drive){ 
	    			m.stop(); 
	    			m1.stop(); 
	    			
	    		}
	    		else{ 
	    			m.backward(); 
	    			m1.backward(); 
	    			
	    		}
	    		drive = !drive;
	    	}
	      Delay.msDelay(150);
	    }
	    */


	}
}
