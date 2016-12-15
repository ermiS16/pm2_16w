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

import java.awt.Font;
//import java.awt.FontMetrics;
//import java.awt.Graphics;

/**
 * Zeichenflaeche fuer eine Braitenberg-Vehikel-Simulation.
 * 
 * @author Philipp Jenke
 */

/**
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* Eric Misfeld, Simon Felske
* @version 15.12.2016
* Aufgabenblatt 4
 */

public class BVCanvas extends Canvas implements Observer{

  /**
   * Bild eines Vehikels. Achtung: Package mit dem Bild muss korrekt angegeben
   * werden.
   */
  private Image bvImage =
		  new Image("aufgabe4/assets/braitenberg_vehikel.png");

  /**
   * Referenz auf die Simulation.
   */
  private final BVSimulation sim;
  
  /**
   * Referenz auf Canvasflaeche
   */
  private final int BVCbreite;
  private final int BVChoehe;

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

  public static Font textFont = new Font("Monospaced", Font.PLAIN, 12);
  public Font f = textFont;
  
  /**
   * Zeichnet ein Bild gedreht.
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
   * Zeichnet ein Braitenberg-Vehikel,
   * und zugehoerigen Status-Pfeil
   * 
   * @param gc
   * @param bv
   */
  protected void zeichneVehikel(GraphicsContext gc, BraitenbergVehikel bv) {
	Image bvbimg = bv.getbvbImage();
    Point p = welt2BildKoordinaten(bv.getPosition());
    double winkelInGrad = bv.getRotationGradImUhrzeigersinn();
    int x = (int) (p.x - bv.getSeitenlaenge() / 2);
    int y = (int) (p.y - bv.getSeitenlaenge() / 2);
    zeichneGedrehtesBild(gc, bvImage, winkelInGrad, x, y);
    zeichneGedrehtesBild(gc, bvbimg, winkelInGrad, x + 45d, y);
    //TODO fix positioning in relation to vehikle
  }

  /**
   * Zeichnet das Signal.
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
  
//  private void zeichneText(Graphics g){
//	  g.setFont(textFont);
//	  FontMetrics fm = g.getFontMetrics();
//	  int w = fm.stringWidth("testTXT");
//	  int h = fm.getAscent();
//	  g.drawString("testTXT", 0 - (w / 1), 0 + (h / 1));
//  }

  /**
   * 
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
