package activity7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;

public class Area2_Tests
{
	private SizeableItem coffee = new SizeableItem(MenuItem.getMenuItem("Coffee", Category.DRINK, 3.50, Diet.VEGAN, Diet.VEGETARIAN));
	private Item steak = MenuItem.getMenuItem("Steak", Category.MAIN, 16.80);
	private SizeableItem fries = new SizeableItem(MenuItem.getMenuItem("Fries", Category.SNACK, 5.50, Diet.VEGETARIAN));
	private SizeableItem salad = new SizeableItem(MenuItem.getMenuItem("Salad", Category.APPETIZER, 6.00, Diet.GLUTEN_FREE, Diet.VEGAN));
	private Item combo1 = Combo.getCombo("Combo 1", coffee.medium(), salad.small());
	private Item combo2 = Combo.getCombo("Combo 2", fries.small(), steak);

	
	private Menu aMenu;
	
	/**
	 * setup the menu with items before each test
	 */
	@BeforeEach
	public void initMenu()
	{
		aMenu = Menu.instance();
		aMenu.addItem(coffee);
		aMenu.addItem(steak);
		aMenu.addItem(fries);
		aMenu.addItem(salad);
		
	}
	
	/**
	 * test to see if SizeableItem's price is adjusted according to size
	 */
	@Test
	public void getSizePrice()
	{
		double smallPrice = salad.small().price();
		double mediumPrice = salad.medium().price();
		double largePrice = salad.large().price(); 
	
		assertEquals(smallPrice, 6.00 * 0.75);
		assertEquals(mediumPrice, 6.00);
		assertEquals(largePrice, 6.00 * 1.25);
	}
	
	/**
	 * test for adding Combo items to Menu
	 */
	@Test
	public void addComboToMenu() 
	{
		aMenu.removeAllItem();
		aMenu.addItem(combo1);
		assertEquals(Arrays.asList(combo1), getMenuItems(aMenu));
	}
	
	/**
	 * test for combo discount price with adjustment of price from item's size
	 */
	@Test
	public void getComboPrice()
	{
		double combo1Price = combo1.price();
		double combo2Price = combo2.price();
		
		assertEquals(combo1Price, ((coffee.medium().price()+salad.small().price()) * 0.9));
		assertEquals(combo2Price, ((fries.small().price()+steak.price()) * 0.9));
	}
	
	/** 
	 * test for setting individual items on Special price
	 */
	@Test
	public void setItemSpecial()
	{
		Item c = coffee.small().setSpecial(20);
		Item s = steak.setSpecial(15);
		
		assertEquals(c.price(), 3.50 * 0.75 * 0.8);
		assertEquals(s.price(), 16.80 * 0.85);
	}
	
	/**
	 * test for setting combos on special price
	 */
	@Test
	public void setComboSpecial()
	{
		Item combo1Special = combo1.setSpecial(10);
		assertEquals(combo1Special.price(), combo1.price() * 0.9);
	}
	/**
	 * get aMenuItems from a menu
	 * @param pMenu
	 * @return aMenuItems from menu
	*/ 
	@SuppressWarnings("unchecked")
	private ArrayList<Item> getMenuItems(Menu pMenu){
		try {
			Field field = Menu.class.getDeclaredField("aMenuItems");
			field.setAccessible(true);
			return (ArrayList<Item>) field.get(pMenu);
		}catch(ReflectiveOperationException e) {
			fail();
			return null;
		}
	}
	
	
	
	
}