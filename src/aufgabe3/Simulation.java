package aufgabe3;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
* Simulation eines aktiven Rangier-
* bahnhofes mit Zuegen und
* Lokfuehrern
* 
* Praktikum TIPR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* @author Eric Misfeld, Simon Felske
* @version 19.11.2016
* Aufgabenblatt 3 | Aufgabe 3
*/

public class Simulation extends Application implements Runnable{
	
	private final int INTERVALL = 500;
	
	/*
	 * Erstellt einen neuen Lokfuehrer
	 */
	public Lokfuehrer erzeugeLokfuehrer(Rangierbahnhof bahnhof){
		int gleis = (int) (Math.random() * 5);
		int aufgabe = (int) (Math.random() * 2);
		//Rangierbahnhof bahnhof = null;
		Lokfuehrer neuerLokfuehrer = new Lokfuehrer(aufgabe, gleis, bahnhof);
		return neuerLokfuehrer;
	}// END METHOD

	@Override
	public void run(){
		Rangierbahnhof tbhf = new Rangierbahnhof();
		//tbhf.start();
		int q = 0;
		//sinnvolle dinge
		while(true){
			try {
				q++;
				if(1 > 0){
					Lokfuehrer x = erzeugeLokfuehrer(tbhf);
					x.start();
					}
				System.out.println("Zeit " + q);
				Thread.sleep(INTERVALL);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]){
		//Rangierbahnhof tbhf = new Rangierbahnhof();
		//Lokfuehrer tlf = new Lokfuehrer(0,1,tbhf);
		//Lokfuehrer tlf2 = new Lokfuehrer(0,2,tbhf);
		//Lokfuehrer tlf3 = new Lokfuehrer(1,2,tbhf);
		//Lokfuehrer tlf4 = new Lokfuehrer(0,1,tbhf);
		//tbhf.start();
		//tlf.start();
		//tlf2.start();
		//tlf3.start();
		//tlf4.start();
		//wait(20);
		//System.out.println(tbhf.getZugAnzahl());
		Application.launch();
		Thread test = new Thread(new Simulation());
		test.start();
	}
	
	@Override
	public void start(Stage primaryStage){
		GridPane root = new GridPane();
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 600, 600);
		primaryStage.setTitle("Rangierbahnhof");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
