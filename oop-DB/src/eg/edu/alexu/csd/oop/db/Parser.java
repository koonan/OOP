package eg.edu.alexu.csd.oop.db;

import java.io.File;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	Parser() {

	}

	public String[] validate(String query) {
		String arr[];
		String newQuery = query.toUpperCase();
		// log("\n\n\n"+query+"\n\n\n",false);
		// Paring CREATE DATABASE
		if (regexChecker("(?i)^\\s*(CREATE)\\s+(DATABASE)\\s+(.+)\\s*$",
				newQuery, 3) != null) {
			arr = regexChecker("(?i)^\\s*(CREATE)\\s+(DATABASE)\\s+(.+)\\s*$",
					newQuery, 3);
			return arr;

		}

		// Parsing DROP DATABASE
		// CREATE DATABASE database_name
		if (regexChecker("(?i)^\\s*(DROP)\\s+(DATABASE)\\s+(.+)\\s*$",
				newQuery, 3) != null) {
			arr = regexChecker("(?i)^\\s*(DROP)\\s+(DATABASE)\\s+(.+)\\s*$",
					newQuery, 3);
			return arr;

		}

		// Parsing CREATE TABLE
		// CREATE TABLE table_name
		// (
		// column_name1 data_type,
		// column_name2 data_type,
		// column_name3 data_type,
		// ...
		// )
		if (regexChecker(
				"(?i)^\\s*(CREATE)\\s+(TABLE)\\s+(.+)\\s*\\(\\s*(.*)\\s*\\)\\s*$",
				newQuery, 4) != null) {
			arr = regexChecker(
					"(?i)^\\s*(CREATE)\\s+(TABLE)\\s+(.+)\\s*\\(\\s*(.*)\\s*\\)\\s*$",
					newQuery, 4);
			return arr;

		}

		// Parsing DROP TABLE
		// DROP TABLE table_name
		if (regexChecker("(?i)^\\s*(DROP)\\s+(TABLE)\\s+(.+)\\s*$", newQuery, 3) != null) {
			arr = regexChecker("(?i)^\\s*(DROP)\\s+(TABLE)\\s+(.+)\\s*$",
					newQuery, 3);
			return arr;

		}

		// Parsing INSERT INTO
		// INSERT INTO table_name
		// (column1, column2, column3,...)
		// VALUES (value1, value2, value3,....)
		else if (regexChecker(
				"(?i)^\\s*(INSERT)\\s+INTO\\s+(.+)\\s*\\(\\s*(.+)\\)\\s*VALUES\\s*\\(\\s*(.+)\\)\\s*$",
				newQuery, 4) != null) {
			arr = regexChecker(
					"(?i)^\\s*(INSERT)\\s+INTO\\s+(.+)\\s*\\(\\s*(.+)\\)\\s*VALUES\\s*\\(\\s*(.+)\\)\\s*$",
					newQuery, 4);
			return arr;
		}

		// Parsing INSERT INTO
		// INSERT INTO table_name
		// VALUES (value1, value2, value3,....)
		else if (regexChecker(
				"(?i)^\\s*(INSERT)\\s+?INTO\\s+(.+)\\s+VALUES\\s*\\(\\s*(.+)\\)\\s*$",
				newQuery, 3) != null) {
			arr = regexChecker(
					"(?i)^\\s*(INSERT)\\s+?INTO\\s+(.+)\\s+VALUES\\s*\\(\\s*(.+)\\)\\s*$",
					newQuery, 3);
			return arr;
		}

		// Parsing Select
		// SELECT *
		// FROM table_name where
		else if (regexChecker(
				"(?i)^\\s*(SELECT)\\s+\\*\\s+FROM\\s+(.+)\\s+WHERE\\s+(.+)\\s*(<|>|=)\\s*(.+)\\s*$",
				newQuery, 2) != null) {
			arr = regexChecker(
					"(?i)^\\s*(SELECT)\\s+\\*\\s+FROM\\s+(.+)\\s+WHERE\\s+(.+)\\s*(<|>|=)\\s*(.+)\\s*$",
					newQuery, 5);
			return arr;
		}

		// Parsing SELECT
		// SELECT column_name(s)
		// FROM table_name
		// WHERE column_name operator value
		else if (regexChecker(
				"(?i)^\\s*(SELECT)\\s+(.+)\\s+FROM\\s+(.+)\\s+WHERE\\s+(.+)\\s*(<|>|=)\\s*(.+)\\s*$",
				newQuery, 6) != null) {
			arr = regexChecker(
					"(?i)^\\s*(SELECT)\\s+(.+)\\s+FROM\\s+(.+)\\s+WHERE\\s+(.+)\\s*(<|>|=)\\s*(.+)\\s*$",
					newQuery, 6);
			return arr;
		}

		// Parsing Select
		// SELECT *
		// FROM table_name
		else if (regexChecker("(?i)^\\s*(SELECT)\\s+\\*\\s+FROM\\s+(.+)\\s*$",
				newQuery, 2) != null) {
			arr = regexChecker("(?i)^\\s*(SELECT)\\s+\\*\\s+FROM\\s+(.+)\\s*$",
					newQuery, 2);
			return arr;
		}

		// Parsing Select
		// SELECT column_name(s)
		// FROM table_name
		// select\\s+\\*\\s+from\\s+(\\w+
		else if (regexChecker("(?i)^\\s*(SELECT)\\s+(.+)\\s+FROM\\s+(.+)\\s*$",
				newQuery, 3) != null) {
			System.out.println("yarrrab");
			arr = regexChecker(
					"(?i)^\\s*(SELECT)\\s+(.+)\\s+FROM\\s+(.+)\\s*$", newQuery,
					3);
			return arr;
		}

		// Parsing DELETE FROM TABLE
		// DELETE FROM table_name
		// WHERE some_column=some_value
		else if (regexChecker(
				"(?i)^\\s*(DELETE)\\s+FROM\\s+(.+)\\s+WHERE\\s+(.+)\\s*(<|>|=)\\s*(.+)\\s*$",
				newQuery, 5) != null) {
			arr = regexChecker(
					"(?i)^\\s*(DELETE)\\s+FROM\\s+(.+)\\s+WHERE\\s+(.+)\\s*(<|>|=)\\s*(.+)\\s*$",
					newQuery, 5);
			return arr;
		}

		// Parsing DLETE FROM TABLE
		// DELETE * FROM TABLE table_name
		// Delete the entire table

		else if (regexChecker("(?i)^\\s*(DELETE)\\s+\\*\\s+FROM\\s+(,+)\\s*$",
				newQuery, 2) != null) {
			System.out.println("sss");
			arr = regexChecker("(?i)^\\s*(DELETE)\\s+\\*\\s+FROM\\s+(.+)\\s*$",
					newQuery, 2);
			return arr;
		}

		// Parsing DELETE FROM TABLE
		// DELETE FROM TABLE table_name
		// Delete the entire table
		else if (regexChecker("(?i)^\\s*(DELETE)\\s+FROM\\s+(.+)\\s*$",
				newQuery, 2) != null) {
			arr = regexChecker("(?i)^\\s*(DELETE)\\s+FROM\\s+(.+)\\s*$",
					newQuery, 2);
			return arr;
		}

		// Parsing UPDATE//UPDATE table_name
		// SET column1=value column2=value,...
		// WHERE some_column=some_value
		else if (regexChecker(
				"(?i)^\\s*(UPDATE)\\s+(.+)\\s+SET\\s+(.+)\\s+(WHERE)\\s+(.+)\\s*(=|<|>)\\s*(.+)\\s*$",
				newQuery, 7) != null) {
			arr = regexChecker(
					"(?i)^\\s*(UPDATE)\\s+(.+)\\s+SET\\s+(.+)\\s+(WHERE)\\s+(.+)\\s*(=|<|>)\\s*(.+)\\s*$",
					newQuery, 7);
			return arr;
		}

		// Parsing UPDATE//UPDATE table_name
		// SET column1=value column2=value,...
		else if (regexChecker("(?i)^\\s*(UPDATE)\\s+(.+)\\s+SET\\s+(.+)\\s*$",
				newQuery, 3) != null) {
			arr = regexChecker("(?i)^\\s*(UPDATE)\\s+(.+)\\s+SET\\s+(.+)\\s*$",
					newQuery, 3);
			return arr;
		} else {
			return null;
		}

	}

	public String[] regexChecker(String theRegex, String strTooCheck, int groups) {
		String required[];
		required = new String[groups];
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(strTooCheck);
		if (regexMatcher.find()) {
			for (int index = 1; index <= groups; index++) {
				required[index - 1] = regexMatcher.group(index);
			}
			return required;
		}
		return null;
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
