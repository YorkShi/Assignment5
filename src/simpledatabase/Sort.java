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
		if (tuplesResult.size()==0) {
			
			ArrayList<Tuple> tupleList = new ArrayList<Tuple>();
            int n;
			Tuple tuple = child.next();
			
			
			while (null != tuple) {
				tupleList.add(tuple);
				tuple = child.next();
			}

			if(tupleList.isEmpty()) 
                return null;
            

			tuple = tupleList.get(0);
			//find the index
			for(n = 0; n < tuple.getAttributeList().size(); n++) {
				if(tuple.getAttributeName(n).equals(orderPredicate)) {
                    break;
                }
			}

			while(!tupleList.isEmpty()) {
				int m = 0;
				for(int i = 0; i < tupleList.size(); i++) {
					String tempString = tupleList.get(i).getAttributeValue(n).toString();
					String tempString1 = tupleList.get(m).getAttributeValue(n).toString();
					if(tempString.compareTo(tempString1) < 0) {
						//reset the index 
                        m = i;
                    }
				}
				tuplesResult.add(tupleList.get(m));
				tupleList.remove(m);
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