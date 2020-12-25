package activity7;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*Commands for running a particular configuration. This is an application of the functional Command pattern
 * Functional implementation was chosen because we only have a few constant configurations, there's no need
 * to allow users to pass in parameters dynamically and save data in a Command object, we can just save the constant data
 * in the command codes themselves, like what title to have for the three panels, and which Items to have in each of them.
 */

public class ConfigUtils
{
	private static HashMap<Position, MenuPanel> setup = new HashMap<Position, MenuPanel>(); 
	
	/* Helper method to create a panel. Returns one of the three panels to be on the GUI.
	 * @param aMenu: Menu the panel is based on
	 * @param pPos: position of the panel on the GUI
	 * @param pWidth: width of the panel
	 * @param pTitle: title of the panel
	 * @param aSubList: a list containing all contents to be shown on the panel.
	 * @param aConfig: configuration the panel is subjected to.
	 * @param aOrder: order in which contents in panel are to be sorted
	 */
	private static MenuPanel createPanel(Menu aMenu, Position pPos,int pWidth, String pTitle, Stream<Item> aSubList, Config aConfig, Order aOrder)
    {	
    	MenuPanel panel = new MenuPanel(aMenu,pPos,pTitle,aSubList, aConfig, aOrder);
    	panel.setStyle(MenuConfigurationManager.STYLE);
    	panel.setPrefWidth(pWidth);
    	return panel;
    }

	
	/* These are the commands that were run when switching to a config.
	 * Essentially, they take a menu, then return a hashmap that indicates
	 * what panel is to be on which position.
	 * All information we need to know to create a panel, like what title is 
	 * to be used for which panel and what to include in each panel, is specified
	 * in the command themselves.
	 */
//-------------COMMANDS----------------------------------------------------------------------------
	
	/*
	 * In the left: All drinks.
	 * In the centre: All Main.
	 * In the right: All Snack.
	 */
	public static HashMap<Position,MenuPanel> drinksSnacksCommand(Menu pMenu){
		
		Stream<Item> leftList = FilteringUtils.filterCategory(Category.DRINK)
									.filter(pMenu.items());
		Stream<Item> centreList = FilteringUtils.filterCategory(Category.MAIN)
									.filter(pMenu.items());
		Stream<Item> rightList = FilteringUtils.filterCategory(Category.SNACK)
									.filter(pMenu.items());
		
		setup.put(Position.LEFT, createPanel(pMenu,Position.LEFT,MenuConfigurationManager.WIDTH_SIDE,"Drinks",leftList,Config.DRINK_SNACK,Order.ALPHABETICAL));
		setup.put(Position.CENTRE, createPanel(pMenu,Position.CENTRE,MenuConfigurationManager.WIDTH_SIDE,"Main",centreList,Config.DRINK_SNACK,Order.ALPHABETICAL));
		setup.put(Position.RIGHT, createPanel(pMenu,Position.RIGHT,MenuConfigurationManager.WIDTH_SIDE,"Snack",rightList,Config.DRINK_SNACK,Order.ALPHABETICAL));
		return setup;
	}
	
	/*
	 * In the left: First 10 cheapest items.
	 * In the centre: All Items.
	 * In the right: All combo.
	 */
	public static HashMap<Position,MenuPanel> cheapExpensiveCommand(Menu pMenu){
		
		Stream<Item> leftList = FilteringUtils.firstXItems(10)
										.filter(pMenu.items().sorted(OrderingUtil.OrderbyPrice()));
		Stream<Item> centreList = pMenu.items();
		
		Stream<Item> rightList = FilteringUtils.filterCategory(Category.COMBO)
										.filter(pMenu.items());
		
		setup.put(Position.LEFT, createPanel(pMenu,Position.LEFT,MenuConfigurationManager.WIDTH_SIDE,"10 Cheapest Items",leftList,Config.CHEAP_EXPENSIVE,Order.PRICE));
		setup.put(Position.CENTRE, createPanel(pMenu,Position.CENTRE,MenuConfigurationManager.WIDTH_SIDE,"All Items",centreList,Config.CHEAP_EXPENSIVE,Order.ALPHABETICAL));
		setup.put(Position.RIGHT, createPanel(pMenu,Position.RIGHT,MenuConfigurationManager.WIDTH_SIDE,"Combo",rightList,Config.CHEAP_EXPENSIVE,Order.PRICE));
		return setup;
	}
	
	/*
	 * In the left: All APPETIZER.
	 * In the centre: All MAIN.
	 * In the right: All DESSERT.
	 */
	public static HashMap<Position, MenuPanel> appMainDessertCommand(Menu pMenu){
		
		Stream<Item> leftList = FilteringUtils.filterCategory(Category.APPETIZER)
										.filter(pMenu.items());
		Stream<Item> centreList = FilteringUtils.filterCategory(Category.MAIN)
										.filter(pMenu.items());
		Stream<Item> rightList = FilteringUtils.filterCategory(Category.DESSERT)
										.filter(pMenu.items());

		setup.put(Position.LEFT, createPanel(pMenu,Position.LEFT,MenuConfigurationManager.WIDTH_SIDE,"Appetizer",leftList,Config.APP_MAIN_DESSERT,Order.ALPHABETICAL));
		setup.put(Position.CENTRE, createPanel(pMenu,Position.CENTRE,MenuConfigurationManager.WIDTH_SIDE,"Main",centreList,Config.APP_MAIN_DESSERT,Order.ALPHABETICAL));
		setup.put(Position.RIGHT, createPanel(pMenu,Position.RIGHT,MenuConfigurationManager.WIDTH_SIDE,"Dessert",rightList,Config.APP_MAIN_DESSERT,Order.ALPHABETICAL));

		return setup;
	}
	
	// Finds the 3 diet categories with the most items and shows them
	public static HashMap<Position, MenuPanel> topDietsCommand(Menu pMenu){
		List<Diet> top3 = pMenu.items().flatMap(item -> item.diets().stream())
				.collect(Collectors.groupingBy(Function.identity()))
				.entrySet()
				.stream()
				.sorted((diet1, diet2) -> diet1.getValue().size() - diet2.getValue().size())
				.limit(3)
				.map(set -> set.getKey())
				.collect(Collectors.toList());
		Stream<Item> leftList = FilteringUtils.filterDiets(top3.get(0)).filter(pMenu.items());
		Stream<Item> centreList = FilteringUtils.filterDiets(top3.get(1)).filter(pMenu.items());
		Stream<Item> rightList = FilteringUtils.filterDiets(top3.get(2)).filter(pMenu.items());
		
		setup.put(Position.LEFT, createPanel(pMenu,Position.LEFT,MenuConfigurationManager.WIDTH_SIDE,top3.get(0).toString(),leftList,Config.MOST_POPULAR_DIETS,Order.ALPHABETICAL));
		setup.put(Position.CENTRE, createPanel(pMenu,Position.CENTRE,MenuConfigurationManager.WIDTH_SIDE,top3.get(1).toString(),centreList,Config.MOST_POPULAR_DIETS,Order.ALPHABETICAL));
		setup.put(Position.RIGHT, createPanel(pMenu,Position.RIGHT,MenuConfigurationManager.WIDTH_SIDE,top3.get(2).toString(),rightList,Config.MOST_POPULAR_DIETS,Order.ALPHABETICAL));

		return setup;
	}
}
