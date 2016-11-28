package aufgabe3;

/**
* Repraesentiert das GUI
* zur Steuerung der Simulation
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 28.11.2016
* Aufgabenblatt 3 | Aufgabe 2
*/

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Gui extends Application implements Observer{

	private boolean isRunning;
	private Thread test;
	private Button start;
	private Button stop;
	private Button beenden;
	private TextField anzahlGleise;
	private Label g1;
	private Label g2;
	private Label g3;
	private Label gleis1;
	private Label gleis2;
	private Label gleis3;

	
	@Override
	public void update(Observable observable, Object arg){
		
	}
	
	@Override
	public void start(Stage primaryStage){
		
		System.out.println("Anwendung gestartet");
		Polygon[] zuege = new Polygon[3];
		start = new Button("Start");
		stop = new Button("Stop");
		beenden = new Button("Beenden");
		anzahlGleise= new TextField("No function");
		g1 = new Label("G1");
		g2 = new Label("G2");
		g3 = new Label("G3");
		gleis1 = new Label("Gleis 1");
		gleis2 = new Label("Gleis 2");
		gleis3 = new Label("Gleis 3");
		
		for(int i=0;i<3;i++){
			Polygon zug = new Polygon();
			zug.getPoints().addAll(new Double[]{5.0,0.0,20.0,5.0,5.0,10.0});
			zug.fillProperty().set(Color.BLUE);
			zuege[i] = zug;
		}
		
		
		GridPane root = new GridPane();
		GridPane base = new GridPane();
		GridPane baseFunction = new GridPane();
		GridPane baseParameter = new GridPane();
		GridPane railwayYard = new GridPane();
		
		root.setVgap(10d);
		
		root.setAlignment(Pos.TOP_LEFT);
		root.add(base, 0, 0);
		root.add(railwayYard, 0, 1);
		base.add(baseFunction, 0, 0);
		base.add(baseParameter, 0, 1);
		baseFunction.setAlignment(Pos.TOP_CENTER);
		baseFunction.add(start, 0, 0);
		baseFunction.add(stop, 1, 0);
		baseFunction.add(beenden, 2, 0);
		baseParameter.setAlignment(Pos.TOP_CENTER);
		baseParameter.add(anzahlGleise, 1, 0);
		railwayYard.setAlignment(Pos.TOP_LEFT);
		railwayYard.setHgap(5d);
		railwayYard.add(g1, 0, 0);
		railwayYard.add(g2, 0, 1);
		railwayYard.add(g3, 0, 2);
		railwayYard.add(gleis1, 1, 0);
		railwayYard.add(gleis2, 1, 1);
		railwayYard.add(gleis3, 1, 2);
		
		g1.setStyle("-fx-background-color: green; -fx-text-fill: white;");
		g2.setStyle("-fx-background-color: green; -fx-text-fill: white;");
		g3.setStyle("-fx-background-color: green; -fx-text-fill: white;");
		gleis1.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
		gleis2.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
		gleis3.setStyle("-fx-background-color: grey; -fx-text-fill: white;");
		
		start.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(!isRunning){
					test = new Thread(new Simulation());
					test.start();
					System.out.println("Neue Simulation gestartet");
					isRunning = true;
				}else{
					System.out.println("Es laeuft bereits eine Simulation");
				}
			}
		});
		
		stop.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(!test.isInterrupted() && isRunning){
					test.interrupt();
					System.out.println("Aktuelle Simulation beendet");
					isRunning = false;
//					try {
//						test.join();
//					} catch (InterruptedException e1) {
//						e1.printStackTrace();
//						System.err.println("Fehler Gui");
//					}
				}// END IF
			}		
		});
		
		beenden.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				System.out.println("Anwendung beendet");
				Platform.exit();
				System.exit(0);
			}
		});
		
		Scene scene = new Scene(root, 200, 200);
		primaryStage.setTitle("Rangierbahnhof");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
