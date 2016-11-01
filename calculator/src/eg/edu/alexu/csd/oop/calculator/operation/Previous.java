package eg.edu.alexu.csd.oop.calculator.operation;

import eg.edu.alexu.csd.oop.calculator.command.Command2;
import eg.edu.alexu.csd.oop.calculator.device.CalculatorDevice;

public class Previous implements Command2 {
	CalculatorDevice theDevice;

	public Previous(CalculatorDevice newDevice) {
		theDevice = newDevice;

	}

	@Override
	public String execute() {
		return theDevice.prev();
	}

}
