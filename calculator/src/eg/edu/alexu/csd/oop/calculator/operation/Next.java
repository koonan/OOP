package eg.edu.alexu.csd.oop.calculator.operation;

import eg.edu.alexu.csd.oop.calculator.command.Command2;
import eg.edu.alexu.csd.oop.calculator.device.CalculatorDevice;


public class Next implements Command2 {
	CalculatorDevice theDevice;
	public Next(CalculatorDevice newDevice){
		theDevice=newDevice;
	}

	@Override
	public String  execute() {
		return theDevice.next();
	}

}
