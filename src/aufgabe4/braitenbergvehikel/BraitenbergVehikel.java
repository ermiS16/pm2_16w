package aufgabe4.braitenbergvehikel;

/**
 * Ein Braitenberg-Vehikel "fuehlt" zwei Sensorwerte und steuert darauf basierend
 * zwei Motoren an.
 * 
 * @author Philipp Jenke
 */

/**
* Praktikum TI-PR2, WS2016/2017
* Praktikumsgruppe Nr. 4
* Eric Misfeld, Simon Felske
* @version 16.12.2016
* Aufgabenblatt 4
 */

import java.util.Observable;

import javafx.scene.image.Image;

public class BraitenbergVehikel extends Observable{

  /**
   * Richtungs-Enum fuer Motoren und Sensoren.
   * 
   * @author Philipp Jenke
   *
   */
  public static enum Richtung {
    LINKS, RECHTS
  };

  /**
   * Sensorwerte, liegen in [0,1]
   */
  private double[] sensorWert = { 0, 0 };

  /**
   * Position des Vehikels im Weltkoordinatensystem.
   */
  private Vektor2 position = new Vektor2(0, 0);

  /**
   * Laenge (und Breite) des quadratischen Vehikels.
   */
  private double seitenlaenge = 50;

  /**
   * Radius der Raeder.
   */
  private double radRadius = 10;

  /**
   * Orientierung des Vehikels (in diese Richtung zeigen die Sensoren.
   */
  private Vektor2 orientierung = new Vektor2(0, 1);

  /**
   * Aktuelles Bewegungsverhalten.
   */
  private BVBewegung bewegung;

  /**
   * Maximale Motorgeschwindigkeit im Umdrehungen pro Sekunde.
   */
  private double maxMotorUmdrehungenProSek = 0.25;

  /**
   * Name des Vehikels zur Identifikation.
   */
  private final String name;
  
  /**
   * BVBewegungsImage
   */
  private Image bvbImage;
  
  /**
   * Konstruktor
   * @param name - nicht null, nicht ""
   * @param bewegung - nicht null
   * @param position
   * @param orientierung
   */
  public BraitenbergVehikel(String name, BVBewegung bewegung, Vektor2 position,
      Vektor2 orientierung) {
	this.name = name;
    this.bewegung = bewegung;
    this.position = position;
    this.orientierung = orientierung;
    this.setbvbImage();
  }

  /**
   * Konstruktor
   * @param name - nicht null, nicht ""
   * @param bewegung - nicht null
   */
  public BraitenbergVehikel(String name, BVBewegung bewegung) {
    this(name, bewegung, new Vektor2(0, 0), new Vektor2(0, 1));
  }

  /**
   * Berechnet die Bewegung des Vehikels aus den aktuellen Sensorwerten.
   */
  public void bewege() {
    double umdrehungenLinks = bewegung.berechneMotorAnsteuerungLinks(
        sensorWert[Richtung.LINKS.ordinal()],
        sensorWert[Richtung.RECHTS.ordinal()]) * getMaxUmdrehungenProSek();
    double umdrehungenRechts = bewegung.berechneMotorAnsteuerungRechts(
        sensorWert[Richtung.LINKS.ordinal()],
        sensorWert[Richtung.RECHTS.ordinal()]) * getMaxUmdrehungenProSek();
    double motorBewegungLinks =
        umdrehungenLinks * Math.PI * getRadRadius() * 2.0;
    double motorBewegungRechts =
        umdrehungenRechts * Math.PI * getRadRadius() * 2.0;
    bewege(motorBewegungLinks, motorBewegungRechts);
    setChanged();
    notifyObservers(position);
  }

  /**
   * Setzt den Sensorwert des angegebenen Sensors. Die Werte muessen aus [0,1]
   * kommen.
   */
  public void setSensorwert(Richtung richtung, double wert) {
    assert (wert >= 0 && wert <= 1);
    sensorWert[richtung.ordinal()] = wert;
  }

  /**
   * Rotation der Orientierung in Grad, 0 Grad = (0,1), aufsteigend gegen den
   * Uhrzeigersinn.
   */
  public double getRotationGradGegenUhrzeigersinn() {
    double cosWinkel = new Vektor2(0, 1).skalarProdukt(orientierung);
    double winkel = Math.acos(cosWinkel) * 180.0 / Math.PI;
    if (orientierung.skalarProdukt(new Vektor2(1, 0)) > 0) {
      winkel = -winkel;
    }
    return winkel;
  }

  /**
   * Rotation der Orientierung in Grad, 0 Grad = (0,1), aufsteigend im
   * Uhrzeigersinn.
   */
  public double getRotationGradImUhrzeigersinn() {
    return -getRotationGradGegenUhrzeigersinn();
  }

