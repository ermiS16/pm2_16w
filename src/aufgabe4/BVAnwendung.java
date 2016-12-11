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

/**
 * JavaFX Anwendung zur Darstellung und Interaktion mit einer
 * Braitenberg-Vehikel-Simulation.
 * 
 * @author Philipp Jenke
 */
public class BVAnwendung extends Application {
	
	private Button simuliere = new Button ("Simuliere!");
	private CheckBox simulieren = new CheckBox ("Simuliere!");
	private ComboBox<String> box1 = new ComboBox<String>(FXCollections.observableArrayList("ATTRAKTION", "ABSTOSSUNG"));
	private ComboBox<String> box2 = new ComboBox<String>(FXCollections.observableArrayList("ATTRAKTION", "ABSTOSSUNG"));

  @Override
  public void start(Stage primaryStage) {
    // Simulation zusammenstellen
    BVSimulation sim = erzeugeSimulationsszene();

    // Canvas setzen
    BVCanvas canvas = new BVCanvas(600, 600, sim);

    canvas.zeichneSimulation();

    // Szenengraph aufbauen
    primaryStage.setTitle("Braitenberg-Vehikel!");
    BorderPane wurzel = new BorderPane();
    wurzel.setCenter(canvas);
    
    //ListView list = new ListView();
    //wurzel.setAlignment(list, Pos.TOP_RIGHT);
    //wurzel.setMargin(list, new Insets(12,12,12,12));
    
    //wurzel.setRight(list);
    
    GridPane grid = new GridPane();
    grid.add(simuliere, 0, 0);
    grid.add(simulieren, 0, 1);
    grid.add(box1, 0, 3);
    grid.add(box2, 0, 4);
    wurzel.setRight(grid);
    
    
    //wurzel.setRight(simuliere);
    //wurzel.setRight(simulieren);

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
