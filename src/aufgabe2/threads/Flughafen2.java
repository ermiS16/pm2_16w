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
		anzahlFlugzeuge = flugzeugListe.size();
	}
	
	/*
	 * Konstruktor mit Nutzereingabe
	 * @param inputFlugzeugListe
	 */
	public Flughafen2(List<Flugzeug> inputFlugzeugListe){
		flugzeugListe = inputFlugzeugListe;
		anzahlFlugzeuge = inputFlugzeugListe.size();
	}
	
	/*
	 * Landet ein Flugzeug
	 * @param fz - das zu landende Flugzeug
	 */
	public synchronized void landen(Flugzeug fz) throws InterruptedException{
		fz.setStatus(Status.IM_LANDEANFLUG);
		Thread.sleep(LANDE_DAUER_ZEIT);
		fz.setStatus(Status.GELANDET);
		fz.interrupt();
		flugzeugListe.remove(fz);
		System.out.println("XXXX");
		System.out.println(flugzeugListe.size());
		System.out.println(anzahlFlugzeuge);
	}
	
	/*
	 * Liefert den Namen des Flughafens zurueck
	 * @return - Name des Flughafens
	 */
	public String toString(){
		return "Generic International";
	}
	
	@Override
	public void run(){
		int zeit = 0;
		//int umrechnungsFaktor = 1000;
		int echteZeit = 0;
		int syncroZeit = 0;
		
		while(!isInterrupted() && true){
			try {
				Thread.sleep(WARTE_ZEIT);
				zeit += WARTE_ZEIT;
				//System.out.println("----");
				//System.out.println(flugzeugListe.size());
				//System.out.println("aF: " + anzahlFlugzeuge);
				//System.out.println("----");
				if(zeit%1000 == 0){ //smoothing time
					echteZeit = zeit/1000;
					System.out.println("\nZeit: " + echteZeit);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				interrupt();
			}
			synchronized(flugzeugListe){
				if(anzahlFlugzeuge > flugzeugListe.size()){
					Flugzeug neuesFlugzeug = erzeugeFlugzeug(echteZeit);
					flugzeugListe.add(neuesFlugzeug);
					//flugzeugListe.add(erzeugeFlugzeug(echteZeit));
					System.out.println("Flugzeug erzeugt: " + neuesFlugzeug.toString());
					neuesFlugzeug.start();
				}//END IF
			}
				if(echteZeit > syncroZeit){
					syncroZeit = echteZeit;
					for(int i = 0; i < flugzeugListe.size(); i++){
						flugzeugListe.get(i).setZeit(echteZeit);
						System.out.println(flugzeugListe.get(i).toString());
						if(!flugzeugListe.get(i).isInterrupted() && !flugzeugListe.get(i).isAlive()){
							flugzeugListe.remove(i);
						}
					}
				}
			
		}
	}
	
	/*
	 * Erzeugt ein zufaelliges neues Flugzeug
	 * @param currentTime
	 */
	public Flugzeug erzeugeFlugzeug(int currentTime){
		int zufall = (int) (Math.random() * 3);
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
		Flughafen2 fh = new Flughafen2();
		List<Flugzeug> liste = new ArrayList<Flugzeug>();
		Flugzeug fz1 = new Flugzeug("Lufthansa 1", 1, fh, 0);
		Flugzeug fz2 = new Flugzeug("Air Berlin 1", 2, fh, 0);
		Flugzeug fz3 = new Flugzeug("Air Berlin 2", 3, fh, 0);
		Flugzeug fz4 = new Flugzeug("Air Berlin 3", 4, fh, 0);
		liste.add(fz1);
		liste.add(fz2);
		liste.add(fz3);
		liste.add(fz4);
		Thread flughafen = new Flughafen2(liste);
		flughafen.start();
		fz1.start();
		fz2.start();
		fz3.start();
		fz4.start();
		System.out.println("Flughafen 2 hat Betrieb aufgenommen");
		System.out.println("Zeit: 0");
		//System.out.println(fz1.toString());
		//System.out.println(fz2.toString());
	}
	
}