  /**
   * Bewegt das Vehikel basierend auf der Laufdistanz der beiden Raeder.
   */
  protected void bewege(double streckeLinks, double streckeRechts) {
    if (Math.abs(streckeLinks - streckeRechts) < 1e-5) {
      position = position.addiere(orientierung.skaliere(streckeLinks));
    } else {
      Vektor2 rotationszentrum = null;
      double winkelBogenmass = 0;
      if (streckeLinks <= streckeRechts) {
        double x =
            (-seitenlaenge * streckeLinks) / (streckeLinks - streckeRechts);
        winkelBogenmass = streckeRechts / (x + seitenlaenge);
        rotationszentrum =
            position.subtrahiere(orientierung.skaliere(seitenlaenge / 2.0))
                .addiere(getLinksVektor().skaliere(seitenlaenge / 2.0 + x));
      } else {
        double x =
            (seitenlaenge * streckeRechts) / (streckeLinks - streckeRechts);
        winkelBogenmass = -streckeLinks / (x + seitenlaenge);
        rotationszentrum =
            position.subtrahiere(orientierung.skaliere(seitenlaenge / 2.0))
                .addiere(getRechtsVektor().skaliere(seitenlaenge / 2.0 + x));
      }
      position = position.subtrahiere(rotationszentrum);
      position = position.rotiere(winkelBogenmass);
      position = position.addiere(rotationszentrum);
      orientierung = orientierung.rotiere(winkelBogenmass);
      orientierung.normieren();
    }
  }

  /**
   * Liefert die Position eines Sensors.
   */
  public Vektor2 getSensorPosition(Richtung richtung) {
    if (richtung == Richtung.LINKS) {
      return position.addiere(orientierung.skaliere(seitenlaenge / 2.0))
          .addiere(getLinksVektor().skaliere(seitenlaenge / 2.0));
    } else {
      return position.addiere(orientierung.skaliere(seitenlaenge / 2.0))
          .addiere(getRechtsVektor().skaliere(seitenlaenge / 2.0));
    }
  }

  /**
   * Liefert einen Vektor, nach rechts zur Fahrtrichtung zeigt.
   */
  private Vektor2 getRechtsVektor() {
    return orientierung.rotiere(-Math.PI / 2.0);
  }

  /**
   * Liefert einen Vektor, nach rechts zur Fahrtrichtung zeigt.
   */
  private Vektor2 getLinksVektor() {
    return orientierung.rotiere(Math.PI / 2.0);
  }

  /**
   * Getter fuer Seitenlaenge
   * @return seitenlaenge
   */
  public double getSeitenlaenge() {
    return seitenlaenge;
  }

  /**
   * Getter fuer RadRadius
   * @return radRadius
   */
  public double getRadRadius() {
    return radRadius;
  }

  /**
   * Setter fuer Position
   * @param position
   */
  public void setPosition(Vektor2 position) {
    this.position = position;
  }

  /**
   * Setter fuer Orientierung
   * @param orientierung
   */
  public void setOrientierung(Vektor2 orientierung) {
    this.orientierung = orientierung;
  }

  /**
   * Getter fuer max U/Sek
   * @return maxMotorUmdrehungenProSek
   */
  public double getMaxUmdrehungenProSek() {
    return maxMotorUmdrehungenProSek;
  }

  /**
   * Getter fuer Bewegung
   * @return bewegung
   */
  public BVBewegung getBewegung() {
    return bewegung;
  }

  /**
   * Setter fuer Bewegung
   * @param bewegung
   */
  public void setBewegung(BVBewegung bewegung) {
    this.bewegung = bewegung;
    this.setbvbImage();
    setChanged();
    notifyObservers(bewegung);
  }

  /**
   * Getter fuer Name
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Gibt Position zurueck
   * @return position
   */
  public Vektor2 getPosition() {
    return position;
  }

  /**
   * Gibt Orientierung zurueck
   * @return orientierung
   */
  public Vektor2 getOrientierung() {
    return orientierung;
  }

  /**
   * Gibt SensorWert zurueck
   * @param richtung
   * @return sensorWert
   */
  public double getSensorWert(Richtung richtung) {
    return sensorWert[richtung.ordinal()];
  }

  /**
   * Gibt formatierten String zurueck
   * @return 'einen String'
   */
  @Override
  public String toString() {
    return "Braitenberg-Vehikel " + getName() + " (p: " + position + ", o: "
        + orientierung + ")";
  }
  
  /**
   * Setzt bvbImage
   */
  public void setbvbImage(){
	  if(bewegung instanceof BVBewegungAttraktion){
		  bvbImage = new Image("aufgabe4/assets/icon_attraktion.png");
		  //System.out.println("ATT_ICON_geht");
	  }
	  if(bewegung instanceof BVBewegungAbstossung){
		  bvbImage = new Image("aufgabe4/assets/icon_abstossung.png");
		  //System.out.println("ABST_ICON_geht");
	  }
  }//END method
  
  /**
   * Gibt bvbImage zurueck
   * @return bvbImage
   */
  public Image getbvbImage(){
	  return bvbImage;
  }
  
}
