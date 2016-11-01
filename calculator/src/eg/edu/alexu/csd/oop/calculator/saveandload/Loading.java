package eg.edu.alexu.csd.oop.calculator.saveandload;

import eg.edu.alexu.csd.oop.calculator.command.Command1;
import eg.edu.alexu.csd.oop.calculator.device.CalculatorDevice;


public class Loading implements Command1 {

	CalculatorDevice theDevice;
	public Loading( CalculatorDevice newDevice){
		theDevice=newDevice;
	}
	
	
	public void execute() {
		theDevice.load();
	}

}
