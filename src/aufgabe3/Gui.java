package aufgabe3;

/**
* Repraesentiert das GUI
* zur Steuerung der Simulation
* 
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 29.11.2016
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

//TODO: Labels in Array packen.

public class Gui extends Application implements Observer{

	private boolean isRunning;
	private Thread test;
	private Button start;
	private Button stop;
	private Button beenden;
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
	@SuppressWarnings("unused")
	private Boolean visible;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable observable, Object arg){
		if(observable instanceof Simulation){
			if(arg instanceof Zug[]){
				Zug[] zugAufGleis = (Zug[]) arg;
				for(int i = 0; i < zugAufGleis.length; i++){
					final int k = i;
					if(zugAufGleis[i] != null){
						LabelTask newLabels = new LabelTask(st[k], laneBlocked, zuege[k], true);
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
						LabelTask newLabels = new LabelTask(st[k], laneFree, zuege[k], false);
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
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#init()
	 */
	@Override
	public void init(){
		sim = new Simulation();
		sim.addObserver(this);
		System.out.println("Anwendung gestartet");
		zuege = new Polygon[3];
		st = new Label[3];
		gl = new Label[3];
		start = new Button("Start");
		stop = new Button("Stop");
		beenden = new Button("Beenden");
		anzahlGleise= new TextField("No function");
		lane = "-fx-background-color: grey;";
		laneFree = "-fx-background-color: green; -fx-text-fill: white;";
		laneBlocked = "-fx-background-color: red; -fx-text-fill: white;";
		visible = false;
		
		
		for(int i = 0; i < 3; i++){
			zug = new Polygon();
			zug.getPoints().addAll(new Double[]{5.0,0.0,20.0,5.0,5.0,10.0});
			zug.fillProperty().set(Color.BLUE);
			zug.setVisible(false);
			zuege[i] = zug;
		}// END FOR
		
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
		railwayYard.setAlignment(Pos.TOP_LEFT);
		railwayYard.setHgap(5d);
		railwayYard.setVgap(5d);
		
		for(int i = 0; i < 3; i++){
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
	}// END METHOD (INIT)
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
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
		
		Scene scene = new Scene(root, 200, 200);
		primaryStage.setTitle("Rangierbahnhof");
		primaryStage.setScene(scene);
		primaryStage.show();
	}// END METHOD (START)
	
}
