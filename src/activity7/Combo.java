package activity7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * A combo Item with a discount
 */
public class Combo extends AbstractItem
{ 
	private ArrayList<Item> aItems = new ArrayList<>();
	private double aDiscount = 10.0;
	private final static Map<String,Combo> flyweightObjects = new HashMap<String,Combo> (); //key = name + category

	private Combo(String pName, Item...pItems)
	{	
		super(pName, Category.COMBO, originalPrice(Arrays.asList(pItems))*0.9, intersectingDiets(Arrays.asList(pItems)));
		Arrays.asList(pItems).stream().filter(item -> item!= null).forEach(item -> aItems.add(item));
	}
	
	private static double originalPrice(List<Item> pItems) {
		return pItems.stream().mapToDouble(item -> item.price()).sum();
	}
	
	private static Diet[] intersectingDiets(List<Item> pItems){
		return pItems.stream()
				.flatMap(item -> item.diets().stream())
				.collect(Collectors.groupingBy(Function.identity()))
				.entrySet().stream()
				.filter(diet -> diet.getValue().size()==pItems.size())
				.map(diet -> diet.getKey())
				.toArray(Diet[] :: new);
	}
	
	public static Combo getCombo(String pName, Item... pItems) { //unique combo names
		String key = pName;
		if (flyweightObjects.containsKey(key)) {
			System.out.println("Combo name already exists, returning pre-existing combo"); 
			return flyweightObjects.get(key); 
		}
		else {
			Combo combo = new Combo(pName, pItems); 
			flyweightObjects.put(key, combo); 
			return combo; 
		}
	}
	
	public static Combo getCombo(String pName) throws Exception { //unique combo names
		String key = pName;
		if (flyweightObjects.containsKey(key)) {
			System.out.println("Combo exists, returning Item"); 
			return flyweightObjects.get(key); 
		}
		else {
			throw new Exception("Menu does not contain a Combo with that name"); 
		}
	}

	//---------------- adding and removing items from the combo ----------------- 
	public void addItem(Item pItem) {
		assert !aItems.contains(pItem); 
		
		aItems.add(pItem); 
		
		if (aDiscount==0) { 
			setPrice(super.price() + pItem.price()); //add original price of the object to the total combo			
		}
		else {
			setPrice((super.price()+pItem.price())*(1.0 - aDiscount/100.0)); //apply the discount to the updated set of items
		}
		super.setDiets((ArrayList<Diet>) Arrays.asList(intersectingDiets(aItems)));
	}
	
	public void removeItem(Item pItem) {
		aItems.remove(pItem); 
		
		if (aDiscount== 0) { 
			setPrice(super.price()-pItem.price()); //subtract the original price of the object from the total combo			
		}
		else {
			setPrice((super.price()-pItem.price())*(1.0 - aDiscount/100.0)); //apply the discount to the updated set of items
		}
		super.setDiets((ArrayList<Diet>) Arrays.asList(intersectingDiets(aItems)));
	}
	
	//---------------- price methods ------------------
	public void setDiscount(double pDiscount) { 
		aDiscount = pDiscount; 
		super.setPrice(super.price()*(1.0 - aDiscount/100.0)); 
	}
	
	@Override
	public void setPrice(double pPrice) { 
		super.setPrice(pPrice);
		aDiscount = 0; //discount = 0 tells us that the price was set manually
	}
	
	public ArrayList<Item> getItems(){ 
		return new ArrayList<Item>(aItems); 
	}
	
	@Override
	public String description() {
		String info =""; 
		List<String> names = aItems.stream().map(item -> item.name()).collect(Collectors.toList());
		String n = names.get(0);
		for (String name : names.subList(1, names.size())) {
			n = n + " & " +name;
		}
		
		if (aDiscount > 0)
			info = String.format(" Combo Discount : %.2f%% off! , $", aDiscount);
		else
			info = "";
		String []temp = super.description().split("\\$");
		return String.format("%s%s%s%s", temp[0], n, info, temp[1]);
	}

	@Override //to ensure that there are no duplicate combos being added to menu --> duplicates = name + category + items
	public boolean equals(Object pItem) { 
		if (pItem == null) {
			return false;
		}
		if (pItem == this) {
			return true;
		}
		if (pItem.getClass()!= this.getClass()) {
			return false;
		}
		Combo other = (Combo) pItem;
		long diffItems = other.getItems().stream().filter(item -> !this.getItems().contains(item)).count();
		long diffDiets = other.diets().stream().filter(diet -> !this.diets().contains(diet)).count();
		return (other.name()==this.name()&&other.price()==this.price()&&other.category()==this.category()&& diffItems==0 && diffDiets==0);
	}

}
