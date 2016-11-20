package aufgabe2.threads;

import java.util.ArrayList;
import java.util.List;

/**
* Repraesentiert einen Flughafen.
* Es wird der Betrieb eines Flughafens simuliert,
* mit landenden und startenden Flugzeugen, die
* sich eine Landebahn "teilen" muessen 
* 
 * Praktikum TIPR2, WS2016/2017
 * Praktikumsgruppe Nr. 4
 * @author Eric Misfeld, Simon Felske
 * @version 19.11.2016
 * Aufgabenblatt 2 | Aufgabe 3
 */

public class Flughafen2 extends Thread{
	private final int WARTE_ZEIT = 500;
	private final int LANDE_DAUER_ZEIT = 3000;
	
	/*
	 * Alternativer Wert fuer 2. Implementation
	 */
	//private final int LANDE_DAUER_ZEIT = 1500;
	
	private final String FLUGHAFEN_NAME = "Generic International"; //Name oder Kennung des Flughafens
	
	private List<Flugzeug> flugzeugListe;
	private int anzahlFlugzeuge;
	
	/*
	 * Konstruktor
	 */
	public Flughafen2(){
		flugzeugListe = new ArrayList<Flugzeug>();
		anzahlFlugzeuge = 0;
	}

	/*
	 * Fuegt dem Flughafen ein zusaetzliches
	 * Flugzeug hinzu
	 * @param fz - das hinzuzufuegende Flugzeug
	 */
	public void ergaenzeFlugzeug(Flugzeug fz){
		flugzeugListe.add(fz);
		anzahlFlugzeuge++;
	}
	
	/*
	 * Erzeugt ein neues Flugzeug-Objekt,
	 * mit zufaelliger ID und Flugdauer
	 * @param currentTime - aktuelle "Ganzzeit"
	 */
	public Flugzeug erzeugeFlugzeug(int currentTime){
		int zufall = (int) (Math.random() * 3);
		String luftHansa = "Lufthansa ";
		String airBerlin = "Air Berlin ";
		String germanWings = "German Wings ";
		int flugId = (int) ((Math.random() + 1) * 1000);
		int flugdauer = (int) (Math.random() * 10) + 1; //flugdauer muss >0 sein
		Flughafen2 zielort = this;
		
		switch(zufall){
		case 0:	Flugzeug neuesFlugzeug = new Flugzeug(luftHansa+flugId, flugdauer, zielort, currentTime);
				return neuesFlugzeug;
		case 1: Flugzeug neuesFlugzeug2 = new Flugzeug(airBerlin+flugId, flugdauer, zielort, currentTime);
				return neuesFlugzeug2;
		case 2: Flugzeug neuesFlugzeug3 = new Flugzeug(germanWings+flugId, flugdauer, zielort, currentTime);
				return neuesFlugzeug3;
		default: Flugzeug neuesFlugzeugDefault 
					= new Flugzeug("Unbekannter Flug", flugdauer, zielort, currentTime);
				return neuesFlugzeugDefault;
		}// END SWITCH
	}// END METHOD
	
	/*
	 * Landet ein Flugzeug
	 * @param fz - das zu landende Flugzeug
	 */
	public synchronized void landen(Flugzeug fz) throws InterruptedException{
		fz.setStatus(Status.IM_LANDEANFLUG);
		Thread.sleep(LANDE_DAUER_ZEIT);
		fz.setStatus(Status.GELANDET);
		System.out.println(fz.toString());
		fz.interrupt();
		flugzeugListe.remove(fz);
	}
	
	/*
	 * Liefert den Namen des Flughafens zurueck
	 * @return - Name des Flughafens
	 */
	public String toString(){
		return FLUGHAFEN_NAME;
	}
	
	@Override
	public void run(){
		int zeit = 0;
		int echteZeit = 0;
		int syncroZeit = 0;
		
		while(!isInterrupted() && true){
			try {
				Thread.sleep(WARTE_ZEIT);
				zeit += WARTE_ZEIT;
				if(zeit%1000 == 0){ //Zeitglaettung
					echteZeit = zeit/1000;
					System.out.println("\nZeit: " + echteZeit);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				interrupt();
			}
			
			// Startet ein neues Flugzeug, wenn weniger 
			// Flugzeuge aktiv sind als moeglich waere
			
			if(anzahlFlugzeuge > flugzeugListe.size()){
				Flugzeug neuesFlugzeug = erzeugeFlugzeug(echteZeit);
				flugzeugListe.add(neuesFlugzeug);
				System.out.println("Flugzeug erzeugt: " + neuesFlugzeug.toString());
				neuesFlugzeug.start();
			}
			
			synchronized(flugzeugListe){
				if(echteZeit > syncroZeit){
					syncroZeit = echteZeit;
					for(int i = 0; i < flugzeugListe.size(); i++){
						flugzeugListe.get(i).setZeit(echteZeit);
						System.out.println(flugzeugListe.get(i).toString());
					}// END FOR
				}// END IF
			}// END SYNCHRONIZED
		}// END WHILE
	}// END METHOD

	/*
	 * Alternative Implementation
	 * Zu verwenden mit LANDE_DAUER_ZEIT = 1500;
	 * Die Main-Methode ist anzupassen,
	 * die Konsolenausgabe des 0. Zeitpunktes erfolgt
	 * von selbst
	 */
	
//	@Override
//	public void run(){
//		int zeit = 0;
//		
//		while(!isInterrupted() && true){
//			System.out.println("\nZeit: " + zeit);
//			synchronized(flugzeugListe){
//					for(int i = 0; i < flugzeugListe.size(); i++){
//						flugzeugListe.get(i).setZeit(zeit);
//						if(!flugzeugListe.get(i).isGelandet()){
//							System.out.println(flugzeugListe.get(i).toString());
//						}// END IF
//					}// END FOR
//			}
//			synchronized(flugzeugListe){
//					if(anzahlFlugzeuge > flugzeugListe.size()){
//						Flugzeug neuesFlugzeug = erzeugeFlugzeug(zeit);
//						flugzeugListe.add(neuesFlugzeug);
//						System.out.println("Flugzeug erzeugt: " + neuesFlugzeug.toString());
//						neuesFlugzeug.start();
//					}//END IF
//			}
//			zeit++;
//			try {
//				Thread.sleep(WARTE_ZEIT);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//				interrupt();
//			}
//		}// END WHILE
//	}// END METHOD
	
	public static void main(String[] args){
		Flughafen2 fh = new Flughafen2();
		Flugzeug fz1 = new Flugzeug("Lufthansa 1", 2, fh, 0);
		Flugzeug fz2 = new Flugzeug("Air Berlin 1", 2, fh, 0);
		//Flugzeug fz3 = new Flugzeug("Air Berlin 2", 3, fh, 0);
		//Flugzeug fz4 = new Flugzeug("Air Berlin 3", 4, fh, 0);
		fh.ergaenzeFlugzeug(fz1);
		fh.ergaenzeFlugzeug(fz2);
		//fh.ergaenzeFlugzeug(fz3);
		//fh.ergaenzeFlugzeug(fz4);
		fh.start();
		fz2.start();
		fz1.start();
		//fz2.start();
		//fz3.start();
		//fz4.start();
		System.out.println("Flughafen 2 hat Betrieb aufgenommen");
		System.out.println("Zeit: 0");
		System.out.println(fz1.toString());
		System.out.println(fz2.toString());
	}
}
