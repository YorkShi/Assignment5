package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;
import simpledatabase.Type.DataTypes;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	ArrayList<Tuple> tuplesResult;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
		
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		int m,n = 0;
		if(tuplesResult.size() == 0){
			
			ArrayList<Tuple> tupleList = new ArrayList<Tuple>(); 
			Tuple tuple = child.next();
			
			while(tuple != null){
				tupleList.add(tuple);
			}
			tuple = tupleList.get(0);
			
		
			for(m = 0; m < tuple.getAttributeList().size(); m++) {
				if(tuple.getAttributeName(m).equals(orderPredicate)) {
                    break;
                }
			}

			while(tupleList.isEmpty()!=true) {
				
				for(int i = 0; i < tupleList.size(); i++) {
					
					String tempString = tupleList.get(i).getAttributeValue(m).toString();
					String tempString1 = tupleList.get(n).getAttributeValue(m).toString();
		
					if(tempString.compareTo(tempString1) < 0) {
                        n = i;
                    }
				}
				tuplesResult.add(tupleList.get(n));
				tupleList.remove(n);
			}
		}	
		return tuplesResult.remove(0);
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}