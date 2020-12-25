package activity7;

import java.util.Comparator;

/*This class contains enum constants that each represent a way to sort lists and tied to a comparator
 * Comparators are implemented in the OrderingUtil class. 
 * An application of functional Strategy pattern. Functional implementation is chosen because
 * if we use objects the comparators are essentially just objects with only one function.
 */

public enum Order
{

	ALPHABETICAL(OrderingUtil.OrderbyAlphabet()), 
	PRICE(OrderingUtil.OrderbyPrice());
	
	private String pName;
	private Comparator<Item> aOrder;
	
	Order(Comparator<Item> aOperation) {
		aOrder=aOperation;
	}
	
	public Comparator<Item> getComparator(){
		return aOrder;
	}
	
	
	
	
}
