/**
 * Prof. Philipp Jenke
 * Hochschule fuer Angewandte Wissenschaften (HAW), Hamburg
 * Lecture demo program.
 */

/**
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 12.12.2016
* Aufgabenblatt 4
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


import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

//CHECKEN
//import java.awt.event.MouseListener;
//import java.awt.event.MouseEvent;

/**
 * JavaFX Anwendung zur Darstellung und Interaktion mit einer
 * Braitenberg-Vehikel-Simulation.
 * 
 * @author Philipp Jenke
 */

@SuppressWarnings("unchecked")
public class BVAnwendung extends Application {
	
	private Button simuliere;
	private CheckBox simulieren;
	private GridPane grid;
	private Label lb[];
	private Object[] tab;
	private boolean isActive;
	private BVSThread simThread;
	private BVSimulation sim;
	
	/**
	 * Init der GUI
	 */
	@Override
	public void init(){
		
		isActive = false;
		simuliere = new Button ("Simuliere!");
		simulieren = new CheckBox ("Simuliere!");
		grid = new GridPane();
		grid.setHgap(5d);
		grid.setVgap(5d);
		grid.add(simuliere, 0, 0);
		grid.add(simulieren, 0, 1);
		sim = erzeugeSimulationsszene();
		setInitObjects(sim);
		//box1 = new ComboBox<String>(FXCollections.observableArrayList("ATTRAKTION", "ABSTOSSUNG"));
		//box2 = new ComboBox<String>(FXCollections.observableArrayList("ATTRAKTION", "ABSTOSSUNG"));

		//box1.setValue(sim.getVehikel(0).getBewegung().getId());
		//box2.setValue(sim.getVehikel(1).getBewegung().getId());
		//grid.add(box1, 1, 2);
		//grid.add(box2, 1, 3);
	}
	
	/**
	 * Init anderer GUI Elemente
	 * @param simul
	 */
	private void setInitObjects(BVSimulation simul){
		lb = new Label[simul.getAnzahlVehike()];
		tab = new Object[simul.getAnzahlVehike()];
		for(int i = 0; i < simul.getAnzahlVehike(); i++){
			ComboBox<String> boxX = new ComboBox<String>(FXCollections.observableArrayList("ATTRAKTION", "ABSTOSSUNG"));
			boxX.setValue(simul.getVehikel(i).getBewegung().getId());
			Label x = new Label((simul.getVehikel(i).getName()));
			lb[i] = x;
			tab[i] = boxX;
			grid.add(x, 0, (2+i));
			grid.add(boxX, 1, (2+i));
		}
	}

	@Override
	public void start(Stage primaryStage) {
		
		// Simulation zusammenstellen
		
		// Canvas setzen
		BVCanvas canvas = new BVCanvas(600, 600, sim);
		sim.addObserver(canvas);
		
		for(int i = 0; i < sim.getAnzahlVehike(); i++){
			BraitenbergVehikel X = sim.getVehikel(i);
			X.addObserver(canvas);
		}

		canvas.zeichneSimulation();
		
		canvas.setOnMouseClicked(null);
		
//		canvas.setOnMouseClicked(new MouseEvent(){
//			
//		});
		
		//canvas.addEventHandler(eventType, eventHandler);
//		canvas.addMouseListener(new MouseListener(){
//			@Override
//			public void mousePressed(MouseEvent e){
//				
//			}
//		});

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
				}
				else{
					System.out.println("Nicht moeglich");
				}
			}
		});
    
		//CheckBox
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
			}
		});
		
		for(int i = 0; i < tab.length; i++){
			@SuppressWarnings("rawtypes")
			ComboBox XY = (ComboBox) tab[i];
			final int z = i;
			XY.setOnAction(new EventHandler<ActionEvent>() {
			      @Override
			      public void handle(ActionEvent arg0) {
			    	  if(XY.getValue().toString() == "ATTRAKTION"){
//			    		  if(isActive){
//			    			  try{
//				    			  simThread.wait();
//					    		  System.out.println("Zwangspause");
//				    			  } catch (InterruptedException e) {
//				    				  Thread.currentThread().interrupt();
//				    			  }
//			    		  }
			    		  sim.getVehikel(z).setBewegung(new BVBewegungAttraktion());
			    		  System.out.println("ATT geht");
			    		  //notifyAll();
			    	  }
			    	  if(XY.getValue().toString() == "ABSTOSSUNG"){
//			    		  if(isActive){
//			    			  try{
//				    			  simThread.wait();
//					    		  System.out.println("Zwangspause2");
//				    			  } catch (InterruptedException e) {
//				    				  Thread.currentThread().interrupt();
//				    			  }
//			    		  }
			    		  sim.getVehikel(z).setBewegung(new BVBewegungAbstossung());
			    		  System.out.println("ABST geht");
			    		  //notifyAll();
			    	  }
			    	  if(XY.getValue().toString() != "ATTRAKTION" && XY.getValue().toString() != "ABSTOSSUNG"){
			    		  System.out.println("Fehler");
			    	  }
			      }//END handle
			});
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
