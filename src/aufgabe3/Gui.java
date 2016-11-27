package aufgabe3;

import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
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

	private Thread test;
	private Button start;
	private Button stop;
	private Button beenden;
	private TextField anzahlGleise;

	
	@Override
	public void update(Observable observable, Object arg){
		
	}
	
	@Override
	public void start(Stage primaryStage){
		test = new Thread(new Simulation());
		
		Button start = new Button("start");
		Button stop = new Button("stop");
		Button beenden = new Button("beenden");
		TextField anzahlGleise= new TextField("No function");
		
		Polygon zug = new Polygon();
		zug.getPoints().addAll(new Double[]{5.0,0.0,20.0,5.0,5.0,10.0});
		zug.fillProperty().set(Color.BLUE);
		
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
		
//		g1.setStyle("-fx-background-color: green; -fx-text-fill: white;");
		
		start.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				test.start();
			}
			
		});
		
		stop.setOnAction(new EventHandler<ActionEvent>(){
			@Override public void handle(ActionEvent e){
				if(!test.isInterrupted()){
					test.interrupt();
				}else{
					try {
						test.join();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}		
		});
		
		Scene scene = new Scene(root, 200, 200);
		primaryStage.setTitle("Rangierbahnhof");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
