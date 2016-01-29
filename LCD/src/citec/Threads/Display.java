package citec.Threads;
import java.util.Random;

import Emotion.Emotions;
import citec.roboter.Actions;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.utility.Delay;

// size of display: 178 x 128
public class Display extends Thread{
	private GraphicsLCD lcd;
	private int height;
	private int width;
	private int eyesWidth;
	private int eyesHeight;
	private int yOffset;
	private int xOffset;
	private boolean terminated = false;
	private Emotions emotion = Emotions.Normal;
	private Actions action = Actions.None;
	private boolean changed = true;
	public Display(){
		lcd = BrickFinder.getDefault().getGraphicsLCD();
		this.height = lcd.getHeight();
		this.width = lcd.getWidth();
		
		xOffset = 20;
		yOffset = (this.height-eyesHeight)/2+10;
		eyesHeight = 70;
		eyesWidth = 50;
	}
	
	
	public void drawVal(int val){
		lcd.setColor(255, 255, 255);
		lcd.drawLine(0, 0, 0, 1000);
		lcd.drawLine(1, 0, 1, 1000);
		
		lcd.setColor(0,0,0);
		lcd.drawLine(0, 0, 0, val);
		lcd.drawLine(1, 0, 1, val);
		
	}
public void setEmotion(Emotions e){
		
		if (action == Actions.None && e != emotion){
			emotion = e;
			changed = true;
		}
	}
	
	public void setAction(Actions a){
		if (a != action){
			action = a;
			changed = true;
		}
	}
	
	public void run(){
		while(!terminated){
			if (changed == true){
				changed = false;
				switch(action){
					case None:
						break;
					case Sleeping:
						this.sleeping();
						break;
					case Eating:
						break;
					case Healing:
						break;
					case Playing:
						break;
					default:
						break;
				}
				if (action == Actions.None){
					switch(emotion){
						case Bored:
							this.bored();
							break;
						case Dirty:
							this.dirty();
							break;
						case Dying:
							this.dying();
							break;
						case Excited:
							this.happy();
							break;
						case Hungry:
							this.hungry();
							break;
						case Ill:
							this.ill();
							break;
						case Normal:
							this.normal();
							break;
						case Offended:
							this.offended();
							break;
						case Tired:
							this.tired();
							break;
					}
				}
			}
	
		}
	}

	
	
	
	public void drawEyes(){
		yOffset = (this.height-eyesHeight)/2+10;
		
		lcd.drawRect(xOffset,yOffset, eyesWidth, eyesHeight);
		lcd.drawRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, eyesHeight);
		
