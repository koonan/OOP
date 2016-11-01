package eg.edu.alexu.csd.oop.draw;

import java.util.HashMap;
import java.util.Map;

public class Circle extends Ellipse {

	private Map<String, Double> circleMap;

	public Circle() {
		circleMap = new HashMap<String, Double>();
		circleMap.put("Raduis", 0.0);
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub

		circleMap.putAll(properties);
		this.minorAxis = circleMap.get("Raduis");
		this.majorAxis = this.minorAxis;

	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return circleMap;
	}

}
