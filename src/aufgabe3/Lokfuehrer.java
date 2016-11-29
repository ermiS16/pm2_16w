package aufgabe3;

/**
* Repraesentiert einen Lokfuehrer, 
* der Zuege in einen Bahnhof ein- oder
* ausfaehrt
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 29.11.2016
* Aufgabenblatt 3 | Aufgabe 2
*/

public class Lokfuehrer extends Thread{
	
	private int aufgabe;
	private int gleis;
	private Zug zug;
	private Rangierbahnhof bahnhof;
	private int idNr;
	
	//Zaehlvariable fuer Instanzen von Lokfuehrer
	private static int instanzNr = 1;
	
	/*
	 * Erstellt ein neues Lokfuehrer-Objekt
	 * und ggf. ein neues Zug-Objekt
	 * 
	 * @param aufgabe - muss 0 (einfahren) oder 1 (ausfahren) sein
	 * @param gleis - Zielgleis
	 * @param bahnhof - Zielbahnhof
	 */
	Lokfuehrer(int aufgabe, int gleis, Rangierbahnhof bahnhof){
		if((aufgabe == 0 || aufgabe == 1) && gleis >= 0){
			this.aufgabe = aufgabe;
			this.gleis = gleis;
			this.bahnhof = bahnhof;
			idNr = instanzNr;
		}// END IF
		
		//Zug erzeugen wenn ein Zug eingefahren werden soll
		if(aufgabe == 0){
			zug = new Zug();
		}// END IF
		else{
			zug = null;
		}// END ELSE
		
		instanzNr++;
		System.out.println("LF"+ idNr +": " + "A" + aufgabe + " G" + gleis);
	}// END METHOD
	
	/*
	 * Setter fuer den Zug
	 * 
	 * @param abholZug - fuegt einen 
	 * (bestehenden) Zug hinzu,
	 * sofern keiner vorhanden ist
	 */
	public void setZug(Zug abholZug){
		if( zug == null){
			zug = abholZug;
		}// END IF
	}// END METHOD
	
	/*
	 * Getter fuer den Zug
	 * 
	 * @return zug
	 */
	public Zug getZug(){
		return zug;
	}// END METHOD
	
	/*
	 * Getter fuer die Aufgabe
	 * 
	 * @return aufgabe
	 */
	public int getAufgabe(){
		return aufgabe;
	}// END METHOD
	
	/*
	 * Getter fuer das Gleis
	 * 
	 * @return gleis
	 */
	public int getGleis(){
		return gleis;
	}// END METHOD
	
	/*
	 * Getter fuer idNr
	 * 
	 * @return idNr - Instanznummer des Lokfuehrers
	 */
	public int getidNr(){
		return idNr;
	}// END METHOD

	/*
	 * Erstellt einen formatierten String
	 * mit allen notwendigen Informationen
	 * 
	 * @return formatiert - String mit allen Lokfuehrer-Informationen
	 */
	public String toString(){
		String formatiert = "Lokführer " + idNr + " ";
		if(aufgabe == 0){
			formatiert += "hat einen Zug auf Gleis " + gleis + " eingefahren";
		}
		else{
			formatiert += "hat einen Zug aus Gleis " + gleis + " ausgefahren";
		}
		return formatiert;
	}// END METHOD
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run(){
		try{
			bahnhof.arbeiten(this);
			System.out.println(toString());
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}// END METHOD

}
