package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;

public class Rectangle extends Shapes {

	private Map<String, Double> rectangleMap;
	protected Double length, width;

	public Rectangle() {
		rectangleMap = new LinkedHashMap<String, Double>();
		rectangleMap.put("Length", null);
		rectangleMap.put("Width", null);
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub

		rectangleMap.putAll(properties);
		length = rectangleMap.get("Length");
		width = rectangleMap.get("Width");
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return rectangleMap;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Point position = getPosition();
		canvas.setColor(getColor());
		canvas.drawRect(position.x, position.y, length.intValue(),
				width.intValue());
		if (getFillColor() != null) {
			canvas.setColor(getFillColor());
			canvas.fillRect(position.x, position.y, length.intValue(),
					width.intValue());
		}

	}

}
