package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class JSON {

	// save file
	public void save(LinkedList<Shape> myShapes, String path) {
		Formatter formatFile;
		try {
			formatFile = new Formatter(path);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("Path Not Valid");
		}
		if (myShapes.size() == 0 || myShapes == null) {
			formatFile.close();
			return;
		}
		formatFile.format("{%n");
		formatFile.format("\t\"Shapes\" :%n\t\t\t \t[");
		int pointer = 0;
		for (Shape shape : myShapes) {
			formatFile.format("%n");
			WriteShpae(shape, formatFile);
			if (pointer < myShapes.size() - 1)
				formatFile.format(",");
			pointer++;
		}
		formatFile.format("%n\t\t\t\t]");
		formatFile.format("%n}");
		formatFile.close();
		return;
	}

	// Write shapes
	public void WriteShpae(Shape shape, Formatter formatFile) {

		// class name of shape like circle .....etc.
		formatFile.format("\t\t\t \t\t{ \"classname\":  \"%s\"", shape
				.getClass().getName());

		// position of shape maybe center or .....etc

		if (shape.getPosition() != null) {
			formatFile.format(",%n\t\t\t \t\t  \"position.x\": \"%s\"",
					shape.getPosition().x);
			formatFile.format(",%n\t\t\t \t\t  \"position.y\": \"%s\"",
					shape.getPosition().y);
		} else {
			formatFile.format(",%n\t\t\t \t\t  \"position.x\": \"%s\"", "null");
			formatFile.format(",%n\t\t\t \t\t  \"position.y\": \"%s\"", "null");
		}

		// color of shape (the outer)
		if (shape.getColor() != null) {
			formatFile.format(",%n\t\t\t \t\t  \"color\": \"%s\"", shape
					.getColor().getRGB());
		} else {
			formatFile.format(",%n\t\t\t \t\t  \"color\": \"%s\"", "null");
		}

		// color of shape (the fill)
		if (shape.getFillColor() != null) {
			formatFile.format(",%n\t\t\t \t\t  \"fillcolor\": \"%s\"", shape
					.getFillColor().getRGB());
		} else {
			formatFile.format(",%n\t\t\t \t\t  \"fillcolor\": \"%s\"", "null");
		}

		// properties of shape
		if (shape.getProperties() != null) {
			formatFile.format(",%n\t\t\t \t\t  \"map\": \"%s\"", "exist");
			String[] keys = new String[shape.getProperties().size()];
			Double[] values = new Double[shape.getProperties().size()];
			shape.getProperties().keySet().toArray(keys);
			shape.getProperties().values().toArray(values);
			for (int index = 0; index < keys.length; index++) {
				formatFile.format(",%n\t\t\t \t\t  \"%s\": \"%s\"",
						keys[index], values[index]);
			}
		} else {
			formatFile.format(",%n\t\t\t \t\t  \"map\": \"%s\"", "null");
		}

		formatFile.format("%n\t\t\t \t\t}");

	}

	// read file
	public LinkedList<Shape> load(String path) throws FileNotFoundException {
		LinkedList<Shape> myShapes = new LinkedList<Shape>();
		File file = new File(path);
		Scanner reader;
		try {
			
			reader = new Scanner(file);
			
		} catch (FileNotFoundException e) {

			throw new RuntimeException(
					"there not valid path or file cannot be open");
		}
		
		reader.nextLine();
		reader.nextLine();
		while (!reader.hasNext("}")) {
			Shape shape = (Shape) readShape(reader);
			myShapes.add(shape);
		}
		reader.close();
		return myShapes;
	}

	// read every shape
	public Shape readShape(Scanner reader) {
		ShapeType shape = new ShapeType();
		Shape returnShape;
		StringTokenizer line = new StringTokenizer(reader.nextLine());
		String required;
		Map<String, Double> map = new LinkedHashMap<String, Double>();

		line = new StringTokenizer(reader.nextLine());
		// remove strings don't needed
		for (int counter = 0; counter < 2; counter++){
			line.nextToken();
		}
		
		required = line.nextToken();

		// create shape from class name
		returnShape = shape.getShape(required.substring(1,
				required.length() - 2));

		// set position of shape(coordinate x)
		Point position  = new Point();
		line = new StringTokenizer(reader.nextLine());
		for (int counter = 0; counter < 1; counter++)
			line.nextToken();
		required = line.nextToken();
		if (!required.contains("null")) {
			position.x = Integer.parseInt(required.substring(1,
					required.length() - 2));
		}

		// set position of shape(coordinate y)
		line = new StringTokenizer(reader.nextLine());
		for (int counter = 0; counter < 1; counter++)
			line.nextToken();
		required = line.nextToken();
		if (!required.contains("null")) {
			position.y = Integer.parseInt(required.substring(1,
					required.length() - 2));
		}
		// set position of shape
		returnShape.setPosition(position);

		// set color of shape
		line = new StringTokenizer(reader.nextLine());
		for (int counter = 0; counter < 1; counter++)
			line.nextToken();
		required = line.nextToken();
		if (!required.contains("null")) {
			returnShape.setColor(new Color(Integer.parseInt(required.substring(
					1, required.length() - 2))));
		}

		// set fill color of shape
		line = new StringTokenizer(reader.nextLine());
		for (int counter = 0; counter < 1; counter++)
			line.nextToken();
		required = line.nextToken();
		if (!required.contains("null")) {
			returnShape.setFillColor(new Color(Integer.parseInt(required
					.substring(1, required.length() - 2))));
		}

		// set properties for shape
		map = returnShape.getProperties();

		line = new StringTokenizer(reader.nextLine());
		for (int counter = 0; counter < 1; counter++)
			line.nextToken();
		required = line.nextToken();
		if (!required.contains("null")) {
			for (Entry<String, Double> entry : map.entrySet()) {
				line = new StringTokenizer(reader.nextLine());
				line.nextToken();
				required = line.nextToken().replace("\"", "").replace(",", "");
				if (!required.contains("null")) {
					entry.setValue(Double.parseDouble(required));
				}
				returnShape.setProperties(map);
			}
		}
		return returnShape;

	}

}
