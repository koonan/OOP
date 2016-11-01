package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedHashMap;
import java.util.Map;

public class Triangle extends Shapes {
	
	private Map<String, Double> triangleMap;
	private Point secondPoint;
	private Point thirdPoint;


	public Triangle() {
		secondPoint = new Point(0,0);
		thirdPoint = new Point(0,0);
		triangleMap = new LinkedHashMap<String, Double>();
		triangleMap.put("X_of_second_point", 0.0);
		triangleMap.put("Y_of_second_point", 0.0);
		triangleMap.put("X_of_third_point", 0.0);
		triangleMap.put("Y_of_third_point", 0.0);
		
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		triangleMap.putAll(properties);
		secondPoint.x = triangleMap.get("X_of_second_point").intValue();
		secondPoint.y = triangleMap.get("Y_of_second_point").intValue();
		thirdPoint.x = triangleMap.get("X_of_third_point").intValue();
		thirdPoint.y = triangleMap.get("Y_of_third_point").intValue();

	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return triangleMap;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Point firstPoint = getPosition();
		canvas.setColor(getColor());
		canvas.drawPolygon(new int[] { firstPoint.x, secondPoint.x, thirdPoint.x },
				new int[] { firstPoint.y, secondPoint.y, thirdPoint.y }, 3);

		if (getFillColor() != null) {
			canvas.setColor(getFillColor());
			canvas.fillPolygon(new int[] { firstPoint.x, secondPoint.x, thirdPoint.x },
					new int[] { firstPoint.y, secondPoint.y, thirdPoint.y }, 3);

		}
	}
}
