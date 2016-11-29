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
* @version 29.11.2016
* Aufgabenblatt 3 | Aufgabe 3
*/

public class Simulation extends Observable implements Runnable{

	//Zeitdauer fuer Intervalllaenge
	private final int INTERVALL = 1000;
	
	private Rangierbahnhof testBahnhof;
	private Zug[] syncZuege;
	
	/*
	 * Konstruktor
	 */
	//TODO Observer richtig
	public Simulation(){
		testBahnhof = new Rangierbahnhof();
		syncZuege = new Zug[testBahnhof.getGleisAnzahl()];
		//testBahnhof.addObserver(arg0);
	}// END METHOD
	
	/*
	 * Erstellt ein neues Lokfuehrer-Objekt
	 * Generiert zufaellige Werte fuer aufgabe und gleis
	 * 
	 * @param bahnhof - Zielbahnhof
	 * @return neuerLokfuehrer
	 */
	public Lokfuehrer erzeugeLokfuehrer(Rangierbahnhof bahnhof){
		int gleis = (int) (Math.random() * 3);
		int aufgabe = (int) (Math.random() * 2);
		Lokfuehrer neuerLokfuehrer = new Lokfuehrer(aufgabe, gleis, bahnhof);
		return neuerLokfuehrer;
	}// END METHOD

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
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
				
				System.out.println("\nZeit: " + zeit);
				Lokfuehrer neuerLf = erzeugeLokfuehrer(testBahnhof);
				neuerLf.start();
				zeit++;
				
				Thread.sleep(INTERVALL);
				setChanged();
				notifyObservers(syncZuege);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}// END WHILE
	}// END METHOD
	
	public static void main(String[] args){
		Gui newGui = new Gui();
		Application.launch(newGui.getClass());
	}
	
}
