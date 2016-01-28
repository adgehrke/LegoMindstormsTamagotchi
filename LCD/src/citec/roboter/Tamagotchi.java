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
import citec.Threads.Display;
import citec.Threads.Sounds;
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
	//Motor motor = new Motor();
	Sounds sound = new Sounds();
	

	//TouchSensor touchSensorCleaning = new TouchSensor("S1");
	ColorSensor coSensor = new ColorSensor("S3");
	GyroSensor gyroSensor = new GyroSensor("S2");
	TouchSensor touchSensorHealing = new TouchSensor("S4");
	
	
	
	private boolean alive = true;
	
	/**
	 * 
	 */
	
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
		sleep.addBoundary(10, 8);
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

		
		//motor.start();
		display.start();
		sound.start();
		
		touchSensorHealing.start();
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

			//motor.setEmotion(shownEmotion);
			display.setEmotion(shownEmotion);
			sound.setEmotion(shownEmotion);
			
		}
		else if(tmpMaxPrio > 0){
			// same emotion as before
		}
		else{
			// set to normal emotion
			
			shownEmotion = normalEmotion;

			//motor.setEmotion(shownEmotion);
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
		
		// Food = Green
		if (coSensor.getData() == 1){
			//if(shownAction == Actions.None){
				shownAction = Actions.Eating;
				food.setValue(food.getValue()+50);
			//}
		}
		else{
			if (shownAction == Actions.Eating){
				shownAction = Actions.None;
			}
		}
		
		// Sleeping
		if (gyroSensor.getData() >= 80 || gyroSensor.getData() <= -80){
			if(shownAction == Actions.None){
				shownAction = Actions.Sleeping;
				sound.setAction(Actions.Sleeping);
				sleep.setValue(sleep.getValue()+100);
			}
			else if(shownAction == Actions.Sleeping){
				sleep.setValue(sleep.getValue()+100);
			}
		}
		else{
				shownAction = Actions.None;
				sound.setAction(Actions.None);
		}
		
	}

	private int counter = 0;
	private void newDay() {
		calculateAction();
		
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
				//LCD.drawString(n.getName() + n.getValue() + " PRIO: " + n.getPriority(), 0, y);
			}
			LCD.drawString("Wellbeing" + wellbeing, 0, 5);
			
			if (shownAction == Actions.None){
				calculateEmotion();
			}
		}
		
		
	
		

	}


}
