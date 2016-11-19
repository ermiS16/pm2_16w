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
	 * oder eine neue Lokfuehrerin
	 */
	public Lokfuehrer erzeugeLokfuehrer(){
		int aufgabe = 0;
		int gleis = 0;
		int x = (int) (Math.random() * 10) + 1;
		if(x > 5){
			aufgabe = 1;
		}
		else{
			aufgabe = -1;
		}
		Lokfuehrer neuerLokfuehrer = new Lokfuehrer(aufgabe,gleis);
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
	
}
