package citec.Threads;

public class Action extends Thread{

	
	/*
	 * Kopf: Kopf runter, Kopf leicht auf/ab
	 * Sound: Ess-Sound
	 * 
	 * Endet, wenn Essen weg (Farbsensor)
	 * 
	 */
	public void eating(){
		
	}
	
	/*
	 * Keine Bewegung
	 * Display: Augen zu
	 * Sound: Schnarchen
	 * 
	 * Nach 1 min, schreit er, bis er wieder gerade ist.
	 * Endet, wenn er wieder aufgestellt ist.
	 * 
	 */
	public void sleeping(){
		
	}
	
	/*
	 * Bewegung: F�hrt gradeaus, bis schwarze-Randlinie, dreht sich min 90� max 270�
	 * Kopf: stabil eher unten
	 * Sound: singsang-summen
	 * Display: Augen Auf. (Pupillenbewegung)
	 * 
	 * Endet nach max 1min, oder wenn Ball gefunden.
	 * 
	 */
	public void playing(){
		
	}
	
	
	/*
	 * 
	 * Sound: gl�cklich summen (aufsteigend)
	 * Display: Augen �ffnen sich langsam
	 * 
	 * Endet, sofort nach Spritze
	 * 
	 */
	public void healing(){
		
	}
	
	/*
	 * 
	 * Sound: nach 2-Mal streicheln: Schreien, danach zufrieden summen
	 * Bewegen: bei 2-Mal streicheln: kurzes r�ckfahren, danach stillstand
	 * Kopf: bei 2-Mal unzufrieden kopf runter: danach: Kopf hoch
	 * Display: Augen stabil.
	 * 
	 * Endet, wenn Button nach 3 Sekunden noch nicht wieder gedr�ckt wurde
	 * 
	 */
	public void washing(){
		
	}
	
}
