package citec.Threads;
import java.util.Random;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class GyroSensor extends Thread{
	private String port;
	private SensorModes sensor;
	private float[] data;
	private SampleProvider gyro;
	private boolean active;
	
	public GyroSensor(String port){
		this.sensor = new EV3GyroSensor(LocalEV3.get().getPort(port)); 
		this.gyro = sensor.getMode(1); 
		this.data =  new float[gyro.sampleSize()];
		this.active = false;
	}


	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	

	public SensorModes getSensor() {
		return sensor;
	}


	public void setSensor(SensorModes sensor) {
		this.sensor = sensor;
	}

	
	
	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public float getData() {
		if (data.length >= 1){
			return this.data[0];
		}
		return 999999.9f;				
	}


	public void terminate(){
		this.active = false;
	}



	@Override
	public void run(){
		while (true){
			gyro.fetchSample(this.data,0);
			/*
			 LCD.clear();
			 LCD.drawString("gyro"+this.data[0], 0, 0);
			 */
			Delay.msDelay(300);
		}
	}
	

	
}
