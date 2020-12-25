package activity7;

import java.util.List;

/**
 * An abstract object for objects that decorate an Item
 */

public abstract class AbstractDecorator implements Item {
	private Item aItem;

	public AbstractDecorator(Item pItem) {
		aItem = pItem;
	}
	
	@Override
	public String toString()
	{
		return String.format("[%s]", description());
	}

	@Override
	public String name() {
		return aItem.name();
	}
	
	@Override
	public double price() {
		return aItem.price();
	}
	
	@Override
	public Category category() {
		return aItem.category();
	}
	
	@Override
	public List<Diet> diets(){
		return aItem.diets();
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
