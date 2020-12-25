package activity7;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;

public class Area3_Tests
{
	Menu aMenu;
	MenuConfigurationManager aMCM;
	
	@BeforeAll
	public static void setupClass() {
		JavaFXLoader.load();
		
	}
	
	@BeforeEach
	void setUp() {
		aMenu=Menu.instance();
		aMenu.removeAllItem();
		
		aMCM=new MenuConfigurationManager(Config.DRINK_SNACK, aMenu);
		
		MenuItem testDrink1 = MenuItem.getMenuItem("testDrink1", Category.DRINK, 5.00); 
		MenuItem testDrink2 = MenuItem.getMenuItem("testDrink2", Category.DRINK, 3.00); 
		MenuItem testDrink3 = MenuItem.getMenuItem("testDrink3", Category.DRINK, 1.00);
		
		MenuItem testMain1 = MenuItem.getMenuItem("testMain1", Category.MAIN, 15.00);
		MenuItem testMain2 = MenuItem.getMenuItem("testMain2", Category.MAIN, 9.00);
		MenuItem testMain3 = MenuItem.getMenuItem("testMain3", Category.MAIN, 5.00);
		
		MenuItem testSnack1 = MenuItem.getMenuItem("testSnack1", Category.SNACK, 5.00); 
		MenuItem testSnack2 = MenuItem.getMenuItem("testSnack2", Category.SNACK, 3.00); 
		MenuItem testSnack3 = MenuItem.getMenuItem("testSnack3", Category.SNACK, 2.00); 

		aMenu.addItem(testDrink1);
		aMenu.addItem(testDrink2);
		aMenu.addItem(testDrink3);
		
		aMenu.addItem(testMain1);
		aMenu.addItem(testMain2);
		aMenu.addItem(testMain3);
		
		aMenu.addItem(testSnack1);
		aMenu.addItem(testSnack2);
		aMenu.addItem(testSnack3);
		
		
	}
	
	
	// Test that MenuPanel in a particular configuration does update itself when corresponding changes to the menu are made. 
	// In the following test, the left panel in aMCM should only contain DRINK items, and only update itself when drink items are added.
	// Ordered by price descending
	@Test
	void TestAddUpdateLeftPanel()
	{		
		
		assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(0).name().equals("testDrink1"));
		assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(2).name().equals("testDrink3"));
		
		aMenu.addItem(MenuItem.getMenuItem("addedSnack", Category.SNACK, 10.00));
		aMenu.addItem(MenuItem.getMenuItem("addedMain", Category.MAIN, 10.00));
		aMenu.addItem(MenuItem.getMenuItem("addedDrink", Category.DRINK, 10.00));
		
		assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(0).name().equals("addedDrink"));
		assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(3).name().equals("testDrink3"));
		aMenu=null;
	}
	
	// In the following test, the Center panel in aMCM should only contain MAIN items, and only update itself when MAIN items are added.
		@Test
		void TestAddUpdateCenterPanel()
		{	
			assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(0).name().equals("testMain1")); 
			assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(2).name().equals("testMain3")); 
			
			aMenu.addItem(MenuItem.getMenuItem("addedSnack", Category.SNACK, 10.00));
			aMenu.addItem(MenuItem.getMenuItem("addedMain", Category.MAIN, 10.00));
			aMenu.addItem(MenuItem.getMenuItem("addedDrink", Category.DRINK, 10.00));
			
			assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(0).name().equals("addedMain")); 
			assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(3).name().equals("testMain3"));
			aMenu=null;
		}
	
	// In the following test, the Center panel in aMCM should only contain MAIN items, and only update itself when MAIN items are added.
		@Test
		void TestAddUpdateRightPanel()
		{	
			assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(0).name().equals("testSnack1")); 
			assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(2).name().equals("testSnack3")); 
			
			aMenu.addItem(MenuItem.getMenuItem("addedSnack", Category.SNACK, 10.00));
			aMenu.addItem(MenuItem.getMenuItem("addedMain", Category.MAIN, 10.00));
			aMenu.addItem(MenuItem.getMenuItem("addedDrink", Category.DRINK, 10.00));
			
			assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(0).name().equals("addedSnack"));
			assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(3).name().equals("testSnack3"));
			aMenu=null;
		}
	
		// In the following test, the left panel in aMCM should only contain DRINK items, and only update itself when drink items are added.
		// Ordered by price descending
		@Test
		void TestRemoveUpdateLeftPanel()
		{		
			
			assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(0).name().equals("testDrink1"));
			assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(2).name().equals("testDrink3"));
			
			aMenu.removeItem(MenuItem.getMenuItem("testSnack1", Category.SNACK, 10.00));
			aMenu.removeItem(MenuItem.getMenuItem("testMain1", Category.MAIN, 15.00));
			aMenu.removeItem(MenuItem.getMenuItem("testDrink1", Category.DRINK, 5.00));
			
			assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(0).name().equals("testDrink2"));
			assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(1).name().equals("testDrink3"));
			aMenu=null;
		}
		
		// In the following test, the Center panel in aMCM should only contain MAIN items, and only update itself when MAIN items are added.
		@Test
		void TestRemoveUpdateCenterPanel()
			{	
				assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(0).name().equals("testMain1"));
				assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(2).name().equals("testMain3"));
				
				aMenu.removeItem(MenuItem.getMenuItem("testSnack1", Category.SNACK, 10.00));
				aMenu.removeItem(MenuItem.getMenuItem("testMain1", Category.MAIN, 15.00));
				aMenu.removeItem(MenuItem.getMenuItem("testDrink1", Category.DRINK, 5.00));
				
				
				assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(0).name().equals("testMain2"));
				assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(1).name().equals("testMain3"));
				aMenu=null;
			}
		
		// In the following test, the Center panel in aMCM should only contain MAIN items, and only update itself when MAIN items are added.
		@Test
		void TestRemoveUpdateRightPanel()
			{	
				assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(0).name().equals("testSnack1")); 
				assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(2).name().equals("testSnack3")); 
				
				aMenu.removeItem(MenuItem.getMenuItem("testSnack1", Category.SNACK, 10.00));
				aMenu.removeItem(MenuItem.getMenuItem("testMain1", Category.MAIN, 15.00));
				aMenu.removeItem(MenuItem.getMenuItem("testDrink1", Category.DRINK, 5.00));
				
				assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(0).name().equals("testSnack2"));
				assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(1).name().equals("testSnack3"));
				aMenu=null;
			}
			
		
	
	// Test for using the changeOrder() method in MenuPanel to change the order of a panel according to orders defined in the enum Order
	// In default DRINK_SNACK config the panels are ordered alphabetically, we try to change it to order by price.
	@Test
	void TestOrderingLeftPanel()
	{
		assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(0).name().equals("testDrink1"));
		((MenuPanel)aMCM.getLeft()).changeOrder(Order.PRICE);
		assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(0).name().equals("testDrink3"));
		assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(0).name().equals("testSnack1"));
		assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(0).name().equals("testMain1"));
		
		
		ObservableList<Item> l = ((MenuPanel)aMCM.getLeft()).getListView().getItems();
		double prevPrice = -1;
		for(Item item : l) {
			if(prevPrice >= 0) {
				assertTrue(item.price() > prevPrice);
			}
			prevPrice = item.price();
		}
		aMenu=null;
	}
	@Test
	void TestOrderingCenterPanel()
		{
		assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(0).name().equals("testMain1"));
		((MenuPanel)aMCM.getCenter()).changeOrder(Order.PRICE);
		assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(0).name().equals("testMain3"));
		assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(0).name().equals("testDrink1"));
		assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(0).name().equals("testSnack1"));
		
		
		ObservableList<Item> l = ((MenuPanel)aMCM.getCenter()).getListView().getItems();
		double prevPrice = -1;
		for(Item item : l) {
			if(prevPrice >= 0) {
				assertTrue(item.price() > prevPrice);
			}
			prevPrice = item.price();
		}
		aMenu=null;
	}
	
	@Test
	void TestOrderingRightPanel()
		{
		assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(0).name().equals("testSnack1"));
		((MenuPanel)aMCM.getRight()).changeOrder(Order.PRICE);
		assertTrue(((MenuPanel)aMCM.getRight()).getListView().getItems().get(0).name().equals("testSnack3"));
		
		assertTrue(((MenuPanel)aMCM.getLeft()).getListView().getItems().get(0).name().equals("testDrink1"));
		assertTrue(((MenuPanel)aMCM.getCenter()).getListView().getItems().get(0).name().equals("testMain1"));
		
		ObservableList<Item> l = ((MenuPanel)aMCM.getRight()).getListView().getItems();
		double prevPrice = -1;
		for(Item item : l) {
			if(prevPrice >= 0) {
				assertTrue(item.price() > prevPrice);
			}
			prevPrice = item.price();
		}
		aMenu=null;
	}

	
}

