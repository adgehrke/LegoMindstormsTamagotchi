package citec.roboter;

import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Timer;

import citec.Threads.Action;
import citec.Threads.ColorSensor;
import citec.Threads.Display;
import citec.Threads.GyroSensor;
import citec.Threads.Motor;
import citec.Threads.Sounds;
import citec.Threads.TouchSensor;
import Emotion.Emotions;


/**
 *
 */
public class Tamagotchi{

	int speed = 1000;
	int wellbeing = 100;
	Tamagotchi tamagotchi;
	GraphicsLCD gLCD = LocalEV3.get().getGraphicsLCD();
	Emotions shownEmotion = Emotions.Normal;
	Emotions normalEmotion = Emotions.Normal;
	Emotions dyingEmotion = Emotions.Dying;
	Actions shownAction = Actions.None;
	
	private int age;

	// Needs
	Need health = new Need();
	Need sleep = new Need();
	Need fun = new Need();
	Need food = new Need();
	Need cleanliness = new Need();
	
	
	List<Need> needs = new ArrayList<>();

	Integer emotionThreshold = 50;
	
	Display display = new Display();
	Motor motor = new Motor();
	Sounds sound = new Sounds();
	

	//TouchSensor touchSensorCleaning = new TouchSensor("S1");
	ColorSensor coSensor = new ColorSensor("S2");
	GyroSensor gyroSensor = new GyroSensor("S3");
	TouchSensor touchSensorHealing = new TouchSensor("S1", this);
	TouchSensor touchSensorCleaning = new TouchSensor("S4", this);
	
	
	
	private boolean alive = true;
	
	/**
	 * 
	 */
	
	public void sensorPressed(String port){
		
	}
	public Tamagotchi(int lengthOfDay, int emotionThreshold){
		
		
		this.speed = lengthOfDay;
		this.emotionThreshold = emotionThreshold;
		health.setName("health");
		health.setEmotion(Emotions.Ill);
		health.addBoundary(10, 10);
		health.addBoundary(20, 6);
		health.addBoundary(60, 2);
		health.addBoundary(80, 10);
		
		sleep.setName("sleep");
		sleep.setEmotion(Emotions.Tired);
		sleep.addBoundary(10, 9);
		sleep.addBoundary(20, 5);
		sleep.addBoundary(60, 2);
		sleep.addBoundary(80, 9);
		
		fun.setName("fun");
		fun.setEmotion(Emotions.Bored);
		fun.addBoundary(10, 7);
		fun.addBoundary(20, 8);
		fun.addBoundary(60, 5);
		fun.addBoundary(80, 2);
		
		food.setName("food");
		food.setEmotion(Emotions.Hungry);
		food.addBoundary(10, 9);
		food.addBoundary(20, 10);
		food.addBoundary(60, 6);
		food.addBoundary(80, 3);
		
		cleanliness.setName("cleanliness");
		cleanliness.setEmotion(Emotions.Dirty);
		cleanliness.addBoundary(10, 6);
		cleanliness.addBoundary(20, 3);
		cleanliness.addBoundary(60, 2);
		cleanliness.addBoundary(80, 7);
		
		needs.add(health);
		needs.add(sleep);
		needs.add(fun);
		needs.add(food);
		needs.add(cleanliness);
		
		for(Need n: needs){
			n.setPriority(50);
			n.setValue(100);
		}

		age = 0;

		
		motor.start();
		display.start();
		sound.start();
		
		touchSensorHealing.start();
		touchSensorCleaning.start();
		
		gyroSensor.start();
		coSensor.start();
		
		
		while(alive==true){
			newDay();
			Delay.msDelay(1000);
		}
	}
	

	private void calculateEmotion() {
		int tmpMaxPrio = 0;
		Emotions tmpNewEmotion = shownEmotion;
		/*if (wellbeing < 15){
			this.alive = false;
			return;
		}
		else if(wellbeing<20){
			if (shownEmotion != dyingEmotion) {
				shownEmotion = dyingEmotion;
				display.setEmotion(shownEmotion);
				//sound.setEmotion(shownEmotion);
				//motor.setEmotion(shownEmotion);	
			}
			return;
		}*/
		
		for (Need need : needs) {
			if (need.getValue() < emotionThreshold && need.getPriority() > tmpMaxPrio) {
					tmpNewEmotion = need.getEmotion();
					tmpMaxPrio = need.getPriority();
			}
		}
		if (tmpMaxPrio > 0 && tmpNewEmotion != shownEmotion){
			// emotion has changed 
			
			shownEmotion = tmpNewEmotion;

			motor.setEmotion(shownEmotion);
			display.setEmotion(shownEmotion);
			sound.setEmotion(shownEmotion);
			
		}
		else if(tmpMaxPrio > 0){
			// same emotion as before
		}
		else{
			// set to normal emotion
			
			shownEmotion = normalEmotion;

			motor.setEmotion(shownEmotion);
			display.setEmotion(shownEmotion);
			sound.setEmotion(shownEmotion);
		}
		
		//display.drawVal(health.getValue());

		
	}
	

	private void calculateAction(){
		shownAction = Actions.None;
		
		// Healing Button pressed
		if (touchSensorHealing.isPressed()){
			if (shownAction == Actions.Healing){
				shownAction = Actions.None;
			}
			else if(shownAction == Actions.None){
				shownAction = Actions.Healing;
				health.setValue(health.getValue()+100);
			}
		}
		
		// Cleaning Button pressed
		if (touchSensorCleaning.isPressed()){
			if (shownAction == Actions.Cleaning){
				shownAction = Actions.None;
			}
			else if(shownAction == Actions.None){
				shownAction = Actions.Cleaning;
				cleanliness.setValue(cleanliness.getValue()+100);
			}
		}
		
		// Food = Green
		if (coSensor.getData() == 1){

			food.setValue(food.getValue()+50);
			fun.setValue(fun.getValue()+50);
			
			if(shownAction != Actions.Eating){
				setAction(Actions.Eating);
			}
			return;
		}
		else{
			if (shownAction == Actions.Eating){
				setAction(Actions.None);
			}
		}
		
		// Sleeping
		if (gyroSensor.getData() >= 80 || gyroSensor.getData() <= -80){
			if(shownAction == Actions.None){
				setAction(Actions.Sleeping);
				
				sleep.setValue(sleep.getValue()+100);
			}
			else if(shownAction == Actions.Sleeping){
				sleep.setValue(sleep.getValue()+100);
			}
		}
		else{
				setAction(Actions.None);
				
		}
		
	}
	
	private void setAction(Actions a){
		shownAction = a;
		sound.setAction(a);
		display.setAction(a);
		motor.setAction(a);
	}
	private int counter = 0;
	private void newDay() {
		calculateAction();
		counter+=1000;
		if (counter % speed == 0){
			age++;
			wellbeing = 0;
			for(Need n: needs){
				n.calculatePriority(age);
				n.calculateValue(age);
				wellbeing+=n.getValue();
			}
			
			// calculate average
			wellbeing=wellbeing/needs.size();
			
	
			//LCD.setPixel(age, 0, 1);
			// LCD.setPixel(age, 1, 1);
			// LCD.setPixel(age, 2, 1);
			int y = 1;
			for(Need n: needs){
				y++;
				LCD.drawString(n.getName().substring(0, 3) + n.getValue() + ", " + n.getPriority(), 0, y);
			}
			LCD.drawString("Wellbeing" + wellbeing, 0, 7);
			
			if (shownAction == Actions.None){
				calculateEmotion();
			}
		}
		
		
	
		

	}


}
