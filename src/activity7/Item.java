package activity7;

import java.util.List;

public interface Item extends Comparable<Item>
{
	String name();
	String description();
	double price();
	Category category();
	List<Diet> diets();
	
	Item setSpecial(int pSpecial);
	
	@Override
	int compareTo(Item pItem);
	
	@Override 
	boolean equals(Object object);

}
