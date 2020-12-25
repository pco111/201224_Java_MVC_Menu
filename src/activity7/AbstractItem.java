package activity7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An abstract object for Items
 */
public class AbstractItem implements Item {

	private String aName;
	private double aPrice;
	private Category aCategory;
	private ArrayList<Diet> aDiets = new ArrayList<Diet>();
	
	protected AbstractItem(String pName, Category pCategory, double pPrice, Diet...pDiets) {
		aName = pName;
		aCategory = pCategory;
		aPrice = pPrice;
		Arrays.asList(pDiets).stream().filter(diet -> diet!= null).forEach(diet -> aDiets.add(diet));
	}
	
	protected void setPrice(double pPrice) {
		aPrice = pPrice;
	}
	
	protected void setDiets(ArrayList<Diet> pDiets) {
		aDiets.clear();
		pDiets.stream().filter(diet -> diet!=null).forEach(diet -> aDiets.add(diet));
	}
	
	@Override
	public String toString() {
		return String.format("[%s]", description());
	}
	
	@Override
	public String name() {
		return aName;
	}

	@Override
	public String description() {
		String info; 
		if (!diets().isEmpty()) {
			info = ", " + diets().toString();
		}
		else
			info = "";
		return String.format("%s%s, $%.2f", aName, info, aPrice);
	}

	@Override
	public double price() {
		return aPrice;
	}

	@Override
	public Category category() {
		return aCategory;
	}

	@Override
	public List<Diet> diets() {
		return new ArrayList<Diet>(aDiets);
	}

	@Override
	public Item setSpecial(int pSpecial) {
		return new SpecialItem(this, pSpecial);
	}

	@Override
	public int compareTo(Item pItem) {
		return this.description().compareTo(pItem.description());
	}

}
