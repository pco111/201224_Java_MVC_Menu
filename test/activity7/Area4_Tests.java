package activity7;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

class Area4_Tests
{	
	Menu aMenu;
	MenuConfigurationManager aMCM;
	
	@BeforeAll
	public static void setupClass() {
		JavaFXLoader.load();
	}
	
	@BeforeEach
	void setUp() {
		aMenu = Menu.instance();
		aMCM=null;
	}
	
	@Test
	void Test_DrinkSnack()
	{	
		MenuItem testSnack=MenuItem.getMenuItem("testSnack", Category.SNACK, 5.00);
		MenuItem testMain=MenuItem.getMenuItem("testMain", Category.MAIN, 5.00);
		MenuItem testDrink=MenuItem.getMenuItem("testDrink", Category.DRINK, 5.00);
		aMenu.addItem(testSnack);
		aMenu.addItem(testMain);
		aMenu.addItem(testDrink);
		aMCM=new MenuConfigurationManager(Config.DRINK_SNACK, aMenu);
		ObservableList<Item> leftList=getMenuPanelList((MenuPanel)(aMCM.getLeft()));
		ObservableList<Item> centreList=getMenuPanelList((MenuPanel)(aMCM.getCenter()));
		ObservableList<Item> rightList=getMenuPanelList((MenuPanel)(aMCM.getRight()));
		assertEquals(leftList.get(0).toString(),testDrink.toString());
		assertEquals(centreList.get(0).toString(),testMain.toString());
		assertEquals(rightList.get(0).toString(),testSnack.toString());
	}
	
	@Test
	void Test_CheapExpensive()
	{	
		ArrayList<Item> inputList=new ArrayList<>();
		for(int i=0;i<10;i++) {
			inputList.add(MenuItem.getMenuItem("testMain", Category.MAIN,i+1));
			aMenu.addItem(inputList.get(i));
		}
		SizeableItem coffee = new SizeableItem(MenuItem.getMenuItem("Coffee", Category.DRINK, 20.00, Diet.VEGAN, Diet.VEGETARIAN));
		SizeableItem salad = new SizeableItem(MenuItem.getMenuItem("Salad", Category.APPETIZER, 20.00, Diet.GLUTEN_FREE, Diet.VEGAN));
		Item combo = Combo.getCombo("Combo 1", coffee.medium(), salad.small());
		inputList.add(combo);
		aMenu.addItem(combo);
		aMCM=new MenuConfigurationManager(Config.CHEAP_EXPENSIVE, aMenu);
		ObservableList<Item> leftList=getMenuPanelList((MenuPanel)(aMCM.getLeft()));
		ObservableList<Item> centreList=getMenuPanelList((MenuPanel)(aMCM.getCenter()));
		ObservableList<Item> rightList=getMenuPanelList((MenuPanel)(aMCM.getRight()));
		//Checking if leftList displays correctly
		for(int i=0;i<10;i++) {
			boolean isContained=leftList.contains(inputList.get(i));
			assertTrue(isContained);
		}
		//Checking if centreList displays correctly
		for(int i=0;i<10;i++) {
			boolean isContained=centreList.contains(inputList.get(i));
			assertTrue(isContained);
		}
		boolean isContained=centreList.contains(inputList.get(10));
		assertTrue(isContained);
		//Checking if rightList displays correctly
		isContained=rightList.contains(inputList.get(10));
		assertTrue(isContained);
	}
	
	@Test
	void Test_ConfigSwitch()
	{
		ArrayList<Item> inputList=new ArrayList<>();
		for(int i=0;i<10;i++) {
			inputList.add(MenuItem.getMenuItem("testMain", Category.MAIN,i+1));
			aMenu.addItem(inputList.get(i));
		}
		SizeableItem coffee = new SizeableItem(MenuItem.getMenuItem("Coffee", Category.DRINK, 20.00, Diet.VEGAN, Diet.VEGETARIAN));
		SizeableItem salad = new SizeableItem(MenuItem.getMenuItem("Salad", Category.APPETIZER, 20.00, Diet.GLUTEN_FREE, Diet.VEGAN));
		Item combo = Combo.getCombo("Combo 1", coffee.medium(), salad.small());
		inputList.add(combo);
		aMenu.addItem(combo);
		aMCM=new MenuConfigurationManager(Config.CHEAP_EXPENSIVE, aMenu);		
		
		// test if the config is set properly
		Config config = getConfig(aMCM);
		assertEquals(Config.CHEAP_EXPENSIVE, config);
		
		
		// test if the config is set properly after change
		aMCM.configurationChanged(Config.DRINK_SNACK);
		config = getConfig(aMCM);
		assertEquals(Config.DRINK_SNACK, config);
		
	}
	
	private ObservableList<Item> getMenuPanelList(MenuPanel pMP) {
		try {
			Field listField = MenuPanel.class.getDeclaredField("list");
			listField.setAccessible(true);
			return (ObservableList<Item>) listField.get(pMP);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
			return null; //Will never return this
		}
	}
	
	private Config getConfig(MenuConfigurationManager mcm) {
		Field configField;
		try
		{
			configField = aMCM.getClass().getDeclaredField("aConfig");
			configField.setAccessible(true);
			Config config = (Config)configField.get(mcm);
			return config;
		}
		catch (Exception e)
		{
			fail();
			return null;
		}
		
	}

}
