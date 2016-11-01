package eg.edu.alexu.csd.oop.db;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TableCreation {

	public void creatTable(String path, String tableName, Object[][] table) {

		try {

			int row = table.length;
			int col = table[0].length;

			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			// creat root element
			Element rootElement = doc.createElement("table");
			doc.appendChild(rootElement);
			rootElement.setAttribute("id", tableName);
			// first element always 'attributes'
			Element attributes = doc.createElement("attributes");
			rootElement.appendChild(attributes);
			// get all attributes from columns of table array --> first row
			String attrib = "";
			for (int i = 0; i < table[0].length; i++) {
				table[0][i] = table[0][i].toString().trim();
				table[0][i] = table[0][i].toString().replace(" ", "_");
				attrib += table[0][i] + ",";
			}
			attributes.appendChild(doc.createTextNode(attrib));
			if (row != 1) {
				// creat elements depends on attributes
				for (int i = 0; i < col; i++) {
					Element element = doc.createElement(table[0][i].toString());
					rootElement.appendChild(element);
					String temp = "";
					for (int j = 1; j < row; j++) {
						table[j][i] = table[j][i].toString().trim();
						temp += table[j][i] + ",";
					}
					element.appendChild(doc.createTextNode(temp));
				}
			}

			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);

		} catch (ParserConfigurationException pce) {
			// pce.printStackTrace();
			throw new RuntimeException("File Cannot be Read");
		} catch (TransformerException tfe) {
			// tfe.printStackTrace();
			throw new RuntimeException("File Cannot be Read");
		}

		return;
	}
}
