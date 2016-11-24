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
	private Zug zug;
	private Rangierbahnhof bahnhof;
	
	/*
	 * Konstruktor
	 */
	Lokfuehrer(){
		
	}
	
	/*
	 * Konstruktor
	 * @param aufgabe - muss 0 (einfahren) oder 1 (ausfahren) sein
	 * @param gleis - welches Gleis gemeint ist
	 */
	Lokfuehrer(int aufgabe, int gleis, Rangierbahnhof bahnhof){
		if((aufgabe == 0 || aufgabe == 1) && gleis >= 0){
			this.aufgabe = aufgabe;
			this.gleis = gleis;
			this.bahnhof = bahnhof;
		}// END IF
		//Zug erzeugen wenn ein Zug eingefahren werden soll
		if(aufgabe == 0){
			this.zug = new Zug();
		}// END IF
	}// END METHOD
	
	
	/*
	 * Arbeiten
	 */
	public void arbeiten(){
		switch(aufgabe){
		case 0: bahnhof.zugEinfahrenAuf(gleis);
			System.out.println(toString());
			break;
		case 1: bahnhof.zugEinfahrenAuf(gleis);
			System.out.println(toString());
			break;
		default: System.out.println("Error");
			break;
		}
	}
	
	/*
	 * @param zug
	 */
	public Zug getZug(){
		return zug;
	}
	
	/*
	 * Erstellt einen formatierten String
	 * mit allen notwendigen
	 * Informationen
	 */
	public String toString(){
		String formatiert = "Lokführer ";
		if(aufgabe == 0){
			formatiert += "hat einen Zug auf Gleis " + gleis + " eingefahren.";
		}
		else{
			formatiert += "hat einen Zug aus Gleis " + gleis + " ausgefahren.";
		}
		return formatiert;
	}// END METHOD
	
	@Override
	public void run(){
		//do things
	}

}
