package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class Shapes implements Shape {

	private Point shapePosition = new Point();
	private Color outerColor = Color.black;
	private Color innerColor;

	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		shapePosition = position;
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return shapePosition;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		outerColor = color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return outerColor;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub

		innerColor = color;

	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return innerColor;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {

		// clone using reflection 
		Constructor<?> c = null;
		Shape shape = null;
		try {
			c = Class.forName(this.getClass().toString().substring(6))
					.getConstructor();
			shape = (Shape) c.newInstance();
		} catch (NoSuchMethodException | SecurityException
				| ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// get the properties of old shape
		Map<String, Double> newMap;
		newMap = this.getProperties();
		Point position = this.getPosition();
		Color color = this.getColor();
		Color fillColor = this.getFillColor();
		// set this properties to new shape
		shape.setPosition(position);
		shape.setProperties(newMap);
		shape.setColor(color);
		shape.setFillColor(fillColor);
		return shape;
	}

}
