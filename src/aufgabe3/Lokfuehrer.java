package aufgabe3;

/**
* Repraesentiert einen Lokfuehrer, 
* der Zuege in den Bahnhof ein- oder
* ausfaehrt
* 
* Praktikum TIPR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 19.11.2016
* Aufgabenblatt 3 | Aufgabe 2
*/

public class Lokfuehrer extends Thread{
	
	private int aufgabe;
	private int gleis;
	
	/*
	 * Konstruktor
	 */
	Lokfuehrer(){
		
	}
	
	/*
	 * Konstruktor
	 * @param aufgabe - was der Lokfuehrer machen soll
	 * muss 1 oder -1 sein
	 * @param gleis - welches Gleis gemeint ist
	 */
	Lokfuehrer(int aufgabe, int gleis){
		if((aufgabe == 1 || aufgabe == -1) && gleis >= 0){
			this.aufgabe = aufgabe;
			this.gleis = gleis;
		}// END IF
	}// END METHOD
	
	
	/*
	 * Arbeiten
	 */
	public void arbeiten(){
		//do things
	}
	
	/*
	 * Erstellt einen schoenen String
	 * mit allen notwendigen
	 * Informationen
	 */
	public String toSting(){
		String formatiert = "Lokführer ";
		if(aufgabe == 1){
			formatiert += "fährt einen Zug auf Gleis " + gleis + " ein.";
		}
		else{
			formatiert += "fährt einen Zug aus Gleis " + gleis + " aus";
		}
		return formatiert;
	}// END METHOD
	
	@Override
	public void run(){
		//do things
	}

}
