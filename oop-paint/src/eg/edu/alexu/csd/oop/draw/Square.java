package eg.edu.alexu.csd.oop.draw;

import java.util.HashMap;
import java.util.Map;

public class Square extends Rectangle{
	
	private Map<String, Double> squareMap ;
	
	public Square(){
		squareMap = new HashMap<String, Double>();
		squareMap.put("Length", null);
	}
	
	@Override
	public void setProperties(Map<String, Double> properties) {
		
		// TODO Auto-generated method stub
		if(properties != null){
		squareMap.putAll(properties);
		this.length=squareMap.get("Length");
		this.width=this.length;
		}
		else{
			squareMap=null;
		}
	}
	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return squareMap;
	}
}
