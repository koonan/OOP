package eg.edu.alexu.csd.oop.db;

import java.io.File;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;

public class Engine implements Database {
	private File currDataBase;
	private Parser valid = new Parser();
	private Manipulation manipulation = new Manipulation();

	@Override
	public String createDatabase(String databaseName, boolean dropIfExists) {
		// TODO Auto-generated method stub
		// log("deleted",true);
		try {
			if (!dropIfExists) {
				try {
					this.executeStructureQuery("CREATE DATABASE "
							+ databaseName.toUpperCase());
					currDataBase = new File(System.getProperty("user.dir")
							+ File.separatorChar + databaseName.toUpperCase());
					return (currDataBase.toString());

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			} else {
				try {
					currDataBase = new File(System.getProperty("user.dir")
							+ File.separatorChar + databaseName.toUpperCase());
					this.executeStructureQuery("DROP DATABASE "
							+ databaseName.toUpperCase());
					return (currDataBase.toString());

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;

				}
			}
		} catch (RuntimeException e) {
			currDataBase = null;
			return null;
		}

	}

	@Override
	public boolean executeStructureQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		String required[] = valid.validate(query);
		if (required != null) {
			if (required[0].equalsIgnoreCase("drop")
					&& required[1].equalsIgnoreCase("table")) {
				if (currDataBase != null) {
					try {
						File xmlFile = new File(currDataBase.toString()
								+ File.separatorChar
								+ required[2].toUpperCase() + ".xml");

						if (xmlFile.exists()) {
							xmlFile.deleteOnExit();
							return true;
						} else {
							return false;
						}
					} catch (Exception e) {
						throw new SQLException("No valid name");
					}
				} else {
					throw new SQLException("No DataBase");
				}
			} else if (required[0].equalsIgnoreCase("drop")
					&& required[1].equalsIgnoreCase("database")) {
				try {

					File dataBaseFile = new File(System.getProperty("user.dir")
							+ File.separatorChar + required[2].toUpperCase());
					currDataBase = dataBaseFile;
					
					if (dataBaseFile.exists()) {
						// dataBaseFile.deleteOnExit();
						String files[] = dataBaseFile.list();
						for (String temp : files) {
							File fileDelete = new File(dataBaseFile, temp);
							fileDelete.delete();
						}
						dataBaseFile.mkdir();
						return true;
					} else {
						dataBaseFile.mkdir();
						return false;
					}
				} catch (Exception e) {
					currDataBase = null;
					throw new RuntimeException(e);
				}
			} else if (required[0].equalsIgnoreCase("create")
					&& required[1].equalsIgnoreCase("database")) {
				try {
					
					File dataBaseFile = new File(System.getProperty("user.dir")
							+ File.separatorChar + required[2].toUpperCase());
					currDataBase = dataBaseFile;
					if (!dataBaseFile.exists()) {
						dataBaseFile.mkdir();

						return true;
					} else {
						// dataBaseFile.mkdir();
						return true;
					}
				} catch (Exception e) {

					currDataBase = null;
					throw new RuntimeException(e);
				}
			} else if (required[0].equalsIgnoreCase("create")
					&& required[1].equalsIgnoreCase("table")) {
				if(required.length != 4){
					throw new RuntimeException("not valid table structure");
				}
				
				if (currDataBase != null) {
					try {
						File xmlFile = new File(currDataBase.toString()
								+ File.separatorChar
								+ required[2].toUpperCase() + ".xml");
						if (xmlFile.exists()) {
							return false;
						} else {
							String path = xmlFile.toString();
							Object[][] table = new Object[1][required[3]
									.split(",").length];
							table[0] = required[3].split(",");
							
							 for (int i = 0; i < table[0].length; i++) { if
							 (!table[0][i].toString().toLowerCase()
							 .contains("int") && !table[0][i].toString()
							 .toLowerCase() .contains("varchar")) { throw new
							 SQLException("Invalid"); }
							 
							 }
							 

							TableCreation create = new TableCreation();
							create.creatTable(path, required[2].toUpperCase(),
									table);
							return true;
						}
					} catch (Exception e) {
						throw new SQLException("No DataBase");

					}
				}

				else {
					throw new RuntimeException("No DataBase");
				}
			}

		}
		throw new SQLException("In Valid");

	}

