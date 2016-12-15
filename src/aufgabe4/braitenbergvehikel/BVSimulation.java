package aufgabe4.braitenbergvehikel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import aufgabe4.braitenbergvehikel.BraitenbergVehikel.Richtung;

/**
 * Simulation von Braitenberg-Vehikeln.
 * 
 * @author Philipp Jenke
 */
public class BVSimulation extends Observable{

  /**
   * Position des Signals.
   */
  private Vektor2 signal = new Vektor2(150, 200);

  /**
   * Liste der zu simulierenden Vehikel
   */
  private List<BraitenbergVehikel> vehikel =
      new ArrayList<BraitenbergVehikel>();

  public BVSimulation() {
  }

  /**
   * Fuehrt einen Simulationsschritt fuer alle Vehikel durch.
   */
  public void simulationsSchritt() {
    for (BraitenbergVehikel vehikel : this.vehikel) {
      // Berechne Sensorstaerke
      vehikel.setSensorwert(Richtung.LINKS,
          getSignalstaerke(vehikel.getSensorPosition(Richtung.LINKS),
              vehikel.getOrientierung()));
      vehikel.setSensorwert(Richtung.RECHTS,
          getSignalstaerke(vehikel.getSensorPosition(Richtung.RECHTS),
              vehikel.getOrientierung()));

      // Bewege vehikel
      vehikel.bewege();

    }
  }

  /**
   * Berechnet die Signalstaerke fuer einen Sensor durch die Lichtquelle.
   */
  private double getSignalstaerke(Vektor2 sensorPosition,
      Vektor2 orientierung) {
    Vektor2 d = signal.subtrahiere(sensorPosition);
    double entfernung = d.getNorm();
    d = d.skaliere(1.0 / entfernung);
    double cosWinkel = d.skalarProdukt(orientierung);
    if (cosWinkel < 0) {
      // Vehikel sieht vom Sensor weg.
      return 0;
    }

    // Winkel-basierte Signalstaerke
    return cosWinkel;
  }

  public void vehikelHinzufuegen(BraitenbergVehikel vehikel) {
    this.vehikel.add(vehikel);
  }

  public int getAnzahlVehike() {
    return vehikel.size();
  }

  /**
   * Getter fuer BV an Index
   * @param index
   * @return vehikel@index
   */
  public BraitenbergVehikel getVehikel(int index) {
    return vehikel.get(index);
  }

  /**
   * Getter fuer Signal
   * @return
   */
  public Vektor2 getSignal() {
    return signal;
  }

  /**
   * Setter fuer Signal
   * @param x
   * @param y
   */
  public void setSignal(double x, double y) {
    signal = new Vektor2(x, y);
    setChanged();
    notifyObservers(signal);
  }
}
