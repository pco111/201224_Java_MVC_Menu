package activity7;

import javafx.application.Application;

public class Client{
	
	
	public static void main (String [] args) {
		
		//example items
    	SizeableItem coffee = new SizeableItem(MenuItem.getMenuItem("coffee", Category.DRINK, 3f));
    	SizeableItem juice = new SizeableItem(MenuItem.getMenuItem("fruit juice", Category.DRINK, 3f));
    	SizeableItem tea = new SizeableItem(MenuItem.getMenuItem("tea", Category.DRINK, 2.5f));
    	MenuItem soup = MenuItem.getMenuItem("soup", Category.APPETIZER, 6f, Diet.VEGETARIAN);
    	SizeableItem salad = new SizeableItem(MenuItem.getMenuItem("salad", Category.APPETIZER, 7f, Diet.VEGETARIAN));
    	MenuItem pasta = MenuItem.getMenuItem("pasta", Category.MAIN, 13f);
    	MenuItem gf_pasta = MenuItem.getMenuItem("pasta", Category.MAIN, 15f, Diet.GLUTEN_FREE, Diet.VEGETARIAN);
    	MenuItem pizza = MenuItem.getMenuItem("pizza", Category.MAIN, 14f);
    	MenuItem burger = MenuItem.getMenuItem("hamburger", Category.MAIN, 15f);
    	MenuItem chocolate_cake = MenuItem.getMenuItem("chocolate cake", Category.DESSERT, 7f);
    	MenuItem cheesecake = MenuItem.getMenuItem("cheesecake", Category.DESSERT, 8f);
    	MenuItem chips = MenuItem.getMenuItem("chips", Category.SNACK, 3f, Diet.VEGETARIAN);
    	MenuItem fruit_cup = MenuItem.getMenuItem("fruit salad", Category.SNACK, 5.5f, Diet.VEGAN, Diet.VEGETARIAN);
    	//Item chips_guac = new Combo((MenuItem)chips, MenuItem.getMenuItem("guacamole", Category.SNACK, 6f), 30);
		
		Menu menu = Menu.instance();
		/* Another way to modify Menu and show GUI 
		MenuDisplay test = new MenuDisplay();
		test.setMenu(menu);
		
		menu.addItem(chips);
		menu.addItem(coffee);
		menu.addItem(soup);
		menu.addItem(gf_pasta);
		
		test.show();
		*/
	}
	

}
