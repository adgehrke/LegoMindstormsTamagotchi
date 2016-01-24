package citec.roboter;

import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

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

	int speed = 2000;
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
		health.addBoundary(20, 5);
		health.addBoundary(60, 2);
		health.addBoundary(80, 10);
		
		sleep.setName("sleep");
		sleep.setEmotion(Emotions.Tired);
		health.addBoundary(10, 10);
		health.addBoundary(20, 5);
		health.addBoundary(60, 2);
		health.addBoundary(80, 10);
		
		fun.setName("fun");
		fun.setEmotion(Emotions.Bored);
		health.addBoundary(10, 10);
		health.addBoundary(20, 8);
		health.addBoundary(60, 5);
		health.addBoundary(80, 2);
		
		food.setName("food");
		food.setEmotion(Emotions.Hungry);
		health.addBoundary(10, 8);
		health.addBoundary(20, 10);
		health.addBoundary(60, 6);
		health.addBoundary(80, 3);
		
		needs.add(health);
		needs.add(sleep);
		needs.add(fun);
		needs.add(food);
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
		}
	}

	private void calculateEmotion() {
		Collections.sort(needs);
		for (Need need : needs) {
			if (need.getValue() < emotionThreshold) {
				if (shownEmotion != need.getEmotion()) {;
					shownEmotion = need.getEmotion();
					display.setEmotion(shownEmotion);
					sound.setEmotion(shownEmotion);
					//motor.setEmotion(shownEmotion);
					return;
				}
			}
		}
		if (shownEmotion != normalEmotion) {
			shownEmotion = normalEmotion;
			display.setEmotion(shownEmotion);
			sound.setEmotion(shownEmotion);
			//motor.setEmotion(shownEmotion);
			return;
		}
		
		
	}

	private void newDay() {

		age++;
		int wellbeing = 0;
		for(Need n: needs){
			n.calculatePriority(age);
			n.calculateValue(age);
			wellbeing+=n.getValue();
		}

		wellbeing = wellbeing / 5;
		if(wellbeing<10){
			if (shownEmotion != dyingEmotion) {
				shownEmotion = dyingEmotion;
				display.setEmotion(shownEmotion);
				sound.setEmotion(shownEmotion);
				//motor.setEmotion(shownEmotion);
				return;
			}
		}

		LCD.setPixel(age, 0, 1);
		// LCD.setPixel(age, 1, 1);
		// LCD.setPixel(age, 2, 1);
		for(Need n: needs){
			LCD.drawString(n.getName() + n.getValue() + " PRIO: " + n.getPriority(), 0, 1);
		}
		LCD.drawString("Wellbeing" + wellbeing, 0, 5);
		
		calculateEmotion();

	}


}
