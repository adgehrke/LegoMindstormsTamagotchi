package citec.roboter;
import java.util.Random;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class ColorSensor extends Thread{
	private String port;
	private SensorModes sensor;
	private float[] data;
	private SampleProvider color;
	private boolean active;
	private int test;
	
	public ColorSensor(String port){
		/*this.port = "S4";
		this.sensor = new EV3ColorSensor(LocalEV3.get().getPort(port)); 
		this.color = sensor.getMode("ColorID"); 
		this.data =  new float[color.sampleSize()];
		this.active = false;*/
	}


	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	private void fetchData(){
		color.fetchSample(this.data,0);
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


	public SampleProvider getColor() {
		return color;
	}


	public void setColor(SampleProvider color) {
		this.color = color;
	}


	@Override
	public void run(){
		while (true){
			test = randInt(0, 7);
			Delay.msDelay(1000);
			//distance.fetchSample(this.data,0);
		}
	}
	
	private int randInt(int min, int max) {

	    // Usually this can be a field rather than a method variable
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}

	
}