		lcd.drawRect(xOffset-1,yOffset-1, eyesWidth+2, eyesHeight+2);
		lcd.drawRect(this.width-eyesWidth-xOffset-1,yOffset-1, eyesWidth+2, eyesHeight+2);
	}
	
	
	public void happy(){	
		this.clear();
		this.drawEyes();
		lcd.drawString("Happy", 0, 0, 0);
		
		// Pupils
		drawPupils(15, 0, -(eyesHeight/2)-10);
		
		//lcd.fillRect(xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		//lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
	}
	
	public void dirty(){	
		this.clear();
		this.drawEyes();
		lcd.drawString("Dirty", 0, 0, 0);
		
		// Pupils
		drawPupils(55, 0, -(eyesHeight/2)-10);
		
		//lcd.fillRect(xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		//lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
	}
	
	public void dying(){	
		this.clear();
		this.drawEyes();
		lcd.drawString("Dying", 0, 0, 0);
		
		// Pupils
		int thickness = 2;
		drawThickLine(xOffset+eyesWidth/2-thickness, yOffset+eyesHeight/2-15, xOffset+eyesWidth/2+thickness, yOffset+eyesHeight/2-15, 30);
		drawThickLine(xOffset+eyesWidth/2-15, yOffset+eyesHeight/2-thickness, xOffset+eyesWidth/2+15, yOffset+eyesHeight/2-thickness, 5);
		
		drawThickLine(this.width-xOffset-eyesWidth/2-thickness, yOffset+eyesHeight/2-15, this.width-xOffset-eyesWidth/2+thickness, yOffset+eyesHeight/2-15, 30);
		drawThickLine(this.width-xOffset-eyesWidth/2-15, yOffset+eyesHeight/2-thickness, this.width-xOffset-eyesWidth/2+15, yOffset+eyesHeight/2-thickness, 5);
		
		
		
		//lcd.fillRect(xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		//lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
	}
	
	public void hungry(){	
		this.clear();
		this.drawEyes();
		lcd.drawString("Hungry", 0, 0, 0);
		
		// Pupils
		drawPupils(15, 0, -(eyesHeight/2)-10);
		
		//lcd.fillRect(xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		//lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
	}
	
	public void bored(){	
		this.clear();
		this.drawEyes();
		lcd.drawString("Bored", 0, 0, 0);
		
		// Pupils
		drawPupils(30, 0, -(eyesHeight/2));
		
		//lcd.fillRect(xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		//lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
	}
	
	public void normal(){	
		this.clear();
		
		for(int i=0;i<3;i++){
			lcd.drawString("Normal", 0, 0, 0);
			lcd.fillRect(xOffset,yOffset, eyesWidth, eyesHeight);
			lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, eyesHeight);
			Delay.msDelay(300);
			this.clear();
			this.drawEyes();
			lcd.drawString("Normal", 0, 0, 0);
			// Pupils
			drawPupils(15, 0, -(eyesHeight/2)-10);
			Delay.msDelay(2000);
			
			//lcd.fillRect(xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
			//lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		}
		
	}
	
	public void eating(){
		this.clear();
		lcd.drawString("Eating", 0, 0, 0);
	}
	
	public void sleeping(){	
		this.clear();
		lcd.drawString("Sleeping", 0, 0, 0);
		lcd.fillRect(xOffset,yOffset, eyesWidth, eyesHeight-5);		
	}
	
	private void drawPupils(int pupilsSize, int xOffset, int yOffset){
		int pupilsXOffset = eyesWidth/2-pupilsSize/2+xOffset;
		int pupilsYOffset = this.yOffset+eyesHeight/1-pupilsSize/2+yOffset;
		
		
		lcd.fillRect(this.xOffset+pupilsXOffset, pupilsYOffset, pupilsSize, pupilsSize);
		lcd.fillRect(this.width-eyesWidth-this.xOffset+pupilsXOffset, pupilsYOffset, pupilsSize, pupilsSize);
		
	}
	
	public void playing(){
		
		// Pupils
		for (int i = 0; i<10; i++){
			this.clear();
			this.drawEyes();
			lcd.drawString("Happy", 0, 0, 0);
			int offsetX = randInt(-10,10);
			int offsetY = randInt(-10,10);
			
			drawPupils(15, offsetX, -(eyesHeight/2)-offsetY);
			Delay.msDelay(300);
		}	
	}
	
	private int randInt(int min, int max) {

		    Random rand = new Random();
		    int randomNum = rand.nextInt((max - min) + 1) + min;
		    return randomNum;
		}
	
	public void tired(){	
		
		this.clear();
		this.drawEyes();
		lcd.drawString("Tired", 0, 0, 0);
		
		lcd.fillRect(xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		
		// Pupils
		drawPupils(15, 0, -20);
		
		//Eyebrows
		int eyebrowsOffset = 5;
		
		drawThickLine(xOffset, 20, xOffset+eyesWidth+eyebrowsOffset, 5, 5);
		drawThickLine(this.width-xOffset, 20, this.width-xOffset-eyesWidth-eyebrowsOffset, 5, 5);
	}
	
	public void ill(){	
		
		this.clear();
		this.drawEyes();
		lcd.drawString("Ill", 0, 0, 0);
		
		lcd.fillRect(xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, (int) Math.round(eyesHeight/1.5));
		
		// Pupils
		drawPupils(15, 0, -20);
		
		//Eyebrows
		int eyebrowsOffset = 5;
		
		drawThickLine(xOffset, 20, xOffset+eyesWidth+eyebrowsOffset, 5, 5);
		drawThickLine(this.width-xOffset, 20, this.width-xOffset-eyesWidth-eyebrowsOffset, 5, 5);
	}
	
public void healing(){	
		
		for(int i = (int) Math.round(eyesHeight/1.5);i>0;i--){
			this.clear();
			this.drawEyes();
			lcd.drawString("Healing", 0, 0, 0);
			
			lcd.fillRect(xOffset,yOffset, eyesWidth, i);
			lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, i);
			
			// Pupils
			drawPupils(15, 0, -20);
			
			//Eyebrows
			int eyebrowsOffset = 5;
			
			drawThickLine(xOffset, 20, xOffset+eyesWidth+eyebrowsOffset, 5, 5);
			drawThickLine(this.width-xOffset, 20, this.width-xOffset-eyesWidth-eyebrowsOffset, 5, 5);
			Delay.msDelay(100);
		}
		
	}
	
	
	
	public void offended(){
		this.clear();
		this.drawEyes();
		
		lcd.drawString("Offended", 0, 0, 0);
		lcd.fillRect(xOffset,yOffset, eyesWidth, eyesHeight/3);
		lcd.fillRect(this.width-eyesWidth-xOffset,yOffset, eyesWidth, eyesHeight/3);
		
		
		
		// Pupils
		drawPupils(15, 0, -20);
		
		//Eyebrows
		int eyebrowsOffset = 5;
		
		drawThickLine(xOffset, 5, xOffset+eyesWidth+eyebrowsOffset, 20, 5);
		drawThickLine(this.width-xOffset, 5, this.width-xOffset-eyesWidth-eyebrowsOffset, 20, 5);

	}
	
	public void drawThickLine(int x0, int y0, int x1, int y1, int thickness){
		for(int i = 0; i<thickness; i++){
			lcd.drawLine(x0, y0+i, x1, y1+i);
		}
	}
	
	public void clear(){
		lcd.clear();
	}
}
