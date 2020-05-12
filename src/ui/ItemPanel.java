package ui;

import services.ItemService;

public class ItemPanel extends ViewPanel {
	
	ItemService itemService;

	public ItemPanel(ItemService itemService) {
		super();
		activeViewName.setText("Items");
		this.itemService = itemService;
	}
	
}
