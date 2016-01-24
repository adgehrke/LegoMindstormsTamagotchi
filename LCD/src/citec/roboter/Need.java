package citec.roboter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Emotion.Emotion;

public class Need {
	
	private String name;
	private int value;
	private Integer priority;
	private Emotion underThreshold;
	
	private List<Level> boundaries = new ArrayList<>();
	
	public void addBoundary(int toAge, int value, Emotion emotion){
		boundaries.add(new Level(toAge,value,emotion));
	}
	
	public void calculatePriority(int age){
		if (age <= 25) {
			priority = (50 - age) * 10;
		} else if (age <= 50) {
			priority = age * 10;
		} else if (age <= 75) {
			priority = age * 15;
		} else {
			priority = age * 20;
		}
	}
	
	public void calculateValue(int age){


		int difference = priority / 100;
		if (difference < 1) {
			difference = 1;
		}
		value -= difference;
		
	}
	
	public void setEmotion(Emotion e){
		underThreshold = e;
	}
	
	public Emotion getEmotion(){
		return underThreshold;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}


}
