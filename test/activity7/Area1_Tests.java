package activity7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Area1_Tests {

	private static Menu aMenu = Menu.instance(); 
	//some menu items
	private SizeableItem coffee = new SizeableItem(MenuItem.getMenuItem("coffee", Category.DRINK, 3f));
	private SizeableItem juice = new SizeableItem(MenuItem.getMenuItem("fruit juice", Category.DRINK, 3f));
	private SizeableItem tea = new SizeableItem(MenuItem.getMenuItem("tea", Category.DRINK, 2.5f));
	private MenuItem soup = MenuItem.getMenuItem("soup", Category.APPETIZER, 6f, Diet.VEGETARIAN);
	private SizeableItem salad = new SizeableItem(MenuItem.getMenuItem("salad", Category.APPETIZER, 7f, Diet.VEGETARIAN));
	private MenuItem pasta = MenuItem.getMenuItem("pasta", Category.MAIN, 13f);
	private MenuItem gf_pasta = MenuItem.getMenuItem("pasta", Category.MAIN, 15f, Diet.GLUTEN_FREE, Diet.VEGETARIAN);
	private MenuItem pizza = MenuItem.getMenuItem("pizza", Category.MAIN, 14f);
	private MenuItem burger = MenuItem.getMenuItem("hamburger", Category.MAIN, 15f);
	private MenuItem chocolate_cake = MenuItem.getMenuItem("chocolate cake", Category.DESSERT, 7f);
	private MenuItem cheesecake = MenuItem.getMenuItem("cheesecake", Category.DESSERT, 8f);
	private MenuItem chips = MenuItem.getMenuItem("chips", Category.SNACK, 3f, Diet.VEGETARIAN);
	private MenuItem fruit_cup = MenuItem.getMenuItem("fruit salad", Category.SNACK, 5.5f, Diet.VEGAN, Diet.VEGETARIAN);
	private Combo combo1 = Combo.getCombo("soup and salad", soup, salad.small());

	/**
	 * clear the menu
	 */
	@BeforeEach
	public void setup() {
		try {
			Field field = Menu.class.getDeclaredField("aMenuItems");
			field.setAccessible(true);
			field.set(aMenu, new ArrayList<>());
		}catch (ReflectiveOperationException e) {
			
		}
	}
	
	/**
	 * helper to get current list of items from aMenu
	 * @param pMenu
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<Item> get(){
		try {
			Field field = Menu.class.getDeclaredField("aMenuItems");
			field.setAccessible(true);
			return (ArrayList<Item>) field.get(aMenu);
		}catch(ReflectiveOperationException e) {
			fail();
			return null;
		}
	}
	
	/**
	 * addItem(Item) adds to aMenuItems list
	 */
	@Test
	public void add_item(){
		aMenu.addItem(chips);
		assertEquals(new ArrayList<Item>(Arrays.asList(chips)),get());
	}
	
	/**
	 * addItem(combo) doesn't add 2 equal combos to list
	 */
	@Test
	public void add_combo() {
		aMenu.addItem(combo1);
		aMenu.addItem(Combo.getCombo("soup and salad", soup, salad.small()));
		assertEquals(new ArrayList<Item>(Arrays.asList(combo1)), get());
	}
	
	/**
	 * addItem(Item) does not add 2 equal items to the list
	 */
	@Test
	public void add_two_same_items() {
		aMenu.addItem(chips);
		aMenu.addItem(chips);
		assertEquals(new ArrayList<Item>(Arrays.asList(chips)),get());
	}
	
	/**
	 * addItem(Sizeable) adds all three objects
	 */
	@Test
	public void add_sizeable_item() {
		aMenu.addItem(coffee);
		assertEquals(new ArrayList<Item>(Arrays.asList(coffee.small(), coffee.medium(), coffee.large())), get());
	}
	
	@Test
	public void add_two_same_sizeable_items() {
		aMenu.addItem(coffee);
		aMenu.addItem(coffee);
		assertEquals(new ArrayList<Item>(Arrays.asList(coffee.small(), coffee.medium(), coffee.large())), get());
	}
	
	/**
	 * addItem(Item) does not add null item to aMenuItems list
	 */
	@Test
	public void add_null_item() {
		try {
			aMenu.addItem(new SizeableItem(null));
			fail();
		}catch (AssertionError e) {
			//expected
		}
	}
	
	/**
	 * deleteItem(Item) removes item in aMenuItems list
	 */
	@Test
	public void remove_existing_item() {
		aMenu.addItem(chips);
		aMenu.removeItem(chips);
		assertTrue(get().isEmpty());
	}

	/**
	 * deleteItem(Item) does not remove item not in aMenuItems list
	 */
	@Test
	public void remove_non_existing_item() {
		ArrayList<Item> before = get();
		aMenu.removeItem(chips);
		ArrayList<Item> after = get();
		assertEquals(before,after);
	}
	
	
	/**
	 * verify return value from items() method is equal to the aMenuItems field
	 */
	@Test
	public void get_items_equals_field() {
		aMenu.addItem(salad);
		aMenu.addItem(soup);
		aMenu.addItem(chocolate_cake);
		assertTrue(get().equals(new ArrayList<Item> (aMenu.items().collect(Collectors.toList()))));
	}
	
	/**
	 * verify an item has a category field that is accurate
	 */
	@Test
	public void item_category() {
		try {
			Field category = chips.getClass().getSuperclass().getDeclaredField("aCategory");
			category.setAccessible(true);
			assertEquals(Category.SNACK, (Category)category.get(chips));
		}catch (ReflectiveOperationException e) {
			fail();
		}
	}
	
	/**
	 * verify an item has a name field that is accurate
	 */
	@Test
	public void item_name() {
		try {
			Field name = chips.getClass().getSuperclass().getDeclaredField("aName");
			name.setAccessible(true);
			assertEquals("chips", (String) name.get(chips));
		}catch (ReflectiveOperationException e) {
			fail();
		}
	}
	
	/**
	 * verify an item has a price field that is accurate
	 */
	@Test
	public void item_price() {
		try {
			Field price = chips.getClass().getSuperclass().getDeclaredField("aPrice");
			price.setAccessible(true);
			assertEquals(3.0, (Double) price.get(chips));
		}catch (ReflectiveOperationException e) {
			fail();
		}
	}
	
	/**
	 * verify an item has a diets field that is accurate
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void item_diets() {
		try {
			Field diets = chips.getClass().getSuperclass().getDeclaredField("aDiets");
			diets.setAccessible(true);
			assertEquals(new ArrayList<Diet>(Arrays.asList
					(Diet.VEGETARIAN)), (ArrayList<Diet>) diets.get(chips));
		}catch (ReflectiveOperationException e) {
			fail();
		}
	}
	
	/**
	 * verify filter below a price with a result
	 */
	@Test
	public void filter_below_price_result() {
		aMenu.addItem(soup);
		aMenu.addItem(pasta);
		List<Item> result = (ArrayList<Item>) FilteringUtils.filterBelowPrice(7f).filter(aMenu.items()).collect(Collectors.toList());
		assertEquals(new ArrayList<Item>(Arrays.asList(soup)), result);
	}
	/**
	 * verify filter below a price with no result
	 */
	@Test
	public void filter_below_price_no_result() {
		aMenu.addItem(soup);
		aMenu.addItem(pasta);
		List<Item> result = (ArrayList<Item>) FilteringUtils.filterBelowPrice(5f).filter(aMenu.items()).collect(Collectors.toList());
		assertTrue(result.isEmpty());
	}
	
	/**
	 * verify filter between prices with a result
	 */
	@Test
	public void filter_between_prices_result() {
		aMenu.addItem(soup);
		aMenu.addItem(pasta);
		aMenu.addItem(burger);
		List<Item> result = (ArrayList<Item>) FilteringUtils.filterPriceRange(5f, 14f).filter(aMenu.items()).collect(Collectors.toList());
		assertEquals(new ArrayList<Item>(Arrays.asList(soup, pasta)), result);
	}
	
	/**
	 * verify filter between prices with no result
	 */
	@Test
	public void filter_between_prices_no_result() {
		aMenu.addItem(soup);
		aMenu.addItem(pasta);
		aMenu.addItem(burger);
		List<Item> result = (ArrayList<Item>) FilteringUtils.filterPriceRange(18f, 20f).filter(aMenu.items()).collect(Collectors.toList());
		assertTrue(result.isEmpty());
	}
	
	/**
	 * verify filter on a category with a result
	 */
	@Test
	public void filter_category_result() {
		aMenu.addItem(coffee);
		aMenu.addItem(soup);
		aMenu.addItem(pasta);
		aMenu.addItem(chocolate_cake);
		List<Item> result = (ArrayList<Item>) FilteringUtils.filterCategory(Category.DRINK).filter(aMenu.items()).collect(Collectors.toList());
		assertEquals(new ArrayList<Item>(Arrays.asList(coffee.small(), coffee.medium(), coffee.large())), result);
	}
	
	/**
	 * verify filter on a category with no result
	 */
	@Test
	public void filter_category_no_result() {
		aMenu.addItem(coffee);
		aMenu.addItem(soup);
		aMenu.addItem(pasta);
		aMenu.addItem(chocolate_cake);
		List<Item> result = (ArrayList<Item>) FilteringUtils.filterCategory(Category.COMBO).filter(aMenu.items()).collect(Collectors.toList());
		assertTrue(result.isEmpty());
	}
	
	/**
	 * verify filter on a diet with a result
	 */
	@Test
	public void filter_diets_result() {
		aMenu.addItem(soup);
		aMenu.addItem(salad);
		aMenu.addItem(pasta);
		aMenu.addItem(pizza);
		aMenu.addItem(burger);
		List<Item> result = (ArrayList<Item>) FilteringUtils.filterDiets(Diet.VEGETARIAN).filter(aMenu.items()).collect(Collectors.toList());
		assertEquals(new ArrayList<Item>(Arrays.asList(soup, salad.small(), salad.medium(), salad.large())), result);
	}
	
	/**
	 * verify filter on a diet with no result
	 */
	@Test
	public void filter_diets_no_result() {
		aMenu.addItem(soup);
		aMenu.addItem(salad);
		aMenu.addItem(pasta);
		aMenu.addItem(pizza);
		aMenu.addItem(burger);
		List<Item> result = (ArrayList<Item>) FilteringUtils.filterDiets(Diet.VEGETARIAN, Diet.KOSHER).filter(aMenu.items()).collect(Collectors.toList());
		assertTrue(result.isEmpty());
	}
}
