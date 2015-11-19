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
		if(tuplesResult.size() == 0){
			
			ArrayList<Tuple> tempList = new ArrayList<Tuple>(); 
			Tuple tuple = child.next();
			
			while(tuple != null){
				tempList.add(tuple);
			}
			if(tempList.isEmpty()){
				return null;
			}
			tuple = tempList.get(0);
			int m;
			for(m = 0; m < tuple.getAttributeList().size(); m++) {
				if(tuple.getAttributeName(m).equals(orderPredicate)) {
                    break;
                }
			}

			while(!tempList.isEmpty()) {
				int n = 0;
				for(int i = 0; i < tempList.size(); i++) {
					String tempString = tempList.get(i).getAttributeValue(m).toString();
					String tempString1 = tempList.get(n).getAttributeValue(m).toString();
					if(tempString.compareTo(tempString1) < 0) {
                        n = i;
                    }
				}
				tuplesResult.add(tempList.get(n));
				tempList.remove(n);
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