package citec.roboter;
import citec.Threads.Motor;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.GraphicsLCD;

import lejos.hardware.lcd.LCD;
public class Menu {
	private GraphicsLCD lcd;
	private int lengthOfDay = 5;
	private int emotionThreshold = 5;
	int menuPointDistance = 25;
	
	public Menu(){
		boolean exit = false;
		lcd = BrickFinder.getDefault().getGraphicsLCD();
		Tamagotchi t = null;
		
		
	
		
		int activeMenuPoint = 20;
		Motor m = new Motor();
		while(!exit){
			lcd.clear();
			drawMenuText();
			drawActiveMenuBars(activeMenuPoint);
			Button.waitForAnyPress();
			if (Button.readButtons() == Button.ID_DOWN && activeMenuPoint < 80){
				activeMenuPoint += menuPointDistance;
			}
			else if(Button.readButtons() == Button.ID_DOWN){
				m.headDown(360);
			}
			if (Button.readButtons() == Button.ID_UP && activeMenuPoint > 20){
				activeMenuPoint -= menuPointDistance;
			}
			else if(Button.readButtons() == Button.ID_UP){
				m.headUp(360);
			}
			
			if (Button.readButtons() == Button.ID_RIGHT){
				if (activeMenuPoint == 20+menuPointDistance*1 && lengthOfDay < 1000){
					lengthOfDay += 1;
				}
				else if (activeMenuPoint == 20+menuPointDistance*2 && emotionThreshold < 10){
					emotionThreshold += 1;
				}
			}
			if (Button.readButtons() == Button.ID_LEFT){
				if (activeMenuPoint == 20+menuPointDistance*1 && lengthOfDay > 1){
					lengthOfDay -= 1;
				}
				else if (activeMenuPoint == 20+menuPointDistance*2 && emotionThreshold > 1){
					emotionThreshold -= 1;
				}
			}
			if (Button.readButtons() == Button.ID_ENTER){
				if (activeMenuPoint == 20){
					activeMenuPoint = 20;
					t = new Tamagotchi(lengthOfDay*1000, emotionThreshold*10);
					lcd.clear();
					LCD.clear();
				}
				if (activeMenuPoint == 20+menuPointDistance*3){
					exit=true;
					System.exit(0);
				}
				
			}
		}
		
	}
	
	private void drawActiveMenuBars(int y){
		lcd.fillRect(0, y, 10, 15);
		lcd.fillRect(lcd.getWidth()-10, y, 10, 15);
	}
	
	private void drawMenuText(){
		lcd.drawString("Start Game", lcd.getWidth()/2, 20+menuPointDistance*0, 1);
		lcd.drawString("Day Length: "+lengthOfDay+"s", lcd.getWidth()/2, 20+menuPointDistance*1, 1);
		lcd.drawString("Difficulty: "+emotionThreshold, lcd.getWidth()/2, 20+menuPointDistance*2, 1);
		lcd.drawString("Exit", lcd.getWidth()/2, 20+menuPointDistance*3, 1);
		
	}
}
