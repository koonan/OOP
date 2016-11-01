package eg.edu.alexu.csd.oop.calculator.button;

import eg.edu.alexu.csd.oop.calculator.command.Command2;

public class Button2{
	
	Command2 theCommand;
	
	public Button2(Command2 newCommand){
		
		theCommand = newCommand;
		
	}
	
	public String press(){
		
		return theCommand.execute();
		
	}
	

	
}