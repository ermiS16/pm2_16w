package aufgabe1.xml.solution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Anwendung {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException{
		File newFile = new File("src/sensor.xml");
		Sensor sensor = new Sensor();
		Input input = new Input();
		
		try {
			sensor = input.readXMLData(newFile.getAbsolutePath());
		} catch (SAXException e) {
			e.printStackTrace();
		}
		System.out.println(sensor.toString());
		
		
		Messung messung1 = new Messung();
		Messung messung2 = new Messung();
		Messung messung3 = new Messung();
		messung1.setWert(23.3);
		messung1.setLocalDateTime();
		messung2.setWert(23.4);
		messung2.setLocalDateTime();
		messung3.setWert(22.7);
		messung3.setLocalDateTime();
		
		List<Messung> listMessung = new ArrayList<Messung>();
		listMessung.add(messung1);
		listMessung.add(messung2);
		listMessung.add(messung3);
		
		Sensor sensor2 = new Sensor(listMessung,"Temperatur Badezimmer");
		Output output = new Output();
		output.writeXMLData(sensor2);
		
		File newFile2 = new File("src/newSensor.xml");
		Sensor sensor3 = new Sensor();
		try {
			sensor3 = input.readXMLData(newFile2.getAbsolutePath());
		} catch (SAXException e) {
			e.printStackTrace();
		}
		System.out.println(sensor3.toString());
		
	}
	
}
