package citec.Threads;
import citec.roboter.Tamagotchi;
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
	
	private Tamagotchi listener;
	
	public void addListener(Tamagotchi t){
		listener = t;
	}
	public TouchSensor(String port, Tamagotchi t){
		addListener(t);
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
		    	setPressed(true);
		    }
		     
			//distance.fetchSample(this.data,0);
		}
	}
	
	private void setPressed(boolean f){
		listener.sensorPressed(this.port);
		isPressed = f;
	}

	
}
