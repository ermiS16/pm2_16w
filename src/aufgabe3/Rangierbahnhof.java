package aufgabe3;

public class Rangierbahnhof {
	
/**
* Repraesentiert einen Rangierbahnhof 
* mit beliebig vielen Rangiergleisen
* fuer Zuege
* 
* @author Eric Misfeld, Simon Felske
* @version 15.11.2016
*
*/
	
	private Zug[] gleisAnzahl;
	
	/*
	 * Konstruktor
	 */
	Rangierbahnhof(){
		gleisAnzahl = new Zug[5];
	}
	
	/*
	 * Konstruktor mit Nutzereingabe
	 * @param anzahlGleise - wie viele Rangiergleise 
	 * es gibt. Muss >= 1 sein
	 */
	Rangierbahnhof(int anzahlGleise){
		if(anzahlGleise >= 1 && anzahlGleise < Integer.MAX_VALUE){
			gleisAnzahl = new Zug[anzahlGleise];
		}
	}
	
	/*
	 * Faehrt einen Zug auf das gegebene Rangiergleis ein
	 * Rangiergleis muss vorhanden und frei sein
	 * @param gleisNummer
	 */
	public void zugEinfahren(int gleisNummer){
		if(gleisNummer >= 0 && gleisNummer < gleisAnzahl.length && gleisAnzahl[gleisNummer] == null){
			
		}
	}
	
	/*
	 * Faehrt einen Zug aus dem gegebenen Rangiergleis aus
	 * Rangiergleis muss vorhanden und belegt sein
	 * @param gleisNummer
	 */
	public void zugAusfahren(int gleisNummer){
		if(gleisNummer >= 0 && gleisNummer < gleisAnzahl.length && gleisAnzahl[gleisNummer] != null){
			
		}
		gleisAnzahl[gleisNummer] = null;
	}

}
