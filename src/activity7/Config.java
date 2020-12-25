package activity7;

import java.util.HashMap;
import java.util.function.Function;

/* Contains enums for 4 different configs, each config is tied to a "command" or essentially a Function to be executed 
 * when switching to this config. The commands are specified in the ConfigUtils class. The first 3 are the default options. 
 * To show another combination, change pStage.setScene to include the desired configs.
 * To add new configs, simply
 * add a new enum here and add the corresponding command in the ConfigUtils class.
 */
public enum Config { 
	DRINK_SNACK("Drinks/Snacks",ConfigUtils::drinksSnacksCommand), 
	CHEAP_EXPENSIVE("Cheap/Expensive",ConfigUtils::cheapExpensiveCommand),
	APP_MAIN_DESSERT("Appetizer/Main/Dessert", ConfigUtils::appMainDessertCommand),
	MOST_POPULAR_DIETS("Diets With Most Options", ConfigUtils::topDietsCommand);
	
	private String pName;
	private Function<Menu,HashMap<Position,MenuPanel>> aCommand;
	
	Config(String aName,Function<Menu,HashMap<Position,MenuPanel>> aOperation) {
		pName = aName;
		aCommand=aOperation;
	}
	
	@Override
	public String toString() {
		return pName;
	}
	
	public HashMap<Position, MenuPanel> runConfig(Menu pMenu) {
		return aCommand.apply(pMenu);
	}
	
	public static Config fromString(String string) {
		for(Config cfg : Config.values()){
			if(cfg.name().equalsIgnoreCase(string)){
				return cfg;
			}
		}
		throw new IllegalArgumentException("Configuration " + string + " not found");
	}
}

