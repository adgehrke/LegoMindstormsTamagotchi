package citec.roboter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import Emotion.Emotions;

public class Need implements Comparable<Need>{
	
	private String name;
	private int value;
	private Integer priority;
	private Emotions underThreshold;
	
	private List<int[]> boundaries = new ArrayList<>();
	
	/**
	 * @param toAge
	 * @param value
	 */
	public void addBoundary(int toAge, int value){
		boundaries.add(new int[]{toAge,value});
	}
	
	/**
	 * @param age
	 * 
	 */
	public void calculatePriority(int age){
		priority = boundaries.get(0)[1];
		for (int[] boundary : boundaries) {
			if (age > boundary[0]){
				priority = boundary[1];
			}
		}
	}
	
	public void calculateValue(int age){
		int difference = ((priority*100)*(priority*100)) / 100000;
		if (difference < 1) {
			difference = 1;
		}
		value -= difference;
		
	}
	
	public void setEmotion(Emotions e){
		underThreshold = e;
	}
	
	public Emotions getEmotion(){
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

	@Override
	public int compareTo(Need o) {
		return this.getPriority().compareTo(o.getPriority());
	}


}
