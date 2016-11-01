package eg.edu.alexu.csd.oop.calculator.operation;


import eg.edu.alexu.csd.oop.calculator.command.Command2;
import eg.edu.alexu.csd.oop.calculator.device.CalculatorDevice;

public class Current implements Command2{
	CalculatorDevice theDevice;
	private static int current=-1;
	public  Current(CalculatorDevice newDevice) {
		theDevice= newDevice;
	}
	@Override
	public String  execute() {
		return theDevice.current();
	}
	public static int getCurrent() {
		return current;
	}
	public static void setCurrent(int curr) {
		current = curr;
	}

}