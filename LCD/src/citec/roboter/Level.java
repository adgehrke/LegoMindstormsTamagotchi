package citec.roboter;

import Emotion.Emotion;

public class Level {
	
	private int startAge;
	private int toAge;
	private int value;
	private Emotion emotion;
	
	public Level(int toAge, int value, Emotion emotion){
		this.toAge = toAge;
		this.value = value;
		this.emotion = emotion;
	}
	
	public int getStartAge() {
		return startAge;
	}
	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}
	public int getToAge() {
		return toAge;
	}
	public void setToAge(int toAge) {
		this.toAge = toAge;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Emotion getEmotion() {
		return emotion;
	}
	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

}
