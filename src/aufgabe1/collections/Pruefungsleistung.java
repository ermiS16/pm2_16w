package aufgabe1.collections;

/**
 * Klasse repraesentiert eine Pruefungsleistung.
 * @author Eric Misfeld, Simon Felske
 * @version 11.10.2016
 */


public class Pruefungsleistung {
	private String modul;
	private int note;
	
	
	/*
	 * Erstellt ein neues Pruefungsleistungsobject
	 * @param inputModul
	 * @param inputNote
	 */
	public Pruefungsleistung(String inputModul, int inputNote){
		//Modul darf nicht leer oder NULL sein
		//Note zwischen 0 und 15
		if(inputModul != "" && inputModul != null && inputNote >= 0 && inputNote <=15){
			modul = inputModul.trim(); //remove spaces from front and back
			note = inputNote;
		}
	}
	
	/*
	 * Liefert einen formatierten String zurueck
	 * @return String
	 */
	public String toString(){
		return modul + ": " + note;
	}
}
