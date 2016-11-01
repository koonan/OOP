package eg.edu.alexu.csd.oop.calculator.saveandload;

import java.util.Stack;
import java.io.*;

public class ReaderWriterFile {
	private Stack<String> stack1 = new Stack<String>();

	public Stack <String>read() {

		String fileName = "words.txt";
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);

			BufferedReader bufferReader = new BufferedReader(fileReader);
			while ((line = bufferReader.readLine()) != null ) {
				stack1.push(line);
			}
			bufferReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
		return stack1;
		
	}

	public void write(Stack<String> stack) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(
					"words.txt"));
			while (!stack.isEmpty()) {
				writer.write(stack.pop());
				writer.newLine();
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("write txt error");
		}
		return ;
	}

}
