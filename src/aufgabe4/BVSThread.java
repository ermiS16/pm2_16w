package aufgabe4;

/**
* Die Klasse repraesentiert einen
* Thread fuer automatisches simulieren
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 13.12.2016
* Aufgabenblatt 4 | Aufgabe 2
*/

import aufgabe4.braitenbergvehikel.*;

public class BVSThread extends Thread{
	
	private BVSimulation sim;

	/**
	 * Konstruktor mit Nutzereingabe
	 * @param sim
	 */
	BVSThread(BVSimulation sim){
		this.sim = sim;
	}
	
	/**
	 * run
	 */
	public void run(){
		while(!interrupted()){
			try{
				sim.simulationsSchritt();
				sleep(200);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}//END WHILE
	}//END METHOD
}
