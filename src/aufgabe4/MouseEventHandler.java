package aufgabe4;

/**
* Die Klasse repraesentiert einen
* Eventhandler fuer MouseEvent(s),
* damit der Zielpunkt der Simulation
* durch den Nutzer gesetzt werden kann
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 13.12.2016
* Aufgabenblatt 4 | Aufgabe 4
*/

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import aufgabe4.braitenbergvehikel.*;

public class MouseEventHandler implements EventHandler<MouseEvent>{
	
	private BVSimulation sim;
	
	/**
	 * Konstruktor mit Nutzereingabe
	 * @param sim
	 */
	public MouseEventHandler(BVSimulation sim){
		this.sim = sim;
	}
	
	/**
	 * Eventhandler
	 * @param event
	 */
	@Override
	public void handle(MouseEvent event){
		double x = event.getX();
		double y = event.getY();
		System.out.println("X: " + x + " Y: " + y);
		sim.setSignal(x-300d, y-300d);
		System.out.println(sim.getSignal());
		if(y < 300){
			y -= 300d;
		}
		if(x <= 300){
			x -= 300d;
		}
		else{
			x += 300d;
		}
		//sim.setSignal(x-300d, y-300d);
		//sim.setSignal(0d, 0d);
		//sim.setSignal(x, y);
	}
}