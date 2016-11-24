package aufgabe3;

/**
* Simulation eines aktiven Rangier-
* bahnhofes mit Zuegen und
* Lokfuehrern
* 
* Praktikum TIPR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 19.11.2016
* Aufgabenblatt 3 | Aufgabe 3
*/

public class Simulation implements Runnable{
	
	private final int INTERVALL = 500;
	
	/*
	 * Erstellt einen neuen Lokfuehrer
	 */
	public Lokfuehrer erzeugeLokfuehrer(){
		int gleis = (int) (Math.random() * 5);
		int aufgabe = (int) (Math.random() * 2);
		Rangierbahnhof bahnhof = null;
		Lokfuehrer neuerLokfuehrer = new Lokfuehrer(aufgabe, gleis, bahnhof);
		return neuerLokfuehrer;
	}// END METHOD

	@Override
	public void run(){
		//sinnvolle dinge
		while(true){
			try {
				Thread.sleep(INTERVALL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]){
		
	}
}
