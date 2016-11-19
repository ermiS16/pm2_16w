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
	
	/*
	 * Erstellt einen neuen Lokfuehrer
	 * oder eine neue Lokfuehrerin
	 * @param aufgabe
	 * @param gleis
	 */
	public Lokfuehrer erzeugeLokfuehrer(int aufgabe, int gleis){
		Lokfuehrer neuerLokfuehrer = new Lokfuehrer(aufgabe,gleis);
		return neuerLokfuehrer;
	}// END METHOD

	@Override
	public void run(){
		//sinnvolle dinge
	}
	
}
