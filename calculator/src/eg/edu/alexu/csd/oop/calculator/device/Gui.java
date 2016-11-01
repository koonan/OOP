package eg.edu.alexu.csd.oop.calculator.device;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import eg.edu.alexu.csd.oop.calculator.button.Button1;
import eg.edu.alexu.csd.oop.calculator.button.Button2;
import eg.edu.alexu.csd.oop.calculator.operation.Current;
import eg.edu.alexu.csd.oop.calculator.operation.GetterResult;
import eg.edu.alexu.csd.oop.calculator.operation.GetterInput;
import eg.edu.alexu.csd.oop.calculator.operation.Next;
import eg.edu.alexu.csd.oop.calculator.operation.Previous;
import eg.edu.alexu.csd.oop.calculator.saveandload.Loading;
import eg.edu.alexu.csd.oop.calculator.saveandload.Saving;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class Gui extends JFrame {
	CalculatorDevice calculator = CalculatorDevice.getInstance();
	String operation;
	private JPanel contentPane;
	private JTextField jTxtDisplay;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 763);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		jTxtDisplay = new JTextField();
		jTxtDisplay.setHorizontalAlignment(SwingConstants.LEFT);
		jTxtDisplay.setFont(new Font("Tahoma", Font.BOLD, 28));
		jTxtDisplay.setBounds(10, 22, 387, 65);
		contentPane.add(jTxtDisplay);
		jTxtDisplay.setColumns(10);

		final JButton jBtn1 = new JButton("1");
		jBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn1.getText();
				jTxtDisplay.setText(Enternumber);
			}
		});
		jBtn1.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn1.setBounds(20, 98, 71, 75);
		contentPane.add(jBtn1);

		final JButton jBtn2 = new JButton("2");
		jBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn2.getText();
				jTxtDisplay.setText(Enternumber);
			}
		});
		jBtn2.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn2.setBounds(121, 98, 71, 75);
		contentPane.add(jBtn2);

		final JButton jBtn3 = new JButton("3");
		jBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn3.getText();
				jTxtDisplay.setText(Enternumber);
			}
		});
		jBtn3.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn3.setBounds(215, 98, 71, 75);
		contentPane.add(jBtn3);

		final JButton jBtn4 = new JButton("4");
		jBtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn4.getText();
				jTxtDisplay.setText(Enternumber);
			}
		});
		jBtn4.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn4.setBounds(315, 98, 71, 75);
		contentPane.add(jBtn4);

		final JButton jBtn5 = new JButton("5");
		jBtn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn5.getText();
				jTxtDisplay.setText(Enternumber);
			}
		});
		jBtn5.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn5.setBounds(20, 184, 71, 75);
		contentPane.add(jBtn5);

		final JButton jBtn6 = new JButton("6");
		jBtn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn6.getText();
				jTxtDisplay.setText(Enternumber);

			}
		});
		jBtn6.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn6.setBounds(121, 184, 71, 75);
		contentPane.add(jBtn6);

		final JButton jBtn7 = new JButton("7");
		jBtn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn7.getText();
				jTxtDisplay.setText(Enternumber);

			}
		});
		jBtn7.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn7.setBounds(215, 184, 71, 75);
		contentPane.add(jBtn7);

		final JButton jBtn8 = new JButton("8");
		jBtn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn8.getText();
				jTxtDisplay.setText(Enternumber);

			}
		});
		jBtn8.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn8.setBounds(315, 184, 71, 75);
		contentPane.add(jBtn8);

		final JButton jBtn9 = new JButton("9");
		jBtn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn9.getText();
				jTxtDisplay.setText(Enternumber);

			}
		});
		jBtn9.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn9.setBounds(20, 270, 71, 75);
		contentPane.add(jBtn9);

		final JButton jBtn0 = new JButton("0");
		jBtn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtn0.getText();
				jTxtDisplay.setText(Enternumber);
			}
		});
		jBtn0.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtn0.setBounds(121, 270, 71, 75);
		contentPane.add(jBtn0);

		JButton jBtnPlus = new JButton("+");
		jBtnPlus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTxtDisplay.setText(jTxtDisplay.getText() + "+" );
				operation = "+";
			}
		});
		jBtnPlus.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnPlus.setBounds(215, 270, 71, 75);
		contentPane.add(jBtnPlus);

		JButton jBtnMins = new JButton("-");
		jBtnMins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTxtDisplay.setText(jTxtDisplay.getText() + "-" );
				operation = "-";
			}
		});
		jBtnMins.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnMins.setBounds(315, 270, 71, 75);
		contentPane.add(jBtnMins);

		JButton jBtnDivide = new JButton("/");
		jBtnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTxtDisplay.setText(jTxtDisplay.getText() + "/" );
				operation = "/";
			}
		});
		jBtnDivide.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnDivide.setBounds(20, 359, 71, 75);
		contentPane.add(jBtnDivide);

		JButton jBtnMulti = new JButton("*");
		jBtnMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTxtDisplay.setText(jTxtDisplay.getText() + "*" );
				operation = "*";
			}
		});
		jBtnMulti.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnMulti.setBounds(121, 359, 71, 75);
		contentPane.add(jBtnMulti);

		final JButton jBtnDot = new JButton(".");
		jBtnDot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Enternumber = jTxtDisplay.getText() + jBtnDot.getText();
				jTxtDisplay.setText(Enternumber);
			}
		});
		jBtnDot.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnDot.setBounds(215, 359, 71, 75);
		contentPane.add(jBtnDot);

		JButton jBtnClear = new JButton("C");
		jBtnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jTxtDisplay.setText("");
			}
		});
		jBtnClear.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnClear.setBounds(315, 356, 71, 75);
		contentPane.add(jBtnClear);

		JButton jBtnLoad = new JButton("load");
		jBtnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Loading onCommand = new Loading(calculator);
				Button1 onPressed = new Button1(onCommand);
				onPressed.press();
				jTxtDisplay.setText(" ");
			}
		});
		jBtnLoad.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnLoad.setBounds(30, 445, 162, 49);
		contentPane.add(jBtnLoad);

		JButton jBtnCurr = new JButton("current");
		jBtnCurr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Current onCommand = new Current(calculator);
				Button2 onPressed = new Button2(onCommand);
				String result=onPressed.press();
				if(result==null)
					jTxtDisplay.setText("No current");
				else
				jTxtDisplay.setText(result);
			}
		});
		jBtnCurr.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnCurr.setBounds(215, 445, 167, 49);
		contentPane.add(jBtnCurr);

		JButton jBtnPrev = new JButton("prev");
		jBtnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Previous onCommand = new Previous(calculator);
				Button2 onPressed = new Button2(onCommand);
				String result=onPressed.press();
				if(result==null)
					jTxtDisplay.setText("No previous");
				else
				jTxtDisplay.setText(result);
			}
		});
		jBtnPrev.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnPrev.setBounds(30, 514, 162, 49);
		contentPane.add(jBtnPrev);

		JButton jBtnNext = new JButton("next");
		jBtnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Next onCommand = new Next(calculator);
				Button2 onPressed = new Button2(onCommand);
				String result=onPressed.press();
				if(result==null)
					jTxtDisplay.setText("No Next");
				else
				jTxtDisplay.setText(result);
			}
		});
		jBtnNext.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnNext.setBounds(215, 514, 167, 49);
		contentPane.add(jBtnNext);

		JButton jBtnEqual = new JButton("=");
		jBtnEqual.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				GetterResult onCommand = new GetterResult(calculator);
				Button2 onPressed = new Button2(onCommand);
				String answer;
				String finall,result;
				try {
					//answer=AddCurrent.sendCurrentGui();
					answer=calculator.getCurrent.sendCurrent();
					result=onPressed.press();
					finall = answer + "=" + " "+result ;
				} catch (RuntimeException e1) {
					result="Syntax Error";	
					finall=result;
				}
				jTxtDisplay.setText(finall);
			}
		});
		jBtnEqual.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnEqual.setBounds(29, 649, 349, 52);
		contentPane.add(jBtnEqual);

		JButton jBtnSave = new JButton("save");
		jBtnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Saving onCommand = new Saving(calculator);
				Button1 onPressed = new Button1(onCommand);
				onPressed.press();
				jTxtDisplay.setText("");
			}
		});
		jBtnSave.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnSave.setBounds(30, 586, 162, 49);
		contentPane.add(jBtnSave);

		JButton jBtnEnter = new JButton("Enter");
		jBtnEnter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				String answer = jTxtDisplay.getText();
				GetterInput onCommand1 = new GetterInput(calculator, answer);
				Button1 onPressed1 = new Button1(onCommand1);
				onPressed1.press();
			}
		});
		jBtnEnter.setFont(new Font("Tahoma", Font.BOLD, 28));
		jBtnEnter.setBounds(215, 586, 167, 46);
		contentPane.add(jBtnEnter);
	}
}