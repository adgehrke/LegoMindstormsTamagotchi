package citec.roboter;

import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Timer;

import citec.Threads.Display;
import citec.Threads.Sounds;
import Emotion.Emotions;


/**
 *
 */
public class Tamagotchi {

	int speed = 1000;
	int wellbeing = 100;
	Tamagotchi tamagotchi;
	GraphicsLCD gLCD = LocalEV3.get().getGraphicsLCD();
	Emotions shownEmotion = Emotions.Normal;
	Emotions normalEmotion = Emotions.Normal;
	Emotions dyingEmotion = Emotions.Dying;

	private int age;

	// Needs[Value][Prio]
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
	
	/**
	 * 
	 */
	
	public Tamagotchi() {
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
		while(true){
			newDay();
			Delay.msDelay(speed);
		}
	}

	private void calculateEmotion() {
		int tmpMaxPrio = 0;
		Emotions tmpNewEmotion = shownEmotion;
		
		if(wellbeing<10){
			if (shownEmotion != dyingEmotion) {
				shownEmotion = dyingEmotion;
				display.setEmotion(shownEmotion);
				sound.setEmotion(shownEmotion);
				//motor.setEmotion(shownEmotion);	
			}
			return;
		}
		
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
		
		display.drawVal(health.getValue());

		
	}

	private void newDay() {

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
		//LCD.drawString("Wellbeing" + wellbeing, 0, 5);
		
		calculateEmotion();

	}


}
