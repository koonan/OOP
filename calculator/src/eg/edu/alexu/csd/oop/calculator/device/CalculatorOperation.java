package eg.edu.alexu.csd.oop.calculator.device;

public class CalculatorOperation {
	private static CalculatorOperation firstInstance = null;

	private CalculatorOperation() {
	}

	public static CalculatorOperation getInstance() {
		if (firstInstance == null) {
			firstInstance = new CalculatorOperation();
		}
		return firstInstance;
	}

	String operator(String input) throws RuntimeException {
		double frstNum, secondNum, result = 0;
		String output = null, operation;
		if (input == null) {
			output = "Syntax Error";
			throw new RuntimeException("Syntax Error");
		} else {
			input.trim();
			String[] arr = input.split("(?<=[-+*/])|(?=[-+*/])");
			frstNum = Double.parseDouble(arr[0]);
			secondNum = Double.parseDouble(arr[2]);
			operation = arr[1];
			if (operation.equals("+"))
				result = frstNum + secondNum;
			else if (operation.equals("*"))
				result = frstNum * secondNum;
			else if (operation.equals("-"))
				result = frstNum - secondNum;
			else if (operation.equals("/")) {
				if (secondNum != 0) {
					result = frstNum / secondNum;
				} else {
					output = "Syntax Error";
					throw new RuntimeException("Syntax Error");
				}
			}
			output = Double.toString(result);
		}

		return output;
	}
}
