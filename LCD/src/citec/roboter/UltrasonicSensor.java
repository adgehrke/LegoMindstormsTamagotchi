package citec.roboter;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class UltrasonicSensor extends Thread{
	private String port;
	private SensorModes sensor;
	private float[] data;
	private SampleProvider distance;
	private boolean active;
	
	private int test;
	
	public UltrasonicSensor(String port){
		this.port = port;
		/*this.sensor = new EV3UltrasonicSensor(LocalEV3.get().getPort("S2")); 
		this.distance = sensor.getMode("Distance"); 
		this.data =  new float[distance.sampleSize()];
		this.active = false;*/
		
		test = 0;
	}


	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	private void fetchData(){
		//distance.fetchSample(this.data,0);
		
	}

	public SensorModes getSensor() {
		return sensor;
	}


	public void setSensor(SensorModes sensor) {
		this.sensor = sensor;
	}

	private void sampleData(){
		while(isActive()){
			fetchData();
		}
	}
	
	
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public float getData() {
		return test;
		/*if (data.length >= 1){
			return this.data[0];
		}
		return 999999.9f;	*/	
	}


	public void setData(float[] data) {
		this.data = data;
	}
	
	public void terminate(){
		this.active = false;
	}


	public SampleProvider getDistance() {
		return distance;
	}


	public void setDistance(SampleProvider distance) {
		this.distance = distance;
	}
	@Override
	public void run(){
		while (true){
			test++;
			Delay.msDelay(1000);
			//distance.fetchSample(this.data,0);
		}
	}

	
}
