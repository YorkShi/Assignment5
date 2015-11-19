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
		
			Tuple leftTuple = leftChild.next();
			Tuple rightTuple = rightChild.next();
			int leftIndex = 0;
			int rightIndex = 0;
			int resultIndex = 0;
			ArrayList<Attribute> attributeList= new ArrayList<Attribute>();
			ArrayList<Tuple> leftTupleList = new ArrayList<Tuple>();
			ArrayList<Tuple> rightTupleList = new ArrayList<Tuple>();
			ArrayList<Tuple> returnTuple = new ArrayList<Tuple>();
		
			while(leftTuple!=null){
				leftTupleList.add(leftTuple);
			}
			while(rightTuple!=null){
				rightTupleList.add(rightTuple);
			}
	
			
			for (int i=0; i<leftTupleList.get(0).getAttributeList().size();i++){
				for (int j=0; j<rightTupleList.get(0).getAttributeList().size();j++){
					if(leftTupleList.get(0).getAttributeName(i).equals(rightTupleList.get(0).getAttributeName(j))){
						leftIndex = i;
						rightIndex = j;
						break;
					}
				}	
			
			for(int m =0;m<rightTupleList.size();m++){
				for(int n =0;n<leftTupleList.size();n++){
					Object temp = rightTupleList.get(m).getAttributeValue(rightIndex);
					Object temp2 = leftTupleList.get(n).getAttributeValue(leftIndex);
					if(temp.equals(temp2)){
						attributeList.clear();
						attributeList.addAll(rightTupleList.get(m).getAttributeList());
						attributeList.addAll(leftTupleList.get(n).getAttributeList());
						attributeList.remove((leftTupleList.get(n).getAttributeList().size()+(rightIndex)));
						returnTuple.add(new Tuple(new ArrayList<Attribute>(attributeList)));	
					}
				}
			}
		}
		
		
	
		if(resultIndex!=returnTuple.size()-1){
			newAttributeList.clear();
			resultIndex++;
			newAttributeList.addAll(returnTuple.get(resultIndex).getAttributeList());
			return returnTuple.get(resultIndex);
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