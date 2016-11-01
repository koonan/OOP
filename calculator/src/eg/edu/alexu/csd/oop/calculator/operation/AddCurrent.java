package eg.edu.alexu.csd.oop.calculator.operation;

import java.util.Stack;

public class AddCurrent {
	private static Stack<String> stack1;
	private Stack<String> stack2 = new Stack<String>();
	private static int current;

	public AddCurrent() {
		stack1 = new Stack<String>();
	}

	public void add(String operation) {
		if (Current.getCurrent() == stack1.size() - 1) {
			stack1.push(operation);

		} else if (Current.getCurrent() < stack1.size()) {
			int count = Current.getCurrent();
			while (count != getStack1().size() - 1 && !stack1.empty()) {
				stack1.pop();
			}
			stack1.push(operation);
		}
		for (int i = 0; i < 5 && (!stack1.empty()); i++) {
			stack2.push(stack1.pop());
		}
		stack1.clear();
		for (int i = 0; i < 5 && (!stack2.empty()); i++) {
			stack1.push(stack2.pop());
		}
		stack2.clear();
		current = stack1.size() - 1;
		Current.setCurrent(current);
	}

	public static String sendCurrent() {
		current = Current.getCurrent();
		return stack1.get(current);
	}

	public Stack<String> getStack1() {
		return stack1;
	}

	public void setStack1(Stack<String> stack11) {
		stack1 = stack11;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current1) {
		current = current1;
	}

}