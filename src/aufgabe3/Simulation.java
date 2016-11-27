package aufgabe3;

import java.util.Observable;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;



/**
* Simulation eines aktiven Rangier-
* bahnhofes mit Zuegen und
* Lokfuehrern
* 
* Praktikum TIPR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 26.11.2016
* Aufgabenblatt 3 | Aufgabe 3
*/

public class Simulation extends Observable implements Runnable{

	
	private final int INTERVALL = 1000;
	
	/*
	 * Erstellt ein neues Lokfuehrer-Objekt
	 * Generiert zufaellige Werte fuer Aufgabe und Gleis
	 * @param bahnhof - Zielbahnhof
	 */
	public Lokfuehrer erzeugeLokfuehrer(Rangierbahnhof bahnhof){
		int gleis = (int) (Math.random() * 3);
		int aufgabe = (int) (Math.random() * 2);
		//Rangierbahnhof bahnhof = null;
		Lokfuehrer neuerLokfuehrer = new Lokfuehrer(aufgabe, gleis, bahnhof);
		return neuerLokfuehrer;
	}// END METHOD

	@Override
	public void run(){
		Rangierbahnhof tbhf = new Rangierbahnhof();
		int q = 0;
		while(true){
			try {
				q++;
				System.out.println("Zeit: " + q);
				Lokfuehrer x = erzeugeLokfuehrer(tbhf);
				x.start();
				Thread.sleep(INTERVALL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
