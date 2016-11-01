package eg.edu.alexu.csd.oop.draw;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ShapeType {
	
	public Shape getShape(String shapeType) {
		try {
			Constructor<?> c = Class.forName(shapeType).getConstructor();
			Shape shape = (Shape) c.newInstance();
			return shape;
		} catch (NoSuchMethodException | SecurityException
				| ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IllegalArgumentException("class not found");
		}

	}

}
