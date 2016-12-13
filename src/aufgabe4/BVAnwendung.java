/**
 * Prof. Philipp Jenke
 * Hochschule fuer Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */

/**
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 13.12.2016
* Aufgabenblatt 4
 */
package aufgabe4;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import aufgabe4.braitenbergvehikel.BVBewegungAbstossung;
import aufgabe4.braitenbergvehikel.BVBewegungAttraktion;
import aufgabe4.braitenbergvehikel.BVSimulation;
import aufgabe4.braitenbergvehikel.BraitenbergVehikel;
import aufgabe4.braitenbergvehikel.Vektor2;
import aufgabe4.view.BVCanvas;


import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

//CHECKEN
//import java.awt.event.MouseListener;
//import java.awt.event.MouseEvent;

/**
 * JavaFX Anwendung zur Darstellung und Interaktion mit einer
 * Braitenberg-Vehikel-Simulation.
 * 
 * @author Philipp Jenke
 */

public class BVAnwendung extends Application {
	
	private Button beenden;
	private Button info;
	private Button simuliere;
	private CheckBox simulieren;
	private GridPane grid;
	private Label[] lb;
	private Object[] tab;
	private boolean isActive;
	private BVSThread simThread;
	private BVSimulation sim;
	
	/**
	 * Init der GUI
	 * Nur fixe GUI-Elemente werden initialisiert
	 */
	@Override
	public void init(){
		
		isActive = false;
		beenden = new Button ("Beenden!");
		info = new Button ("Info!");
		simuliere = new Button ("Simuliere!");
		simulieren = new CheckBox ("Simuliere!");
		grid = new GridPane();
		grid.setHgap(5d);
		grid.setVgap(5d);
		grid.add(beenden, 1, 0);
		grid.add(info, 1, 1);
		grid.add(simuliere, 0, 0);
		grid.add(simulieren, 0, 1);
		sim = erzeugeSimulationsszene();
		setInitObjects(sim);
	}
	
	/**
	 * Init anderer GUI Elemente
	 * @param simul
	 */
	private void setInitObjects(BVSimulation simul){
		lb = new Label[simul.getAnzahlVehike()];
		tab = new Object[simul.getAnzahlVehike()];
		
		//Erstellt Namenslabel und Verhaltensauswahl
		//fuer alle BV in der Simulation
		for(int i = 0; i < simul.getAnzahlVehike(); i++){
			ComboBox<String> box = new ComboBox<String>
				(FXCollections.observableArrayList("ATTRAKTION", "ABSTOSSUNG"));
			box.setValue(simul.getVehikel(i).getBewegung().getId());
			Label namenLabel = new Label((simul.getVehikel(i).getName()));
			lb[i] = namenLabel;
			tab[i] = box;
			grid.add(namenLabel, 0, (2+i));
			grid.add(box, 1, (2+i));
		}//END for
	}//END setInitObjects

	@Override
	public void start(Stage primaryStage) {
		
		// Canvas setzen
		BVCanvas canvas = new BVCanvas(600, 600, sim);
		sim.addObserver(canvas);
		
		for(int i = 0; i < sim.getAnzahlVehike(); i++){
			BraitenbergVehikel bv = sim.getVehikel(i);
			bv.addObserver(canvas);
		}

		canvas.zeichneSimulation();
		
		canvas.setOnMouseClicked(null);

		// Szenengraph aufbauen
		primaryStage.setTitle("Braitenberg-Vehikel!");
		BorderPane wurzel = new BorderPane();
		wurzel.setCenter(canvas);
		wurzel.setRight(grid);
    
		/**
		 * Funktionalitaet fuer Button simuliere
		 */
		simuliere.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(!isActive){
					sim.simulationsSchritt();
				}
				else{
					System.out.println("Nicht moeglich");
				}
			}//END handle
		});
    
		/**
		 * Funktionalitaet fuer CheckBox simulieren
		 */
		simulieren.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(!isActive){
					simThread = new BVSThread(sim);
					simThread.start();
					isActive = true;
					System.out.println("Simulation gestartet");
				}
				else{
					simThread.interrupt();
					isActive = false;
					System.out.println("Simulation beendet");
				}
			}//END handle
		});
		
		/**
		 * Funktionalitaet fuer Button beenden
		 */
		beenden.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(isActive){
					simThread.interrupt();
					System.out.println("Simulation beendet");
				}
				System.out.println("Anwendung beendet");
				Platform.exit();
				System.exit(0);
			}// END handle
		});
		
		/**
		 * Funktionalitaet fuer Button info
		 */
		info.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				String contextText="Autoren: Eric Misfeld, Simon Felske\n"
						+ "Version: 0.8, 13.12.2016\n\n"
						+ "Dieses Programm simuliert eine beliebige Anzahl\n"
						+ "von Braitenberg-Vehikeln. Ihr Verhalten kann\n"
						+ "vom Anwender geändert werden.\n";
				Alert information = new Alert(AlertType.INFORMATION);
				information.setTitle("Information");
				information.setHeaderText("Informationen zum Programm");
				information.setContentText(contextText);
				information.showAndWait();
			}//END handle
		});
		
		/**
		 * Funktionalitaet fuer ComboBoxen in tab[]
		 */
		for(int i = 0; i < tab.length; i++){
			@SuppressWarnings("rawtypes")
			ComboBox<?> verhalten = (ComboBox) tab[i];
			final int z = i;
			verhalten.setOnAction(new EventHandler<ActionEvent>(){
			      @Override
			      public void handle(ActionEvent arg0) {
			    	  if(verhalten.getValue().toString() == "ATTRAKTION"){
			    		  sim.getVehikel(z).setBewegung(new BVBewegungAttraktion());
			    		  System.out.println("ATT geht");
			    	  }
			    	  if(verhalten.getValue().toString() == "ABSTOSSUNG"){
			    		  sim.getVehikel(z).setBewegung(new BVBewegungAbstossung());
			    		  System.out.println("ABST geht");
			    	  }
			    	  if(verhalten.getValue().toString() != "ATTRAKTION" &&
			    			  verhalten.getValue().toString() != "ABSTOSSUNG"){
			    		  System.out.println("Fehler");
			    	  }
			      }//END handle
			});//END setOnAction
		}//END FOR
		
		primaryStage.setScene(new Scene(wurzel, 850, 600));
		primaryStage.show();
	}

	/**
	 * Erzeugt eine Simulationsszene zum Testen.
	 */
	public BVSimulation erzeugeSimulationsszene() {
		BraitenbergVehikel vehikel1 =
				new BraitenbergVehikel("Susi", new BVBewegungAttraktion());
		BraitenbergVehikel vehikel2 = new BraitenbergVehikel("Mike",
				new BVBewegungAbstossung(), new Vektor2(-100, 100), new Vektor2(1, 0));
		BraitenbergVehikel vehikel3 = new BraitenbergVehikel("ABCDe",
				new BVBewegungAbstossung(), new Vektor2(-125, 45), new Vektor2(1, 0));
		BVSimulation sim = new BVSimulation();
		sim.vehikelHinzufuegen(vehikel1);
		sim.vehikelHinzufuegen(vehikel2);
		sim.vehikelHinzufuegen(vehikel3);
		return sim;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
