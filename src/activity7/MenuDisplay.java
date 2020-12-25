package activity7;


import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Skeleton code that illustrates the general layout
 * expected. Modify as necessary.
 */
public class MenuDisplay extends Application
{
	private Menu aMenu = Menu.instance();
	
	/**
	 * Launches the application.
	 * @param pArgs This program takes no argument.
	 */
	public static void main(String[] pArgs) 
	{
        launch(pArgs);
    }
    
    @Override
    public void start(Stage pStage) 
    {
    	
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
    	Combo chips_guac = Combo.getCombo("Combo 1", chips, MenuItem.getMenuItem("guacamole", Category.SNACK, 6f));
    	Item special_pasta = new SpecialItem(gf_pasta, 10);

    	MenuConfigurationManager main = new MenuConfigurationManager(Config.DRINK_SNACK, aMenu);
    	//select combination of Config.DRINK_SNACK, Config.CHEAP_EXPENSIVE, Config.APP_MAIN_DESSERT, Config.MOST_POPULAR_DIETS
        pStage.setScene(new Scene(new VBox(main, createControl(main, Config.DRINK_SNACK, Config.CHEAP_EXPENSIVE, Config.APP_MAIN_DESSERT))));
        pStage.show();
        
        //one way to show changes in GUI
        //comment out this section and uncomment the code in Client.java if you choose to view that way
        aMenu.addItem(coffee);
        aMenu.addItem(juice);
        aMenu.addItem(tea);
        aMenu.addItem(soup);
        aMenu.addItem(salad);
        aMenu.addItem(pasta);
        aMenu.addItem(gf_pasta);
        aMenu.addItem(pizza);
        aMenu.addItem(burger);
        aMenu.addItem(chocolate_cake);
        aMenu.addItem(cheesecake);
        aMenu.addItem(chips);
        aMenu.addItem(fruit_cup);
        aMenu.addItem(chips_guac);
        aMenu.addItem(special_pasta);
    }
    
    
    private static HBox createControl(MenuConfigurationManager main, Config... pConfigurations)
    {
    	assert pConfigurations.length > 0;
    	HBox control = new HBox();
    	control.setPadding(new Insets(5));
        control.setAlignment(Pos.CENTER);
    	ToggleGroup group = new ToggleGroup();

        for( Config configuration : pConfigurations )
        {
        	RadioButton button = new RadioButton(configuration.toString());
        	button.setOnAction(e -> {main.configurationChanged(configuration);});
        	button.setPadding(new Insets(5));
        	button.setToggleGroup(group);
            control.getChildren().add(button);
        }
        ((RadioButton)control.getChildren().get(0)).setSelected(true);
        return control;
    }
    
    /**
     * Allow client to show Menu from Client class
     * @param pMenu
     */
    public void setMenu(Menu pMenu) {
    	aMenu = pMenu;
    }
    
    public void show() {
    	launch();
    }
    
}
