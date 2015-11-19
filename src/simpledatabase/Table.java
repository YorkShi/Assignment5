package simpledatabase;

import java.util.ArrayList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private Tuple tuple;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
			
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		ArrayList<Attribute> attributeList = new ArrayList<Attribute>(); 
		Tuple tuple = null;
		String attributeName = null;
		String attributeType = null;
		String attributeValue;
		try{
			if(getAttribute != true){
				attributeName = br.readLine();
				attributeType = br.readLine();
				getAttribute = true;
			}else{
				attributeValue = br.readLine();
				
				if(null == attributeValue){
					return null;
				}
				
				tuple = new Tuple(attributeName, attributeType, attributeValue);
				tuple.setAttributeName();
				tuple.setAttributeType();
				tuple.setAttributeValue();
				
			}
	      }catch(Exception e){
	         e.printStackTrace();
	      }
		
		return tuple;
	}
	


	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}