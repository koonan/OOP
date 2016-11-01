package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DrawArea extends JComponent {

	private static final long serialVersionUID = 1L;
	// Image in which we're going to draw
	private Image image;
	// Graphics2D object ==> used to draw on
	private Graphics2D graphics;
	// Mouse coordinates
	private int currX, currY, oldX, oldY;

	private ShapeOptions option = new ShapeOptions();
	private Color frameColor, fillColor;
	private Shape[] list;
	private Shape selectedShape;
	private boolean selected = false;

	public DrawArea(final JLabel xCoor, final JLabel yCoor) {

		list = option.getList();
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				// save coord x,y when mouse is pressed
				oldX = e.getX();
				oldY = e.getY();
				selected = false;
				for (int i = 0; i < list.length; i++) {
					Point pos = list[i].getPosition();
					if (Math.abs(oldX - pos.x) <= 2
							&& Math.abs(oldY - pos.y) <= 2) {
						selectedShape = list[i];
						graphics.drawRect(pos.x - 2, pos.y - 2, 4, 4);
						repaint();
						selected = true;
					}
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				currX = e.getX();
				currY = e.getY();
				xCoor.setText("X: " + currX + " ");
				yCoor.setText("Y: " + currY + " ");
				for (int i = 0; i < list.length; i++) {
					Point pos = list[i].getPosition();
					if (Math.abs(currX - pos.x) < 5
							&& Math.abs(currY - pos.y) < 5) {
						graphics.drawRect(pos.x - 2, pos.y - 2, 4, 4);
						repaint();
					}
				}
			}
		});

	}

	protected void paintComponent(Graphics g) {
		if (image == null) {
			// image to draw null ==> we create
			image = createImage(getSize().width, getSize().height);
			graphics = (Graphics2D) image.getGraphics();
			// enable antialiasing
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			// clear draw area
			clear();
		}

		g.drawImage(image, 0, 0, null);
	}

	// now we create exposed methods
	public void clear() {
		graphics.setPaint(Color.white);
		// draw white on entire draw area to clear
		graphics.fillRect(0, 0, getSize().width, getSize().height);
		graphics.setPaint(Color.black);
		repaint();
	}

	public void undo() {
		this.clear();
		option.undo(graphics);
	}

	public void redo() {
		this.clear();
		option.redo(graphics);
	}

	public void remove() {
		if (selected) {
			this.clear();
			option.remove(selectedShape, graphics);
		}
	}

	public void move() {
		if (selected) {
			Point newPos = new Point();
			String temp = JOptionPane.showInputDialog("new position X :");
			if (temp == null) {
				temp = "0";
			}
			newPos.x = (int) Double.parseDouble(temp);

			temp = JOptionPane.showInputDialog("new position Y :");
			if (temp == null) {
				temp = "0";
			}
			newPos.y = (int) Double.parseDouble(temp);
			this.clear();
			option.move(selectedShape, graphics, newPos);
		}

	}

	public void resize() {
		if (selected) {

			Map<String, Double> map = new LinkedHashMap<String, Double>();
			map = selectedShape.getProperties();
			double val = 0.0;
			for (Entry<String, Double> entry : map.entrySet()) {
				String temp = JOptionPane.showInputDialog(entry.getKey());
				if (temp == null) {
					temp = "0";
				}
				val = Double.parseDouble(temp);
				entry.setValue(val);
			}
			this.clear();
			option.resize(map, selectedShape, graphics);
		}

	}

	public void setColor() {
		if (selected) {

			frameColor = JColorChooser.showDialog(null, "Pick a Frame color",
					Color.black);
			fillColor = JColorChooser.showDialog(null, "Pick a Fill Color",
					Color.black);

			this.clear();
			option.setColor(selectedShape, frameColor, fillColor, graphics);

		}

	}

	public void freeHand(Color frameColor) {
		graphics.setColor(frameColor);
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				// coord x,y when drag mouse
				currX = e.getX();
				currY = e.getY();

				if (graphics != null) {
					// draw line if graphics context not null
					graphics.drawLine(oldX, oldY, currX, currY);
					// refresh draw area to repaint
					repaint();
					// store current coords x,y as olds x,y
					oldX = currX;
					oldY = currY;
				}
			}
		});
	}

	public void save() {
		final JFileChooser fc = new JFileChooser();
		int response = fc.showSaveDialog(null);
		if (response == JFileChooser.APPROVE_OPTION) {
			option.save(fc.getSelectedFile().toString());
		}

	}

	public void load() {
		final JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		int go = chooser.showOpenDialog(null);
		if (go == JFileChooser.APPROVE_OPTION) {
			this.clear();
			option.load(chooser.getSelectedFile().toString(), graphics);
		}
	}

	public List<Class<? extends Shape>> getList() {
		return option.getShapeList();
	}

	public void makeAction(String clickedBut, Color frameColor, Color fillColor) {
		if (clickedBut.equals("Clear")) {
			this.clear();
		} else if (clickedBut.equals("UnDo")) {
			this.undo();
		} else if (clickedBut.equals("ReDo")) {
			this.redo();
		} else if (clickedBut.equals("Free Hand")) {
			this.freeHand(frameColor);
		} else { // creat new shape
			this.clear();
			option.createShape(clickedBut, graphics, oldX, oldY, frameColor,
					fillColor);
			repaint();
		}

		list = option.getList();
	}

}