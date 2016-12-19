package aufgabe4.view;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import aufgabe4.braitenbergvehikel.BVSimulation;
import aufgabe4.braitenbergvehikel.BraitenbergVehikel;
import aufgabe4.braitenbergvehikel.Vektor2;

/**
 * Zeichenflaeche fuer eine Braitenberg-Vehikel-Simulation.
 * 
 * @author Philipp Jenke
 */

/**
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* Simon Felske, Eric Misfeld
* @version 16.12.2016
* Aufgabenblatt 4
 */

public class BVCanvas extends Canvas implements Observer{

  /**
   * Bild eines Vehikels. Achtung: Package mit dem Bild muss korrekt angegeben
   * werden.
   */
  private Image bvImage =
		  new Image("aufgabe4/assets/braitenberg_vehikel_a1.png");
  private Image bvImage2 = 
		  new Image("aufgabe4/assets/braitenberg_vehikel_b1.png");

  /**
   * Referenz auf die Simulation.
   */
  private final BVSimulation sim;
  
  /**
   * Referenz auf Canvasflaeche
   */
  private final int BVCbreite;
  private final int BVChoehe;

  /**
   * Konstruktor
   * 
   * @param breite
   * @param hoehe
   * @param sim
   */
  public BVCanvas(int breite, int hoehe, BVSimulation sim) {
    super(breite, hoehe);
    this.sim = sim;
	BVCbreite = breite;
	BVChoehe = hoehe;
  }

  /**
   * Zeichnet die gesamte Simulation neu.
   */
  public void zeichneSimulation() {
    GraphicsContext gc = getGraphicsContext2D();
    
    // Alles loeschen
    gc.setFill(Color.WHITE);
    gc.fillRect(0, 0, getWidth(), getHeight());
    
    // Vehikel zeichnen
    for (int i = 0; i < sim.getAnzahlVehike(); i++) {
      zeichneVehikel(gc, sim.getVehikel(i));
    }
    
    // Signal zeichnen
    zeichneSignal(gc, sim.getSignal());
    
  }

  /**
   * Rechnet von Welt- und Bild-Koordinaten um.
   */
  public Point welt2BildKoordinaten(Vektor2 position) {
    return new Point((int) (position.x() + getWidth() / 2),
        (int) (getHeight() / 2 - position.y()));
  }

  /**
   * Rotiert den aktuellen Grafik-Kontext (zum Zeichnen eines rotierten Bildes).
   */
  private void rotieren(GraphicsContext gc, double winkel, double px,
      double py) {
    Rotate r = new Rotate(winkel, px, py);
    gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(),
        r.getTy());
  }
  
  /**
   * Zeichnet ein Bild gedreht.
   * 
   * @param gc
   * @param image
   * @param winkel
   * @param x
   * @param y
   */
  private void zeichneGedrehtesBild(GraphicsContext gc, Image image,
      double winkel, double x, double y) {
    // Zustand auf dem Stack sichern
    gc.save();
    rotieren(gc, winkel, x + image.getWidth() / 2, y + image.getHeight() / 2);
    gc.drawImage(image, x, y);
    
    // Zustand wiederherstellen
    gc.restore();
  }

  /**
   * Zeichnet einen Text gedreht.
   * 
   * @param gc
   * @param image
   * @param name
   * @param winkel
   * @param x
   * @param y
   */
  private void zeichneGedrehtenText(GraphicsContext gc, Image image, String name,
      double winkel, double x, double y) {
    // Zustand auf dem Stack sichern
    gc.save();
    rotieren(gc, winkel, x + image.getWidth() / 2, y + image.getHeight() / 2);
    
    gc.setFill(Color.BLACK);
    gc.fillText(name, x, y + 62d);
    
    // Zustand wiederherstellen
    gc.restore();
  }
  
  /**
   * Zeichnet einen Pfeil gedreht.
   * 
   * @param gc
   * @param image
   * @param image2
   * @param winkel
   * @param x
   * @param y
   */
  private void zeichneGedrehtenPfeil(GraphicsContext gc, Image image, Image image2,
      double winkel, double x, double y) {
    // Zustand auf dem Stack sichern
    gc.save();
    rotieren(gc, winkel, x + image.getWidth() / 2, y + image.getHeight() / 2);
    
    gc.drawImage(image2, x + 40d, y);
    
    // Zustand wiederherstellen
    gc.restore();
  }
  
  /**
   * Zeichnet ein Braitenberg-Vehikel,
   * zugehoerigen Status-Pfeil und Namen.
   * 
   * @param gc
   * @param bv
   */
  protected void zeichneVehikel(GraphicsContext gc, BraitenbergVehikel bv) {
	String checkName = bv.getName();
	Image bvbimg = bv.getbvbImage();
    Point p = welt2BildKoordinaten(bv.getPosition());
    double winkelInGrad = bv.getRotationGradImUhrzeigersinn();
    
    int x = (int) (p.x - bv.getSeitenlaenge() / 2);
    int y = (int) (p.y - bv.getSeitenlaenge() / 2);
    
    if(bv.getBewegung().getId() == "ABSTOSSUNG"){
        zeichneGedrehtesBild(gc, bvImage2, winkelInGrad, x, y);
    }
    else{
        zeichneGedrehtesBild(gc, bvImage, winkelInGrad, x, y);
    }
    
    //Status-Pfeil
    zeichneGedrehtenPfeil(gc, bvImage, bvbimg, winkelInGrad, x, y);
    
    //Name mit Fehlerbehandlung
    if(checkName == null || checkName == ""){
    	checkName = "NameError";
    	//System.out.println("fault detected");
    }
    
    //Name mit Laengenbehandlung
    if(checkName.length() > 8){
    	checkName = checkName.substring(0, 8) + "\n" 
				+ checkName.substring(8, checkName.length());
	}
    
    zeichneGedrehtenText(gc, bvImage, checkName, winkelInGrad, x, y);
    
  }

  /**
   * Zeichnet das Signal.
   * 
   * @param gc
   * @param signal
   */
  private void zeichneSignal(GraphicsContext gc, Vektor2 signal) {
    int breite = 10;
    int offset = 2;
    Point p = welt2BildKoordinaten(signal);
    gc.setFill(Color.BLACK);
    gc.fillOval(p.x - breite / 2 - offset, p.y - breite / 2 - offset,
        breite + offset * 2, breite + offset * 2);
    gc.setFill(Color.YELLOW);
    gc.fillOval(p.x - breite / 2, p.y - breite / 2, breite, breite);
  }

  /**
   * Update
   * 
   * @param o
   * @param arg
   */
  @Override
  public void update(Observable o, Object arg) {
    // Zeichenroutine wird im JavaFX-Thread aufgerufen.
	  if(o instanceof BraitenbergVehikel || o instanceof BVSimulation){
		  Platform.runLater(new Runnable() {
			  @Override
			  public void run() {
				  zeichneSimulation();
			  }
		  });
	  }//END if
  }//END method
  
  /**
   * Getter fuer Canvasbreite
   * @return BVCbreite
   */
  public int getBVCbreite(){
	  return BVCbreite;
  }
  
  /**
   * Getter fuer Canvashoehe
   * @return BVChoehe
   */
  public int getBVChoehe(){
	  return BVChoehe;
  }
  
}
