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
* @version 15.12.2016
* Aufgabenblatt 4 | Aufgabe 4
*/

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import aufgabe4.braitenbergvehikel.*;
import aufgabe4.view.*;

public class MouseEventHandler implements EventHandler<MouseEvent>{
	
	private BVSimulation sim;
	private BVCanvas can;
	
	/**
	 * Konstruktor mit Nutzereingabe
	 * @param sim
	 * @param can
	 */
	public MouseEventHandler(BVSimulation sim, BVCanvas can){
		this.sim = sim;
		this.can = can;
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
		System.out.println(can.getBVCbreite());
		System.out.println(can.getBVChoehe());
		if(y >= 0 && y <= (can.getBVChoehe() * 0.5)){
			y = (Math.abs(y - ((double) (can.getBVChoehe() * 0.5))));
		}
		if(y > (can.getBVChoehe() * 0.5)){
			y -= (double) (can.getBVChoehe() * 0.5);
			y *= (-1);
		}
		x -= (double) (can.getBVCbreite() * 0.5);
		sim.setSignal(x, y);
		System.out.println(sim.getSignal());
	}
}