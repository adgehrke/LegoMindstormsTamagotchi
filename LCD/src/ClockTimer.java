import java.util.Timer;
import java.util.TimerTask;

import lejos.hardware.lcd.LCD;

public class ClockTimer {
	int speed = 10;
	Timer t;
	public Timer getT() {
		return t;
	}
	
	public void setT(Timer t) {
		this.t = t;
	}
	ClockTimer(){
		t = new Timer();
	}
	
	
	
}
