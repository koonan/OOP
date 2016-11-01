package eg.edu.alexu.csd.oop.calculator.device;

import java.util.Stack;

import eg.edu.alexu.csd.oop.calculator.Calculator;
import eg.edu.alexu.csd.oop.calculator.operation.AddCurrent;
import eg.edu.alexu.csd.oop.calculator.operation.Current;
import eg.edu.alexu.csd.oop.calculator.saveandload.ReaderWriterFile;

public class CalculatorDevice implements Calculator {

	public AddCurrent getCurrent;
	public static CalculatorDevice instance;

	public static void destoryInstance() {
		instance = null;
	}

	private CalculatorDevice() {
		getCurrent = new AddCurrent();
		Current.setCurrent(-1);
	}

	static {
		try {
			instance = new CalculatorDevice();
		} catch (Exception e) {
			throw new RuntimeException("Exception while creating instance");
		}
	}

	public static CalculatorDevice getInstance() {
		return instance;
	}

	CalculatorOperation operate = CalculatorOperation.getInstance();
	String input;

	@Override
	public void input(String s) {
		getCurrent.add(s);
	}

	@Override
	public String getResult() throws RuntimeException {

		if (getCurrent.getCurrent() == -1)
			throw new RuntimeException("Syntax Error");
		input = AddCurrent.sendCurrent();
		String result;
		try {
			result = operate.operator(input);
			return result;
		} catch (RuntimeException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public String current() {
		String present;
		int num = Current.getCurrent();
		if (num == -1)
			return null;
		else {
			present = AddCurrent.sendCurrent();
			return present;
		}

	}

	@Override
	public String prev() {

		String previous;
		int num = Current.getCurrent();
		if (num == 0 || num == -1) {
			return null;
		} else {
			num--;
			Current.setCurrent(num);
			previous = AddCurrent.sendCurrent();
			return previous;
		}

	}

	@Override
	public String next() {

		String next;
		int num = Current.getCurrent();
		Stack<String> stack1 = getCurrent.getStack1();
		if (num + 1 == stack1.size())
			return null;
		else {
			num++;
			Current.setCurrent(num);
			next = AddCurrent.sendCurrent();
			return next;
		}
	}

	ReaderWriterFile read = new ReaderWriterFile();

	@SuppressWarnings("unchecked")
	@Override
	public void save() {

		Stack<String> stack1 = (Stack<String>) getCurrent.getStack1().clone();
		Stack<String> stack3;
		stack3 = (Stack<String>) getCurrent.getStack1().clone();
		int count2 = Current.getCurrent();
		if (count2 != stack3.size() - 1) {
			while (count2 != stack3.size() - 1 && !stack3.empty()) {
				stack3.pop();
			}

			read.write(stack3);

		} else {
			read.write(stack1);

		}
	}

	@Override
	public void load() {

		Stack<String> stack1 = new Stack<String>();
		Stack<String> stack2 = new Stack<String>();
		getCurrent.getStack1().clear();
		stack1 = read.read();
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		getCurrent.setStack1(stack2);
		getCurrent.setCurrent(stack2.size() - 1);
		Current.setCurrent(stack2.size() - 1);
	}

}