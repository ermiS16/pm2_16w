package aufgabe3;

import java.util.Arrays;
import java.util.Observable;

import javafx.application.Application;

/**
* Simulation eines aktiven Rangier-
* bahnhofes mit Zuegen und
* Lokfuehrern
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 30.11.2016
* Aufgabenblatt 3 | Aufgabe 3
*/

public class Simulation extends Observable implements Runnable{

	//Zeitdauer fuer Intervalllaenge
	private final int INTERVALL = 500;
	
	private Rangierbahnhof testBahnhof;
	private Zug[] syncZuege;
	
	public Simulation(int anzahlGleise){
		testBahnhof = new Rangierbahnhof(anzahlGleise);
		syncZuege = new Zug[testBahnhof.getGleisAnzahl()];
	}
	
	/**
	 * Konstruktor
	 */
	public Simulation(){
		testBahnhof = new Rangierbahnhof();
		syncZuege = new Zug[testBahnhof.getGleisAnzahl()];
	}// END METHOD
	
	/**
	 * Erstellt ein neues Lokfuehrer-Objekt
	 * Generiert zufaellige Werte fuer aufgabe und gleis
	 * 
	 * @param bahnhof - Zielbahnhof
	 * @return neuerLokfuehrer
	 */
	public Lokfuehrer erzeugeLokfuehrer(Rangierbahnhof bahnhof){
		int gleis = (int) ((Math.random() * bahnhof.getGleisAnzahl()));
		int aufgabe = (int) (Math.random() * 2);
		Lokfuehrer neuerLokfuehrer = new Lokfuehrer(aufgabe, gleis, bahnhof);
		return neuerLokfuehrer;
	}// END METHOD

	/**
	 * Ausfuehrung des Threads Simulation
	 */
	@Override
	public void run(){
		int zeit = 1;
		while(!Thread.currentThread().isInterrupted()){
			try {
				if(!Arrays.equals(syncZuege, testBahnhof.getZugArray())){
					Zug[] switc = testBahnhof.getZugArray();
					for(int i = 0; i < testBahnhof.getGleisAnzahl(); i++){
						syncZuege[i] = switc[i];
					}// END FOR
				}// END IF
				setChanged();
				notifyObservers(syncZuege);
				System.out.println("\nZeit: " + zeit);
				Lokfuehrer neuerLf = erzeugeLokfuehrer(testBahnhof);
				neuerLf.start();
				zeit++;
				
				setChanged();
				notifyObservers(syncZuege);
				Thread.sleep(INTERVALL);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}// END WHILE
	}// END METHOD
	
	public static void main(String[] args){
		Application.launch(Gui.class);
	}// END METHOD
}
