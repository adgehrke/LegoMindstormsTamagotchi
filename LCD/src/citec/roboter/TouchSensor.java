package citec.roboter;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;
import lejos.robotics.SampleProvider;


public class TouchSensor extends Thread{
	private String port;
	private EV3TouchSensor sensor;
	
	private boolean isPressed;
	private float[] data;
	private int test;
	
	public TouchSensor(String port){
		this.port = port;
		/*this.sensor = new EV3UltrasonicSensor(LocalEV3.get().getPort("S2")); 
		this.distance = sensor.getMode("Distance"); 
		this.data =  new float[distance.sampleSize()];
		this.active = false;*/
		sensor = new EV3TouchSensor(LocalEV3.get().getPort(port));

		this.data =  new float[sensor.getMode("Touch").sampleSize()];
		
		
		test = 0;
	}

	public boolean isPressed() {
		return isPressed;
	}


	public void setData(float[] data) {
		this.data = data;
	}
	
	
	@Override
	public void run(){
		while (true){
			sensor.fetchSample(data, 0);
			
		    if (data[0] == 0){
		    	isPressed = false;
		    }
		    else{
		    	isPressed = true;
		    }
		     
			//distance.fetchSample(this.data,0);
		}
	}

	
}
