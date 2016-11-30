package aufgabe3;

/**
 * Task um die GUI zu aktualisieren
 * 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 30.11.2016
* Aufgabenblatt 3 | Aufgabe 4
*/

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.shape.Polygon;

public class LabelTask extends Task<Boolean>{

	//Label
	private final Label LABEL;
	
	//indirekter Farbmodifikator
	private final String STYLEVALUE;
	
	//Darstellung des Zuges im GUI
	private final Polygon ZUG;
	
	// Sichtbarkeitsstatus
	private final Boolean VISIBLE;
	
	/**
	 * Konstruktor
	 * 
	 * @param label - spez. Label
	 * @param styleValue
	 * @param zug - visuelle Zugdarstellung
	 * @param visible - Sichtbarkeitsstatus
	 */
	public LabelTask(Label label, String styleValue, Polygon zug, Boolean visible){
		this.LABEL = label;
		this.STYLEVALUE = styleValue;
		this.ZUG = zug;
		this.VISIBLE = visible;
	}
	
	/**
	 * Aufruf des LabelTask
	 */
	@Override
	protected Boolean call() throws Exception {
		updateLabel();
		return true;
	}

	/**
	 * Updated die Label
	 */
	private void updateLabel(){
		Platform.runLater(()->{
			LABEL.setStyle(this.STYLEVALUE);
			ZUG.setVisible(this.VISIBLE);
		;});
	}
}
