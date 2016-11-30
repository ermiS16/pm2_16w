package aufgabe3;

/**
* Repraesentiert das GUI
* zur Steuerung der Simulation
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 30.11.2016
* Aufgabenblatt 3 | Aufgabe 4
*/

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Gui extends Application implements Observer{
	
	private int anzahl;
	private boolean isRunning;
	private Thread test;
	private Button start;
	private Button stop;
	private Button beenden;
	private Button ok;
	private TextField anzahlGleise;
	private Label status;
	private Label gleis;
	private GridPane root;
	private GridPane base;
	private GridPane baseFunction;
	private GridPane baseParameter;
	private GridPane railwayYard;
	private Polygon zug;
	private Polygon[] zuege;
	private Label[] st;
	private Label[] gl;
	private Simulation sim;
	private String lane;
	private String laneFree;
	private String laneBlocked;
	private Boolean visible;
	
	/*
	 * Update the Labels via JavaFX-Tasks
	 */
	@Override
	public void update(Observable observable, Object arg){
		if(observable instanceof Simulation){
			if(arg instanceof Zug[]){
				Zug[] zugAufGleis = (Zug[]) arg;
				for(int i = 0; i < zugAufGleis.length; i++){
					if(zugAufGleis[i] != null){
						visible = true;
						LabelTask newLabels = new LabelTask(st[i], laneBlocked, zuege[i], visible);
						if(!newLabels.isCancelled()){
							try {
								newLabels.call();
								newLabels.cancel();
							} catch (Exception e) {
								newLabels.cancel();
								e.printStackTrace();
							}
						}	
					}// END IF
					if(zugAufGleis[i] == null){
						visible = false;
						LabelTask newLabels = new LabelTask(st[i], laneFree, zuege[i], visible);
						if(!newLabels.isCancelled()){
							try {
								newLabels.call();
								newLabels.cancel();
							} catch (Exception e) {
								newLabels.cancel();
								e.printStackTrace();
							}
						}
					}// END IF
				}// END FOR
			}// END IF
		}// END IF
	}// END METHOD
	
	/*
	 * Init der GUI
	 * Hier werden nur GUI Objekte initialisiert, deren Darstellung sich nicht aendert.
	 */
	@Override
	public void init(){
		
		anzahl = 3;
		System.out.println("Anwendung gestartet");
		start = new Button("Start");
		stop = new Button("Stop");
		beenden = new Button("Beenden");
		ok = new Button("OK");
		anzahlGleise= new TextField();
		anzahlGleise.setPromptText("Anzahl Gleise");
		lane = "-fx-background-color: grey;";
		laneFree = "-fx-background-color: green; -fx-text-fill: white;";
		laneBlocked = "-fx-background-color: red; -fx-text-fill: white;";
		visible = false;
		
		root = new GridPane();
		base = new GridPane();
		baseFunction = new GridPane();
		baseParameter = new GridPane();
		railwayYard = new GridPane();

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
		baseParameter.add(ok, 2, 0);
		railwayYard.setAlignment(Pos.TOP_LEFT);
		railwayYard.setHgap(5d);
		railwayYard.setVgap(5d);
		
		//Initialisieren von GUI Objekten mit verschiedener Anzahl
		setInitObjects(anzahl);
	}// END METHOD (INIT)
	
	/*
	 * Startmethode fuer die GUI-Applikation
	 */
	@Override
	public void start(Stage primaryStage){

		/*
		 * Funktionalitaet fuer Button start
		 */
		start.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(!isRunning){
					test = new Thread(sim);
					test.start();
					System.out.println("Neue Simulation gestartet");
					isRunning = true;
				}else{
					System.out.println("Es laeuft bereits eine Simulation");
				}// END ELSE
			}// END handle
		});
		
		/*
		 * Funktionalitaet fuer Button stop
		 */
		stop.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(isRunning && !test.isInterrupted()){
					test.interrupt();
					System.out.println("Aktuelle Simulation beendet");
					isRunning = false;
				}else{
					System.out.println("Es laeuft keine Simulation");
				}// END ELSE
			}// END handle
		});
		
		/*
		 * Funktionalitaet fuer Button beenden
		 */
		beenden.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(isRunning && !test.isInterrupted()){
					test.interrupt();
					System.out.println("Aktuelle Simulation beendet");
					isRunning = false;
				}
				System.out.println("Anwendung beendet");
				Platform.exit();
				System.exit(0);
			}// END handle
		});
		
		/*
		 * Funktionalitaet fuer Button ok
		 */
		ok.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(!isRunning){
					String ag = anzahlGleise.getText();
					int anzahl;
					try{
						anzahl = Integer.parseInt(ag);
						setInitObjects(anzahl);
					}catch(NumberFormatException exc){
						anzahlGleise.setText("Ungueltige Eingabe");
					}					
				}else{
					anzahlGleise.setText("Simulation erst Stoppen");
				}
			}
		});

		/*
		 * GUI erzeugen und zeigen
		 */
		Scene scene = new Scene(root, 200, 200);
		primaryStage.setTitle("Rangierbahnhof");
		primaryStage.setScene(scene);
		primaryStage.show();
	}// END METHOD (START)
	
	/*
	 * Methode zum richtigen initialisieren der GUI-Objekte, deren Darstellung
	 * einer variablen Anzahl unterliegen.
	 * @param anzahlGleise Anzahl der Gleise, die fuer die Simulation dargestellt werden
	 * sollen.
	 */
	private void setInitObjects(int anzahlGleise){
		//Simulation wird erzeugt und dessen Observer bekannt gegeben.		
		
		sim = new Simulation(anzahlGleise);
		sim.addObserver(this);
		
		zuege = new Polygon[anzahlGleise];
		st = new Label[anzahlGleise];
		gl = new Label[anzahlGleise];
		
		//Zug Darstellung als Polygon
		for(int i = 0; i < anzahlGleise; i++){
			zug = new Polygon();
			zug.getPoints().addAll(new Double[]{5.0,0.0,20.0,5.0,5.0,10.0});
			zug.fillProperty().set(Color.BLUE);
			zug.setVisible(false);
			zuege[i] = zug;
		}// END FOR
		
		//Setzen der Objekte, deren Anzahl nicht fix ist
		for(int i = 0; i < anzahlGleise; i++){
			status = new Label("G"+i);
			gleis = new Label();
			st[i] = status;
			gl[i] = gleis;
			railwayYard.add(status, 0, i);
			railwayYard.add(gleis, 1, i);
			railwayYard.add(zuege[i], 1, i);
			status.setStyle(laneFree);
			gleis.setMinWidth(100d);
			gleis.setStyle(lane);
		}// END FOR
	}	
}
