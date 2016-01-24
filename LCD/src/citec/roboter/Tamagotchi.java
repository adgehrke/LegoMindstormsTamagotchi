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

import Emotion.Bored;
import Emotion.Emotion;
import Emotion.Hungry;
import Emotion.Ill;
import Emotion.Tired;

/**
 *
 */
public class Tamagotchi {

	int speed = 2000;
	Tamagotchi tamagotchi;
	GraphicsLCD gLCD = LocalEV3.get().getGraphicsLCD();
	Timer t;
	Emotion shownEmotion;
	Emotion normalEmotion;
	Emotion dyingEmotion;

	private int age;

	// Needs[Value][Prio]
	Need health = new Need();
	Need sleep = new Need();
	Need fun = new Need();
	Need food = new Need();
	
	List<Need> needs = new ArrayList<>();

	Integer emotionThreshold = 50;

	private static RegulatedMotor m = new EV3LargeRegulatedMotor(MotorPort.D);
	private static RegulatedMotor m1 = new EV3LargeRegulatedMotor(MotorPort.A);
	private static RegulatedMotor m2 = new EV3LargeRegulatedMotor(MotorPort.C);
	
	/**
	 * 
	 */
	
	public Tamagotchi() {
		health.setName("health");
		health.setEmotion(new Ill(m,m1,m2));
		health.addBoundary(10, 10, null);
		health.addBoundary(20, 5, null);
		health.addBoundary(60, 2, null);
		health.addBoundary(80, 10, null);
		
		sleep.setName("sleep");
		sleep.setEmotion(new Tired(m,m1,m2));
		health.addBoundary(10, 10, null);
		health.addBoundary(20, 5, null);
		health.addBoundary(60, 2, null);
		health.addBoundary(80, 10, null);
		
		fun.setName("fun");
		fun.setEmotion(new Bored(m,m1,m2));
		health.addBoundary(10, 10, null);
		health.addBoundary(20, 8, null);
		health.addBoundary(60, 5, null);
		health.addBoundary(80, 2, null);
		
		food.setName("food");
		food.setEmotion(new Hungry(m,m1,m2));
		health.addBoundary(10, 8, null);
		health.addBoundary(20, 10, null);
		health.addBoundary(60, 6, null);
		health.addBoundary(80, 3, null);
		
		needs.add(health);
		needs.add(sleep);
		needs.add(fun);
		needs.add(food);
		for(Need n: needs){
			n.setPriority(50);
			n.setValue(100);
		}

		age = 0;

		t = new Timer(speed, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newDay();
			}
		});
		t.setRepeats(true);
		t.start();
	}

	private void calculateEmotion() {
		Collections.sort(needs);
		for (Need need : needs) {
			if (need.getValue() < emotionThreshold) {
				if (shownEmotion != need.getEmotion()) {
					shownEmotion.terminate();
					shownEmotion = need.getEmotion();
					shownEmotion.start();
					return;
				}
			}
		}
		if (shownEmotion != normalEmotion) {
			shownEmotion.terminate();
			shownEmotion = normalEmotion;
			shownEmotion.start();
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
				shownEmotion.terminate();
				shownEmotion = dyingEmotion;
				shownEmotion.start();
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
