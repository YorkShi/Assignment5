package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		
		Tuple tuple = child.next();
		
		if (!child.from.equals(whereTablePredicate) && !whereTablePredicate.equals("")) {
			return tuple;
		}
		while (null != tuple) {
			attributeList = tuple.getAttributeList();
			for (int i = 0; i < attributeList.size(); i++) {
				if (attributeList.get(i).getAttributeName().equals(whereAttributePredicate)) {
					if (attributeList.get(i).getAttributeValue().equals(whereValuePredicate))
						return tuple;
				}
			}
			tuple = child.next();
		}
		return null;
			
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}