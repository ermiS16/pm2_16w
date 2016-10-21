package aufgabe1.xml.solution;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * Output is creating an XML from a Sensor Object. * 
 * 
 * @author Eric Misfeld, Simon Felske
 * @version 19.10.2016
 *
 */

public class Output {
	
	/*
	 * Creates a XML, based on the data it gets from a Sensor Object.
	 */
	
	public void writeXMLData(Sensor sensor) throws IOException, ParserConfigurationException, SAXException, TransformerException{
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = builderFactory.newDocumentBuilder();
		
		Document doc = builder.newDocument();
		Element root = doc.createElement("Sensor");
		root.setAttribute("id", sensor.getId());
		doc.appendChild(root);
		
		for(Messung messung : sensor.getListeMessung()){
			Element neueMessung = doc.createElement("Messung");
			root.appendChild(neueMessung);
			neueMessung.setAttribute("wert", String.valueOf(messung.getWert()));
			neueMessung.setAttribute("zeitstempel", messung.getLocalDateTime().toString());
			/*
			Attr wert = doc.createAttribute("wert");
			wert.setNodeValue(String.valueOf(messung.getWert()));
			neueMessung.setAttributeNode(wert);
			Attr zeitstempel = doc.createAttribute("zeitstempel");
			zeitstempel.setNodeValue(messung.getLocalDateTime().toString());
			neueMessung.setAttributeNode(zeitstempel);
			*/
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource src = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("src/newSensor.xml"));
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "sensor.dtd");
		transformer.transform(src, result);
	}
}
