package eg.edu.alexu.csd.oop.db;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Database db = new Engine();
		db.createDatabase("Sample", true);
        try {
			db.executeStructureQuery(
			        "CREATE TABLE table_name1(column_name1 varchar       , column_name2 int, column_name3 varchar)        ");
			  db.executeUpdateQuery(
		                "INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 4)");
		        db.executeUpdateQuery(
		                "INSERT INTO table_name1(column_NAME1, COLUMN_name3, column_name2) VALUES ('value1', 'value3', 5)");
		        db.executeUpdateQuery(
		                "INSERT INTO table_name1(column_name1, COLUMN_NAME3, column_NAME2) VALUES ('value2', 'value4', 6)");
		        int x = db.executeUpdateQuery("DELETE From table_name1  WHERE coLUmn_NAME2=4");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      

	}
}
