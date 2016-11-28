package aufgabe3;

import java.util.Observable;




/**
* Simulation eines aktiven Rangier-
* bahnhofes mit Zuegen und
* Lokfuehrern
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 28.11.2016
* Aufgabenblatt 3 | Aufgabe 3
*/

public class Simulation extends Observable implements Runnable{

	//Zeitdauer fuer Intervalllaenge
	private final int INTERVALL = 1000;
	
	private Rangierbahnhof testBahnhof;
	
	/*
	 * Konstruktor
	 */
	//TODO Observer richtig
	public Simulation(){
		testBahnhof = new Rangierbahnhof();
		//testBahnhof.addObserver(arg0);
	}// END METHOD
	
	/*
	 * Erstellt ein neues Lokfuehrer-Objekt
	 * Generiert zufaellige Werte fuer Aufgabe und Gleis
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
				System.out.println("\nZeit: " + zeit);
				Lokfuehrer x = erzeugeLokfuehrer(testBahnhof);
				x.start();
				zeit++;
				Thread.sleep(INTERVALL);	
			} catch (InterruptedException e) {
//				System.out.println("Simulation wurde geweckt");
				Thread.currentThread().interrupt();
			}
		}// END WHILE
	}// END METHOD
	
}
