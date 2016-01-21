import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.Sound;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.lcd.LCD;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Timer;
 
/**
 *
 */
public class Tamagotchi {
	
	
	int speed=2000;
	Tamagotchi tamagotchi;
	GraphicsLCD gLCD = LocalEV3.get().getGraphicsLCD();
	Timer t;
	
	private int age;
	
	// Needs[Value][Prio]
	int[] health = new int[2];
	int[] sleep = new int[2];
	int[] fun = new int[2];
	int[] food = new int[2];
	
	
	/**
	 * 
	 */
	private void newDay(){
		Sound.playSample(new File("growl.wav"));
		age++;
		
		calcNewHealth();
		sleep[0]--;
		fun[0]--;
		food[0]--;
		
		int wellbeing = (health[0]+sleep[0]+fun[0]+food[0])/5;
		
		LCD.setPixel(age, 0, 1);
		//LCD.setPixel(age, 1, 1);
		//LCD.setPixel(age, 2, 1);
		
		LCD.drawString("Health"+health[0]+" PRIO: "+health[1], 0, 1);
		LCD.drawString("Sleep"+sleep[0], 0, 2);
		LCD.drawString("Fun"+fun[0], 0, 3);
		LCD.drawString("Food"+food[0], 0, 4);
		LCD.drawString("Wellbeing"+wellbeing, 0, 5);
		if (wellbeing <= 10){ die(); }
		else if (wellbeing <= 50){ warn(wellbeing); }

		

	}
	
	/**
	 * 
	 */
	private void die(){
		LCD.drawString("ich bin tot :(", 0, 7);
	}
	
	/**
	 * @param wellbeing
	 */
	private void warn(int wellbeing){
		LCD.drawString("mir geht es schlecht :( "+wellbeing, 0, 7);
	}
	
	/**
	 * 
	 */
	private void calcNewHealth(){
		// health Function
		// 0-25		PRIO => 100-age
		// 25-50	PRIO => age/2
		// 50_75	PRIO => age
		// 75-XX	PRIO => age*2
		if (age <= 25){
			health[1] = (50-age)*10;
		}
		else if (age <= 50){
			health[1] = age*10;
		}else if (age <= 75){
			health[1] = age*15;
		}else{
			health[1] = age*20;
		}
		
		int difference = health[1]/100;
		if (difference < 1){
			difference = 1;
		}
		health[0]-=difference;
	}
	
	/**
	 * 
	 */
	public Tamagotchi(){
		health[0] = sleep[0] = fun[0] = food[0] = 100;
		health[1] = sleep[1] = fun[1] = food[1] = 50;
		
		
		
		
		age=0;
		
		
		t = new Timer(speed, new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					 newDay();
				}
			});
		t.setRepeats(true);
		t.start(); 
	}
	
}
