package eg.edu.alexu.csd.oop.calculator.operation;

import eg.edu.alexu.csd.oop.calculator.command.Command1;
import eg.edu.alexu.csd.oop.calculator.device.CalculatorDevice;

public class GetterInput implements Command1 {

	CalculatorDevice theDevice;
	private String input;

	public GetterInput(CalculatorDevice newDevice, String in) {
		input = in;
		theDevice = newDevice;
	}

	public void execute() {
		theDevice.input(input);
	}

}
