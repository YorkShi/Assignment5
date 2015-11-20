package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	ArrayList<Tuple> tuples1;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();
		
	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		Tuple tuple;
		int n = 2;
		int m = 0;
        while(null!=(tuple = leftChild.next())) {
            tuples1.add(tuple);
        }

        if(null!=(tuple = rightChild.next())) {
            for(int i = 0; i < tuples1.size(); i++) {
            	Object tempValue = tuple.getAttributeList().get(n).attributeValue;
            	Object tempValue1 = tuples1.get(i).getAttributeList().get(m).attributeValue;
                if(tempValue.equals(tempValue1)) {
                    tuple.getAttributeList().addAll(tuples1.get(i).getAttributeList());
                }
            }
            return tuple;
        }
        return null;
	
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}