package aufgabe1.xml.solution;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Input create an Sensor, based on the data we get from a specific XML File.
 * @author Eric Misfeld, Simon Felske
 * @version 16.10.2016
 *
 */

public class Input {
	/**
	 * Building a Document from a XML.
	 * @param filename Path of the XML File, we want to read.
	 * @return sensor, it's the build Sensor, based on the data we got.
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public Sensor readXMLData(String filename) throws IOException, ParserConfigurationException, SAXException {
		//DBF and DB needed for creating an Document with w3c.dom
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Sensor sensor = new Sensor();
		//Try to open the file and getting the root element.
		try {
			Document doc = builder.parse(new File(filename));
			sensor = processSensor((Element) doc.getDocumentElement());
		} catch (SAXException | IOException e) {
			System.out.println("Fehler beim einlesen der XML Datei");
		}
		return sensor;
	}
	/**
	 * Building the Sensor with the measurements we got from the childElements.
	 * @param element it's the first element in the XML
	 * @return Sensor, the Sensor we build.
	 */
	public Sensor processSensor(Element element) {
		/* FOR DEBUGGING ONLY
	    if (element != null && element.getNodeName().equals("Sensor")) {
	        System.out.print("Sensor gefunden, \n");
	    }
	    attributeOutput(element);
	    */
	    //As long there are ChildElements from Sensor, we will getting it.
		List<Messung> messListe = new ArrayList<Messung>();;
	    NodeList nodeList = element.getChildNodes();
	    int nodeListLength = nodeList.getLength();
	    for(int i=0; i<element.getChildNodes().getLength();i++){
	    	Node childNode = element.getChildNodes().item(i);
	    	if(childNode instanceof Element){
	    		Element childElement = (Element) childNode;
	    		if(childElement.getNodeName().equals("Messung")){
	    			Messung neueMessung = processMessung(childElement);
	    			messListe.add(neueMessung);
	    		}else{
	    			System.out.println("Unbekannter Knoten"); //Debug print
	    		}//end else
	    	}//end if
	    }//end for
	    Sensor sensor = new Sensor(messListe, element.getAttribute("id"));
	    return sensor;
	}//end method
	
	/**
	 * We setting the attributes inputWert and inputZeitstempel.
	 * @param element
	 */
	public Messung processMessung(Element element) {
		/*FOR DEBUGGING ONLY
	    if (element != null && element.getNodeName().equals("Messung")) {
	        System.out.print("Messung gefunden, \n");
	    }
	    attributeOutput(element);
	    */
		Messung messung = new Messung();
	    NamedNodeMap attribute = element.getAttributes();
		for (int i = 0; i < attribute.getLength(); i++) {
			Node attribut = attribute.item(i);
			switch(attribut.getNodeName()){
				case "wert":String tmpWert = replaceCommaWithDot(attribut.getNodeValue()); 
							messung.setWert(Double.parseDouble(tmpWert));
							break;
				case "zeitstempel":
							DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
							messung.setLocalDateTime(LocalDateTime.parse(attribut.getNodeValue(),dateFormatter));
			}
		}
		return messung;
	}
	
	/**
	 * Replace the comma from a String, we want to parse to an double, to a dot.
	 * @param string wert we want to parse in double
	 * @return result, string with dots instead of commas.
	 */
	public String replaceCommaWithDot(String string){
		String result = "";
		
		for(int i=0; i<string.length();i++){
			if(string.charAt(i) == ','){
				result += ".";
			}else{
				result += string.charAt(i);
			}
		}		
		return result;
	}
	
	//GETTER
	
	/**
	 * FOR DEBUGGING ONLY
	 * @param element, from which we want the attributes.
	 */
	public void attributeOutput(Element element) {

		NamedNodeMap attribute = element.getAttributes();
		for (int i = 0; i < attribute.getLength(); i++) {
			Node attribut = attribute.item(i);
			System.out.println(attribut.getNodeName() + ": " + attribut.getNodeValue());
		}
	}
}
