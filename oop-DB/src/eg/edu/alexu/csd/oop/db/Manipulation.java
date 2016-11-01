package eg.edu.alexu.csd.oop.db;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Manipulation { // update , insert , delete

	Selection2 selectObj = new Selection2();
	TableCreation creat = new TableCreation();

	// update data --> get table array and update then rewrite table array in
	// xml file
	// parameters : path to table to update , table name , attributes to be
	// update and their new values
	public int update(String path, String tableName, String required,
			String conditionParameter, String operator, String conditionValue) {

		tableName = tableName.trim();
		Object[][] table = null;
		int counter = 0;

		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();
			// find root element
			NodeList nList = doc.getElementsByTagName("table");
			org.w3c.dom.Node nNode = nList.item(0);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				// create array with update data
				String[] req = required.split(",");
				String[][] updateTable = new String[req.length][2];
				for (int i = 0; i < updateTable.length; i++) {
					req[i] = req[i].trim();
					updateTable[i] = req[i].split("=");
				}
				// get all attributes in table
				Element element = (Element) nNode;
				// check valid column attribute and get index of given
				// attributes
				String[] attributes = element
						.getElementsByTagName("attributes").item(0)
						.getTextContent().split(",");
				for (int i = 0; i < updateTable.length; i++) {
					boolean valid = false;
					for (int j = 0; j < attributes.length; j++) {
						if (attributes[j].contains(updateTable[i][0])) {
							updateTable[i][0] = "" + j;
							valid = true;
						}
					}
					if (!valid) {
						throw new SQLException("table not have column named "
								+ updateTable[i][0]);
					}
				}
				// check valid condition
				int conditionColumn = 0;
				if (conditionParameter != null) {
					conditionParameter = conditionParameter.trim();
					boolean valid = false;
					for (int j = 0; j < attributes.length; j++) {
						if (attributes[j].contains(conditionParameter)) {
							conditionParameter = "" + j;
							valid = true;
						}
					}
					if (!valid) {
						throw new SQLException("table not have column named "
								+ conditionParameter);
					}

					// get index of condition col
					conditionColumn = Integer.parseInt(conditionParameter);

				}
				// valid --> return table array to edit for update
				table = selectObj.select(path, tableName, null, true);
				if (table == null || table.length == 1) {
					return 0;
				}
				for (int i = 1; i < table.length; i++) {
					if (operator != null) {
						conditionValue = conditionValue.trim();
						switch (operator) {
						case "=":
							if (table[i][conditionColumn].toString().equals(
									conditionValue)) {
								counter++;
								// this row achive the condition --> update it
								for (int j = 0; j < updateTable.length; j++) {
									int colToSet = Integer
											.parseInt(updateTable[j][0]);
									table[i][colToSet] = updateTable[j][1];
								}
							}
							break;

						case ">":
							// must be int data type
							if (!table[i][conditionColumn].equals("null")
									&& (int) table[i][conditionColumn] < Integer
											.parseInt(conditionValue)) {
								// this row achive the condition --> update it
								counter++;
								for (int j = 0; j < updateTable.length; j++) {
									int colToSet = Integer
											.parseInt(updateTable[j][0]);
									table[i][colToSet] = updateTable[j][1];
								}
							}
							break;

						case "<":
							// must be int data type
							if (!table[i][conditionColumn].equals("null")
									&& (int) table[i][conditionColumn] < Integer
											.parseInt(conditionValue)) {
								// this row achive the condition --> update it
								counter++;
								for (int j = 0; j < updateTable.length; j++) {
									int colToSet = Integer
											.parseInt(updateTable[j][0]);
									table[i][colToSet] = updateTable[j][1];
								}
							}
							break;

						default:
							return 0;
						}
					} else { // null --> no condition
						counter++;
						for (int j = 0; j < updateTable.length; j++) {
							int colToSet = Integer.parseInt(updateTable[j][0]);
							table[i][colToSet] = updateTable[j][1];
						}
					}
				}
				// rewrite table in xml file
				creat.creatTable(path, tableName, table);

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Exception in file");
			return 0;

		}

		return counter;
	}

	// insert data --> get table array and add new row with new data then
	// rewrite table in xml file
	// parameters : path to table to update , table name , new data , columns of
	// table in which insert new data
	public int insert(String path, String tableName, String data, String columns) {
		tableName = tableName.trim();
		Object[][] table = null;
		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();

			// find root element
			NodeList nList = doc.getElementsByTagName("table");
			org.w3c.dom.Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String[] rowToInsert = null;
				// check valid data
				if (columns != null) {
					// check #of columns and values
					if (columns.split(",").length != data.split(",").length) {
						throw new SQLException(
								"Not match number of columns and values");
					}
					// check valid column attribute and get index of given
					// attributes
					String[] attributes = element
							.getElementsByTagName("attributes").item(0)
							.getTextContent().split(",");
					String[] columnsAttrib = columns.split(",");
					String[] dataArray = data.split(",");
					for (int i = 0; i < columnsAttrib.length; i++) {
						columnsAttrib[i] = columnsAttrib[i].trim();
						dataArray[i] = dataArray[i].trim();
						boolean valid = false;
						for (int j = 0; j < attributes.length; j++) {

							if (attributes[j].contains(columnsAttrib[i])) {
								columnsAttrib[i] = "" + j;
								valid = true;
								if (attributes[j].contains("varchar")) {
									if (!(dataArray[i].charAt(0) == '\'' && dataArray[i]
											.charAt(dataArray[i].length() - 1) == '\'')) {
										throw new SQLException(dataArray[i]
												+ "must be varchar");

									}
								}
							}
						}
						if (!valid) {
							throw new SQLException(
									"table not hase column named "
											+ columnsAttrib[i]);
						}
					}
					// insert data in selected columns ,else be null
					rowToInsert = new String[attributes.length];
					for (int i = 0; i < columnsAttrib.length; i++) {
						rowToInsert[Integer.parseInt(columnsAttrib[i])] = dataArray[i];
					}
				} else {
					String[] dataArr = data.split(",");
					// check #of values equals to #of attributes
					String[] attrib = element
							.getElementsByTagName("attributes").item(0)
							.getTextContent().split(",");
					if (dataArr.length != attrib.length) {
						throw new SQLException(
								"Not match number of values and columns of table");

					}
					// new data now valid
					for (int i = 0; i < dataArr.length; i++) {
						dataArr[i] = dataArr[i].trim();
					}
					rowToInsert = dataArr;
				}

				// get array table to insert in it
				table = selectObj.select(path, tableName, null, true);
				// creat new table with size of old table + 1
				Object[][] newTable = new Object[table.length + 1][table[0].length];
				// put data in new table
				for (int i = 0; i < newTable.length - 1; i++) {
					newTable[i] = table[i];
				}
				// add new row
				newTable[newTable.length - 1] = rowToInsert;
				// save new table
				creat.creatTable(path, tableName, newTable);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		} catch (ParserConfigurationException | SAXException | IOException e) {
			System.out.println("Exception in file");
			return 0;
		}

		return 1;

	}
	/*

	public int Delete(String path, String tableName, String condition,
			String operator, String value) {
		int counter = 0;
		
		condition = condition.trim();
		value = value.trim();

		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			doc.getDocumentElement().getNodeName();

			// find root element
			NodeList nList = doc.getElementsByTagName("table");
			org.w3c.dom.Node nNode = nList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) nNode;
				String[] conditioncol = element
						.getElementsByTagName(condition).item(0)
						.getTextContent().split(",");
				boolean[] rowToDelete = new boolean[conditioncol.length];
				for(int i=0 ; i<conditioncol.length ; i++){
					
					switch (operator) {
					case "=":
						log("da5l===", false);
						if (conditioncol[i].toString().equalsIgnoreCase(value)) {
							//rowToDelete[i] = true;
							// count #of delete
							counter++;
							log("zad elmafrood", false);

						}
						break;

					case ">": // must be int type
						if (!conditioncol[i].equals("null")
								&& Integer.parseInt(conditioncol[i].toString()) > Integer
										.parseInt(value)) {
							//rowToDelete[i] = true;
							// count #of delete
							counter++;
						}
						break;

					case "<": // must be int type
						if (!conditioncol[i].equals("null")
								&& Integer.parseInt(conditioncol[i].toString()) < Integer
										.parseInt(value)) {
							//rowToDelete[i] = true;
							// count #of delete
							counter++;
						}
						break;

					default:
						throw new RuntimeException("not valid operator");
						// return 0;
					}

					
				}

			}

		} catch (Exception e) {
			throw new RuntimeException();
		}
		return counter;
	}
	*/

	// delete data --> get table array and mark row that achieves the condition,
	// then rewrite table in xml file
	// parameters : path to table to update , table name , condition of delete
	// --> if null delete all data in table
	
	public int Delete(String path, String tableName, String conditionParameter,
			String operator, String conditionValue) {

		// tableName = tableName.trim();
		if (conditionParameter != null) {
			conditionParameter = conditionParameter.trim();
			conditionValue = conditionValue.trim();
			operator = operator.trim();
		}

		Object[][] table;
		boolean[] rowToDelete;
		int counter = 0;
		if (conditionParameter != null) {
			// delete under a condition
			// get condition column only
			table = selectObj.select(path, tableName, conditionParameter, true);
			if (table == null) {
				// return 0;
				log("-_-", false);
				throw new RuntimeException("empty");
			}
			log(conditionParameter, false);
			log(operator, false);
			log(conditionValue, false);

			// boolean array --> true if row will delete
			rowToDelete = new boolean[table.length];
			for (int i = 1; i < table.length; i++) {
				switch (operator) {
				case "=":
					log("da5l===", false);
					if (table[i][0].toString().equalsIgnoreCase(conditionValue)) {
						rowToDelete[i] = true;
						// count #of delete
						counter++;
						log("zad elmafrood", false);

					}
					break;

				case ">": // must be int type
					if (!table[i][0].equals("null")
							&& Integer.parseInt(table[i][0].toString()) > Integer
									.parseInt(conditionValue)) {
						rowToDelete[i] = true;
						// count #of delete
						counter++;
					}
					break;

				case "<": // must be int type
					if (!table[i][0].equals("null")
							&& Integer.parseInt(table[i][0].toString()) < Integer
									.parseInt(conditionValue)) {
						rowToDelete[i] = true;
						// count #of delete
						counter++;
					}
					break;

				default:
					throw new RuntimeException("not valid operator");
					// return 0;
				}

			}
			// get table array to update
			table = selectObj.select(path, tableName, null, true);
			// creat new table to save data not have to been deleted
			Object[][] newTable = new Object[table.length - counter][table[0].length];
			// put true rows in new array
			int index = 0;
			for (int i = 0; i < table.length; i++) {
				if (!rowToDelete[i]) {
					newTable[index] = table[i];
					index++;
				}
			}
			creat.creatTable(path, tableName, newTable);

		} else { // delete all data , but not structure of table
			table = selectObj.select(path, tableName, null, true);
			Object[][] newTable = new Object[1][table[0].length];
			newTable[0] = table[0];
			// #of delete equal #of rows-1
			counter = table.length - 1;
			creat.creatTable(path, tableName, newTable);
		}

		log("-_____-", false);
		return counter;

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
