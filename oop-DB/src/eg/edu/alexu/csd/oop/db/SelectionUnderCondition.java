package eg.edu.alexu.csd.oop.db;



public class SelectionUnderCondition {

	public Object[][] conditionSelect(String path, String tableName,
			String required, String condition, String operator, String value) {
		
		Selection2 obj = new Selection2();
		Object[][] table = obj.select(path, tableName, null , true);

		condition = condition.trim();
		value = value.trim();
		boolean valid = false;
		for (int j = 0; j < table[0].length; j++) {
			if(table[0][j].toString().contains(condition)){
				condition = "" + j;
				valid = true;
				break;
			}

		}
		if(!valid){
			System.out.println("table not hase column named " + condition);
					
		}
				
		int counter =0 ;
		int conditionCol = Integer.parseInt(condition);
		boolean [] rowToAdd = new boolean[table.length];
		for (int i = 1; i < table.length; i++) {
			switch (operator) {
			case "=":
				if (table[i][conditionCol].toString().equals(value)) {
					rowToAdd[i] = true;
					counter++;
				}
				break;

			case ">": // must be int type
				if (!table[i][0].equals("null")
						&& Integer.parseInt(table[i][conditionCol].toString()) > Integer.parseInt(value)) {
					rowToAdd[i] = true;
					counter++;
				}
				break;

			case "<": // must be int type
				if (!table[i][0].equals("null")
						&& Integer.parseInt(table[i][conditionCol].toString()) < Integer.parseInt(value)) {
					rowToAdd[i] = true;
					counter++;
				}
				break;

			default:
				return null;
			}

		}
		table = obj.select(path, tableName, required , true);
		
		Object[][] newTable = new Object[counter][table[0].length];
		//newTable[0] = table[0];
		int index = 0;
		for(int i=0 ; i<rowToAdd.length ; i++){
			if(rowToAdd[i]){
				newTable[index] = table[i];
				index++;
			}
		}
	
		return newTable;
	}
}
