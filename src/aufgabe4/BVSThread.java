package aufgabe4;

/**
* BLA BLA
*TODO
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 12.12.2016
* Aufgabenblatt 4 | Aufgabe 2
*/

import aufgabe4.braitenbergvehikel.*;
import aufgabe4.view.*;

public class BVSThread extends Thread{
	
	private BVSimulation sim;
	private BVCanvas canvas;

	/**
	 * Konstruktor mit Nutzereingabe
	 * @param sim
	 * @param canvas
	 */
	BVSThread(BVSimulation sim, BVCanvas canvas){
		this.sim = sim;
		this.canvas = canvas;
	}
	
	/**
	 * run
	 */
	public void run(){
		while(!interrupted()){
			try{
				sim.simulationsSchritt();
				canvas.zeichneSimulation();
				sleep(200);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}//END WHILE
	}//END METHOD
}
