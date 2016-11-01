package eg.edu.alexu.csd.oop.draw;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Ellipse extends Shapes {

	private Map<String, Double> ellipseMap;
	protected Double minorAxis;
	protected Double majorAxis;

	public Ellipse() {
		ellipseMap = new HashMap<String, Double>();
		ellipseMap.put("a", null);
		ellipseMap.put("b", null);
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		ellipseMap.putAll(properties);
		minorAxis = ellipseMap.get("a");
		majorAxis = ellipseMap.get("b");
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return ellipseMap;
	}

	@Override
	public void draw(java.awt.Graphics canvas) {
		Point position = getPosition();
		canvas.setColor(getColor());
		canvas.drawOval(position.x - minorAxis.intValue() / 2, position.y
				- majorAxis.intValue() / 2, minorAxis.intValue(),
				majorAxis.intValue());
		if (getFillColor() != null) {
			canvas.setColor(getFillColor());
			canvas.fillOval(position.x - minorAxis.intValue() / 2,
					position.y - majorAxis.intValue() / 2,
					minorAxis.intValue(), majorAxis.intValue());
		}
	}
}
