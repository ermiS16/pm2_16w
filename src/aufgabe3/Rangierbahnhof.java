package aufgabe3;



/**
* Repraesentiert einen Rangierbahnhof 
* mit beliebig vielen Rangiergleisen
* fuer Zuege
* 
* Praktikum TIPR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 19.11.2016
* Aufgabenblatt 3 | Aufgabe 1
*/

public class Rangierbahnhof{
	
	private Zug[] gleisAnzahl;
	private int zugAnzahl = 0;
	
	//private Object monitorX = new Object();
	
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
	 * Gibt die Anzahl der Rangiergleise zurueck
	 * @return gleisAnzahl.length
	 */
	public int getGleisAnzahl(){
		return gleisAnzahl.length;
	}
	
	/*
	 * Gibt die Anzahl der Zuege im
	 * Bahnhof zurueck
	 * @return zugAnzahl
	 */
	public int getZugAnzahl(){
		return zugAnzahl;
	}
	
	/*
	 * Faehrt einen Zug auf das gegebene Rangiergleis ein
	 * Rangiergleis muss vorhanden und frei sein
	 * @param zug
	 * @param gleisNummer
	 */
	//Zug zug, mit oder ohne?
	public void zugEinfahrenAuf(Zug zug, int gleisNummer){
		synchronized(gleisAnzahl){
			if(gleisNummer >= 0 && gleisNummer < gleisAnzahl.length 
					&& gleisAnzahl[gleisNummer] == null){
				//gleisAnzahl[gleisNummer] = zug;
				zugAnzahl++;
			}// END IF
		}// END SYNCRO
	}// END METHOD
	
	/*
	 * Faehrt einen Zug aus dem gegebenen Rangiergleis aus
	 * Rangiergleis muss vorhanden und belegt sein
	 * @param gleisNummer
	 */
	public void zugAusfahrenAuf(int gleisNummer){
		synchronized(gleisAnzahl){
			if(gleisNummer >= 0 && gleisNummer < gleisAnzahl.length 
					&& gleisAnzahl[gleisNummer] != null){
				gleisAnzahl[gleisNummer] = null;
				zugAnzahl--;
			}// END IF
		}// END SYNCRO
	}// END METHOD
	
	
	public void arbeiten(Lokfuehrer lf) throws InterruptedException{
		switch(lf.getAufgabe()){
		case 0: zugEinfahrenAuf(lf.getZug(), lf.getGleis());
			System.out.println(lf.toString());
			break;
		case 1:zugAusfahrenAuf(lf.getGleis());
			System.out.println(lf.toString());
			break;
		default: System.out.println("Error");
			break;
		}
	}
}
