package aufgabe3;

/**
* Repraesentiert einen Rangierbahnhof 
* mit beliebig vielen Rangiergleisen
* fuer Zuege
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 30.11.2016
* Aufgabenblatt 3 | Aufgabe 1
*/

public class Rangierbahnhof{
	
	private Zug[] zugAufGleis;
	
	/**
	 * Konstruktor
	 */
	public Rangierbahnhof(){
		zugAufGleis = new Zug[3];
	}
	
	/**
	 * Konstruktor mit Nutzereingabe
	 * 
	 * @param gleisAnzahl - Anzahl der Gleise,
	 * mindestens 1 Gleis
	 */
	public Rangierbahnhof(int gleisAnzahl){
		if(gleisAnzahl > 0){
			zugAufGleis = new Zug[gleisAnzahl];
		}// END IF
	}// END METHOD
	
	/**
	 * Gibt die Anzahl der Gleise zurueck
	 * 
	 * @return gleisAnzahl.length - Anzahl der Gleise
	 */
	public int getGleisAnzahl(){
		return zugAufGleis.length;
	}// END METHOD
	
	/**
	 * Gibt das Array zurueck
	 * 
	 * @return zugAufGleis
	 */
	public Zug[] getZugArray(){
		return zugAufGleis;
	}
	
	/**
	 * Nimmt einen Lokfuehrer entgegen,
	 * der je nach Aufgabe weitergereicht wird
	 * 
	 * @param lf - Lokfuehrer der seine Arbeit machen moechte
	 */
	public synchronized void arbeiten(Lokfuehrer lf) throws InterruptedException{
		switch(lf.getAufgabe()){
		
		case 0: zugEinfahrenAuf(lf.getGleis(),lf.getZug(),lf);
			break;
				
		case 1:zugAusfahrenAuf(lf.getGleis(),lf);
			break;
				
		default: System.out.println("Error");
			lf.interrupt();
			break;
		}// END SWITCH
	}// END METHOD
	
	/**
	 * Nimmt einen Lokfuehrer, ein Zielgleis und einen Zug
	 * entgegen. Der Zug soll auf das Rangiergleis einfahren,
	 * dazu muss das Rangiergleis frei sein,
	 * Sonst wird die Aktion pausiert
	 * 
	 * @param gleis - Zielgleis fuer den einfahrenden Zug
	 * @param zug - einfahrender Zug
	 * @param lf - Lokfuehrer
	 */
	public synchronized void zugEinfahrenAuf(int gleis, Zug zug, Lokfuehrer lf){
		while(zugAufGleis[gleis] != null){
			try{
				wait();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}// END WHILE
		
		zugAufGleis[gleis] = zug;
		notifyAll();
		
	}// END METHOD
	
	/**
	 * Nimmt einen Lokfuehrer und ein Zielgleis entgegen.
	 * Der Zug soll aus einem Rangiergleis ausfahren.
	 * Das Rangiergleis muss belegt sein,
	 * sonst wird die Aktion pausiert
	 * 
	 * @param gleis - Gleis von dem ein Zug ausgefahren werden soll
	 * @param lf - Lokfuehrer
	 */
	public synchronized void zugAusfahrenAuf(int gleis, Lokfuehrer lf){
		while(zugAufGleis[gleis] == null){
			try{
				wait();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}// END WHILE
		
		lf.setZug(zugAufGleis[gleis]);
		zugAufGleis[gleis] = null;
		notifyAll();
		
	}// END METHOD
}