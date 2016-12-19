/**
 * Prof. Philipp Jenke
 * Hochschule fuer Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */

/**
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 16.12.2016
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
import aufgabe4.braitenbergvehikel.BVBewegungAbstand;
import aufgabe4.braitenbergvehikel.BVSimulation;
import aufgabe4.braitenbergvehikel.BraitenbergVehikel;
import aufgabe4.braitenbergvehikel.Vektor2;
import aufgabe4.view.BVCanvas;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * JavaFX Anwendung zur Darstellung und Interaktion mit einer
 * Braitenberg-Vehikel-Simulation.
 * 
 * @author Philipp Jenke
 */

public class BVAnwendung extends Application {
	
	//Feste GUI Bedienelemente
	private Button beenden;
	private Button info;
	private Button simuliere;
	private CheckBox simulieren;
	
	//Layoutbasis fuer Bedienelemente
	private GridPane grid;
	
	//Speicher fuer nicht-feste GUI Bedienelemente
	private Label[] labels;
	private Object[] tab;
	
	//Notwendiges
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
		
		//Setup feste GUI Bedienelemente
		grid = new GridPane();
		grid.setHgap(5d);
		grid.setVgap(5d);
		grid.add(beenden, 1, 0);
		grid.add(info, 1, 1);
		grid.add(simuliere, 0, 0);
		grid.add(simulieren, 0, 1);
		
		sim = erzeugeSimulationsszene();
		setInitObjects(sim);
	}//END init
	
	/**
	 * Init anderer GUI Elemente
	 * @param simul
	 */
	private void setInitObjects(BVSimulation simul){
		labels = new Label[simul.getAnzahlVehike()];
		tab = new Object[simul.getAnzahlVehike()];
		
		//Erstellt Namenslabel und Verhaltensauswahl
		//fuer alle BV in der Simulation.
		//Fuegt EV hinzu, sofern notwendig.
		for(int i = 0; i < simul.getAnzahlVehike(); i++){
			ComboBox<String> box = new ComboBox<String>
				(FXCollections.observableArrayList("ATTRAKTION", "ABSTOSSUNG", "ABSTAND"));
			
			if(simul.getVehikel(i).getBewegung() != null){
				box.setValue(simul.getVehikel(i).getBewegung().getId());
			}
			
			//Fehlerbehandlung
			else{
				simul.getVehikel(i).setBewegung(new BVBewegungAttraktion());
				box.setValue(simul.getVehikel(i).getBewegung().getId());
			}
			
			Label namenLabel = new Label((simul.getVehikel(i).getName()));
			
			//Fehlerbehandlung
			if(namenLabel.getText() == null || namenLabel.getText() == ""){
				//System.out.println("fault detected");
				namenLabel = new Label("NameError");
			}
			
			//Layoutbehandlung
			if(namenLabel.getText().length() > 9){
				String format = namenLabel.getText().substring(0, 9) + "\n" 
						+ namenLabel.getText().substring(9, namenLabel.getText().length());
				namenLabel = new Label(format);
			}
			
			labels[i] = namenLabel;
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

		//Canvas bereitstellen
		canvas.zeichneSimulation();
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED, 
				new MouseEventHandler(sim, canvas));

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
					System.out.println("Simulation unterbrochen");
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
						+ "Version: 1.0, 16.12.2016\n\n"
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
			ComboBox<?> verhalten = (ComboBox<?>) tab[i];
			final int z = i;
			verhalten.setOnAction(new EventHandler<ActionEvent>(){
			      @Override
			      public void handle(ActionEvent arg0) {
			    	  if(verhalten.getValue().toString() == "ATTRAKTION"){
			    		  sim.getVehikel(z).setBewegung(new BVBewegungAttraktion());
			    		  //System.out.println("ATT geht");
			    	  }
			    	  
			    	  if(verhalten.getValue().toString() == "ABSTOSSUNG"){
			    		  sim.getVehikel(z).setBewegung(new BVBewegungAbstossung());
			    		  //System.out.println("ABST geht");
			    	  }
			    	  
			    	  if(verhalten.getValue().toString() == "ABSTAND"){
			    		  sim.getVehikel(z).setBewegung(new BVBewegungAbstand());
			    		  //System.out.println("ABSTAND geht");
			    	  }
			    	  
			    	  //Fehlerbehandlung
			    	  if(verhalten.getValue().toString() != "ATTRAKTION" &&
			    			  verhalten.getValue().toString() != "ABSTOSSUNG" &&
			    			  verhalten.getValue().toString() != "ABSTAND"){
			    		  System.out.println("Fehler");
			    		  if(isActive){
			    			  simThread.interrupt();
			    			  System.out.println("Kritischer Fehler: Simulation beendet");
			    		  }
			    	  }
			      }//END handle
			});//END setOnAction
		}//END FOR
		
		primaryStage.setScene(new Scene(wurzel, 850, 600));
		primaryStage.show();
	}//END Start

	/**
	 * Erzeugt eine Simulationsszene zum Testen.
	 */
	public BVSimulation erzeugeSimulationsszene() {
		BraitenbergVehikel vehikel1 =
				new BraitenbergVehikel("Susi", new BVBewegungAttraktion());
		BraitenbergVehikel vehikel2 = new BraitenbergVehikel("Mike",
				new BVBewegungAbstossung(), new Vektor2(-100, 100), new Vektor2(1, 0));
		BraitenbergVehikel vehikel3 = new BraitenbergVehikel("Stanley",
				new BVBewegungAbstossung(), new Vektor2(-125, 45), new Vektor2(1, 0));
		BraitenbergVehikel vehikel4 = new BraitenbergVehikel("123456789AA",
				null, new Vektor2(20, 145), new Vektor2(1, 0));
		BVSimulation sim = new BVSimulation();
		sim.vehikelHinzufuegen(vehikel1);
		sim.vehikelHinzufuegen(vehikel2);
		sim.vehikelHinzufuegen(vehikel3);
		sim.vehikelHinzufuegen(vehikel4);
		return sim;
	}

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
