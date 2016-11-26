package aufgabe3;

/**
* Repraesentiert einen Lokfuehrer, 
* der Zuege in den Bahnhof ein- oder
* ausfaehrt
* 
* Praktikum TIPR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 26.11.2016
* Aufgabenblatt 3 | Aufgabe 2
*/

public class Lokfuehrer extends Thread{
	
	private int aufgabe;
	private int gleis;
	private Zug zug;
	private Rangierbahnhof bahnhof;
	private static int instanzNr = 0;
	
	
	/*
	 * Konstruktor
	 * @param aufgabe - muss 0 (einfahren) oder 1 (ausfahren) sein
	 * @param gleis - Zielgleis
	 * @param bahnhof - Zielbahnhof
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
		else{
			zug = null;
		}
		instanzNr++;
		System.out.println("LF: " + "A" + aufgabe + " G" + gleis);
	}// END METHOD
	
	
	/*
	 * Setter fuer zug
	 * @param abholZug
	 */
	public void setZug(Zug abholZug){
		if( zug == null){
			zug = abholZug;
		}// END IF
	}// END METHOD
	
	
	/*
	 * Getter fuer zug
	 * @return zug
	 */
	public Zug getZug(){
		return zug;
	}// END METHOD
	
	
	/*
	 * Getter fuer aufgabe
	 * @return aufgabe
	 */
	public int getAufgabe(){
		return aufgabe;
	}// END METHOD
	
	
	/*
	 * Getter fuer gleis
	 * @return gleis
	 */
	public int getGleis(){
		return gleis;
	}// END METHOD
	
	
	/*
	 * Erstellt einen formatierten String
	 * mit allen notwendigen Informationen
	 */
	public String toString(){
		String formatiert = "Lokführer " + instanzNr + " ";
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
		try{
			bahnhof.arbeiten(this);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

}
