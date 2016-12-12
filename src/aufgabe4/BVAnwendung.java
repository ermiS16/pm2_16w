/**
 * Prof. Philipp Jenke
 * Hochschule fuer Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */
package aufgabe4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import aufgabe4.braitenbergvehikel.BVBewegungAbstossung;
import aufgabe4.braitenbergvehikel.BVBewegungAttraktion;
import aufgabe4.braitenbergvehikel.BVSimulation;
import aufgabe4.braitenbergvehikel.BraitenbergVehikel;
import aufgabe4.braitenbergvehikel.Vektor2;
import aufgabe4.view.BVCanvas;

//import javafx.application.Platform;
import javafx.scene.control.*;
//import javafx.geometry.*;
import javafx.scene.layout.GridPane;
//import javafx.scene.control.Alert.AlertType;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
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
	
	private Button simuliere;
	private CheckBox simulieren;
	private GridPane grid;
	private Label lb[];
	private Label label1 = new Label ("LANGER NAME");
	private Label label2 = new Label ("T2");
	private ComboBox<String> ba[];
	private ComboBox<String> box1 = new ComboBox<String>(FXCollections.observableArrayList("ATTRAKTION", "ABSTOSSUNG"));
	private ComboBox<String> box2 = new ComboBox<String>(FXCollections.observableArrayList("ATTRAKTION", "ABSTOSSUNG"));
	private boolean isActive;
	private BVSThread simThread;
	private BVSimulation sim;
	
	/**
	 * Init der GUI
	 */
	@Override
	public void init(){
		simuliere = new Button ("Simuliere!");
		simulieren = new CheckBox ("Simuliere!");
		isActive = false;
		sim = erzeugeSimulationsszene();
		grid = new GridPane();
		grid.add(simuliere, 0, 0);
		grid.add(simulieren, 0, 1);
		grid.add(label1, 0, 2);
		grid.add(label2, 0, 3);
		grid.add(box1, 1, 2);
		grid.add(box2, 1, 3);
		grid.setHgap(5d);
		grid.setVgap(5d);
	}
	
	private void setInitObjects(BVSimulation simul){
		//TODO Arrays für variable Labels und Comboboxen
	}

	@Override
	public void start(Stage primaryStage) {
		// Simulation zusammenstellen
		//BVSimulation sim = erzeugeSimulationsszene();

		// Canvas setzen
		BVCanvas canvas = new BVCanvas(600, 600, sim);

		canvas.zeichneSimulation();

		// Szenengraph aufbauen
		primaryStage.setTitle("Braitenberg-Vehikel!");
		BorderPane wurzel = new BorderPane();
		wurzel.setCenter(canvas);
		wurzel.setRight(grid);
    
		//Button
		simuliere.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(!isActive){
					sim.simulationsSchritt();
					canvas.zeichneSimulation();
				}
				else{
					System.out.println("Simulation laeuft");
				}
			}
		});
    
		//CheckBox
		simulieren.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(!isActive){
					simThread = new BVSThread(sim, canvas);
					simThread.start();
					isActive = true;
				}
				else{
					simThread.interrupt();
					isActive = false;
				}
			}
		});

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
		BVSimulation sim = new BVSimulation();
		sim.vehikelHinzufuegen(vehikel1);
		sim.vehikelHinzufuegen(vehikel2);
		return sim;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
