package eg.edu.alexu.csd.oop.draw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SwingPaint extends JComponent implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton outerColor, innerColor;
	private JMenuItem move, remove, color, resize, save, load;

	private Color frameColor, fillColor;
	private DrawArea drawArea;

	public SwingPaint() {

		// create main frame
		JFrame frame = new JFrame("Java Paint");
		frame.setSize(1000, 700);
		// can close frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// creat coordinates panel
		JPanel coordinates = new JPanel();
		coordinates.setBackground(Color.lightGray);
		// labels for coordinates
		JLabel xCoor = new JLabel("X:");
		JLabel yCoor = new JLabel("Y:");
		coordinates.add(xCoor);
		coordinates.add(yCoor);
		// add coordinates panel to frame
		frame.add(coordinates, BorderLayout.SOUTH);

		// create draw area
		drawArea = new DrawArea(xCoor, yCoor);
		// add draw area to frame
		frame.add(drawArea, BorderLayout.CENTER);

		// creat panel for shapes
		JPanel shapesPanel = new JPanel();
		shapesPanel.setBackground(Color.LIGHT_GRAY);
		shapesPanel.setLayout(new GridLayout(15, 1, 2, 3));

		// shape buttons
		// get suported shapes
		List<Class<? extends Shape>> shapeList = drawArea.getList();
		for (Class<? extends Shape> shape : shapeList) {
			String shapeName = shape.getName();
			JButton theBut = makeButtons(shapeName.substring(26));
			// add shape buttons on shape panel
			shapesPanel.add(theBut);
		}
		// add option free draw
		JButton freeBut = makeButtons("Free Hand");
		shapesPanel.add(freeBut);
		// add shape panel to frame
		frame.add(shapesPanel, BorderLayout.EAST);

		// creat panel for options
		JPanel optionPanel = new JPanel();
		optionPanel.setBackground(Color.lightGray);
		// optionPanel.setLayout(new GridLayout(10, 1, 2, 3));
		// option buttons
		JButton clearBut = makeButtons("Clear");
		JButton undoBut = makeButtons("UnDo");
		JButton redoBut = makeButtons("ReDo");
		outerColor = makeColorButtons("Frame Color");
		innerColor = makeColorButtons("Fill Color");

		// add option buttons to panel
		optionPanel.add(undoBut);
		optionPanel.add(redoBut);
		optionPanel.add(outerColor);
		optionPanel.add(innerColor);
		optionPanel.add(clearBut);
		// add option panel to frame
		frame.add(optionPanel, BorderLayout.NORTH);

		// add menu bar
		JMenuBar menuBar = new JMenuBar();
		// add file, edit menu
		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);

		// add items to file menu
		save = new JMenuItem("save");
		save.addActionListener(this);
		file.add(save);

		load = new JMenuItem("load");
		load.addActionListener(this);
		file.add(load);

		// add items to edit menu
		move = new JMenuItem("move");
		move.addActionListener(this);
		edit.add(move);

		resize = new JMenuItem("resize");
		resize.addActionListener(this);
		edit.add(resize);

		remove = new JMenuItem("remove");
		remove.addActionListener(this);
		edit.add(remove);

		color = new JMenuItem("color");
		color.addActionListener(this);
		edit.add(color);

		frame.setJMenuBar(menuBar);
		frame.setVisible(true);

	}

	public JButton makeButtons(String buttonName) {

		JButton theBut = new JButton(buttonName);
		// Icon butIcon = new ImageIcon(iconName + ".png");
		// theBut.setIcon(butIcon);
		theBut.addActionListener(this);
		return theBut;
	}

	public JButton makeColorButtons(String buttonName) {
		JButton theBut = new JButton(buttonName);
		// Icon butIcon = new ImageIcon(iconName + ".png");
		// theBut.setIcon(butIcon);
		theBut.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent bt) {
				// TODO Auto-generated method stub
				if (bt.getSource() == outerColor) {
					frameColor = JColorChooser.showDialog(null,
							"Pick a Frame color", Color.black);

				} else if (bt.getSource() == innerColor) {
					fillColor = JColorChooser.showDialog(null,
							"Pick a Fill Color", Color.black);

				}
			}
		});
		return theBut;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource().equals(save)) {
			drawArea.save();
		} else if (e.getSource().equals(load)) {
			drawArea.load();
		} else if (e.getSource().equals(move)) {
			drawArea.move();
		} else if (e.getSource().equals(remove)) {
			drawArea.remove();
		} else if (e.getSource().equals(resize)) {
			drawArea.resize();
		} else if (e.getSource().equals(color)) {
			drawArea.setColor();
		} else {
			drawArea.makeAction(((JButton) e.getSource()).getText(),
					frameColor, fillColor);
			frameColor = Color.black;
			fillColor = null;
		}

	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new SwingPaint();
	}

}
