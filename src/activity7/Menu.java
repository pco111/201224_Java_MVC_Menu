package activity7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents a menu that can be displayed in the menu display.
 */
public class Menu implements Iterable<Item>
{
	
	private final static Menu MenuInstance = new Menu(); 
	private List<Item> aMenuItems = new ArrayList<>(); 
	private ArrayList<Observer> aObservers = new ArrayList<>();

	
	private Menu() 
	{
	}
	
	public static Menu instance()
	{
		return MenuInstance;
	}
	
	public void addObserver(Observer pObserver) {
	
		aObservers.add(pObserver);
	}
	
	public void removeAllObserver() {
		aObservers.clear();
	}

	/**
	 * add an item to the menu if it's not already added
	 * @param pItem != null
	 */
	public void addItem(Item pItem) {
		assert pItem != null;
		if (!aMenuItems.contains(pItem)) {
			aMenuItems.add(pItem);
			notifyObservers();
			return;
		}
		System.out.println("This item has already been added to the menu. No changes made");
	}
	
	/**
	 * add a sizeable item to the menu if it's not already added
	 * @param pItem
	 */
	public void addItem(SizeableItem pItem) {
		assert pItem.small() != null && pItem.medium()!=null && pItem.large()!=null;
		if (!aMenuItems.contains(pItem.small())&&!aMenuItems.contains(pItem.medium())&&!aMenuItems.contains(pItem.large())) {
			aMenuItems.addAll(Arrays.asList(pItem.small(),pItem.medium(),pItem.large()));
			notifyObservers();
		}
	}

	/**
	 * remove an item from the menu if it's in the menu
	 * @param pItem
	 */
	public void removeItem(Item pItem) {
		if (aMenuItems.contains(pItem)) {
			aMenuItems.remove(pItem);
			notifyObservers();
		}
	}

	
	public void removeAllItem() {
		this.aMenuItems.clear();
		notifyObservers();
	}
	

	/**
	 * get all menu items
	 * @return Stream of items
	 */
	public Stream<Item> items() {
		return aMenuItems.stream();
	}

	/**
	 * notify observers of menu change
	 */
	private void notifyObservers() {
		for (Observer observer : aObservers)
		{
			observer.menuChanged();
		}
	}
	
	@Override
	public Iterator<Item> iterator()
	{
		return aMenuItems.iterator();
	}
	
	@Override
	public String toString()
	{
		return Arrays.toString(aMenuItems.toArray());
	}

}
