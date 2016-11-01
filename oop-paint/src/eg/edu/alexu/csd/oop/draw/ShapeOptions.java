package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

public class ShapeOptions {

	private Shape shape;
	private ShapeType shapeType;
	private DrawingEngine obj = new Drawing();

	public void createShape(String model, Graphics2D canvas, int x, int y,
			Color frameColor, Color fillColor) {

		shapeType = new ShapeType();
		// position point of shape
		Point position = new Point();
		position.x = x;
		position.y = y;
		// creat wanted shape
		String shapeName = "eg.edu.alexu.csd.oop.draw.";
		shape = shapeType.getShape(shapeName + model);
		Map<String, Double> map = new HashMap<String, Double>();
		map = shape.getProperties();
		double value = 0.0;
		// user enter properties by input Dialog
		boolean creat = true;
		if (map != null) {
			for (Entry<String, Double> entry : map.entrySet()) {
				String temp = JOptionPane.showInputDialog(entry.getKey());
				if (temp != null) {
					value = Double.parseDouble(temp);
					entry.setValue(value);
				} else {
					creat = false;
				}

			}
			shape.setProperties(map);
		}
		if (creat) {
			// set shape
			shape.setPosition(position);
			shape.setColor(frameColor);
			shape.setFillColor(fillColor);
			obj.addShape(shape);
			obj.refresh(canvas);
		}

	}

	public void undo(Graphics2D canvas) {
		obj.undo();
		obj.refresh(canvas);
	}

	public void redo(Graphics2D canvas) {
		obj.redo();
		obj.refresh(canvas);
	}

	public void remove(Shape selectShape, Graphics2D canvas) {
		obj.removeShape(selectShape);
		obj.refresh(canvas);
	}

	public void move(Shape selectShape, Graphics2D canvas, Point newPosition) {
		Shape newShape = null;
		try {
			newShape = (Shape) selectShape.clone();
			newShape.setPosition(newPosition);
			obj.updateShape(selectShape, newShape);
			obj.refresh(canvas);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void resize(Map<String, Double> map, Shape selectShape,
			Graphics2D canvas) {

		Shape newShape = null;
		try {
			newShape = (Shape) selectShape.clone();
			newShape.setProperties(map);
			obj.updateShape(selectShape, newShape);
			obj.refresh(canvas);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setColor(Shape selectShape, Color frameColor, Color fillColor,
			Graphics2D canvas) {

		Shape newShape = null;
		try {
			newShape = (Shape) selectShape.clone();
			newShape.setColor(frameColor);
			newShape.setFillColor(fillColor);
			obj.updateShape(selectShape, newShape);
			obj.refresh(canvas);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void save(String path) {
		obj.save(path);
	}

	public void load(String path, Graphics2D canvas) {
		obj.load(path);
		obj.refresh(canvas);
	}

	public Shape[] getList() {
		return obj.getShapes();
	}

	public List<Class<? extends Shape>> getShapeList() {

		List<Class<? extends Shape>> list = obj.getSupportedShapes();

		return list;
	}
}
