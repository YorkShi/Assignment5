package simpledatabase;
import java.util.ArrayList;

public class Projection extends Operator{
	
	ArrayList<Attribute> newAttributeList;
	private String attributePredicate;


	public Projection(Operator child, String attributePredicate){
		
		this.attributePredicate = attributePredicate;
		this.child = child;
		newAttributeList = new ArrayList<Attribute>();
		
	}
	
	
	/**
     * Return the data of the selected attribute as tuple format
     * @return tuple
     */
	@Override
	public Tuple next(){
		
		Tuple tuple1 = child.next();
		
		ArrayList<Attribute> attributeList = tuple1.getAttributeList();;
		
		for (int i = 0; i < attributeList.size(); i++) {
			if (tuple1.getAttributeName(i).equals(attributePredicate)) {
				newAttributeList.add(attributeList.get(i));					
			}
		}
		Tuple tuple2 = new Tuple(newAttributeList);
		return tuple2;
	}
	

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}