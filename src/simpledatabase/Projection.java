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
		
		newAttributeList.clear();
		Tuple tuple = child.next();
		Tuple tuple2 = null;
		ArrayList<Attribute> attributeList;
		
		if (tuple != null) {
			attributeList = tuple.getAttributeList();
			for (int i = 0; i < attributeList.size(); i++) {
				if (tuple.getAttributeName(i).equals(attributePredicate)) {
					newAttributeList.add(attributeList.get(i));					
					tuple2 = new Tuple(newAttributeList);
				}
			}
			return tuple2;
		}
		
		return null;
	}
	

	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return child.getAttributeList();
	}

}