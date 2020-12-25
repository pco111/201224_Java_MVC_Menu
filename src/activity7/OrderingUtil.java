package activity7;

import java.util.Comparator;

public class OrderingUtil
{
	public static Comparator<Item> OrderbyAlphabet(){
		return Comparator.comparing(Item::name)
				.thenComparing(OrderbyPrice());
	}
	
	
	public static Comparator<Item> OrderbyPrice(){
		return Comparator.comparing(a->Double.valueOf(a.price()));
	}
}

