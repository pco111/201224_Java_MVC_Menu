package activity7;

import java.util.HashMap;

import javafx.scene.layout.BorderPane;


public class MenuConfigurationManager extends BorderPane implements MenuConfigurationObserver
{
	
	public static final int WIDTH_SIDE = 150;
	public static final int WIDTH_CENTER = 200;
	public static final String STYLE = 
			"-fx-pref-height: 300px; -fx-border-width: 1; -fx-border-color: black;" +
					"-fx-background-color: lightgrey; -fx-padding: 5px; -fx-alignment: top-center";

	private Config aConfig;
	private Menu aMenu;
	
	public MenuConfigurationManager(Config pConfig, Menu pMenu) {
		aConfig = pConfig;
		aMenu = pMenu;
		setConfiguration();
	}
	
	public Config getConfig() {
		return aConfig;
	}
	
	
	private void setConfiguration() {
		HashMap<Position,MenuPanel> panels=aConfig.runConfig(aMenu);
		aMenu.removeAllObserver();
		for(Observer aPanel : panels.values()) {
			aMenu.addObserver(aPanel);
		}
			
		setLeft(panels.get(Position.LEFT));
	    setCenter(panels.get(Position.CENTRE));
	    setRight(panels.get(Position.RIGHT));	
	}

	@Override
	public void configurationChanged(Config pConfig)
	{
		aConfig = pConfig;
		setConfiguration();
	}
}
