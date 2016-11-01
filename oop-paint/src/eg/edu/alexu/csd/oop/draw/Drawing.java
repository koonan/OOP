package eg.edu.alexu.csd.oop.draw;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Stack;

public class Drawing implements DrawingEngine {

	private LinkedList<Shape> listOfShapes;
	private Stack<LinkedList<Shape>> undoStack;
	private Stack<LinkedList<Shape>> redoStack;
	private XML xml;
	private JSON json;

	public Drawing() {
		// TODO Auto-generated constructor stub
		listOfShapes = new LinkedList<>();
		undoStack = new Stack<>();
		undoStack.push(listOfShapes);
		redoStack = new Stack<>();
		xml = new XML();
		json = new JSON();

	}

	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub

		ListIterator<Shape> listIterator = listOfShapes.listIterator();
		while (listIterator.hasNext()) {
			listIterator.next().draw(canvas);

		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		listOfShapes.add((Shape) shape);
		if (undoStack.size() == 20) {
			undoStack.remove(0);
		}

		undoStack.push((LinkedList<Shape>) listOfShapes.clone());

	}

	@SuppressWarnings("unchecked")
	@Override
	public void removeShape(Shape shape) throws RuntimeException {
		// TODO Auto-generated method stub
		if (listOfShapes.contains(shape))
			listOfShapes.remove(shape);
		else {
			throw new RuntimeException("No element Exist");
		}
		if (undoStack.size() == 20) {
			undoStack.remove(0);
		}
		undoStack.push((LinkedList<Shape>) listOfShapes.clone());

	}

	@SuppressWarnings("unchecked")
	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		int index = listOfShapes.indexOf(oldShape);
		listOfShapes.set(index, (Shape) newShape);
		if (undoStack.size() == 20) {
			undoStack.remove(0);
		}
		undoStack.push((LinkedList<Shape>) listOfShapes.clone());

	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		Shape[] shapes = new Shape[listOfShapes.size()];
		for (int i = 0; i < shapes.length; i++) {
			shapes[i] = listOfShapes.get(i);
		}

		return shapes;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		SupportedClasses classLoader = new SupportedClasses();
		Set<Class<? extends Shape>> shapesPlugin = new HashSet<Class<? extends Shape>>();

		shapesPlugin.add(Circle.class);
		shapesPlugin.add(Square.class);
		shapesPlugin.add(Rectangle.class);
		shapesPlugin.add(Ellipse.class);
		shapesPlugin.add(Line.class);
		shapesPlugin.add(Triangle.class);
		try {
			for (Class<? extends Shape> dummy : classLoader.classNames()) {
				shapesPlugin.add(dummy);
			}
			List<Class<? extends Shape>> returnedList = new LinkedList<Class<? extends Shape>>();
			for (Class<? extends Shape> dummy : shapesPlugin) {
				returnedList.add(dummy);
			}
			return returnedList;
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
	}

	@Override
	public void undo() throws RuntimeException {
		// TODO Auto-generated method stub

		if (undoStack.size() != 0 && undoStack.size() != 1) {
			redoStack.push(undoStack.pop());
			listOfShapes.clear();
			listOfShapes.addAll((LinkedList<Shape>) undoStack.peek());
		} else {
			throw new RuntimeException("No Thing To UnDo");
		}

	}

	@Override
	public void redo() throws RuntimeException {
		// TODO Auto-generated method stub

		if (redoStack.size() != 0) {
			undoStack.push(redoStack.pop());
			listOfShapes.clear();
			listOfShapes.addAll((LinkedList<Shape>) undoStack.peek());
		} else {
			throw new RuntimeException("No Thing To ReDo");
		}

	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub

		if (path.toLowerCase().contains("xml"))
			xml.writeFile(listOfShapes, path);
		if (path.toLowerCase().contains("json"))
			json.save(listOfShapes, path);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		if (path.toLowerCase().contains("xml")) {
			try {
				listOfShapes = xml.read(path);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				throw new RuntimeException(
						"there not valid path or file cannot be open");
			}
		}
		if (path.toLowerCase().contains("json")) {
			try {
				listOfShapes = json.load(path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(
						"there not valid path or file cannot be open");
			}
		}

		undoStack.clear();
		undoStack.push((LinkedList<Shape>) listOfShapes.clone());
		redoStack.clear();

	}
}