	@Override
	public Object[][] executeQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		Object[][] table = null;
		String[] required = valid.validate(query);
		if (required == null || !required[0].equalsIgnoreCase("SELECT")) {
			throw new SQLException("Invalid here");
		} else {
			// call select code
			Selection2 selectObj = new Selection2();
			SelectionUnderCondition conditionSelect = new SelectionUnderCondition();
			// four types of select , select all if optional = null
			String optional = null;
			if (required.length == 5 || required.length == 6) {
				String condition = required[2];
				String operator = required[3];
				String value = required[4];
				String tableName = required[1];
				if (required.length == 6) {
					optional = required[1];
					tableName = required[2];
					condition = required[3];
					operator = required[4];
					value = required[5];
				}
				String path = currDataBase.toString() + File.separatorChar
						+ tableName.toUpperCase() + ".xml";
				table = conditionSelect.conditionSelect(path,
						tableName.toUpperCase(), optional, condition, operator,
						value);
			} else if (required.length == 3 || required.length == 2) {
				String tableName = required[1];
				if (required.length == 3) {
					optional = required[1];
					tableName = required[2];
				}
				String path = currDataBase.toString() + File.separatorChar
						+ tableName.toUpperCase() + ".xml";
				table = selectObj.select(path, tableName.toUpperCase(),
						optional, false);
			}

		}

		return table;
	}

	@Override
	public int executeUpdateQuery(String query) throws SQLException {
		// TODO Auto-generated method stub
		// parse given query
		String[] required = valid.validate(query);
		// find which update needed
		if (required != null) {
			if (currDataBase != null) {
				if (required[0].equalsIgnoreCase("UPDATE")) {
					System.out.println("in update");
					try {
						File xmlFile = new File(currDataBase.toString()
								+ File.separatorChar
								+ required[1].toUpperCase() + ".txt");
						xmlFile.deleteOnExit();
						xmlFile = new File(currDataBase.toString()
								+ File.separatorChar
								+ required[1].toUpperCase() + ".xml");
						if (xmlFile.exists() && !xmlFile.isDirectory()) {
							System.out.println("in update");
							String path = xmlFile.toString();
							String condition = null;
							String operator = null;
							String value = null;

							if (required.length == 7) {
								condition = required[4];
								operator = required[5];
								value = required[6];
							}

							return manipulation.update(path,
									required[1].toUpperCase(), required[2],
									condition, operator, value);
						} else {
							//xmlFile.deleteOnExit();
							throw new SQLException("Wrong table");
						}

					} catch (Exception e) {
						//return 0;
						throw new SQLException("Wrong table");

					}
				} else if (required[0].equalsIgnoreCase("INSERT")) {
					try {

						File xmlFile = new File(currDataBase.toString()
								+ File.separatorChar
								+ required[1].toUpperCase() + ".xml");
						String path = xmlFile.toString();
						String columns = null;
						String values = required[2];
						if (required.length == 4) {
							columns = required[2];
							values = required[3];
						}
						return manipulation.insert(path,
								required[1].toUpperCase(), values, columns);
					} catch (Exception e) {
						return 0;
					}
				}

				else if (required[0].equalsIgnoreCase("DELETE")) {
					log(query+"\n", true);
					try {
						required[1] = required[1].trim();
						File xmlFile = new File(currDataBase.toString()
								+ File.separatorChar
								+ required[1].toUpperCase() + ".xml");
						String path = xmlFile.toString();
						String condition = null;
						String operator = null;
						String value = null;
						if (required.length == 5) {
							condition = required[2];
							operator = required[3];
							value = required[4];
						}
						return manipulation.Delete(path,
								required[1].toUpperCase(), condition, operator,
								value);

					} catch (Exception e) {
						return 0;
					}

				}
			}
			throw new SQLException("Not DataBase");

		} else {
			throw new SQLException("Not valid");
		}

	}

	private static final String FILE_NAME = "/debug/SafaaAboElfotouhWally.log";

	private static void log(String str, boolean delete) {
		try {
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