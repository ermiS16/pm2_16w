package aufgabe2.threads;

import java.util.ArrayList;
import java.util.List;

/**
* Repraesentiert einen Flughafen.
* Es wird der Betrieb eines Flughafens simuliert,
* mit landenden und startenden Flugzeugen, die
* sich eine Landebahn "teilen" muessen 
* 
* @author Eric Misfeld, Simon Felske
* @version 15.11.2016
*
*/

public class Flughafen2 extends Thread{
	private final int WARTE_ZEIT = 500;
	private final int LANDE_DAUER_ZEIT = 1500;
	
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
	 * Konstruktor mit Nutzereingabe
	 * @param inputFlugzeugListe
	 */
	public Flughafen2(List<Flugzeug> inputFlugzeugListe){
		flugzeugListe = inputFlugzeugListe;
		anzahlFlugzeuge = 0;
	}
	
	/*
	 * Landet ein Flugzeug
	 * @param fz - das zu landende Flugzeug
	 */
	public synchronized void landen(Flugzeug fz) throws InterruptedException{
		//flugzeug set status auf imLandeanflug
		//sleep fz LANDE_DAUER_ZEIT
		//flugzeug set status auf gelandet
		//entferne fz aus flugzeugListe
	}
	
	@Override
	public void run(){
		int zeit = 0;
		int umrechnungsFaktor = 1000;
		int echteZeit = 0;
		
		while(!isInterrupted() && true){
			try {
				Flughafen2.sleep(WARTE_ZEIT);
				zeit += WARTE_ZEIT;
				if(zeit%umrechnungsFaktor == 0){ //smoothing time
					echteZeit = zeit/umrechnungsFaktor;
					System.out.println("\nZeit: " + echteZeit);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				interrupt();
			}
			if(anzahlFlugzeuge > flugzeugListe.size()){
				Flugzeug neuesFlugzeug = erzeugeFlugzeug(echteZeit);
				flugzeugListe.add(neuesFlugzeug);
				System.out.println("Flugzeug erzeugt: " + neuesFlugzeug.toString());
				neuesFlugzeug.start();
			}//END IF
			for(int i = 0; i<flugzeugListe.size();i++){
				flugzeugListe.get(i).setZeit(echteZeit);
			}
			
		}
	}
	
	/*
	 * Erzeugt ein zufaelliges neues Flugzeug
	 * @param currentTime
	 */
	public Flugzeug erzeugeFlugzeug(int currentTime){
		int zufall = (int) (Math.random() * 2);
		String luftHansa = "Lufthansa ";
		String airBerlin = "Air Berlin ";
		String germanWings = "German Wings ";
		int flugId = (int) ((Math.random() + 1) * 1000);
		int flugdauer = (int) (Math.random() * 10) + 1; //flugdauer darf nicht 0 sein
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

	public static void main(String[] args){
		Thread flughafen = new Thread(new Flughafen2());
		flughafen.start();
	}
	
}
