package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XML {

	// write xml file
	public void writeFile(LinkedList<Shape> myList, String path)
			throws RuntimeException {

		try {
			Map<String, Double> shapeMap;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("Shapes");
			doc.appendChild(rootElement);

			// loop to get all shapes
			int index = 0;
			for (Shape getShape : myList) {

				// element
				Element shape = doc.createElement("shape");
				rootElement.appendChild(shape);
				// indexing elements
				shape.setAttribute("id", Integer.toString(index++));

				// class name
				Element className = doc.createElement("class");
				shape.appendChild(className);
				className.appendChild(doc.createTextNode(getShape.getClass()
						.getName()));

				// child position
				Element posX = doc.createElement("Position.x");
				shape.appendChild(posX);
				Element posY = doc.createElement("Position.y");
				shape.appendChild(posY);
				if (getShape.getPosition() != null) {

					// position x
					Point p = getShape.getPosition();
					posX.appendChild(doc.createTextNode(Double.toString(p.x)));

					// position y
					posY.appendChild(doc.createTextNode(Double.toString(p.y)));
				} else {

					posX.appendChild(doc.createTextNode("null"));
					posY.appendChild(doc.createTextNode("null"));
				}

				// child map to get properties
				Element map = doc.createElement("Map");
				shape.appendChild(map);
				Element check = doc.createElement("check");
				map.appendChild(check);

				if (getShape.getProperties() != null) {
					check.appendChild(doc.createTextNode("exist"));
					shapeMap = getShape.getProperties();
					for (Entry<String, Double> entry : shapeMap.entrySet()) {
						Element attribute = doc.createElement(entry.getKey());
						map.appendChild(attribute);
						if (entry.getValue() != null) {
							attribute.appendChild(doc.createTextNode(entry
									.getValue().toString()));
						} else {
							attribute.appendChild(doc.createTextNode("null"));
						}
					}
				} else {
					check.appendChild(doc.createTextNode("null"));
				}

				// child outer color
				Element color = doc.createElement("Color");
				shape.appendChild(color);
				if (getShape.getColor() != null) {
					Color c = getShape.getColor();
					color.appendChild(doc.createTextNode("" + c.getAlpha()));
				} else {
					color.appendChild(doc.createTextNode("null"));
				}

				// child fill color
				Element fillColor = doc.createElement("FillColor");
				shape.appendChild(fillColor);
				if (getShape.getFillColor() != null) {
					Color c = getShape.getFillColor();
					fillColor
							.appendChild(doc.createTextNode("" + c.getAlpha()));
				} else {
					fillColor.appendChild(doc.createTextNode("null"));
				}
			}

			// write the content into xmL file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			// path to save xmL file
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			// pce.printStackTrace();
			throw new RuntimeException("File Cannot be Read");
		} catch (TransformerException tfe) {
			// tfe.printStackTrace();
			throw new RuntimeException("File Cannot be Read");
		}
	}

	// Read xmL file
	public LinkedList<Shape> read(String path) throws FileNotFoundException {

		LinkedList<Shape> myList = new LinkedList<Shape>();
		Map<String, Double> map = new LinkedHashMap<String, Double>();
		Point posision;
		Shape shape;
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();
			NodeList nList = doc.getElementsByTagName("shape");

			for (int index = 0; index < nList.getLength(); index++) {
				org.w3c.dom.Node nNode = nList.item(index);
				nNode.getNodeName();
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					// read class name to create object from it
					String className = eElement.getElementsByTagName("class")
							.item(0).getTextContent();
					Constructor<?> c = Class.forName(className)
							.getConstructor();
					shape = (Shape) c.newInstance();

					// read position
					posision = new Point();
					if (!eElement.getElementsByTagName("Position.x").item(0)
							.getTextContent().contains("null")) {
						posision.x = (int) Double.parseDouble(eElement
								.getElementsByTagName("Position.x").item(0)
								.getTextContent());
					}
					if (!eElement.getElementsByTagName("Position.y").item(0)
							.getTextContent().contains("null")) {
						posision.y = (int) Double.parseDouble(eElement
								.getElementsByTagName("Position.y").item(0)
								.getTextContent());

					}
					shape.setPosition(posision);
					// read properties from map and set it to the new created
					// shape
					eElement.getElementsByTagName("map");
					map = shape.getProperties();
					if (!eElement.getElementsByTagName("check").item(0)
							.getTextContent().contains("null")) {

						for (Entry<String, Double> entry : map.entrySet()) {
							if (!eElement.getElementsByTagName(entry.getKey())
									.item(0).getTextContent().contains("null")) {
								entry.setValue(Double.parseDouble(eElement
										.getElementsByTagName(entry.getKey())
										.item(0).getTextContent()));
							}
						}
						shape.setProperties(map);
					}
					// read the outer color
					if (eElement.getElementsByTagName("Color").item(0)
							.getTextContent().contains("null"))
						;
					else {
						Color outerColor = new Color(Integer.parseInt(eElement
								.getElementsByTagName("Color").item(0)
								.getTextContent()));
						shape.setColor(outerColor);
					}

					// read fill color
					if (eElement.getElementsByTagName("FillColor").item(0)
							.getTextContent().contains("null"))
						;
					else {
						Color fillColor = new Color(Integer.parseInt(eElement
								.getElementsByTagName("FillColor").item(0)
								.getTextContent()));
						shape.setFillColor(fillColor);
					}

					// add shape to list
					myList.add(shape);
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(
					"there not valid path or file cannot be open");
		}
		return myList;
	}
}