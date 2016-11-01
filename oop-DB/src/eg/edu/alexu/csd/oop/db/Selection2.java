package eg.edu.alexu.csd.oop.db;

import java.io.File;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Selection2 {
	// select table from database
	// parameters : path of selected xml file , file name , columns to selected
	public Object[][] select(String path, String tableName, String required , boolean check) {

		Object[][] table = null;

		log(required,true);
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();

			// find root element which name table
			NodeList nList = doc.getElementsByTagName("table");
			org.w3c.dom.Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				// if selected all put all element in required
				if (required == null) {
					required = element.getElementsByTagName("attributes")
							.item(0).getTextContent();
				} else { // check valid required
					String temp = element.getElementsByTagName("attributes")
							.item(0).getTextContent();
					String[] attributes = temp.split(",");
					String[] reqElement = required.split(",");
					required = "";
					for (int i = 0; i < reqElement.length; i++) {
						reqElement[i] = reqElement[i].trim();
						boolean valid = false;
						for (int j = 0; j < attributes.length; j++) {
							if (attributes[j].contains(reqElement[i])) {
								required += attributes[j] +",";
								valid = true;

							}
						}
						if (!valid) {
							throw new SQLException(
									"table not hase column named "
											+ reqElement[i]);
						}
					}
					
				}
				log("3daaa",false);


				String[] reqElement = required.split(",");
				// get dimensions of table array
				// case table not has data
				if (nNode.getChildNodes().getLength() == 1) {
					table = new Object[1][reqElement.length];
					table[0] = reqElement;
					log("no data in table",false);

				} else {
					log("hereeeeeee",false);

					String temp = element.getElementsByTagName(reqElement[0])
							.item(0).getTextContent();
					int row = temp.split(",").length + 1;
					int col = reqElement.length;
					// creat table array
					table = new Object[row][col];
					// put required data in table
					for (int i = 0; i < col; i++) {
						table[0][i] = reqElement[i];
						// split values of each attribute
						temp = element.getElementsByTagName(reqElement[i])
								.item(0).getTextContent();
						String[] attValues = temp.split(",");
						for (int j = 1; j < row; j++) {
							if (!attValues[j - 1].equals("null")
									&& reqElement[i].contains("INT")) {
								// int data type
								table[j][i] = Integer
										.parseInt(attValues[j - 1]);
							} else { // varchar
								table[j][i] = attValues[j - 1];
							}
						}

					}

				}
			}
		} catch (Exception e) {
			System.out.println("error select");
			log("exception",false);

			throw new RuntimeException();

		}
		if(check){
			return table;
		}else{
			
			Object[][] newTable = new Object[table.length-1][table[0].length];
			for(int i=0 ; i<newTable.length ; i++){
				newTable[i] = table[i+1];
			}
			return newTable;
		}
		

	}
	
	private static final String FILE_NAME = "/debug/SafaaAboElfotouhWally.log";

	public static void log(String str, boolean delete) {
		try {
			str += "\n";
			if (delete)
				new File(FILE_NAME).delete();
			java.nio.file.Files.write(java.nio.file.Paths.get(FILE_NAME), str
					.getBytes(),
					new File(FILE_NAME).exists() ? StandardOpenOption.APPEND
							: StandardOpenOption.CREATE);
		} catch (Throwable e1) {
			e1.printStackTrace();
		}
	}



}

