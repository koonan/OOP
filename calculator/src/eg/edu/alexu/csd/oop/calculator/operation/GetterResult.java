package eg.edu.alexu.csd.oop.calculator.operation;

import eg.edu.alexu.csd.oop.calculator.command.Command2;
import eg.edu.alexu.csd.oop.calculator.device.CalculatorDevice;

public class GetterResult implements Command2{
	//ModelViewControl controller = ModelViewControl.getInstance();
	CalculatorDevice theDevice;
	public GetterResult(CalculatorDevice newDevice){
		theDevice=newDevice;
	}
	
	
	@Override
	public String execute() {
		return  theDevice.getResult();
	}

}
