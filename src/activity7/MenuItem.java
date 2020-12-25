/**
 * 
 */
package activity7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * a Composite class that implements the component interface
 *
 */
public class MenuItem extends AbstractItem{

	private final static Map<String,MenuItem> flyweightObjects = new HashMap<String,MenuItem> (); //key = name
	
	/**
	 * Constructs a food item
	 * @param pName, name of the item
	 * @param pCategory, category of the item
	 * @param pPrice, price of the item
	 * @pre pName != null && pCategory != null && pPrice > 0
	 */
	private MenuItem(String pName, Category pCategory, double pPrice, Diet... pDiets)
	{
		super(pName, pCategory, pPrice, pDiets);
	}
	
	public static MenuItem getMenuItem(String pName, Category pCategory, double pPrice, Diet... pDiets) { 
		String key = pName;
		
		if (flyweightObjects.containsKey(key)) {
			System.out.println("MenuItem already exists, returning pre-existing MenuItem"); 
			return flyweightObjects.get(key); 
		}
		else {
			MenuItem menuitem = new MenuItem(pName, pCategory, pPrice, pDiets); 
			flyweightObjects.put(key, menuitem); 
			return menuitem; 
		}
		
	}
	
	public static MenuItem getMenuItem(String pName) throws Exception { 
		
		String key = pName; 
		
		if (flyweightObjects.containsKey(key)) {
			System.out.println("MenuItem exists, returning Item"); 
			return flyweightObjects.get(key); 
		}
		else {
			throw new Exception("Menu does not contain an Item with that name"); 
		}
		
	}
	
	@Override 
	public boolean equals(Object pItem) { 
		if (pItem == null) {
			return false;
		}
		if (pItem == this) {
			return true;
		}
		if (pItem.getClass()!=this.getClass()) {
			return false;
		}
		MenuItem other = (MenuItem) pItem;
		long diff = other.diets().stream().filter(diet -> !this.diets().contains(diet)).count();
		return (other.name() == this.name() && other.price() == this.price() && other.category() == this.category() && diff==0);
	}

}