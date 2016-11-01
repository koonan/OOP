package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;

public class Line extends Shapes {
	
	private Map<String, Double> lineMap;
	private Point secondPoint;

	public Line() {
		lineMap = new LinkedHashMap<String, Double>();
		secondPoint = new Point(0,0);
		lineMap.put("X_of_second_point", 0.0);
		lineMap.put("Y_of_second_point", 0.0);

		
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		lineMap.putAll(properties);
		secondPoint.x= lineMap.get("X_of_second_point").intValue();
		secondPoint.y= lineMap.get("Y_of_second_point").intValue();

	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return lineMap;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Point firstPoint = getPosition();
		canvas.setColor(getColor());
		canvas.drawLine(firstPoint.x, firstPoint.y, secondPoint.x, secondPoint.y);

	}

}
