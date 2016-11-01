package eg.edu.alexu.csd.oop.calculator.button;

import eg.edu.alexu.csd.oop.calculator.command.Command1;

public class Button1 {

	Command1 theCommand;

	public Button1(Command1 newCommand) {

		theCommand = newCommand;

	}

	public void press() {

		theCommand.execute();

	}

}