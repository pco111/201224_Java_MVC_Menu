package activity7;

import java.util.Collections;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class MenuPanel extends VBox implements Observer
{
	
	private Menu aMenu;
	private ObservableList<Item> list; //the built-in list of aListView
	private ListView<Item> aListView; //the list that is actually being displayed, could be a filtered version of localStorage
	private Position aPos;
	private final Config aConfig;
	private Order aOrder;

	public MenuPanel(Menu pMenu,Position pPos,String pTitle, Stream<Item> pSubList, Config pConfig, Order pOrder)
	{
		
		assert pMenu != null;
		aConfig=pConfig;
		aOrder=pOrder;
		aPos=pPos;
		aMenu=pMenu;
		list = FXCollections.observableArrayList();
		pSubList.sorted(aOrder.getComparator())
				.forEach(item -> list.add(item));
		aListView = new ListView<Item>(list);
    	Label title = new Label(pTitle);
    	title.setStyle("-fx-font-weight: bold");
    	getChildren().add(title);
		getChildren().add(aListView);
	}

	protected ListView<Item> getListView()
	{
		return this.aListView;
	}
	
	public void changeOrder(Order pOrder) {
		aOrder=pOrder;
		Collections.sort(list,aOrder.getComparator());
		
		getChildren().remove(aListView);
		aListView=new ListView<Item>(list);
		getChildren().add(aListView);
	}

	@Override
	public void menuChanged()
	{		
		ListView<Item> newView = ((MenuPanel)aConfig.runConfig(aMenu).get(aPos)).getListView();
		list=FXCollections.observableArrayList(newView.getItems());
		getChildren().remove(aListView);
		aListView=new ListView<Item>(newView.getItems());
		getChildren().add(aListView);
	}
	
}
