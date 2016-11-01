package eg.edu.alexu.csd.oop.calculator.saveandload;

import eg.edu.alexu.csd.oop.calculator.command.Command1;
import eg.edu.alexu.csd.oop.calculator.device.CalculatorDevice;

public class Saving implements Command1 {

	CalculatorDevice theDevice;

	public Saving(CalculatorDevice newDevice) {
		theDevice = newDevice;
	}

	public void execute() {
		theDevice.save();
	}

}
