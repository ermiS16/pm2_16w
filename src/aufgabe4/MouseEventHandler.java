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
		if(y == 0){
			y = 300d;
		}
		if(y > 0 && y <= 300){
			y -= 300d;
			y *= (-1);
		}
		if(y > 300){
			y -= 300;
			y *= (-1);
		}
		x -= 300d;
		sim.setSignal(x, y);
		System.out.println(sim.getSignal());
	}
}