package aufgabe3;

import java.util.Observable;

/**
* Repraesentiert einen Rangierbahnhof 
* mit beliebig vielen Rangiergleisen
* fuer Zuege
* 
* Praktikum TIPR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 28.11.2016
* Aufgabenblatt 3 | Aufgabe 1
*/

public class Rangierbahnhof extends Observable{
	
	private Zug[] zugAufGleis;
	
	
	/*
	 * Konstruktor
	 */
	Rangierbahnhof(){
		zugAufGleis = new Zug[3];
	}
	
	
	/*
	 * Konstruktor mit Nutzereingabe
	 * 
	 * @param gleisAnzahl - Anzahl der Gleise,
	 * mindestens 1 Gleis
	 */
	Rangierbahnhof(int gleisAnzahl){
		if(gleisAnzahl > 0){
			zugAufGleis = new Zug[gleisAnzahl];
		}// END IF
	}// END METHOD
	
	
	/*
	 * Gibt die Anzahl der Gleise zurueck
	 * 
	 * @return gleisAnzahl.length - Anzahl der Gleise
	 */
	public int getGleisAnzahl(){
		return zugAufGleis.length;
	}// END METHOD
	
	
	
	/*
	 * Nimmt einen Lokfuehrer entgegen,
	 * der je nach Aufgabe weitergereicht wird
	 * 
	 * @param lf - Lokfuehrer der seine Arbeit machen moechte
	 */
	public synchronized void arbeiten(Lokfuehrer lf)throws InterruptedException{
		switch(lf.getAufgabe()){
		case 0: zugEinfahrenAuf(lf.getGleis(),lf.getZug(),lf);
			break;
				
		case 1:zugAusfahrenAuf(lf);
			break;
				
		default: System.out.println("Error");
			lf.interrupt();
			break;
		}// END SWITCH
	}// END METHOD
	
	
	/*
	 * Nimmt einen Lokfuehrer entgegen, der
	 * einen Zug auf ein Rangiergleis einfaehrt.
	 * Rangiergleis muss frei sein,
	 * sonst wird der Lokfuehrer pausiert
	 * 
	 * @param lf
	 */
	public synchronized void zugEinfahrenAuf(int gleis, Zug zug, Lokfuehrer lf)throws InterruptedException{
		while(zugAufGleis[gleis] != null){
			try{
				wait();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}// END WHILE
		
		zugAufGleis[gleis] = zug;
		lf.setKill(1);
		notifyAll();
	}// END METHOD
	
	
	/*
	 * Nimmt einen Lokfuehrer entgegen, der
	 * einen Zug aus einem Rangiergleis ausfaehrt.
	 * Rangiergleis muss belegt sein,
	 * sonst wird der Lokfuehrer pausiert
	 * 
	 * @param lf
	 */
	public synchronized void zugAusfahrenAuf(Lokfuehrer lf) throws InterruptedException{
		while(zugAufGleis[lf.getGleis()] == null){
			try{
				wait();
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}// END WHILE
		
		lf.setZug(zugAufGleis[lf.getGleis()]);
		zugAufGleis[lf.getGleis()] = null;
		lf.setKill(1);
		notifyAll();
	}// END METHOD
	
}